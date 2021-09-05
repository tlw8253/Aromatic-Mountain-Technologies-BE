package com.amt.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.amt.app.Constants;
import com.amt.model.OrderedItem;

//DTO - Data Transfer Object
//This is to be used in passing the full dto to the dao
public class OrderDTO implements Constants {

	private String username;
	private double orderAmount;
	private Timestamp orderSubmitted;
	private Timestamp orderSent;
	private String orderStatus;
	private List<OrderedItem> lstOrderedItem;


		
	public OrderDTO() {
		super();
		lstOrderedItem = new ArrayList<OrderedItem>();
	}

	public OrderDTO (String username, double orderAmount, Timestamp orderSubmitted, Timestamp orderSent, String orderStatus) {
		this.username = username;
		this.orderAmount = orderAmount;
		this.orderSubmitted = orderSubmitted;
		this.orderSent = orderSent;
		this.orderStatus = orderStatus;
	}

	public String getUsername() {
		return username;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public Timestamp getOrderSubmitted() {
		return orderSubmitted;
	}

	public Timestamp getOrderSent() {
		return orderSent;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public List<OrderedItem> getLstOrderedItem() {
		return lstOrderedItem;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public void setOrderSubmitted(Timestamp orderSubmitted) {
		this.orderSubmitted = orderSubmitted;
	}

	public void setOrderSent(Timestamp orderSent) {
		this.orderSent = orderSent;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setLstOrderedItem(List<OrderedItem> lstOrderedItem) {
		this.lstOrderedItem = lstOrderedItem;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lstOrderedItem, orderAmount, orderSent, orderStatus, orderSubmitted, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDTO other = (OrderDTO) obj;
		return Objects.equals(lstOrderedItem, other.lstOrderedItem)
				&& Double.doubleToLongBits(orderAmount) == Double.doubleToLongBits(other.orderAmount)
				&& Objects.equals(orderSent, other.orderSent) && Objects.equals(orderStatus, other.orderStatus)
				&& Objects.equals(orderSubmitted, other.orderSubmitted) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "OrderDTO [username=" + username + ", orderAmount=" + orderAmount + ", orderSubmitted=" + orderSubmitted
				+ ", orderSent=" + orderSent + ", orderStatus=" + orderStatus + ", lstOrderedItem=" + lstOrderedItem
				+ "]";
	}


	
	
	
	
}//END Class
