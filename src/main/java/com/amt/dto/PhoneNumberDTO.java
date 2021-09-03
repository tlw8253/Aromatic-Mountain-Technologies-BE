package com.amt.dto;

import java.util.Objects;

import com.amt.app.Constants;

//DTO - Data Transfer Object
//This is to be used in passing the full dto to the dao
public class PhoneNumberDTO implements Constants {

	
	private int phoneNumbereId = 0;
	private String phoneNumberCountryCode = "01";
	private String phoneNumber = "";
	private String phoneNumberType;
	private String username;


		
	public PhoneNumberDTO() {
		super();
	}

	public PhoneNumberDTO (String phoneNumber, String phoneNumberType, String username) {
		this.phoneNumber = phoneNumber;
		this.phoneNumberType = phoneNumberType;
		this.username = username;
	}

	public int getPhoneNumbereId() {
		return phoneNumbereId;
	}

	public String getPhoneNumberCountryCode() {
		return phoneNumberCountryCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPhoneNumberType() {
		return phoneNumberType;
	}

	public String getUsername() {
		return username;
	}

	public void setPhoneNumbereId(int phoneNumbereId) {
		this.phoneNumbereId = phoneNumbereId;
	}

	public void setPhoneNumberCountryCode(String phoneNumberCountryCode) {
		this.phoneNumberCountryCode = phoneNumberCountryCode;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPhoneNumberType(String phoneNumberType) {
		this.phoneNumberType = phoneNumberType;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		return Objects.hash(phoneNumber, phoneNumberCountryCode, phoneNumberType, phoneNumbereId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneNumberDTO other = (PhoneNumberDTO) obj;
		return Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(phoneNumberCountryCode, other.phoneNumberCountryCode)
				&& Objects.equals(phoneNumberType, other.phoneNumberType) && phoneNumbereId == other.phoneNumbereId
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "PhoneNumberDTO [phoneNumbereId=" + phoneNumbereId + ", phoneNumberCountryCode=" + phoneNumberCountryCode
				+ ", phoneNumber=" + phoneNumber + ", phoneNumberType=" + phoneNumberType + ", username=" + username
				+ "]";
	}


	
	
	
}//END Class
