// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report.model;

import com.mcworkshop.common.domain.DomainObject;

/**
 * @author $Author$
 * 
 */
public class EconomyEntityCount extends DomainObject {

	private static final long serialVersionUID = 1L;

	private Long industryCount;
	private Long commercialCount;
	private Long serviceCount;
	private Long constructionCount;
	private Long houseHoldingCount;

	public Long getIndustryCount() {
		return industryCount;
	}

	public Long getCommercialCount() {
		return commercialCount;
	}

	public Long getServiceCount() {
		return serviceCount;
	}

	public Long getConstructionCount() {
		return constructionCount;
	}

	public Long getHouseHoldingCount() {
		return houseHoldingCount;
	}

	public void setIndustryCount(Long industryCount) {
		this.industryCount = industryCount;
	}

	public void setCommercialCount(Long commercialCount) {
		this.commercialCount = commercialCount;
	}

	public void setServiceCount(Long serviceCount) {
		this.serviceCount = serviceCount;
	}

	public void setConstructionCount(Long constructionCount) {
		this.constructionCount = constructionCount;
	}

	public void setHouseHoldingCount(Long houseHoldingCount) {
		this.houseHoldingCount = houseHoldingCount;
	}

}
