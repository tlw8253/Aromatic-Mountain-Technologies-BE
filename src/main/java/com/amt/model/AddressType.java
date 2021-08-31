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
@Table(name = Constants.csAddressTypeTable)


public class AddressType implements Constants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = csAddressTypeTblAddressTypeId)
	private int addressTypeId = 0;

	@Column(name = csAddressTypeTblAddressType, length = 50, nullable = false, unique = true)
	private String addressType = "";

	@Column(name = csAddressTypeTblAddressTypeDesc, length = 150, nullable = false)
	private String addressTypeDesc = "";

	public AddressType() {
		super();
	}

	public AddressType(String addressType, String addressTypeDesc) {
		this.addressType = addressType;
		this.addressTypeDesc = addressTypeDesc;
	}

	public int getAddressTypeId() {
		return addressTypeId;
	}

	public String getAddressType() {
		return addressType;
	}

	public String getAddressTypeDesc() {
		return addressTypeDesc;
	}

	public void setAddressTypeId(int addressTypeId) {
		this.addressTypeId = addressTypeId;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public void setAddressTypeDesc(String addressTypeDesc) {
		this.addressTypeDesc = addressTypeDesc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressType, addressTypeDesc, addressTypeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressType other = (AddressType) obj;
		return Objects.equals(addressType, other.addressType) && Objects.equals(addressTypeDesc, other.addressTypeDesc)
				&& addressTypeId == other.addressTypeId;
	}

	@Override
	public String toString() {
		return "AddressType [addressTypeId=" + addressTypeId + ", addressType=" + addressType + ", addressTypeDesc="
				+ addressTypeDesc + "]";
	}
	
	
	
}
