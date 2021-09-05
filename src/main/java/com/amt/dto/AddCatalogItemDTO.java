package com.amt.dto;

import java.util.Objects;

//DTO - Data Transfer Object
//This is to be used in the controller class to get parameters by body
public class AddCatalogItemDTO {

	private String loginUsername = "";
	private String loginPassword = "";
	private String catalogItemName;
	private String catalogItem;
	private String catalogItemDescription;
	private String catalogItemPrice;
	private String catalogItemInStockQty;
	private String catalogItemType;

	public AddCatalogItemDTO() {
		super();
	}

	public AddCatalogItemDTO(String catalogItemName, String catalogItem,
			String catalogItemDescription, String catalogItemPrice, String catalogItemInStockQty,
			String catalogItemType) {
		this.catalogItemName = catalogItemName;
		this.catalogItem = catalogItem;
		this.catalogItemDescription = catalogItemDescription;
		this.catalogItemPrice = catalogItemPrice;
		this.catalogItemInStockQty = catalogItemInStockQty;
		this.catalogItemType = catalogItemType;
	}

	
	public AddCatalogItemDTO(String loginUsername, String loginPassword, String catalogItemName, String catalogItem,
			String catalogItemDescription, String catalogItemPrice, String catalogItemInStockQty,
			String catalogItemType) {
		this.loginUsername = loginUsername;
		this.loginPassword = loginPassword;
		this.catalogItemName = catalogItemName;
		this.catalogItem = catalogItem;
		this.catalogItemDescription = catalogItemDescription;
		this.catalogItemPrice = catalogItemPrice;
		this.catalogItemInStockQty = catalogItemInStockQty;
		this.catalogItemType = catalogItemType;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public String getLoginPassword() {
		return loginPassword;
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

	public String getCatalogItemPrice() {
		return catalogItemPrice;
	}

	public String getCatalogItemInStockQty() {
		return catalogItemInStockQty;
	}

	public String getCatalogItemType() {
		return catalogItemType;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
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

	public void setCatalogItemPrice(String catalogItemPrice) {
		this.catalogItemPrice = catalogItemPrice;
	}

	public void setCatalogItemInStockQty(String catalogItemInStockQty) {
		this.catalogItemInStockQty = catalogItemInStockQty;
	}

	public void setCatalogItemType(String catalogItemType) {
		this.catalogItemType = catalogItemType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(catalogItem, catalogItemDescription, catalogItemInStockQty, catalogItemName,
				catalogItemPrice, catalogItemType, loginPassword, loginUsername);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddCatalogItemDTO other = (AddCatalogItemDTO) obj;
		return Objects.equals(catalogItem, other.catalogItem)
				&& Objects.equals(catalogItemDescription, other.catalogItemDescription)
				&& Objects.equals(catalogItemInStockQty, other.catalogItemInStockQty)
				&& Objects.equals(catalogItemName, other.catalogItemName)
				&& Objects.equals(catalogItemPrice, other.catalogItemPrice)
				&& Objects.equals(catalogItemType, other.catalogItemType)
				&& Objects.equals(loginPassword, other.loginPassword)
				&& Objects.equals(loginUsername, other.loginUsername);
	}

	@Override
	public String toString() {
		return "AddCatalogItemDTO [loginUsername=" + loginUsername + ", loginPassword=" + loginPassword
				+ ", catalogItemName=" + catalogItemName + ", catalogItem=" + catalogItem + ", catalogItemDescription="
				+ catalogItemDescription + ", catalogItemPrice=" + catalogItemPrice + ", catalogItemInStockQty="
				+ catalogItemInStockQty + ", catalogItemType=" + catalogItemType + "]";
	}


	
	
	
	
}// END Class
