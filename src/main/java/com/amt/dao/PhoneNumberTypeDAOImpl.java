package com.amt.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dto.AddOrEditDTO;
import com.amt.model.PhoneNumberType;
import com.amt.util.SessionFactorySingleton;


public class PhoneNumberTypeDAOImpl implements GenericDAO<PhoneNumberType>, Constants{
	private Logger objLogger = LoggerFactory.getLogger(PhoneNumberTypeDAOImpl.class);

	public PhoneNumberTypeDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PhoneNumberType> getAllRecords() throws SQLException {
		String sMethod = "\n\t getAllRecords(): ";
		objLogger.trace(sMethod + "Entered");

		// load a complete persistent objects into memory
		String sHQL = "FROM "; // + csHQL_ModelClassReimbType; //fully qualify class name in HQL
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		List<PhoneNumberType> lstReimbursementType = session.createQuery(sHQL).getResultList();
		objLogger.debug(sMethod + "lstReimbursementType: [" + lstReimbursementType.toString() + "]");
		
		tx.commit();
		session.close();
		
		return lstReimbursementType;
	}

	@Override
	public PhoneNumberType getByRecordId(int iRecordId) throws SQLException {
		String sMethod = "\n\t getByRecordId(): ";
		objLogger.trace(sMethod + "Entered");
			
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String sHQL = "";

		sHQL = "FROM ReimbursementType rt WHERE rt.reimbTypeId = :reimbTypeId"; //this works with using setParameter
		objLogger.debug(sMethod + "sHQL: [" + sHQL + "]" + " param: iRecordId: [" + iRecordId +"]");
		
		try {
			PhoneNumberType ojbReimbursementType = 
					(PhoneNumberType) session.createQuery(sHQL)
					.setParameter("reimbTypeId", iRecordId)
					.getSingleResult();
			objLogger.debug(sMethod + "ojbReimbursementType: [" + ojbReimbursementType.toString() + "]");
			
			
			tx.commit();
			return ojbReimbursementType;
			
		}catch(Exception e) {
			objLogger.error(sMethod + "Exception: cause: [" + e.getCause() + "] class name [" + e.getClass().getName() + "] [" + e.toString() + "]");
			objLogger.error(sMethod + "Exception: message: [" + e.getMessage() + "]");	
			return null;
		}finally {
			session.close();
		}	
	}

	@Override
	public PhoneNumberType getByRecordIdentifer(String sRecordIdentifier) throws SQLException, HibernateException {
		String sMethod = "\n\t getByRecordIdentifer(): ";
		objLogger.trace(sMethod + "Entered");
			
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String sHQL = "";

		sHQL = "FROM PhoneNumberType pnt WHERE pnt.phoneNumberType = :phoneNumberType";
		objLogger.debug(sMethod + "sHQL: [" + sHQL + "]" + " param: sRecordIdentifier: [" + sRecordIdentifier +"]");
		
		try {
			PhoneNumberType objPhoneNumberType = 
					(PhoneNumberType) session.createQuery(sHQL)
					.setParameter("phoneNumberType", sRecordIdentifier)
					.getSingleResult();
			objLogger.debug(sMethod + "objPhoneNumberType: [" + objPhoneNumberType.toString() + "]");			
			
			tx.commit();
			return objPhoneNumberType;
			
		}catch(Exception e) {
			objLogger.error(sMethod + "Error getting Phone Number Type by sRecordIdentifier: [" + sRecordIdentifier + "]");	
			objLogger.error(sMethod + "Exception: cause: [" + e.getCause() + "] class name [" + e.getClass().getName() + "] [" + e.toString() + "]");
			objLogger.error(sMethod + "Exception: message: [" + e.getMessage() + "]");	
			return null;
		}finally {
			session.close();
		}	
	}

	//
	//###
	@Override
	public PhoneNumberType addRecord(AddOrEditDTO objAddOrEditDTO) throws SQLException, HibernateException {
		String sMethod = csCRT + "addRecord(): ";
		objLogger.trace(sMethod + "Entered: objAddOrEditDTO: [" + objAddOrEditDTO.toString() + "]");

		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		String sType = objAddOrEditDTO.getDataElement(csPhoneNumberTypeTblPhoneNumberType);
		String sTypeDesc = objAddOrEditDTO.getDataElement(csPhoneNumberTypeTblPhoneNumberTypeDesc);
		objLogger.debug(sMethod + "Extracted from objAddOrEditDTO: sType: [" + sType + "] sTypeDesc: [" + sTypeDesc + "]");
		
		PhoneNumberType objPhoneNumberType = new PhoneNumberType(sType, sTypeDesc);
		objLogger.debug(sMethod + "Adding objPhoneNumberType: [" + objPhoneNumberType.toString() + "]");
		
		session.persist(objPhoneNumberType);
		
		tx.commit();
		session.close();

		return objPhoneNumberType;
	}

	@Override
	public PhoneNumberType editRecord(AddOrEditDTO objAddOrEditDTO)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteRecord(String sRecordIdentifier) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PhoneNumberType getLogin(String sUsername, String sPassword) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PhoneNumberType> getListByRecordIdentifer(int iListId, String sRecordIdentifier) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
