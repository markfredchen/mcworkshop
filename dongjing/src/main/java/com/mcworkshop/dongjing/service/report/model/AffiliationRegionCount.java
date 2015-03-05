// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: AffiliationRegionCount.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.service.report.model;

import com.mcworkshop.common.domain.DomainObject;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class AffiliationRegionCount extends DomainObject {

	private static final long serialVersionUID = 1L;

	private Long regCount;
	private Long localCount;
	private Long mycCount;

	public Long getRegCount() {
		return regCount;
	}

	public void setRegCount(Long regCount) {
		this.regCount = regCount;
	}

	public Long getLocalCount() {
		return localCount;
	}

	public void setLocalCount(Long localCount) {
		this.localCount = localCount;
	}

	public Long getMycCount() {
		return mycCount;
	}

	public void setMycCount(Long mycCount) {
		this.mycCount = mycCount;
	}

}
