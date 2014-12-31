// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.persistence.search;

import com.mcworkshop.common.search.SearchField;

/**
 * @author $Author$
 * 
 */
public enum CompanySearchField implements SearchField {
	COMPANY_ID("companyID"), NAME("name"), ECONOMY_ENTITY("economyEntityID"), ECONOMY_NATURE(
			"economyNatureID"), AFFILIATE_BLOCK("regionID");

	private String value;

	private CompanySearchField(String value) {
		this.value = value;
	}

	@Override
	public String value() {
		return value;
	}

}
