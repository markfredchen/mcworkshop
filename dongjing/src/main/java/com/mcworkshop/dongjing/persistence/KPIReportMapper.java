// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.persistence;

import com.mcworkshop.dongjing.service.report.model.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Date;

/**
 * @author $Author$
 * 
 */
public interface KPIReportMapper {

	// 历年累计注册企业数
	@SelectProvider(type = KPIReportProvider.class, method = "getCompanyEconomyEntity")
	EconomyEntityCount getCompanyEconomyEntity();

	// 工商年检企业数
	@SelectProvider(type = KPIReportProvider.class, method = "getCompanyConfirmedByEconomyEntity")
	EconomyEntityCount getCompanyConfirmedByEconomyEntity();

	@SelectProvider(type = KPIReportProvider.class, method = "getCompanyConfirmedByAffiliationRegion")
	AffiliationRegionCount getCompanyConfirmedByAffiliationRegion();

	// 本月新增注册企业数
	// 本年累计新增注册企业数
	@SelectProvider(type = KPIReportProvider.class, method = "getNewRegCompanies")
	EconomyEntityCount getNewRegCompanies(@Param("startDate") Date startDate,
			@Param("endDate") Date endtDate);

	@SelectProvider(type = KPIReportProvider.class, method = "getNewRegCompaniesAffiliationRegion")
	AffiliationRegionCount getNewRegCompaniesAffiliationRegion(
			@Param("startDate") Date startDate, @Param("endDate") Date endtDate);

	// 本月注销企业数
	// 本年累计注销企业数
	@SelectProvider(type = KPIReportProvider.class, method = "getNewCancellationCompanies")
	EconomyEntityCount getNewCancellationCompanies(
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@SelectProvider(type = KPIReportProvider.class, method = "getNewCancellationAFCompanies")
	AffiliationRegionCount getNewCancellationAFCompanies(
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	// 本年实有注册企业数
	@SelectProvider(type = KPIReportProvider.class, method = "getNewRegAssetsCompanies")
	EconomyEntityRegAssets getNewRegAssets(@Param("startDate") Date startDate,
			@Param("endDate") Date endtDate);

	@SelectProvider(type = KPIReportProvider.class, method = "getNewRegAssetsAFCompanies")
	AffiliationRegionRegAssets getNewRegAssetsAF(
			@Param("startDate") Date startDate, @Param("endDate") Date endtDate);

	// 注册资金
	@SelectProvider(type = KPIReportProvider.class, method = "getAllRegAssetsCompanies")
	EconomyEntityRegAssets getAllRegAssets();

	@SelectProvider(type = KPIReportProvider.class, method = "getAllRegAssetsAFCompanies")
	AffiliationRegionRegAssets getAllRegAssetsAF();

	// 本月纳税户数
	@SelectProvider(type = KPIReportProvider.class, method = "getCheckedTaxCompanyEconomyEntity")
	EconomyEntityCount getCheckedTaxCompanyEconomyEntity(
			@Param("year") Integer year, @Param("month") Integer month);

	@SelectProvider(type = KPIReportProvider.class, method = "getCheckedTaxAFCompanyEconomyEntity")
	AffiliationRegionCount getCheckedTaxAFCompanyEconomyEntity(
			@Param("year") Integer year, @Param("month") Integer month);

	@SelectProvider(type = KPIReportProvider.class, method = "getTaxedCompanyEconomyEntity")
	EconomyEntityCount getTaxedCompanyEconomyEntity(
			@Param("year") Integer year, @Param("month") Integer month);

	@SelectProvider(type = KPIReportProvider.class, method = "getTaxedCompanyAffiliateRegion")
	AffiliationRegionCount getTaxedCompanyAffiliateRegion(
			@Param("year") Integer year, @Param("month") Integer month);

	// 本月纳税金额
	@SelectProvider(type = KPIReportProvider.class, method = "getMonthTotalTaxByEconomyEntity")
	EconomyEntityTaxData getMonthTotalTaxByEconomyEntity(
			@Param("year") Integer year, @Param("month") Integer month);

	@SelectProvider(type = KPIReportProvider.class, method = "getMonthTotalTaxByAffiliateRegion")
	AffiliationRegionTaxData getMonthTotalTaxByAffiliateRegion(
			@Param("year") Integer year, @Param("month") Integer month);

	// 本月累积纳税
	@SelectProvider(type = KPIReportProvider.class, method = "getYearTotalTaxByEconomyEntity")
	EconomyEntityTaxData getYearTotalTaxByEconomyEntity(
			@Param("year") Integer year, @Param("month") Integer month);

	@SelectProvider(type = KPIReportProvider.class, method = "getMonthTotalSalesByEconmyEntity")
	EconomyEntityTaxData getMonthTotalSalesByEconmyEntity(
			@Param("year") Integer year, @Param("month") Integer month);

	// 本月累积纳税
	@SelectProvider(type = KPIReportProvider.class, method = "getYearTotalSalesByEconmyEntity")
	EconomyEntityTaxData getYearTotalSalesByEconmyEntity(
			@Param("year") Integer year, @Param("month") Integer month);

	@SelectProvider(type = KPIReportProvider.class, method = "getYearTotalSalesByAffiliateRegion")
	AffiliationRegionSales getYearTotalSalesByAffiliateRegion(
			@Param("year") Integer year, @Param("month") Integer month);

	@SelectProvider(type = KPIReportProvider.class, method = "getMonthTotalIncomingTaxByEconomyEntity")
	EconomyEntityTaxData getMonthTotalIncomingTaxByEconomyEntity(
			@Param("year") Integer year, @Param("month") Integer month);
}
