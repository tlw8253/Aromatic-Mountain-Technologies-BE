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
@Table(name = Constants.csCatalogItemTable)

public class CatalogItem implements Constants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = csCatalogItemTblCatalogItemId)
	private int catalogItemId;

	@Column(name = csCatalogItemTblCatalogItem, length = 50, nullable = false)
	private String catalogItem;
	
	@Column(name = csCatalogItemTblCatalogItemDesc, length = ciDescriptionMaxLen, nullable = false)
	private String catalogItemDescription;
	
	@Column(name = csCatalogItemTblCatalogItemPrice, length = 255, nullable = false)
	private double catalogItemPrice;
	
	@Column(name = csCatalogItemTblCatalogItemInStockQty, length = 255, nullable = false)
	private int catalogItemInStockQty;

	@ManyToOne
	@JoinColumn(name = csCatalogItemTypeTblCatalogItemTypeId, nullable = false)
	private CatalogItemType catalogItemType;

	
	public CatalogItem() {
		super();
		catalogItemId = 0;
		catalogItem = "";
		catalogItemDescription = "";
		catalogItemPrice = 0.0;
		catalogItemInStockQty = 0;
		catalogItemType = new CatalogItemType();
	}
	
	public CatalogItem(String catalogItem, String catalogItemDescription, double catalogItemPrice, int catalogItemInStockQty) {
		this.catalogItem = catalogItem;
		this.catalogItemDescription = catalogItemDescription;
		this.catalogItemPrice = catalogItemPrice;
		this.catalogItemInStockQty = catalogItemInStockQty;
	}

	public CatalogItem(String catalogItem, String catalogItemDescription, double catalogItemPrice, int catalogItemInStockQty, CatalogItemType catalogItemType) {
		this.catalogItem = catalogItem;
		this.catalogItemDescription = catalogItemDescription;
		this.catalogItemPrice = catalogItemPrice;
		this.catalogItemInStockQty = catalogItemInStockQty;
		this.catalogItemType = catalogItemType;
	}

	public int getCatalogItemId() {
		return catalogItemId;
	}

	public String getCatalogItem() {
		return catalogItem;
	}

	public String getCatalogItemDescription() {
		return catalogItemDescription;
	}

	public double getCatalogItemPrice() {
		return catalogItemPrice;
	}

	public int getCatalogItemInStockQty() {
		return catalogItemInStockQty;
	}

	public CatalogItemType getCatalogItemType() {
		return catalogItemType;
	}

	public void setCatalogItemId(int catalogItemId) {
		this.catalogItemId = catalogItemId;
	}

	public void setCatalogItem(String catalogItem) {
		this.catalogItem = catalogItem;
	}

	public void setCatalogItemDescription(String catalogItemDescription) {
		this.catalogItemDescription = catalogItemDescription;
	}

	public void setCatalogItemPrice(double catalogItemPrice) {
		this.catalogItemPrice = catalogItemPrice;
	}

	public void setCatalogItemInStockQty(int catalogItemInStockQty) {
		this.catalogItemInStockQty = catalogItemInStockQty;
	}

	public void setCatalogItemType(CatalogItemType catalogItemType) {
		this.catalogItemType = catalogItemType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(catalogItem, catalogItemDescription, catalogItemId, catalogItemInStockQty, catalogItemPrice,
				catalogItemType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatalogItem other = (CatalogItem) obj;
		return Objects.equals(catalogItem, other.catalogItem)
				&& Objects.equals(catalogItemDescription, other.catalogItemDescription)
				&& catalogItemId == other.catalogItemId && catalogItemInStockQty == other.catalogItemInStockQty
				&& Double.doubleToLongBits(catalogItemPrice) == Double.doubleToLongBits(other.catalogItemPrice)
				&& Objects.equals(catalogItemType, other.catalogItemType);
	}

	@Override
	public String toString() {
		return "CatalogItem [catalogItemId=" + catalogItemId + ", catalogItem=" + catalogItem
				+ ", catalogItemDescription=" + catalogItemDescription + ", catalogItemPrice=" + catalogItemPrice
				+ ", catalogItemInStockQty=" + catalogItemInStockQty + ", catalogItemType=" + catalogItemType + "]";
	}

	
	
	
	
}
