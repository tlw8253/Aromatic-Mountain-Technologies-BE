package com.amt.app;

/**
 * Implementing class Constants as an interface allows the class to use defined
 * variable direction without using qualifying notation: Constants.varName;
 * 
 * Classes not implementing class Constants can still access variable using
 * qualifying notation: Constants.varName;
 * 
 * @author tlw8748253
 *
 */

/*
 * Interface methods are by default abstract and public Interface attributes are
 * by default public, static and final
 */
public interface Constants {

	// Return status codes
	// source: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status
	int ciStatusCodeSuccess = 200; // The request has succeeded.
	int ciStatusCodeSuccessCreated = 201; // The request has succeeded and a new resource has been created as a result.
	int ciStatusCodeSuccessNoContent = 204; // There is no content to send for this request,

	int ciStatusCodeErrorBadRequest = 400; // The server could not understand the request due to invalid syntax.
	int ciStatusCodeNotFound = 404; // The server can not find the requested resource. In the browser.
	int ciStatusCodeImA_Teapot = 418; // The server refuses the attempt to brew coffee with a teapot.

	int ciStatusCodeInternalServerError = 500; // The server has encountered a situation it doesn't know how to handle.

	// password special characters allowed for this program
	String csPasswordAllowedSpecialChars = "~$^_!#%+-<>";
	String csAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	String csNumeric = "0123456789";
	String csAllPasswordCharacters = csNumeric + csAlphabet + csPasswordAllowedSpecialChars;

	// system parameters
	String csClientStaticFolder = "AMT-Client";
	int ciListingPort = 3025;
	String csSessionCurrentUser = "current_user";
	String csEnvironmentDB_UsernameVarible = "p0_db_username";

	// End Points
	String csRootEndpoint = "/amt";

	String csRootEndpointLogin = "/amt_login";
	String csRootEndpointLogout = "/amt_logout";
	String csRootEndpointCurrentUser = "/amt_current_user";
	String csRootEndpointSessionValidate = "/amt_session_user";

	String csRootEndpointAdminStatus = "/amt_admin_status";

	// HTTP request parameter constants
	String csParamPathUserId = "user_id";
	String csParamUserName = "username";
	String csParamPassword = "password";
	String csParamReimStatus = "reim_status";
	String csParamPathReimbId = "reim_id";

	// other constants
	int ciUsernameMinLength = 8;
	int ciUsernamMaxLength = 15;
	int ciReimbRecByIdentifierAuthor = 10;
	int ciReimbRecByIdentifierResolver = 20;
	int ciUserMinPassword = 8;
	int ciUserMaxPassword = 15;

	// database constants
	String csDatabaseName = "amt_online_sys"; // database name

	// table constants these must match the table attributes

	// table: order Status
	String csOrderStatusTable = "amt_order_status"; // table name
	String csDBOrderStatusTable = csDatabaseName + "." + csOrderStatusTable; // qualified table name

	String csOrderStatusTblOrderStatusId = "order_status_id"; // PK primary key
	String csReimbStatusTblReimbStatus = "order_status"; // String
	String csReimbStatusTblReimbStatusDesc = "order_status_desc"; // String
	String[] csReimbStatus = { "NEW", "HOLD", "PENDING", "PAID", "FINAL" };

	enum enOrderStatus {
		NEW(0), HOLD(1), PENDING(2), PAID(3), FINAL(4);

		int pos;

		enOrderStatus(int pos) {
			this.pos = pos;
		}

		int pos() {
			return pos;
		}
	};

	// table: order Status
	String csOrderTable = "amt_order"; // table name
	String csDBOrderTable = csDatabaseName + "." + csOrderStatusTable; // qualified table name
	String csOrderTblOrderId = "order_id"; // PK primary key
	String csOrderTblSubmitted = "order_submitted";
	String csOrderTblAmount = "order_amount";
	String csOrderTblOrderItems = "order_items";
	
	// table: order items
	String csOrderItemsTable = "amt_order_items"; // table name
	String csDBOrderItemsTable = csDatabaseName + "." + csOrderItemsTable; // qualified table name
	String csOrderItemsTblOrderItemsId = "order_items_id"; // PK primary key
	String csOrderItemsTblOrderPrice = "order_items_price";
	String csOrderItemsTblItemQty = "order_items_qty";


	// table: Catalog
	String csCatalogTable = "amt_catalog_item"; // table name
	String csDBCatalogTable = csDatabaseName + "." + csCatalogTable; // qualified table name
	String csCatalogTblCatalogId = "catalog_id"; // PK primary key
	String csCatalogItem = "catalog_item";
	String csCatalogItemDesc = "catalog_item_description";
	String csCatalogItemPrice = "catalog_item_price";
	String csCatalogItemInStockQty = "catalog_item_in_stock_qty";
	String csCatalogTblCatalogTypeId = "catalog_type_id";

