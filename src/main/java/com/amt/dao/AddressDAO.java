package com.amt.dao;

import java.sql.SQLException;

import com.amt.dto.AddressDTO;
import com.amt.model.Address;



//DAO - Data Access Object
public interface AddressDAO {

	public abstract Address addAddress(AddressDTO objAddressDTO) throws SQLException;

}
