package com.amt.dao;

import java.sql.SQLException;

import com.amt.dto.OrderDTO;
import com.amt.model.Order;




//DAO - Data Access Object
public interface OrderDAO {

	public abstract Order addNewOrder(OrderDTO objOrderDTO) throws SQLException;

}
