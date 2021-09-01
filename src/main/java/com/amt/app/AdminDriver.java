package com.amt.app;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.dto.OrderStatusDTO;
import com.amt.dto.UserTypeDTO;
import com.amt.dto.UserDTO;
import com.amt.dto.AddressTypeDTO;
import com.amt.dto.EmployeeRoleDTO;
import com.amt.exception.BadParameterException;
import com.amt.exception.DatabaseException;
import com.amt.model.OrderStatus;
import com.amt.model.UserType;
import com.amt.model.User;
import com.amt.model.AddressType;
import com.amt.model.EmployeeRole;
import com.amt.service.AdminService;
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

public class AdminDriver implements Constants {
	private static Logger objLogger = LoggerFactory.getLogger(AdminDriver.class);
	private static UserService objERSService = new UserService();
	private static UserDTO objUserDTO = new UserDTO();

	public AdminDriver() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String sMethod = csCRT + "main(): ";
		objLogger.trace(sMethod + "Entered");

		  //createTablesViaHibernate(); //NOTE: change configuration file to create
		  amtAdminAddStaticTableValues();

		  // addNewUser("tlw8253", "A_Pass12345", "Tomas", "Ykel", "tlw8253@wws.com", csUserRoles[ciUserRoleEmployee]);
		 // addNewReimbursement("1");	//add Reimbursement for above user
		 // addNewUser("smp8253", "A_Pass12345", "Sam", "Smith", "smp8253@wws.com", csUserRoles[ciUserRoleFinanceMgr]);
		 // addNewReimbursement("2");	//add Reimbursement for above user
		
		 //addNewUser("cwg8253", "A_Pass12345", "Clark", "Griswold", "cwg8253@wws.com", csUserRoles[ciUserRoleEmployee]);
		 //addNewUser("cqe8253", "A_Pass12345", "Cousin", "Eddy", "cqe8253@wws.com", csUserRoles[ciUserRoleFinanceMgr]);

	}

	//
	// ###
	private static void createTablesViaHibernate() {
		String sMethod = csCRT + "createTablesViaHibernate(): ";
		objLogger.trace(sMethod + "Entered");

		// need to set the config.xm property to: <property
		// name="hbm2ddl.auto">create</property>
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		tx.commit();

	}

	//
	// ###
	private static void amtAdminAddStaticTableValues() {

		addAddressTypes();

		
		//addUserTypes();
		//addEmployeeRoles();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	// ###
	private static void addAddressTypes() {
		String sMethod = csCRT + "addAddressTypes(): ";
		objLogger.trace(sMethod + "Entered");

		
		for (int iCtr = 0; iCtr < carrAddressType.length; iCtr++) {
			String sType = carrAddressType[iCtr];
			String sTypeDesc = carrAddressTypeDesc[iCtr];
			
			objLogger.debug(sMethod + "Adding type: [" + sType + "] description: [" + sTypeDesc + "]");
			addAddressType(sType, sTypeDesc);
		}

	}
	
	//
	// ###
	private static void addAddressType(String sType, String sTypeDesc) {
		String sMethod = csCRT + "addAddressType(): ";
		objLogger.trace(sMethod + "Entered:");

		AdminService objAdminService = new AdminService();
		AddressTypeDTO objAddressTypeDTO = new AddressTypeDTO(sType, sTypeDesc);

		try {
			AddressType objAddressType = objAdminService.addAddressType(objAddressTypeDTO);
			objLogger.debug(sMethod + "objAddressType: [" + objAddressType.toString() + "]");
		} catch (Exception e) {
			objLogger.error(sMethod + "Exception during processing: [" + e.getMessage() + "]");
		}
	}


	
	////////////////////////////////////////////////////////////////////////////////////////////////
	//###
	private static void addEmployeeRoles() {
		String sMethod = csCRT + "addEmployeeRoles(): ";
		objLogger.trace(sMethod + "Entered");

		for (int iCtr = 0; iCtr <csarEmployeeRoles.length; iCtr++) {
			String sRole = csarEmployeeRoles[iCtr];
			String sRoleDesc = csarEmployeeRolesDesc[iCtr];
			objLogger.debug(sMethod + "Adding employee role: [" + sRole + "] description: [" + sRoleDesc + "]");
			addEmployeeRole(sRole, sRoleDesc);			
		}
	}

	// ###
	private static void addEmployeeRole(String sRole, String sRoleDesc) {
		String sMethod = csCRT + "addEmployeeRole(): ";
		objLogger.trace(sMethod + "Entered");

		AdminService objAdminService = new AdminService();		

		objLogger.debug(sMethod + "Setting DTO: sUserRole: [" + sRole + "] sUserRoleDesc: [" + sRoleDesc + "]");
		EmployeeRoleDTO objUserRoleDTO = new EmployeeRoleDTO(sRole,sRoleDesc);
		
		objLogger.debug(sMethod + "objUserRoleDTO: [" + objUserRoleDTO.toString() + "]");

		try {
			EmployeeRole objUserRole = objAdminService.addEmployeeRole(objUserRoleDTO);
			objLogger.debug(sMethod + "objUserRole: [" + objUserRole.toString() + "]");
		} catch (Exception e) {
			objLogger.error(sMethod + "Exception during processing: [" + e.getMessage() + "]");
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	// ###
	private static void addUserTypes() {
		String sMethod = csCRT + "addUserType(): ";
		objLogger.trace(sMethod + "Entered");

		
		for (int iCtr = 0; iCtr < csarUserType.length; iCtr++) {
			String sType = csarUserType[iCtr];
			String sTypeDesc = csarUserTypeDesc[iCtr];
			
			objLogger.debug(sMethod + "Adding type: [" + sType + "] description: [" + sTypeDesc + "]");
			addUserType(sType, sTypeDesc);
		}

	}
	
	//
	// ###
	private static void addUserType(String sType, String sTypeDesc) {
		String sMethod = csCRT + "addUserType(): ";
		objLogger.trace(sMethod + "Entered:");

		AdminService objAdminService = new AdminService();
		UserTypeDTO objUserTypeDTO = new UserTypeDTO(sType, sTypeDesc);

		try {
			UserType objUserType = objAdminService.addUserType(objUserTypeDTO);
			objLogger.debug(sMethod + "objReimbType: [" + objUserType.toString() + "]");
		} catch (Exception e) {
			objLogger.error(sMethod + "Exception during processing: [" + e.getMessage() + "]");
		}
	}

	
	
	
	
	
	

	////////////////////////////////////////////////////////////////////////////////////////////////
	// ###
	public static void addNewUser(String sUsername, String sPassword, String sFirstName, String sLastName,
			String sEmail, String sRole) {
		String sMethod = "\n\t addNewEmployee(): ";
		objLogger.trace(sMethod + "Entered");

		objUserDTO = new UserDTO(sUsername, sPassword, sFirstName, sLastName, sEmail, sRole);
		objLogger.debug(sMethod + "objUserDTO: [" + objUserDTO.toString() + "]");

		try {
			User objEmployee = objERSService.addNewUser(objUserDTO);
			objLogger.debug(sMethod + "objEmployee: [" + objEmployee.toString() + "]");
		} catch (Exception e) {
			objLogger.debug(sMethod + "Exception: [" + e.getMessage() + "]");
		}

	}






	
	

}
