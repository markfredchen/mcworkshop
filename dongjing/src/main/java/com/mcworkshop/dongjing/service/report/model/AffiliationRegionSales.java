// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report.model;

import com.mcworkshop.common.domain.DomainObject;

/**
 * @author $Author$
 * 
 */
public class AffiliationRegionSales extends DomainObject {

	private static final long serialVersionUID = 1L;
	private Double regSales;
	private Double localSales;
	private Double mycSales;

	public Double getRegSales() {
		return regSales;
	}

	public void setRegSales(Double regSales) {
		this.regSales = regSales;
	}

	public Double getLocalSales() {
		return localSales;
	}

	public void setLocalSales(Double localSales) {
		this.localSales = localSales;
	}

	public Double getMycSales() {
		return mycSales;
	}

	public void setMycSales(Double mycSales) {
		this.mycSales = mycSales;
	}

}
