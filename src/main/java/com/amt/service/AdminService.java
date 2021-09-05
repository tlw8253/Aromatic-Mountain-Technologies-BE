package com.amt.service;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dao.GenericDAO;
import com.amt.dao.OrderStatusDAOImpl;
import com.amt.dao.PhoneNumberTypeDAOImpl;
import com.amt.dao.UserTypeDAOImpl;
import com.amt.dao.AddressTypeDAOImpl;
import com.amt.dao.CatalogItemTypeDAOImpl;
import com.amt.dao.EmployeeRoleDAOImpl;
import com.amt.dto.OrderStatusDTO;
import com.amt.dto.PhoneNumberTypeDTO;
import com.amt.dto.UserTypeDTO;
import com.amt.dto.AddressTypeDTO;
import com.amt.dto.CatalogItemTypeDTO;
import com.amt.dto.EmployeeRoleDTO;
import com.amt.exception.*;
import com.amt.model.OrderStatus;
import com.amt.model.PhoneNumberType;
import com.amt.model.UserType;
import com.amt.model.AddressType;
import com.amt.model.CatalogItemType;
import com.amt.model.EmployeeRole;
import com.amt.util.Validate;

public class AdminService implements Constants {
	private Logger objLogger = LoggerFactory.getLogger(AdminService.class);

	private GenericDAO<AddressType> objAddressTypeDAO;
	private GenericDAO<CatalogItemType> objCatalogItemTypeDAO;
	private GenericDAO<EmployeeRole> objEmployeeRoleDAO;
	private GenericDAO<OrderStatus> objOrderStatusDAO;
	private GenericDAO<PhoneNumberType> objPhoneNumberTypeDAO;
	private GenericDAO<UserType> objUserTypeDAO;

	public AdminService() {		
		this.objUserTypeDAO = new UserTypeDAOImpl();
		this.objEmployeeRoleDAO = new EmployeeRoleDAOImpl();
		this.objAddressTypeDAO = new AddressTypeDAOImpl();
		this.objCatalogItemTypeDAO = new CatalogItemTypeDAOImpl();
		this.objOrderStatusDAO = new OrderStatusDAOImpl();
		this.objPhoneNumberTypeDAO = new PhoneNumberTypeDAOImpl();
	}

