package com.amt.dto;

import java.util.Objects;

//DTO - Data Transfer Object
//This is to be used in the controller class to get parameters by body
public class AddAddressDTO {

	private String loginUsername = "";
	private String loginPassword = "";
	private String addressLine1 = "";
	private String addressLine2 = "";
	private String addressCity = "";
	private String addressState = "";
	private String addressZipCode = "";
	private String addressType;	
	
	public AddAddressDTO() {
		super();
	}

	public AddAddressDTO(String loginUsername, String loginPassword, String addressLine1, String addressLine2, String addressCity, String addressState, String addressZipCode, String addressType) {
		this.loginUsername = loginUsername;
		this.loginPassword = loginPassword;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressZipCode = addressZipCode;
		this.addressType = addressType;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public String getAddressState() {
		return addressState;
	}

	public String getAddressZipCode() {
		return addressZipCode;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	public void setAddressZipCode(String addressZipCode) {
		this.addressZipCode = addressZipCode;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressCity, addressLine1, addressLine2, addressState, addressType, addressZipCode,
				loginPassword, loginUsername);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddAddressDTO other = (AddAddressDTO) obj;
		return Objects.equals(addressCity, other.addressCity) && Objects.equals(addressLine1, other.addressLine1)
				&& Objects.equals(addressLine2, other.addressLine2) && Objects.equals(addressState, other.addressState)
				&& Objects.equals(addressType, other.addressType)
				&& Objects.equals(addressZipCode, other.addressZipCode)
				&& Objects.equals(loginPassword, other.loginPassword)
				&& Objects.equals(loginUsername, other.loginUsername);
	}

	@Override
	public String toString() {
		return "AddAddressDTO [loginUsername=" + loginUsername + ", loginPassword=" + "********" + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", addressCity=" + addressCity + ", addressState="
				+ addressState + ", addressZipCode=" + addressZipCode + ", addressType=" + addressType + "]";
	}


	
	
	
}//END Class
