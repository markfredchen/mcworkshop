package com.mcworkshop.dongjing.domain;

import java.util.Date;

import com.mcworkshop.common.domain.DomainObject;

/**
 * @author Markfred
 *
 */
public class RentStatus extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	private Long relationID;
	private Company rentor;
	private Company rentee;
	private String nonDJCompany;
	private Double area;
	private String address;
	private Date startDate;
	private Date endDate;
	
	public Long getRelationID() {
		return relationID;
	}
	public void setRelationID(Long relationID) {
		this.relationID = relationID;
	}
	public Company getRentor() {
		return rentor;
	}
	public void setRentor(Company rentor) {
		this.rentor = rentor;
	}
	public Company getRentee() {
		return rentee;
	}
	public void setRentee(Company rentee) {
		this.rentee = rentee;
	}
	
	public String getNonDJCompany() {
		return nonDJCompany;
	}
	public void setNonDJCompany(String nonDJCompany) {
		this.nonDJCompany = nonDJCompany;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
}
