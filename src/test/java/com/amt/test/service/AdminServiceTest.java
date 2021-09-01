package com.amt.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*; // You may need to type this import manually to make use of 
//the argument matchers for Mockito, such as eq() or any()

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.amt.app.Constants;
import com.amt.app.Constants.enumAddressType;
import com.amt.dao.GenericDAO;
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
import com.amt.service.AdminService;



public class AdminServiceTest implements Constants {
	private static Logger objLogger = LoggerFactory.getLogger(AdminServiceTest.class);
	
	private GenericDAO<AddressType> objMockAddressTypeDAO;
	private AdminService objAdminSeriveAddressType;	

	private GenericDAO<CatalogItemType> objMockCatalogItemTypeDAO;
	private AdminService objAdminSeriveCatalogItemType;	

	private GenericDAO<EmployeeRole> objMockEmployeeRoleDAO;
	private AdminService objAdminSeriveEmployeeRole;	

	private GenericDAO<OrderStatus> objMockOrderStatusDAO;
	private AdminService objAdminSeriveOrderStatus;	
	
	private GenericDAO<PhoneNumberType> objMockPhoneNumberTypeDAO;
	private AdminService objAdminSerivePhoneNumberType;	
	
	private GenericDAO<UserType> objMockUserTypeDAO;
	private AdminService objAdminSeriveUserType;	

	public AdminServiceTest() {
		super();
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		objLogger.trace("setUpBeforeClass()");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		objLogger.trace("tearDownAfterClass()");
	}

	@Before
	public void setUp() throws Exception {
		objLogger.trace("setUp()");
		//fake DAO using the GenericDAO<T> interface class		
		this.objMockAddressTypeDAO = mock(GenericDAO.class);
		objAdminSeriveAddressType = new AdminService().getMockAddressTypeAdminService(objMockAddressTypeDAO);		
		
		objMockCatalogItemTypeDAO = mock(GenericDAO.class);
		objAdminSeriveCatalogItemType = new AdminService().getMockCatalogItemTypeAdminService(objMockCatalogItemTypeDAO);	

		objMockEmployeeRoleDAO = mock(GenericDAO.class);
		objAdminSeriveEmployeeRole = new AdminService().getMockEmployeeRoleAdminService(objMockEmployeeRoleDAO);
		
		objMockOrderStatusDAO = mock(GenericDAO.class);
		objAdminSeriveOrderStatus = new AdminService().getMockOrderStatusAdminService(objMockOrderStatusDAO);

		objMockPhoneNumberTypeDAO = mock(GenericDAO.class);
		objAdminSerivePhoneNumberType = new AdminService().getMockPhoneNumberTypeAdminService(objMockPhoneNumberTypeDAO);

		objMockUserTypeDAO = mock(GenericDAO.class);
		objAdminSeriveUserType = new AdminService().getMockUserTypeAdminService(objMockUserTypeDAO);

	}

