package com.amt.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dto.AddOrEditDTO;
import com.amt.model.EmployeeRole;
import com.amt.util.SessionFactorySingleton;


public class EmployeeRoleDAOImpl implements GenericDAO<EmployeeRole>, Constants{
	private Logger objLogger = LoggerFactory.getLogger(EmployeeRoleDAOImpl.class);

	public EmployeeRoleDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<EmployeeRole> getAllRecords() throws SQLException {
		String sMethod = csCRT + "getAllRecords(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		// load a complete persistent objects into memory
		String sHQL = "FROM " + csHQL_ModelClassEmployeeRole; //fully qualify class name in HQL
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		List<EmployeeRole> lstUserRole = session.createQuery(sHQL).getResultList();
		objLogger.debug(sMethod + "lstReimbursementType: [" + lstUserRole.toString() + "]");
		
		tx.commit();
		session.close();
		
		return lstUserRole;
	}

	@Override
	public EmployeeRole getByRecordId(int sRecordId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeRole getByRecordIdentifer(String sRecordIdentifier) throws SQLException {
		String sMethod = csCRT + "getByRecordIdentifer(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		// load a complete persistent objects into memory		
		String sHQL = "";		
		sHQL = "FROM EmployeeRole er WHERE er.employeeRole = :employeeRole"; //this works with using setParameter
		objLogger.debug(sMethod + "sHQL: [" + sHQL + "]" + " param: sRecordIdentifier: [" + sRecordIdentifier +"]");
		
		try {
			EmployeeRole objEmployeeRole = (EmployeeRole) session.createQuery(sHQL)
											.setParameter("employeeRole", sRecordIdentifier)
											.getSingleResult();
			objLogger.debug(sMethod + "objEmployeeRole: [" + objEmployeeRole.toString() + "]");
			
			tx.commit();
			return objEmployeeRole;
			
		}catch(Exception e) {
			objLogger.error(sMethod + "Error getting Employee Role by sRecordIdentifier: [" + sRecordIdentifier + "]");
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
	public EmployeeRole addRecord(AddOrEditDTO objAddOrEditDTO) throws SQLException, HibernateException {
		String sMethod = csCRT + "addRecord(): ";
		objLogger.trace(csCR + sMethod + "Entered");

		objLogger.debug(sMethod + "objAddOrEditDTO: [" + objAddOrEditDTO.toString() + "]");
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		String sRole = objAddOrEditDTO.getDataElement(csEmployeeRolesTblEmployeeRole);
		String sRoleDesc = objAddOrEditDTO.getDataElement(csEmployeeRolesTblEmployeeRoleDesc);
		
		EmployeeRole objEmployeeRole = new EmployeeRole(sRole, sRoleDesc);
		
		objLogger.debug(sMethod + "objEmployeeRole: [" + objEmployeeRole.toString() + "]");
		
		session.persist(objEmployeeRole);
		
		tx.commit();
		session.close();

		return objEmployeeRole;
	}

	@Override
	public EmployeeRole editRecord(AddOrEditDTO objGenericEditDTO) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteRecord(String sRecordIdentifier) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EmployeeRole getLogin(String sUsername, String sPassword) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeRole> getListByRecordIdentifer(int iListId, String sRecordIdentifier) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
