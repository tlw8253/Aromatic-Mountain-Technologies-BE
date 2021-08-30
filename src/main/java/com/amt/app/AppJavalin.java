package com.amt.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.controller.Controller;
import com.amt.controller.CustomerController;
import com.amt.controller.CatalogAdminController;
import com.amt.controller.LoginController;
import com.amt.controller.EmployeeController;
import com.amt.controller.ExceptionController;

import io.javalin.Javalin;


/**
 * 
 * 
 * This is the main driver for this project when using Javalin technologies.
 * 
 * 
 * 
 * @author tlw8253
 *
 */
public class AppJavalin implements Constants {
	private final static Logger objLogger = LoggerFactory.getLogger(AppJavalin.class);
	private static Javalin objJavalinApp;
	
	public static void main(String[] args) {
		String sMethod = "\n\t main(): ";		
		objLogger.trace(sMethod + "Entered");

		//Chrome will not allow sending request without the following config
		objJavalinApp = Javalin.create((config) -> {
			config.enableCorsForAllOrigins();
			config.addStaticFiles(csClientStaticFolder);
		});

//		objJavalinApp = Javalin.create();		
		
		objLogger.debug(sMethod + "mapControllers(new ExceptionController(), new EmployeeController(), new CustomerController(), new CatalogAdminController(), new LoginController() );");
		mapControllers(/*new TestController(),*/ new ExceptionController(), new EmployeeController(), new CustomerController(), 
				new CatalogAdminController(), new LoginController() );
		
		objLogger.info(sMethod + "Starting listening on port: [" + ciListingPort + "]");
		objJavalinApp.start(ciListingPort); // start up our Javalin server on port defined for this program	
		
	}
	
	
	//
	//###
	public static void mapControllers(Controller... controllers) {
		String sMethod = "\n\t mapControllers(): ";		
		objLogger.trace(sMethod + "Entered");

		for (Controller c : controllers) {
			c.mapEndpoints(AppJavalin.objJavalinApp);
		}
	}


}



















