package com.amt.dto;

import com.amt.app.Constants;

public class UserDTO extends AddOrEditDTO implements Constants {
	//Class attributes are store in the parent in HashMap tables.	

	//private int userId = 0;
	//private String username = "";
	//private String password = "";
	//private String passwordSalt = "";
	//private String firstName = "";
	//private String lastName = "";
	//private String email = "";	
	//private String userType = "";
	//private String employeeRole = "";

	//
	//###
	public UserDTO() {
		super();
	}

	public UserDTO(String username, String password, String firstName, String lastName, String email, String userType, String employeeRole) {
		setUsername(username);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setUserType(userType);
		setEmployeeRole(employeeRole);
	}

	public String getUserId() {
		return super.getDataElement(csUserTblUserId);
	}
	public int getUserIdAsInt() {
		return super.getIntDataElement(csUserTblUserId);
	}

	public String getUsername() {
		return super.getDataElement(csUserTblUsername);
	}

	public String getPassword() {
		return super.getDataElement(csUserTblPassword);
	}
	public String getPasswordSalt() {
		return super.getDataElement(csUserTblPasswordSalt);
	}

	public String getFirstName() {
		return super.getDataElement(csUserTblFirstName);
	}

	public String getLastName() {
		return super.getDataElement(csUsrTblLastName);
	}

	public String getEmail() {
		return super.getDataElement(csUserTblEmail);
	}

	public String getUserType() {
		return super.getDataElement(csUserTblUserType);
	}

	public String getUserTypeId() {
		return super.getDataElement(csUserTblUserTypeId);
	}
	public int getUserTypeIdByInt() {
		return super.getIntDataElement(csUserTblUserTypeId);
	}
	
	
	public String getEmployeeRole() {
		return super.getDataElement(csEmployeeRolesTblEmployeeRole);
	}
	public int getEmployeeRoleId() {
		return super.getIntDataElement(csEmployeeRolesTblEmployeeRoleId);
	}

	///////////////////////////////////////////////////////////////////////////
	public void setUserId(int userId) {
		super.setDataElement(csUserTblUserId, userId);
	}

	public void setUsername(String username) {
		super.setDataElement(csUserTblUsername, username);
	}

	public void setPassword(String password) {
		super.setDataElement(csUserTblPassword, password);
	}
	public void setPasswordSalt(String passwordSalt) {
		super.setDataElement(csUserTblPasswordSalt, passwordSalt);
	}

	public void setFirstName(String firstName) {
		super.setDataElement(csUserTblFirstName, firstName);
	}

	public void setLastName(String lastName) {
		super.setDataElement(csUsrTblLastName, lastName);
	}

	public void setEmail(String email) {
		super.setDataElement(csUserTblEmail, email);
	}

	public void setEmployeeRoleId(int userRoleId) {
		super.setDataElement(csEmployeeRolesTblEmployeeRoleId, userRoleId);
	}

	public void setEmployeeRole(String employeeRole) {
		super.setDataElement(csEmployeeRolesTblEmployeeRole, employeeRole.toUpperCase());
	}

	public void setUserTypeId(String userTypeId) {
		super.setDataElement(csUserTblUserTypeId, userTypeId);
	}
	public void setUserTypeId(int userTypeId) {
		super.setDataElement(csUserTblUserTypeId, userTypeId);
	}

	public void setUserType(String userType) {
		super.setDataElement(csUserTblUserType, userType);
	}

	
	@Override
	public String toString() {
		return super.toString();
	}


	
	
}
