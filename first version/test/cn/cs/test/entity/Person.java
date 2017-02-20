package cn.cs.test.entity;

import java.io.Serializable;

public class Person implements Serializable{
	private String id;//UUID保存，跨系统时不会冲突
	private String name;
	public Person() {
	}
	public Person(String name){
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