	// Cannot overload constructor with different signatures of GenericDAO<T> so
	// provide get methods
	public AdminService getMockAddressTypeAdminService(GenericDAO<AddressType> objMockAddressTypeDAO) {
		this.objAddressTypeDAO = objMockAddressTypeDAO;
		return this;
	}
	public AdminService getMockCatalogItemTypeAdminService(GenericDAO<CatalogItemType> objMockCatalogItemTypeDAO) {
		this.objCatalogItemTypeDAO = objMockCatalogItemTypeDAO;
		return this;
	}
	public AdminService getMockEmployeeRoleAdminService(GenericDAO<EmployeeRole> objMockEmployeeRoleDAO) {
		this.objEmployeeRoleDAO = objMockEmployeeRoleDAO;
		return this;
	}
	public AdminService getMockOrderStatusAdminService(GenericDAO<OrderStatus> objMockOrderStatusDAO) {
		this.objOrderStatusDAO = objMockOrderStatusDAO;
		return this;
	}
	public AdminService getMockPhoneNumberTypeAdminService(GenericDAO<PhoneNumberType> objMockPhoneNumberTypeDAO) {
		this.objPhoneNumberTypeDAO = objMockPhoneNumberTypeDAO;
		return this;
	}
	public AdminService getMockUserTypeAdminService(GenericDAO<UserType> objMockUserTypeTypeDAO) {
		this.objUserTypeDAO = objMockUserTypeTypeDAO;
		return this;
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
					sMethod + csMsgBadParamUserType + " sType: [" + sType + "] sTypeDesc: [" + sTypeDesc + "]");
			throw new BadParameterException(csMsgBadParamUserType);
		}

		try {

			objLogger.debug(sMethod + "calling addRecord to add: objUserTypeDTO: [" + objUserTypeDTO.toString() + "]");
			UserType objUserType = objUserTypeDAO.addRecord(objUserTypeDTO);
			return objUserType;

		} catch (SQLException e) {
			objLogger.warn(sMethod + "SQLException: " + csMsgDB_ErrorAddingUserType + " [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingUserType);
		} catch (HibernateException e) {
			objLogger.warn(sMethod + "HibernateException: " + csMsgDB_ErrorAddingUserType + " [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingUserType);
		} catch (Exception e) {
			objLogger.warn(sMethod + "Exception: " + csMsgDB_ErrorAddingUserType + " [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingUserType);
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
			objLogger.debug(sMethod + "calling addRecord to add: objEmployeeRoleDTO: [" + objEmployeeRoleDTO.toString() + "]");
			EmployeeRole objEmployeeRole = objEmployeeRoleDAO.addRecord(objEmployeeRoleDTO);
			return objEmployeeRole;

		} catch (SQLException e) {
			objLogger.warn(sMethod + "SQLException: " + csMsgDB_ErrorAddingEmployeeRole + ": [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingEmployeeRole);
		} catch (HibernateException e) {
			objLogger.warn(sMethod + "HibernateException: " + csMsgDB_ErrorAddingEmployeeRole + ": [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingEmployeeRole);
		} catch (Exception e) {
			objLogger.warn(sMethod + "Exception: " + csMsgDB_ErrorAddingEmployeeRole + ": [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingEmployeeRole);
		}

	}

	
	//
	// ### Completed 20210901
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

			objLogger.debug(sMethod + "calling addRecord to add: objAddressTypeDTO: [" + objAddressTypeDTO.toString() + "]");
			AddressType objAddressType = objAddressTypeDAO.addRecord(objAddressTypeDTO);
			return objAddressType;

		} catch (SQLException e) {
			objLogger.warn(sMethod + "SQLException while adding Address Type: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingAddressType);
		} catch (HibernateException e) {
			objLogger.warn(sMethod + "HibernateException while adding Address Type: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingAddressType);
		} catch (Exception e) {
			objLogger.warn(sMethod + "Exception while adding Address Type: [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingAddressType);
		}

	}



	//
	// ### Completed 20210901
	public CatalogItemType addCatalogItemType(CatalogItemTypeDTO objTypeDTO) throws DatabaseException, BadParameterException {
		String sMethod = csCRT + "addCatalogItemType(): ";
		objLogger.trace(sMethod + "Entered: objTypeDTO: [" + objTypeDTO.toString() + "]");

		String sType = objTypeDTO.getCatalogItemType();
		String sTypeDesc = objTypeDTO.getCatalogItemTypeDescription();
		
		boolean bValidUserType = Validate.isValidValueInArray(sType,csarrCatalogItemType);

		if (!bValidUserType || (sType.length() == 0) || (sTypeDesc.length() == 0)) {
			objLogger.debug(
					sMethod + "Invalid parameters received sType: [" + sType + "] sTypeDesc: [" + sTypeDesc + "]");
			throw new BadParameterException(csMsgBadParamCatalogItemType);
		}

		try {

			objLogger.debug(sMethod + "calling addRecord to add: objTypeDTO: [" + objTypeDTO.toString() + "]");
			CatalogItemType objCatalogItemType = objCatalogItemTypeDAO.addRecord(objTypeDTO);
			return objCatalogItemType;

		} catch (SQLException e) {
			objLogger.warn(sMethod + "SQLException: " + csMsgDB_ErrorAddingCatalogItemType + " [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingCatalogItemType);
		} catch (HibernateException e) {
			objLogger.warn(sMethod + "HibernateException: " + csMsgDB_ErrorAddingCatalogItemType + " [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingCatalogItemType);
		} catch (Exception e) {
			objLogger.warn(sMethod + "Exception: " + csMsgDB_ErrorAddingCatalogItemType + " [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingCatalogItemType);
		}

	}


	//
	// ### Completed 20210901
	public OrderStatus addOrderStatus(OrderStatusDTO objStatusDTO) throws DatabaseException, BadParameterException {
		String sMethod = csCRT + "addOrderStatus(): ";
		objLogger.trace(sMethod + "Entered: objStatusDTO: [" + objStatusDTO.toString() + "]");

		String sStatus = objStatusDTO.getOrderStatus();
		String sStatusDesc = objStatusDTO.getOrderStatusDescription();
		
		boolean bValidStatus = Validate.isValidValueInArray(sStatus,csarrOrderStatus);

		if (!bValidStatus || (sStatus.length() == 0) || (sStatusDesc.length() == 0)) {
			objLogger.debug(
					sMethod + "Invalid parameters received sStatus: [" + sStatus + "] sStatusDesc: [" + sStatusDesc + "]");
			throw new BadParameterException(csMsgBadParamOrderStatus);
		}

		try {

			objLogger.debug(sMethod + "calling addRecord to add: objStatusDTO: [" + objStatusDTO.toString() + "]");
			OrderStatus objOrderStatus = objOrderStatusDAO.addRecord(objStatusDTO);
			return objOrderStatus;

		} catch (SQLException e) {
			objLogger.warn(sMethod + "SQLException: " + csMsgDB_ErrorAddingOrderStatus + " [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingOrderStatus);
		} catch (HibernateException e) {
			objLogger.warn(sMethod + "HibernateException: " + csMsgDB_ErrorAddingOrderStatus + " [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingOrderStatus);
		} catch (Exception e) {
			objLogger.warn(sMethod + "Exception: " + csMsgDB_ErrorAddingOrderStatus + " [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingOrderStatus);
		}

	}

	//
	// ### Completed 20210901
	public PhoneNumberType addPhoneNumberType(PhoneNumberTypeDTO objTypeDTO) throws DatabaseException, BadParameterException {
		String sMethod = csCRT + "addPhoneNumberType(): ";
		objLogger.trace(sMethod + "Entered: objStatusDTO: [" + objTypeDTO.toString() + "]");

		String sType = objTypeDTO.getPhoneNumberType();
		String sTypeDesc = objTypeDTO.getPhoneNumberTypeDescription();
		
		boolean bValidStatus = Validate.isValidValueInArray(sType, csarrPhoneNumberType);

		if (!bValidStatus || (sType.length() == 0) || (sTypeDesc.length() == 0)) {
			objLogger.debug(
					sMethod + csMsgBadParamPhoneNumberType + " sType: [" + sType + "] sTypeDesc: [" + sTypeDesc + "]");
			throw new BadParameterException(csMsgBadParamPhoneNumberType);
		}

		try {

			objLogger.debug(sMethod + "calling addRecord to add: objTypeDTO: [" + objTypeDTO.toString() + "]");
			PhoneNumberType objPhoneNumberType = objPhoneNumberTypeDAO.addRecord(objTypeDTO);
			return objPhoneNumberType;

		} catch (SQLException e) {
			objLogger.warn(sMethod + "SQLException: " + csMsgDB_ErrorAddingPhoneNumberType + " [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingPhoneNumberType);
		} catch (HibernateException e) {
			objLogger.warn(sMethod + "HibernateException: " + csMsgDB_ErrorAddingPhoneNumberType + " [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingPhoneNumberType);
		} catch (Exception e) {
			objLogger.warn(sMethod + "Exception: " + csMsgDB_ErrorAddingPhoneNumberType + " [" + e.getMessage() + "]");
			throw new DatabaseException(csMsgDB_ErrorAddingPhoneNumberType);
		}

	}
	
	
	
	
}//End Class
