package cn.cs.core.permission;

import java.util.List;

import cn.cs.nsfw.user.entity.UserRole;

public interface PermissionCheck {
	/**
	 * 查询用户是否有权限
	 * @param user 登录用户信息
	 * @param privilegeCode  权限系统标识符
	 * @return  是否有权限进入 true--有权限  false--无权限
	 */
	public boolean isAccessiable(List<UserRole> userRoles,String privilegeCode);
}

