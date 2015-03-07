// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.domain;

import com.mcworkshop.common.domain.DomainObject;
import com.mcworkshop.common.sysenum.SystemEnumeration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author $Author$
 * 
 */
public class Project extends DomainObject {

	private static final long serialVersionUID = 1L;

	private Long projectID;
	private String name;
	private ProjectStatus status;
	private Boolean isCommercial;
	private Double area;
	private Double constructionArea;
	private String product;
	private Double domesticInvestment;
	private Double foreignInvestment;
	private Double fixInvestment;
	private Double totalInvestment;
	private Double sales;
	private Double profit;
	private Double tax;
	private Date auditPassDate;
	private ConstructType constructType;
	private Double actualArea;
	private String numberOfBuilding;
	private Date startDate;
	private Date endDate;
	private Date produceDate;
	private String progress;
	private String problem;
	private String contactor;
	private String phone;
	private String email;
	private String fax;
	private String comment;
	private List<Company> relatedCompanies = new ArrayList<Company>();

	public Long getProjectID() {
		return projectID;
	}

	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public Boolean isCommercial() {
		return isCommercial;
	}

	public void setCommercial(Boolean isCommercial) {
		this.isCommercial = isCommercial;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Double getConstructionArea() {
		return constructionArea;
	}

	public void setConstructionArea(Double constructionArea) {
		this.constructionArea = constructionArea;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Double getDomesticInvestment() {
		return domesticInvestment;
	}

	public void setDomesticInvestment(Double domesticInvestment) {
		this.domesticInvestment = domesticInvestment;
	}

	public Double getForeignInvestment() {
		return foreignInvestment;
	}

	public void setForeignInvestment(Double foreignInvestment) {
		this.foreignInvestment = foreignInvestment;
	}

	public Double getFixInvestment() {
		return fixInvestment;
	}

	public void setFixInvestment(Double fixInvestment) {
		this.fixInvestment = fixInvestment;
	}

	public Double getSales() {
		return sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Date getAuditPassDate() {
		return auditPassDate;
	}

	public void setAuditPassDate(Date auditPassDate) {
		this.auditPassDate = auditPassDate;
	}

	public ConstructType getConstructType() {
		return constructType;
	}

	public void setConstructType(ConstructType constructType) {
		this.constructType = constructType;
	}

	public Double getActualArea() {
		return actualArea;
	}

	public void setActualArea(Double actualArea) {
		this.actualArea = actualArea;
	}

	public String getNumberOfBuilding() {
		return numberOfBuilding;
	}

	public void setNumberOfBuilding(String numberOfBuilding) {
		this.numberOfBuilding = numberOfBuilding;
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

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getContactor() {
		return contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Double getTotalInvestment() {
		return totalInvestment;
	}

	public void setTotalInvestment(Double totalInvestment) {
		this.totalInvestment = totalInvestment;
	}

	public Long getStatusID() {
		return SystemEnumeration.getInstance().getIdByKey(this.status);
	}

	public void setStatusID(Long statusID) {
		this.status = SystemEnumeration.getInstance().getKeyById(
				ProjectStatus.class, statusID);
	}

	public Long getConstructTypeID() {
		return SystemEnumeration.getInstance().getIdByKey(this.constructType);
	}

	public void setConstructType(Long constructTypeID) {
		this.constructType = SystemEnumeration.getInstance().getKeyById(
				ConstructType.class, constructTypeID);
	}

	public List<Company> getRelatedCompanies() {
		return relatedCompanies;
	}

	public void setRelatedCompanies(List<Company> relatedCompanies) {
		this.relatedCompanies = relatedCompanies;
	}

}
