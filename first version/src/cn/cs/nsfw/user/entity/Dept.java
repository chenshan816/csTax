package cn.cs.nsfw.user.entity;

import java.io.Serializable;

public class Dept implements Serializable {
	private String id;
	private String deptName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
