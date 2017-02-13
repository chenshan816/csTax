package cn.cs.test.entity;

/**
 * EmpRoleId entity. @author MyEclipse Persistence Tools
 */

public class EmpRoleId implements java.io.Serializable {

	// Fields

	private Employee employee;
	private Role role;

	// Constructors

	/** default constructor */
	public EmpRoleId() {
	}

	/** full constructor */
	public EmpRoleId(Employee employee, Role role) {
		this.employee = employee;
		this.role = role;
	}

	// Property accessors

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EmpRoleId))
			return false;
		EmpRoleId castOther = (EmpRoleId) other;

		return ((this.getEmployee() == castOther.getEmployee()) || (this
				.getEmployee() != null && castOther.getEmployee() != null && this
				.getEmployee().equals(castOther.getEmployee())))
				&& ((this.getRole() == castOther.getRole()) || (this.getRole() != null
						&& castOther.getRole() != null && this.getRole()
						.equals(castOther.getRole())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getEmployee() == null ? 0 : this.getEmployee().hashCode());
		result = 37 * result
				+ (getRole() == null ? 0 : this.getRole().hashCode());
		return result;
	}

}