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

	@Insert("INSERT INTO securityInfo(securityInfoID, name, isEmergencyPassStandard, isSecurityStandardPassed, fireController, isEnvAssessPass, isDirtyWaterMgn, isDCProduceOrg, dcTypeID, elevator, crane, recreationFacility, indoorFacility, boiler, pressureContainer, specialOthers, companyHead, safeManagerName, electronist, boilist, electrowelder, climbConstructor, forklifter, driver, elevateOperator, trainingOther) " +
            "VALUES(#{securityInfoID}, #{name}, #{isEmergencyPassStandard}, #{isSecurityStandardPassed}, #{fireController}, #{isEnvAssessPass}, #{isDirtyWaterMgn}, #{isDCProduceOrg}, #{dcTypeID}, #{elevator}, #{crane}, #{recreationFacility}, #{indoorFacility}, #{boiler}, #{pressureContainer}, #{specialOthers}, #{companyHead}, #{safeManagerName}, #{electronist}, #{boilist}, #{electrowelder}, #{climbConstructor}, #{forklifter}, #{driver}, #{elevateOperator}, #{trainingOther})")
	void createSecurityInfo(Security security);

	@Update("UPDATE securityInfo SET securityInfoID = #{securityInfoID}, name = #{name}, isEmergencyPassStandard = #{isEmergencyPassStandard}, isSecurityStandardPassed = #{isSecurityStandardPassed}, fireController = #{fireController}, isEnvAssessPass = #{isEnvAssessPass}, isDirtyWaterMgn = #{isDirtyWaterMgn}, isDCProduceOrg = #{isDCProduceOrg}, dcTypeID = #{dcTypeID}, elevator = #{elevator}, crane = #{crane}, recreationFacility = #{recreationFacility}, indoorFacility = #{indoorFacility}, boiler = #{boiler}, pressureContainer = #{pressureContainer}, specialOthers = #{specialOthers}, companyHead = #{companyHead}, safeManagerName = #{safeManagerName}, electronist = #{electronist}, boilist = #{boilist}, electrowelder = #{electrowelder}, climbConstructor = #{climbConstructor}, forklifter = #{forklifter}, driver = #{driver}, elevateOperator = #{elevateOperator}, trainingOther = #{trainingOther}\n" +
            "WHERE securityInfoID = #{securityInfoID}")
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
