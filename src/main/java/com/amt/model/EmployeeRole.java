package com.amt.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.amt.app.Constants;

@Entity
@Table(name = Constants.csEmployeeRolesTable)

public class EmployeeRole implements Constants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = csEmployeeRolesTblEmployeeRoleId)
	private int employeeRoleId = 0;

	@Column(name = csEmployeeRolesTblEmployeeRole, length = 50, nullable = false, unique = true)
	private String employeeRole = "";
	
	@Column(name = csEmployeeRolesTblEmployeeRoleDesc, length = 150, nullable = false)
	private String employeeRoleDesc = "";

	
	public EmployeeRole() {
		super();
	}

	public EmployeeRole(String employeeRole, String employeeRoleDesc) {
		this.employeeRole = employeeRole;
		this.employeeRoleDesc = employeeRoleDesc;
	}

	public int getUserRoleId() {
		return employeeRoleId;
	}

	public String getUserRole() {
		return employeeRole;
	}

	public void setUserRoleId(int employeeRoleId) {
		this.employeeRoleId = employeeRoleId;
	}

	public void setUserRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}

	public String getUserRoleDesc() {
		return employeeRoleDesc;
	}

	public void setUserRoleDesc(String employeeRoleDesc) {
		this.employeeRoleDesc = employeeRoleDesc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(employeeRole, employeeRoleDesc, employeeRoleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeRole other = (EmployeeRole) obj;
		return Objects.equals(employeeRole, other.employeeRole) && Objects.equals(employeeRoleDesc, other.employeeRoleDesc)
				&& employeeRoleId == other.employeeRoleId;
	}

	@Override
	public String toString() {
		return "UserRole [employeeRoleId=" + employeeRoleId + ", employeeRole=" + employeeRole + ", employeeRoleDesc=" + employeeRoleDesc + "]";
	}


	
	
	
	
}
