package com.amt.dto;

import java.util.Objects;


//DTO - Data Transfer Object
//This is to be used in the controller class to get parameters by body
public class AddOrderedItemDTO {

	private String catalogItemName;
	private String orderItemPrice;
	private String orderItemQty;

	
	public AddOrderedItemDTO() {
		super();
	}

	public AddOrderedItemDTO(String catalogItemName, String orderItemPrice, String orderItemQty) {
		this.catalogItemName = catalogItemName;
		this.orderItemPrice = orderItemPrice;
		this.orderItemQty = orderItemQty;
	}

	public String getCatalogItemName() {
		return catalogItemName;
	}

	public String getOrderItemPrice() {
		return orderItemPrice;
	}

	public String getOrderItemQty() {
		return orderItemQty;
	}

	public void setCatalogItemName(String catalogItemName) {
		this.catalogItemName = catalogItemName;
	}

	public void setOrderItemPrice(String orderItemPrice) {
		this.orderItemPrice = orderItemPrice;
	}

	public void setOrderItemQty(String orderItemQty) {
		this.orderItemQty = orderItemQty;
	}

	@Override
	public int hashCode() {
		return Objects.hash(catalogItemName, orderItemPrice, orderItemQty);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddOrderedItemDTO other = (AddOrderedItemDTO) obj;
		return Objects.equals(catalogItemName, other.catalogItemName)
				&& Objects.equals(orderItemPrice, other.orderItemPrice)
				&& Objects.equals(orderItemQty, other.orderItemQty);
	}

	@Override
	public String toString() {
		return "AddOrderItemDTO [catalogItemName=" + catalogItemName + ", orderItemPrice=" + orderItemPrice
				+ ", orderItemQty=" + orderItemQty + "]";
	}


	
	
}//END Class
