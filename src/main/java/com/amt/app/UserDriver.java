package com.amt.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.dto.UserDTO;
import com.amt.exception.BadParameterException;
import com.amt.exception.DatabaseException;
import com.amt.model.User;
import com.amt.service.UserService;




/**
 * This is a driver used during development to test functionality as it is
 * built. 
 * 
 * @author tlw8748253
 *
 */
public class UserDriver implements Constants {
	private final static Logger objLogger = LoggerFactory.getLogger(UserDriver.class);

	public static void main(String[] args) {
		String sMethod = csCRT + "main(): ";
		objLogger.trace(sMethod + "Entered");
		

		addProjectMemberUsers();
		
	}
	
	//
	//###
	public static void addProjectMemberUsers() {
		String sMethod = csCRT + "addProjectMemberUsers(): ";
		objLogger.trace(sMethod + "Entered");

		addNewUsers("tw8253", "A_Pass12345", "Tom", "Weikel", "thomas.weikel@revature.net", csarUserType[enumUserType.EMPLOYEE.pos],csarEmployeeRoles[enumUserEmployee.CATALOG_ADMIN.pos]);
//		addNewUsers("ap1234", "A_Pass12345", "Antonio", "Pierre", "antonio.pierre@revature.net", csarUserType[enumUserType.EMPLOYEE.pos],csarEmployeeRoles[enumUserEmployee.CATALOG_ADMIN.pos]);
//		addNewUsers("mr1234", "A_Pass12345", "Matthew", "Rho", "matthew.rho@revature.net", csarUserType[enumUserType.EMPLOYEE.pos],csarEmployeeRoles[enumUserEmployee.CATALOG_EMPLOYEE.pos]);
	}
	
	//
	//###
	public static void addNewUsers(String username, String password, String firstName, String lastName, String email, String userType, String employeeRole) {
		String sMethod = csCRT + "addNewUsers(): ";
		objLogger.trace(sMethod + "Entered");

		UserService objUserService = new UserService();
		UserDTO objUserDTO = new UserDTO(username, password, firstName, lastName, email, userType, employeeRole);
		
		try {
			User objUser = objUserService.addNewUser(objUserDTO);
		} catch (DatabaseException e) {
			objLogger.debug(sMethod + "DatabaseException: [" + e.getMessage() + "]");
		} catch (BadParameterException e) {
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		}

		
		
	}


}
