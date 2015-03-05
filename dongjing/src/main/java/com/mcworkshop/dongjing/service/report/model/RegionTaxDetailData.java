// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report.model;

import com.mcworkshop.common.domain.DomainObject;
import com.mcworkshop.dongjing.domain.TaxData;

/**
 * @author $Author$
 * 
 */
public class RegionTaxDetailData extends DomainObject {

	private static final long serialVersionUID = 1L;

	private TaxData privateTax;
	private TaxData publicTax;

	private TaxData lastPrivateTax;
	private TaxData lastPublicTax;

	public TaxData getPrivateTax() {
		return privateTax;
	}

	public TaxData getPublicTax() {
		return publicTax;
	}

	public void setPrivateTax(TaxData privateTax) {
		this.privateTax = privateTax;
	}

	public void setPublicTax(TaxData publicTax) {
		this.publicTax = publicTax;
	}

	public TaxData getLastPrivateTax() {
		return lastPrivateTax;
	}

	public void setLastPrivateTax(TaxData lastPrivateTax) {
		this.lastPrivateTax = lastPrivateTax;
	}

	public TaxData getLastPublicTax() {
		return lastPublicTax;
	}

	public void setLastPublicTax(TaxData lastPublicTax) {
		this.lastPublicTax = lastPublicTax;
	}

}
