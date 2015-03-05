// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: CompanyMember.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.domain;

import com.mcworkshop.common.domain.DomainObject;
import com.mcworkshop.common.sysenum.SystemEnumeration;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class CompanyMember extends DomainObject {

	private static final long serialVersionUID = 1L;

	private Long memberID;
	private Long companyID;
	private String name;
	private String position;
	private String produceMethod;
	private IdType idType;
	private String idNo;
	private String certificate;

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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getProduceMethod() {
		return produceMethod;
	}

	public void setProduceMethod(String produceMethod) {
		this.produceMethod = produceMethod;
	}

	public IdType getIdType() {
		return idType;
	}

	public void setIdType(IdType idType) {
		this.idType = idType;
	}

	public Long getIdTypeID() {
		return SystemEnumeration.getInstance().getIdByKey(this.idType);
	}

	public void setIdTypeID(Long idTypeID) {
		this.idType = SystemEnumeration.getInstance().getKeyById(IdType.class,
				idTypeID);
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

}
