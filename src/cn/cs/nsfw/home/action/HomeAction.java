package cn.cs.nsfw.home.action;

import cn.cs.core.action.BaseAction;

public class HomeAction extends BaseAction {
	
	//跳转到纳税系统首页
	public String frame(){
		return "frame";
	}
	//跳转到纳税系统首页-顶部
	public String top(){
		return "top";
	}
	//跳转到纳税系统首页-左边菜单
	public String left(){
		return "left";
	}
}
