package cn.cs.test.dao;

import java.io.Serializable;

import cn.cs.test.entity.Dept;
import cn.cs.test.entity.Person;

public interface TestDao {
	//保存人员
		public void save(Dept dept);
		
		//根据id查询人员
		public Person findById(Serializable id);
}
