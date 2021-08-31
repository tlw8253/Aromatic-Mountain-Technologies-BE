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
@Table(name = Constants.csCatalogTypeTable)


public class CatalogType implements Constants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = csCatalogTypeTblCatalogTypeId)
	private int catalogTypeId = 0;

	@Column(name = csCatalogTypeTblCatalogType, length = 50, nullable = false, unique = true)
	private String catalogType = "";

	@Column(name = csCatalogTypeTblCatalogTypeDesc, length = 150, nullable = false)
	private String catalogTypeDesc = "";

	
	public CatalogType() {
		super();
	}

	
	public CatalogType(String catalogType, String catalogTypeDesc) {
		this.catalogType = catalogType;
		this.catalogTypeDesc = catalogTypeDesc;
	}


	public int getCatalogTypeId() {
		return catalogTypeId;
	}


	public String getCatalogType() {
		return catalogType;
	}


	public String getCatalogTypeDesc() {
		return catalogTypeDesc;
	}


	public void setCatalogTypeId(int catalogTypeId) {
		this.catalogTypeId = catalogTypeId;
	}


	public void setCatalogType(String catalogType) {
		this.catalogType = catalogType;
	}


	public void setCatalogTypeDesc(String catalogTypeDesc) {
		this.catalogTypeDesc = catalogTypeDesc;
	}


	@Override
	public int hashCode() {
		return Objects.hash(catalogType, catalogTypeDesc, catalogTypeId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatalogType other = (CatalogType) obj;
		return Objects.equals(catalogType, other.catalogType) && Objects.equals(catalogTypeDesc, other.catalogTypeDesc)
				&& catalogTypeId == other.catalogTypeId;
	}


	@Override
	public String toString() {
		return "CatalogType [catalogTypeId=" + catalogTypeId + ", catalogType=" + catalogType + ", catalogTypeDesc="
				+ catalogTypeDesc + "]";
	}

	
	
	
	
}
