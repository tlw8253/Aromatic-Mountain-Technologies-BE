package com.amt.dto;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.rowset.serial.SerialBlob;

import java.lang.Integer;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.model.User;
import com.amt.util.Utility;


//DTO - Data Transfer Object
public class EditUserDTO {
	private Logger objLogger = LoggerFactory.getLogger(EditUserDTO.class);
	
	private int userId = 0;
	private String username = "";
	private String password = "";
	private String passwordSalt = "";
	private String firstName = "";
	private String lastName = "";
	private String email = "";
	private String employeeRole;
	private String userType;
	
	
	public EditUserDTO() {
		super();
	}	
	

	
	
	
}//END Class
