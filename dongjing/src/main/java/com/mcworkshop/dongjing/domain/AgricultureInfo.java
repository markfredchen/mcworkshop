// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.domain;

import com.mcworkshop.common.domain.DomainObject;

/**
 * @author $Author$
 * 
 */
public class AgricultureInfo extends DomainObject {

	private static final long serialVersionUID = 1L;

	private Long agriID;
	private String farmerName;
	private String contactNumber;
	private Integer area;
	private String address;
	private Integer paddyArea;
	private Integer wheatArea;
	private Integer greenArea;
	private Integer assartArea;

	public Long getAgriID() {
		return agriID;
	}

	public void setAgriID(Long agriID) {
		this.agriID = agriID;
	}

	public String getFarmerName() {
		return farmerName;
	}

	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPaddyArea() {
		return paddyArea;
	}

	public void setPaddyArea(Integer paddyArea) {
		this.paddyArea = paddyArea;
	}

	public Integer getWheatArea() {
		return wheatArea;
	}

	public void setWheatArea(Integer wheatArea) {
		this.wheatArea = wheatArea;
	}

	public Integer getGreenArea() {
		return greenArea;
	}

	public void setGreenArea(Integer greenArea) {
		this.greenArea = greenArea;
	}

	public Integer getAssartArea() {
		return assartArea;
	}

	public void setAssartArea(Integer assartArea) {
		this.assartArea = assartArea;
	}

}