	// table: Catalog Type
	String csCatalogTypeTable = "amt_catalog_type"; // table name
	String csDBCatalogTypeTable = csDatabaseName + "." + csCatalogTypeTable; // qualified table name
	String csCatalogTypeTblCatalogTypeId = "catalog_type_id"; // PK primary key
	String csCatalogTypeTblCatalogType = "catalog_type"; // String
	String csCatalogTypeTblCatalogTypeDesc = "catalog_type_desc"; // String
	String[] csCatalogType = { "BEANS", "GROUND", "SYRUP", "ACCESSORY", "APPAREL" }; // coffee beans, ground coffee,
																						// syrups, coffee accessory and
																						// apparel

	enum enCatalogType {
		BEANS(0), GROUND(1), SYRUP(2), ACCESSORY(3), APPAREL(4);

		int pos;

		enCatalogType(int pos) {
			this.pos = pos;
		}

		int pos() {
			return pos;
		}
	};

	// table: User Type
	String csUserTypeTable = "amt_user_type"; // table name
	String csDBUserTypeTable = csDatabaseName + "." + csUserTypeTable; // qualified table name

	String csUserTypeTblUserTypeId = "user_type_id"; // PK primary key
	String csUserTypeTblUserType = "user_type"; // String
	String csUserTypeTblUserTypeDesc = "user_type_desc"; // String
	String[] csUserType = { "EMPLOYEE", "CUSTOMER" };
	int ciUserTypeEmployee = 0;
	int ciUserTypeCustomer = 1;
	String[] csUserTypeDesc = { "EMPLOYEE: This user type is an active employee of the AMT system.",
			"CUSTOMER: This user type is an external customer viewing the catalog and buying merchandise." };

	// table: User Roles
	String csUserRolesTable = "amt_user_roles"; // table name
	String csDBUserRolesTable = csDatabaseName + "." + csUserRolesTable; // qualified table name

	String csUserRolesTblUserRoleId = "user_role_id"; // PK primary key
	String csUserRolesTblUserRole = "user_role"; // String
	String csUserRolesTblUserRoleDesc = "user_role_desc"; // String
	String[] csEmployeeRoles = { "EMPLOYEE", "CATALOG ADMIN", "CATALOG EMPLOYEE" };
	int ciUserRoleEmployee = 0;
	int ciUserRoleCatalogAmdin = 1;
	int ciUserRoleCatalogeEmployee = 2;
	String[] csEmployeeRolesDesc = { "EMPLOYEE: Any person actively employeed by the company with a valid username.",
			"CATALOG ADMIN: A Catalog Admin controls publishing of Employee catalog items and pages.",
			"CATALOG EMPLOYEE: A Catalog Employee creates catalog items and pages." };


	// table: Address Type
	String csAddressTypeTable = "amt_address_type"; // table name
	String csDBAddressTypeTable = csDatabaseName + "." + csAddressTypeTable; // qualified table name
	String csAddressTypeTblAddressTypeId = "address_type_id"; // PK primary key
	String csAddressTypeTblAddressType = "address_type"; // String
	String csAddressTypeTblAddressTypeDesc = "address_type_desc"; // String
	String[] carrAddressType = { "BILLING", "SHIPPING", "MAILING"}; //
	String[] carrAddressTypeDesc = {"BILLING: The address where the bill of credit card used is mailed.",
									"SHIPPING: The address where the customer wants the merchandise shipped.",
									"MAILING: The home or mailing address where the customer resides."};

	enum enumAddressType {
		BILLING(0), SHIPPING(1), MAILING(2);

		int pos;

		enumAddressType(int pos) {
			this.pos = pos;
		}

		int pos() {
			return pos;
		}
	};


	
	
	
	// table: user
	String csUserTable = "amt_users"; // table name
	String csDBUserTable = csDatabaseName + "." + csUserTable; // if using JDBC

	String csUserTblId = "user_id";
	String csUserTblUsername = "username";
	String csUserTblPassword = "password";
	String csUserTblPasswordSalt = "password_salt";
	String csUserTblFirstName = "first_name";
	String csUsrTblLastName = "last_name";
	String csUserTblEmail = "email";
	String csUserTblRoleId = "role_id";
	String csUserTblRoleName = csUserRolesTblUserRole;

