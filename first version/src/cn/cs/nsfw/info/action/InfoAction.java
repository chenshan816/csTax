package cn.cs.nsfw.info.action;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.cs.core.action.BaseAction;
import cn.cs.core.constant.Constant;
import cn.cs.core.exception.ServiceException;
import cn.cs.core.exception.SysException;
import cn.cs.core.util.PageResult;
import cn.cs.core.util.QueryHelper;
import cn.cs.nsfw.info.entity.Info;
import cn.cs.nsfw.info.service.InfoService;

/**
 * 信息管理界面功能
 * 列表界面
 * 跳转到新增页面
 * 保存新增
 * 跳转到编辑页面
 * 保存编辑
 * 删除操作
 * 批量删除
 * @author dell
 *
 */
public class InfoAction extends BaseAction {
	@Resource
	private InfoService infoService;
	private List<Info> infoList;
	private Info info;
	
	//信息查询
	private String strTitle;
	
	
	//列表界面
	public String listUI() throws Exception{
		//从业务层取出所有信息
		QueryHelper queryHelper = new QueryHelper(Info.class, "i");
		if(info !=null){
			if(StringUtils.isNotBlank(info.getTitle())){
				info.setTitle(URLDecoder.decode(info.getTitle(),"utf-8"));
				queryHelper.addCondition("i.title like ?", "%"+info.getTitle()+"%");
			}
		}
		//分页信息
		queryHelper.addOrderByProperty("i.createTime", Constant.ORDERBY_DESC);
		setPageResult(infoService.getPageResult(getPageNo(),getPageSize(),queryHelper));
		//获取分类信息
		ActionContext.getContext().getContextMap().put("infoTypeMap", Constant.INFO_TYPE_MAP);
		return "listUI";
	}
	//跳转到新增页面 
	public String addUI() throws ServiceException{
		ActionContext.getContext().getContextMap().put("infoTypeMap", Constant.INFO_TYPE_MAP);
		//解决信息被覆盖问题
		strTitle = info.getTitle();
		info = new Info();
		info.setCreateTime(new Timestamp(new Date().getTime()));
		return "addUI";
	}
	//保存新增 
	public String add(){
		if(info != null){
			infoService.save(info);
			info.setTitle(strTitle);
		}
		return "list";
	}
	//跳转到编辑页面  
	public String editUI() throws ServiceException{
		if(info != null && info.getInfoId() != null){
			ActionContext.getContext().getContextMap().put("infoTypeMap", Constant.INFO_TYPE_MAP);
			//解决信息被覆盖问题
			strTitle = info.getTitle();
			info = infoService.findById(info.getInfoId());
		}
		return "editUI";
	}
	//保存编辑     
	public String edit(){
		if(info !=null){
			infoService.update(info);
			info.setTitle(strTitle);
		}
		return "list";
	}
	//删除操作     
	public String delete(){
		if(info !=null && info.getInfoId() != null)
			infoService.delete(info.getInfoId());
		return "list";
	}
	//批量删除    
	public String deleteSelected(){
		if(selectedRow != null){
			for(String id : selectedRow){
				infoService.delete(id);
			}
		}
		return "list";
	}
	
	//异步信息发布
	public void publicInfo(){
		try {
			if(info != null){
				if(info.getInfoId() != "" && info.getState() != ""){
					Info info1 = infoService.findById(info.getInfoId());
					info1.setState(info.getState());
					infoService.update(info1);
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setContentType("text/html");
					ServletOutputStream outputStream = response.getOutputStream();
					outputStream.write("更新状态成功".getBytes("utf-8"));
					outputStream.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取信息
	public List<Info> getInfoList() {
		return infoList;
	}
	public void setInfoList(List<Info> infoList) {
		this.infoList = infoList;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public String getStrTitle() {
		return strTitle;
	}
	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}
}
