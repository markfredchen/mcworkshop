// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.persistence.search;

import com.mcworkshop.common.search.SearchField;

/**
 * @author $Author$
 * 
 */
public enum ProjectSearchField implements SearchField {
	PROJECT_ID("projectID"), NAME("name"), STATUS("statusID");

	private String value;

	private ProjectSearchField(String value) {
		this.value = value;
	}

	@Override
	public String value() {
		return value;
	}
}
