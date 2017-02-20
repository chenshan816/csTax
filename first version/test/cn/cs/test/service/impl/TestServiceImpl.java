package cn.cs.test.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cs.test.dao.TestDao;
import cn.cs.test.entity.Dept;
import cn.cs.test.entity.Person;
import cn.cs.test.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {
	@Resource
	private TestDao testDao;
	@Override
	public void say() {
		System.out.println("service完成输出");
	}

	@Override
	public void save(Dept dept) {
		testDao.save(dept);
	}

	@Override
	public Person findById(Serializable id) {
		return testDao.findById(id);
	}

}
