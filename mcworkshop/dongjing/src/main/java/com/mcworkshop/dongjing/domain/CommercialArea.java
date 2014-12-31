// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: CommercialArea.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.domain;

import com.mcworkshop.common.domain.DomainObject;
import com.mcworkshop.common.sysenum.SystemEnumeration;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class CommercialArea extends DomainObject {

	private static final long serialVersionUID = 1L;
	private Long commercialAreaID;
	private String name;
	private CommercialAreaType type;
	private String scope;
	private String residentialArea;
	private String residentNumbers;
	private String storePopulation;
	private String ownerEduProvinceAge;
	private String start;
	private String end;
	private String length;
	private String totalArea;
	private String ownArea;
	private String rentArea;
	private String spareArea;
	private String constructDescription;
	private Integer retailNumber;
	private String retailMainBrands;
	private Integer caterNumber;
	private String caterMainBrands;
	private Integer entertainNumber;
	private String entertainMainBrands;
	private Integer serviceNumber;
	private String serviceMainBrands;
	private Integer farmNumber;
	private String farmMainBrands;
	private Integer otherNumber;
	private String otherMainBrands;
	private String annualIncoming;
	private String annualTax;
	private String comment;
	private String totalCommercialEquipment;

	public Long getCommercialAreaID() {
		return commercialAreaID;
	}

	public void setCommercialAreaID(Long commercialAreaID) {
		this.commercialAreaID = commercialAreaID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CommercialAreaType getType() {
		return type;
	}

	public void setType(CommercialAreaType type) {
		this.type = type;
	}

	public Long getTypeID() {
		return SystemEnumeration.getInstance().getIdByKey(type);
	}

	public void setTypeID(Long typeID) {
		this.type = SystemEnumeration.getInstance().getKeyById(
				CommercialAreaType.class, typeID);
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getResidentialArea() {
		return residentialArea;
	}

	public void setResidentialArea(String residentialArea) {
		this.residentialArea = residentialArea;
	}

	public String getResidentNumbers() {
		return residentNumbers;
	}

	public void setResidentNumbers(String residentNumbers) {
		this.residentNumbers = residentNumbers;
	}

	public String getStorePopulation() {
		return storePopulation;
	}

	public void setStorePopulation(String storePopulation) {
		this.storePopulation = storePopulation;
	}

	public String getOwnerEduProvinceAge() {
		return ownerEduProvinceAge;
	}

	public void setOwnerEduProvinceAge(String ownerEduProvinceAge) {
		this.ownerEduProvinceAge = ownerEduProvinceAge;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(String totalArea) {
		this.totalArea = totalArea;
	}

	public String getOwnArea() {
		return ownArea;
	}

	public void setOwnArea(String ownArea) {
		this.ownArea = ownArea;
	}

	public String getRentArea() {
		return rentArea;
	}

	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}

	public String getSpareArea() {
		return spareArea;
	}

	public void setSpareArea(String spareArea) {
		this.spareArea = spareArea;
	}

	public String getConstructDescription() {
		return constructDescription;
	}

	public void setConstructDescription(String constructDescription) {
		this.constructDescription = constructDescription;
	}

	public Integer getRetailNumber() {
		return retailNumber;
	}

	public void setRetailNumber(Integer retailNumber) {
		this.retailNumber = retailNumber;
	}

	public String getRetailMainBrands() {
		return retailMainBrands;
	}

	public void setRetailMainBrands(String retailMainBrands) {
		this.retailMainBrands = retailMainBrands;
	}

	public Integer getCaterNumber() {
		return caterNumber;
	}

	public void setCaterNumber(Integer caterNumber) {
		this.caterNumber = caterNumber;
	}

	public String getCaterMainBrands() {
		return caterMainBrands;
	}

	public void setCaterMainBrands(String caterMainBrands) {
		this.caterMainBrands = caterMainBrands;
	}

	public Integer getEntertainNumber() {
		return entertainNumber;
	}

	public void setEntertainNumber(Integer entertainNumber) {
		this.entertainNumber = entertainNumber;
	}

	public String getEntertainMainBrands() {
		return entertainMainBrands;
	}

	public void setEntertainMainBrands(String entertainMainBrands) {
		this.entertainMainBrands = entertainMainBrands;
	}

	public Integer getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(Integer serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public String getServiceMainBrands() {
		return serviceMainBrands;
	}

	public void setServiceMainBrands(String serviceMainBrands) {
		this.serviceMainBrands = serviceMainBrands;
	}

	public Integer getFarmNumber() {
		return farmNumber;
	}

	public void setFarmNumber(Integer farmNumber) {
		this.farmNumber = farmNumber;
	}

	public String getFarmMainBrands() {
		return farmMainBrands;
	}

	public void setFarmMainBrands(String farmMainBrands) {
		this.farmMainBrands = farmMainBrands;
	}

	public Integer getOtherNumber() {
		return otherNumber;
	}

	public void setOtherNumber(Integer otherNumber) {
		this.otherNumber = otherNumber;
	}

	public String getOtherMainBrands() {
		return otherMainBrands;
	}

	public void setOtherMainBrands(String otherMainBrands) {
		this.otherMainBrands = otherMainBrands;
	}

	public String getAnnualIncoming() {
		return annualIncoming;
	}

	public void setAnnualIncoming(String annualIncoming) {
		this.annualIncoming = annualIncoming;
	}

	public String getAnnualTax() {
		return annualTax;
	}

	public void setAnnualTax(String annualTax) {
		this.annualTax = annualTax;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTotalCommercialEquipment() {
		return totalCommercialEquipment;
	}

	public void setTotalCommercialEquipment(String totalCommercialEquipment) {
		this.totalCommercialEquipment = totalCommercialEquipment;
	}

}
