package com.amt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dao.AddressDAO;
import com.amt.dao.AddressDAOImpl;
import com.amt.dao.PhoneNumberDAO;
import com.amt.dao.PhoneNumberDAOImpl;
import com.amt.dto.AddAddressDTO;
import com.amt.dto.AddressDTO;
import com.amt.dto.PhoneNumberDTO;
import com.amt.exception.*;
import com.amt.model.Address;
import com.amt.model.PhoneNumber;
import com.amt.util.Validate;

public class PhoneService implements Constants {
	private Logger objLogger = LoggerFactory.getLogger(PhoneService.class);
	private PhoneNumberDAO objPhoneNumberDAO;

	public PhoneService() {
		this.objPhoneNumberDAO = new PhoneNumberDAOImpl();
	}

	public PhoneService(PhoneNumberDAO objMockDAO) {
		this.objPhoneNumberDAO = objMockDAO;
	}
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////


	
	
	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	public PhoneNumber addNewPhoneNumber(PhoneNumberDTO objPhoneNumberDTO) throws DatabaseException, BadParameterException {
		String sMethod = csCRT + "addNewPhoneNumber(): ";
		objLogger.trace(csCR + sMethod + "Entered: objPhoneNumberDTO: [" + objPhoneNumberDTO.toString() + "]");

		if (isValidPhoneNumberDTO(objPhoneNumberDTO)) {
			try {
				objLogger.debug(sMethod + "Validated objPhoneNumberDTO: [" + objPhoneNumberDTO.toString() + "]");	
				
				objLogger.debug(sMethod + "calling add dao with objPhoneNumberDTO: [" + objPhoneNumberDTO.toString() + "]");	
				PhoneNumber objPhoneNumber = new PhoneNumber();
				objPhoneNumber = objPhoneNumberDAO.addNewPhoneNumber(objPhoneNumberDTO);
				objLogger.debug(sMethod + "objPhoneNumber: [" + objPhoneNumber.toString() + "]");
				return objPhoneNumber;

			} catch (Exception e) {// not sure what exception hibernate throws but not SQLException
				objLogger.error(sMethod + "Exception adding address record with username: [" + objPhoneNumberDTO.getUsername()
						+ "] Exception: [" + e.toString() + "] [" + e.getMessage() + "]");
				throw new DatabaseException(csMsgDB_ErrorAddingPhoneNumber);
			}

		} else {
			objLogger.warn(sMethod + "objPhoneNumberDTO is not valid: [" + objPhoneNumberDTO.toString() + "]");
			throw new BadParameterException(csMsgBadParamAddPhoneNumber);
		}
	}


	////////////////////// Utility Methods for this Class /////////////////////////////////////////
	public boolean isValidPhoneNumberDTO(PhoneNumberDTO objPhoneNumberDTO) {
		String sMethod = csCRT + "isValidPhoneNumberDTO(): ";
		objLogger.trace(csCR + sMethod + "Entered: objPhoneNumberDTO: [" + objPhoneNumberDTO.toString() + "]");
		boolean bValid = false;
		
		String sPhoneNumber = objPhoneNumberDTO.getPhoneNumber();
		String sPhoneNumberType = objPhoneNumberDTO.getPhoneNumberType();
		String sUsername = objPhoneNumberDTO.getUsername();
		
		boolean bPhoneNumberValid = (sPhoneNumber.length() == 10) && Validate.isNumeric(sPhoneNumber);
		boolean bPhoneNumberTypeValid = Validate.isValidValueInArray(sPhoneNumberType, csarrPhoneNumberType);
		boolean bUsernameValid = UserService.isValidUsername(sUsername);
		
		if (bPhoneNumberValid && bPhoneNumberTypeValid && bUsernameValid) {
			bValid = true;
		}else {
			objLogger.trace(csCR + sMethod + "One or more add Phone Number Parameters did not pass validation.:");
			objLogger.warn(sMethod + "\t phone number: [" + sPhoneNumber + "] is valid: [" + bPhoneNumberValid + "]");
			objLogger.warn(sMethod + "\t phone number type: [" + sPhoneNumberType + "] is valid: [" + bPhoneNumberTypeValid + "]");
			objLogger.warn(sMethod + "\t username: [" + sUsername + "] is valid: [" + bUsernameValid + "]");
		}
		
		
		return bValid;
	}


}//END Class
