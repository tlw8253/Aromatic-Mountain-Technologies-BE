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
	private int orderItemId;

	@Column(name = csOrderItemsTblOrderPrice)
	private double orderItemPrice;
	
	@Column(name = csOrderItemsTblItemQty)
	private int orderItemQty;

	@ManyToOne
	@JoinColumn(name = csOrderTblOrderId, nullable = false) // 
	private Order order;

	@ManyToOne
	@JoinColumn(name = csCatalogItemTblCatalogItemId, nullable = false) // 
	private CatalogItem catalogItem;


	public OrderedItem() {
		super();
		orderItemId = 0;
		order = new Order();
		orderItemPrice = 0.0;
		orderItemQty = 0;
		catalogItem = new CatalogItem();
	}
	
	public OrderedItem(Double orderPrice, int orderQty) {
		this.orderItemPrice = orderPrice;
		this.orderItemQty = orderQty;
	}

	public OrderedItem(Double orderPrice, int orderQty, Order order, CatalogItem catalogItem) {
		this.orderItemPrice = orderPrice;
		this.orderItemQty = orderQty;
		this.order = order;
		this.catalogItem = catalogItem;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public double getOrderPrice() {
		return orderItemPrice;
	}

	public int getOrderQty() {
		return orderItemQty;
	}

	public Order getOrder() {
		return order;
	}

	public CatalogItem getCatalogItem() {
		return catalogItem;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderItemPrice = orderPrice;
	}

	public void setOrderQty(int orderQty) {
		this.orderItemQty = orderQty;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setCatalogItem(CatalogItem catalogItem) {
		this.catalogItem = catalogItem;
	}

	@Override
	public int hashCode() {
		return Objects.hash(catalogItem, order, orderItemId, orderItemPrice, orderItemQty);
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
		return Objects.equals(catalogItem, other.catalogItem) && Objects.equals(order, other.order)
				&& orderItemId == other.orderItemId
				&& Double.doubleToLongBits(orderItemPrice) == Double.doubleToLongBits(other.orderItemPrice)
				&& orderItemQty == other.orderItemQty;
	}

	@Override
	public String toString() {
		return "OrderedItem [orderItemId=" + orderItemId + ", orderPrice=" + orderItemPrice + ", orderQty=" + orderItemQty
				+ ", order=" + order + ", catalogItem=" + catalogItem + "]";
	}


	


	
	
}
