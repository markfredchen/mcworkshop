// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.persistence;

import com.mcworkshop.common.search.SearchCriteria;
import com.mcworkshop.dongjing.domain.*;
import com.mcworkshop.dongjing.persistence.po.AnnualOutputPO;
import com.mcworkshop.dongjing.persistence.po.CompanyAreaPO;
import com.mcworkshop.dongjing.persistence.po.ProjectApplyStatus;
import com.mcworkshop.dongjing.persistence.po.RentStatusPO;
import com.mcworkshop.dongjing.service.report.model.OverallMonthData;
import com.mcworkshop.dongjing.service.report.model.OverallYearData;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author $Author$
 * 
 */
public interface DJServiceMapper {

	@Insert("INSERT INTO companySaleTaxData(year,month,companyID,industry,accSales,sales,vat,operateTax,expenseTax,domesticIncomingTax,foreignIncomingTax,housingTax,stampTax,trafficTax,landVAT,landUseTax,personalIncomingTax,constructionTax,veichleTax,riverTax,educationTax,cultureTax,otherTax,economyNatureID,economyEntityID,taxOrgID,affiliateRegionID, isMYC)"
			+ " VALUES(#{year}, #{month}, #{company.companyID}, #{industry}, #{accSales}, #{sales}, #{vat}, #{operateTax}, #{expenseTax}, #{domesticIncomingTax}, #{foreignIncomingTax}, #{housingTax}, #{stampTax}, #{trafficTax}, #{landVAT}, #{landUseTax}, #{personalIncomingTax}, #{constructionTax}, #{veichleTax}, #{riverTax}, #{educationTax}, #{cultureTax}, #{otherTax}, #{economyNatureID},#{economyEntityID}, #{taxOrgID}, #{affiliateRegionID}, #{isMYC})")
	void importTaxData(TaxData data);

