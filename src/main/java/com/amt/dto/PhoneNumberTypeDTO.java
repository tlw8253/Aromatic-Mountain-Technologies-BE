package com.amt.dto;

import com.amt.app.Constants;

public class PhoneNumberTypeDTO extends AddOrEditDTO implements Constants{
	//Class attributes are store in the parent in HashMap tables.	

	//
	//###
	public PhoneNumberTypeDTO() {
		super();
	}

	public PhoneNumberTypeDTO(String sPhoneNumberType, String sPhoneNumberTypeDesc) {
		setPhoneNumberType(sPhoneNumberType);
		setPhoneNumberTypeDescription(sPhoneNumberTypeDesc);
	}

	public String getPhoneNumberTypeId() {
		return super.getDataElement(csPhoneNumberTypeTblPhoneNumberTypeId);
	}
	public int getPhoneNumberTypeIdAsInt() {
		return super.getIntDataElement(csPhoneNumberTypeTblPhoneNumberTypeId);
	}

	public String getPhoneNumberType() {
		return super.getDataElement(csPhoneNumberTypeTblPhoneNumberType);
	}

	public String getPhoneNumberTypeDescription() {
		return super.getDataElement(csPhoneNumberTypeTblPhoneNumberTypeDesc);
	}

	public void setPhoneNumberTypeId(int PhoneNumberTypeId) {
		super.setDataElement(csPhoneNumberTypeTblPhoneNumberTypeId, PhoneNumberTypeId);
	}
	public void setPhoneNumberTypeId(String PhoneNumberTypeId) {
		super.setDataElement(csPhoneNumberTypeTblPhoneNumberTypeId, PhoneNumberTypeId);
	}

	public void setPhoneNumberType(String PhoneNumberType) {
		super.setDataElement(csPhoneNumberTypeTblPhoneNumberType, PhoneNumberType);
	}

	public void setPhoneNumberTypeDescription(String PhoneNumberTypeDesc) {
		super.setDataElement(csPhoneNumberTypeTblPhoneNumberTypeDesc, PhoneNumberTypeDesc);
	}

	@Override
	public String toString() {
		return super.toString();
	}


	
	
}
