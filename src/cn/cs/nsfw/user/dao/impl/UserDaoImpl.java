package cn.cs.nsfw.user.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;

import cn.cs.core.dao.impl.BaseDaoImpl;
import cn.cs.nsfw.user.dao.UserDao;
import cn.cs.nsfw.user.entity.User;
import cn.cs.nsfw.user.entity.UserRole;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User findByAccount(User user) {
		String sql ="";
		if(user.getId() == "")
			sql ="from User where account = '"+ user.getAccount() +"'";
		else 
			sql ="from User where account = '"+ user.getAccount() +"' and id != '"+ user.getId() +"'";
		User user1;
		try {
			user1 = (User) getHibernateTemplate().find(sql).get(0);
		} catch (Exception e) {
			user1 = null;
		}
		return user1;
	}

	@Override
	public void saveUserRole(UserRole userRole) {
		getHibernateTemplate().save(userRole);
	}

	@Override
	public void deleteUserRoleByUserId(Serializable userId) {
		String sql = "delete from UserRole where id.userId =?";
		Query query = getSession().createQuery(sql);
		query.setParameter(0, userId);
		query.executeUpdate();
	}

	@Override
	public List<UserRole> getUserRoleByUserId(String id) {
		List<UserRole> roles =  new ArrayList<UserRole>();
		String sql = "from UserRole where id.userId =?";
		Query query = getSession().createQuery(sql);
		query.setParameter(0, id);
		return query.list();
	}

	@Override
	public List<User> findByAccountAndPassword(User user) {
		String sql = "from User where account =? and password = ?";
		Query query = getSession().createQuery(sql);
		query.setParameter(0, user.getAccount());
		query.setParameter(1, user.getPassword());
		return query.list();
	}


}