	// HQL fully qualified class names
	String csHQL_ModelPackage = "com.tlw8253.model";
	String csHQL_ModelClassReimbStatus = csHQL_ModelPackage + ".ReimbursementStatus";
	String csHQL_ModelClassReimbType = csHQL_ModelPackage + ".ReimbursementType";
	String csHQL_ModelClassUserRole = csHQL_ModelPackage + ".UserRole";
	String csHQL_ModelClassReimbursement = csHQL_ModelPackage + ".Reimbursement";
	String csHQL_ModelClassUser = csHQL_ModelPackage + ".User";

	// Define program messages to use in the program and for testing
	String csMsgDB_ErrorGettingWithLogin = "Error with database during employee login.";
	String csMsgDB_ErrorAddingReimbursementStatus = "Error with database when adding Reimbursement Status.";
	String csMsgDB_ErrorGettingAllReimbursementStatus = "Error with database when getting all Reimbursement Status.";
	String csMsgDB_ErrorGettingReimbursementStatus = "Error with database when getting a Reimbursement Status.";
	String csMsgDB_ErrorAddingReimbursementType = "Error with database when adding Reimbursement Type.";
	String csMsgDB_ErrorGettingReimbursementType = "Error with database when getting all Reimbursement Types.";
	String csMsgDB_ErrorAddingUserRole = "Error with database when adding User Role.";
	String csMsgDB_ErrorGettingUserRole = "Error with database when getting all User Roles.";

	String csMsgDB_ErrorAddingReimbursement = "Database error when adding reimbursement record.";
	String csMsgDB_ErrorUpdatingReimbursement = "Database error when updating reimbursement record.";
	String csMsgDB_ErrorGettingReimbursements = "Database error when getting all reimbursement.";
	String csMsgDB_ErrorGettingReimbursementById = "Database error when getting a reimbursement by id.";
	String csMsgDB_ErrorGettingReimbursementAuthor = "Database error when getting a reimbursement by Athour's username.";

	String csMsgDB_ErrorAddingEmployee = "Database error when adding an employee.";
	String csMsgDB_ErrorUpdatingEmployee = "Database error when updating employee information.";
	String csMsgDB_ErrorGettingEmployees = "Database error when getting all employees.";
	String csMsgDB_ErrorGettingEmployeeById = "Database error when getting an employee by id.";
	String csMsgDB_ErrorGettingEmployeeByUsername = "Database error when getting an employee by username.";
	String csMsgDB_ErrorDeletingAnEmployee = "Database error while deleting an employee.";

	String csMsgDB_ErrorAuthenticatingUsername = "Database error authenticating a username.";

	String csMsgEmployeeRecordNotFound = "Employee was not found in the database.";

	String csMsgBadParamNoPathParm = "Parmeter(s) expected. No Path Parameter(s) Received.";
	String csMsgBadParamNoBodyParm = "Parmeter(s) expected. No Body Parameter(s) Received.";
	String csMsgBadParamPathParmNotRightNumber = "Parmeter(s) expected. Not right number of Path Parameter(s) received.";
	String csMsgBadParamPathParmNotRightParam = "Parmeter(s) expected. Not right name for Path Parameter(s) received.";

	String csMsgBadParamNoQueryParm = "Parmeter(s) expected. No Query Parameter(s) Received.";
	String csMsgBadParamQueryParm = "Parmeter(s) expected. Not right number of Query Parameter(s) received.";

	String csMsgBadParamReimbStatus = "Invalid Reimbursement Status parameters received.";
	String csMsgBadParamReimbType = "Invalid Reimbursement Type parameters received.";
	String csMsgBadParamUserRole = "Invalid User Role parameters received.";

	String csMsgBadParamAddUser = "One or more add User parameters are invalid.";
	String csMsgBadParamEditUser = "One or more edit User parameters are invalid.";
	String csMsgBadParamGetUserById = "The user id provided was not a number or was zero.";
	String csMsgBadParamGetUserByUsername = "The user name provided was not alpha numeric or length was invalid.";

	String csMsgBadParamGetReimbursementById = "The reimbursement id provided was not a number or was zero.";
	String csMsgBadParamGetUserReimbursementById = "The username and/or reimbursement id provided was not valid.";

	String csMsgBadParamGetUserReimbByIdDoesNotBelong = "The reimbursement does not belong to the username.";

	String csMsgBadParamAddReimb = "One or more add Reimbursement parameters are invalid.";
	String csMsgBadParamUpdateReimb = "One or more update Reimbursement parameters are invalid.";

	String csMsgBadParamLoginUsernamePwdBlank = "Username and password must contain values.";
	String csMsgBadParamLoginUsernamePwdLength = "Username and/or password length is invalid.";

	String csMsgAutenticationFailed = "Autentication failed for username and password provided.";
	String csMsgSessionUserNotActive = "The user does not have an active login session.";
	String csMsgSessionUserLoggedOut = "The user is logged out.";

}
