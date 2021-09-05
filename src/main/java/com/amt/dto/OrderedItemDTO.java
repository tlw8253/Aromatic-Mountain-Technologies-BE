package com.amt.dto;


import java.util.Objects;

import com.amt.app.Constants;
import com.amt.model.Order;

//DTO - Data Transfer Object
//This is to be used in passing the full dto to the dao
public class OrderedItemDTO implements Constants {

	private Order order;
	private String catalogItemName;
	private Double orderItemPrice;
	private int orderItemQty;


		
	public OrderedItemDTO() {
		super();
	}

	public OrderedItemDTO (Order order, String catalogItemName, Double orderItemPrice, int orderItemQty) {
		this.order = order;
		this.catalogItemName = catalogItemName;
		this.orderItemPrice = orderItemPrice;
		this.orderItemQty = orderItemQty;		
	}

	public Order getOrder() {
		return order;
	}

	public String getCatalogItemName() {
		return catalogItemName;
	}

	public Double getOrderItemPrice() {
		return orderItemPrice;
	}

	public int getOrderItemQty() {
		return orderItemQty;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setCatalogItemName(String catalogItemName) {
		this.catalogItemName = catalogItemName;
	}

	public void setOrderItemPrice(Double orderItemPrice) {
		this.orderItemPrice = orderItemPrice;
	}

	public void setOrderItemQty(int orderItemQty) {
		this.orderItemQty = orderItemQty;
	}

	@Override
	public int hashCode() {
		return Objects.hash(catalogItemName, order, orderItemPrice, orderItemQty);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderedItemDTO other = (OrderedItemDTO) obj;
		return Objects.equals(catalogItemName, other.catalogItemName) && Objects.equals(order, other.order) && Objects.equals(orderItemPrice, other.orderItemPrice)
				&& orderItemQty == other.orderItemQty;
	}

	@Override
	public String toString() {
		return "OrderedItemDTO [order=" + order + ", catalogItemName=" + catalogItemName + ", orderItemPrice=" + orderItemPrice + ", orderItemQty=" + orderItemQty + "]";
	}



	
	
	
	
}//END Class
