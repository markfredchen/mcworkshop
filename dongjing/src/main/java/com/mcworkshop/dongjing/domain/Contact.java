// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.domain;

import org.apache.wicket.util.lang.Objects;

/**
 * @author $Author$
 * 
 */
public class Contact extends com.mcworkshop.common.domain.Contact {

	private static final long serialVersionUID = 1L;

	private String title;
	private String department;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Contact)) {
			return false;
		}
		Contact c = (Contact) obj;
		return Objects.equal(getContactID(), c.getContactID())
				&& Objects.equal(getFullName(), c.getFullName())
				&& Objects.equal(getPhone(), c.getPhone())
				&& Objects.equal(getCellPhone(), c.getCellPhone())
				&& Objects.equal(getEmailAddress(), c.getEmailAddress())
				&& Objects.equal(getTitle(), c.getTitle())
				&& Objects.equal(getDepartment(), c.getDepartment());
	}

}
