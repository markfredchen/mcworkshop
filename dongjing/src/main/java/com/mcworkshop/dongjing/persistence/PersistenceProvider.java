// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.persistence;

import com.mcworkshop.dongjing.domain.Contact;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author $Author$
 * 
 */
public class PersistenceProvider {

	public static String updateContact(Map<String, Object> params) {
		final Contact orginalContact = (Contact) params.get("orginalContact");
		final Contact updatedContact = (Contact) params.get("updatedContact");
		return new SQL() {
			{
				UPDATE("contacts");
				if (!orginalContact.getFullName().equals(
						updatedContact.getFullName())) {
					SET("fullName = '" + updatedContact.getFullName() + "'");
				}
				if (orginalContact.getPhone() != null
						&& !orginalContact.getPhone().equals(
								updatedContact.getPhone())) {
					SET("phone = '" + updatedContact.getPhone() + "'");
				}
				if (orginalContact.getCellPhone() != null
						&& !orginalContact.getCellPhone().equals(
								updatedContact.getCellPhone())) {
					SET("cellPhone = '" + updatedContact.getCellPhone() + "'");
				}
				if (orginalContact.getEmailAddress() != null
						&& !orginalContact.getEmailAddress().equals(
								updatedContact.getEmailAddress())) {
					SET("emailAddress = '" + updatedContact.getEmailAddress()
							+ "'");
				}
				if (orginalContact.getTitle() != null
						&& !orginalContact.getTitle().equals(
								updatedContact.getTitle())) {
					SET("title = '" + updatedContact.getTitle() + "'");
				}
				if (orginalContact.getDepartment() != null
						&& !orginalContact.getDepartment().equals(
								updatedContact.getDepartment())) {
					SET("department = '" + updatedContact.getDepartment() + "'");
				}
				WHERE("contactID = " + updatedContact.getContactID());
			}
		}.toString();
	}
}
