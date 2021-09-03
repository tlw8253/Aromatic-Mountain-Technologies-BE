package com.amt.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dto.AddCustomerDTO;
import com.amt.dto.AddOrEditDTO;
import com.amt.dto.AddUserDTO;
import com.amt.dto.AddressDTO;
import com.amt.dto.PhoneNumberDTO;
import com.amt.model.User;
import com.amt.model.UserType;
import com.amt.model.Address;
import com.amt.model.AddressType;
import com.amt.model.EmployeeRole;
import com.amt.model.PhoneNumber;
import com.amt.model.PhoneNumberType;
import com.amt.util.SessionFactorySingleton;
import com.amt.util.PasswordUtil;

public class PhoneNumberDAOImpl implements PhoneNumberDAO, Constants {
	private Logger objLogger = LoggerFactory.getLogger(PhoneNumberDAOImpl.class);

	public PhoneNumberDAOImpl() {
		super();
	}


	
	//
	// ###
	@Override // 20210819 1223 working method added user using Admin drive through service
				// layer
	public PhoneNumber addNewPhoneNumber(PhoneNumberDTO objPhoneNumberDTO) throws SQLException, HibernateException {
		String sMethod = csCRT + "addNewPhoneNumber(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		objLogger.debug(sMethod + "objPhoneNumberDTO: [" + objPhoneNumberDTO.toString() + "]");

		/*
			private int phoneNumbereId = 0;
	private String phoneNumberCountryCode = "01";
	private String phoneNumber = "";
	private PhoneNumberType phoneNumberType;
	private User user;
*/
		
		// by this time the service layer would have validated the parameters
		String sPhoneNumber = objPhoneNumberDTO.getPhoneNumber();
		String sPhoneNumberType = objPhoneNumberDTO.getPhoneNumberType();
		String sUsername = objPhoneNumberDTO.getUsername();

		
		PhoneNumber objPhoneNumber = new PhoneNumber(sPhoneNumber);
		objLogger.debug(sMethod + "objPhoneNumber: [" + objPhoneNumber.toString() + "]");
		
		// get Address Type object
		PhoneNumberTypeDAOImpl objPhoneNumberTypeDAOImpl = new PhoneNumberTypeDAOImpl();
		PhoneNumberType objPhoneNumberType = objPhoneNumberTypeDAOImpl.getByRecordIdentifer(sPhoneNumberType);
		objLogger.debug(sMethod + "objPhoneNumberType: [" + objPhoneNumberType.toString() + "]");
		
		objPhoneNumber.setPhoneNumberType(objPhoneNumberType);;		
		
		// get User object
		UserDAOImpl objUserDAOImpl = new UserDAOImpl();
		User objUser = objUserDAOImpl.getByUsername(sUsername);
		objPhoneNumber.setUser(objUser);
		objLogger.debug(sMethod + "objPhoneNumber: [" + objPhoneNumber.toString() + "]");				

		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		try {
			
			objLogger.debug(sMethod + "Adding final objPhoneNumber: [" + objPhoneNumber.toString() + "] to the database.");

			session.persist(objPhoneNumber);
			tx.commit();
			return objPhoneNumber;

		} catch (Exception e) {
			objLogger.error(sMethod + "Exception: cause: [" + e.getCause() + "] class name [" + e.getClass().getName()
					+ "] [" + e.toString() + "]");
			objLogger.error(sMethod + "Exception: toString: [" + e.toString() + " message: [" + e.getMessage() + "]");
			return null;
		} finally {
			session.close();
		}

	}



}//END Class
