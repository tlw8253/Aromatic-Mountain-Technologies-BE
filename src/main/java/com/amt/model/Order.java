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

	//this is set internally
	@Column(name = csOrderTblSubmitted, nullable = false)
	private Timestamp orderSubmitted;

	@Column(name = csOrderTblAmount, nullable = false)	
	private double orderAmount = 0.0;
		
	@ManyToOne
	@JoinColumn(name = csOrderStatusTblOrderStatusId)	//, nullable = false) // 
	private OrderStatus orderStatus;

	@ManyToOne
	@JoinColumn(name = csOrderItemsTblOrderItemsId)	//, nullable = false) // 
	private OrderItems orderItems;
	


	
	public Order() {
		//use when setting the timestamp values:
		//Timestamp objTimestamp = Timestamp.valueOf(LocalDateTime.now());
		
		orderSubmitted = new Timestamp(0);
	}

	public Order(double dReimAmount, Timestamp tsReimbSubmitted, Timestamp tsReimbResolved, String sReimbDescription, SerialBlob sbReimbReceipt) {
	}


	
	
}
