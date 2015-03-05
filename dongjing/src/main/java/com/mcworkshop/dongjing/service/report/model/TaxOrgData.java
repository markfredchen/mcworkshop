// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report.model;

import com.mcworkshop.common.domain.DomainObject;
import com.mcworkshop.dongjing.domain.TaxData;

/**
 * @author $Author$
 * 
 */
public class TaxOrgData extends DomainObject {

	private static final long serialVersionUID = 1L;
	private TaxData monthTaxOrgData;
	private TaxData monthHouseData;
	private TaxData yearTaxOrgData;
	private TaxData yearHouseData;

	public TaxData getMonthTaxOrgData() {
		return monthTaxOrgData;
	}

	public TaxData getMonthHouseData() {
		return monthHouseData;
	}

	public TaxData getYearTaxOrgData() {
		return yearTaxOrgData;
	}

	public TaxData getYearHouseData() {
		return yearHouseData;
	}

	public void setMonthTaxOrgData(TaxData monthTaxOrgData) {
		this.monthTaxOrgData = monthTaxOrgData;
	}

	public void setMonthHouseData(TaxData monthHouseData) {
		this.monthHouseData = monthHouseData;
	}

	public void setYearTaxOrgData(TaxData yearTaxOrgData) {
		this.yearTaxOrgData = yearTaxOrgData;
	}

	public void setYearHouseData(TaxData yearHouseData) {
		this.yearHouseData = yearHouseData;
	}
}
