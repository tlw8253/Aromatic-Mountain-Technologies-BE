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
import com.amt.model.CatalogItemType;
import com.amt.util.SessionFactorySingleton;


public class CatalogItemTypeDAOImpl implements GenericDAO<CatalogItemType>, Constants{
	private Logger objLogger = LoggerFactory.getLogger(CatalogItemTypeDAOImpl.class);

	public CatalogItemTypeDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CatalogItemType> getAllRecords() throws SQLException {
		String sMethod = "\n\t getAllRecords(): ";
		objLogger.trace(sMethod + "Entered");

		// load a complete persistent objects into memory
		String sHQL = "FROM "; // + csHQL_ModelClassReimbType; //fully qualify class name in HQL
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		List<CatalogItemType> lstReimbursementType = session.createQuery(sHQL).getResultList();
		objLogger.debug(sMethod + "lstReimbursementType: [" + lstReimbursementType.toString() + "]");
		
		tx.commit();
		session.close();
		
		return lstReimbursementType;
	}

	@Override
	public CatalogItemType getByRecordId(int iRecordId) throws SQLException {
		String sMethod = "\n\t getByRecordId(): ";
		objLogger.trace(sMethod + "Entered");
			
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String sHQL = "";

		sHQL = "FROM ReimbursementType rt WHERE rt.reimbTypeId = :reimbTypeId"; //this works with using setParameter
		objLogger.debug(sMethod + "sHQL: [" + sHQL + "]" + " param: iRecordId: [" + iRecordId +"]");
		
		try {
			CatalogItemType ojbReimbursementType = 
					(CatalogItemType) session.createQuery(sHQL)
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
	public CatalogItemType getByRecordIdentifer(String sRecordIdentifier) throws SQLException, HibernateException {
		String sMethod = csCRT + "getByRecordIdentifer(): ";
		objLogger.trace(csCR + sMethod + "Entered");
			
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String sHQL = "";

		sHQL = "FROM CatalogItemType cit WHERE cit.catalogItemType = :catalogItemType";
		objLogger.debug(sMethod + "sHQL: [" + sHQL + "]" + " param: sRecordIdentifier: [" + sRecordIdentifier +"]");
		
		try {
			CatalogItemType objCatalogItemType = 
					(CatalogItemType) session.createQuery(sHQL)
					.setParameter("catalogItemType", sRecordIdentifier)
					.getSingleResult();
			objLogger.debug(sMethod + "objCatalogItemType: [" + objCatalogItemType.toString() + "]");			
			
			tx.commit();
			return objCatalogItemType;
			
		}catch(Exception e) {
			objLogger.error(sMethod + "Error getting Reimbursement Type by sRecordIdentifier: [" + sRecordIdentifier + "]");	
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
	public CatalogItemType addRecord(AddOrEditDTO objAddOrEditDTO) throws SQLException, HibernateException {
		String sMethod = csCRT + "addRecord(): ";
		objLogger.trace(sMethod + "Entered: objAddOrEditDTO: [" + objAddOrEditDTO.toString() + "]");

		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		String sType = objAddOrEditDTO.getDataElement(csCatalogItemTypeTblCatalogItemType);
		String sTypeDesc = objAddOrEditDTO.getDataElement(csCatalogItemTypeTblCatalogItemTypeDesc);
		objLogger.debug(sMethod + "Extracted from objAddOrEditDTO: sType: [" + sType + "] sTypeDesc: [" + sTypeDesc + "]");
		
		CatalogItemType objCatalogItemType = new CatalogItemType(sType, sTypeDesc);
		objLogger.debug(sMethod + "Adding objCatalogItemType: [" + objCatalogItemType.toString() + "]");
		
		session.persist(objCatalogItemType);
		
		tx.commit();
		session.close();

		return objCatalogItemType;
	}

	@Override
	public CatalogItemType editRecord(AddOrEditDTO objAddOrEditDTO)
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
	public CatalogItemType getLogin(String sUsername, String sPassword) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CatalogItemType> getListByRecordIdentifer(int iListId, String sRecordIdentifier) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
