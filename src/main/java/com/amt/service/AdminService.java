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
import com.amt.dao.EmployeeRoleDAOImpl;
import com.amt.dto.OrderStatusDTO;
import com.amt.dto.UserTypeDTO;
import com.amt.dto.AddressTypeDTO;
import com.amt.dto.EmployeeRoleDTO;
import com.amt.exception.*;
import com.amt.model.OrderStatus;
import com.amt.model.UserType;
import com.amt.model.AddressType;
import com.amt.model.EmployeeRole;
import com.amt.util.Validate;

public class AdminService implements Constants {
	private Logger objLogger = LoggerFactory.getLogger(AdminService.class);
	private GenericDAO<OrderStatus> objCatalogTypeDAO;
	private GenericDAO<UserType> objUserTypeDAO;
	private GenericDAO<EmployeeRole> objEmployeeRoleDAO;
	private GenericDAO<AddressType> objAddressTypeDAO;

	public AdminService() {
		this.objCatalogTypeDAO = new ReimbursementStatusDAOImpl();
		this.objUserTypeDAO = new UserTypeDAOImpl();
		this.objEmployeeRoleDAO = new EmployeeRoleDAOImpl();
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

	public AdminService getMockUserRoleDAO(GenericDAO<EmployeeRole> objMockUserRoleDAO) {
		this.objEmployeeRoleDAO = objMockUserRoleDAO;
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
	public List<EmployeeRole> getAllEmployeeRole() throws DatabaseException {
		String sMethod = "getAllUserRole(): ";
		objLogger.trace(sMethod + "Entered");

		try {

			List<EmployeeRole> lstUserRole = objEmployeeRoleDAO.getAllRecords();
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



	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// ### Completed 20210831
	public UserType addUserType(UserTypeDTO objUserTypeDTO)
			throws DatabaseException, BadParameterException {
		String sMethod = csCRT + "addUserType(): ";
		objLogger.trace(sMethod + "Entered: objUserTypeDTO: [" + objUserTypeDTO + "]");

		String sType = objUserTypeDTO.getUserType();
		String sTypeDesc = objUserTypeDTO.getUserTypeDescription();
		
		boolean bValidUserType = Validate.isValidValueInArray(sType,csarUserType);

		if (!bValidUserType || (sType.length() == 0) || (sTypeDesc.length() == 0)) {
			objLogger.debug(
					sMethod + "Invalid parameters received sType: [" + sType + "] sTypeDesc: [" + sTypeDesc + "]");
			throw new BadParameterException(csMsgBadParamUserType);
		}

		try {

			UserType objUserType = objUserTypeDAO.addRecord(objUserTypeDTO);
			return objUserType;

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
	// ### Completed 20210831
	public EmployeeRole addEmployeeRole(EmployeeRoleDTO objEmployeeRoleDTO) throws DatabaseException, BadParameterException {
		String sMethod = csCRT + "addUserRole(): ";
		objLogger.trace(sMethod + "Entered");

		String sRole = objEmployeeRoleDTO.getRole();
		String sRoleDesc = objEmployeeRoleDTO.getRoleDescription();
		
		boolean bValidRole = Validate.isValidValueInArray(sRole, csarEmployeeRoles);

		if (!bValidRole || (sRole.length() == 0) || (sRoleDesc.length() == 0)) {
			objLogger.debug(sMethod + "Invalid parameters received sRole: [" + sRole + "] sRoleDesc: ["
					+ sRoleDesc + "]");
			throw new BadParameterException(csMsgBadParamEmployeeRole);
		}

		objLogger.debug(sMethod + "objEmployeeRoleDTO: [" + objEmployeeRoleDTO.toString() + "]");
		try {

			EmployeeRole objEmployeeRole = objEmployeeRoleDAO.addRecord(objEmployeeRoleDTO);
			return objEmployeeRole;

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

	
	//
	// ### Completed 
	public AddressType addAddressType(AddressTypeDTO objAddressTypeDTO) throws DatabaseException, BadParameterException {
		String sMethod = csCRT + "addAddressType(): ";
		objLogger.trace(sMethod + "Entered: objAddressTypeDTO: [" + objAddressTypeDTO.toString() + "]");

		String sType = objAddressTypeDTO.getAddressType();
		String sTypeDesc = objAddressTypeDTO.getAddressTypeDescription();
		
		boolean bValidUserType = Validate.isValidValueInArray(sType,carrAddressType);

		if (!bValidUserType || (sType.length() == 0) || (sTypeDesc.length() == 0)) {
			objLogger.debug(
					sMethod + "Invalid parameters received sType: [" + sType + "] sTypeDesc: [" + sTypeDesc + "]");
			throw new BadParameterException(csMsgBadParamAddressType);
		}

		try {

			AddressType objAddressType = objAddressTypeDAO.addRecord(objAddressTypeDTO);
			return objAddressType;

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


	
	
}//End Class
