// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.domain;

import org.apache.wicket.util.lang.Objects;

import com.mcworkshop.common.domain.Role;

/**
 * @author $Author$
 * 
 */
public class User extends com.mcworkshop.common.domain.User {

	private static final long serialVersionUID = 1L;
	private Contact contact = new Contact();
	private Role role;

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User u = (User) obj;
		return Objects.equal(getUsername(), u.getUsername())
				&& Objects.equal(getPassword(), u.getPassword())
				&& Objects.equal(getUserStatus(), u.getUserStatus())
				&& Objects.equal(getContact(), u.getContact())
				&& Objects.equal(getRole(), u.getRole());
	}
}
