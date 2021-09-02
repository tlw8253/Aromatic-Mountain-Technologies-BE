package com.amt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactorySingleton {
	private static Logger objLogger = LoggerFactory.getLogger(SessionFactorySingleton.class);
	// This is a singleton SessionFactory, which means only a single instance of
	// SessionFactory will exist
	// during the lifetime of our application running
	private static SessionFactory sessionFactory;

	public synchronized static SessionFactory getSessionFactory() {
		String sMethod = "SessionFactory(): ";
		objLogger.trace(sMethod + "Entered");

		boolean bLocalhost = false;
		boolean bConfigFile = true;

		if (sessionFactory == null) {
			objLogger.debug(sMethod + "creating session configuration");
			Configuration config = new Configuration();

			if (!bConfigFile) {
				// the base database url without the database name
				String sDbEnv = "";
				String sUsernameEnv = "";
				String sPasswordEnv = "";

				sUsernameEnv = "aws_db_username";
				sPasswordEnv = "aws_db_password";
				sDbEnv = "aws_db_url";

				if (bLocalhost) {
					sUsernameEnv = "localhost_db_username";
					sPasswordEnv = "localhost_db_password";
					sDbEnv = "localhost_db_url";
				}

				// get system environment variables either local or remote based on flag
				String sDbUrl = System.getenv(sDbEnv);
				String sDbUsername = System.getenv(sUsernameEnv);
				String sDbPassword = System.getenv(sPasswordEnv);

				// set the project database name in the connection url
				String sProjectDbName = System.getenv("p2_db_name");
				String sHibernateConUrl = sDbUrl + sProjectDbName;

				config.setProperty("hibernate.connection.url", System.getenv(sHibernateConUrl));
				config.setProperty("hibernate.connection.username", System.getenv(sDbUsername));
				config.setProperty("hibernate.connection.password", System.getenv(sDbPassword));
			}

			config.configure("hibernate.cfg.xml");

			objLogger.debug(sMethod + "building session factory");
			sessionFactory = config.buildSessionFactory();
			objLogger.debug(sMethod + "returned from building session factory");
		}

		return sessionFactory;
	}
}
