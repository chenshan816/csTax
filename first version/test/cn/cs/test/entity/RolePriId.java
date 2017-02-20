package cn.cs.test.entity;

/**
 * RolePriId entity. @author MyEclipse Persistence Tools
 */

public class RolePriId implements java.io.Serializable {

	// Fields

	private Role role;
	private Privilege privilege;

	// Constructors

	/** default constructor */
	public RolePriId() {
	}

	/** full constructor */
	public RolePriId(Role role, Privilege privilege) {
		this.role = role;
		this.privilege = privilege;
	}

	// Property accessors

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Privilege getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RolePriId))
			return false;
		RolePriId castOther = (RolePriId) other;

		return ((this.getRole() == castOther.getRole()) || (this.getRole() != null
				&& castOther.getRole() != null && this.getRole().equals(
				castOther.getRole())))
				&& ((this.getPrivilege() == castOther.getPrivilege()) || (this
						.getPrivilege() != null
						&& castOther.getPrivilege() != null && this
						.getPrivilege().equals(castOther.getPrivilege())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRole() == null ? 0 : this.getRole().hashCode());
		result = 37 * result
				+ (getPrivilege() == null ? 0 : this.getPrivilege().hashCode());
		return result;
	}

}