// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.persistence.search;

import com.mcworkshop.common.search.SearchField;

/**
 * @author $Author$
 * 
 */
public enum AgricultureInfoSearchField implements SearchField {

	AGRICULTURE_ID("agriID"), NAME("farmerName"), AREA("area"), CONTACT_NUMBER(
			"contactNumber");

	private String value;

	private AgricultureInfoSearchField(String value) {
		this.value = value;
	}

	@Override
	public String value() {
		return value;
	}
}
