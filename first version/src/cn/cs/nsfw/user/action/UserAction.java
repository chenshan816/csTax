package cn.cs.nsfw.user.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.commons.lang.StringUtils;

import cn.cs.core.action.BaseAction;
import cn.cs.core.constant.Constant;
import cn.cs.core.exception.ServiceException;
import cn.cs.core.exception.SysException;
import cn.cs.core.util.PageResult;
import cn.cs.core.util.QueryHelper;
import cn.cs.nsfw.role.entity.Role;
import cn.cs.nsfw.role.service.RoleService;
import cn.cs.nsfw.user.entity.User;
import cn.cs.nsfw.user.entity.UserRole;
import cn.cs.nsfw.user.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户管理界面功能
 * 列表界面
 * 跳转到新增页面
 * 保存新增
 * 跳转到编辑页面
 * 保存编辑
 * 删除操作
 * 批量删除
 * 导入、导出
 * 帐号唯一性校验
 * @author dell
 *
 */
public class UserAction extends BaseAction {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	private List<User> userList;
	private User user;
	//头像上传
	private File headImg;
	private String headImgContextType;
	private String headImgFileName;
	//批量删除
	
	//导入文件
	private File userExcel;
	private String userExcelContextType;
	private String userExcelFileName;
	
	//用户角色信息
	private String[] userRoleIds;
	//保存搜索信息
	private String strName;
	
	
	
	//列表界面
	public String listUI() throws Exception{
		//从业务层取出所有用户
		QueryHelper queryHelper = new QueryHelper(User.class, "u");
		if(user !=null){
			if(StringUtils.isNotBlank(user.getName())){
				user.setName(URLDecoder.decode(user.getName(),"utf-8"));
				queryHelper.addCondition("u.name like ?", "%"+user.getName()+"%");
			}
		}
		setPageResult(userService.getPageResult(getPageNo(),getPageSize(),queryHelper));
		return "listUI";
	}
	//跳转到新增页面 
	public String addUI() throws ServiceException{
		//加载角色列表
		ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());
		strName = user.getName();
		user = null;
		return "addUI";
	}
	//保存新增 
	public String add(){
			if(user != null){
				saveHeadImag();
				//保存用户和用户对应的角色
				userService.saveUserAndRole(user,userRoleIds);
			}
		user.setName(strName);
		return "list";
	}
	//跳转到编辑页面  
	public String editUI() throws ServiceException{
		//显示信息
		if(user != null && user.getId() != null){
			ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());
			strName = user.getName();
			user = userService.findById(user.getId());
			//处理角色回显
			List<UserRole> list = userService.getUserRoleByUserId(user.getId());
			if(list != null && list.size()>0){
				userRoleIds = new String[list.size()];
				for(int i=0;i<list.size();i++){
					userRoleIds[i] = list.get(i).getId().getRole().getRoleId();
				}
			}
		}
		return "editUI";
	}
	//保存编辑     
	public String edit(){
		if(user !=null){
			saveHeadImag();
			userService.updateUserAndRole(user,userRoleIds);
		}
		user.setName(strName);
		return "list";
	}
	//删除操作     
	public String delete(){
		if(user !=null && user.getId() != null)
			userService.delete(user.getId());
		return "list";
	}
	//批量删除    
	public String deleteSelected(){
		if(selectedRow != null){
			for(String id : selectedRow){
				userService.delete(id);
			}
		}
		return "list";
	}
	//导出功能
	public void exportExcel(){
		try {
			userList = userService.findObjects();
			//导出
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-execl");
			response.setHeader("Content-Disposition", "attachment;filename="+new String("用户列表.xls".getBytes(),"ISO-8859-1"));
			//输出流
			ServletOutputStream outputStream = response.getOutputStream();
			userService.exportExcel(userList,outputStream);
			if(outputStream != null){
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//导入功能
	public String importExcel(){
		if(userExcel !=null){
			if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
				userService.importExcel(userExcel,userExcelFileName);
			}
		}
		return "list";
	}
	//帐号唯一性校验
	public void verifyAccount(){
		try {
			if(user != null && StringUtils.isNotBlank(user.getAccount())){
				User userByAccount = userService.findByAccount(user);
				String strResult = "true";
				if(userByAccount != null){
					//用户已经存在
					strResult ="false";
				}
				//输出
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(strResult.getBytes());
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//保存头像
	private void saveHeadImag(){
		//传头像
		try {
			if(headImg != null){
				//1.把图片保存放upload/user
					//1.1绝对路径
				String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
					//1.2保存图片+防止重名+中英文设置
				String expandName = headImgFileName.substring(headImgFileName.lastIndexOf("."));
				String fileName = UUID.randomUUID().toString().replaceAll("-", "")+expandName;
					//1.3复制文件
				FileUtils.copyFile(headImg, new File(filePath,fileName));
				//2.设置图片路径至数据库
				user.setHeadImg("user/"+fileName);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	
	
	//列表获取
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	//头像获取
	public File getHeadImg() {
		return headImg;
	}
	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}
	public String getHeadImgContextType() {
		return headImgContextType;
	}
	public void setHeadImgContextType(String headImgContextType) {
		this.headImgContextType = headImgContextType;
	}
	public String getHeadImgFileName() {
		return headImgFileName;
	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
	//导入文件的获取
	public File getUserExcel() {
		return userExcel;
	}
	public void setUserExcel(File userExcel) {
		this.userExcel = userExcel;
	}
	public String getUserExcelContextType() {
		return userExcelContextType;
	}
	public void setUserExcelContextType(String userExcelContextType) {
		this.userExcelContextType = userExcelContextType;
	}
	public String getUserExcelFileName() {
		return userExcelFileName;
	}
	public void setUserExcelFileName(String userExcelFileName) {
		this.userExcelFileName = userExcelFileName;
	}
	//用户角色关系
	public String[] getUserRoleIds() {
		return userRoleIds;
	}
	public void setUserRoleIds(String[] userRoleIds) {
		this.userRoleIds = userRoleIds;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}

	
}
