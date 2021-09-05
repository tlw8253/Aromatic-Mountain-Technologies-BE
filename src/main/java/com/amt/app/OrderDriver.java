package com.amt.app;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amt.dto.AddCatalogItemDTO;
import com.amt.dto.AddOrderDTO;
import com.amt.dto.AddOrderedItemDTO;
import com.amt.exception.BadParameterException;
import com.amt.exception.DatabaseException;
import com.amt.model.CatalogItem;
import com.amt.model.Order;
import com.amt.service.CatalogItemService;
import com.amt.service.OrderService;




/**
 * This is a driver used during development to test functionality as it is
 * built. 
 * 
 * @author tlw8748253
 *
 */
public class OrderDriver implements Constants {
	private final static Logger objLogger = LoggerFactory.getLogger(OrderDriver.class);

	public static void main(String[] args) {
		String sMethod = csCRT + "main(): ";
		objLogger.trace(csCR + sMethod + "Entered");		

		//addCatalogItems();		
		//addUserOrder("tw8253");
		
	}
	
	//
	public static void addUserOrder(String sUsername) {
		String sMethod = csCRT + "addUserOrder(): ";
		objLogger.trace(csCR + sMethod + "Entered");
		
		String catalogItemName = "FRROAST0001";
		String orderItemPrice = "10.95";
		String orderItemQty = "5";
		AddOrderedItemDTO objAddOrderItemDTO = new AddOrderedItemDTO(catalogItemName, orderItemPrice, orderItemQty);
		List<AddOrderedItemDTO> lstOrderedItem = new ArrayList<AddOrderedItemDTO>();
		lstOrderedItem.add(objAddOrderItemDTO);

		catalogItemName = "TSHIRT0001";
		orderItemPrice = "5.95";
		orderItemQty = "2";
		objAddOrderItemDTO = new AddOrderedItemDTO(catalogItemName, orderItemPrice, orderItemQty);
		lstOrderedItem.add(objAddOrderItemDTO);
		
		objLogger.debug(sMethod + "lstOrderedItem: [" + lstOrderedItem.toString() + "]");

		AddOrderDTO objAddOrderDTO = new AddOrderDTO("66.65", lstOrderedItem);
		
		OrderService objOrderService = new OrderService();
		
		try {
			Order objOrder =  objOrderService.addNewOrder(sUsername, objAddOrderDTO);
			objLogger.debug(sMethod + "objOrder: [" + objOrder.toString() + "]");
		} catch (DatabaseException | BadParameterException e) {
			objLogger.debug(sMethod + "DatabaseException | BadParameterException: [" + e.getMessage() + "]");
		}
		
		
		
	}
	public static void addUserOrder(AddOrderDTO objAddOrderDTO) {
		String sMethod = csCRT + "addUserOrder(): ";
		objLogger.trace(csCR + sMethod + "Entered");
		
	}
	
	
	
	
	//
	//###
	public static void addCatalogItems() {
		String sMethod = csCRT + "addCatalogItems(): ";
		objLogger.trace(csCR + sMethod + "Entered");
		
		AddCatalogItemDTO objAddCatalogItemDTO;
		
		String catalogItemName = "FRROAST0001";
		String catalogItem = "French Roast";
		String catalogItemDescription = "French is the name applied to a degree of roast of coffee beans resulting in a dark brown coffee bean. In this roast, the beans are well into the second crack. French roasted beans will have a dark brown color and a shiny surface from its oils.";
		String catalogItemPrice = "12.95";
		String catalogItemInStockQty = "100";
		String catalogItemType = "BEANS";	
		
		objAddCatalogItemDTO = new AddCatalogItemDTO(catalogItemName, catalogItem, catalogItemDescription, catalogItemPrice, catalogItemInStockQty, catalogItemType);
		addCatlogItem(objAddCatalogItemDTO);
		
		catalogItemName = "FRROAST0010";
		catalogItemPrice = "13.95";
		catalogItemType = "GROUND";
		objAddCatalogItemDTO = new AddCatalogItemDTO(catalogItemName, catalogItem, catalogItemDescription, catalogItemPrice, catalogItemInStockQty, catalogItemType);
		addCatlogItem(objAddCatalogItemDTO);
		
		catalogItemName = "FRVANILLA0001";
		catalogItem = "French Vanilla";
		catalogItemDescription = "Pure Cane Sugar, Water, Natural Flavors, Sodium Benzoate (To Preserve Freshness), Citric Acid, Potassium Sorbate (To Preserve Freshness), Caramel Color.";
		catalogItemPrice = "6.95";
		catalogItemInStockQty = "50";
		catalogItemType = "SYRUP";	
		objAddCatalogItemDTO = new AddCatalogItemDTO(catalogItemName, catalogItem, catalogItemDescription, catalogItemPrice, catalogItemInStockQty, catalogItemType);
		addCatlogItem(objAddCatalogItemDTO);


		catalogItemName = "ESPMACHNE0001";
		catalogItem = "Espresso Machine";
		catalogItemDescription = "Espresso Machines 15 Bar with Adjustable Milk Frother Wand Expresso Coffee Machine for Cappuccino, Latte, Mocha, Machiato, 1.5L Removable Water Tank, Double Temperature Control System, 110.";
		catalogItemPrice = "158.99";
		catalogItemInStockQty = "10";
		catalogItemType = "ACCESSORY";	
		objAddCatalogItemDTO = new AddCatalogItemDTO(catalogItemName, catalogItem, catalogItemDescription, catalogItemPrice, catalogItemInStockQty, catalogItemType);
		addCatlogItem(objAddCatalogItemDTO);


		catalogItemName = "TSHIRT0001";
		catalogItem = "Coffee Pothead";
		catalogItemDescription = "pass the bean juice man.  100% cotton.  Heather colors are 50/50 poly-cotton.  Heather gray is 10/90 poly-cotton.  Designed & printed in the USA.";
		catalogItemPrice = "6.00";
		catalogItemInStockQty = "1000";
		catalogItemType = "APPAREL";	
		objAddCatalogItemDTO = new AddCatalogItemDTO(catalogItemName, catalogItem, catalogItemDescription, catalogItemPrice, catalogItemInStockQty, catalogItemType);
		addCatlogItem(objAddCatalogItemDTO);

		
	}
	
	//
	//###
	public static void addCatlogItem(AddCatalogItemDTO objAddCatalogItemDTO) {
		String sMethod = csCRT + "addCatlogItem(): ";
		objLogger.trace(csCR + sMethod + "Entered.");

		CatalogItemService objCatalogItemService = new CatalogItemService();
		objLogger.debug(sMethod + "adding user with objAddCatalogItemDTO: [" + objAddCatalogItemDTO.toString() + "]");

		try {
			CatalogItem objCatalogItem = objCatalogItemService.addNewCatalogItem(objAddCatalogItemDTO);
			objLogger.debug(sMethod + "catalog object added objCatalogItem: [" + objCatalogItem.toString() + "]");
		} catch (DatabaseException e) {
			objLogger.debug(sMethod + "DatabaseException: [" + e.getMessage() + "]");
		} catch (BadParameterException e) {
			objLogger.debug(sMethod + "BadParameterException: [" + e.getMessage() + "]");
		}

		
		
	}


}
