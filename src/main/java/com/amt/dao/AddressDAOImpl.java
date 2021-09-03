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
import com.amt.model.User;
import com.amt.model.UserType;
import com.amt.model.Address;
import com.amt.model.AddressType;
import com.amt.model.EmployeeRole;
import com.amt.util.SessionFactorySingleton;
import com.amt.util.PasswordUtil;

public class AddressDAOImpl implements AddressDAO, Constants {
	private Logger objLogger = LoggerFactory.getLogger(AddressDAOImpl.class);

	public AddressDAOImpl() {
		super();
	}


	
	//
	// ###
	@Override // 20210819 1223 working method added user using Admin drive through service
				// layer
	public Address addAddress(AddressDTO objAddressDTO) throws SQLException, HibernateException {
		String sMethod = csCRT + "addAddress(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		objLogger.debug(sMethod + "objAddressDTO: [" + objAddressDTO.toString() + "]");
/*
		private int addressTypeId = 0;
	private String addressLine1 = "";
	private String addressLine2 = "";
	private String addressCity = "";
	private String addressState = "";
	private String addressZipCode = "";
	private String addressCountry = "United States";
	private String addressType;
	private String username;
*/	
		
		// by this time the service layer would have validated the parameters
		String sAdx1 = objAddressDTO.getAddressLine1();
		String sAdx2 = objAddressDTO.getAddressLine2();
		String sCity = objAddressDTO.getAddressCity();
		String sState = objAddressDTO.getAddressState();
		String sZip = objAddressDTO.getAddressZipCode();
		String sCountry = objAddressDTO.getAddressCountry();		
		String sType = objAddressDTO.getAddressType();
		String sUsername = objAddressDTO.getUsername();

		
		Address objNewAddress = new Address(sAdx1, sAdx2, sCity, sState, sZip, sCountry);
		objLogger.debug(sMethod + "objAddress: [" + objNewAddress.toString() + "]");
		
		// get Address Type object
		AddressTypeDAOImpl objAddressTypeDAOImpl = new AddressTypeDAOImpl();
		AddressType objAddressType = objAddressTypeDAOImpl.getByRecordIdentifer(sType);
		objNewAddress.setAddressType(objAddressType);
		objLogger.debug(sMethod + "objAddressType: [" + objAddressType.toString() + "]");
		
		// get User object
		UserDAOImpl objUserDAOImpl = new UserDAOImpl();
		User objUser = objUserDAOImpl.getByUsername(sUsername);
		objNewAddress.setUser(objUser);
		objLogger.debug(sMethod + "objAddress: [" + objNewAddress.toString() + "]");				

		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		try {
			
			objLogger.debug(sMethod + "Adding final objNewAddress: [" + objNewAddress.toString() + "] to the database.");

			session.persist(objNewAddress);
			tx.commit();
			return objNewAddress;

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
