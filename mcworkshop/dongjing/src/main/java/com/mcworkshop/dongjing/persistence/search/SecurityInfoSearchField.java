// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.persistence.search;

import com.mcworkshop.common.search.SearchField;

/**
 * @author $Author$
 * 
 */
public enum SecurityInfoSearchField implements SearchField {

	SECURITY_INFO_ID("securityInfoID"), NAME("name"), ECONOMY_ENTITY(
			"economyEntityID");

	private String value;

	private SecurityInfoSearchField(String value) {
		this.value = value;
	}

	@Override
	public String value() {
		return value;
	}
}
