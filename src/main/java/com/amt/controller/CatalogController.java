package com.amt.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dto.AddCatalogItemDTO;
import com.amt.dto.AddPhoneNumberDTO;
import com.amt.dto.LoginDTO;
import com.amt.dto.MessageDTO;
import com.amt.dto.PhoneNumberDTO;
import com.amt.exception.AuthenticationFailureException;
import com.amt.exception.BadParameterException;
import com.amt.exception.DatabaseException;
import com.amt.model.CatalogItem;
import com.amt.model.OrderStatus;
import com.amt.model.PhoneNumber;
import com.amt.model.User;
import com.amt.service.AdminService;
import com.amt.service.CatalogItemService;
import com.amt.service.LoginService;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class CatalogController implements Controller, Constants {
	private Logger objLogger = LoggerFactory.getLogger(CatalogController.class);
	private LoginService objLoginService;
	private CatalogItemService objCatalogItemService;
	
	
	Map<String, String> mPathParmaMap;
	Map<String, List<String>> mQueryParmaMap;
	int imPathParmaMapSize = 0;
	int imQueryParmaMap = 0;
	boolean bmQueryParmaMapIsEmpty = true;

	public CatalogController() {
		super();
		objLoginService = new LoginService();
		objCatalogItemService = new CatalogItemService();
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
	private Handler postAddCatalogItem = (objCtx) -> {
		String sMethod = csCRT + "postAddCatalogItem(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		setContextMaps(objCtx);

		AddCatalogItemDTO objAddCatalogItemDTO = new AddCatalogItemDTO();
		User objCurrSessionUser = new User();
		LoginDTO objDTO = new LoginDTO();

		objCtx.status(ciStatusCodeErrorBadRequest);
		objCtx.json(csMsgBadParamCustomerBodyAsClass);

		objAddCatalogItemDTO = objCtx.bodyAsClass(AddCatalogItemDTO.class);
		objLogger.debug(sMethod + "objAddCatalogItemDTO: [" + objAddCatalogItemDTO.toString() + "]");

		objDTO.setUsername(objAddCatalogItemDTO.getLoginUsername());
		objDTO.setPassword(objAddCatalogItemDTO.getLoginPassword());

		boolean isValidUserSession = validateUserSession(objCtx, objCurrSessionUser, objDTO);

		if (isValidUserSession) {

			CatalogItem objCatalogItem = new CatalogItem();

			
			objLogger.debug(sMethod + "calling add service with objAddCatalogItemDTO: [" + objAddCatalogItemDTO.toString() + "]");
			objCatalogItem = objCatalogItemService.addNewCatalogItem(objAddCatalogItemDTO);					
			
			objLogger.debug(sMethod + "objCatalogItem: [" + objCatalogItem.toString() + "]");			

			objCtx.status(ciStatusCodeSuccess);
			objCtx.json(objCatalogItem);
		}

	};

	
	
	@Override
	public void mapEndpoints(Javalin app) {
		//
		app.post("/amt_catalog_item", postAddCatalogItem);		
		
	}

}
