package com.amt.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.amt.app.Constants;

@Entity
@Table(name = Constants.csCatalogItemTypeTable)


public class CatalogItemType implements Constants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = csCatalogItemTypeTblCatalogItemTypeId)
	private int catalogItemTypeId = 0;

	@Column(name = csCatalogItemTypeTblCatalogItemType, length = ciRoleTypeLen, nullable = false, unique = true)
	private String catalogItemType = "";

	@Column(name = csCatalogItemTypeTblCatalogItemTypeDesc, length = ciRoleTypeDescLen, nullable = false)
	private String catalogItemTypeDesc = "";

	
	public CatalogItemType() {
		super();
	}

	
	public CatalogItemType(String catalogType, String catalogTypeDesc) {
		this.catalogItemType = catalogType;
		this.catalogItemTypeDesc = catalogTypeDesc;
	}


	public int getCatalogTypeId() {
		return catalogItemTypeId;
	}


	public String getCatalogType() {
		return catalogItemType;
	}


	public String getCatalogTypeDesc() {
		return catalogItemTypeDesc;
	}


	public void setCatalogTypeId(int catalogTypeId) {
		this.catalogItemTypeId = catalogTypeId;
	}


	public void setCatalogType(String catalogType) {
		this.catalogItemType = catalogType;
	}


	public void setCatalogTypeDesc(String catalogTypeDesc) {
		this.catalogItemTypeDesc = catalogTypeDesc;
	}


	@Override
	public int hashCode() {
		return Objects.hash(catalogItemType, catalogItemTypeDesc, catalogItemTypeId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatalogItemType other = (CatalogItemType) obj;
		return Objects.equals(catalogItemType, other.catalogItemType) && Objects.equals(catalogItemTypeDesc, other.catalogItemTypeDesc)
				&& catalogItemTypeId == other.catalogItemTypeId;
	}


	@Override
	public String toString() {
		return "CatalogType [catalogTypeId=" + catalogItemTypeId + ", catalogType=" + catalogItemType + ", catalogTypeDesc="
				+ catalogItemTypeDesc + "]";
	}

	
	
	
	
}
