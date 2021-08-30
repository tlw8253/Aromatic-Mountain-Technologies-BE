package com.amt.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.amt.app.Constants;

@Entity
@Table(name = Constants.csOrderStatusTable)

public class OrderStatus implements Constants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = csOrderStatusTblOrderStatusId)
	private int orderStatusId = 0;

	@Column(name = csReimbStatusTblReimbStatus, length = 10, nullable = false, unique = true)
	private String orderStatus = "";

	@Column(name = csReimbStatusTblReimbStatusDesc, length = 150, nullable = false)
	private String orderStatusDesc = "";
	
	public OrderStatus() {
		super();
	}
	public OrderStatus(String orderStatus, String orderStatusDesc) {
		this.orderStatus = orderStatus;
		this.orderStatusDesc = orderStatusDesc;
	}
	public int getOrderStatusId() {
		return orderStatusId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public String getOrderStatusDesc() {
		return orderStatusDesc;
	}
	public void setOrderStatusId(int orderStatusId) {
		this.orderStatusId = orderStatusId;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public void setOrderStatusDesc(String orderStatusDesc) {
		this.orderStatusDesc = orderStatusDesc;
	}
	@Override
	public int hashCode() {
		return Objects.hash(orderStatus, orderStatusDesc, orderStatusId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderStatus other = (OrderStatus) obj;
		return Objects.equals(orderStatus, other.orderStatus) && Objects.equals(orderStatusDesc, other.orderStatusDesc)
				&& orderStatusId == other.orderStatusId;
	}
	@Override
	public String toString() {
		return "OrderStatus [orderStatusId=" + orderStatusId + ", orderStatus=" + orderStatus + ", orderStatusDesc="
				+ orderStatusDesc + "]";
	}



	
	
	
}
