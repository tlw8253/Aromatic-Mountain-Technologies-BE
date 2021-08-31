package com.amt.model;

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


public class OrderItems implements Constants {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = csOrderItemsTblOrderItemsId)
	private int orderItemsId = 0;

	@Column(name = csOrderTblOrderId)
	private int orderId = 0;
	
	@Column(name = csOrderItemsTblOrderPrice)
	private double orderPrice = 0.0;
	
	@ManyToOne
	@JoinColumn(name = csCatalogTblCatalogId)	//, nullable = false) // 
	private CatalogItem catalogItem;

	@ManyToOne
	@JoinColumn(name = csUserTblId)	//, nullable = false) // 
	private User Customer;
	
	public OrderItems() {
		// TODO Auto-generated constructor stub
	}

}
