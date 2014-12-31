// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.domain;

import java.util.Date;

import com.mcworkshop.common.domain.DomainObject;
import com.mcworkshop.common.sysenum.SystemEnumeration;

/**
 * @author $Author$
 * 
 */
public class Security extends DomainObject {

	private static final long serialVersionUID = 1L;

	private Long securityInfoID;
	private String name;
	private EconomyEntity economyEntity;
	private String major;
	private String reportDepartment;
	private Date safeCertificateValidDate;
	private Boolean isContractedCompany;
	private String safeLevel;
	private Date assetDate;
	private String safeManageOrg;
	private Integer safeMemberFullTime;
	private Integer safeMemberParttime;
	private Integer fireControlMembers;
	private Date fireControlCertificateDate;
	private Boolean isEnvAssessPass;
	private String dirtyWaterMgn;
	private Boolean isPassStandard;
	private String safeNo;
	private Integer elevator;
	private Integer crane;
	private Integer recreationFacility;
	private Integer indoorFacility;
	private Integer boiler;
	private Integer pressureContainer;
	private Integer pressurePipe;
	private Integer pipeLength;
	private Integer specialOthers;

	private Date mgnCertValidDate;
	private String safeManagerName;
	private Date traningValidDate;
	private Integer electronist;
	private Integer boilist;
	private Integer electrowelder;
	private Integer climbConstructor;
	private Integer forklifter;
	private Integer driver;
	private Integer elevateOperator;
	private Integer trainingOther;
	private Integer employeeNumber;
	private Integer employeeTrainee;
	private Integer farmerNumber;
	private Integer farmerTrainee;
	private Boolean isDCProduceOrg;
	private DangerousChemistryType dcType;
	private String dcCategroy;
	private Integer dcNumber;

	public Long getSecurityInfoID() {
		return securityInfoID;
	}

