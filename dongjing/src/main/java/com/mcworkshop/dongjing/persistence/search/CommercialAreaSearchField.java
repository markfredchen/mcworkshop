// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: CommercialAreaSearchField.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.persistence.search;

import com.mcworkshop.common.search.SearchField;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public enum CommercialAreaSearchField implements SearchField {

	AREA_ID("commercialAreaID"), NAME("name"), AREA_TYPE("typeID");

	private String value;

	private CommercialAreaSearchField(String value) {
		this.value = value;
	}

	@Override
	public String value() {
		return value;
	}
}
