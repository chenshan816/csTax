package cn.cs.core.permission.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import cn.cs.core.permission.PermissionCheck;
import cn.cs.nsfw.role.entity.RolePrivilege;
import cn.cs.nsfw.user.entity.User;
import cn.cs.nsfw.user.entity.UserRole;
import cn.cs.nsfw.user.service.UserService;

public class PermissionCheckImpl implements PermissionCheck {

	
	@Override
	public boolean isAccessiable(List<UserRole> userRoles, String privilegeCode) {
		//查询user的角色信息
		if(userRoles != null && userRoles.size() > 0)
		for(UserRole userRole:userRoles){
			Set<RolePrivilege> rolePrivileges = userRole.getId().getRole().getRolePrivilege();
			if(rolePrivileges != null && rolePrivileges.size() >0){
				for(RolePrivilege rolePrivilege:rolePrivileges){
					if(privilegeCode.equals(rolePrivilege.getId().getCode())){
						return true;
					}
				}
			}
		}
		return false;
	}

}