	@After
	public void tearDown() throws Exception {
		objLogger.trace("tearDown()");
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test	//### 00.000 Add static values: AddressType
	public void test_AddAddressType_Success() throws Exception {
		String sMethod = csCRT + "test_AddAddressType_Success(): ";
		objLogger.trace(csCR + sMethod);		
		
		AddressType mockReturn = new AddressType(carrAddressType[enumAddressType.SHIPPING.pos], carrAddressTypeDesc[enumAddressType.SHIPPING.pos]);
		objLogger.debug(sMethod + "mockReturn: [" + mockReturn + "]");		
		
		AddressTypeDTO objDTO = new AddressTypeDTO(carrAddressType[enumAddressType.SHIPPING.pos], carrAddressTypeDesc[enumAddressType.SHIPPING.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO + "]");
		
		when(objMockAddressTypeDAO.addRecord(objDTO)).thenReturn(mockReturn);
	
		AddressType objActual = objAdminSeriveAddressType.addAddressType(objDTO);
		objLogger.debug(sMethod + "objActual: [" + objActual + "]");
		
		AddressType objExpected = new AddressType(carrAddressType[enumAddressType.SHIPPING.pos], carrAddressTypeDesc[enumAddressType.SHIPPING.pos]);
		objLogger.debug(sMethod + "objExpected: [" + objExpected + "]");
		
		assertEquals(objExpected, objActual);
		
	}

	
	@Test	//### 00.001 Add static values: AddressType
	public void test_AddAddressType_Exception() throws Exception {
		String sMethod = csCRT + "test_AddAddressType_Exception(): ";
		objLogger.trace(csCR + sMethod);		
		
		AddressTypeDTO objDTO = new AddressTypeDTO(carrAddressType[enumAddressType.SHIPPING.pos], carrAddressTypeDesc[enumAddressType.SHIPPING.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");
		when(objMockAddressTypeDAO.addRecord(objDTO)).thenThrow(SQLException.class);

		try {
			objAdminSeriveAddressType.addAddressType(objDTO);
			fail();			
		}catch(DatabaseException e) {		
			objLogger.debug(sMethod + "DatabaseException: [" + e.getMessage() + "]");
		assertEquals(csMsgDB_ErrorAddingAddressType, e.getMessage());
		}
	}

	
	@Test	//### 00.002 Add static values: AddressType
	public void test_AddAddressType_BadParam_Type_Not_In_List() throws Exception {
		String sMethod = csCRT + "test_AddAddressType_BadParam_Type_Not_In_List(): ";
		objLogger.trace(csCR + sMethod);		
		
		AddressTypeDTO objDTO = new AddressTypeDTO("TYPE_NOT_IN_LIST", carrAddressTypeDesc[enumAddressType.SHIPPING.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSeriveAddressType.addAddressType(objDTO);	//test real AdminService				
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamAddressType, e.getMessage());
		}
	}


	@Test	//### 00.003 Add static values: AddressType
	public void test_AddAddressType_BadParam_Type_Blank() throws Exception {
		String sMethod = csCRT + "test_AddAddressType_BadParam_Type_Blank(): ";
		objLogger.trace(csCR + sMethod);		
		
		AddressTypeDTO objDTO = new AddressTypeDTO("", carrAddressTypeDesc[enumAddressType.SHIPPING.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSeriveAddressType.addAddressType(objDTO);	//test real AdminService				
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamAddressType, e.getMessage());
		}
	}

	@Test	//### 00.004 Add static values: AddressType
	public void test_AddAddressType_BadParam_TypeDesc_Blank() throws Exception {
		String sMethod = csCRT + "test_AddAddressType_BadParam_TypeDesc_Blank(): ";
		objLogger.trace(csCR + sMethod);		
		
		AddressTypeDTO objDTO = new AddressTypeDTO(carrAddressType[enumAddressType.SHIPPING.pos], "");
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSeriveAddressType.addAddressType(objDTO);	//test real AdminService				
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamAddressType, e.getMessage());
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test	//### 01.000 Add static values: CatalogItemType
	public void test_AddCatalogItemType_Success() throws Exception {
		String sMethod = csCRT + "test_AddCatalogItemType_Success(): ";
		objLogger.trace(csCR + sMethod);		
		
		CatalogItemType mockReturn = new CatalogItemType(csarrCatalogItemType[enumCatalogItemType.ACCESSORY.pos], csarrCatalogItemTypeDesc[enumCatalogItemType.ACCESSORY.pos]);
		objLogger.debug(sMethod + "mockReturn: [" + mockReturn + "]");		
		
		CatalogItemTypeDTO objDTO = new CatalogItemTypeDTO(csarrCatalogItemType[enumCatalogItemType.ACCESSORY.pos], csarrCatalogItemTypeDesc[enumCatalogItemType.ACCESSORY.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO + "]");
		
		when(objMockCatalogItemTypeDAO.addRecord(objDTO)).thenReturn(mockReturn);
	
		CatalogItemType objActual = objAdminSeriveCatalogItemType.addCatalogItemType(objDTO);
		objLogger.debug(sMethod + "objActual: [" + objActual + "]");
		
		CatalogItemType objExpected = new CatalogItemType(csarrCatalogItemType[enumCatalogItemType.ACCESSORY.pos], csarrCatalogItemTypeDesc[enumCatalogItemType.ACCESSORY.pos]);
		objLogger.debug(sMethod + "objExpected: [" + objExpected + "]");
		
		assertEquals(objExpected, objActual);
	}

	
	@Test	//### 01.001 Add static values: CatalogItemType
	public void test_AddCatalogItemType_Exception() throws Exception {
		String sMethod = csCRT + "test_AddCatalogItemType_Exception(): ";
		objLogger.trace(csCR + sMethod);		
		
		CatalogItemTypeDTO objDTO = new CatalogItemTypeDTO(csarrCatalogItemType[enumCatalogItemType.ACCESSORY.pos], csarrCatalogItemTypeDesc[enumCatalogItemType.ACCESSORY.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");
		when(objMockCatalogItemTypeDAO.addRecord(objDTO)).thenThrow(SQLException.class);

		try {
			objAdminSeriveCatalogItemType.addCatalogItemType(objDTO);
			fail();			
		}catch(DatabaseException e) {		
			objLogger.debug(sMethod + "DatabaseException: [" + e.getMessage() + "]");
		assertEquals(csMsgDB_ErrorAddingCatalogItemType, e.getMessage());
		}
	}

	
	@Test	//### 01.002 Add static values: AddressType
	public void test_AddCatalogItemType_BadParam_Type_Not_In_List() throws Exception {
		String sMethod = csCRT + "test_AddCatalogItemType_BadParam_Type_Not_In_List(): ";
		objLogger.trace(csCR + sMethod);		
		
		CatalogItemTypeDTO objDTO = new CatalogItemTypeDTO("NOT_IN_LIST", csarrCatalogItemTypeDesc[enumCatalogItemType.ACCESSORY.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSeriveCatalogItemType.addCatalogItemType(objDTO);
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamCatalogItemType, e.getMessage());
		}
	}


	@Test	//### 01.003 Add static values: AddressType
	public void test_AddCatalogItemType_BadParam_Type_Blank() throws Exception {
		String sMethod = csCRT + "test_AddCatalogItemType_BadParam_Type_Blank(): ";
		objLogger.trace(csCR + sMethod);		
		
		CatalogItemTypeDTO objDTO = new CatalogItemTypeDTO("", csarrCatalogItemTypeDesc[enumCatalogItemType.ACCESSORY.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSeriveCatalogItemType.addCatalogItemType(objDTO);	//test real AdminService				
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamCatalogItemType, e.getMessage());
		}
	}

	@Test	//### 01.004 Add static values: AddressType
	public void test_AddCatalogItemType_BadParam_TypeDesc_Blank() throws Exception {
		String sMethod = csCRT + "test_AddCatalogItemType_BadParam_TypeDesc_Blank(): ";
		objLogger.trace(csCR + sMethod);		
		
		CatalogItemTypeDTO objDTO = new CatalogItemTypeDTO(csarrCatalogItemType[enumCatalogItemType.ACCESSORY.pos], "");
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSeriveCatalogItemType.addCatalogItemType(objDTO);	//test real AdminService				
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamCatalogItemType, e.getMessage());
		}
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test	//### 02.000 Add static values: EmployeeRole
	public void test_AddEmployeeRole_Success() throws Exception {
		String sMethod = csCRT + "test_AddEmployeeRole_Success(): ";
		objLogger.trace(csCR + sMethod);		
		
		EmployeeRole mockReturn = new EmployeeRole(csarEmployeeRoles[enumUserEmployee.CATALOG_EMPLOYEE.pos], csarEmployeeRolesDesc[enumUserEmployee.CATALOG_EMPLOYEE.pos]);
		objLogger.debug(sMethod + "mockReturn: [" + mockReturn + "]");		
		
		EmployeeRoleDTO objDTO = new EmployeeRoleDTO(csarEmployeeRoles[enumUserEmployee.CATALOG_EMPLOYEE.pos], csarEmployeeRolesDesc[enumUserEmployee.CATALOG_EMPLOYEE.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO + "]");
		
		when(objMockEmployeeRoleDAO.addRecord(objDTO)).thenReturn(mockReturn);
	
		EmployeeRole objActual = objAdminSeriveEmployeeRole.addEmployeeRole(objDTO);
		objLogger.debug(sMethod + "objActual: [" + objActual + "]");
		
		EmployeeRole objExpected = new EmployeeRole(csarEmployeeRoles[enumUserEmployee.CATALOG_EMPLOYEE.pos], csarEmployeeRolesDesc[enumUserEmployee.CATALOG_EMPLOYEE.pos]);
		objLogger.debug(sMethod + "objExpected: [" + objExpected + "]");
		
		assertEquals(objExpected, objActual);
	}

	
	@Test	//### 02.001 Add static values: EmployeeRole
	public void test_AddEmployeeRole_Exception() throws Exception {
		String sMethod = csCRT + "test_AddEmployeeRole_Exception(): ";
		objLogger.trace(csCR + sMethod);		
		
		EmployeeRoleDTO objDTO = new EmployeeRoleDTO(csarEmployeeRoles[enumUserEmployee.CATALOG_EMPLOYEE.pos], csarEmployeeRolesDesc[enumUserEmployee.CATALOG_EMPLOYEE.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");
		when(objMockEmployeeRoleDAO.addRecord(objDTO)).thenThrow(SQLException.class);

		try {
			objAdminSeriveEmployeeRole.addEmployeeRole(objDTO);
			fail();			
		}catch(DatabaseException e) {		
			objLogger.debug(sMethod + "DatabaseException: [" + e.getMessage() + "]");
		assertEquals(csMsgDB_ErrorAddingEmployeeRole, e.getMessage());
		}
	}

	
	@Test	//### 02.002 Add static values: EmployeeRole
	public void test_AddEmployeeRole_BadParam_Type_Not_In_List() throws Exception {
		String sMethod = csCRT + "test_AddEmployeeRole_BadParam_Type_Not_In_List(): ";
		objLogger.trace(csCR + sMethod);		
		
		EmployeeRoleDTO objDTO = new EmployeeRoleDTO("NOT_IN_LIST", csarEmployeeRolesDesc[enumUserEmployee.CATALOG_EMPLOYEE.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSeriveEmployeeRole.addEmployeeRole(objDTO);
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamEmployeeRole, e.getMessage());
		}
	}


	@Test	//### 02.003 Add static values: EmployeeRole
	public void test_AddEmployeeRole_BadParam_Type_Blank() throws Exception {
		String sMethod = csCRT + "test_AddEmployeeRole_BadParam_Type_Blank(): ";
		objLogger.trace(csCR + sMethod);		
		
		EmployeeRoleDTO objDTO = new EmployeeRoleDTO("", csarEmployeeRolesDesc[enumUserEmployee.CATALOG_EMPLOYEE.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSeriveEmployeeRole.addEmployeeRole(objDTO);
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamEmployeeRole, e.getMessage());
		}
	}

	@Test	//### 02.004 Add static values: EmployeeRole
	public void test_AddEmployeeRole_BadParam_TypeDesc_Blank() throws Exception {
		String sMethod = csCRT + "test_AddEmployeeRole_BadParam_TypeDesc_Blank(): ";
		objLogger.trace(csCR + sMethod);		
		
		EmployeeRoleDTO objDTO = new EmployeeRoleDTO(csarEmployeeRoles[enumUserEmployee.CATALOG_EMPLOYEE.pos], "");
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSeriveEmployeeRole.addEmployeeRole(objDTO);
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamEmployeeRole, e.getMessage());
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test	//### 03.000 Add static values: OrderStatus
	public void test_AddOrderStatus_Success() throws Exception {
		String sMethod = csCRT + "test_AddOrderStatus_Success(): ";
		objLogger.trace(csCR + sMethod);		
		
		OrderStatus mockReturn = new OrderStatus(csarrOrderStatus[enumOrderStatus.PAID.pos], csarrOrderStatusDesc[enumOrderStatus.PAID.pos]);
		objLogger.debug(sMethod + "mockReturn: [" + mockReturn + "]");		
		
		OrderStatusDTO objDTO = new OrderStatusDTO(csarrOrderStatus[enumOrderStatus.PAID.pos], csarrOrderStatusDesc[enumOrderStatus.PAID.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO + "]");
		
		when(objMockOrderStatusDAO.addRecord(objDTO)).thenReturn(mockReturn);
	
		OrderStatus objActual = objAdminSeriveOrderStatus.addOrderStatus(objDTO);
		objLogger.debug(sMethod + "objActual: [" + objActual + "]");
		
		OrderStatus objExpected = new OrderStatus(csarrOrderStatus[enumOrderStatus.PAID.pos], csarrOrderStatusDesc[enumOrderStatus.PAID.pos]);
		objLogger.debug(sMethod + "objExpected: [" + objExpected + "]");
		
		assertEquals(objExpected, objActual);
	}

	
	@Test	//### 03.001 Add static values: OrderStatus
	public void test_AddOrderStatus_Exception() throws Exception {
		String sMethod = csCRT + "test_AddOrderStatus_Exception(): ";
		objLogger.trace(csCR + sMethod);		
		
		OrderStatusDTO objDTO = new OrderStatusDTO(csarrOrderStatus[enumOrderStatus.PAID.pos], csarrOrderStatusDesc[enumOrderStatus.PAID.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");
		when(objMockOrderStatusDAO.addRecord(objDTO)).thenThrow(SQLException.class);

		try {
			objAdminSeriveOrderStatus.addOrderStatus(objDTO);
			fail();			
		}catch(DatabaseException e) {		
			objLogger.debug(sMethod + "DatabaseException: [" + e.getMessage() + "]");
		assertEquals(csMsgDB_ErrorAddingOrderStatus, e.getMessage());
		}
	}

	
	@Test	//### 03.002 Add static values: OrderStatus
	public void test_AddOrderStatus_BadParam_Type_Not_In_List() throws Exception {
		String sMethod = csCRT + "test_AddOrderStatus_BadParam_Type_Not_In_List(): ";
		objLogger.trace(csCR + sMethod);		
		
		OrderStatusDTO objDTO = new OrderStatusDTO("NOT_IN_LIST", csarrOrderStatusDesc[enumOrderStatus.PAID.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSeriveOrderStatus.addOrderStatus(objDTO);
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamOrderStatus, e.getMessage());
		}
	}


	@Test	//### 03.003 Add static values: OrderStatus
	public void test_AddOrderStatus_BadParam_Type_Blank() throws Exception {
		String sMethod = csCRT + "test_AddOrderStatus_BadParam_Type_Blank(): ";
		objLogger.trace(csCR + sMethod);		
		
		OrderStatusDTO objDTO = new OrderStatusDTO("", csarrOrderStatusDesc[enumOrderStatus.PAID.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSeriveOrderStatus.addOrderStatus(objDTO);
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamOrderStatus, e.getMessage());
		}
	}

	@Test	//### 03.004 Add static values: EmployeeRole
	public void test_AddOrderStatus_BadParam_TypeDesc_Blank() throws Exception {
		String sMethod = csCRT + "test_AddOrderStatus_BadParam_TypeDesc_Blank(): ";
		objLogger.trace(csCR + sMethod);		
		
		OrderStatusDTO objDTO = new OrderStatusDTO(csarrOrderStatus[enumOrderStatus.PAID.pos], "");
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSeriveOrderStatus.addOrderStatus(objDTO);
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamOrderStatus, e.getMessage());
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test	//### 04.000 Add static values: PhoneNumberType
	public void test_AddPhoneNumberType_Success() throws Exception {
		String sMethod = csCRT + "test_AddPhoneNumberType_Success(): ";
		objLogger.trace(csCR + sMethod);		
		
		PhoneNumberType mockReturn = new PhoneNumberType(csarrPhoneNumberType[enumPhoneNumberType.MOBILE.pos], csarrPhoneNumberTypeDesc[enumPhoneNumberType.MOBILE.pos]);
		objLogger.debug(sMethod + "mockReturn: [" + mockReturn + "]");		
		
		PhoneNumberTypeDTO objDTO = new PhoneNumberTypeDTO(csarrPhoneNumberType[enumPhoneNumberType.MOBILE.pos], csarrPhoneNumberTypeDesc[enumPhoneNumberType.MOBILE.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO + "]");
		
		when(objMockPhoneNumberTypeDAO.addRecord(objDTO)).thenReturn(mockReturn);
	
		PhoneNumberType objActual = objAdminSerivePhoneNumberType.addPhoneNumberType(objDTO);
		objLogger.debug(sMethod + "objActual: [" + objActual + "]");
		
		PhoneNumberType objExpected = new PhoneNumberType(csarrPhoneNumberType[enumPhoneNumberType.MOBILE.pos], csarrPhoneNumberTypeDesc[enumPhoneNumberType.MOBILE.pos]);
		objLogger.debug(sMethod + "objExpected: [" + objExpected + "]");
		
		assertEquals(objExpected, objActual);
	}

	
	@Test	//### 04.001 Add static values: PhoneNumberType
	public void test_AddPhoneNumberType_Exception() throws Exception {
		String sMethod = csCRT + "test_AddPhoneNumberType_Exception(): ";
		objLogger.trace(csCR + sMethod);		
		
		PhoneNumberTypeDTO objDTO = new PhoneNumberTypeDTO(csarrPhoneNumberType[enumPhoneNumberType.MOBILE.pos], csarrPhoneNumberTypeDesc[enumPhoneNumberType.MOBILE.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");
		when(objMockPhoneNumberTypeDAO.addRecord(objDTO)).thenThrow(SQLException.class);

		try {
			objAdminSerivePhoneNumberType.addPhoneNumberType(objDTO);
			fail();			
		}catch(DatabaseException e) {		
			objLogger.debug(sMethod + "DatabaseException: [" + e.getMessage() + "]");
		assertEquals(csMsgDB_ErrorAddingPhoneNumberType, e.getMessage());
		}
	}

	
	@Test	//### 04.002 Add static values: PhoneNumberType
	public void test_AddPhoneNumberType_BadParam_Type_Not_In_List() throws Exception {
		String sMethod = csCRT + "test_AddPhoneNumberType_BadParam_Type_Not_In_List(): ";
		objLogger.trace(csCR + sMethod);		
		
		PhoneNumberTypeDTO objDTO = new PhoneNumberTypeDTO("NOT_IN_LIST", csarrPhoneNumberTypeDesc[enumPhoneNumberType.MOBILE.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSerivePhoneNumberType.addPhoneNumberType(objDTO);
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamPhoneNumberType, e.getMessage());
		}
	}


	@Test	//### 04.003 Add static values: PhoneNumberType
	public void test_AddPhoneNumberType_BadParam_Type_Blank() throws Exception {
		String sMethod = csCRT + "test_AddPhoneNumberType_BadParam_Type_Blank(): ";
		objLogger.trace(csCR + sMethod);		
		
		PhoneNumberTypeDTO objDTO = new PhoneNumberTypeDTO("", csarrPhoneNumberTypeDesc[enumPhoneNumberType.MOBILE.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSerivePhoneNumberType.addPhoneNumberType(objDTO);
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamPhoneNumberType, e.getMessage());
		}
	}

	@Test	//### 04.004 Add static values: PhoneNumberType
	public void test_AddPhoneNumberType_BadParam_TypeDesc_Blank() throws Exception {
		String sMethod = csCRT + "test_AddPhoneNumberType_BadParam_TypeDesc_Blank(): ";
		objLogger.trace(csCR + sMethod);		
		
		PhoneNumberTypeDTO objDTO = new PhoneNumberTypeDTO(csarrPhoneNumberType[enumPhoneNumberType.MOBILE.pos], "");
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSerivePhoneNumberType.addPhoneNumberType(objDTO);
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamPhoneNumberType, e.getMessage());
		}
	}

	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test	//### 05.000 Add static values: UserType
	public void test_AddUserType_Success() throws Exception {
		String sMethod = csCRT + "test_AddUserType_Success(): ";
		objLogger.trace(csCR + sMethod);		
		
		UserType mockReturn = new UserType(csarUserType[enumUserType.CUSTOMER.pos], csarUserTypeDesc[enumUserType.CUSTOMER.pos]);
		objLogger.debug(sMethod + "mockReturn: [" + mockReturn + "]");		
		
		UserTypeDTO objDTO = new UserTypeDTO(csarUserType[enumUserType.CUSTOMER.pos], csarUserTypeDesc[enumUserType.CUSTOMER.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO + "]");
		
		when(objMockUserTypeDAO.addRecord(objDTO)).thenReturn(mockReturn);
	
		UserType objActual = objAdminSeriveUserType.addUserType(objDTO);
		objLogger.debug(sMethod + "objActual: [" + objActual + "]");
		
		UserType objExpected = new UserType(csarUserType[enumUserType.CUSTOMER.pos], csarUserTypeDesc[enumUserType.CUSTOMER.pos]);
		objLogger.debug(sMethod + "objExpected: [" + objExpected + "]");
		
		assertEquals(objExpected, objActual);
	}
	
	@Test	//### 05.001 Add static values: UserType
	public void test_AddUserType_Exception() throws Exception {
		String sMethod = csCRT + "test_AddUserType_Exception(): ";
		objLogger.trace(csCR + sMethod);		
		
		UserTypeDTO objDTO = new UserTypeDTO(csarUserType[enumUserType.CUSTOMER.pos], csarUserTypeDesc[enumUserType.CUSTOMER.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");
		when(objMockUserTypeDAO.addRecord(objDTO)).thenThrow(SQLException.class);

		try {
			objAdminSeriveUserType.addUserType(objDTO);
			fail();			
		}catch(DatabaseException e) {		
			objLogger.debug(sMethod + "DatabaseException: [" + e.getMessage() + "]");
		assertEquals(csMsgDB_ErrorAddingUserType, e.getMessage());
		}
	}

	
	@Test	//### 05.002 Add static values: UserType
	public void test_AddUserType_BadParam_Type_Not_In_List() throws Exception {
		String sMethod = csCRT + "test_AddUserType_BadParam_Type_Not_In_List(): ";
		objLogger.trace(csCR + sMethod);		
		
		UserTypeDTO objDTO = new UserTypeDTO("NOT_IN_LIST", csarUserTypeDesc[enumUserType.CUSTOMER.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSeriveUserType.addUserType(objDTO);
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamUserType, e.getMessage());
		}
	}


	@Test	//### 05.003 Add static values: UserType
	public void test_AddUserType_BadParam_Type_Blank() throws Exception {
		String sMethod = csCRT + "test_AddUserType_BadParam_Type_Blank(): ";
		objLogger.trace(csCR + sMethod);		
		
		UserTypeDTO objDTO = new UserTypeDTO("", csarUserTypeDesc[enumUserType.CUSTOMER.pos]);
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSeriveUserType.addUserType(objDTO);
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamUserType, e.getMessage());
		}
	}

	@Test	//### 05.004 Add static values: UserType
	public void test_AddUserType_BadParam_TypeDesc_Blank() throws Exception {
		String sMethod = csCRT + "test_AddUserType_BadParam_TypeDesc_Blank(): ";
		objLogger.trace(csCR + sMethod);		
		
		UserTypeDTO objDTO = new UserTypeDTO(csarUserType[enumUserType.CUSTOMER.pos], "");
		objLogger.debug(sMethod + "objDTO: [" + objDTO.toString() + "]");	

		try {
			objAdminSeriveUserType.addUserType(objDTO);
		}catch(BadParameterException e) {		
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		assertEquals(csMsgBadParamUserType, e.getMessage());
		}
	}

	
}//END of Class
