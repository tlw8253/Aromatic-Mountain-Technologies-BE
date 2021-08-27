package com.amt.controller;

import com.amt.app.Constants;
import com.amt.dto.ExceptionMessageDTO;
import com.amt.exception.AuthenticationFailureException;
import com.amt.exception.BadParameterException;
import com.amt.exception.DatabaseException;
import com.amt.exception.RecordNotFoundException;

import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;

public class ExceptionController implements Controller, Constants {

	
	private ExceptionHandler<DatabaseException> databaseExceptionHandler = (e, ctx) -> {
		ctx.status(ciStatusCodeInternalServerError); 
		
		// Here we encapsulate the exception message into a DTO that will be sent as JSON back to the user
		ExceptionMessageDTO messageDTO = new ExceptionMessageDTO();
		messageDTO.setMessage(e.getMessage());
		
		ctx.json(messageDTO);
	};
	
	
	private ExceptionHandler<AuthenticationFailureException> genericExceptionHandler = (e, ctx) -> {
		ctx.status(ciStatusCodeImA_Teapot); 
		
		ExceptionMessageDTO messageDTO = new ExceptionMessageDTO();
		messageDTO.setMessage(e.getMessage());
		
		ctx.json(messageDTO);
	};
	
	
	private ExceptionHandler<BadParameterException> badParameterExceptionHandler = (e, ctx) -> {
		ctx.status(ciStatusCodeErrorBadRequest);
		
		ExceptionMessageDTO messageDTO = new ExceptionMessageDTO();
		messageDTO.setMessage(e.getMessage());
		
		ctx.json(messageDTO);
	};
	
	private ExceptionHandler<RecordNotFoundException> AccountNotFoundExceptionHandler = (e, ctx) -> {
		ctx.status(ciStatusCodeNotFound);
		
		ExceptionMessageDTO messageDTO = new ExceptionMessageDTO();
		messageDTO.setMessage(e.getMessage());
		
		ctx.json(messageDTO);
	};

	@Override
	public void mapEndpoints(Javalin app) {
		app.exception(DatabaseException.class, databaseExceptionHandler);
		app.exception(AuthenticationFailureException.class, genericExceptionHandler);
		app.exception(BadParameterException.class, badParameterExceptionHandler);
		app.exception(RecordNotFoundException.class, AccountNotFoundExceptionHandler);
	}


	
	
	
}











