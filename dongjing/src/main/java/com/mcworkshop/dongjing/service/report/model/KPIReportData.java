// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report.model;

import com.mcworkshop.common.domain.DomainObject;

/**
 * @author $Author$
 * 
 */
public class KPIReportData extends DomainObject {

	private static final long serialVersionUID = 1L;

	private EconomyEntityCount companies;
	private EconomyEntityCount checkedCompanies;
	private EconomyEntityCount monthNewAddedCompanies;
	private EconomyEntityCount yearNewAddedCompanies;
	private EconomyEntityCount lastYearNewAddedCompanies;
	private EconomyEntityCount monthNewSignedOffCompanies;
	private EconomyEntityCount yearNewSignedOffCompanies;
	private EconomyEntityRegAssets yearNewRegAssets;
	private EconomyEntityRegAssets allRegAssets;

	private EconomyEntityCount operatedCompanies;
	private EconomyEntityTaxData monthSales;
	private EconomyEntityTaxData yearSales;
	private EconomyEntityTaxData lastYearSales;

	private EconomyEntityCount checkedTaxCompanies;
	private EconomyEntityTaxData monthTax;
	private EconomyEntityTaxData yearTax;
	private EconomyEntityTaxData lastYearTax;
	private EconomyEntityTaxData incomingTax;

	private AffiliationRegionCount checkedAFCompanies;
	private AffiliationRegionCount yearNewAddedAFCompanies;
	private AffiliationRegionCount lastYearNewAddedAFCompanies;
	private AffiliationRegionCount yearNewSignedOffAFCompanies;
	private AffiliationRegionRegAssets allRegAssetsAF;
	private AffiliationRegionCount operatedAFCompanies;
	private AffiliationRegionSales yearSalesAF;
	private AffiliationRegionSales lastYearSalesAF;
	private AffiliationRegionCount checkedTaxAFCompanies;
	private AffiliationRegionTaxData monthAFTax;
	private AffiliationRegionTaxData lastYearAFTax;

	public EconomyEntityCount getCompanies() {
		return companies;
	}

	public void setCompanies(EconomyEntityCount companies) {
		this.companies = companies;
	}

	public EconomyEntityCount getCheckedCompanies() {
		return checkedCompanies;
	}

	public void setCheckedCompanies(EconomyEntityCount checkedCompanies) {
		this.checkedCompanies = checkedCompanies;
	}

	public EconomyEntityCount getMonthNewAddedCompanies() {
		return monthNewAddedCompanies;
	}

	public void setMonthNewAddedCompanies(
			EconomyEntityCount monthNewAddedCompanies) {
		this.monthNewAddedCompanies = monthNewAddedCompanies;
	}

	public EconomyEntityCount getYearNewAddedCompanies() {
		return yearNewAddedCompanies;
	}

	public void setYearNewAddedCompanies(
			EconomyEntityCount yearNewAddedCompanies) {
		this.yearNewAddedCompanies = yearNewAddedCompanies;
	}

	public EconomyEntityCount getMonthNewSignedOffCompanies() {
		return monthNewSignedOffCompanies;
	}

	public void setMonthNewSignedOffCompanies(
			EconomyEntityCount monthNewSignedOffCompanies) {
		this.monthNewSignedOffCompanies = monthNewSignedOffCompanies;
	}

	public EconomyEntityCount getYearNewSignedOffCompanies() {
		return yearNewSignedOffCompanies;
	}

	public void setYearNewSignedOffCompanies(
			EconomyEntityCount yearNewSignedOffCompanies) {
		this.yearNewSignedOffCompanies = yearNewSignedOffCompanies;
	}

	public EconomyEntityRegAssets getYearNewRegAssets() {
		return yearNewRegAssets;
	}

	public void setYearNewRegAssets(EconomyEntityRegAssets yearNewRegAssets) {
		this.yearNewRegAssets = yearNewRegAssets;
	}

	public EconomyEntityRegAssets getAllRegAssets() {
		return allRegAssets;
	}

	public void setAllRegAssets(EconomyEntityRegAssets allRegAssets) {
		this.allRegAssets = allRegAssets;
	}

	public EconomyEntityCount getOperatedCompanies() {
		return operatedCompanies;
	}

	public void setOperatedCompanies(EconomyEntityCount operatedCompanies) {
		this.operatedCompanies = operatedCompanies;
	}

	public EconomyEntityCount getCheckedTaxCompanies() {
		return checkedTaxCompanies;
	}

	public void setCheckedTaxCompanies(EconomyEntityCount checkedTaxCompanies) {
		this.checkedTaxCompanies = checkedTaxCompanies;
	}

	public EconomyEntityTaxData getMonthSales() {
		return monthSales;
	}

	public void setMonthSales(EconomyEntityTaxData monthSales) {
		this.monthSales = monthSales;
	}

