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

		
		return null;
	}

	@Override
	public OrderStatus getByRecordId(int iRecordId) throws SQLException {
		String sMethod = "\n\t getByRecordId(): ";
		objLogger.trace(sMethod + "Entered");

		return null;
	}

	@Override
	public OrderStatus getByRecordIdentifer(String sRecordIdentifier) throws SQLException, HibernateException {
		String sMethod = csCRT + "getByRecordIdentifer(): ";
		objLogger.trace(csCR + sMethod + "Entered");
			
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String sHQL = "";

		sHQL = "FROM OrderStatus os WHERE os.orderStatus = :orderStatus";
		objLogger.debug(sMethod + "sHQL: [" + sHQL + "]" + " param: sRecordIdentifier: [" + sRecordIdentifier +"]");
		
		try {
			OrderStatus objOrderStatus = 
					(OrderStatus) session.createQuery(sHQL)
					.setParameter("orderStatus", sRecordIdentifier)
					.getSingleResult();
			objLogger.debug(sMethod + "objOrderStatus: [" + objOrderStatus.toString() + "]");			
			
			tx.commit();
			return objOrderStatus;
			
		}catch(Exception e) {
			objLogger.error(sMethod + "Error getting Order Status by sRecordIdentifier: [" + sRecordIdentifier + "]");	
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
