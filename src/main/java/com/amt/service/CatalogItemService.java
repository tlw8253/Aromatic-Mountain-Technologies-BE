package com.amt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.app.Constants;
import com.amt.dao.AddressDAO;
import com.amt.dao.AddressDAOImpl;
import com.amt.dao.CatalogItemDAO;
import com.amt.dao.CatalogItemDAOImpl;
import com.amt.dao.PhoneNumberDAO;
import com.amt.dao.PhoneNumberDAOImpl;
import com.amt.dto.AddAddressDTO;
import com.amt.dto.AddCatalogItemDTO;
import com.amt.dto.AddressDTO;
import com.amt.dto.CatalogItemDTO;
import com.amt.dto.PhoneNumberDTO;
import com.amt.exception.*;
import com.amt.model.Address;
import com.amt.model.CatalogItem;
import com.amt.model.PhoneNumber;
import com.amt.util.Validate;

public class CatalogItemService implements Constants {
	private Logger objLogger = LoggerFactory.getLogger(CatalogItemService.class);
	private CatalogItemDAO objCatalogItemDAO;

	public CatalogItemService() {
		this.objCatalogItemDAO = new CatalogItemDAOImpl();
	}

	public CatalogItemService(CatalogItemDAO objMockDAO) {
		this.objCatalogItemDAO = objMockDAO;
	}
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////


	
	
	// ###//////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	public CatalogItem addNewCatalogItem(AddCatalogItemDTO objAddCatalogItemDTO) throws DatabaseException, BadParameterException {
		String sMethod = csCRT + "addNewCatalogItem(): ";
		objLogger.trace(csCR + sMethod + "Entered: objAddCatalogItemDTO: [" + objAddCatalogItemDTO.toString() + "]");

		if (isValidCatalogItemDTO(objAddCatalogItemDTO)) {
			try {
				objLogger.debug(sMethod + "Validated objAddCatalogItemDTO: [" + objAddCatalogItemDTO.toString() + "]");	
				
				//create the DAO layer DTO
				CatalogItemDTO objCatalogItemDTO = new CatalogItemDTO();
				objCatalogItemDTO.setCatalogItem(objAddCatalogItemDTO.getCatalogItem());
				objCatalogItemDTO.setCatalogItemDescription(objAddCatalogItemDTO.getCatalogItemDescription());
				objCatalogItemDTO.setCatalogItemPrice(Double.parseDouble(objAddCatalogItemDTO.getCatalogItemPrice()));
				objCatalogItemDTO.setCatalogItemInStockQty(Integer.parseInt(objAddCatalogItemDTO.getCatalogItemInStockQty()));
				objCatalogItemDTO.setCatalogItemType(objAddCatalogItemDTO.getCatalogItemType());
								
				objLogger.debug(sMethod + "calling add dao with objCatalogItemDTO: [" + objCatalogItemDTO.toString() + "]");	
				CatalogItem objCatalogItem = objCatalogItemDAO.addNewCatalogItem(objCatalogItemDTO);
				objLogger.debug(sMethod + "objCatalogItem: [" + objCatalogItem.toString() + "]");
				
				return objCatalogItem;

			} catch (Exception e) {// not sure what exception hibernate throws but not SQLException
				objLogger.error(sMethod + "Exception adding catalog item: [" + objAddCatalogItemDTO.getCatalogItem()
						+ "] Exception: [" + e.toString() + "] [" + e.getMessage() + "]");
				throw new DatabaseException(csMsgDB_ErrorAddingCatalogItem);
			}

		} else {
			objLogger.warn(sMethod + "objAddCatalogItemDTO is not valid: [" + objAddCatalogItemDTO.toString() + "]");
			throw new BadParameterException(csMsgBadParamAddCatalogItem);
		}
	}


	////////////////////// Utility Methods for this Class /////////////////////////////////////////
	public boolean isValidCatalogItemDTO(AddCatalogItemDTO objAddCatalogItemDTO) {
		String sMethod = csCRT + "isValidCatalogItemDTO(): ";
		objLogger.trace(csCR + sMethod + "Entered: objAddCatalogItemDTO: [" + objAddCatalogItemDTO.toString() + "]");
		boolean bValid = false;
		
		String sCatItem = objAddCatalogItemDTO.getCatalogItem().trim();
		String sCatItemDesc = objAddCatalogItemDTO.getCatalogItemDescription().trim();
		String sCatItemPrice = objAddCatalogItemDTO.getCatalogItemPrice();
		String sCatItemInStock = objAddCatalogItemDTO.getCatalogItemInStockQty();
		String sCatItemType = objAddCatalogItemDTO.getCatalogItemType().trim();
		
		boolean bCatItemValid = sCatItem.length() > 0;
		boolean bCatItemDescValid = sCatItem.length() > 0 && sCatItem.length() < ciDescriptionMaxLen;
		boolean bCatItemPriceValid = Validate.isDouble(sCatItemPrice) && Double.parseDouble(sCatItemPrice) > 0;
		boolean bCatItemInStockValid = Validate.isInt(sCatItemInStock);
		boolean bCatItemTypeValid = Validate.isValidValueInArray(sCatItemType, csarrCatalogItemType);
		
		
		if (bCatItemValid && bCatItemDescValid && bCatItemPriceValid && bCatItemInStockValid && bCatItemTypeValid) {
			bValid = true;
		}else {
			objLogger.trace(csCR + sMethod + "One or more add Catalog Item Parameters did not pass validation.:");
			objLogger.warn(sMethod + "\t catalog item: [" + sCatItem + "] is valid: [" + bCatItemValid + "]");
			objLogger.warn(sMethod + "\t catalog item description: [" + sCatItemDesc + "] is valid: [" + bCatItemDescValid + "]");
			objLogger.warn(sMethod + "\t catalog item price: [" + sCatItemPrice + "] is valid: [" + bCatItemPriceValid + "]");
			objLogger.warn(sMethod + "\t catalog item in stock qty: [" + sCatItemInStock + "] is valid: [" + bCatItemInStockValid + "]");
			objLogger.warn(sMethod + "\t catalog item type: [" + sCatItemType + "] is valid: [" + bCatItemTypeValid + "]");
		}
		
		
		return bValid;
	}

	/*
		private String catalogItem;
	private String catalogItemDescription;
	private String catalogItemPrice;
	private String catalogItemInStockQty;
	private String catalogItemType;

*/
}//END Class
