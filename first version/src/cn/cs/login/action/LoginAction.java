package cn.cs.login.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.cs.core.constant.Constant;
import cn.cs.nsfw.user.entity.User;
import cn.cs.nsfw.user.entity.UserRole;
import cn.cs.nsfw.user.service.UserService;

public class LoginAction {
	@Resource
	private UserService userService;
	private User user;
	//登录失败信息回显
	private String loginResult;
	
	//跳转到登录页面
	public String loginUI(){
		
		return "loginUI";
	}
	
	//登录检测
	public String login(){
		if(user != null){
			
			if(StringUtils.isNotBlank(user.getAccount()) && StringUtils.isNotBlank(user.getPassword())){
				//2、通过帐号和密码查询用户表；如果查询有记录说登录成功；否则登录失败跳转到登录页面并且提示用户登录失败信息
				List<User> userList = userService.findByAccountAndPassword(user);
				if(userList == null ||userList.size() <=0){
					loginResult = "帐号或密码错误";
				}else{
					//2.1、登录成功
					//2.1.1、将用户信息保存到session中
					User loginUser =  userList.get(0);
					ServletActionContext.getRequest().getSession().setAttribute(Constant.User, loginUser);
					//2.1.2、权限查询
					List<UserRole> userRoles = userService.getUserRoleByUserId(loginUser.getId());
					ServletActionContext.getRequest().getSession().setAttribute(Constant.UserRole, userRoles);
					//2.1.3、将用户登录记录到日志文件
					Log log = LogFactory.getLog(getClass());
					log.info("用户名称为："+loginUser.getName()+"登录系统");
					//2.1.4、重定向跳转到首页 
					return "home";
				}
			}else{
				loginResult = "帐号或密码不能为空";
			}
			
		}else{
			loginResult = "请输入帐号和密码";
		}
		//2.2、登录失败
			//跳转到登录页面并且提示用户登录失败信息
		return loginUI();
	}

	//注销
	public String logout(){
		//清除session中用户信息
		//ServletActionContext.getRequest().getSession().removeAttribute(Constant.User);
		ActionContext.getContext().getSession().remove(Constant.User);
		return "logout";
	}
	
	//跳转到无权限访问的页面
	public String noPermissionUI(){
		return "noPermissionUI";
	}
	
	//获取用户的账户和密码
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	//提示信息回显
	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	
}
