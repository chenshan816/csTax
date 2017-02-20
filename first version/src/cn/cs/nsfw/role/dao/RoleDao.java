package cn.cs.nsfw.role.dao;

import cn.cs.core.dao.BaseDao;
import cn.cs.nsfw.role.entity.Role;
import cn.cs.nsfw.user.entity.User;

public interface RoleDao extends BaseDao<Role>{

	void deleteRolePrivilegeByRoleId(String roleId);

}
