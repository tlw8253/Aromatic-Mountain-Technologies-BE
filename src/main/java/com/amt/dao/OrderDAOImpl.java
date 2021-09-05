package com.amt.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dto.OrderDTO;
import com.amt.model.Order;
import com.amt.model.OrderStatus;
import com.amt.model.OrderedItem;
import com.amt.model.User;
import com.amt.util.SessionFactorySingleton;

public class OrderDAOImpl implements OrderDAO, Constants {
	private Logger objLogger = LoggerFactory.getLogger(OrderDAOImpl.class);
	
	

	public OrderDAOImpl() {
		super();
	}


	
	//
	// ###
	@Override // 20210819 1223 working method added user using Admin drive through service
				// layer
	public Order addNewOrder(OrderDTO objOrderDTO) throws SQLException, HibernateException {
		String sMethod = csCRT + "addNewOrder(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		objLogger.debug(sMethod + "objOrderDTO: [" + objOrderDTO.toString() + "]");

		
		// by this time the service layer would have validated the parameters
		
		Double dAmount = objOrderDTO.getOrderAmount();
		Timestamp objOrderSubmitted = objOrderDTO.getOrderSubmitted();
		Timestamp objOrderSent = objOrderDTO.getOrderSent();
		
		// get Status object
		String sStatus = objOrderDTO.getOrderStatus();
		OrderStatusDAOImpl objOrderStatusDAOImpl = new OrderStatusDAOImpl();
		OrderStatus objOrderStatus = objOrderStatusDAOImpl.getByRecordIdentifer(sStatus);

		// get User object
		String sUsername = objOrderDTO.getUsername();
		UserDAOImpl objUserDAOImpl = new UserDAOImpl();
		User objUser = objUserDAOImpl.getByUsername(sUsername);
		
		Order objOrder = new Order(dAmount, objOrderSubmitted, objOrderSent, objOrderStatus, objUser);

		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		try {
			
			objLogger.debug(sMethod + "Adding final objOrder: [" + objOrder.toString() + "] to the database.");

			session.persist(objOrder);		
			tx.commit();			
			
			//service layer handles adding the ordered items.		
			
			objLogger.debug(sMethod + "returning database objOrder: [" + objOrder.toString() + "].");
			return objOrder;

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
