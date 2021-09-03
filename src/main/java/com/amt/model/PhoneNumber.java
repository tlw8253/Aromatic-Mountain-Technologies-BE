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
@Table(name = Constants.csPhoneNumberTable)

public class PhoneNumber implements Constants {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = csPhoneNumberTblPhoneNumberId)
	private int phoneNumbereId = 0;

	@Column(name = csPhoneNumberTblPhoneNumberCountryCode, length = 10, nullable = false)
	private String phoneNumberCountryCode = "01";

	@Column(name = csPhoneNumberTblPhoneNumber, length = 15, nullable = false)
	private String phoneNumber = "";

	@ManyToOne
	@JoinColumn(name = csPhoneNumberTypeTblPhoneNumberTypeId, nullable = false)
	private PhoneNumberType phoneNumberType;		//

	
	@ManyToOne
	@JoinColumn(name = csUserTblId, nullable = false)
	private User user;		//

	
	public PhoneNumber() {
		super();
		phoneNumberType = new PhoneNumberType();
	}

	public PhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		phoneNumberType = new PhoneNumberType();
	}
	
	public PhoneNumber(String phoneNumber, PhoneNumberType phoneNumberType) {
		this.phoneNumber = phoneNumber;
		this.phoneNumberType = phoneNumberType;		
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


	public PhoneNumberType getPhoneNumberType() {
		return phoneNumberType;
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


	public void setPhoneNumberType(PhoneNumberType phoneNumberType) {
		this.phoneNumberType = phoneNumberType;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public int hashCode() {
		return Objects.hash(phoneNumber, phoneNumberCountryCode, phoneNumberType, phoneNumbereId, user);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneNumber other = (PhoneNumber) obj;
		return Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(phoneNumberCountryCode, other.phoneNumberCountryCode)
				&& Objects.equals(phoneNumberType, other.phoneNumberType) && phoneNumbereId == other.phoneNumbereId
				&& Objects.equals(user, other.user);
	}


	@Override
	public String toString() {
		return "PhoneNumber [phoneNumbereId=" + phoneNumbereId + ", phoneNumberCountryCode=" + phoneNumberCountryCode
				+ ", phoneNumber=" + phoneNumber + ", phoneNumberType=" + phoneNumberType + ", user=" + user + "]";
	}



	
}
