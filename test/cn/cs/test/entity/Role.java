package cn.cs.test.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

	// Fields

	private String roleId;
	private String name;
	private Set empRoles = new HashSet(0);
	private Set rolePris = new HashSet(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** full constructor */
	public Role(String name, Set empRoles, Set rolePris) {
		this.name = name;
		this.empRoles = empRoles;
		this.rolePris = rolePris;
	}

	// Property accessors

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getEmpRoles() {
		return this.empRoles;
	}

	public void setEmpRoles(Set empRoles) {
		this.empRoles = empRoles;
	}

	public Set getRolePris() {
		return this.rolePris;
	}

	public void setRolePris(Set rolePris) {
		this.rolePris = rolePris;
	}

}