package com.amt.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dao.GenericDAO;
import com.amt.dao.UserDAO;
import com.amt.dao.UserDAOImpl;
import com.amt.dto.LoginDTO;
import com.amt.exception.AuthenticationFailureException;
import com.amt.exception.BadParameterException;
import com.amt.exception.DatabaseException;
import com.amt.model.User;

public class LoginService implements Constants {
	private Logger objLogger = LoggerFactory.getLogger(LoginService.class);
	private UserDAO objUserDAO;

	public LoginService() {
		objUserDAO = new UserDAOImpl();
	}

	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	public boolean validateSessionUser(LoginDTO objDTO, User objSessionUser)
			throws BadParameterException, AuthenticationFailureException, DatabaseException {
		String sMethod = csCRT + "login(): ";
		boolean isValid = false;
		objLogger.trace(csCR + sMethod + "Entered.");

		objLogger.debug(sMethod + "input: objDTO: [" + objDTO + "]");
		objLogger.debug(sMethod + "input: objSessionUser: [" + objSessionUser + "]");

		try {
			User objDtoUser = login(objDTO);
			objLogger.debug(sMethod + "checking if session and dto user objects are equal");
			if (objSessionUser.equals(objDtoUser)) {
				objLogger.debug(sMethod + "the DTO user and Session user ARE the same.");
				isValid = true;		
			}else {
				objLogger.debug(sMethod + "the DTO user and Session user ARE NOT the same.");
			}
			
		} catch (Exception e) {
			objLogger.debug(sMethod + "Database error while validating session for username: [" + objDTO.getUsername() + "]");
			throw new DatabaseException(csMsgDB_ErrorAuthenticatingUsername);
		}

		return isValid;
	}

	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	public User login(LoginDTO objLoginDTO)
			throws BadParameterException, AuthenticationFailureException, DatabaseException {
		String sMethod = csCRT + "login(): ";
		objLogger.trace(csCR + sMethod + "Entered.");

		String sUsername = objLoginDTO.getUsername();
		String sPassword = objLoginDTO.getPassword();

		if (sUsername.trim().equals("") || sPassword.trim().equals("")) {
			objLogger.debug(sMethod + "Invalid parameters sUsername: [" + sUsername + "] and/ or Password is blank.");
			throw new BadParameterException(csMsgBadParamLoginUsernamePwdBlank);
		}

		boolean bValidUsername = UserService.isValidUsername(sUsername);
		boolean bValidPassword = UserService.isValidPassword(sPassword);

		if (!bValidUsername || !bValidPassword) {
			objLogger.debug(
					sMethod + "Invalid parameters sUsername: [" + sUsername + "] and/ or Password format is invalid.");
			throw new BadParameterException(csMsgBadParamLoginUsernamePwdLength);
		}

		User objUser;
		try {
			objUser = objUserDAO.getLogin(sUsername, sPassword);
			if (objUser == null) {
				objLogger.debug(
						sMethod + "Authentication failed with sUsername: [" + sUsername + "] and Password provided.");
				throw new AuthenticationFailureException(csMsgAutenticationFailed);
			}
			return objUser;

		} catch (SQLException e) {
			objLogger.debug(sMethod + "Database error while authenticating username: [" + sUsername + "]");
			throw new DatabaseException(csMsgDB_ErrorAuthenticatingUsername);
		}
	}

}
