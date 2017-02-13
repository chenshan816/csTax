package cn.cs.test.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Dept entity. @author MyEclipse Persistence Tools
 */

public class Dept implements java.io.Serializable {

	// Fields

	private String detpId;
	private Org org;
	private String name;
	private Set employees = new HashSet(0);

	// Constructors

	/** default constructor */
	public Dept() {
	}

	/** minimal constructor */
	public Dept(Org org, String name) {
		this.org = org;
		this.name = name;
	}

	/** full constructor */
	public Dept(Org org, String name, Set employees) {
		this.org = org;
		this.name = name;
		this.employees = employees;
	}

	// Property accessors

	public String getDetpId() {
		return this.detpId;
	}

	public void setDetpId(String detpId) {
		this.detpId = detpId;
	}

	public Org getOrg() {
		return this.org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set employees) {
		this.employees = employees;
	}

}