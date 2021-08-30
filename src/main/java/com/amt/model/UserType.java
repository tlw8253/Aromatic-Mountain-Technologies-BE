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
@Table(name = Constants.csUserTypeTable)

public class UserType implements Constants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = csUserTypeTblUserTypeId)
	private int userTypeId = 0;

	@Column(name = csUserTypeTblUserType, length = 50, nullable = false, unique = true)
	private String userType = "";

	@Column(name = csUserTypeTblUserTypeDesc, length = 150, nullable = false)
	private String userTypeDesc = "";

		
	public UserType() {
		super();
	}

	public UserType(String userType, String userTypeDesc) {
		this.userType = userType;
		this.userTypeDesc = userTypeDesc;
	}

	public int getUserTypeId() {
		return userTypeId;
	}

	public String getUserType() {
		return userType;
	}

	public String getUserTypeDesc() {
		return userTypeDesc;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setUserTypeDesc(String userTypeDesc) {
		this.userTypeDesc = userTypeDesc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userType, userTypeDesc, userTypeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserType other = (UserType) obj;
		return Objects.equals(userType, other.userType) && Objects.equals(userTypeDesc, other.userTypeDesc)
				&& userTypeId == other.userTypeId;
	}

	@Override
	public String toString() {
		return "UserType [userTypeId=" + userTypeId + ", userType=" + userType + ", userTypeDesc=" + userTypeDesc + "]";
	}

	

	
	
	
}
