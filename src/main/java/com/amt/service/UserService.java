package com.amt.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dao.GenericDAO;
import com.amt.dao.UserDAO;
import com.amt.dao.UserDAOImpl;
import com.amt.dto.AddUserDTO;
import com.amt.dto.UserDTO;
import com.amt.exception.*;
import com.amt.model.Order;
import com.amt.model.User;
import com.amt.util.PasswordUtil;
import com.amt.util.Validate;

public class UserService implements Constants {
	private Logger objLogger = LoggerFactory.getLogger(UserService.class);
	private UserDAO objUserDAO;

	public UserService() {
		this.objUserDAO = new UserDAOImpl();
	}

	public UserService(UserDAO objMockUserDAO) {
		this.objUserDAO = objMockUserDAO;
	}
	
	public UserService getMockUserDAO(UserDAO objMockUserDAO) {
		this.objUserDAO = objMockUserDAO;
		return this;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////


	
	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	public User getUsersByUsername(String sUsername) throws DatabaseException, BadParameterException {
		String sMethod = csCRT + "getUsersByUsername(): ";
		objLogger.trace(csCR + sMethod + "Entered");
		
		boolean bValidLen = (sUsername.length() >= ciUsernameMinLength && sUsername.length() <= ciUsernameMaxLength);

		if (bValidLen && Validate.isAlphaNumeric(sUsername)) {			
			try {
				User objUser = objUserDAO.getByUsername(sUsername);
				objLogger.debug(sMethod + "objUser: [" + objUser.toString() + "]");
				return objUser;

			} catch (Exception e) {// not sure what exception hibernate throws but not SQLException
				objLogger.error(sMethod + "Exception getting all User records from the database: Exception: ["
						+ e.toString() + "] [" + e.getMessage() + "]");
				throw new DatabaseException(csMsgDB_ErrorGettingEmployeeByUsername);
			}
		}else {
			objLogger.debug(sMethod + "Invalid user name received: [" + sUsername + "]");
			throw new BadParameterException(csMsgBadParamGetUserByUsername);			
		}
	}


	
	//
	// ### Cannot update UserId or the username
	//will not update the record id, the company username, or the company email.
	//also password should be updated through separate process where old pwd and new pwd are entered
	public User editExistingUser(UserDTO objUserDTO) throws DatabaseException, BadParameterException {
		String sMethod = csCRT + "EditExistingUser(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		if (isValidUserDTOEditAttributes(objUserDTO)) {
			try {
				objLogger.debug(sMethod + "Validated objUserDTO: [" + objUserDTO.toString() + "]");

				User objUser = objUserDAO.editRecord(objUserDTO);
				objLogger.debug(sMethod + "objEmployee: [" + objUser.toString() + "]");
				return objUser;

			} catch (Exception e) {// not sure what exception hibernate throws but not SQLException
				objLogger.error(sMethod + "Exception adding User record with username: [" + objUserDTO.getUsername()
						+ "] Exception: [" + e.toString() + "] [" + e.getMessage() + "]");
				throw new DatabaseException(csMsgDB_ErrorGettingEmployees);
			}


		}else {
			objLogger.warn(sMethod + "objUserDTO to update user is not valid: [" + objUserDTO.toString() + "]");
			throw new BadParameterException(csMsgBadParamEditUser);

		}
	}
	
	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	public User addNewUser(AddUserDTO objAddUserDTO) throws DatabaseException, BadParameterException {
		String sMethod = csCRT + "addNewUser(): ";
		objLogger.trace(csCR + sMethod + "Entered: objUserDTO: [" + objAddUserDTO.toString() + "]");

		if (isValidAddUserDTO(objAddUserDTO)) {
			try {
				objLogger.debug(sMethod + "Validated objUserDTO: [" + objAddUserDTO.toString() + "]");				
				
				//Encrypt and set password values
				String sPwdSalt = PasswordUtil.getSalt(30);
				String sSecurePassword = PasswordUtil.generateSecurePassword(objAddUserDTO.getPassword(), sPwdSalt);				
				objAddUserDTO.setPassword(sSecurePassword);
				objAddUserDTO.setPasswordSalt(sPwdSalt);

				//set up the add dto used by the add dao
				
				objLogger.debug(sMethod + "calling addRecord with objUserDTO: [" + objAddUserDTO.toString() + "]");	
				User objUser = new User();
				objUser = objUserDAO.addUser(objAddUserDTO);
				objLogger.debug(sMethod + "objEmployee: [" + objUser.toString() + "]");
				return objUser;

			} catch (Exception e) {// not sure what exception hibernate throws but not SQLException
				objLogger.error(sMethod + "Exception adding User record with username: [" + objAddUserDTO.getUsername()
						+ "] Exception: [" + e.toString() + "] [" + e.getMessage() + "]");
				throw new DatabaseException(csMsgDB_ErrorAddingUser);
			}

		} else {
			objLogger.warn(sMethod + "objAddUserDTO is not valid: [" + objAddUserDTO.toString() + "]");
			throw new BadParameterException(csMsgBadParamAddUser);
		}
	}


