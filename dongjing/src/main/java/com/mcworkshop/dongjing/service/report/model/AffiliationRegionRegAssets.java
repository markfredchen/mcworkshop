// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: AffiliationRegionRegAssets.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.service.report.model;

import com.mcworkshop.common.domain.DomainObject;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class AffiliationRegionRegAssets extends DomainObject {

	private static final long serialVersionUID = 1L;
	private Long reg;
	private Long local;
	private Long myc;

	public Long getReg() {
		return reg;
	}

	public void setReg(Long reg) {
		this.reg = reg;
	}

	public Long getLocal() {
		return local;
	}

	public void setLocal(Long local) {
		this.local = local;
	}

	public Long getMyc() {
		return myc;
	}

	public void setMyc(Long myc) {
		this.myc = myc;
	}

}
