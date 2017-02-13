package cn.cs.nsfw.complain.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.cs.core.action.BaseAction;
import cn.cs.core.constant.Constant;
import cn.cs.core.util.PageResult;
import cn.cs.core.util.QueryHelper;
import cn.cs.nsfw.complain.entity.Complain;
import cn.cs.nsfw.complain.entity.ComplainReply;
import cn.cs.nsfw.complain.service.ComplainService;
import freemarker.template.utility.DateUtil;


/**
 * 投诉管理系统的界面管理
 * 列表展示
 * 受理页面
 * @author dell
 *
 */
public class ComplainAction extends BaseAction {
	@Resource
	private ComplainService complainService;
	private Complain complain;
	private String startTime;
	private String endTime;
	private ComplainReply  reply;
	//回显信息
	private String strState;
	private String strTitle;
	//统计投诉数
	private Map<String,Object> statisticMap;
	//列表
	public String listUI() throws Exception{
		//加载投诉状态
		ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
		QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
		if(StringUtils.isNotBlank(startTime)){
			//解码
			startTime = URLDecoder.decode(startTime,"utf-8");
			queryHelper.addCondition("c.compTime >= ?", DateUtils.parseDate(startTime, "yyyy-MM-dd HH:mm"));
		}
		if(StringUtils.isNotBlank(endTime)){
			//解码
			endTime = URLDecoder.decode(endTime,"utf-8");
			queryHelper.addCondition("c.compTime <= ?", DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm"));
		}
		if(complain != null){
			if(StringUtils.isNotBlank(complain.getCompTitle())){
				complain.setCompTitle(URLDecoder.decode(complain.getCompTitle(),"utf-8"));
				queryHelper.addCondition("c.compTitle like ?", "%"+complain.getCompTitle()+"%");
			}
			if(StringUtils.isNotBlank(complain.getState())){
				queryHelper.addCondition("c.state=?", complain.getState());
			}
		}
		//排序，按照状态已未受理优先(升序)、投诉时间升序排序，
		queryHelper.addOrderByProperty("c.state", Constant.ORDERBY_ASC);
		queryHelper.addOrderByProperty("c.compTime", Constant.ORDERBY_ASC);
		setPageResult(complainService.getPageResult(getPageNo(), getPageSize(), queryHelper));
		return "listUI";
	}
	
	//跳转到受理页面
	public String dealUI(){
		ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
		if(complain != null){
			strTitle = complain.getCompTitle();
			strState = complain.getState();
			complain = complainService.findById(complain.getCompId());
		}
		return "dealUI";
	}
	
	//受理投诉
	public String deal(){
		if(complain != null)
		{
			//1.更新投诉的状态为已受理
			Complain tmp = complainService.findById(complain.getCompId());
			if(!Complain.COMPLAIN_STATE_DONE.equals(tmp.getState())){
				tmp.setState(Complain.COMPLAIN_STATE_DONE);
			}
			//2.保存回复信息
			if(reply != null){
				reply.setTComplain(tmp);
				reply.setReplyTime(new Timestamp(new Date().getTime()));
				tmp.getComplainReplies().add(reply);
			}
			complainService.update(tmp);
		}
		
		return "list";
	}
	
	//跳转到统计
	public String annualStatisticChartUI(){
		return "annualStatisticChartUI";
	}
	
	//数据统计
	public String annualStatisticChart(){
		//获取年份
		int year = Integer.valueOf(ServletActionContext.getRequest().getParameter("year"));
		if(year == 0){
			year = Calendar.getInstance().get(Calendar.YEAR);
		}
		//统计该年份每个月的投诉数
		List<Map> chartData = complainService.getAnnualStatisticData(year);
		statisticMap = new HashMap<String,Object>();
		statisticMap.put("msg", "success");
		statisticMap.put("chartData", chartData);
		
		//输出json格式字符串
		return "annualStatisticChart";
	}
	
	//get、set方法
	public Complain getComplain() {
		return complain;
	}
	public void setComplain(Complain complain) {
		this.complain = complain;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public ComplainReply getReply() {
		return reply;
	}

	public void setReply(ComplainReply reply) {
		this.reply = reply;
	}

	public String getStrState() {
		return strState;
	}

	public void setStrState(String strState) {
		this.strState = strState;
	}

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

	public Map<String, Object> getStatisticMap() {
		return statisticMap;
	}


	
}
