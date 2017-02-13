package cn.cs.nsfw.consult.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.opensymphony.xwork2.ActionContext;

import cn.cs.core.action.BaseAction;
import cn.cs.core.constant.Constant;
import cn.cs.core.util.QueryHelper;
import cn.cs.nsfw.consult.entity.ConsultReply;
import cn.cs.nsfw.consult.service.ConsultService;
import cn.cs.nsfw.consult.entity.Consult;

/**
 * 咨询处理模块
 * 咨询管理页面
 * 跳转回复页面
 * 保存回复
 * @author dell
 *
 */
public class ConsultAction extends BaseAction {
	@Resource
	private ConsultService consultService;
	private Consult consult;
	private ConsultReply reply;
	private String startTime;
	private String endTime;
	private String strTitle;
	private String strState;
	
	//咨询界面
	public String listUI() throws Exception{
		ActionContext.getContext().getContextMap().put("consultStateMap", Consult.CONSULT_STATE_MAP);
		//查找
		QueryHelper queryHelper = new QueryHelper(Consult.class, "c");
		//根据时间查询
		if(StringUtils.isNotBlank(startTime))
		{
			//解码
			startTime = URLDecoder.decode(startTime,"utf-8");
			queryHelper.addCondition("c.consTime >= ?", DateUtils.parseDate(startTime,"yyyy-MM-dd HH:mm"));
		}
		if(StringUtils.isNotBlank(endTime))
		{
			//解码
			endTime = URLDecoder.decode(endTime,"utf-8");
			queryHelper.addCondition("c.consTime <= ?", DateUtils.parseDate(endTime,"yyyy-MM-dd HH:mm"));
		}
		//根据标题查询
		if(consult != null){
			if(StringUtils.isNotBlank(consult.getConsTitle())){
				consult.setConsTitle(URLDecoder.decode(consult.getConsTitle(),"utf-8"));
				queryHelper.addCondition("c.consTitle like ?", "%"+consult.getConsTitle()+"%");
			}
			if(StringUtils.isNotBlank(consult.getState())){
				consult.setState(URLDecoder.decode(consult.getState(),"utf-8"));
				queryHelper.addCondition("c.state=?", consult.getState());
			}
		}
		queryHelper.addOrderByProperty("c.state", Constant.ORDERBY_ASC);
		queryHelper.addOrderByProperty("c.consTime", Constant.ORDERBY_ASC);
		setPageResult(consultService.getPageResult(getPageNo(), getPageSize(), queryHelper));
		return "listUI";
	}
	//跳转回复界面
	public String dealUI(){
		ActionContext.getContext().getContextMap().put("consultStateMap", Consult.CONSULT_STATE_MAP);
		strState = consult.getState();
		strTitle = consult.getConsTitle();
		consult = consultService.findById(consult.getConsId());
		return "dealUI";
	}
	//保存咨询的状态
	public String deal(){
		if(consult != null){
			Consult tmp = consultService.findById(consult.getConsId());
			reply.setReplyTime(new Timestamp(new Date().getTime()));
			reply.setConsult(tmp);
			tmp.setConsultReply(reply);
			tmp.setState(Consult.CONSULT_STATE_DONE);
			consultService.update(tmp);
		}
		return "list";
	}
	
	
	
	//get、set方法
	public Consult getConsult() {
		return consult;
	}

	public void setConsult(Consult consult) {
		this.consult = consult;
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
	public String getStrTitle() {
		return strTitle;
	}
	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}
	public String getStrState() {
		return strState;
	}
	public void setStrState(String strState) {
		this.strState = strState;
	}
	public ConsultReply getReply() {
		return reply;
	}
	public void setReply(ConsultReply reply) {
		this.reply = reply;
	}
	
}
