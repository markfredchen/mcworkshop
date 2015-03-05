// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report;

import com.mcworkshop.common.domain.DomainObject;
import com.mcworkshop.dongjing.domain.TaxData;

/**
 * @author $Author$
 * 
 */
public class LocalExternalTaxDetail extends DomainObject {
	private static final long serialVersionUID = 1L;

	TaxData monthData;
	TaxData yearData;

	public TaxData getMonthData() {
		return monthData;
	}

	public TaxData getYearData() {
		return yearData;
	}

	public void setMonthData(TaxData monthData) {
		this.monthData = monthData;
	}

	public void setYearData(TaxData yearData) {
		this.yearData = yearData;
	}

}
