package com.amt.dto;

import java.util.Objects;

import com.amt.app.Constants;

//DTO - Data Transfer Object
//This is to be used in passing the full dto to the dao
public class CatalogItemDTO implements Constants {

	private String catalogItemName;
	private String catalogItem;
	private String catalogItemDescription;
	private double catalogItemPrice;
	private int catalogItemInStockQty;
	private String catalogItemType;

	public CatalogItemDTO() {
		super();
	}

	public CatalogItemDTO(String catalogItemName, String catalogItem, String catalogItemDescription, double catalogItemPrice,
			int catalogItemInStockQty, String catalogItemType) {
		this.catalogItemName = catalogItemName;
		this.catalogItem = catalogItem;
		this.catalogItemDescription = catalogItemDescription;
		this.catalogItemPrice = catalogItemPrice;
		this.catalogItemInStockQty = catalogItemInStockQty;
		this.catalogItemType = catalogItemType;		
	}

	public String getCatalogItemName() {
		return catalogItemName;
	}

	public String getCatalogItem() {
		return catalogItem;
	}

	public String getCatalogItemDescription() {
		return catalogItemDescription;
	}

	public double getCatalogItemPrice() {
		return catalogItemPrice;
	}

	public int getCatalogItemInStockQty() {
		return catalogItemInStockQty;
	}

	public String getCatalogItemType() {
		return catalogItemType;
	}

	public void setCatalogItemName(String catalogItemName) {
		this.catalogItemName = catalogItemName;
	}

	public void setCatalogItem(String catalogItem) {
		this.catalogItem = catalogItem;
	}

	public void setCatalogItemDescription(String catalogItemDescription) {
		this.catalogItemDescription = catalogItemDescription;
	}

	public void setCatalogItemPrice(double catalogItemPrice) {
		this.catalogItemPrice = catalogItemPrice;
	}

	public void setCatalogItemInStockQty(int catalogItemInStockQty) {
		this.catalogItemInStockQty = catalogItemInStockQty;
	}

	public void setCatalogItemType(String catalogItemType) {
		this.catalogItemType = catalogItemType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(catalogItem, catalogItemDescription, catalogItemInStockQty, catalogItemName,
				catalogItemPrice, catalogItemType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatalogItemDTO other = (CatalogItemDTO) obj;
		return Objects.equals(catalogItem, other.catalogItem)
				&& Objects.equals(catalogItemDescription, other.catalogItemDescription)
				&& catalogItemInStockQty == other.catalogItemInStockQty
				&& Objects.equals(catalogItemName, other.catalogItemName)
				&& Double.doubleToLongBits(catalogItemPrice) == Double.doubleToLongBits(other.catalogItemPrice)
				&& Objects.equals(catalogItemType, other.catalogItemType);
	}

	@Override
	public String toString() {
		return "CatalogItemDTO [catalogItemName=" + catalogItemName + ", catalogItem=" + catalogItem
				+ ", catalogItemDescription=" + catalogItemDescription + ", catalogItemPrice=" + catalogItemPrice
				+ ", catalogItemInStockQty=" + catalogItemInStockQty + ", catalogItemType=" + catalogItemType + "]";
	}


	
	
	
	
	
}// END Class
