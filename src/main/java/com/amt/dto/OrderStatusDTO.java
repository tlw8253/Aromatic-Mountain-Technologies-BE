package com.amt.dto;

import com.amt.app.Constants;

public class OrderStatusDTO extends AddOrEditDTO implements Constants{
	//Class attributes are store in the parent in HashMap tables.	

	/*Stored in hashmaps in the super class
	//ers_reimbursement_status table
	private int OrderStatusId;
	private String OrderStatus;
	private String OrderStatusDesc;
	
	*/
	
	//
	//###
	public OrderStatusDTO() {
		super();
	}

	public OrderStatusDTO(String sOrderStatus, String sOrderStatusDesc) {
		setOrderStatus(sOrderStatus);
		setOrderStatusDescription(sOrderStatusDesc);
	}

	public String getOrderStatusId() {
		return super.getDataElement(csOrderStatusTblOrderStatusId);
	}
	public int getOrderStatusIdAsInt() {
		return super.getIntDataElement(csOrderStatusTblOrderStatusId);
	}

	public String getOrderStatus() {
		return super.getDataElement(csOrderStatusTblOrderStatus);
	}

	public String getOrderStatusDescription() {
		return super.getDataElement(csOrderStatusTblOrderStatusDesc);
	}

	public void setOrderStatusId(int OrderStatusId) {
		super.setDataElement(csOrderStatusTblOrderStatusId, OrderStatusId);
	}
	public void setOrderStatusId(String OrderStatusId) {
		super.setDataElement(csOrderStatusTblOrderStatusId, OrderStatusId);
	}

	public void setOrderStatus(String OrderStatus) {
		super.setDataElement(csOrderStatusTblOrderStatus, OrderStatus);
	}

	public void setOrderStatusDescription(String OrderStatusDesc) {
		super.setDataElement(csOrderStatusTblOrderStatusDesc, OrderStatusDesc);
	}

	@Override
	public String toString() {
		return super.toString();
	}


	
	
}
