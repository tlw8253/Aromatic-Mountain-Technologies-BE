package com.amt.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.amt.app.Constants;

@Entity
@Table(name = Constants.csAddressTable)

public class Address implements Constants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = csAddressTblAddressId)
	private int addressTypeId = 0;

	@Column(name = csAddressTblAddressLine1, length = 100, nullable = false)
	private String addressLine1 = "";

	@Column(name = csAddressTblAddressLine2, length = 100, nullable = true)
	private String addressLine2 = "";
	
	@Column(name = csAddressTblAddressCity, length = 100, nullable = false)
	private String addressCity = "";

	@Column(name = csAddressTblAddressState, length = 5, nullable = false)
	private String addressState = "";

	@Column(name = csAddressTblAddressZipCode, length = 15, nullable = false)
	private String addressZipCode = "";

	@Column(name = csAddressTblAddressCountry, length = 100, nullable = false)
	private String addressCountry = "United States";

	@ManyToOne
	@JoinColumn(name = csAddressTypeTblAddressTypeId, nullable = false)
	private AddressType addressType;		//

	@ManyToOne
	@JoinColumn(name = csUserTblId, nullable = false)
	private User user;		//
	
	public Address() {
		super();
	}

	public Address(String addressLine1, String addressLine2, String addressCity, String addressState, String addressZipCode) {
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressZipCode = addressZipCode;
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

	public AddressType getAddressType() {
		return addressType;
	}

	public User getUser() {
		return user;
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
		this.addressCountry = addressCountry;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressCity, addressCountry, addressLine1, addressLine2, addressState, addressType,
				addressTypeId, addressZipCode, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(addressCity, other.addressCity) && Objects.equals(addressCountry, other.addressCountry)
				&& Objects.equals(addressLine1, other.addressLine1) && Objects.equals(addressLine2, other.addressLine2)
				&& Objects.equals(addressState, other.addressState) && Objects.equals(addressType, other.addressType)
				&& addressTypeId == other.addressTypeId && Objects.equals(addressZipCode, other.addressZipCode)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Address [addressTypeId=" + addressTypeId + ", addressLine1=" + addressLine1 + ", addressLine2="
				+ addressLine2 + ", addressCity=" + addressCity + ", addressState=" + addressState + ", addressZipCode="
				+ addressZipCode + ", addressCountry=" + addressCountry + ", addressType=" + addressType + ", user="
				+ user + "]";
	}



	
	
}
