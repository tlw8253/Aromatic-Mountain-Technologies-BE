package com.amt.controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dto.AddAddressDTO;
import com.amt.dto.AddCustomerDTO;
import com.amt.dto.AddPhoneNumberDTO;
import com.amt.dto.AddUserDTO;
import com.amt.dto.AddressDTO;
import com.amt.dto.LoginDTO;
import com.amt.dto.MessageDTO;
import com.amt.dto.PhoneNumberDTO;
import com.amt.exception.AuthenticationFailureException;
import com.amt.exception.BadParameterException;
import com.amt.exception.DatabaseException;
import com.amt.model.Address;
import com.amt.model.Order;
import com.amt.model.PhoneNumber;
import com.amt.model.User;
import com.amt.service.AddressService;
import com.amt.service.LoginService;
import com.amt.service.PhoneService;
import com.amt.service.UserService;

public class UserController implements Controller, Constants {
	private Logger objLogger = LoggerFactory.getLogger(UserController.class);
	private UserService objUserService;
	private AddressService objAddressService;
	private LoginService objLoginService;
	private PhoneService objPhoneService;

	Map<String, String> mPathParmaMap;
	Map<String, List<String>> mQueryParmaMap;
	int imPathParmaMapSize = 0;
	int imQueryParmaMap = 0;
	boolean bmQueryParmaMapIsEmpty = true;

	public UserController() {
		this.objUserService = new UserService();
		this.objAddressService = new AddressService();
		this.objLoginService = new LoginService();
		this.objPhoneService = new PhoneService();
	}

	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	private void setContextMaps(Context objCtx) {
		String sMethod = csCRT + "setContextMaps(): ";
		objLogger.trace(csCR + sMethod + "Entered");

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
		String sMethod = csCRT + "logContextParameters(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		mPathParmaMap = objCtx.pathParamMap();
		objLogger.debug(sMethod + "Context path parameter map: [" + mPathParmaMap + "]");
		objLogger.debug(sMethod + "Context path parameter map size: [" + imPathParmaMapSize + "]");

		mQueryParmaMap = objCtx.queryParamMap();
		objLogger.debug(sMethod + "Context query parameter map: [" + mQueryParmaMap + "]");
		objLogger.debug(sMethod + "Context query parameter map size: [" + imQueryParmaMap + "] isEmpty: ["
				+ bmQueryParmaMapIsEmpty + "]");
	}

	// ###
	private User getCurrentSessionUser(Context objCtx) {
		String sMethod = csCRT + "getCurrentSessionUser(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		HttpSession httpSession = objCtx.req.getSession();
		objLogger.debug(sMethod + "Getting session attribute for: [" + csSessionCurrentUser + "]");
		if (httpSession.getAttribute(csSessionCurrentUser) == null) {
			objLogger.debug(sMethod + "no active session recorded for any user");
			objCtx.json(new MessageDTO(csMsgSessionUserNotActive));
			objCtx.status(401);
			return null;
		} else {
			User objUser = (User) httpSession.getAttribute(csSessionCurrentUser);
			objLogger.debug(sMethod + "Active session recorded objUser: [" + objUser.toString() + "]");
			return objUser;
		}
	}

	private boolean validateUserSession(Context objCtx, User objGetFromSession, LoginDTO objDTO)
			throws BadParameterException, AuthenticationFailureException, DatabaseException {
		String sMethod = csCRT + "validateUserSession(): ";
		objLogger.trace(csCR + sMethod + "Entered");
		boolean isValid = false;

		objGetFromSession = getCurrentSessionUser(objCtx);
		if (objGetFromSession == null) {
			objLogger.debug(sMethod + "no active session recorded for any user");
		} else {
			if (objLoginService.validateSessionUser(objDTO, objGetFromSession)) {
				isValid = true;
			} else {
				objLogger.debug(sMethod + "current user is not the session user");
				objCtx.json(new MessageDTO(csMsgSessionUserNotActive));
				objCtx.status(401);
			}
		}

		return isValid;
	}

	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	private Handler postAddAddress = (objCtx) -> {
		String sMethod = csCRT + "postAddAddress(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		setContextMaps(objCtx);

		AddAddressDTO objAddAddressDTO = new AddAddressDTO();

		objCtx.status(ciStatusCodeErrorBadRequest);
		objCtx.json(csMsgBadParamCustomerBodyAsClass);

		objAddAddressDTO = objCtx.bodyAsClass(AddAddressDTO.class);
		objLogger.debug(sMethod + "objAddAddressDTO: [" + objAddAddressDTO.toString() + "]");

		Address objAddress = new Address();

		String sParamUsername = "";

		// expect 1 path parameters with user id
		if (imPathParmaMapSize != 1) {
			objLogger.debug(sMethod + csMsgBadParamPathParmNotRightNumber);
			objCtx.status(ciStatusCodeErrorBadRequest);
			objCtx.json(csMsgBadParamPathParmNotRightNumber);
			return;
		}

		sParamUsername = objCtx.pathParam(csParamUserName);
		objLogger.debug(sMethod + "Context path parameter username: [" + sParamUsername + "]");

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
		objUser = objUserService.getUserByUsername(sParamUsername);
		objLogger.debug(sMethod + "objEmployee: [" + objUser.toString() + "]");

		objCtx.status(ciStatusCodeSuccess);
		objCtx.json(objUser);
	};

	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	private Handler postAddPhoneNumber = (objCtx) -> {
		String sMethod = csCRT + "postAddPhoneNumber(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		setContextMaps(objCtx);

		AddPhoneNumberDTO objAddPhoneNumberDTO = new AddPhoneNumberDTO();
		objCtx.status(ciStatusCodeErrorBadRequest);
		objCtx.json(csMsgBadParamCustomerBodyAsClass);

		objAddPhoneNumberDTO = objCtx.bodyAsClass(AddPhoneNumberDTO.class);
		objLogger.debug(sMethod + "objAddPhoneNumberDTO: [" + objAddPhoneNumberDTO.toString() + "]");

		PhoneNumber objPhoneNumber = new PhoneNumber();

		String sParamUsername = "";

		// expect 1 path parameters with user id
		if (imPathParmaMapSize != 1) {
			objLogger.debug(sMethod + csMsgBadParamPathParmNotRightNumber);
			objCtx.status(ciStatusCodeErrorBadRequest);
			objCtx.json(csMsgBadParamPathParmNotRightNumber);
			return;
		}

		sParamUsername = objCtx.pathParam(csParamUserName);
		objLogger.debug(sMethod + "Context path parameter username: [" + sParamUsername + "]");

		String sPhoneNumber = objAddPhoneNumberDTO.getPhoneNumber();
		String sPhoneNumberType = objAddPhoneNumberDTO.getPhoneNumberType();

		PhoneNumberDTO objPhoneNumberDTO = new PhoneNumberDTO(sPhoneNumber, sPhoneNumberType, sParamUsername);
		objLogger.debug(sMethod + "calling add service with objPhoneNumberDTO: [" + objPhoneNumberDTO.toString() + "]");
		objPhoneNumber = objPhoneService.addNewPhoneNumber(objPhoneNumberDTO);
		objLogger.debug(sMethod + "objPhoneNumber: [" + objPhoneNumber.toString() + "]");

		objCtx.status(ciStatusCodeSuccess);
		objCtx.json(objPhoneNumber);

	};

	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	@Override
	public void mapEndpoints(Javalin app) {

		app.post("/amt_adx/:username", postAddAddress);
		app.post("/amt_phone/:username", postAddPhoneNumber);
		app.get("/amt_user/:username", getUserByUsername);
	}

}
