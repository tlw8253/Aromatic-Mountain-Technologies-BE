package com.amt.dto;

import java.util.Objects;

//DTO - Data Transfer Object
//This is to be used in the controller class to get parameters by body
public class AddPhoneNumberDTO {

	private String phoneNumber = "";
	private String phoneNumberType;

	//private String phoneNumberCountryCode = "01";	not allowed to be set
	
	public AddPhoneNumberDTO() {
		super();
	}

	public AddPhoneNumberDTO(String phoneNumber, String phoneNumberType) {
		this.phoneNumber = phoneNumber;
		this.phoneNumberType = phoneNumberType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPhoneNumberType() {
		return phoneNumberType;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPhoneNumberType(String phoneNumberType) {
		this.phoneNumberType = phoneNumberType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(phoneNumber, phoneNumberType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddPhoneNumberDTO other = (AddPhoneNumberDTO) obj;
		return Objects.equals(phoneNumber, other.phoneNumber) && Objects.equals(phoneNumberType, other.phoneNumberType);
	}

	@Override
	public String toString() {
		return "AddPhoneNumberDTO [phoneNumber=" + phoneNumber + ", phoneNumberType=" + phoneNumberType + "]";
	}


	
	
	
	
	
}//END Class
