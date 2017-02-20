package cn.cs.test.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */

public class Employee implements java.io.Serializable {

	// Fields

	private String empId;
	private Dept dept;
	private String name;
	private Set leaders = new HashSet(0);
	private Set empRoles = new HashSet(0);

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** minimal constructor */
	public Employee(Dept dept, String name) {
		this.dept = dept;
		this.name = name;
	}

	/** full constructor */
	public Employee(Dept dept, String name, Set leaders, Set empRoles) {
		this.dept = dept;
		this.name = name;
		this.leaders = leaders;
		this.empRoles = empRoles;
	}

	// Property accessors

	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Dept getDept() {
		return this.dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getLeaders() {
		return this.leaders;
	}

	public void setLeaders(Set leaders) {
		this.leaders = leaders;
	}

	public Set getEmpRoles() {
		return this.empRoles;
	}

	public void setEmpRoles(Set empRoles) {
		this.empRoles = empRoles;
	}

}