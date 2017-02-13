package cn.cs.nsfw.role.dao.impl;

import org.hibernate.Query;

import cn.cs.core.dao.impl.BaseDaoImpl;
import cn.cs.nsfw.role.dao.RoleDao;
import cn.cs.nsfw.role.entity.Role;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	
	@Override
	public void deleteRolePrivilegeByRoleId(String roleId) {
		Query query = getSession().createQuery("delete from RolePrivilege where id.role.roleId = ?");
		query.setParameter(0, roleId);
		query.executeUpdate();
	}

}
