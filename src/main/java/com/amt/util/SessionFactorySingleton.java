package com.amt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactorySingleton implements Constants {
	private static Logger objLogger = LoggerFactory.getLogger(SessionFactorySingleton.class);
	// This is a singleton SessionFactory, which means only a single instance of
	// SessionFactory will exist
	// during the lifetime of our application running
	private static SessionFactory sessionFactory;

	public synchronized static SessionFactory getSessionFactory() {
		String sMethod = "SessionFactory(): ";
		objLogger.trace(sMethod + "Entered");

		if (sessionFactory == null) {
			objLogger.debug(sMethod + "creating session configuration");
			Configuration config = new Configuration();
			/*
			String sHibernateUrl = System.getenv("aws_db_url") + System.getenv("p2_db_name");
			config.setProperty("hibernate.connection.url", sHibernateUrl);
			config.setProperty("hibernate.connection.username", System.getenv("aws_db_username"));
			config.setProperty("hibernate.connection.password", System.getenv("aws_db_password"));
			*/
			
			config.setProperty("hibernate.connection.url", csDatabaseConnectionURL);
			config.setProperty("hibernate.connection.username", csDatabaseConnectionUsername);
			config.setProperty("hibernate.connection.password", csDatabaseConnectionPwd);
			
			
			
			
			config.configure("hibernate.cfg.xml");

			objLogger.debug(sMethod + "building session factory");
			sessionFactory = config.buildSessionFactory();
			objLogger.debug(sMethod + "returned from building session factory");
		}

		return sessionFactory;
	}

}
