package com.amt.app;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.dto.LoginDTO;
import com.amt.dto.OrderStatusDTO;
import com.amt.dto.UserTypeDTO;
import com.amt.dto.UserDTO;
import com.amt.dto.EmployeeRoleDTO;
import com.amt.exception.AuthenticationFailureException;
import com.amt.exception.BadParameterException;
import com.amt.exception.DatabaseException;
import com.amt.model.OrderStatus;
import com.amt.model.UserType;
import com.amt.model.User;
import com.amt.model.EmployeeRole;
import com.amt.service.AdminService;
import com.amt.service.LoginService;
import com.amt.service.UserService;
import com.amt.util.*;

/**
 * This is a driver for admin functionality until an admin front-end is built.
 * It has methods to create the Hibranate schema, load static type values and
 * add users.
 * 
 * @author tlw8748253
 *
 */

public class LoginDriver implements Constants {
	private static Logger objLogger = LoggerFactory.getLogger(LoginDriver.class);
	private static LoginService objERSLoginService = new LoginService();
	private static LoginDTO objLoginDTO = new LoginDTO();

	public LoginDriver() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String sMethod = "\n\t main(): ";
		objLogger.trace(sMethod + "Entered");

		test_ERSLoginService_login("tlw8253", "A_Pass12345");

	}

	//
	// ###
	public static void test_ERSLoginService_login(String sUsername, String sPassword) {
		String sMethod = "\n\t test_ERSLoginService_login(): ";
		objLogger.trace(sMethod + "Entered");
		
		objLoginDTO.setUsername(sUsername);
		objLoginDTO.setPassword(sPassword);
		
		try {
			User objUser = objERSLoginService.login(objLoginDTO);
			objLogger.debug(sMethod + "objUser: [" + objUser.toString() + "]");
			
		} catch (BadParameterException e) {
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		} catch (AuthenticationFailureException e) {
			objLogger.debug(sMethod + "AuthenticationFailureException: [" + e.getMessage() + "]");
		} catch (DatabaseException e) {
			objLogger.debug(sMethod + "DatabaseException: [" + e.getMessage() + "]");
		}
		
	}

}
















