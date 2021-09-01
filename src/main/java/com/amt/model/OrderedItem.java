package com.amt.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.amt.app.Constants;

@Entity
@Table(name = Constants.csOrderItemsTable)


public class OrderedItem implements Constants {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = csOrderItemsTblOrderItemsId)
	private int orderItemsId;

	@Column(name = csOrderTblOrderId)
	private int orderId;
	
	@Column(name = csOrderItemsTblOrderPrice)
	private double orderPrice;
	
	@Column(name = csOrderItemsTblItemQty)
	private int orderQty;

	@ManyToOne
	@JoinColumn(name = csCatalogItemTblCatalogItemId)	//, nullable = false) // 
	private CatalogItem catalogItem;

	@ManyToOne
	@JoinColumn(name = csUserTblId)	//, nullable = false) // 
	private User customer;
	
	public OrderedItem() {
		super();
		orderItemsId = 0;
		orderId = 0;
		orderPrice = 0.0;
		orderQty = 0;
		catalogItem = new CatalogItem();
		customer = new User();
	}
	
	public OrderedItem(Double orderPrice, int orderQty) {
		this.orderPrice = orderPrice;
		this.orderQty = orderQty;
	}

	public OrderedItem(Double orderPrice, int orderQty, CatalogItem catalogItem, User customer) {
		this.orderPrice = orderPrice;
		this.orderQty = orderQty;
		this.catalogItem = catalogItem;
		this.customer = customer;		
	}

	public int getOrderItemsId() {
		return orderItemsId;
	}

	public int getOrderId() {
		return orderId;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public CatalogItem getCatalogItem() {
		return catalogItem;
	}

	public User getCustomer() {
		return customer;
	}

	public void setOrderItemsId(int orderItemsId) {
		this.orderItemsId = orderItemsId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	public void setCatalogItem(CatalogItem catalogItem) {
		this.catalogItem = catalogItem;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(catalogItem, customer, orderId, orderItemsId, orderPrice, orderQty);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderedItem other = (OrderedItem) obj;
		return Objects.equals(catalogItem, other.catalogItem) && Objects.equals(customer, other.customer)
				&& orderId == other.orderId && orderItemsId == other.orderItemsId
				&& Double.doubleToLongBits(orderPrice) == Double.doubleToLongBits(other.orderPrice)
				&& orderQty == other.orderQty;
	}

	@Override
	public String toString() {
		return "OrderedItem [orderItemsId=" + orderItemsId + ", orderId=" + orderId + ", orderPrice=" + orderPrice
				+ ", orderQty=" + orderQty + ", catalogItem=" + catalogItem + ", customer=" + customer + "]";
	}
	

	
	
}
