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
import com.amt.dto.AddCatalogItemDTO;
import com.amt.dto.AddCustomerDTO;
import com.amt.dto.AddOrEditDTO;
import com.amt.dto.AddUserDTO;
import com.amt.dto.AddressDTO;
import com.amt.dto.CatalogItemDTO;
import com.amt.dto.PhoneNumberDTO;
import com.amt.model.User;
import com.amt.model.UserType;
import com.amt.model.Address;
import com.amt.model.AddressType;
import com.amt.model.CatalogItem;
import com.amt.model.CatalogItemType;
import com.amt.model.EmployeeRole;
import com.amt.model.PhoneNumber;
import com.amt.model.PhoneNumberType;
import com.amt.util.SessionFactorySingleton;
import com.amt.util.PasswordUtil;

public class CatalogItemDAOImpl implements CatalogItemDAO, Constants {
	private Logger objLogger = LoggerFactory.getLogger(CatalogItemDAOImpl.class);

	public CatalogItemDAOImpl() {
		super();
	}

/*
		private int catalogItemId;
	private String catalogItem;
	private String catalogItemDescription;
	private double catalogItemPrice;
	private int catalogItemInStockQty;
	private CatalogItemType catalogItemType;
*/
	//
	// ###
	@Override // 20210819 1223 working method added user using Admin drive through service
				// layer
	public CatalogItem addNewCatalogItem(CatalogItemDTO objCatalogItemDTO) throws SQLException, HibernateException {
		String sMethod = csCRT + "addNewCatalogItem(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		objLogger.debug(sMethod + "objAddCatalogItemDTO: [" + objCatalogItemDTO.toString() + "]");

		// by this time the service layer would have validated the parameters
		String sCatalogItem = objCatalogItemDTO.getCatalogItem();
		String sCatalogItemDescription = objCatalogItemDTO.getCatalogItemDescription();
		double dCatalogItemPrice = objCatalogItemDTO.getCatalogItemPrice();
		int iCatalogItemInStockQty = objCatalogItemDTO.getCatalogItemInStockQty();
		String sCatalogItemType = objCatalogItemDTO.getCatalogItemType();

		
		CatalogItem objCatalogItem = new CatalogItem(sCatalogItem, sCatalogItemDescription, dCatalogItemPrice, iCatalogItemInStockQty);
		objLogger.debug(sMethod + "objCatalogItem: [" + objCatalogItem.toString() + "]");
		
		// get Address Type object
		CatalogItemTypeDAOImpl objCatalogItemTypeDAOImpl = new CatalogItemTypeDAOImpl();
		CatalogItemType objCatalogItemType = objCatalogItemTypeDAOImpl.getByRecordIdentifer(sCatalogItemType);
		objLogger.debug(sMethod + "objCatalogItemType: [" + objCatalogItemType.toString() + "]");
		
		objCatalogItem.setCatalogItemType(objCatalogItemType);		
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		try {
			
			objLogger.debug(sMethod + "Adding final objCatalogItem: [" + objCatalogItem.toString() + "] to the database.");

			session.persist(objCatalogItem);
			tx.commit();
			return objCatalogItem;

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