	public EconomyEntityTaxData getYearSales() {
		return yearSales;
	}

	public void setYearSales(EconomyEntityTaxData yearSales) {
		this.yearSales = yearSales;
	}

	public EconomyEntityTaxData getMonthTax() {
		return monthTax;
	}

	public void setMonthTax(EconomyEntityTaxData monthTax) {
		this.monthTax = monthTax;
	}

	public EconomyEntityTaxData getYearTax() {
		return yearTax;
	}

	public void setYearTax(EconomyEntityTaxData yearTax) {
		this.yearTax = yearTax;
	}

	public EconomyEntityTaxData getIncomingTax() {
		return incomingTax;
	}

	public void setIncomingTax(EconomyEntityTaxData incomingTax) {
		this.incomingTax = incomingTax;
	}

	public EconomyEntityTaxData getLastYearSales() {
		return lastYearSales;
	}

	public void setLastYearSales(EconomyEntityTaxData lastYearSales) {
		this.lastYearSales = lastYearSales;
	}

	public EconomyEntityTaxData getLastYearTax() {
		return lastYearTax;
	}

	public void setLastYearTax(EconomyEntityTaxData lastYearTax) {
		this.lastYearTax = lastYearTax;
	}

	public EconomyEntityCount getLastYearNewAddedCompanies() {
		return lastYearNewAddedCompanies;
	}

	public void setLastYearNewAddedCompanies(
			EconomyEntityCount lastYearNewAddedCompanies) {
		this.lastYearNewAddedCompanies = lastYearNewAddedCompanies;
	}

	public AffiliationRegionCount getCheckedAFCompanies() {
		return checkedAFCompanies;
	}

	public void setCheckedAFCompanies(AffiliationRegionCount checkedAFCompanies) {
		this.checkedAFCompanies = checkedAFCompanies;
	}

	public AffiliationRegionCount getYearNewAddedAFCompanies() {
		return yearNewAddedAFCompanies;
	}

	public void setYearNewAddedAFCompanies(
			AffiliationRegionCount yearNewAddedAFCompanies) {
		this.yearNewAddedAFCompanies = yearNewAddedAFCompanies;
	}

	public AffiliationRegionCount getLastYearNewAddedAFCompanies() {
		return lastYearNewAddedAFCompanies;
	}

	public void setLastYearNewAddedAFCompanies(
			AffiliationRegionCount lastYearNewAddedAFCompanies) {
		this.lastYearNewAddedAFCompanies = lastYearNewAddedAFCompanies;
	}

	public AffiliationRegionCount getYearNewSignedOffAFCompanies() {
		return yearNewSignedOffAFCompanies;
	}

	public void setYearNewSignedOffAFCompanies(
			AffiliationRegionCount yearNewSignedOffAFCompanies) {
		this.yearNewSignedOffAFCompanies = yearNewSignedOffAFCompanies;
	}

	public AffiliationRegionRegAssets getAllRegAssetsAF() {
		return allRegAssetsAF;
	}

	public void setAllRegAssetsAF(AffiliationRegionRegAssets allRegAssetsAF) {
		this.allRegAssetsAF = allRegAssetsAF;
	}

	public AffiliationRegionCount getOperatedAFCompanies() {
		return operatedAFCompanies;
	}

	public void setOperatedAFCompanies(
			AffiliationRegionCount operatedAFCompanies) {
		this.operatedAFCompanies = operatedAFCompanies;
	}

	public AffiliationRegionSales getYearSalesAF() {
		return yearSalesAF;
	}

	public void setYearSalesAF(AffiliationRegionSales yearSalesAF) {
		this.yearSalesAF = yearSalesAF;
	}

	public AffiliationRegionSales getLastYearSalesAF() {
		return lastYearSalesAF;
	}

	public void setLastYearSalesAF(AffiliationRegionSales lastYearSalesAF) {
		this.lastYearSalesAF = lastYearSalesAF;
	}

	public AffiliationRegionCount getCheckedTaxAFCompanies() {
		return checkedTaxAFCompanies;
	}

	public void setCheckedTaxAFCompanies(
			AffiliationRegionCount checkedTaxAFCompanies) {
		this.checkedTaxAFCompanies = checkedTaxAFCompanies;
	}

	public AffiliationRegionTaxData getMonthAFTax() {
		return monthAFTax;
	}

	public void setMonthAFTax(AffiliationRegionTaxData monthAFTax) {
		this.monthAFTax = monthAFTax;
	}

	public AffiliationRegionTaxData getLastYearAFTax() {
		return lastYearAFTax;
	}

	public void setLastYearAFTax(AffiliationRegionTaxData lastYearAFTax) {
		this.lastYearAFTax = lastYearAFTax;
	}

}
