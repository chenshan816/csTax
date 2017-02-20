package cn.cs.test.entity;

/**
 * EmpRole entity. @author MyEclipse Persistence Tools
 */

public class EmpRole implements java.io.Serializable {

	// Fields

	private EmpRoleId id;
	private Integer state;

	// Constructors

	/** default constructor */
	public EmpRole() {
	}

	/** minimal constructor */
	public EmpRole(EmpRoleId id) {
		this.id = id;
	}

	/** full constructor */
	public EmpRole(EmpRoleId id, Integer state) {
		this.id = id;
		this.state = state;
	}

	// Property accessors

	public EmpRoleId getId() {
		return this.id;
	}

	public void setId(EmpRoleId id) {
		this.id = id;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}