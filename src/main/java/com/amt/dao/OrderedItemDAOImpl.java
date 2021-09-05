package com.amt.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dto.AddOrderedItemDTO;
import com.amt.dto.OrderDTO;
import com.amt.dto.OrderedItemDTO;
import com.amt.model.CatalogItem;
import com.amt.model.Order;
import com.amt.model.OrderStatus;
import com.amt.model.OrderedItem;
import com.amt.model.User;
import com.amt.util.SessionFactorySingleton;

public class OrderedItemDAOImpl implements OrderedItemDAO, Constants {
	private Logger objLogger = LoggerFactory.getLogger(OrderedItemDAOImpl.class);

	public OrderedItemDAOImpl() {
		super();
	}

	//
	// ###
	@Override // 20210819 1223 working method added user using Admin drive through service
				// layer
	public OrderedItem addNewOrderItem(OrderedItemDTO objOrderItemDTO) throws SQLException, HibernateException {
		String sMethod = csCRT + "addNewOrderItem(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		objLogger.debug(sMethod + "objOrderItemDTO: [" + objOrderItemDTO.toString() + "]");

		// by this time the service layer would have validated the parameters
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String catalogItemName = objOrderItemDTO.getCatalogItemName();
		CatalogItemDAOImpl objCatalogItemDAOImpl = new CatalogItemDAOImpl();		
		CatalogItem objCatalogItem = objCatalogItemDAOImpl.getCatalogItemByName(catalogItemName);

		Order objOrder = objOrderItemDTO.getOrder();
		Double orderItemPrice = objOrderItemDTO.getOrderItemPrice();
		int orderItemQty = objOrderItemDTO.getOrderItemQty();

		OrderedItem objOrderedItem = new OrderedItem(orderItemPrice, orderItemQty, objOrder, objCatalogItem);

		
		try {
			objLogger.debug(sMethod + "Adding this objOrderedItem: [" + objOrderedItem.toString() + "] to the database.");

			session.persist(objOrderedItem);

			objLogger.debug(sMethod + "returning database objOrderedItem: [" + objOrderedItem.toString() + "].");
			tx.commit();
			return objOrderedItem;

		} catch (Exception e) {
			objLogger.error(sMethod + "Exception: cause: [" + e.getCause() + "] class name [" + e.getClass().getName() + "] [" + e.toString() + "]");
			objLogger.error(sMethod + "Exception: toString: [" + e.toString() + " message: [" + e.getMessage() + "]");
			return null;
		} finally {
			session.close();
		}

	}

}// END Class
