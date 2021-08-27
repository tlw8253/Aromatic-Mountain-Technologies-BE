package com.amt.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dao.GenericDAO;
import com.amt.dao.UserDAOImpl;
import com.amt.dto.LoginDTO;
import com.amt.exception.AuthenticationFailureException;
import com.amt.exception.BadParameterException;
import com.amt.exception.DatabaseException;
import com.amt.model.User;


public class ERSLoginService implements Constants {
	private Logger objLogger = LoggerFactory.getLogger(ERSLoginService.class);
	private GenericDAO<User> objUserDAO;

	public ERSLoginService() {
		objUserDAO = new UserDAOImpl();
	}
	
	public User login(LoginDTO objLoginDTO) throws BadParameterException, AuthenticationFailureException, DatabaseException {
		String sMethod = "\n\t login";
		
		objLogger.trace(sMethod + "Entered.");
		
		String sUsername = objLoginDTO.getUsername();
		String sPassword = objLoginDTO.getPassword();
		
		if (sUsername.trim().equals("") || sPassword.trim().equals("")) {
			objLogger.debug(sMethod + "Invalid parameters sUsername: [" + sUsername + "] and/ or Password is blank.");
			throw new BadParameterException(csMsgBadParamLoginUsernamePwdBlank);
		}
		
		if (sUsername.length() != ciUsernameLength || sPassword.length() < ciUserMinPassword || sPassword.length() > ciUserMaxPassword) {
			objLogger.debug(sMethod + "Invalid parameters sUsername: [" + sUsername + "] and/ or Password length is invalid.");
			throw new BadParameterException(csMsgBadParamLoginUsernamePwdLength);
		}
		
		User objUser;
		try {
			objUser = objUserDAO.getLogin(sUsername, sPassword);			
			if (objUser == null) {
				objLogger.debug(sMethod + "Authentication failed with sUsername: [" + sUsername + "] and Password provided.");
				throw new AuthenticationFailureException(csMsgAutenticationFailed);
			}
			return objUser;

		} catch (SQLException e) {
			objLogger.debug(sMethod + "Database error while authenticating username: [" + sUsername + "]");
			throw new DatabaseException(csMsgDB_ErrorAuthenticatingUsername);
		}
	}

}
