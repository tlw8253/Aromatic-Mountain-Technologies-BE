package com.amt.dto;

import com.amt.app.Constants;

public class EmployeeRoleDTO extends AddOrEditDTO implements Constants{
	//Class attributes are store in the parent in HashMap tables.	

	/*Stored in hashmaps in the super class
	//ers_user_role table
	*/
	
	//
	//###
	public EmployeeRoleDTO() {
		super();
	}

	public EmployeeRoleDTO(String role, String roleDesc) {
		setRole(role);
		setRoleDescription(roleDesc);
	}

	public String getRoleId() {
		return super.getDataElement(csEmployeeRolesTblEmployeeRoleId);
	}
	public int getRoleIdAsInt() {
		return super.getIntDataElement(csEmployeeRolesTblEmployeeRoleId);
	}

	public String getRole() {
		return super.getDataElement(csEmployeeRolesTblEmployeeRole);
	}

	public String getRoleDescription() {
		return super.getDataElement(csEmployeeRolesTblEmployeeRoleDesc);
	}

	public void setRoleId(int roleId) {
		super.setDataElement(csEmployeeRolesTblEmployeeRoleId, roleId);
	}
	public void setRoleId(String roleId) {
		super.setDataElement(csEmployeeRolesTblEmployeeRoleId, roleId);
	}

	public void setRole(String role) {
		super.setDataElement(csEmployeeRolesTblEmployeeRole, role);
	}

	public void setRoleDescription(String roleDesc) {
		super.setDataElement(csEmployeeRolesTblEmployeeRoleDesc, roleDesc);
	}

	@Override
	public String toString() {
		return super.toString();
	}


	
	
}
