package com.amt.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.sql.rowset.serial.SerialBlob;

import com.amt.app.Constants;

@Entity
@Table(name = Constants.csOrderTable)

public class Order implements Constants {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = csOrderTblOrderId)
	private int orderId = 0;

	@Column(name = csOrderTblAmount, nullable = false)	
	private double orderAmount = 0.0;
		
	//this is set internally
	@Column(name = csOrderTblSubmitted, nullable = false)
	private Timestamp orderSubmitted;

	//this is set internally
	@Column(name = csOrderTblSent)
	private Timestamp orderSent;

	@ManyToOne
	@JoinColumn(name = csOrderStatusTblOrderStatusId)	//, nullable = false) // 
	private OrderStatus orderStatus;

	@ManyToOne
	@JoinColumn(name = csOrderItemsTblOrderItemsId)	//, nullable = false) // 
	private OrderedItem orderedItem;
	
	
	public Order() {
		//use when setting the timestamp values:
		//Timestamp objTimestamp = Timestamp.valueOf(LocalDateTime.now());
		
		orderSubmitted = new Timestamp(0);
		orderSent = new Timestamp(0);
	}

	public Order(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public int getOrderId() {
		return orderId;
	}

	public Timestamp getOrderSubmitted() {
		return orderSubmitted;
	}

	public Timestamp getOrderSent() {
		return orderSent;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public OrderedItem getOrderItems() {
		return orderedItem;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setOrderSubmitted(Timestamp orderSubmitted) {
		this.orderSubmitted = orderSubmitted;
	}

	public void setOrderSent(Timestamp orderSent) {
		this.orderSent = orderSent;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setOrderItems(OrderedItem orderItems) {
		this.orderedItem = orderItems;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderAmount, orderId, orderSent, orderStatus, orderSubmitted, orderedItem);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Double.doubleToLongBits(orderAmount) == Double.doubleToLongBits(other.orderAmount)
				&& orderId == other.orderId && Objects.equals(orderSent, other.orderSent)
				&& Objects.equals(orderStatus, other.orderStatus)
				&& Objects.equals(orderSubmitted, other.orderSubmitted)
				&& Objects.equals(orderedItem, other.orderedItem);
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderAmount=" + orderAmount + ", orderSubmitted=" + orderSubmitted
				+ ", orderSent=" + orderSent + ", orderStatus=" + orderStatus + ", orderedItem=" + orderedItem + "]";
	}


	
	
}
