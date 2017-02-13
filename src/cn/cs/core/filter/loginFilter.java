package cn.cs.core.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.cs.core.constant.Constant;
import cn.cs.core.permission.PermissionCheck;
import cn.cs.core.permission.impl.PermissionCheckImpl;
import cn.cs.nsfw.user.entity.User;
import cn.cs.nsfw.user.entity.UserRole;

public class loginFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request= (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		//是否为登录页面请求
		String uri = request.getRequestURI();
		if(!uri.contains("sys/login_")){
			//从session中取出用户信息
			if(request.getSession().getAttribute(Constant.User) != null){
				//已经登录
				//判断事都访问纳税服务子系统
				if(uri.contains("/nsfw/")){
					//判断是否有权限
					List<UserRole> userRoles = (List<UserRole>) request.getSession().getAttribute(Constant.UserRole);
					WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
					PermissionCheck pc = (PermissionCheck) applicationContext.getBean("permissionCheck");
					if(pc.isAccessiable(userRoles,Constant.PRIVILEGE_NSFW)){
						//表示可以
						chain.doFilter(request, response);
					}else{
						//跳转到无权限的提示页面
						response.sendRedirect(request.getContextPath()+"/sys/login_noPermissionUI.action");
					}
				}else{
					chain.doFilter(request, response);
				}
			}else{
				//跳转到登录页面
				response.sendRedirect(request.getContextPath()+"/sys/login_loginUI.action");
			}
		}else{
			//放行
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
