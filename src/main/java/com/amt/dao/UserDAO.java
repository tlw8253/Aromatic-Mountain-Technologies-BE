package com.amt.dao;

import java.sql.SQLException;

import com.amt.dto.AddOrEditDTO;
import com.amt.dto.AddUserDTO;
import com.amt.model.User;



//DAO - Data Access Object
public interface UserDAO {

	public abstract User getByUsername(String sUsername) throws SQLException; 
	
	public abstract User addUser(AddUserDTO objAddUserDTO) throws SQLException;
	
	//all info will be passed through the DTO
	public abstract User editRecord(AddOrEditDTO objAddOrEditDTO) throws SQLException;
	
	public abstract User getLogin(String sUsername, String password) throws SQLException;
	
}
