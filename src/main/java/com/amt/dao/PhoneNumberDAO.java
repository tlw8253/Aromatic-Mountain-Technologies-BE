package com.amt.dao;

import java.sql.SQLException;

import com.amt.dto.PhoneNumberDTO;
import com.amt.model.PhoneNumber;



//DAO - Data Access Object
public interface PhoneNumberDAO {

	public abstract PhoneNumber addNewPhoneNumber(PhoneNumberDTO objPhoneNumberDTO) throws SQLException;

}
