package cn.cs.nsfw.role.entity;

import java.io.Serializable;

public class RolePrivilege implements Serializable{
	
	private RolePrivilegeId id;
	
	public RolePrivilege() {
		
	}

	public RolePrivilege(RolePrivilegeId id) {
		super();
		this.id = id;
	}

	public RolePrivilegeId getId() {
		return id;
	}

	public void setId(RolePrivilegeId id) {
		this.id = id;
	}
	
}