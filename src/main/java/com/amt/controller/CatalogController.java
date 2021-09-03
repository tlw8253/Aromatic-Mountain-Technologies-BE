package com.amt.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.model.OrderStatus;
import com.amt.service.AdminService;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class CatalogController implements Controller, Constants {
	private Logger objLogger = LoggerFactory.getLogger(CatalogController.class);
	private AdminService objERSAdminService = new AdminService();
	
	Map<String, String> mPathParmaMap;
	Map<String, List<String>> mQueryParmaMap;
	int imPathParmaMapSize = 0;
	int imQueryParmaMap = 0;
	boolean bmQueryParmaMapIsEmpty = true;

	public CatalogController() {
		// TODO Auto-generated constructor stub
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

	private Handler postAddErsStatus = (objCtx) -> {
		String sMethod = "getErs(): ";
		boolean bContinue = true;
		objLogger.trace(sMethod + "Entered");

		setContextMaps(objCtx);
		String sParamReimStatus = "";
		String sParamReimStatusDesc = "";
		OrderStatus objReimbStatus = null;
		
		//expect 2 query parameters with login request
		if (imQueryParmaMap != 2) {			
			
			objLogger.debug(sMethod + csMsgBadParamQueryParm);
			objCtx.status(ciStatusCodeErrorBadRequest);
			objCtx.json(csMsgBadParamQueryParm);
			bContinue = false;
		} else {
			sParamReimStatus = objCtx.queryParam(csParamReimStatus);
			objLogger.debug(sMethod + "Context query parameter reimbursement status: [" + sParamReimStatus + "]");
			sParamReimStatusDesc = objCtx.queryParam(csOrderStatusTblOrderStatusDesc);
			objLogger.debug(sMethod + "Context query parameter reimbursement status description: [" + sParamReimStatusDesc + "]");
		}

		
		objCtx.status(ciStatusCodeSuccess);
		objCtx.json(objReimbStatus);
	};

	
	
	@Override
	public void mapEndpoints(Javalin app) {
		//
		app.post(csRootEndpointAdminStatus, postAddErsStatus);
		
	}

}