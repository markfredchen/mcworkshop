// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.domain;

import org.apache.wicket.util.string.Strings;

import com.mcworkshop.common.domain.Contact;
import com.mcworkshop.common.sysenum.SystemEnumeration;

/**
 * @author $Author$
 * 
 */
public class CompanyContact extends Contact {

	private static final long serialVersionUID = 1L;
	private Gender gender;
	private String idCard;
	private String postalCode;
	private String address;
	private String fax;

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Long getGenderID() {
		return SystemEnumeration.getInstance().getIdByKey(this.gender);
	}

	public void setGenderID(Long genderID) {
		this.gender = SystemEnumeration.getInstance().getKeyById(Gender.class,
				genderID);
	}

	public boolean isEmpty() {
		return Strings.isEmpty(this.fullName) && Strings.isEmpty(this.idCard)
				&& Strings.isEmpty(this.postalCode)
				&& Strings.isEmpty(this.address) && Strings.isEmpty(this.phone)
				&& Strings.isEmpty(this.cellPhone)
				&& Strings.isEmpty(this.emailAddress)
				&& Strings.isEmpty(this.fax) && this.gender == null;
	}
}
