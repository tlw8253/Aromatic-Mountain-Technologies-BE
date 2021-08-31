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
import com.amt.dto.UserRoleDTO;
import com.amt.exception.BadParameterException;
import com.amt.exception.DatabaseException;
import com.amt.model.OrderStatus;
import com.amt.model.UserType;
import com.amt.model.User;
import com.amt.model.UserRole;
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

public class Admin implements Constants {
	private static Logger objLogger = LoggerFactory.getLogger(Admin.class);
	private static UserService objERSService = new UserService();
	private static UserDTO objUserDTO = new UserDTO();

	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String sMethod = "\n\t main(): ";
		objLogger.trace(sMethod + "Entered");

		  createTablesViaHibernate(); //NOTE: change configuration file to create
		 // ersAdminAddStaticTableValues();
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
		String sMethod = "\n\t createTablesViaHibernate(): ";
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


	//
	// ###
	private static void ersAdminAddStaticTableValues() {
		addUserType();
		addUserRole();
	}



	//
	// ###
	private static void addUserType() {
		String sMethod = "\n\t addUserType(): ";
		objLogger.trace(sMethod + "Entered");

		
		for (int iCtr = 0; iCtr <csUserType.length; iCtr++) {
			String sType = csUserType[iCtr];
			String sTypeDesc = csUserTypeDesc[iCtr];
			
			objLogger.debug(sMethod + "Adding type: [" + sType + "] description: [" + sTypeDesc + "]");
			addUserType(sType, sTypeDesc);
		}

	}

	
	//
	// ###
	private static void addUserType(String sType, String sTypeDesc) {
		String sMethod = "\n\t addUserType(): ";
		objLogger.trace(sMethod + "Entered:");

		AdminService objAdminService = new AdminService();
		UserTypeDTO objReimbTypeDTO = new UserTypeDTO();

		objReimbTypeDTO.setUserType(sType);
		objReimbTypeDTO.setUserTypeDescription(sTypeDesc);

		try {
			UserType objReimbType = objAdminService.addReimbursementType(objReimbTypeDTO);
			objLogger.debug(sMethod + "objReimbType: [" + objReimbType.toString() + "]");
		} catch (Exception e) {
			objLogger.error(sMethod + "Exception during processing: [" + e.getMessage() + "]");
			// throw new DatabaseException(e.getMessage());
		}
	}

	
	
	private static void addUserRole() {
		String sMethod = "\n\t addUserRole(): ";
		objLogger.trace(sMethod + "Entered");

		for (int iCtr = 0; iCtr <csEmployeeRoles.length; iCtr++) {
			String sUserRole = csEmployeeRoles[iCtr];
			String sUserRoleDesc = csEmployeeRolesDesc[iCtr];
			objLogger.debug(sMethod + "Adding user role: [" + sUserRole + "] description: [" + sUserRoleDesc + "]");
			addUsrRole(sUserRole, sUserRoleDesc);			
		}
	}

	//
	// ###
	private static void addUsrRole(String sUserRole, String sUserRoleDesc) {
		String sMethod = "\n\t addUsrRole(): ";
		objLogger.trace(sMethod + "Entered");

		AdminService objAdminService = new AdminService();
		UserRoleDTO objUserRoleDTO = new UserRoleDTO();

		objLogger.debug(sMethod + "Setting DTO: sUserRole: [" + sUserRole + "] sUserRoleDesc: [" + sUserRoleDesc + "]");

		objUserRoleDTO.setUserRole(sUserRole);
		objUserRoleDTO.setUserRoleDescription(sUserRoleDesc);

		objLogger.debug(sMethod + "objUserRoleDTO: [" + objUserRoleDTO.toString() + "]");

		try {
			UserRole objUserRole = objAdminService.addUserRole(objUserRoleDTO);
			objLogger.debug(sMethod + "objUserRole: [" + objUserRole.toString() + "]");
		} catch (Exception e) {
			objLogger.error(sMethod + "Exception during processing: [" + e.getMessage() + "]");
			// throw new DatabaseException(e.getMessage());
		}
	}

}
