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
import com.amt.model.OrderStatus;
import com.amt.util.SessionFactorySingleton;


public class OrderStatusDAOImpl implements GenericDAO<OrderStatus>, Constants{
	private Logger objLogger = LoggerFactory.getLogger(OrderStatusDAOImpl.class);

	public OrderStatusDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<OrderStatus> getAllRecords() throws SQLException {
		String sMethod = "\n\t getAllRecords(): ";
		objLogger.trace(sMethod + "Entered");

		// load a complete persistent objects into memory
		String sHQL = "FROM "; // + csHQL_ModelClassReimbType; //fully qualify class name in HQL
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		List<OrderStatus> lstReimbursementType = session.createQuery(sHQL).getResultList();
		objLogger.debug(sMethod + "lstReimbursementType: [" + lstReimbursementType.toString() + "]");
		
		tx.commit();
		session.close();
		
		return lstReimbursementType;
	}

	@Override
	public OrderStatus getByRecordId(int iRecordId) throws SQLException {
		String sMethod = "\n\t getByRecordId(): ";
		objLogger.trace(sMethod + "Entered");
			
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String sHQL = "";

		sHQL = "FROM ReimbursementType rt WHERE rt.reimbTypeId = :reimbTypeId"; //this works with using setParameter
		objLogger.debug(sMethod + "sHQL: [" + sHQL + "]" + " param: iRecordId: [" + iRecordId +"]");
		
		try {
			OrderStatus ojbReimbursementType = 
					(OrderStatus) session.createQuery(sHQL)
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
	public OrderStatus getByRecordIdentifer(String sRecordIdentifier) throws SQLException, HibernateException {
		String sMethod = "\n\t getByRecordIdentifer(): ";
		objLogger.trace(sMethod + "Entered");
			
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String sHQL = "";

		sHQL = "FROM ReimbursementType rt WHERE rt.reimbType = :reimbType";
		objLogger.debug(sMethod + "sHQL: [" + sHQL + "]" + " param: sRecordIdentifier: [" + sRecordIdentifier +"]");
		
		try {
			OrderStatus objReimbursementType = 
					(OrderStatus) session.createQuery(sHQL)
					.setParameter("reimbType", sRecordIdentifier)
					.getSingleResult();
			objLogger.debug(sMethod + "objReimbursementType: [" + objReimbursementType.toString() + "]");			
			
			tx.commit();
			return objReimbursementType;
			
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
	public OrderStatus addRecord(AddOrEditDTO objAddOrEditDTO) throws SQLException, HibernateException {
		String sMethod = csCRT + "addRecord(): ";
		objLogger.trace(sMethod + "Entered: objAddOrEditDTO: [" + objAddOrEditDTO.toString() + "]");

		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		String sType = objAddOrEditDTO.getDataElement(csOrderStatusTblOrderStatus);
		String sTypeDesc = objAddOrEditDTO.getDataElement(csOrderStatusTblOrderStatusDesc);
		objLogger.debug(sMethod + "Extracted from objAddOrEditDTO: sType: [" + sType + "] sTypeDesc: [" + sTypeDesc + "]");
		
		OrderStatus objOrderStatus = new OrderStatus(sType, sTypeDesc);
		objLogger.debug(sMethod + "Adding objOrderStatus: [" + objOrderStatus.toString() + "]");
		
		session.persist(objOrderStatus);
		
		tx.commit();
		session.close();

		return objOrderStatus;
	}

	@Override
	public OrderStatus editRecord(AddOrEditDTO objAddOrEditDTO)
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
	public OrderStatus getLogin(String sUsername, String sPassword) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderStatus> getListByRecordIdentifer(int iListId, String sRecordIdentifier) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