	@Select("SELECT * FROM company WHERE establishDate BETWEEN #{startDate} AND #{endDate}")
	List<Company> getCompanyByEstablishDate(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	@Delete("DELETE FROM companySaleTaxData WHERE year = #{year} AND month = #{month}")
	void clearTaxDataByMonth(@Param("year") Integer year,
			@Param("month") Integer month);

	@Select("SELECT * FROM companySaleTaxData WHERE year = #{year} AND month = #{month}")
	List<TaxData> getMonthTaxReport(@Param("year") Integer year,
			@Param("month") Integer month);

	@Select("SELECT DISTINCT year, month FROM companySaleTaxData")
	List<Map<String, Integer>> getSaleTaxReportAvailableMonths();

	@Insert("INSERT INTO company(companyID, name, companyNo, commercialNo, organizationCode, docNo, registerAddress, operateAddress, registerAsset, branchNumber, regionID, commercialStatusID, taxStatusID, economyEntityID, economyNatureID, industryType, collectMethodID, affiliateRegionID, taxNatureID, taxTypeID, establishDate, materialDate, handleDate, issueDate, moveInDate, transferDate, taxCancellation, commercialCancellation, taxCollector, merchat, affliateMerchat, isEDeclaration, isReceiptBought, isNormalTaxer, certificationNo, companyIdentifier, infoCardNo, operationStartYear, operationEndYear, validStartYear, validEndYear, operationMethod, normalTaxerAuthDate, operationScope, remarkables, unifiedCode, taxMngCode, taxRegNo, purchasedBookNo, bank, bankAccount, taxAccount, ownerContactID, otherContactID, shareholderContactID, shareRatio, branches, taxOrgID, isA, isB, isC, isD, isE, isF, isG, isH, isI, isJ, isK, isL, isM, isN, aDate, bDate, cDate, dDate, eDate, fDate, gDate, hDate, iDate, jDate, kDate, lDate, mDate, nDate, isO, isP, isQ, numberOfO, numberOfP, numberOfQ, partyName, partyLeader, area, ownership, sleepingRoomNumbers, usageArea, rentArea, spareArea, companyArea, localEmployeeNumber, employeeNumber, landUsageID, isRent, factoryArea, factoryUsageArea, factoryRentArea, factoryControlArea, factoryActualArea, passDate, noOfDeveloper, devBudget, currencyID, isR, isS, isT, isU, isV, isW, rDate, sDate, tDate, uDate, vDate, wDate)" +
            " VALUES(#{companyID}, #{name}, #{companyNo}, #{commercialNo}, #{organizationCode}, #{docNo}, #{registerAddress}, #{operateAddress}, #{registerAsset}, #{branchNumber}, #{regionID}, #{commercialStatusID}, #{taxStatusID}, #{economyEntityID}, #{economyNatureID}, #{industryType}, #{collectMethodID}, #{affiliateRegionID}, #{taxNatureID}, #{taxTypeID}, #{establishDate}, #{materialDate}, #{handleDate}, #{issueDate}, #{moveInDate}, #{transferDate}, #{taxCancellation}, #{commercialCancellation}, #{taxCollector}, #{merchat}, #{affliateMerchat}, #{isEDeclaration}, #{isReceiptBought}, #{isNormalTaxer}, #{certificationNo}, #{companyIdentifier}, #{infoCardNo}, #{operationStartYear}, #{operationEndYear}, #{validStartYear}, #{validEndYear}, #{operationMethod}, #{normalTaxerAuthDate}, #{operationScope}, #{remarkables}, #{unifiedCode}, #{taxMngCode}, #{taxRegNo}, #{purchasedBookNo}, #{bank}, #{bankAccount}, #{taxAccount}, #{ownerContact.contactID}, #{otherContact.contactID}, #{shareholderContact.contactID}, #{shareRatio}, #{branches}, #{taxOrgID}, #{isA}, #{isB}, #{isC}, #{isD}, #{isE}, #{isF}, #{isG}, #{isH}, #{isI}, #{isJ}, #{isK}, #{isL}, #{isM}, #{isN}, #{aDate}, #{bDate}, #{cDate}, #{dDate}, #{eDate}, #{fDate}, #{gDate}, #{hDate}, #{iDate}, #{jDate}, #{kDate}, #{lDate}, #{mDate}, #{nDate}, #{isO}, #{isP}, #{isQ}, #{numberOfO}, #{numberOfP}, #{numberOfQ}, #{partyName}, #{partyLeader}, #{area}, #{ownership}, #{sleepingRoomNumbers}, #{usageArea}, #{rentArea}, #{spareArea}, #{companyArea}, #{localEmployeeNumber}, #{employeeNumber}, #{landUsageID}, #{isRent}, #{factoryArea}, #{factoryUsageArea}, #{factoryRentArea}, #{factoryControlArea}, #{factoryActualArea}, #{passDate}, #{noOfDeveloper}, #{devBudget}, #{currencyID}, #{isR}, #{isS}, #{isT}, #{isU}, #{isV}, #{isW}, #{rDate}, #{sDate}, #{tDate}, #{uDate}, #{vDate}, #{wDate})")
	@Options(useGeneratedKeys = true, keyProperty = "companyID")
	void createCompany(Company company);

	@Update("UPDATE company SET name = #{name}, companyNo = #{companyNo}, commercialNo = #{commercialNo}, organizationCode = #{organizationCode}, docNo = #{docNo}, registerAddress = #{registerAddress}, operateAddress = #{operateAddress}, registerAsset = #{registerAsset}, branchNumber = #{branchNumber}, regionID = #{regionID}, commercialStatusID = #{commercialStatusID}, taxStatusID = #{taxStatusID}, economyEntityID = #{economyEntityID}, economyNatureID = #{economyNatureID}, industryType = #{industryType}, collectMethodID = #{collectMethodID}, affiliateRegionID = #{affiliateRegionID}, taxNatureID = #{taxNatureID}, taxTypeID = #{taxTypeID}, establishDate = #{establishDate}, materialDate = #{materialDate}, handleDate = #{handleDate}, issueDate = #{issueDate}, moveInDate = #{moveInDate}, transferDate = #{transferDate}, taxCancellation = #{taxCancellation}, commercialCancellation = #{commercialCancellation}, taxCollector = #{taxCollector}, merchat = #{merchat}, affliateMerchat = #{affliateMerchat}, isEDeclaration = #{isEDeclaration}, isReceiptBought = #{isReceiptBought}, isNormalTaxer = #{isNormalTaxer}, certificationNo = #{certificationNo}, companyIdentifier = #{companyIdentifier}, infoCardNo = #{infoCardNo}, operationStartYear = #{operationStartYear}, operationEndYear = #{operationEndYear}, validStartYear = #{validStartYear}, validEndYear = #{validEndYear}, operationMethod = #{operationMethod}, normalTaxerAuthDate = #{normalTaxerAuthDate}, operationScope = #{operationScope}, remarkables = #{remarkables}, unifiedCode = #{unifiedCode}, taxMngCode = #{taxMngCode}, taxRegNo = #{taxRegNo}, purchasedBookNo = #{purchasedBookNo}, bank = #{bank}, bankAccount = #{bankAccount}, taxAccount = #{taxAccount}, shareRatio = #{shareRatio}, branches = #{branches}, taxOrgID = #{taxOrgID}, isA = #{isA}, isB = #{isB}, isC = #{isC}, isD = #{isD}, isE = #{isE}, isF = #{isF}, isG = #{isG}, isH = #{isH}, isI = #{isI}, isJ = #{isJ}, isK = #{isK}, isL = #{isL}, isM = #{isM}, isN = #{isN}, isO = #{isO}, isP = #{isP}, isQ = #{isQ}, aDate = #{aDate}, bDate = #{bDate}, cDate = #{cDate}, dDate = #{dDate}, eDate = #{eDate}, fDate = #{fDate}, gDate = #{gDate}, hDate = #{hDate}, iDate = #{iDate}, jDate = #{jDate}, kDate = #{kDate}, lDate = #{lDate}, mDate = #{mDate}, nDate = #{nDate}, numberOfO = #{numberOfO}, numberOfP = #{numberOfP}, numberOfQ = #{numberOfQ}, partyName = #{partyName}, partyLeader = #{partyLeader}, area = #{area}, ownership = #{ownership}, sleepingRoomNumbers = #{sleepingRoomNumbers}, usageArea = #{usageArea}, rentArea = #{rentArea}, spareArea = #{spareArea}, companyArea = #{companyArea}, localEmployeeNumber = #{localEmployeeNumber}, employeeNumber = #{employeeNumber}, landUsageID = #{landUsageID}, factoryArea = #{factoryArea}, factoryUsageArea = #{factoryUsageArea}, factoryRentArea = #{factoryRentArea}, factoryControlArea = #{factoryControlArea}, ownerContactID = #{ownerContact.contactID}, otherContactID = #{otherContact.contactID}, shareholderContactID = #{shareholderContact.contactID}, passDate = #{passDate}, noOfDeveloper = #{noOfDeveloper}, devBudget = #{devBudget}, factoryActualArea = #{factoryActualArea}, isRent = #{isRent}, currencyID = #{currencyID}, isR = #{isR}, isS = #{isS}, isT = #{isT}, isU = #{isU}, isV = #{isV}, isW = #{isW}, rDate = #{rDate}, sDate = #{sDate}, tDate = #{tDate}, uDate = #{uDate}, vDate = #{vDate}, wDate = #{wDate} "
			+ " WHERE companyID= #{companyID}")
	void updateCompany(Company company);

	@Insert("INSERT INTO companyContact(genderID,fullName,phone,cellPhone,emailAddress,idCard,postalCode,address,fax) "
			+ "VALUES (#{genderID}, #{fullName}, #{phone}, #{cellPhone}, #{emailAddress}, #{idCard}, #{postalCode}, #{address}, #{fax})")
	@Options(useGeneratedKeys = true, keyProperty = "contactID")
	void createCompanyContact(CompanyContact contact);

	@Update("UPDATE companyContact SET genderID = #{genderID}, fullName = #{fullName}, phone = #{phone}, cellPhone = #{cellPhone}, emailAddress = #{emailAddress}, idCard = #{idCard}, postalCode = #{postalCode}, address = #{address}, fax = #{fax}"
			+ " WHERE contactID = #{contactID}")
	void updateCompanyContact(CompanyContact contact);

	@Insert("INSERT INTO member(companyID, name, position, produceMethod, idTypeID, idNo, certificate)"
			+ " VALUES(#{companyID}, #{name}, #{position}, #{produceMethod}, #{idTypeID}, #{idNo}, #{certificate});")
	void createCompanyMemeber(CompanyMember member);

	@Update("UPDATE member SET name = #{name}, position = #{position}, produceMethod = #{produceMethod}, idTypeID = #{idTypeID}, idNo = #{idNo}, certificate = #{certificate}"
			+ " WHERE memberID = #{memberID}")
	void updateCompanyMember(CompanyMember member);

	@Delete("DELETE FROM member WHERE memberID = #{memberID}")
	void deleteCompanyMember(@Param("memberID") Long memeberID);

	@Delete("DELETE FROM member WHERE companyID = #{companyID}")
	void deleteCompanyMemberByCompanyID(@Param("companyID") Long companyID);

	@Insert("INSERT INTO annualOutput(companyID, year, output, profit) VALUES(#{companyID}, #{output.year}, #{output.output}, #{output.profit})")
	void createAnnualOutput(@Param("companyID") Long companyID,
			@Param("output") AnnualOutput output);

	@Update("UPDATE annualOutput SET year = #{year}, output = #{output}, profit = #{profit} WHERE outputID = #{outputID}")
	void updateAnnualOutput(AnnualOutput output);

	@Delete("DELETE FROM annualOutput WHERE outputID = #{outputID}")
	void deleteAnnualOutput(Long outputID);

	@Insert("INSERT INTO partyMember(companyID, name, genderID, education)"
			+ " VALUES(#{companyID}, #{name}, #{genderID}, #{education})")
	void createPartyMember(PartyMember member);

	@Update("UPDATE partyMember SET name = #{name}, genderID = #{genderID}, education = #{education}"
			+ " WHERE memberID = #{memberID}")
	void updatePartyMember(PartyMember member);

	@Delete("DELETE FROM partyMember WHERE memberID = #{memberID}")
	void deletePartyMember(@Param("memberID") Long memeberID);

	@Delete("DELETE FROM partyMember WHERE companyID = #{companyID}")
	void deletePartyMemberByCompanyID(@Param("companyID") Long companyID);

	@SelectProvider(type = CompanySearchProvider.class, method = "searchCompany")
	List<Map<String, Object>> searchCompany(
			@Param("searchCriteria") SearchCriteria criteria);

	@SelectProvider(type = CompanySearchProvider.class, method = "searchCompanyWithTax")
	List<Map<String, Object>> searchCompanyWithTax(
			@Param("searchCriteria") SearchCriteria criteria);

	@SelectProvider(type = CompanySearchProvider.class, method = "searchCompanyTotal")
	int getCompanyTotalCount(@Param("searchCriteria") SearchCriteria criteria);

	@SelectProvider(type = CompanySearchProvider.class, method = "searchCompanyWithTaxTotal")
	int getCompanyWithTaxTotal(@Param("searchCriteria") SearchCriteria criteria);

	@SelectProvider(type = ProjectSearchProvider.class, method = "searchProjects")
	@Results({
			@Result(column = "projectID", property = "projectID"),
			@Result(column = "projectID", property = "relatedCompanies", many = @Many(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyByProjectID")), })
	List<Project> searchProjects(
			@Param("searchCriteria") SearchCriteria criteria);

	@SelectProvider(type = ProjectSearchProvider.class, method = "searchProjectTotal")
	int getProjectTotalCount(@Param("searchCriteria") SearchCriteria criteria);

	@Select("SELECT * FROM companyContact WHERE contactID = #{contactID}")
	CompanyContact getCompanyContact(@Param("contactID") Long contactID);

	@Delete("DELETE FROM companyContact WHERE contactID = #{contactID}")
	void deleteCompanyContact(@Param("contactID") Long contactID);

	@Select("SELECT * FROM company WHERE companyID = #{companyID}")
	@Results(value = {
			@Result(column = "companyID", property = "companyID"),
			@Result(column = "ownerContactID", property = "ownerContact", one = @One(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyContact")),
			@Result(column = "shareholderContactID", property = "shareholderContact", one = @One(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyContact")),
			@Result(column = "otherContactID", property = "otherContact", one = @One(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyContact")),
			@Result(column = "companyID", property = "members", many = @Many(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyMembers")),
			@Result(column = "companyID", property = "partyMembers", many = @Many(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getPartyMembers")),
			@Result(column = "companyID", property = "natureShareholders", many = @Many(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getNatureShareholder")),
			@Result(column = "companyID", property = "companyShareholders", many = @Many(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyShareholder")),
			@Result(column = "companyID", property = "rentees", many = @Many(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getRentees")),
			@Result(column = "companyID", property = "rentors", many = @Many(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getRentors")),
			@Result(column = "companyID", property = "outputs", many = @Many(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getAnnualOuptuts")) })
	Company getCompany(@Param("companyID") Long companyID);
	
	@Select("SELECT companyID, name FROM company WHERE companyID = #{companyID}")
	Company getSimpleCompany(@Param("companyID") Long companyID);

	@Select("SELECT * FROM member WHERE companyID = #{companyID}")
	List<CompanyMember> getCompanyMembers(@Param("companyID") Long companyID);

	@Select("SELECT * FROM partyMember WHERE companyID = #{companyID}")
	List<PartyMember> getPartyMembers(@Param("companyID") Long companyID);

	@Select("SELECT * FROM annualOutput WHERE companyID = #{companyID}")
	List<AnnualOutput> getAnnualOuptuts(@Param("companyID") Long companyID);

	@Select("SELECT * FROM company WHERE name = #{name}")
	@Results(value = {
			@Result(column = "ownerContactID", property = "ownerContact", one = @One(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyContact")),
			@Result(column = "shareholderContactID", property = "shareholderContact", one = @One(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyContact")),
			@Result(column = "otherContactID", property = "otherContact", one = @One(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyContact")) })
	Company getCompanyByName(@Param("name") String name);

	@Select("SELECT * FROM company WHERE companyID = #{companyID}")
	Company getCompanyForReport(@Param("companyID") Long companyID);

	@Select("SELECT t.* FROM companySaleTaxData t INNER JOIN company c on c.companyID = t.companyID "
			+ "WHERE t.year = #{year} AND t.month = #{month}")
	@Results(value = { @Result(column = "companyID", property = "company", one = @One(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyForReport")) })
	List<TaxData> getMonthlyTaxReport(@Param("year") Integer year,
			@Param("month") Integer month);

	@Select("SELECT count(*) FROM company WHERE taxCancellation BETWEEN #{startDate} AND #{endDate}")
	int getSignedOffCompanyCount(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	@Insert("INSERT INTO monthlyStatisticData(year, month, merchantIndustry, merchantCommercial, merchantService, merchantConstruct, merchantHouseHolding, merchantSignOffCompanies, regAssetsIndustry, regAssetsCommercial, regAssetsService, regAssetsConstruct, regAssetsHouseholding, vat, operateTax, incomingTax, constructTax, otherTax, salesTotal, privateCompanyVAT, privateCompanyOperateTax, privateCompanyIncomingTax, privateCompanyPersonalIncomingTax, privateCompanyConstructTax, privateCompanyOtherTax, publicCompanyVAT, publicCompanyOperateTax, publicCompanyIncomingTax, publicCompanyPersonalIncomingTax, publicCompanyConstructTax, publicCompanyOtherTax, groupCompanyVAT, groupCompanyOperateTax, groupCompanyIncomingTax, groupCompanyPersonalIncomingTax, groupCompanyConstructTax, groupCompanyOtherTax, industryVAT, commercialVAT, constructVAT, serviceVAT, houseHoldingVAT, industryOperateTax, commercialOperateTax, constructOperateTax, serviceOperateTax, houseHoldingOperateTax, industryIncomingTax, commercialIncomingTax, constructIncomingTax, serviceIncomingTax, houseHoldingIncomingTax, industryPersonalIncomingTax, commercialPersonalIncomingTax, constructPersonalIncomingTax, servicePersonalIncomingTax, houseHoldingPersonalIncomingTax, industryConstructTax, commercialConstructTax, constructConstructTax, serviceConstructTax, houseHoldingConstructTax, industryOtherTax, commercialOtherTax, constructOtherTax, serviceOtherTax, houseHoldingOtherTax, industrySale, commercialSale, constructSale, serviceSale, houseHoldingSale)"
			+ "VALUES(#{year}, #{month}, #{merchantIndustry}, #{merchantCommercial}, #{merchantService}, #{merchantConstruct}, #{merchantHouseHolding}, #{merchantSignOffCompanies}, #{regAssetsIndustry}, #{regAssetsCommercial}, #{regAssetsService}, #{regAssetsConstruct}, #{regAssetsHouseholding}, #{vat}, #{operateTax}, #{incomingTax}, #{constructTax}, #{otherTax}, #{salesTotal}, #{privateCompanyVAT}, #{privateCompanyOperateTax}, #{privateCompanyIncomingTax}, #{privateCompanyPersonalIncomingTax}, #{privateCompanyConstructTax}, #{privateCompanyOtherTax}, #{publicCompanyVAT}, #{publicCompanyOperateTax}, #{publicCompanyIncomingTax}, #{publicCompanyPersonalIncomingTax}, #{publicCompanyConstructTax}, #{publicCompanyOtherTax}, #{groupCompanyVAT}, #{groupCompanyOperateTax}, #{groupCompanyIncomingTax}, #{groupCompanyPersonalIncomingTax}, #{groupCompanyConstructTax}, #{groupCompanyOtherTax}, #{industryVAT}, #{commercialVAT}, #{constructVAT}, #{serviceVAT}, #{houseHoldingVAT}, #{industryOperateTax}, #{commercialOperateTax}, #{constructOperateTax}, #{serviceOperateTax}, #{houseHoldingOperateTax}, #{industryIncomingTax}, #{commercialIncomingTax}, #{constructIncomingTax}, #{serviceIncomingTax}, #{houseHoldingIncomingTax}, #{industryPersonalIncomingTax}, #{commercialPersonalIncomingTax}, #{constructPersonalIncomingTax}, #{servicePersonalIncomingTax}, #{houseHoldingPersonalIncomingTax}, #{industryConstructTax}, #{commercialConstructTax}, #{constructConstructTax}, #{serviceConstructTax}, #{houseHoldingConstructTax}, #{industryOtherTax}, #{commercialOtherTax}, #{constructOtherTax}, #{serviceOtherTax}, #{houseHoldingOtherTax}, #{industrySale}, #{commercialSale}, #{constructSale}, #{serviceSale}, #{houseHoldingSale})")
	void insertMonthlyReportData(OverallMonthData data);

	@Delete("DELETE FROM monthlyStatisticData WHERE year = #{year} AND month = #{month}")
	void clearMonthlyStatisticData(@Param("year") Integer year,
			@Param("month") Integer month);

	@Select("SELECT year, count(merchantIndustry) AS merchantIndustry, count(merchantCommercial) AS merchantCommercial, count(merchantService) AS merchantService, count(merchantConstruct) AS merchantConstruct, count(merchantHouseHolding) AS merchantHouseHolding, count(merchantSignOffCompanies) AS merchantSignOffCompanies, sum(regAssetsIndustry) AS regAssetsIndustry, sum(regAssetsCommercial) AS regAssetsCommercial, sum(regAssetsService) AS regAssetsService, sum(regAssetsConstruct) AS regAssetsConstruct, sum(regAssetsHouseholding) AS regAssetsHouseholding, sum(vat) AS vat, sum(operateTax) AS operateTax, sum(incomingTax) AS incomingTax, sum(constructTax) AS constructTax, sum(otherTax) AS otherTax, sum(salesTotal) AS salesTotal, sum(privateCompanyVAT) AS privateCompanyVAT, sum(privateCompanyOperateTax) AS privateCompanyOperateTax, sum(privateCompanyIncomingTax) AS privateCompanyIncomingTax, sum(privateCompanyPersonalIncomingTax) AS privateCompanyPersonalIncomingTax, sum(privateCompanyConstructTax) AS privateCompanyConstructTax, sum(privateCompanyOtherTax) AS privateCompanyOtherTax, sum(publicCompanyVAT) AS publicCompanyVAT, sum(publicCompanyOperateTax) AS publicCompanyOperateTax, sum(publicCompanyIncomingTax) AS publicCompanyIncomingTax, sum(publicCompanyPersonalIncomingTax) AS publicCompanyPersonalIncomingTax, sum(publicCompanyConstructTax) AS publicCompanyConstructTax, sum(publicCompanyOtherTax) AS publicCompanyOtherTax, sum(groupCompanyVAT) AS groupCompanyVAT, sum(groupCompanyOperateTax) AS groupCompanyOperateTax, sum(groupCompanyIncomingTax) AS groupCompanyIncomingTax, sum(groupCompanyPersonalIncomingTax) AS groupCompanyPersonalIncomingTax, sum(groupCompanyConstructTax) AS groupCompanyConstructTax, sum(groupCompanyOtherTax) AS groupCompanyOtherTax FROM monthlyStatisticData WHERE year = #{year}")
	OverallYearData getOverallYearData(@Param("year") Integer year);

	@Select("SELECT SUM(industrySale + commercialSale + constructSale + serviceSale + houseHoldingSale) AS Sale,"
			+ "SUM(industryVAT + commercialVAT + constructVAT + serviceVAT + houseHoldingVAT) AS VAT, "
			+ "SUM(industryOperateTax + commercialOperateTax + constructOperateTax + serviceOperateTax + houseHoldingOperateTax) AS OperateTax,"
			+ "SUM(industryIncomingTax + commercialIncomingTax + constructIncomingTax + serviceIncomingTax + houseHoldingIncomingTax) AS IncomingTax,"
			+ "SUM(industryPersonalIncomingTax + commercialPersonalIncomingTax + constructPersonalIncomingTax + servicePersonalIncomingTax + houseHoldingPersonalIncomingTax) AS PersonalIncomingTax,"
			+ "SUM(industryConstructTax + commercialConstructTax + constructConstructTax + serviceConstructTax + houseHoldingConstructTax) AS ConstructTax,"
			+ "SUM(industryOtherTax + commercialOtherTax + constructOtherTax + serviceOtherTax + houseHoldingOtherTax) AS OtherTax"
			+ " FROM monthlyStatisticData "
			+ "WHERE year = #{year} AND month <= #{month}")
	Map<String, Double> getOverallYearTaxData(@Param("year") Integer year,
			@Param("month") Integer month);

	@Select("SELECT * FROM monthlyStatisticData WHERE year = #{year} AND month = #{month}")
	OverallMonthData getOverallMonthData(@Param("year") Integer year,
			@Param("month") Integer month);

	@Select("SELECT * FROM companySaleTaxData"
			+ " WHERE year = #{year} AND month = #{month} AND companyID = #{companyID}")
	@Results(value = { @Result(column = "companyID", property = "company", one = @One(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyForReport")) })
	TaxData getTaxDetail(@Param("year") Integer year,
			@Param("month") Integer month, @Param("companyID") Long companyID);

	@SelectProvider(type = CompanySearchProvider.class, method = "getCompanyEconomyEntity")
	Map<String, Long> getCompanyEconomyEntityCounts();

	@SelectProvider(type = CompanySearchProvider.class, method = "getCompanyEconomyEntityMonthlyCount")
	Map<String, Long> getCompanyEconomyEntityMonthCounts(
			@Param("year") Integer year, @Param("month") Integer month);

	@Select("SELECT * FROM companySaleTaxData WHERE month = #{month} AND year = #{year} AND companyID = #{companyID}")
	@Results(value = { @Result(column = "companyID", property = "company", one = @One(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyForReport")) })
	TaxData getIndividualTaxData(@Param("year") Integer year,
			@Param("month") Integer month, @Param("companyID") Long companyID);

	@Select("SELECT year, month FROM companySaleTaxData WHERE companyID = #{companyID}")
	List<Map<String, Integer>> getCompanyMonthReportList(
			@Param("companyID") Long companyID);

	@Update("Update companySaleTaxData SET taxOrgID = #{taxOrgID}, economyEntityID = #{economyEntityID}, economyNatureID = #{economyNatureID} WHERE companyID = #{companyID}")
	void updateTaxData(@Param("companyID") Long companyID,
			@Param("taxOrgID") Long taxOrgID,
			@Param("economyEntityID") Long economyEntityID,
			@Param("economyNatureID") Long economyNatureID);

	@Insert("INSERT INTO project(projectID, name, statusID, isCommercial, area, constructionArea, product, domesticInvestment, foreignInvestment, fixInvestment, totalInvestment, sales, profit, tax, auditPassDate, constructTypeID, actualArea, numberOfBuilding, startDate, endDate, produceDate, progress, problem, contactor, phone, email, fax, comment)"
			+ " VALUES(#{projectID}, #{name}, #{statusID}, #{isCommercial}, #{area}, #{constructionArea}, #{product}, #{domesticInvestment}, #{foreignInvestment}, #{fixInvestment}, #{totalInvestment}, #{sales}, #{profit}, #{tax}, #{auditPassDate}, #{constructTypeID}, #{actualArea}, #{numberOfBuilding}, #{startDate}, #{endDate}, #{produceDate}, #{progress}, #{problem}, #{contactor}, #{phone}, #{email}, #{fax}, #{comment})")
	@Options(useGeneratedKeys = true, keyProperty = "projectID")
	void createProject(Project project);

	@Insert("INSERT INTO relatedCompany(projectID, companyID) VALUES(#{projectID}, #{companyID})")
	void createRelatedProject(@Param("projectID") Long projectID,
			@Param("companyID") Long companyID);

	@Select("SELECT * FROM project WHERE projectID = #{projectID}")
	@Results({
			@Result(column = "projectID", property = "projectID"),
			@Result(column = "projectID", property = "relatedCompanies", many = @Many(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyByProjectID")), })
	Project getProject(Long projectID);

	@Select("SELECT c.companyID, c.name FROM company c INNER JOIN relatedCompany r ON r.companyID = c.companyID WHERE projectID = #{projectID}")
	List<Company> getCompanyByProjectID(@Param("projectID") Long projectID);

	@Update("UPDATE project"
			+ " SET name = #{name}, statusID = #{statusID}, isCommercial = #{isCommercial}, area = #{area}, constructionArea = #{constructionArea}, product = #{product}, domesticInvestment = #{domesticInvestment}, foreignInvestment = #{foreignInvestment}, fixInvestment = #{fixInvestment}, totalInvestment = #{totalInvestment}, sales = #{sales}, profit = #{profit}, tax = #{tax}, auditPassDate = #{auditPassDate}, constructTypeID = #{constructTypeID}, actualArea = #{actualArea}, numberOfBuilding = #{numberOfBuilding}, startDate = #{startDate}, endDate = #{endDate}, produceDate = #{produceDate}, progress = #{progress}, problem = #{problem}, contactor = #{contactor}, phone = #{phone}, email = #{email}, fax = #{fax}, comment = #{comment}"
			+ " WHERE projectID = #{projectID}")
	void updateProject(Project project);

	@Delete("DELETE FROM relatedCompany WHERE projectID = #{projectID}")
	void deleteRelatedCompanies(Long projectID);

	@Select("SELECT * FROM project WHERE statusID != 1206")
	List<Project> getProjectsForReport();

	@Insert("INSERT INTO agricultureInfo(agriID, farmerName, contactNumber, area, address, paddyArea, wheatArea, greenArea, assartArea)"
			+ " VALUES(#{agriID}, #{farmerName}, #{contactNumber}, #{area}, #{address}, #{paddyArea}, #{wheatArea}, #{greenArea}, #{assartArea})")
	void createAgriculture(AgricultureInfo agriculture);

	@Update("UPDATE agricultureInfo SET farmerName = #{farmerName}, contactNumber = #{contactNumber}, area = #{area}, address = #{address}, paddyArea = #{paddyArea}, wheatArea = #{wheatArea}, greenArea = #{greenArea}, assartArea = #{assartArea}"
			+ " WHERE agriID = #{agriID}")
	void updateAgriculture(AgricultureInfo agriculture);

	@Select("SELECT * FROM agricultureInfo WHERE agriID = #{agriID}")
	AgricultureInfo getAgricultureInfo(Long agriID);

	@SelectProvider(type = CompanySearchProvider.class, method = "searchAgricultureInfo")
	List<Map<String, Object>> searchAgricultureInfo(
			@Param("searchCriteria") SearchCriteria criteria);

	@SelectProvider(type = CompanySearchProvider.class, method = "searchAgricultureInfoTotal")
	int getAgricultureInfoTotalCount(
			@Param("searchCriteria") SearchCriteria criteria);

	@Select("select economyNatureID, IFNull(SUM(vat),0) AS vat"
			+ " FROM companysaletaxdata WHERE year = #{year} AND month = #{month} GROUP BY economyNatureID")
	List<Map<String, Object>> getEconomyNaturePITReport(
			@Param("year") Integer year, @Param("month") Integer month);

	@Select("select c.name as name, t.vat as vat"
			+ " FROM companysaletaxdata t INNER JOIN company c ON c.companyID = t.companyID"
			+ " WHERE t.year = #{year} AND t.month = #{month} ORDER BY vat DESC LIMIT 0, 10")
	List<Map<String, Object>> getTop10VATReport(@Param("year") Integer year,
			@Param("month") Integer month);

	@Select("select economyEntityID, IFNull(SUM(vat),0) AS vat"
			+ " FROM companysaletaxdata WHERE year = #{year} AND month = #{month} GROUP BY economyEntityID")
	List<Map<String, Object>> getEconomyEntityVATReport(
			@Param("year") Integer year, @Param("month") Integer month);

	@SelectProvider(type = CompanySearchProvider.class, method = "getCompanyContactInfo")
	List<Map<String, Object>> getCompanyContacts(
			@Param("companyIDs") List<Long> companyIDs);
	
	
	@Insert("INSERT INTO natureshareholder(contactID, companyID, fullName, cellPhone, phone, emailAddress, idCard, address, fax, shareRatio)"
			+ " VALUES(#{contactID}, #{companyID}, #{fullName}, #{cellPhone}, #{phone}, #{emailAddress}, #{idCard}, #{address}, #{fax}, #{shareRatio})")
	void createNatureShareholder(NatureShareholder n);
	
	@Insert("INSERT INTO companyshareholder(contactID, companyID, fullName, phone, idType, idCard, address, shareRatio)"
			+ " VALUES(#{contactID}, #{companyID}, #{fullName}, #{phone}, #{idType}, #{idCard}, #{address}, #{shareRatio})")
	void createCompanyShareholder(CompanyShareholder n);
	
	@Update("UPDATE natureshareholder "
			+ "SET fullName = #{fullName}, cellPhone = #{cellPhone}, phone = #{phone}, emailAddress = #{emailAddress}, idCard = #{idCard}, address = #{address}, fax = #{fax}, shareRatio = #{shareRatio} "
			+ "WHERE contactID = #{contactID}")
	void updateNatureShareholder(NatureShareholder n);
	
	@Update("UPDATE companyshareholder "
			+ "SET fullName = #{fullName}, phone = #{phone}, idType = #{idType}, idCard = #{idCard}, address = #{address}, shareRatio = #{shareRatio} "
			+ "WHERE contactID = #{contactID}")
	void updateCompanyShareholder(CompanyShareholder n);
	
	@Delete("DELETE FROM natureshareholder where contactID = #{contactID}")
	void deleteNatureShareholder(Long contactID);
	
	@Delete("DELETE FROM companyshareholder where contactID = #{contactID}")
	void deleteCompanyShareholder(Long contactID);
	
	@Select("SELECT * FROM natureshareholder WHERE companyID = #{companyID}")
	List<NatureShareholder> getNatureShareholder(Long companyID);
	
	@Select("SELECT * FROM companyshareholder WHERE companyID = #{companyID}")
	List<CompanyShareholder> getCompanyShareholder(Long companyID);
	
	@Insert("INSERT INTO rentrelation(rentorID, renteeID, nonDJCompany, area, address, startDate, endDate) "
			+ "VALUES(#{rentor.companyID}, #{rentee.companyID}, #{nonDJCompany}, #{area}, #{address}, #{startDate}, #{endDate})")
	void createRentStatus(RentStatus rs);
	
	@Delete("DELETE FROM rentrelation WHERE rentorID = #{rentor.companyID} AND renteeID = #{rentee.companyID}")
	void deleteRentStatus(RentStatus rs);
	
	@Delete("DELETE FROM rentrelation WHERE renteeID = #{renteeID}")
	void clearRentors(Long renteeID);
	
	@Select("SELECT * FROM rentrelation WHERE rentorID = #{rentorID}")
	@Results(value = {
			@Result(column = "rentorID", property = "rentor", many = @Many(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getSimpleCompany")),
			@Result(column = "renteeID", property = "rentee", many = @Many(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getSimpleCompany")) })
	List<RentStatus> getRentees(Long rentorID);
	
	@Select("SELECT * FROM rentrelation WHERE renteeID = #{renteeID}")
	@Results(value = {
			@Result(column = "rentorID", property = "rentor", many = @Many(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getSimpleCompany")),
			@Result(column = "renteeID", property = "rentee", many = @Many(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getSimpleCompany")) })
	List<RentStatus> getRentors(Long renteeID);

    @Select("SELECT * FROM company WHERE regionID = #{blockID}")
    @Results(value = {
            @Result(column = "companyID", property = "companyID"),
            @Result(column = "ownerContactID", property = "ownerContact", one = @One(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyContact")),
            @Result(column = "shareholderContactID", property = "shareholderContact", one = @One(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyContact")),
            @Result(column = "otherContactID", property = "otherContact", one = @One(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyContact"))})
    List<Company> getExportCompanies(@Param("blockID") Long blockID);

    @Select("SELECT * FROM company")
    @Results(value = {
            @Result(column = "companyID", property = "companyID"),
            @Result(column = "ownerContactID", property = "ownerContact", one = @One(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyContact")),
            @Result(column = "shareholderContactID", property = "shareholderContact", one = @One(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyContact")),
            @Result(column = "otherContactID", property = "otherContact", one = @One(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyContact"))})
    List<Company> getAllCompanies();

    @Select("SELECT * FROM rentrelation")
    List<RentStatusPO> getAllRentStatus();

    @Select("SELECT * FROM natureshareholder")
    List<NatureShareholder> getAllNatureShareholders();

    @Select("SELECT * FROM companyshareholder")
    List<CompanyShareholder> getAllCompanyShareholders();

    @Select("SELECT * FROM annualOutput")
    List<AnnualOutputPO> getAllOutputs();

    @Select("SELECT * FROM partyMember")
    List<PartyMember> getAllPartyMembers();

    @Select("SELECT * FROM member")
    List<CompanyMember> getAllCompanyMembers();


	@Select("SELECT isACount, isBCount, isCCount, isDCount, isECount, isFCount, isGCount, isHCount, isICount, isJCount, isKCount, isLCount, isMCount, isNCount, isOCount, isPCount, isQCount, isRCount, isSCount, isTCount, isUCount, isVCount, isWCount\n" +
			"FROM (SELECT count(*) AS isACount FROM company WHERE isA = '1') AS A\n" +
			"JOIN (SELECT count(*) AS isBCount FROM company WHERE isB = '1') AS B\n" +
			"JOIN (SELECT count(*) AS isCCount FROM company WHERE isC = '1') AS C\n" +
			"JOIN (SELECT count(*) AS isDCount FROM company WHERE isD = '1') AS D\n" +
			"JOIN (SELECT count(*) AS isECount FROM company WHERE isE = '1') AS E\n" +
			"JOIN (SELECT count(*) AS isFCount FROM company WHERE isF = '1') AS F\n" +
			"JOIN (SELECT count(*) AS isGCount FROM company WHERE isG = '1') AS G\n" +
			"JOIN (SELECT count(*) AS isHCount FROM company WHERE isH = '1') AS H\n" +
			"JOIN (SELECT count(*) AS isICount FROM company WHERE isI = '1') AS I\n" +
			"JOIN (SELECT count(*) AS isJCount FROM company WHERE isJ = '1') AS J\n" +
			"JOIN (SELECT count(*) AS isKCount FROM company WHERE isK = '1') AS K\n" +
			"JOIN (SELECT count(*) AS isLCount FROM company WHERE isL = '1') AS L\n" +
			"JOIN (SELECT count(*) AS isMCount FROM company WHERE isM = '1') AS M\n" +
			"JOIN (SELECT count(*) AS isNCount FROM company WHERE isN = '1') AS N\n" +
			"JOIN (SELECT count(*) AS isOCount FROM company WHERE isO = '1') AS O\n" +
			"JOIN (SELECT count(*) AS isPCount FROM company WHERE isP = '1') AS P\n" +
			"JOIN (SELECT count(*) AS isQCount FROM company WHERE isQ = '1') AS Q\n" +
			"JOIN (SELECT count(*) AS isRCount FROM company WHERE isR = '1') AS R\n" +
			"JOIN (SELECT count(*) AS isSCount FROM company WHERE isS = '1') AS S\n" +
			"JOIN (SELECT count(*) AS isTCount FROM company WHERE isT = '1') AS T\n" +
			"JOIN (SELECT count(*) AS isUCount FROM company WHERE isU = '1') AS U\n" +
			"JOIN (SELECT count(*) AS isVCount FROM company WHERE isV = '1') AS V\n" +
			"JOIN (SELECT count(*) AS isWCount FROM company WHERE isW = '1') AS W")
	ProjectApplyStatus getProjectApplyStatus();

	@Select("select sum(factoryArea) as factoryArea, sum(factoryUsageArea) as factoryUsageArea, sum(factoryControlArea) as factoryControlArea, b.factoryRentArea " +
			"from company join (select sum(area) as factoryRentArea from rentrelation) as b")
	CompanyAreaPO getCompanyAreaReport();
}