	public void setSecurityInfoID(Long securityInfoID) {
		this.securityInfoID = securityInfoID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EconomyEntity getEconomyEntity() {
		return economyEntity;
	}

	public void setEconomyEntity(EconomyEntity economyEntity) {
		this.economyEntity = economyEntity;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getReportDepartment() {
		return reportDepartment;
	}

	public void setReportDepartment(String reportDepartment) {
		this.reportDepartment = reportDepartment;
	}

	public Date getSafeCertificateValidDate() {
		return safeCertificateValidDate;
	}

	public void setSafeCertificateValidDate(Date safeCertificateValidDate) {
		this.safeCertificateValidDate = safeCertificateValidDate;
	}

	public Boolean getIsContractedCompany() {
		return isContractedCompany;
	}

	public void setIsContractedCompany(Boolean isContractedCompany) {
		this.isContractedCompany = isContractedCompany;
	}

	public String getSafeLevel() {
		return safeLevel;
	}

	public void setSafeLevel(String safeLevel) {
		this.safeLevel = safeLevel;
	}

	public Date getAssetDate() {
		return assetDate;
	}

	public void setAssetDate(Date assetDate) {
		this.assetDate = assetDate;
	}

	public String getSafeManageOrg() {
		return safeManageOrg;
	}

	public void setSafeManageOrg(String safeManageOrg) {
		this.safeManageOrg = safeManageOrg;
	}

	public Integer getSafeMemberFullTime() {
		return safeMemberFullTime;
	}

	public void setSafeMemberFullTime(Integer safeMemberFullTime) {
		this.safeMemberFullTime = safeMemberFullTime;
	}

	public Integer getSafeMemberParttime() {
		return safeMemberParttime;
	}

	public void setSafeMemberParttime(Integer safeMemberParttime) {
		this.safeMemberParttime = safeMemberParttime;
	}

	public Integer getFireControlMembers() {
		return fireControlMembers;
	}

	public void setFireControlMembers(Integer fireControlMembers) {
		this.fireControlMembers = fireControlMembers;
	}

	public Date getFireControlCertificateDate() {
		return fireControlCertificateDate;
	}

	public void setFireControlCertificateDate(Date fireControlCertificateDate) {
		this.fireControlCertificateDate = fireControlCertificateDate;
	}

	public Boolean getIsEnvAssessPass() {
		return isEnvAssessPass;
	}

	public void setIsEnvAssessPass(Boolean isEnvAssessPass) {
		this.isEnvAssessPass = isEnvAssessPass;
	}

	public String getDirtyWaterMgn() {
		return dirtyWaterMgn;
	}

	public void setDirtyWaterMgn(String dirtyWaterMgn) {
		this.dirtyWaterMgn = dirtyWaterMgn;
	}

	public Boolean getIsPassStandard() {
		return isPassStandard;
	}

	public void setIsPassStandard(Boolean isPassStandard) {
		this.isPassStandard = isPassStandard;
	}

	public String getSafeNo() {
		return safeNo;
	}

	public void setSafeNo(String safeNo) {
		this.safeNo = safeNo;
	}

	public Integer getElevator() {
		return elevator;
	}

	public void setElevator(Integer elevator) {
		this.elevator = elevator;
	}

	public Integer getCrane() {
		return crane;
	}

	public void setCrane(Integer crane) {
		this.crane = crane;
	}

	public Integer getRecreationFacility() {
		return recreationFacility;
	}

	public void setRecreationFacility(Integer recreationFacility) {
		this.recreationFacility = recreationFacility;
	}

	public Integer getIndoorFacility() {
		return indoorFacility;
	}

	public void setIndoorFacility(Integer indoorFacility) {
		this.indoorFacility = indoorFacility;
	}

	public Integer getBoiler() {
		return boiler;
	}

	public void setBoiler(Integer boiler) {
		this.boiler = boiler;
	}

	public Integer getPressureContainer() {
		return pressureContainer;
	}

	public void setPressureContainer(Integer pressureContainer) {
		this.pressureContainer = pressureContainer;
	}

	public Integer getPressurePipe() {
		return pressurePipe;
	}

	public void setPressurePipe(Integer pressurePipe) {
		this.pressurePipe = pressurePipe;
	}

	public Integer getPipeLength() {
		return pipeLength;
	}

	public void setPipeLength(Integer pipeLength) {
		this.pipeLength = pipeLength;
	}

	public Integer getSpecialOthers() {
		return specialOthers;
	}

	public void setSpecialOthers(Integer specialOthers) {
		this.specialOthers = specialOthers;
	}

	public Date getMgnCertValidDate() {
		return mgnCertValidDate;
	}

	public void setMgnCertValidDate(Date mgnCertValidDate) {
		this.mgnCertValidDate = mgnCertValidDate;
	}

	public String getSafeManagerName() {
		return safeManagerName;
	}

	public void setSafeManagerName(String safeManagerName) {
		this.safeManagerName = safeManagerName;
	}

	public Date getTraningValidDate() {
		return traningValidDate;
	}

	public void setTraningValidDate(Date traningValidDate) {
		this.traningValidDate = traningValidDate;
	}

	public Integer getElectronist() {
		return electronist;
	}

	public void setElectronist(Integer electronist) {
		this.electronist = electronist;
	}

	public Integer getBoilist() {
		return boilist;
	}

	public void setBoilist(Integer boilist) {
		this.boilist = boilist;
	}

	public Integer getElectrowelder() {
		return electrowelder;
	}

	public void setElectrowelder(Integer electrowelder) {
		this.electrowelder = electrowelder;
	}

	public Integer getClimbConstructor() {
		return climbConstructor;
	}

	public void setClimbConstructor(Integer climbConstructor) {
		this.climbConstructor = climbConstructor;
	}

	public Integer getForklifter() {
		return forklifter;
	}

	public void setForklifter(Integer forklifter) {
		this.forklifter = forklifter;
	}

	public Integer getDriver() {
		return driver;
	}

	public void setDriver(Integer driver) {
		this.driver = driver;
	}

	public Integer getElevateOperator() {
		return elevateOperator;
	}

	public void setElevateOperator(Integer elevateOperator) {
		this.elevateOperator = elevateOperator;
	}

	public Integer getTrainingOther() {
		return trainingOther;
	}

	public void setTrainingOther(Integer trainingOther) {
		this.trainingOther = trainingOther;
	}

	public Integer getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public Integer getEmployeeTrainee() {
		return employeeTrainee;
	}

	public void setEmployeeTrainee(Integer employeeTrainee) {
		this.employeeTrainee = employeeTrainee;
	}

	public Integer getFarmerNumber() {
		return farmerNumber;
	}

	public void setFarmerNumber(Integer farmerNumber) {
		this.farmerNumber = farmerNumber;
	}

	public Integer getFarmerTrainee() {
		return farmerTrainee;
	}

	public void setFarmerTrainee(Integer farmerTrainee) {
		this.farmerTrainee = farmerTrainee;
	}

	public Boolean getIsDCProduceOrg() {
		return isDCProduceOrg;
	}

	public void setIsDCProduceOrg(Boolean isDCProduceOrg) {
		this.isDCProduceOrg = isDCProduceOrg;
	}

	public DangerousChemistryType getDcType() {
		return dcType;
	}

	public void setDcType(DangerousChemistryType dcType) {
		this.dcType = dcType;
	}

	public String getDcCategroy() {
		return dcCategroy;
	}

	public void setDcCategroy(String dcCategroy) {
		this.dcCategroy = dcCategroy;
	}

	public Integer getDcNumber() {
		return dcNumber;
	}

	public void setDcNumber(Integer dcNumber) {
		this.dcNumber = dcNumber;
	}

	public Long getEconomyEntityID() {
		return SystemEnumeration.getInstance().getIdByKey(economyEntity);
	}

	public void setEconomyEntityID(Long economyEntityID) {
		this.economyEntity = SystemEnumeration.getInstance().getKeyById(
				EconomyEntity.class, economyEntityID);
	}

	public Long getDcTypeID() {
		return SystemEnumeration.getInstance().getIdByKey(dcType);
	}

	public void setDcTypeID(Long dcTypeID) {
		this.dcType = SystemEnumeration.getInstance().getKeyById(
				DangerousChemistryType.class, dcTypeID);
	}
}
