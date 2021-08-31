package com.amt.service;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dao.GenericDAO;
import com.amt.dao.ReimbursementStatusDAOImpl;
import com.amt.dao.UserTypeDAOImpl;
import com.amt.dao.UserRoleDAOImpl;
import com.amt.dto.OrderStatusDTO;
import com.amt.dto.UserTypeDTO;
import com.amt.dto.UserRoleDTO;
import com.amt.exception.*;
import com.amt.model.OrderStatus;
import com.amt.model.UserType;
import com.amt.model.UserRole;
import com.amt.util.Validate;

public class AdminService implements Constants {
	private Logger objLogger = LoggerFactory.getLogger(AdminService.class);
	private GenericDAO<OrderStatus> objCatalogTypeDAO;
	private GenericDAO<UserType> objUserTypeDAO;
	private GenericDAO<UserRole> objUserRoleDAO;

	public AdminService() {
		this.objCatalogTypeDAO = new ReimbursementStatusDAOImpl();
		this.objUserTypeDAO = new UserTypeDAOImpl();
		this.objUserRoleDAO = new UserRoleDAOImpl();
	}

	// Cannot overload constructor with different signatures of GenericDAO<T> so
	// provide get methods
	public AdminService getMockReimbStatusDAO(GenericDAO<OrderStatus> objMockReimbStatusDAO) {
		this.objCatalogTypeDAO = objMockReimbStatusDAO;
		return this;
	}

	public AdminService getMockReimbTypeDAO(GenericDAO<UserType> objMockUserTypeDAO) {
		this.objUserTypeDAO = objMockUserTypeDAO;
		return this;
	}

	public AdminService getMockUserRoleDAO(GenericDAO<UserRole> objMockUserRoleDAO) {
		this.objUserRoleDAO = objMockUserRoleDAO;
		return this;
	}

