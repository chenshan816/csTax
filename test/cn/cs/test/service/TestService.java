package cn.cs.test.service;

import java.io.Serializable;

import cn.cs.test.entity.Dept;
import cn.cs.test.entity.Person;

public interface TestService {
	//测试输出
	public void say();
	
	//保存人员
	public void save(Dept dept);
	
	//根据id查询人员
	public Person findById(Serializable id);
}
