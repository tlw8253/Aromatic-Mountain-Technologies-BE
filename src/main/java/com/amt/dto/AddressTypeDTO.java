package com.amt.dto;

import com.amt.app.Constants;

public class AddressTypeDTO extends AddOrEditDTO implements Constants{
	//Class attributes are store in the parent in HashMap tables.	
	//Stored in hashmaps in the super class
	
	//
	//###
	public AddressTypeDTO() {
		super();
	}

	public AddressTypeDTO(String type, String typeDesc) {
		setAddressType(type);
		setAddressTypeDescription(typeDesc);
	}

	public String getAddressTypeId() {
		return super.getDataElement(csAddressTypeTblAddressTypeId);
	}
	public int getAddressTypeIdAsInt() {
		return super.getIntDataElement(csAddressTypeTblAddressTypeId);
	}

	public String getAddressType() {
		return super.getDataElement(csAddressTypeTblAddressType);
	}

	public String getAddressTypeDescription() {
		return super.getDataElement(csAddressTypeTblAddressTypeDesc);
	}

	public void setAddressTypeId(int typeId) {
		super.setDataElement(csAddressTypeTblAddressTypeId, typeId);
	}
	public void setAddressTypeId(String typeId) {
		super.setDataElement(csAddressTypeTblAddressTypeId, typeId);
	}

	public void setAddressType(String type) {
		super.setDataElement(csAddressTypeTblAddressType, type);
	}

	public void setAddressTypeDescription(String typeDesc) {
		super.setDataElement(csAddressTypeTblAddressTypeDesc, typeDesc);
	}

	@Override
	public String toString() {
		return super.toString();
	}


	
	
}
