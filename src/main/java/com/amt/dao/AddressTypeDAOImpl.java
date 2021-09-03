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
import com.amt.model.AddressType;
import com.amt.util.SessionFactorySingleton;

public class AddressTypeDAOImpl implements GenericDAO<AddressType>, Constants {
	private Logger objLogger = LoggerFactory.getLogger(AddressTypeDAOImpl.class);

	public AddressTypeDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<AddressType> getAllRecords() throws SQLException {
		String sMethod = "\n\t getAllRecords(): ";
		objLogger.trace(sMethod + "Entered");

		// load a complete persistent objects into memory
		String sHQL = "FROM "; // + csHQL_ModelClassReimbType; //fully qualify class name in HQL

		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		List<AddressType> lstReimbursementType = session.createQuery(sHQL).getResultList();
		objLogger.debug(sMethod + "lstReimbursementType: [" + lstReimbursementType.toString() + "]");

		tx.commit();
		session.close();

		return lstReimbursementType;
	}

	@Override
	public AddressType getByRecordId(int iRecordId) throws SQLException {
		String sMethod = "\n\t getByRecordId(): ";
		objLogger.trace(sMethod + "Entered");

		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		String sHQL = "";

		sHQL = "FROM ReimbursementType rt WHERE rt.reimbTypeId = :reimbTypeId"; // this works with using setParameter
		objLogger.debug(sMethod + "sHQL: [" + sHQL + "]" + " param: iRecordId: [" + iRecordId + "]");

		try {
			AddressType ojbReimbursementType = (AddressType) session.createQuery(sHQL)
					.setParameter("reimbTypeId", iRecordId).getSingleResult();
			objLogger.debug(sMethod + "ojbReimbursementType: [" + ojbReimbursementType.toString() + "]");

			tx.commit();
			return ojbReimbursementType;

		} catch (Exception e) {
			objLogger.error(sMethod + "Exception: cause: [" + e.getCause() + "] class name [" + e.getClass().getName()
					+ "] [" + e.toString() + "]");
			objLogger.error(sMethod + "Exception: message: [" + e.getMessage() + "]");
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public AddressType getByRecordIdentifer(String sRecordIdentifier) throws SQLException {
		String sMethod = "\n\t getByRecordIdentifer(): ";
		objLogger.trace(sMethod + "Entered");

		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		String sHQL = "";

		sHQL = "FROM AddressType at WHERE at.addressType = :addressType";
		objLogger.debug(sMethod + "sHQL: [" + sHQL + "]" + " param: sRecordIdentifier: [" + sRecordIdentifier + "]");

		try {
			AddressType objAddressType = (AddressType) session.createQuery(sHQL)
						.setParameter("addressType", sRecordIdentifier).getSingleResult();
			objLogger.debug(sMethod + "objAddressType: [" + objAddressType.toString() + "]");

			tx.commit();
			return objAddressType;

		} catch (Exception e) {
			objLogger.error(
					sMethod + "Error getting Address Type by sRecordIdentifier: [" + sRecordIdentifier + "]");
			objLogger.error(sMethod + "Exception: cause: [" + e.getCause() + "] class name [" + e.getClass().getName()
					+ "] [" + e.toString() + "]");
			objLogger.error(sMethod + "Exception: message: [" + e.getMessage() + "]");
			return null;
		} finally {
			session.close();
		}
	}

	//
	// ###
	@Override
	public AddressType addRecord(AddOrEditDTO objAddOrEditDTO) throws SQLException {
		String sMethod = csCRT + "addRecord(): ";
		objLogger.trace(sMethod + "Entered: objAddOrEditDTO: [" + objAddOrEditDTO.toString() + "]");

		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		String sType = objAddOrEditDTO.getDataElement(csAddressTypeTblAddressType);
		String sTypeDesc = objAddOrEditDTO.getDataElement(csAddressTypeTblAddressTypeDesc);
		objLogger.debug(
				sMethod + "Extracted from objAddOrEditDTO: sType: [" + sType + "] sTypeDesc: [" + sTypeDesc + "]");

		AddressType objAddressType = new AddressType(sType, sTypeDesc);
		objLogger.debug(sMethod + "Adding objAddressType: [" + objAddressType.toString() + "]");

		try {
			session.persist(objAddressType);
			tx.commit();
			return objAddressType;

		} catch (Exception e) {
			objLogger.error(
					sMethod + csMsgDB_ErrorAddingAddressType + "sType: [" + sType + "] sTypeDesc: [" + sTypeDesc + "]");
			objLogger.error(sMethod + "Exception: cause: [" + e.getCause() + "] class name [" + e.getClass().getName()
					+ "] [" + e.toString() + "]");
			objLogger.error(sMethod + "Exception: message: [" + e.getMessage() + "]");
			throw new SQLException(csMsgDB_ErrorAddingAddressType);
		} finally {
			session.close();
		}
	}

	@Override
	public AddressType editRecord(AddOrEditDTO objAddOrEditDTO) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteRecord(String sRecordIdentifier) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AddressType getLogin(String sUsername, String sPassword) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AddressType> getListByRecordIdentifer(int iListId, String sRecordIdentifier) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