	////////////////////// Utility Methods for this Class /////////////////////////////////////////

	//
	// ### Cannot update UserId or the username
	//will not update the record id, the company username, or the company email.
	//also password should be updated through separate process where old pwd and new pwd are entered
	public boolean isValidUserDTOEditAttributes(UserDTO objUserDTO) {
		String sMethod = csCRT + "isValidUserDTOEditAttributes(): ";
		objLogger.trace(csCR + sMethod + "Entered");
		boolean isValid = false;
		
		
		String sFirstName = objUserDTO.getFirstName();
		String sLastName = objUserDTO.getLastName();
		String sUserRoleName = objUserDTO.getEmployeeRole();
		
		boolean bFirstNameIsAlpha = Validate.isAlpha(sFirstName);
		boolean bLastNameIsAlpha = Validate.isAlphaPlusLastname(sLastName);
		boolean bUserRoleNameIsValid = Validate.isValidValueInArray(sUserRoleName, csarEmployeeRoles);
		
		if (bFirstNameIsAlpha && bLastNameIsAlpha && bUserRoleNameIsValid) {
			isValid=true;
		}
		else {
			
			objLogger.warn(sMethod + "One or more add User Parameters did not pass validation.:");
			objLogger.warn(sMethod + "\t first name: [" + sFirstName + "] is valid: [" + bFirstNameIsAlpha + "]");
			objLogger.warn(sMethod + "\t last name: [" + sLastName + "] is valid: [" + bLastNameIsAlpha + "]");
			objLogger.warn(sMethod + "\t user role name: [" + sUserRoleName + "] is valid: [" + bUserRoleNameIsValid + "]");
		}
		
		return isValid;
	}
	
	/*
		private String username = "";
	private String password = "";
	private String passwordSalt = "";
	private String firstName = "";
	private String lastName = "";
	private String email = "";
	private String employeeRole = "";
	private String userType = "";
*/
	//
	public boolean isValidAddUserDTO(AddUserDTO objUserDTO) {
		String sMethod = csCRT + "isValidUserDTO(): ";
		objLogger.trace(csCR + sMethod + "Entered");
		boolean isValid = false;

		String sUsername = objUserDTO.getUsername();
		String sPassword = objUserDTO.getPassword();
		String sFirstName = objUserDTO.getFirstName();
		String sLastName = objUserDTO.getLastName();
		String sEmail = objUserDTO.getEmail();
		String sEmployeeRole = objUserDTO.getEmployeeRole();
		String sUserType = objUserDTO.getUserType();
		
		boolean bUsernameIsAlphaNumeric = isValidUsername(sUsername);
		boolean bPasswordIsInFormat = isValidPassword(sPassword);
		boolean bFirstNameIsAlpha = Validate.isAlpha(sFirstName);
		boolean bLastNameIsAlpha = Validate.isAlphaPlusLastname(sLastName);
		boolean bEmailIsInFormat = Validate.isValidEmailAddress(sEmail);
		boolean bEmployeeRoleIsValid = Validate.isValidValueInArray(sEmployeeRole, csarEmployeeRoles);
		boolean bUserTypeIsValid = Validate.isValidValueInArray(sUserType, csarUserType);
		
		
		

		if (bUsernameIsAlphaNumeric &&  bPasswordIsInFormat && bFirstNameIsAlpha && bLastNameIsAlpha &&
				bEmailIsInFormat && bEmployeeRoleIsValid && bUserTypeIsValid) {
			isValid = true;
		} else {
			objLogger.warn(csCR + sMethod + "One or more add User Parameters did not pass validation.:");
			objLogger.warn(sMethod + "\t username: [" + sUsername + "] is valid: [" + bUsernameIsAlphaNumeric + "]");
			objLogger.warn(sMethod + "\t password correct format: [" + bPasswordIsInFormat + "]");
			objLogger.warn(sMethod + "\t first name: [" + sFirstName + "] is valid: [" + bFirstNameIsAlpha + "]");
			objLogger.warn(sMethod + "\t last name: [" + sLastName + "] is valid: [" + bLastNameIsAlpha + "]");
			objLogger.warn(sMethod + "\t email: [" + sEmail + "] correct format: [" + bEmailIsInFormat + "]");
			objLogger.warn(sMethod + "\t employee role name: [" + sEmployeeRole + "] is valid: [" + bEmployeeRoleIsValid + "]");
			objLogger.warn(sMethod + "\t user type name: [" + sUserType + "] is valid: [" + bUserTypeIsValid + "]");
		}
		return isValid;
	}

	public static boolean isValidUsername(String sUsername) {
		boolean bValid = true;
		
		bValid = Validate.isAlphaNumeric(sUsername) 
				&& sUsername.length() >= ciUsernameMinLength 
				&& sUsername.length() <= ciUsernameMaxLength;

		return bValid;
	}

	public static boolean isValidPassword(String sPassword) {
		
		return Validate.isPasswordFormat(sPassword, ciUserMinPassword, ciUserMaxPassword);		
	}

	
}//END Class
