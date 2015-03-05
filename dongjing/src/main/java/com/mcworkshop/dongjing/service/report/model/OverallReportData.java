// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report.model;

import java.io.Serializable;

/**
 * @author $Author$
 * 
 */
public class OverallReportData implements Serializable {

	private static final long serialVersionUID = 1L;

	private OverallMonthData monthData = new OverallMonthData();
	private OverallYearData yearData = new OverallYearData();
	private OverallYearData lastYearData = new OverallYearData();
	private long industryCount;
	private long commercialCount;
	private long constructCount;
	private long serviceCount;
	private long houseHoldingCount;
	private long industryMonthCount;
	private long commercialMonthCount;
	private long constructMonthCount;
	private long serviceMonthCount;
	private long houseHoldingMonthCount;

	public OverallMonthData getMonthData() {
		return monthData;
	}

	public OverallYearData getYearData() {
		return yearData;
	}

	public void setMonthData(OverallMonthData monthData) {
		this.monthData = monthData;
	}

	public void setYearData(OverallYearData yearData) {
		this.yearData = yearData;
	}

	public long getIndustryCount() {
		return industryCount;
	}

	public void setIndustryCount(long industryCount) {
		this.industryCount = industryCount;
	}

	public long getCommercialCount() {
		return commercialCount;
	}

	public void setCommercialCount(long commercialCount) {
		this.commercialCount = commercialCount;
	}

	public long getConstructCount() {
		return constructCount;
	}

	public void setConstructCount(long constructCount) {
		this.constructCount = constructCount;
	}

	public long getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(long serviceCount) {
		this.serviceCount = serviceCount;
	}

	public long getHouseHoldingCount() {
		return houseHoldingCount;
	}

	public void setHouseHoldingCount(long houseHoldingCount) {
		this.houseHoldingCount = houseHoldingCount;
	}

	public long getIndustryMonthCount() {
		return industryMonthCount;
	}

	public void setIndustryMonthCount(long industryMonthCount) {
		this.industryMonthCount = industryMonthCount;
	}

	public long getCommercialMonthCount() {
		return commercialMonthCount;
	}

	public void setCommercialMonthCount(long commercialMonthCount) {
		this.commercialMonthCount = commercialMonthCount;
	}

	public long getConstructMonthCount() {
		return constructMonthCount;
	}

	public void setConstructMonthCount(long constructMonthCount) {
		this.constructMonthCount = constructMonthCount;
	}

	public long getServiceMonthCount() {
		return serviceMonthCount;
	}

	public void setServiceMonthCount(long serviceMonthCount) {
		this.serviceMonthCount = serviceMonthCount;
	}

	public long getHouseHoldingMonthCount() {
		return houseHoldingMonthCount;
	}

	public void setHouseHoldingMonthCount(long houseHoldingMonthCount) {
		this.houseHoldingMonthCount = houseHoldingMonthCount;
	}

	public OverallYearData getLastYearData() {
		return lastYearData;
	}

	public void setLastYearData(OverallYearData lastYearData) {
		this.lastYearData = lastYearData;
	}

}
