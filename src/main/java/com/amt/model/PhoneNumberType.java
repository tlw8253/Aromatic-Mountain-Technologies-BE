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
@Table(name = Constants.csPhoneNumberTypeTable)


public class PhoneNumberType implements Constants {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = csPhoneNumberTypeTblPhoneNumberTypeId)
	private int phoneNumberTypeId = 0;

	@Column(name = csPhoneNumberTypeTblPhoneNumberType, length = ciRoleTypeLen, nullable = false, unique = true)
	private String phoneNumberType = "";

	@Column(name = csPhoneNumberTypeTblPhoneNumberTypeDesc, length = ciRoleTypeDescLen, nullable = false)
	private String phoneNumberTypeDesc = "";

	public PhoneNumberType() {
		super();
	}
	
	public PhoneNumberType(String phoneNumberType, String phoneNumberTypeDesc) {
		this.phoneNumberType = phoneNumberType;
		this.phoneNumberTypeDesc = phoneNumberTypeDesc;		
	}

	public int getPhoneNumberTypeId() {
		return phoneNumberTypeId;
	}

	public String getPhoneNumberType() {
		return phoneNumberType;
	}

	public String getPhoneNumberTypeDesc() {
		return phoneNumberTypeDesc;
	}

	public void setPhoneNumberTypeId(int phoneNumberTypeId) {
		this.phoneNumberTypeId = phoneNumberTypeId;
	}

	public void setPhoneNumberType(String phoneNumberType) {
		this.phoneNumberType = phoneNumberType;
	}

	public void setPhoneNumberTypeDesc(String phoneNumberTypeDesc) {
		this.phoneNumberTypeDesc = phoneNumberTypeDesc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(phoneNumberType, phoneNumberTypeDesc, phoneNumberTypeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneNumberType other = (PhoneNumberType) obj;
		return Objects.equals(phoneNumberType, other.phoneNumberType)
				&& Objects.equals(phoneNumberTypeDesc, other.phoneNumberTypeDesc)
				&& phoneNumberTypeId == other.phoneNumberTypeId;
	}

	@Override
	public String toString() {
		return "PhoneNumberType [phoneNumberTypeId=" + phoneNumberTypeId + ", phoneNumberType=" + phoneNumberType
				+ ", phoneNumberTypeDesc=" + phoneNumberTypeDesc + "]";
	}

	
	
	
	
}
