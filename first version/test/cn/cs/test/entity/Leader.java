package cn.cs.test.entity;

/**
 * Leader entity. @author MyEclipse Persistence Tools
 */

public class Leader implements java.io.Serializable {

	// Fields

	private String empId;
	private Employee employee;
	private String detpId;
	private String name;
	private Integer position;

	// Constructors

	/** default constructor */
	public Leader() {
	}

	/** minimal constructor */
	public Leader(Employee employee, String name) {
		this.employee = employee;
		this.name = name;
	}

	/** full constructor */
	public Leader(Employee employee, String detpId, String name,
			Integer position) {
		this.employee = employee;
		this.detpId = detpId;
		this.name = name;
		this.position = position;
	}

	// Property accessors

	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getDetpId() {
		return this.detpId;
	}

	public void setDetpId(String detpId) {
		this.detpId = detpId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

}