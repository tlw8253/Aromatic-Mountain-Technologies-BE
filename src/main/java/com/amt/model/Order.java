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

//	@ManyToOne
//	@JoinColumn(name = csOrderItemsTblOrderItemsId)	//, nullable = false) // 
//	private OrderedItem orderedItem;
	
	@ManyToOne
	@JoinColumn(name = csUserTblUserId)	//, nullable = false) // 
	private User customer;

	
	public Order() {
		//use when setting the timestamp values:
		//Timestamp objTimestamp = Timestamp.valueOf(LocalDateTime.now());
		
		orderSubmitted = new Timestamp(0);
		orderSent = new Timestamp(0);
	}

	public Order(double orderAmount, Timestamp orderSubmitted, Timestamp orderSent, OrderStatus orderStatus, User customer) {
		this.orderAmount = orderAmount;
		this.orderSubmitted = orderSubmitted;
		this.orderSent = orderSent;
		this.orderStatus = orderStatus;
		this.customer = customer;
	}
	
	
	
	public int getOrderId() {
		return orderId;
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


	public OrderStatus getOrderStatus() {
		return orderStatus;
	}


	public User getCustomer() {
		return customer;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
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


	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}


	public void setCustomer(User customer) {
		this.customer = customer;
	}


	@Override
	public int hashCode() {
		return Objects.hash(customer, orderAmount, orderId, orderSent, orderStatus, orderSubmitted);
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
		return Objects.equals(customer, other.customer)
				&& Double.doubleToLongBits(orderAmount) == Double.doubleToLongBits(other.orderAmount)
				&& orderId == other.orderId && Objects.equals(orderSent, other.orderSent)
				&& Objects.equals(orderStatus, other.orderStatus)
				&& Objects.equals(orderSubmitted, other.orderSubmitted);
	}


	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderAmount=" + orderAmount + ", orderSubmitted=" + orderSubmitted
				+ ", orderSent=" + orderSent + ", orderStatus=" + orderStatus + ", customer=" + customer + "]";
	}



	
	
}
