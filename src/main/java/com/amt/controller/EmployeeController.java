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
import com.amt.model.Order;
import com.amt.model.User;
import com.amt.service.UserService;

public class EmployeeController implements Controller, Constants {
	private Logger objLogger = LoggerFactory.getLogger(EmployeeController.class);
	private UserService objERSUserService;

	Map<String, String> mPathParmaMap;
	Map<String, List<String>> mQueryParmaMap;
	int imPathParmaMapSize = 0;
	int imQueryParmaMap = 0;
	boolean bmQueryParmaMapIsEmpty = true;

	public EmployeeController() {		
		this.objERSUserService = new UserService();
	}

	//
	// ###
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

	

	//
	// ### 
	private Handler getERSUserRole = (objCtx) -> {
		String sMethod = "getERSUserRole(): ";
		boolean bContinue = true;
		objLogger.trace(sMethod + "Entered");
		User objUser = null;
		
		String sParamUserId = "";

		setContextMaps(objCtx);
		
		//expect 1 path parameters with user id
		if (imPathParmaMapSize != 1) {			
			//Check for body params before erroring here		
			
			objLogger.debug(sMethod + csMsgBadParamPathParmNotRightNumber);
			objCtx.status(ciStatusCodeErrorBadRequest);
			objCtx.json(csMsgBadParamPathParmNotRightNumber);
			bContinue = false;
		} else {
			sParamUserId = objCtx.queryParam(csParamPathUserId);
			objLogger.debug(sMethod + "Context query parameter user id: [" + sParamUserId + "]");
		}

		if(bContinue) {
		}
		
		objCtx.status(ciStatusCodeSuccess);
		objCtx.json(objUser);
	};

	//
	// ### 
	//
	// ### 
	private Handler getERSUserById = (objCtx) -> {
		String sMethod = "getERSUserById(): ";
		boolean bContinue = true;
		objLogger.trace(sMethod + "Entered");
		User objUser = null;
		
		String sParamUserId = "";

		setContextMaps(objCtx);
		
		//expect 1 path parameters with user id
		if (imPathParmaMapSize != 1) {			
			//Check for body params before erroring here		
			
			objLogger.debug(sMethod + csMsgBadParamPathParmNotRightNumber);
			objCtx.status(ciStatusCodeErrorBadRequest);
			objCtx.json(csMsgBadParamPathParmNotRightNumber);
			bContinue = false;
		} else {
			
			sParamUserId = objCtx.pathParam(csParamPathUserId);
			objLogger.debug(sMethod + "Context path parameter user id: [" + sParamUserId + "]");
			
		}

		if(bContinue) {
			objUser = objERSUserService.getUsersById(sParamUserId);
			objLogger.debug(sMethod + "objEmployee: [" + objUser.toString() + "]");
		}
		
		objCtx.status(ciStatusCodeSuccess);
		objCtx.json(objUser);
	};



	
	@Override
	public void mapEndpoints(Javalin app) {

		//
		//app.get(csRootEndpointERS_UserRole + "/:" + csParamPathUserId, getERSUserRole);
		app.get("/ers_user_role/:user_id/role", getERSUserRole);
		app.get("/ers/:user_id", getERSUserById);
	}

}
