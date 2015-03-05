// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: AffiliationRegionTaxData.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.service.report.model;

import com.mcworkshop.common.domain.DomainObject;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class AffiliationRegionTaxData extends DomainObject {

	private static final long serialVersionUID = 1L;

	private Double regTax;
	private Double localTax;
	private Double mycTax;

	public Double getRegTax() {
		return regTax;
	}

	public void setRegTax(Double regTax) {
		this.regTax = regTax;
	}

	public Double getLocalTax() {
		return localTax;
	}

	public void setLocalTax(Double localTax) {
		this.localTax = localTax;
	}

	public Double getMycTax() {
		return mycTax;
	}

	public void setMycTax(Double mycTax) {
		this.mycTax = mycTax;
	}

}
