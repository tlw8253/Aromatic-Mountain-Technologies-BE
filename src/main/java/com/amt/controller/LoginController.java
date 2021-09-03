package com.amt.controller;

import javax.servlet.http.HttpSession;

import io.javalin.Javalin;
import io.javalin.http.Context;
//import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dto.LoginDTO;
import com.amt.dto.MessageDTO;
import com.amt.model.User;
import com.amt.service.LoginService;

public class LoginController implements Controller, Constants {
	private Logger objLogger = LoggerFactory.getLogger(LoginController.class);
	private LoginService objLoginService;
	// private LoginDTO objLoginDTO = new LoginDTO();

	Map<String, String> mPathParmaMap;
	Map<String, List<String>> mQueryParmaMap;
	int imPathParmaMapSize = 0;
	int imQueryParmaMap = 0;
	boolean bmQueryParmaMapIsEmpty = true;

	public LoginController() {
		this.objLoginService = new LoginService();
	}

	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	private Handler postLoginHandler = (objCtx) -> {
		String sMethod = "\n\t postLoginHandler(): ";
		objLogger.trace(sMethod + "Entered");

		LoginDTO objLoginDTO = new LoginDTO();
		objLoginDTO = objCtx.bodyAsClass(LoginDTO.class);
		objLogger.debug(sMethod + "objLoginDTO: [" + objLoginDTO.toString() + "]");

		User objUser = objLoginService.login(objLoginDTO);
		objLogger.debug(sMethod + "objUser: [" + objUser.toString() + "]");

		HttpSession httpSession = objCtx.req.getSession();
		httpSession.setAttribute(csSessionCurrentUser, objUser);

		if (httpSession.getAttribute(csSessionCurrentUser) == null) {
			objLogger.debug(sMethod + csSessionCurrentUser + " is null");
			objCtx.json(csMsgAutenticationFailed);
			objCtx.status(401);
		} else {
			objLogger.debug(sMethod + csSessionCurrentUser + " is not null");
			objCtx.json(objUser);
			objCtx.status(200);
		}
	};

	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	private Handler getCurrentUserHandler = (objCtx) -> {
		String sMethod = csCRT + "getCurrentUserHandler(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		HttpSession httpSession = objCtx.req.getSession();
		objLogger.debug(sMethod + "Getting session attribute for: [" + csSessionCurrentUser + "]");
		if (httpSession.getAttribute(csSessionCurrentUser) == null) {
			objLogger.debug(sMethod + "no active session recorded for any user");
			objCtx.json(new MessageDTO(csMsgSessionUserNotActive));
			objCtx.status(401);
		} else {
			User objUser = (User) httpSession.getAttribute(csSessionCurrentUser);
			objLogger.debug(sMethod + "Active session recorded objUser: [" + objUser.toString() + "]");
			objCtx.json(objUser);
			objCtx.status(200);
		}

	};

	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	private Handler postValidateSessionUserHandler = (objCtx) -> {
		String sMethod = csCRT + "postValidateSessionUserHandler(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		HttpSession httpSession = objCtx.req.getSession();

		if (httpSession.getAttribute(csSessionCurrentUser) == null) {
			objLogger.debug(sMethod + "there is no active session for any user.");
			objCtx.json(new MessageDTO(csMsgSessionUserNotActive));
			objCtx.status(401);
		} else {

			User objSessionUser = (User) httpSession.getAttribute(csSessionCurrentUser);
			objLogger.debug(
					sMethod + "current user from httpSession: objSessionUser: [" + objSessionUser.toString() + "]");
			LoginDTO objThisDTO = new LoginDTO();

			objThisDTO = objCtx.bodyAsClass(LoginDTO.class);
			objLogger.debug(sMethod + "bodyAsClass: objThisDTO: [" + objThisDTO.toString() + "]");

			objLogger.debug(sMethod + "calling service to see if session and dto user objects are equal");
			if (objLoginService.validateSessionUser(objThisDTO, objSessionUser)) {
				objLogger.debug(sMethod + "service validated DTO and Session users are the same.");
				objCtx.json(objSessionUser);
				objCtx.status(200);
			} else {
				objLogger
						.debug(sMethod + "there is no active session for username: [" + objThisDTO.getUsername() + "]");
				// objCtx.json(new MessageDTO(csMsgSessionUserNotActive));
				objCtx.json(csMsgSessionUserNotActive);
				objCtx.status(401);
			}
		}

	};

	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	private Handler postLogoutHandler = (objCtx) -> {
		String sMethod = csCRT + "postErsLogoutHandler(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		HttpSession httpSession = objCtx.req.getSession();
		if (httpSession.getAttribute(csSessionCurrentUser) == null) {
			objLogger.debug(sMethod + "there is no active session for any user.");
			objCtx.json(new MessageDTO(csMsgSessionUserLoggedOut));
			objCtx.status(200);
		} else {
			User objSessionUser = (User) httpSession.getAttribute(csSessionCurrentUser);
			objLogger.debug(
					sMethod + "current user from httpSession: objSessionUser: [" + objSessionUser.toString() + "]");
			LoginDTO objThisDTO = new LoginDTO();

			objThisDTO = objCtx.bodyAsClass(LoginDTO.class);
			objLogger.debug(sMethod + "bodyAsClass: objThisDTO: [" + objThisDTO.toString() + "]");

			objLogger.debug(sMethod + "calling service to see if session and dto user objects are equal");
			if (objLoginService.validateSessionUser(objThisDTO, objSessionUser)) {
				objLogger.debug(sMethod + "service validated DTO and Session users are the same.");
				objLogger.debug(sMethod + "username: [" + objThisDTO.getUsername() + "] is being logged out.");
				httpSession.setAttribute(csSessionCurrentUser, null);
				objCtx.json(new MessageDTO(csMsgSessionUserLoggedOut));
				objCtx.status(200);
			} else {
				objLogger
						.debug(sMethod + "there is no active session for username: [" + objThisDTO.getUsername() + "]");
				objCtx.json(new MessageDTO(csMsgSessionUserNotActive));
				objCtx.status(401);
			}
		}

	};

	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	@Override
	public void mapEndpoints(Javalin app) {

		//
		app.post(csRootEndpointLogin, postLoginHandler);
		app.post(csRootEndpointLogout, postLogoutHandler);
		app.get(csRootEndpointCurrentUser, getCurrentUserHandler);
		app.post(csRootEndpointSessionValidate, postValidateSessionUserHandler);

	}

}
