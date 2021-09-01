package com.amt.dto;

import com.amt.app.Constants;

public class CatalogItemTypeDTO extends AddOrEditDTO implements Constants{
	//Class attributes are store in the parent in HashMap tables.	
	//Stored in hashmaps in the super class
	
	//
	//###
	public CatalogItemTypeDTO() {
		super();
	}

	public CatalogItemTypeDTO(String type, String typeDesc) {
		setCatalogItemType(type);
		setCatalogItemTypeDescription(typeDesc);
	}

	public String getCatalogItemTypeId() {
		return super.getDataElement(csCatalogItemTypeTblCatalogItemTypeId);
	}
	public int getCatalogItemTypeIdAsInt() {
		return super.getIntDataElement(csCatalogItemTypeTblCatalogItemTypeId);
	}

	public String getCatalogItemType() {
		return super.getDataElement(csCatalogItemTypeTblCatalogItemType);
	}

	public String getCatalogItemTypeDescription() {
		return super.getDataElement(csCatalogItemTypeTblCatalogItemTypeDesc);
	}

	public void setCatalogItemTypeId(int typeId) {
		super.setDataElement(csCatalogItemTypeTblCatalogItemTypeId, typeId);
	}
	public void setCatalogItemTypeId(String typeId) {
		super.setDataElement(csCatalogItemTypeTblCatalogItemTypeId, typeId);
	}

	public void setCatalogItemType(String type) {
		super.setDataElement(csCatalogItemTypeTblCatalogItemType, type);
	}

	public void setCatalogItemTypeDescription(String typeDesc) {
		super.setDataElement(csCatalogItemTypeTblCatalogItemTypeDesc, typeDesc);
	}

	@Override
	public String toString() {
		return super.toString();
	}


	
	
}
