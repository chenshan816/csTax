package cn.cs.home.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import cn.cs.core.action.BaseAction;
import cn.cs.core.constant.Constant;
import cn.cs.core.util.PageResult;
import cn.cs.core.util.QueryHelper;
import cn.cs.nsfw.complain.entity.Complain;
import cn.cs.nsfw.complain.service.ComplainService;
import cn.cs.nsfw.consult.entity.Consult;
import cn.cs.nsfw.consult.service.ConsultService;
import cn.cs.nsfw.info.entity.Info;
import cn.cs.nsfw.info.service.InfoService;
import cn.cs.nsfw.user.entity.User;
import cn.cs.nsfw.user.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends BaseAction {
	@Resource
	private ComplainService complainService;
	@Resource
	private InfoService infoService;
	@Resource
	private ConsultService consultService;
	//获取被投诉的用户列表
	@Resource
	private UserService userService;
	
	private Complain comp;
	private Info info;
	private Consult consult;
	//投诉信息获取
	private List<Complain> complainList;
	//咨询信息获取
	private List<Consult> consultList;

	
	
	//跳转到首页
	public String execute(){
		//加载信息列表
		ActionContext.getContext().getContextMap().put("infoType", Constant.INFO_TYPE_MAP);
		QueryHelper queryHelper = new QueryHelper(Info.class, "i");
		queryHelper.addCondition("i.state=? ", Info.INFO_STATE_PUBLIC);
		queryHelper.addOrderByProperty("i.createTime", Constant.ORDERBY_DESC);
		setPageResult(infoService.getPageResult(1, 5, queryHelper));
		//加载投诉信息
		ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
		QueryHelper queryHelper1 = new QueryHelper(Complain.class, "c");
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(Constant.User);
		queryHelper1.addCondition("c.compName = ?", user.getName());
		queryHelper1.addOrderByProperty("c.compTime", Constant.ORDERBY_ASC);
		complainList = complainService.getPageResult(1, 6, queryHelper1).getItems();
		//加载咨询信息
		ActionContext.getContext().getContextMap().put("consultStateMap", Consult.CONSULT_STATE_MAP);
		QueryHelper queryHelper2 = new QueryHelper(Consult.class, "c");
		queryHelper2.addCondition("c.consName = ?", user.getName());
		queryHelper2.addOrderByProperty("c.consTime", Constant.ORDERBY_ASC);
		consultList = consultService.getPageResult(1, 6, queryHelper2).getItems();
		return "home";
	}
	//跳转投诉页面
	public String complainAddUI(){
		return "complainAddUI";
	}
	//获得被投诉人列表
	public void getUserJson() throws Exception{
		//获取部门
		String dept = ServletActionContext.getRequest().getParameter("dept");
		//根据部门查询用户列表
		if(StringUtils.isNotBlank(dept)){
			QueryHelper queryHelper = new QueryHelper(User.class, "u");
			queryHelper.addCondition("u.dept = ?", dept);
			List<User> userList = userService.findObjects(queryHelper);
			//返回Json
			JSONObject json = new JSONObject();
			json.put("msg", "success");
			json.accumulate("userList", userList);
			//输出json
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(json.toString().getBytes("utf-8"));
			outputStream.close();
		}
	}
	
	//保存我的投诉
	public void complainAdd() throws Exception{
		if(comp != null){
			comp.setState(Complain.COMPLAIN_STATE_UNDONE);
			comp.setCompTime(new Timestamp(new Date().getTime()));
			complainService.save(comp);
			
			//输出投诉成功
			//输出json
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write("success".getBytes("utf-8"));
			outputStream.close();
		}
	}
	
	
	//具体信息展示页面
	public String infoViewUI(){
		ActionContext.getContext().getContextMap().put("infoType", Constant.INFO_TYPE_MAP);
		if(info != null){
			info = infoService.findById(info.getInfoId());
		}
		return "infoViewUI";
	}
	
	//具体投诉页面展示
	public String complainViewUI(){
		ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
		if(comp !=null){
			comp = complainService.findById(comp.getCompId());
			
		}
		return "complainViewUI";
	}
	
	
	//添加咨询页面
	public String consultAddUI(){
		return "consultAddUI";
	}
	//咨询展示页面
	public String consultViewUI(){
		ActionContext.getContext().getContextMap().put("consultStateMap", Consult.CONSULT_STATE_MAP);
		if(consult !=null){
			consult = consultService.findById(consult.getConsId());
			
		}
		return "consultViewUI";
	}
	//保存我的咨询
	public void consultAdd() throws Exception{
		if(consult != null){
			consult.setState(Consult.CONSULT_STATE_NEW);
			consult.setConsTime(new Timestamp(new Date().getTime()));
			consultService.save(consult);
		}
		//输出投诉成功
		//输出json
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write("success".getBytes("utf-8"));
		outputStream.close();
	}
	//get、set方法
	public Complain getComp() {
		return comp;
	}
	public void setComp(Complain comp) {
		this.comp = comp;
	}
	public List<Complain> getComplainList() {
		return complainList;
	}
	public void setComplainList(List<Complain> complainList) {
		this.complainList = complainList;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public List<Consult> getConsultList() {
		return consultList;
	}
	public void setConsultList(List<Consult> consultList) {
		this.consultList = consultList;
	}
	public Consult getConsult() {
		return consult;
	}
	public void setConsult(Consult consult) {
		this.consult = consult;
	}
	
	
	
}
