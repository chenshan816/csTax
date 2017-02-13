package cn.cs.test.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Privilege entity. @author MyEclipse Persistence Tools
 */

public class Privilege implements java.io.Serializable {

	// Fields

	private String priId;
	private String name;
	private Set rolePris = new HashSet(0);

	// Constructors

	/** default constructor */
	public Privilege() {
	}

	/** full constructor */
	public Privilege(String name, Set rolePris) {
		this.name = name;
		this.rolePris = rolePris;
	}

	// Property accessors

	public String getPriId() {
		return this.priId;
	}

	public void setPriId(String priId) {
		this.priId = priId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getRolePris() {
		return this.rolePris;
	}

	public void setRolePris(Set rolePris) {
		this.rolePris = rolePris;
	}

}