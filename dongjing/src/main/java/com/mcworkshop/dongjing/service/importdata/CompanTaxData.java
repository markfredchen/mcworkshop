// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.importdata;

import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.domain.TaxData;

import java.io.Serializable;

/**
 * @author $Author$
 * 
 */
public class CompanTaxData implements Serializable {

	private static final long serialVersionUID = 1L;

	private Company company = new Company();
	private TaxData tax = new TaxData();

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public TaxData getTax() {
		return tax;
	}

	public void setTax(TaxData tax) {
		this.tax = tax;
	}

}
