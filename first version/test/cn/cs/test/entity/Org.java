package cn.cs.test.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Org entity. @author MyEclipse Persistence Tools
 */

public class Org implements java.io.Serializable {

	// Fields

	private String orgId;
	private String name;
	private Set depts = new HashSet(0);

	// Constructors

	/** default constructor */
	public Org() {
	}

	/** minimal constructor */
	public Org(String name) {
		this.name = name;
	}

	/** full constructor */
	public Org(String name, Set depts) {
		this.name = name;
		this.depts = depts;
	}

	// Property accessors

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getDepts() {
		return this.depts;
	}

	public void setDepts(Set depts) {
		this.depts = depts;
	}

}