package cn.cs.nsfw.user.dao;

import java.io.Serializable;
import java.util.List;

import cn.cs.core.dao.BaseDao;
import cn.cs.nsfw.user.entity.User;
import cn.cs.nsfw.user.entity.UserRole;

public interface UserDao extends BaseDao<User> {

	public User findByAccount(User user);

	public void saveUserRole(UserRole userRole);
	//根据Id删除用户角色表历史信息
	public void deleteUserRoleByUserId(Serializable userId);
	//根据用户的id获得角色信息
	public List<UserRole> getUserRoleByUserId(String id);
	//根据用户帐号密码获取用户信息
	public List<User> findByAccountAndPassword(User user);
	
}
