package com.amt.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dao.AddressDAO;
import com.amt.dao.AddressDAOImpl;
import com.amt.dao.OrderDAO;
import com.amt.dao.OrderDAOImpl;
import com.amt.dao.OrderedItemDAOImpl;
import com.amt.dao.PhoneNumberDAO;
import com.amt.dao.PhoneNumberDAOImpl;
import com.amt.dto.AddAddressDTO;
import com.amt.dto.AddOrderDTO;
import com.amt.dto.AddOrderedItemDTO;
import com.amt.dto.AddressDTO;
import com.amt.dto.OrderDTO;
import com.amt.dto.OrderedItemDTO;
import com.amt.dto.PhoneNumberDTO;
import com.amt.exception.*;
import com.amt.model.Address;
import com.amt.model.Order;
import com.amt.model.OrderedItem;
import com.amt.model.PhoneNumber;
import com.amt.util.Validate;

public class OrderService implements Constants {
	private Logger objLogger = LoggerFactory.getLogger(OrderService.class);
	private OrderDAO objOrderDAO;

	public OrderService() {
		this.objOrderDAO = new OrderDAOImpl();
	}

	public OrderService(OrderDAO objMockDAO) {
		this.objOrderDAO = objMockDAO;
	}
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////


	
	
	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	public Order addNewOrder(String sUsername, AddOrderDTO objAddOrderDTO) throws DatabaseException, BadParameterException {
		String sMethod = csCRT + "addNewOrder(): ";
		objLogger.trace(csCR + sMethod + "Entered: sUsername: [" + sUsername + " ] objAddOrderDTO: [" + objAddOrderDTO.toString() + "]");

		if (isValidOrderDTO(sUsername, objAddOrderDTO)) {
			try {
				objLogger.debug(sMethod + "Validated sUsername: [" + sUsername + "] objAddOrderDTO: [" + objAddOrderDTO.toString() + "]");	
				
				Double dAmount = Double.parseDouble(objAddOrderDTO.getOrderAmount());
				Timestamp objOrderSubmitted = Timestamp.valueOf(LocalDateTime.now());				
				Timestamp objOrderSent = new Timestamp(0);
				String sOrderStatus = csarrOrderStatus[enumOrderStatus.NEW.pos];
				
				OrderDTO objOrderDTO = new OrderDTO(sUsername, dAmount, objOrderSubmitted, objOrderSent, sOrderStatus);				
				Order objOrder = new Order();
				
				objLogger.debug(sMethod + "calling add dao with objAddOrderDTO: [" + objAddOrderDTO.toString() + "]");			
				objOrder = objOrderDAO.addNewOrder(objOrderDTO);
				objLogger.debug(sMethod + "Order: [" + objOrder.toString() + "]");
				
				//now add the ordered items to the database
				List<AddOrderedItemDTO> lstAddOrderedItemDTO = objAddOrderDTO.getLstOrderedItem();
				
				OrderedItemDAOImpl objOrderedItemDAOImpl = new OrderedItemDAOImpl();
				
				for (int iCtr=0; iCtr<lstAddOrderedItemDTO.size(); iCtr++) {
					
					String catalogItemName = lstAddOrderedItemDTO.get(iCtr).getCatalogItemName();
					Double orderItemPrice = Double.parseDouble(lstAddOrderedItemDTO.get(iCtr).getOrderItemPrice());
					int orderItemQty = Integer.parseInt(lstAddOrderedItemDTO.get(iCtr).getOrderItemQty());
					OrderedItemDTO objOrderedItemDTO = new OrderedItemDTO(objOrder, catalogItemName, orderItemPrice, orderItemQty);
					OrderedItem objOrderedItem = objOrderedItemDAOImpl.addNewOrderItem(objOrderedItemDTO);
					objLogger.debug(sMethod + "objOrderedItem: [" + objOrderedItem.toString() + "]");				
				}
				
				return objOrder;

			} catch (Exception e) {// not sure what exception hibernate throws but not SQLException
				objLogger.error(sMethod + "Exception adding order record with username: [" + sUsername
						+ "] Exception: [" + e.toString() + "] [" + e.getMessage() + "]");
				throw new DatabaseException(csMsgDB_ErrorAddingOrder);
			}

		} else {
			objLogger.warn(sMethod + "objAddOrderDTO is not valid: [" + objAddOrderDTO.toString() + "]");
			throw new BadParameterException(csMsgBadParamAddOrder);
		}
	}


	////////////////////// Utility Methods for this Class /////////////////////////////////////////
	public boolean isValidOrderDTO(String sUsername, AddOrderDTO objAddOrderDTO) {
		String sMethod = csCRT + "isValidOrderDTO(): ";
		objLogger.trace(csCR + sMethod + "Entered: objAddOrderDTO: [" + objAddOrderDTO.toString() + "]");
		boolean bValid = false;
		
		String sOrderAmount = objAddOrderDTO.getOrderAmount();
		List<AddOrderedItemDTO> lstOrderedItem = objAddOrderDTO.getLstOrderedItem();
		
		boolean bOrderAmount = Validate.isDouble(sOrderAmount);
		boolean bUsernameValid = UserService.isValidUsername(sUsername);
		
		if (bOrderAmount && bUsernameValid) {
			bValid = true;
		}else {
			objLogger.trace(csCR + sMethod + "One or more add Order Parameters did not pass validation.:");
			objLogger.warn(sMethod + "\t order amount: [" + sOrderAmount + "] is valid: [" + bOrderAmount + "]");
			objLogger.warn(sMethod + "\t username: [" + sUsername + "] is valid: [" + bUsernameValid + "]");
		}
		
		
		return bValid;
	}


}//END Class
