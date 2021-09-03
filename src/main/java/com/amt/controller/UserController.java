package com.amt.controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dto.AddAddressDTO;
import com.amt.dto.AddCustomerDTO;
import com.amt.dto.AddUserDTO;
import com.amt.dto.AddressDTO;
import com.amt.model.Address;
import com.amt.model.Order;
import com.amt.model.User;
import com.amt.service.AddressService;
import com.amt.service.UserService;

public class UserController implements Controller, Constants {
	private Logger objLogger = LoggerFactory.getLogger(UserController.class);
	private UserService objUserService;
	private AddressService objAddressService;

	Map<String, String> mPathParmaMap;
	Map<String, List<String>> mQueryParmaMap;
	int imPathParmaMapSize = 0;
	int imQueryParmaMap = 0;
	boolean bmQueryParmaMapIsEmpty = true;

	public UserController() {
		this.objUserService = new UserService();
		this.objAddressService = new AddressService();
	}

	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	private void setContextMaps(Context objCtx) {
		String sMethod = "setContextMaps(): ";
		objLogger.trace(sMethod + "Entered");

		mPathParmaMap = objCtx.pathParamMap();
		imPathParmaMapSize = mPathParmaMap.size();
		mQueryParmaMap = objCtx.queryParamMap();
		imQueryParmaMap = mQueryParmaMap.size();
		bmQueryParmaMapIsEmpty = mQueryParmaMap.isEmpty();

		logContextParameters(objCtx);
	}

	//
	// ###
	private void logContextParameters(Context objCtx) {
		String sMethod = "logContextParameters(): ";
		objLogger.trace(sMethod + "Entered");

		mPathParmaMap = objCtx.pathParamMap();
		objLogger.debug(sMethod + "Context path parameter map: [" + mPathParmaMap + "]");
		objLogger.debug(sMethod + "Context path parameter map size: [" + imPathParmaMapSize + "]");

		mQueryParmaMap = objCtx.queryParamMap();
		objLogger.debug(sMethod + "Context query parameter map: [" + mQueryParmaMap + "]");
		objLogger.debug(sMethod + "Context query parameter map size: [" + imQueryParmaMap + "] isEmpty: ["
				+ bmQueryParmaMapIsEmpty + "]");
	}

	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	private Handler postAddAddress = (objCtx) -> {
		String sMethod = csCRT + "postAddAddress(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		Address objAddress = new Address();

		String sParamUsername = "";
		setContextMaps(objCtx);

		// expect 1 path parameters with user id
		if (imPathParmaMapSize != 1) {
			objLogger.debug(sMethod + csMsgBadParamPathParmNotRightNumber);
			objCtx.status(ciStatusCodeErrorBadRequest);
			objCtx.json(csMsgBadParamPathParmNotRightNumber);
			return;
		}

		sParamUsername = objCtx.pathParam(csParamUserName);
		objLogger.debug(sMethod + "Context path parameter username: [" + sParamUsername + "]");

		// preset failure to cover if bodyAsClass fails
		objCtx.status(ciStatusCodeErrorBadRequest);
		objCtx.json(csMsgBadParamCustomerBodyAsClass);

		AddAddressDTO objAddAddressDTO = objCtx.bodyAsClass(AddAddressDTO.class);
		objLogger.debug(sMethod + "objAddAddressDTO: [" + objAddAddressDTO.toString() + "]");

		String sLine1 = objAddAddressDTO.getAddressLine1();
		String sLine2 = objAddAddressDTO.getAddressLine2();
		String sCity = objAddAddressDTO.getAddressCity();
		String sState = objAddAddressDTO.getAddressState();
		String sZip = objAddAddressDTO.getAddressZipCode();
		String sType = objAddAddressDTO.getAddressType();

		AddressDTO objAddressDTO = new AddressDTO(sLine1, sLine2, sCity, sState, sZip, sType, sParamUsername);		
		objLogger.debug(sMethod + "calling add service with objAddressDTO: [" + objAddressDTO.toString() + "]");
		objAddress = objAddressService.addNewAddress(objAddressDTO);
		objLogger.debug(sMethod + "objAddress: [" + objAddress.toString() + "]");

		objCtx.status(ciStatusCodeSuccess);
		objCtx.json(objAddress);
	};

	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	private Handler postAddCustomer = (objCtx) -> {
		String sMethod = csCRT + "postAddCustomer(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		User objUser = new User();

		setContextMaps(objCtx);

		// preset failure to cover if bodyAsClass fails
		objCtx.status(ciStatusCodeErrorBadRequest);
		objCtx.json(csMsgBadParamCustomerBodyAsClass);

		AddCustomerDTO objAddCustomerDTO = objCtx.bodyAsClass(AddCustomerDTO.class);
		objLogger.debug(sMethod + "objAddCustomerDTO: [" + objAddCustomerDTO.toString() + "]");

		String sUsername = objAddCustomerDTO.getUsername();
		String sPassword = objAddCustomerDTO.getPassword();
		String sFirstName = objAddCustomerDTO.getFirstName();
		String sLastName = objAddCustomerDTO.getLastName();
		String sEmail = objAddCustomerDTO.getEmail();
		String sEmployeeRole = csarEmployeeRoles[enumUserEmployee.CUSTOMER.pos];
		String sUserType = csarUserType[enumUserType.CUSTOMER.pos];

		AddUserDTO objAddUserDTO = new AddUserDTO(sUsername, sPassword, sFirstName, sLastName, sEmail, sEmployeeRole,
				sUserType);
		objLogger.debug(sMethod + "calling add service with objAddUserDTO: [" + objAddUserDTO.toString() + "]");

		
		objUser = objUserService.addNewUser(objAddUserDTO);
		objLogger.debug(sMethod + "objUser: [" + objUser.toString() + "]");

		objCtx.status(ciStatusCodeSuccess);
		objCtx.json(objUser);
	};

	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	private Handler getUserByUsername = (objCtx) -> {
		String sMethod = csCRT + "getUserByUsername(): ";
		objLogger.trace(csCR + sMethod + "Entered");
		User objUser = new User();

		String sParamUsername = "";
		setContextMaps(objCtx);

		// expect 1 path parameters with user id
		if (imPathParmaMapSize != 1) {
			objLogger.debug(sMethod + csMsgBadParamPathParmNotRightNumber);
			objCtx.status(ciStatusCodeErrorBadRequest);
			objCtx.json(csMsgBadParamPathParmNotRightNumber);
			return;
		}

		sParamUsername = objCtx.pathParam(csParamUserName);
		objLogger.debug(sMethod + "Context path parameter username: [" + sParamUsername + "]");
		objUser = objUserService.getUsersByUsername(sParamUsername);
		objLogger.debug(sMethod + "objEmployee: [" + objUser.toString() + "]");

		objCtx.status(ciStatusCodeSuccess);
		objCtx.json(objUser);
	};

	@Override
	public void mapEndpoints(Javalin app) {

		//
		// app.get(csRootEndpointERS_UserRole + "/:" + csParamPathUserId,
		// getERSUserRole);
		// app.get("/ers_user_role/:user_id/role", getUserRole);
		// app.get("/ers/:user_id", getUserById);
		app.post("/amt_customer", postAddCustomer);
		app.post("/amt_adx/:username", postAddAddress);
		app.get("/amt_user/:username", getUserByUsername);

	}

}
