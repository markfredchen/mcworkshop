// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.mcworkshop.common.search.SearchCriteria;
import com.mcworkshop.dongjing.domain.Security;

/**
 * @author $Author$
 * 
 */
public interface SecurityMapper {

	@Insert("INSERT INTO securityInfo(name, economyEntityID, major, reportDepartment, safeCertificateValidDate, isContractedCompany, safeLevel, assetDate, safeManageOrg, safeMemberFullTime, safeMemberParttime, fireControlMembers, fireControlCertificateDate, isEnvAssessPass, dirtyWaterMgn, isPassStandard, safeNo, elevator, crane, recreationFacility, indoorFacility, boiler, pressureContainer, pressurePipe, pipeLength, specialOthers, mgnCertValidDate, safeManagerName, traningValidDate, electronist, boilist, electrowelder, climbConstructor, forklifter, driver, elevateOperator, trainingOther, employeeNumber, employeeTrainee, farmerNumber, farmerTrainee, isDCProduceOrg, dcTypeID, dcCategroy, dcNumber)"
			+ " VALUES(#{name}, #{economyEntityID}, #{major}, #{reportDepartment}, #{safeCertificateValidDate}, #{isContractedCompany}, #{safeLevel}, #{assetDate}, #{safeManageOrg}, #{safeMemberFullTime}, #{safeMemberParttime}, #{fireControlMembers}, #{fireControlCertificateDate}, #{isEnvAssessPass}, #{dirtyWaterMgn}, #{isPassStandard}, #{safeNo}, #{elevator}, #{crane}, #{recreationFacility}, #{indoorFacility}, #{boiler}, #{pressureContainer}, #{pressurePipe}, #{pipeLength}, #{specialOthers}, #{mgnCertValidDate}, #{safeManagerName}, #{traningValidDate}, #{electronist}, #{boilist}, #{electrowelder}, #{climbConstructor}, #{forklifter}, #{driver}, #{elevateOperator}, #{trainingOther}, #{employeeNumber}, #{employeeTrainee}, #{farmerNumber}, #{farmerTrainee}, #{isDCProduceOrg}, #{dcTypeID}, #{dcCategroy}, #{dcNumber})")
	void createSecurityInfo(Security security);

	@Update("UPDATE securityInfo SET name = #{name}, economyEntityID = #{economyEntityID}, major = #{major}, reportDepartment = #{reportDepartment}, safeCertificateValidDate = #{safeCertificateValidDate}, isContractedCompany = #{isContractedCompany}, safeLevel = #{safeLevel}, assetDate = #{assetDate}, safeManageOrg = #{safeManageOrg}, safeMemberFullTime = #{safeMemberFullTime}, safeMemberParttime = #{safeMemberParttime}, fireControlMembers = #{fireControlMembers}, fireControlCertificateDate = #{fireControlCertificateDate}, isEnvAssessPass = #{isEnvAssessPass}, dirtyWaterMgn = #{dirtyWaterMgn}, isPassStandard = #{isPassStandard}, safeNo = #{safeNo}, elevator = #{elevator}, crane = #{crane}, recreationFacility = #{recreationFacility}, indoorFacility = #{indoorFacility}, boiler = #{boiler}, pressureContainer = #{pressureContainer}, pressurePipe = #{pressurePipe}, pipeLength = #{pipeLength}, specialOthers = #{specialOthers}, mgnCertValidDate = #{mgnCertValidDate}, safeManagerName = #{safeManagerName}, traningValidDate = #{traningValidDate}, electronist = #{electronist}, boilist = #{boilist}, electrowelder = #{electrowelder}, climbConstructor = #{climbConstructor}, forklifter = #{forklifter}, driver = #{driver}, elevateOperator = #{elevateOperator}, trainingOther = #{trainingOther}, employeeNumber = #{employeeNumber}, employeeTrainee = #{employeeTrainee}, farmerNumber = #{farmerNumber}, farmerTrainee = #{farmerTrainee}, isDCProduceOrg = #{isDCProduceOrg}, dcTypeID = #{dcTypeID}, dcCategroy = #{dcCategroy}, dcNumber = #{dcNumber}"
			+ " WHERE securityInfoID = #{securityInfoID}")
	void updateSecurityInfo(Security security);

	@Select("SELECT * FROM securityInfo WHERE securityInfoID = #{securityInfoID}")
	Security getSecurityInfo(@Param("securityInfoID") Long securityInfoID);

	@SelectProvider(type = CompanySearchProvider.class, method = "searchSecurityInfo")
	List<Map<String, Object>> searchSecurityInfo(
			@Param("searchCriteria") SearchCriteria criteria);

	@SelectProvider(type = CompanySearchProvider.class, method = "searchSecurityInfoTotal")
	int getSecurityInfoTotalCount(
			@Param("searchCriteria") SearchCriteria criteria);
}
