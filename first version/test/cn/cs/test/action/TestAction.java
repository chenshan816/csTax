package cn.cs.test.action;

import javax.annotation.Resource;

import cn.cs.test.service.TestService;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {
	@Resource
	private TestService testService;
	
	public String execute(){
		testService.say();
		return SUCCESS;
	}
}
