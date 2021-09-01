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
import com.amt.dto.PhoneNumberTypeDTO;
import com.amt.dto.UserTypeDTO;
import com.amt.dto.UserDTO;
import com.amt.dto.AddressTypeDTO;
import com.amt.dto.CatalogItemTypeDTO;
import com.amt.dto.EmployeeRoleDTO;
import com.amt.exception.BadParameterException;
import com.amt.exception.DatabaseException;
import com.amt.model.OrderStatus;
import com.amt.model.PhoneNumber;
import com.amt.model.PhoneNumberType;
import com.amt.model.UserType;
import com.amt.model.User;
import com.amt.model.AddressType;
import com.amt.model.CatalogItemType;
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


	//
	// ###
	public static void main(String[] args) {
		String sMethod = csCRT + "main(): ";
		objLogger.trace(sMethod + "Entered");

		  //createTablesViaHibernate(); //NOTE: change configuration file to create
		  //amtAdminAddStaticTableValues();

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

		adminAddAddressTypes();
		adminAddCatalogItemTypes();
		adminAddEmployeeRoles();
		adminAddOrderStatuses();
		adminAddPhoneNumberTypes();
		adminAddUserTypes();
		
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	// ###
	private static void adminAddPhoneNumberTypes() {
		String sMethod = csCRT + "adminAddPhoneNumberTypes(): ";
		objLogger.trace(sMethod + "Entered");

		
		for (int iCtr = 0; iCtr < csarrPhoneNumberType.length; iCtr++) {
			String sType = csarrPhoneNumberType[iCtr];
			String sTypeDesc = csarrPhoneNumberTypeDesc[iCtr];
			
			objLogger.debug(sMethod + "Adding type: [" + sType + "] description: [" + sTypeDesc + "]");
			adminAddPhoneNumberType(sType, sTypeDesc);
			//break;
		}

	}
	
	//
	// ###
	private static void adminAddPhoneNumberType(String sType, String sTypeDesc) {
		String sMethod = csCRT + "adminAddPhoneNumberType(): ";
		objLogger.trace(sMethod + "Entered: sType: [" + sType + "] sTypeDesc: [" + sTypeDesc + "]");

		AdminService objAdminService = new AdminService();
		PhoneNumberTypeDTO objPhoneNumberTypeDTO = new PhoneNumberTypeDTO(sType, sTypeDesc);

		try {
			PhoneNumberType objPhoneNumberType = objAdminService.addPhoneNumberType(objPhoneNumberTypeDTO);
			objLogger.debug(sMethod + "objPhoneNumber: [" + objPhoneNumberType.toString() + "]");
		} catch (Exception e) {
			objLogger.error(sMethod + "Exception during processing: [" + e.getMessage() + "]");
		}
	}


	
	////////////////////////////////////////////////////////////////////////////////////////////////
	// ###
	private static void adminAddOrderStatuses() {
		String sMethod = csCRT + "adminAddOrderStatuses(): ";
		objLogger.trace(sMethod + "Entered");

		
		for (int iCtr = 0; iCtr < csarrOrderStatus.length; iCtr++) {
			String sType = csarrOrderStatus[iCtr];
			String sTypeDesc = csarrOrderStatusDesc[iCtr];
			
			objLogger.debug(sMethod + "Adding type: [" + sType + "] description: [" + sTypeDesc + "]");
			adminAddOrderStatus(sType, sTypeDesc);
			//break;
		}

	}
	
	//
	// ###
	private static void adminAddOrderStatus(String sType, String sTypeDesc) {
		String sMethod = csCRT + "adminAddOrderStatus(): ";
		objLogger.trace(sMethod + "Entered: sType: [" + sType + "] sTypeDesc: [" + sTypeDesc + "]");

		AdminService objAdminService = new AdminService();
		OrderStatusDTO objOrderStatusDTO = new OrderStatusDTO(sType, sTypeDesc);

		try {
			OrderStatus objOrderStatus = objAdminService.addOrderStatus(objOrderStatusDTO);
			objLogger.debug(sMethod + "objOrderStatus: [" + objOrderStatus.toString() + "]");
		} catch (Exception e) {
			objLogger.error(sMethod + "Exception during processing: [" + e.getMessage() + "]");
		}
	}


	////////////////////////////////////////////////////////////////////////////////////////////////
	// ###
	private static void adminAddCatalogItemTypes() {
		String sMethod = csCRT + "adminAddCatalogItemTypes(): ";
		objLogger.trace(sMethod + "Entered");

		
		for (int iCtr = 0; iCtr < csarrCatalogItemType.length; iCtr++) {
			String sType = csarrCatalogItemType[iCtr];
			String sTypeDesc = csarrCatalogItemTypeDesc[iCtr];
			
			objLogger.debug(sMethod + "Adding type: [" + sType + "] description: [" + sTypeDesc + "]");
			adminAddCatalogItemType(sType, sTypeDesc);
			//break;
		}

	}
	
	//
	// ###
	private static void adminAddCatalogItemType(String sType, String sTypeDesc) {
		String sMethod = csCRT + "adminAddCatalogItemType(): ";
		objLogger.trace(sMethod + "Entered: sType: [" + sType + "] sTypeDesc: [" + sTypeDesc + "]");

		AdminService objAdminService = new AdminService();
		CatalogItemTypeDTO objCatalogItemTypeDTO = new CatalogItemTypeDTO(sType, sTypeDesc);

		try {
			CatalogItemType objCatalogItemType = objAdminService.addCatalogItemType(objCatalogItemTypeDTO);
			objLogger.debug(sMethod + "objAddressType: [" + objCatalogItemType.toString() + "]");
		} catch (Exception e) {
			objLogger.error(sMethod + "Exception during processing: [" + e.getMessage() + "]");
		}
	}


	
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	// ###
	private static void adminAddAddressTypes() {
		String sMethod = csCRT + "adminAddAddressTypes(): ";
		objLogger.trace(sMethod + "Entered");

		
		for (int iCtr = 0; iCtr < carrAddressType.length; iCtr++) {
			String sType = carrAddressType[iCtr];
			String sTypeDesc = carrAddressTypeDesc[iCtr];
			
			objLogger.debug(sMethod + "Adding type: [" + sType + "] description: [" + sTypeDesc + "]");
			adminAddAddressType(sType, sTypeDesc);
			//break;
		}

	}
	
	//
	// ###
	private static void adminAddAddressType(String sType, String sTypeDesc) {
		String sMethod = csCRT + "adminAddAddressType(): ";
		objLogger.trace(sMethod + "Entered: sType: [" + sType + "] sTypeDesc: [" + sTypeDesc + "]");

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
	private static void adminAddEmployeeRoles() {
		String sMethod = csCRT + "adminAddEmployeeRoles(): ";
		objLogger.trace(sMethod + "Entered");

		for (int iCtr = 0; iCtr <csarEmployeeRoles.length; iCtr++) {
			String sRole = csarEmployeeRoles[iCtr];
			String sRoleDesc = csarEmployeeRolesDesc[iCtr];
			objLogger.debug(sMethod + "Adding employee role: [" + sRole + "] description: [" + sRoleDesc + "]");
			adminAddEmployeeRole(sRole, sRoleDesc);			
		}
	}

	// ###
	private static void adminAddEmployeeRole(String sRole, String sRoleDesc) {
		String sMethod = csCRT + "adminAddEmployeeRole(): ";
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
	private static void adminAddUserTypes() {
		String sMethod = csCRT + "adminAddUserTypes(): ";
		objLogger.trace(sMethod + "Entered");

		
		for (int iCtr = 0; iCtr < csarUserType.length; iCtr++) {
			String sType = csarUserType[iCtr];
			String sTypeDesc = csarUserTypeDesc[iCtr];
			
			objLogger.debug(sMethod + "Adding type: [" + sType + "] description: [" + sTypeDesc + "]");
			adminAddUserType(sType, sTypeDesc);
		}

	}
	
	//
	// ###
	private static void adminAddUserType(String sType, String sTypeDesc) {
		String sMethod = csCRT + "adminAddUserType(): ";
		objLogger.trace(sMethod + "Entered: sType: [" + sType + "] sTypeDesc: [" + sTypeDesc + "]");

		AdminService objAdminService = new AdminService();
		UserTypeDTO objUserTypeDTO = new UserTypeDTO(sType, sTypeDesc);

		try {
			UserType objUserType = objAdminService.addUserType(objUserTypeDTO);
			objLogger.debug(sMethod + "objReimbType: [" + objUserType.toString() + "]");
		} catch (Exception e) {
			objLogger.error(sMethod + "Exception during processing: [" + e.getMessage() + "]");
		}
	}

	
	
	






	
	

}//END Class
