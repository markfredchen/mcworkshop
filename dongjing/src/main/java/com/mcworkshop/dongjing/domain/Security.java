// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.domain;

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
    private Boolean isEmergencyPassStandard;
    private Boolean isSecurityStandardPassed;
    private String fireController;
    private Boolean isEnvAssessPass;
    private String isDirtyWaterMgn;
    private Boolean isDCProduceOrg;
    private DangerousChemistryType dcType;

    private Integer elevator;
    private Integer crane;
    private Integer recreationFacility;
    private Integer indoorFacility;
    private Integer boiler;
    private Integer pressureContainer;
    private Integer specialOthers;

    private String companyHead;
    private String safeManagerName;
    private Integer electronist;
    private Integer boilist;
    private Integer electrowelder;
    private Integer climbConstructor;
    private Integer forklifter;
    private Integer driver;
    private Integer elevateOperator;
    private Integer trainingOther;


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

    public Boolean getIsSecurityStandardPassed() {
        return isSecurityStandardPassed;
    }

    public void setIsSecurityStandardPassed(Boolean isSecurityStandardPassed) {
        this.isSecurityStandardPassed = isSecurityStandardPassed;
    }

	public Boolean getIsEnvAssessPass() {
		return isEnvAssessPass;
	}

	public void setIsEnvAssessPass(Boolean isEnvAssessPass) {
		this.isEnvAssessPass = isEnvAssessPass;
	}

	public Boolean getIsEmergencyPassStandard() {
		return isEmergencyPassStandard;
	}

	public void setIsEmergencyPassStandard(Boolean isEmergencyPassStandard) {
		this.isEmergencyPassStandard = isEmergencyPassStandard;
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

	public Boolean getIsDCProduceOrg() {
		return isDCProduceOrg;
	}

	public void setIsDCProduceOrg(Boolean isDCProduceOrg) {
		this.isDCProduceOrg = isDCProduceOrg;
	}

    public String getFireController() {
        return fireController;
    }

    public void setFireController(String fireController) {
        this.fireController = fireController;
    }

    public String getIsDirtyWaterMgn() {
        return isDirtyWaterMgn;
    }

    public void setIsDirtyWaterMgn(String isDirtyWaterMgn) {
        this.isDirtyWaterMgn = isDirtyWaterMgn;
    }

    public String getCompanyHead() {
        return companyHead;
    }

    public void setCompanyHead(String companyHead) {
        this.companyHead = companyHead;
    }

    public String getSafeManagerName() {
        return safeManagerName;
    }

    public void setSafeManagerName(String safeManagerName) {
        this.safeManagerName = safeManagerName;
    }

    public DangerousChemistryType getDcType() {
		return dcType;
	}

	public void setDcType(DangerousChemistryType dcType) {
		this.dcType = dcType;
	}

	public Long getDcTypeID() {
		return SystemEnumeration.getInstance().getIdByKey(dcType);
	}

	public void setDcTypeID(Long dcTypeID) {
		this.dcType = SystemEnumeration.getInstance().getKeyById(
				DangerousChemistryType.class, dcTypeID);
	}

    public Integer getSpecialOthers() {
        return specialOthers;
    }

    public void setSpecialOthers(Integer specialOthers) {
        this.specialOthers = specialOthers;
    }

}
