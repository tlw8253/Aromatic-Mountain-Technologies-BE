package com.amt.service;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dao.AddressDAO;
import com.amt.dao.AddressDAOImpl;
import com.amt.dto.AddAddressDTO;
import com.amt.dto.AddressDTO;
import com.amt.exception.*;
import com.amt.model.Address;
import com.amt.util.Validate;

public class AddressService implements Constants {
	private Logger objLogger = LoggerFactory.getLogger(AddressService.class);
	private AddressDAO objAddressDAO;

	public AddressService() {
		this.objAddressDAO = new AddressDAOImpl();
	}

	public AddressService(AddressDAO objMockDAO) {
		this.objAddressDAO = objMockDAO;
	}
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////


	
	
	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	public Address addNewAddress(AddressDTO objAddressDTO) throws DatabaseException, BadParameterException {
		String sMethod = csCRT + "addNewAddress(): ";
		objLogger.trace(csCR + sMethod + "Entered: objAddAddressDTO: [" + objAddressDTO.toString() + "]");

		if (isValidAddressDTO(objAddressDTO)) {
			try {
				objLogger.debug(sMethod + "Validated objAddressDTO: [" + objAddressDTO.toString() + "]");	
				
				objLogger.debug(sMethod + "calling add dao with objAddressDTO: [" + objAddressDTO.toString() + "]");	
				Address objAddress = new Address();
				objAddress = objAddressDAO.addAddress(objAddressDTO);
				objLogger.debug(sMethod + "objAddress: [" + objAddress.toString() + "]");
				return objAddress;

			} catch (NoResultException e) {
				objLogger.error(sMethod + "NoResultException adding address record with username: [" + objAddressDTO.getUsername()
						+ "] Exception: [" + e.toString() + "] [" + e.getMessage() + "]");
				throw new DatabaseException(csMsgDB_ErrorAddingAddress);
			} catch (Exception e) {// not sure what exception hibernate throws but not SQLException
				objLogger.error(sMethod + "Exception adding address record with username: [" + objAddressDTO.getUsername()
						+ "] Exception: [" + e.toString() + "] [" + e.getMessage() + "]");
				throw new DatabaseException(csMsgDB_ErrorAddingAddress);
			}

		} else {
			objLogger.warn(sMethod + "objAddressDTO is not valid: [" + objAddressDTO.toString() + "]");
			throw new BadParameterException(csMsgBadParamAddAddress);
		}
	}


	////////////////////// Utility Methods for this Class /////////////////////////////////////////
	public boolean isValidAddressDTO(AddressDTO objAddressDTO) {
		String sMethod = csCRT + "isValidAddressDTO(): ";
		objLogger.trace(csCR + sMethod + "Entered: objAddAddressDTO: [" + objAddressDTO.toString() + "]");
		boolean bValid = false;
		
		String sAdx1 = objAddressDTO.getAddressLine1();
		String sCity = objAddressDTO.getAddressCity();
		String sState = objAddressDTO.getAddressState();
		String sZip = objAddressDTO.getAddressZipCode();
		String sType = objAddressDTO.getAddressType();
		String sUsername = objAddressDTO.getUsername();
		
		boolean bAdx1Valid = (sAdx1.length() > 0);
		boolean bCityValid = Validate.isAlpha(sCity);
		boolean bStateValid = Validate.isValidStateCode(sState);
		boolean bZipValid = Validate.isInt(sZip);
		boolean bTypeValid = Validate.isValidValueInArray(sType, carrAddressType);
		boolean bUsernameValid = UserService.isValidUsername(sUsername);
		
		if (bAdx1Valid && bCityValid && bStateValid && bZipValid && bTypeValid && bUsernameValid) {
			bValid = true;
		}else {
			objLogger.trace(csCR + sMethod + "One or more add Address Parameters did not pass validation.:");
			objLogger.warn(sMethod + "\t address line 1: [" + sAdx1 + "] is valid: [" + bAdx1Valid + "]");
			objLogger.warn(sMethod + "\t city: [" + sCity + "] is valid: [" + bCityValid + "]");
			objLogger.warn(sMethod + "\t state: [" + sState + "] is valid: [" + bStateValid + "]");
			objLogger.warn(sMethod + "\t zip: [" + sZip + "] is valid: [" + bZipValid + "]");
			objLogger.warn(sMethod + "\t address type: [" + sType + "] is valid: [" + bTypeValid + "]");
			objLogger.warn(sMethod + "\t username: [" + sUsername + "] is valid: [" + bUsernameValid + "]");
		}
		
		
		return bValid;
	}

	/*
		private int addressTypeId = 0;
	private String addressLine1 = "";
	private String addressLine2 = "";
	private String addressCity = "";
	private String addressState = "";
	private String addressZipCode = "";
	private String addressCountry = "United States";
	private String addressType;
	private String username;
*/

}//END Class
