package cn.cs.test.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.cs.test.dao.TestDao;
import cn.cs.test.entity.Dept;
import cn.cs.test.entity.Person;

public class TestDaoImpl extends HibernateDaoSupport implements TestDao {

	@Override
	public void save(Dept dept) {
		this.getHibernateTemplate().save(dept);
		
	}

	@Override
	public Person findById(Serializable id) {
//		this.getHibernateTemplate().save(new Person("测试只读操作"));
		return this.getHibernateTemplate().get(Person.class, id);
	}

}
