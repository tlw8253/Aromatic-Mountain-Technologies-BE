package com.amt.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.amt.model.OrderedItem;

//DTO - Data Transfer Object
//This is to be used in the controller class to get parameters by body
public class AddOrderDTO {

	private String orderAmount = "";
	private List<AddOrderedItemDTO> lstOrderedItem;

	
	public AddOrderDTO() {
		super();
		lstOrderedItem = new ArrayList<AddOrderedItemDTO>();
	}

	public AddOrderDTO(String orderAmount, List<AddOrderedItemDTO> lstOrderedItem) {
		this.orderAmount = orderAmount;
		this.lstOrderedItem = lstOrderedItem;		
	}

	
	public String getOrderAmount() {
		return orderAmount;
	}


	public List<AddOrderedItemDTO> getLstOrderedItem() {
		return lstOrderedItem;
	}


	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}


	public void setLstOrderedItem(List<AddOrderedItemDTO> lstOrderedItem) {
		this.lstOrderedItem = lstOrderedItem;
	}


	@Override
	public int hashCode() {
		return Objects.hash(lstOrderedItem, orderAmount);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddOrderDTO other = (AddOrderDTO) obj;
		return Objects.equals(lstOrderedItem, other.lstOrderedItem) && Objects.equals(orderAmount, other.orderAmount);
	}


	@Override
	public String toString() {
		return "AddOrderDTO [orderAmount=" + orderAmount + ", lstOrderedItem=" + lstOrderedItem + "]";
	}



	
	
}//END Class
