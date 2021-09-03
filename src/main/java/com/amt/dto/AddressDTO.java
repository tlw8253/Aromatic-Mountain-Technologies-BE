package com.amt.dto;

import java.util.Objects;

import com.amt.app.Constants;

public class AddressDTO implements Constants {

	private int addressTypeId = 0;
	private String addressLine1 = "";
	private String addressLine2 = "";
	private String addressCity = "";
	private String addressState = "";
	private String addressZipCode = "";
	private String addressCountry = "United States";
	private String addressType;
	private String username;

		
	public AddressDTO() {
		super();
	}

	public AddressDTO (String addressLine1, String addressLine2, String addressCity, String addressState, String addressZipCode, String addressType, String username) {
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressZipCode = addressZipCode;
		this.addressType = addressType;
		this.username = username;
	}


	public int getAddressTypeId() {
		return addressTypeId;
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


	public String getAddressCountry() {
		return addressCountry;
	}


	public String getAddressType() {
		return addressType;
	}


	public String getUsername() {
		return username;
	}


	public void setAddressTypeId(int addressTypeId) {
		this.addressTypeId = addressTypeId;
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


	public void setAddressCountry(String addressCountry) {
		//this.addressCountry = addressCountry;
	}


	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public int hashCode() {
		return Objects.hash(addressCity, addressCountry, addressLine1, addressLine2, addressState, addressType,
				addressTypeId, addressZipCode, username);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressDTO other = (AddressDTO) obj;
		return Objects.equals(addressCity, other.addressCity) && Objects.equals(addressCountry, other.addressCountry)
				&& Objects.equals(addressLine1, other.addressLine1) && Objects.equals(addressLine2, other.addressLine2)
				&& Objects.equals(addressState, other.addressState) && Objects.equals(addressType, other.addressType)
				&& addressTypeId == other.addressTypeId && Objects.equals(addressZipCode, other.addressZipCode)
				&& Objects.equals(username, other.username);
	}


	@Override
	public String toString() {
		return "AddressDTO [addressTypeId=" + addressTypeId + ", addressLine1=" + addressLine1 + ", addressLine2="
				+ addressLine2 + ", addressCity=" + addressCity + ", addressState=" + addressState + ", addressZipCode="
				+ addressZipCode + ", addressCountry=" + addressCountry + ", addressType=" + addressType + ", username="
				+ username + "]";
	}

	
	
	
	
}//END Class
