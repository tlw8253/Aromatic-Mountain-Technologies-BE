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
@Table(name = Constants.csCatalogTable)

public class CatalogItem implements Constants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = csCatalogTblCatalogId)
	private int catalogId = 0;

	@Column(name = csCatalogItem, length = 50, nullable = false)
	private String item = "";
	
	@Column(name = csCatalogItemDesc, length = 255, nullable = false)
	private String itemDescription = "";
	
	@Column(name = csCatalogItemPrice, length = 255, nullable = false)
	private double itemPrice = 0.0;
	
	@ManyToOne
	@JoinColumn(name = csCatalogTblCatalogTypeId, nullable = false)
	private CatalogType catalogType;

	
	public CatalogItem() {
		super();
	}

}
