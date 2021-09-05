package com.amt.dao;

import java.sql.SQLException;
import java.util.List;

import com.amt.dto.OrderedItemDTO;
import com.amt.model.OrderedItem;




//DAO - Data Access Object
public interface OrderedItemDAO {

	public abstract OrderedItem addNewOrderItem(OrderedItemDTO objOrderItemDTO) throws SQLException;

}
