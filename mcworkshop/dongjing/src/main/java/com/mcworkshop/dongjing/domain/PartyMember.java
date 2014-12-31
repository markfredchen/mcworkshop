// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.domain;

import com.mcworkshop.common.domain.DomainObject;
import com.mcworkshop.common.sysenum.SystemEnumeration;

/**
 * @author $Author$
 * 
 */
public class PartyMember extends DomainObject {

	private static final long serialVersionUID = 1L;

	private Long memberID;
	private Long companyID;
	private String name;
	private Gender gender;
	private String education;

	public Long getMemberID() {
		return memberID;
	}

	public void setMemberID(Long memberID) {
		this.memberID = memberID;
	}

	public Long getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Long companyID) {
		this.companyID = companyID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Long getGenderID() {
		return SystemEnumeration.getInstance().getIdByKey(this.gender);
	}

	public void setGenderID(Long genderID) {
		this.gender = SystemEnumeration.getInstance().getKeyById(Gender.class,
				genderID);
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

}
