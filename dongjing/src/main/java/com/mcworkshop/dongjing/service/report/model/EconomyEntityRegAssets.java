// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report.model;

import com.mcworkshop.common.domain.DomainObject;

/**
 * @author $Author$
 * 
 */
public class EconomyEntityRegAssets extends DomainObject {

	private static final long serialVersionUID = 1L;

	private Long industry;
	private Long commercial;
	private Long service;
	private Long construction;
	private Long houseHolding;

	public Long getIndustry() {
		return industry;
	}

	public Long getCommercial() {
		return commercial;
	}

	public Long getService() {
		return service;
	}

	public Long getConstruction() {
		return construction;
	}

	public Long getHouseHolding() {
		return houseHolding;
	}

	public void setIndustry(Long industry) {
		this.industry = industry;
	}

	public void setCommercial(Long commercial) {
		this.commercial = commercial;
	}

	public void setService(Long service) {
		this.service = service;
	}

	public void setConstruction(Long construction) {
		this.construction = construction;
	}

	public void setHouseHolding(Long houseHolding) {
		this.houseHolding = houseHolding;
	}

}
