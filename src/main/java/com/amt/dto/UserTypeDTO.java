package com.amt.dto;

import com.amt.app.Constants;

public class UserTypeDTO extends AddOrEditDTO implements Constants{
	//Class attributes are store in the parent in HashMap tables.	

	/*Stored in hashmaps in the super class
	//ers_reimbursement_type table
	*/
	
	//
	//###
	public UserTypeDTO() {
		super();
	}

	public UserTypeDTO(String userType, String userTypeDesc) {
		setUserType(userType);
		setUserTypeDescription(userTypeDesc);
	}

	public String getUserTypeId() {
		return super.getDataElement(csUserTypeTblUserTypeId);
	}
	public int getUserTypeIdAsInt() {
		return super.getIntDataElement(csUserTypeTblUserTypeId);
	}

	public String getUserType() {
		return super.getDataElement(csUserTypeTblUserType);
	}

	public String getUserTypeDescription() {
		return super.getDataElement(csUserTypeTblUserTypeDesc);
	}

	public void setUserTypeId(int reimbTypeId) {
		super.setDataElement(csUserTypeTblUserTypeId, reimbTypeId);
	}
	public void setUserTypeId(String reimbTypeId) {
		super.setDataElement(csUserTypeTblUserTypeId, reimbTypeId);
	}

	public void setUserType(String reimbType) {
		super.setDataElement(csUserTypeTblUserType, reimbType);
	}

	public void setUserTypeDescription(String reimbTypeDesc) {
		super.setDataElement(csUserTypeTblUserTypeDesc, reimbTypeDesc);
	}

	@Override
	public String toString() {
		return super.toString();
	}


	
	
}
