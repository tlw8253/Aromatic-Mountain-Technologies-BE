package com.amt.dao;

import java.sql.SQLException;

import com.amt.dto.CatalogItemDTO;
import com.amt.model.CatalogItem;



//DAO - Data Access Object
public interface CatalogItemDAO {

	public abstract CatalogItem addNewCatalogItem(CatalogItemDTO objCatalogItemDTO) throws SQLException;

}
