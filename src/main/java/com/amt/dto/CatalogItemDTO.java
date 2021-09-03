package com.amt.dto;

import java.util.Objects;

import com.amt.app.Constants;

//DTO - Data Transfer Object
//This is to be used in passing the full dto to the dao
public class CatalogItemDTO implements Constants {

	private String catalogItem;
	private String catalogItemDescription;
	private double catalogItemPrice;
	private int catalogItemInStockQty;
	private String catalogItemType;

	public CatalogItemDTO() {
		super();
	}

	public CatalogItemDTO(String catalogItem, String catalogItemDescription, double catalogItemPrice,
			int catalogItemInStockQty, String catalogItemType) {
		this.catalogItem = catalogItem;
		this.catalogItemDescription = catalogItemDescription;
		this.catalogItemPrice = catalogItemPrice;
		this.catalogItemInStockQty = catalogItemInStockQty;
		this.catalogItemType = catalogItemType;		
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
		return Objects.hash(catalogItem, catalogItemDescription, catalogItemInStockQty, catalogItemPrice,
				catalogItemType);
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
				&& Double.doubleToLongBits(catalogItemPrice) == Double.doubleToLongBits(other.catalogItemPrice)
				&& Objects.equals(catalogItemType, other.catalogItemType);
	}

	@Override
	public String toString() {
		return "CatalogItemDTO [catalogItem=" + catalogItem + ", catalogItemDescription=" + catalogItemDescription
				+ ", catalogItemPrice=" + catalogItemPrice + ", catalogItemInStockQty=" + catalogItemInStockQty
				+ ", catalogItemType=" + catalogItemType + "]";
	}

	
	
	
	
}// END Class
