package com.amt.dto;

import java.util.Objects;

//DTO - Data Transfer Object
// This is to be used in the controller class to get parameters by body
public class AddUserDTO {
	private String username = "";
	private String password = "";
	private String passwordSalt = "";
	private String firstName = "";
	private String lastName = "";
	private String email = "";
	private String employeeRole = "";
	private String userType = "";

	/*
	 * private int userId = 0; Cannot be set auto generated in the database private
	 */

	public AddUserDTO() {
		super();
	}

	public AddUserDTO(String username, String password, String firstName, String lastName, String email, String userType, String employeeRole) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userType = userType;
		this.employeeRole = employeeRole;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getEmployeeRole() {
		return employeeRole;
	}

	public String getUserType() {
		return userType;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, employeeRole, firstName, lastName, password, passwordSalt, userType, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddUserDTO other = (AddUserDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(employeeRole, other.employeeRole)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(passwordSalt, other.passwordSalt)
				&& Objects.equals(userType, other.userType) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "AddUserDTO [username=" + username + ", password=" + password + ", passwordSalt=" + passwordSalt
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", employeeRole="
				+ employeeRole + ", userType=" + userType + "]";
	}


	
	
	

}// END Class
