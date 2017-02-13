package cn.cs.test.entity;

/**
 * RolePri entity. @author MyEclipse Persistence Tools
 */

public class RolePri implements java.io.Serializable {

	// Fields

	private RolePriId id;

	// Constructors

	/** default constructor */
	public RolePri() {
	}

	/** full constructor */
	public RolePri(RolePriId id) {
		this.id = id;
	}

	// Property accessors

	public RolePriId getId() {
		return this.id;
	}

	public void setId(RolePriId id) {
		this.id = id;
	}

}