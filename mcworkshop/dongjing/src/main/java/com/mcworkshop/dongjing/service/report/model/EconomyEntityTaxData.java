// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report.model;

import com.mcworkshop.common.domain.DomainObject;
import com.mcworkshop.dongjing.domain.TaxData;

/**
 * @author $Author$
 * 
 */
public class EconomyEntityTaxData extends DomainObject {

	private static final long serialVersionUID = 1L;

	private TaxData industyData;
	private TaxData commercialData;
	private TaxData serviceData;
	private TaxData constructionData;
	private TaxData houseHoldingData;
	private TaxData yearData;
	private TaxData lastYearData;

	private Long industryCount;
	private Long commercialCount;
	private Long constructCount;
	private Long serviceCount;
	private Long houseHoldingCount;

	private Long industryMonthCount;
	private Long commercialMonthCount;
	private Long constructMonthCount;
	private Long serviceMonthCount;
	private Long houseHoldingMonthCount;

	private Double industrySales;
	private Double commercialSales;
	private Double constructionSales;
	private Double serviceSales;
	private Double houseHoldingSales;

	private Double industryTax;
	private Double commercialTax;
	private Double constructionTax;
	private Double serviceTax;
	private Double houseHoldingTax;

	public TaxData getIndustyData() {
		return industyData;
	}

	public TaxData getCommercialData() {
		return commercialData;
	}

	public TaxData getServiceData() {
		return serviceData;
	}

	public TaxData getConstructionData() {
		return constructionData;
	}

	public TaxData getHouseHoldingData() {
		return houseHoldingData;
	}

	public TaxData getYearData() {
		return yearData;
	}

	public TaxData getLastYearData() {
		return lastYearData;
	}

	public Long getIndustryCount() {
		return industryCount;
	}

	public Long getCommercialCount() {
		return commercialCount;
	}

	public Long getConstructCount() {
		return constructCount;
	}

	public Long getServiceCount() {
		return serviceCount;
	}

	public Long getHouseHoldingCount() {
		return houseHoldingCount;
	}

	public Long getIndustryMonthCount() {
		return industryMonthCount;
	}

	public Long getCommercialMonthCount() {
		return commercialMonthCount;
	}

	public Long getConstructMonthCount() {
		return constructMonthCount;
	}

	public Long getServiceMonthCount() {
		return serviceMonthCount;
	}

	public Long getHouseHoldingMonthCount() {
		return houseHoldingMonthCount;
	}

	public void setIndustyData(TaxData industyData) {
		this.industyData = industyData;
	}

	public void setCommercialData(TaxData commercialData) {
		this.commercialData = commercialData;
	}

	public void setServiceData(TaxData serviceData) {
		this.serviceData = serviceData;
	}

	public void setConstructionData(TaxData constructionData) {
		this.constructionData = constructionData;
	}

	public void setHouseHoldingData(TaxData houseHoldingData) {
		this.houseHoldingData = houseHoldingData;
	}

	public void setYearData(TaxData yearData) {
		this.yearData = yearData;
	}

	public void setLastYearData(TaxData lastYearData) {
		this.lastYearData = lastYearData;
	}

	public void setIndustryCount(Long industryCount) {
		this.industryCount = industryCount;
	}

	public void setCommercialCount(Long commercialCount) {
		this.commercialCount = commercialCount;
	}

	public void setConstructCount(Long constructCount) {
		this.constructCount = constructCount;
	}

	public void setServiceCount(Long serviceCount) {
		this.serviceCount = serviceCount;
	}

	public void setHouseHoldingCount(Long houseHoldingCount) {
		this.houseHoldingCount = houseHoldingCount;
	}

	public void setIndustryMonthCount(Long industryMonthCount) {
		this.industryMonthCount = industryMonthCount;
	}

	public void setCommercialMonthCount(Long commercialMonthCount) {
		this.commercialMonthCount = commercialMonthCount;
	}

	public void setConstructMonthCount(Long constructMonthCount) {
		this.constructMonthCount = constructMonthCount;
	}

	public void setServiceMonthCount(Long serviceMonthCount) {
		this.serviceMonthCount = serviceMonthCount;
	}

	public void setHouseHoldingMonthCount(Long houseHoldingMonthCount) {
		this.houseHoldingMonthCount = houseHoldingMonthCount;
	}

	public Double getIndustrySales() {
		return industrySales;
	}

	public void setIndustrySales(Double industrySales) {
		this.industrySales = industrySales;
	}

	public Double getCommercialSales() {
		return commercialSales;
	}

	public void setCommercialSales(Double commercialSales) {
		this.commercialSales = commercialSales;
	}

	public Double getConstructionSales() {
		return constructionSales;
	}

	public void setConstructionSales(Double constructionSales) {
		this.constructionSales = constructionSales;
	}

	public Double getServiceSales() {
		return serviceSales;
	}

	public void setServiceSales(Double serviceSales) {
		this.serviceSales = serviceSales;
	}

	public Double getHouseHoldingSales() {
		return houseHoldingSales;
	}

	public void setHouseHoldingSales(Double houseHoldingSales) {
		this.houseHoldingSales = houseHoldingSales;
	}

	public Double getIndustryTax() {
		return industryTax;
	}

	public void setIndustryTax(Double industryTax) {
		this.industryTax = industryTax;
	}

	public Double getCommercialTax() {
		return commercialTax;
	}

	public void setCommercialTax(Double commercialTax) {
		this.commercialTax = commercialTax;
	}

	public Double getConstructionTax() {
		return constructionTax;
	}

	public void setConstructionTax(Double constructionTax) {
		this.constructionTax = constructionTax;
	}

	public Double getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(Double serviceTax) {
		this.serviceTax = serviceTax;
	}

	public Double getHouseHoldingTax() {
		return houseHoldingTax;
	}

	public void setHouseHoldingTax(Double houseHoldingTax) {
		this.houseHoldingTax = houseHoldingTax;
	}

}