	//
	// ###
	public List<OrderStatus> getAllCatalogType() throws DatabaseException {
		String sMethod = "getAllCatalogType(): ";
		objLogger.trace(sMethod + "Entered");

		try {

			List<OrderStatus> lstReimbStatus = objCatalogTypeDAO.getAllRecords();
			objLogger.debug(sMethod + "lstReimbStatus: [" + lstReimbStatus.toString() + "]");
			return lstReimbStatus;

		} catch (SQLException e) {
			objLogger.warn(sMethod + "SQLException while getting all Reimbursement Status: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorGettingAllReimbursementStatus);
		} catch (HibernateException e) {
			objLogger.warn(
					sMethod + "HibernateException while getting all Reimbursement Status: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorGettingAllReimbursementStatus);
		} catch (Exception e) {
			objLogger.warn(sMethod + "Exception while getting all Reimbursement Status: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorGettingAllReimbursementStatus);
		}
	}

	//
	// ###
	public List<UserType> getAllReimbursementType() throws DatabaseException {
		String sMethod = "getAllReimbursementType(): ";
		objLogger.trace(sMethod + "Entered");

		try {

			List<UserType> lstReimbType = objUserTypeDAO.getAllRecords();
			objLogger.debug(sMethod + "lstReimbStatus: [" + lstReimbType.toString() + "]");
			return lstReimbType;

		} catch (SQLException e) {
			objLogger.warn(sMethod + "SQLException while getting all Reimbursement Type: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorGettingReimbursementType);
		} catch (HibernateException e) {
			objLogger.warn(
					sMethod + "HibernateException while getting all Reimbursement Type: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorGettingReimbursementType);
		} catch (Exception e) {
			objLogger.warn(sMethod + "Exception while getting all Reimbursement Type: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorGettingReimbursementType);
		}
	}

	//
	// ###
	public List<UserRole> getAllUserRole() throws DatabaseException {
		String sMethod = "getAllUserRole(): ";
		objLogger.trace(sMethod + "Entered");

		try {

			List<UserRole> lstUserRole = objUserRoleDAO.getAllRecords();
			objLogger.debug(sMethod + "lstUserRole: [" + lstUserRole.toString() + "]");
			return lstUserRole;

		} catch (SQLException e) {
			objLogger.warn(sMethod + "SQLException while getting all User Role: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorGettingUserRole);
		} catch (HibernateException e) {
			objLogger.warn(sMethod + "HibernateException while getting all User Role: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorGettingUserRole);
		} catch (Exception e) {
			objLogger.warn(sMethod + "Exception while getting all User Role: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorGettingUserRole);
		}
	}

	//
	// ###
	public OrderStatus addReimbursementStatus(OrderStatusDTO objReimbStatusDTO)
			throws DatabaseException, BadParameterException {
		String sMethod = "addReimbStatusTableStatus(): ";
		objLogger.trace(sMethod + "Entered");

		String sStatus = objReimbStatusDTO.getReimbStatus();
		String sStatusDesc = objReimbStatusDTO.getReimbStatusDescription();

		if ((sStatus.length() == 0) || (sStatusDesc.length() == 0)) {
			objLogger.debug(sMethod + "Invalid parameters received sStatus: [" + sStatus + "] sStatusDesc: ["
					+ sStatusDesc + "]");
			throw new BadParameterException(csMsgBadParamReimbStatus);
		}

		try {

			OrderStatus objReimbStatus = objCatalogTypeDAO.addRecord(objReimbStatusDTO);
			return objReimbStatus;

		} catch (SQLException e) {
			objLogger.warn(sMethod + "SQLException while adding Reimbursement Status: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingReimbursementStatus);
		} catch (HibernateException e) {
			objLogger.warn(sMethod + "HibernateException while adding Reimbursement Status: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingReimbursementStatus);
		} catch (Exception e) {
			objLogger.warn(sMethod + "Exception while adding Reimbursement Status: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingReimbursementStatus);
		}

	}


	//
	// ###
	public OrderStatus getReimbursementStatusByName(String sStatusName)
			throws DatabaseException, BadParameterException {
		String sMethod = "getReimbursementStatusByName(): ";
		objLogger.trace(sMethod + "Entered");

		if (sStatusName.length() == 0) {
			objLogger.debug(sMethod + "Invalid parameters received sStatus: [" + sStatusName + "]");
			throw new BadParameterException(csMsgBadParamReimbStatus);
		}
		
		if(!Validate.isValidValueInArray(sStatusName, csReimbStatus)) {
			objLogger.debug(sMethod + "Invalid parameters received sStatus: [" + sStatusName + "] not a status define: [" + csReimbStatus.toString() + "]");
			throw new BadParameterException(csMsgBadParamReimbStatus);			
		}

		try {
			objLogger.debug(sMethod + "Getting status by name sStatus: [" + sStatusName + "]");
			OrderStatus objReimbStatus = objCatalogTypeDAO.getByRecordIdentifer(sStatusName);
			return objReimbStatus;

		} catch (SQLException e) {
			objLogger.warn(sMethod + "SQLException while getting Reimbursement Status: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorGettingReimbursementStatus);
		} catch (HibernateException e) {
			objLogger.warn(sMethod + "HibernateException while getting Reimbursement Status: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorGettingReimbursementStatus);
		} catch (Exception e) {
			objLogger.warn(sMethod + "Exception while getting Reimbursement Status: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorGettingReimbursementStatus);
		}

	}


	
	//
	// ###
	public UserType addReimbursementType(UserTypeDTO objReimbTypeDTO)
			throws DatabaseException, BadParameterException {
		String sMethod = "addReimbStatusTableStatus(): ";
		objLogger.trace(sMethod + "Entered");

		String sType = objReimbTypeDTO.getUserType();
		String sTypeDesc = objReimbTypeDTO.getUserTypeDescription();

		if ((sType.length() == 0) || (sTypeDesc.length() == 0)) {
			objLogger.debug(
					sMethod + "Invalid parameters received sStatus: [" + sType + "] sStatusDesc: [" + sTypeDesc + "]");
			throw new BadParameterException(csMsgBadParamReimbType);
		}

		try {

			UserType objReimbType = objUserTypeDAO.addRecord(objReimbTypeDTO);
			return objReimbType;

		} catch (SQLException e) {
			objLogger.warn(sMethod + "SQLException while adding Reimbursement Type: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingReimbursementType);
		} catch (HibernateException e) {
			objLogger.warn(sMethod + "HibernateException while adding Reimbursement Type: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingReimbursementType);
		} catch (Exception e) {
			objLogger.warn(sMethod + "Exception while adding Reimbursement Type: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingReimbursementType);
		}

	}

	//
	// ###
	public UserRole addUserRole(UserRoleDTO objUserRoleDTO) throws DatabaseException, BadParameterException {
		String sMethod = "addUserRole(): ";
		objLogger.trace(sMethod + "Entered");

		String sUserRole = objUserRoleDTO.getUserRole();
		String sUserRoleDesc = objUserRoleDTO.getUserRoleDescription();

		if ((sUserRole.length() == 0) || (sUserRoleDesc.length() == 0)) {
			objLogger.debug(sMethod + "Invalid parameters received sUserRole: [" + sUserRoleDesc + "] sUserRoleDesc: ["
					+ sUserRoleDesc + "]");
			throw new BadParameterException(csMsgBadParamUserRole);
		}

		objLogger.debug(sMethod + "objUserRoleDTO: [" + objUserRoleDTO.toString() + "]");
		try {

			UserRole objUserRole = objUserRoleDAO.addRecord(objUserRoleDTO);
			return objUserRole;

		} catch (SQLException e) {
			objLogger.warn(sMethod + "SQLException while adding User Role: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingUserRole);
		} catch (HibernateException e) {
			objLogger.warn(sMethod + "HibernateException while adding User Role: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingUserRole);
		} catch (Exception e) {
			objLogger.warn(sMethod + "Exception while adding User Role: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingUserRole);
		}

	}

}
