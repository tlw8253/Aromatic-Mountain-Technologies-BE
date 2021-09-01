package com.amt.dto;

import com.amt.app.Constants;

public class AddressDTO extends AddOrEditDTO implements Constants {

	private int addressTypeId = 0;
	private String addressLine1 = "";
	private String addressLine2 = "";
	private String addressCity = "";
	private String addressState = "";
	private String addressZipCode = "";
	private String addressCountry = "United States";
	private String addressType;
	private String userName;

	
	
	public AddressDTO() {
		// TODO Auto-generated constructor stub
	}

}
