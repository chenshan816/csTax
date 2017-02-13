package cn.cs.test;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;








import cn.cs.test.entity.Dept;
import cn.cs.test.entity.Person;
import cn.cs.test.service.TestService;

public class TestMerge {
	
	ClassPathXmlApplicationContext ctx;
	@Before
	public void testSpring() {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	
	public void testHibernate(){
		SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		//保存提交
		session.save(new Person("人员1"));
		transaction.commit();
		session.close();
	}
	
	@Test
	public void testServiceAndDao(){
		TestService ts = (TestService) ctx.getBean("testService");
		
	}
	
}
