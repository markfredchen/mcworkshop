// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service;

import com.google.inject.Inject;
import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.dongjing.domain.AffiliateBlock;
import com.mcworkshop.dongjing.domain.EconomyEntity;
import com.mcworkshop.dongjing.domain.TaxData;
import com.mcworkshop.dongjing.persistence.EconomyEntityTaxMapper;
import com.mcworkshop.dongjing.persistence.KPIReportMapper;
import com.mcworkshop.dongjing.persistence.TaxOrgReportMapper;
import com.mcworkshop.dongjing.persistence.TotalTaxDataMapper;
import com.mcworkshop.dongjing.service.report.model.EconomyEntityTaxData;
import com.mcworkshop.dongjing.service.report.model.KPIReportData;
import com.mcworkshop.dongjing.service.report.model.TaxOrgData;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

/**
 * @author $Author$
 * 
 */
public class ReportServiceImpl implements ReportService {

	@Inject
	private EconomyEntityTaxMapper mapper;

	@Inject
	private TotalTaxDataMapper totalTax;

	@Inject
	private TaxOrgReportMapper taxOrg;

	@Inject
	private KPIReportMapper kpi;

	@Override
	public EconomyEntityTaxData getTaxMonthEconomyEntityReport(int year,
			int month) {
		EconomyEntityTaxData data = new EconomyEntityTaxData();
		data.setIndustyData(mapper.getTaxDataByEconomyEntity(SystemEnumeration
				.getInstance().getIdByKey(EconomyEntity.INDUSTRY), year, month));
		data.setCommercialData(mapper.getTaxDataByEconomyEntity(
				SystemEnumeration.getInstance().getIdByKey(
						EconomyEntity.COMMERCIAL), year, month));
		data.setServiceData(mapper.getTaxDataByEconomyEntity(SystemEnumeration
				.getInstance().getIdByKey(EconomyEntity.SERVICE), year, month));
		data.setConstructionData(mapper.getTaxDataByEconomyEntity(
				SystemEnumeration.getInstance().getIdByKey(
						EconomyEntity.CONSTRUCTION), year, month));
		data.setHouseHoldingData(mapper.getTaxDataByEconomyEntity(
				SystemEnumeration.getInstance().getIdByKey(
						EconomyEntity.HOUSEHOLDING), year, month));
		data.setYearData(mapper.getTaxYearDataByEconomyEntity(year, month));
		data.setLastYearData(mapper.getTaxYearDataByEconomyEntity(year - 1,
				month));
		Map<String, Long> monthMap = mapper.getCompanyEconomyEntityMonthCounts(
				year, month);
		data.setIndustryMonthCount(monthMap.get("industryMonthCount"));
		data.setCommercialMonthCount(monthMap.get("commercialMonthCount"));
		data.setConstructMonthCount(monthMap.get("constructMonthCount"));
		data.setServiceMonthCount(monthMap.get("serviceMonthCount"));
		data.setHouseHoldingMonthCount(monthMap.get("houseHoldingMonthCount"));
		Map<String, Long> totalMap = mapper.getCompanyEconomyEntityCounts();
		data.setIndustryCount(totalMap.get("industryCount"));
		data.setCommercialCount(totalMap.get("commercialCount"));
		data.setConstructCount(totalMap.get("constructCount"));
		data.setServiceCount(totalMap.get("serviceCount"));
		data.setHouseHoldingCount(totalMap.get("houseHoldingCount"));
		if (data.getIndustyData() == null) {
			data.setIndustyData(new TaxData());
		}
		if (data.getCommercialData() == null) {
			data.setCommercialData(new TaxData());
		}
		if (data.getServiceData() == null) {
			data.setServiceData(new TaxData());
		}
		if (data.getConstructionData() == null) {
			data.setConstructionData(new TaxData());
		}
		if (data.getHouseHoldingData() == null) {
			data.setHouseHoldingData(new TaxData());
		}
		if (data.getYearData() == null) {
			data.setYearData(new TaxData());
		}
		if (data.getLastYearData() == null) {
			data.setLastYearData(new TaxData());
		}
		return data;
	}

	@Override
	public TaxData getTotalTaxData(int year, int month) {
		return this.totalTax.getMonthTaxDataByEconmyNature(year, month);
	}

	@Override
	public TaxOrgData getTaxOrgTaxDataReport(int year, int month) {
		TaxOrgData data = new TaxOrgData();
		data.setMonthHouseData(taxOrg.getMonthTaxDataByTaxOrg(year, month));
		data.setMonthTaxOrgData(taxOrg.getMonthTaxDataByTaxOrg(year, month));
		data.setYearHouseData(taxOrg.getYearTaxDataByTaxOrg(year, month));
		data.setYearTaxOrgData(taxOrg
				.getYearTaxDataByEconomyEntity(year, month));
		return data;
	}

	@Override
	public KPIReportData getKPIReportDate(int year, int month) {
		Date endDate = new Date(new GregorianCalendar(year, month, 1).getTime()
				.getTime());
		Date startDate = new Date(new GregorianCalendar(year, month - 1, 1)
				.getTime().getTime());
		Date yearStarDate = new Date(new GregorianCalendar(year - 1, 12, 1)
				.getTime().getTime());

		KPIReportData data = new KPIReportData();
		data.setCompanies(kpi.getCompanyEconomyEntity());
		data.setCheckedCompanies(kpi.getCompanyConfirmedByEconomyEntity());
		data.setMonthNewAddedCompanies(kpi.getNewRegCompanies(startDate,
				endDate));
		data.setYearNewAddedCompanies(kpi.getNewRegCompanies(yearStarDate,
				endDate));
		data.setMonthNewSignedOffCompanies(kpi.getNewCancellationCompanies(
				startDate, endDate));
		data.setYearNewSignedOffCompanies(kpi.getNewCancellationCompanies(
				yearStarDate, endDate));
		data.setYearNewRegAssets(kpi.getNewRegAssets(yearStarDate, endDate));
		data.setAllRegAssets(kpi.getAllRegAssets());
		data.setOperatedCompanies(kpi.getTaxedCompanyEconomyEntity(year, month));
		data.setMonthSales(kpi.getMonthTotalSalesByEconmyEntity(year, month));
		data.setYearSales(kpi.getYearTotalSalesByEconmyEntity(year, month));
		data.setCheckedTaxCompanies(kpi.getCheckedTaxCompanyEconomyEntity(year,
				month));
		data.setMonthTax(kpi.getMonthTotalTaxByEconomyEntity(year, month));
		data.setYearTax(kpi.getYearTotalTaxByEconomyEntity(year, month));
		data.setIncomingTax(kpi.getMonthTotalIncomingTaxByEconomyEntity(year,
				month));
		return data;
	}

	@Override
	public KPIReportData getCombineKPIReportData(int year, int month) {
		Date endDate = new Date(new GregorianCalendar(year, month, 1).getTime()
				.getTime());
		Date startDate = new Date(new GregorianCalendar(year, month - 1, 1)
				.getTime().getTime());
		Date yearStarDate = new Date(new GregorianCalendar(year - 1, 12, 1)
				.getTime().getTime());
		Date lastYearEndDate = new Date(new GregorianCalendar(year - 1, month,
				1).getTime().getTime());
		Date lastYearStarDate = new Date(new GregorianCalendar(year - 2, 12, 1)
				.getTime().getTime());

		KPIReportData data = new KPIReportData();
		data.setCompanies(kpi.getCompanyEconomyEntity());
		data.setCheckedCompanies(kpi.getCompanyConfirmedByEconomyEntity());
		data.setYearNewAddedCompanies(kpi.getNewRegCompanies(yearStarDate,
				endDate));
		data.setLastYearNewAddedCompanies(kpi.getNewRegCompanies(
				lastYearStarDate, lastYearEndDate));
		data.setYearNewSignedOffCompanies(kpi.getNewCancellationCompanies(
				yearStarDate, endDate));
		data.setAllRegAssets(kpi.getAllRegAssets());
		data.setOperatedCompanies(kpi.getTaxedCompanyEconomyEntity(year, month));
		data.setYearSales(kpi.getYearTotalSalesByEconmyEntity(year, month));
		data.setLastYearSales(kpi.getYearTotalSalesByEconmyEntity(year - 1,
				month));
		data.setCheckedTaxCompanies(kpi.getCheckedTaxCompanyEconomyEntity(year,
				month));
		data.setMonthTax(kpi.getMonthTotalTaxByEconomyEntity(year, month));
		data.setLastYearTax(kpi
				.getMonthTotalTaxByEconomyEntity(year - 1, month));
		data.setCheckedAFCompanies(kpi.getCompanyConfirmedByAffiliationRegion());
		data.setYearNewAddedAFCompanies(kpi
				.getNewRegCompaniesAffiliationRegion(yearStarDate, endDate));
		data.setLastYearNewAddedAFCompanies(kpi
				.getNewRegCompaniesAffiliationRegion(lastYearStarDate,
						lastYearEndDate));
		data.setYearNewSignedOffAFCompanies(kpi.getNewCancellationAFCompanies(
				yearStarDate, endDate));
		data.setAllRegAssetsAF(kpi.getAllRegAssetsAF());
		data.setOperatedAFCompanies(kpi.getTaxedCompanyAffiliateRegion(year,
				month));
		data.setYearSalesAF(kpi.getYearTotalSalesByAffiliateRegion(year, month));
		data.setLastYearSalesAF(kpi.getYearTotalSalesByAffiliateRegion(
				year - 1, month));
		data.setCheckedTaxAFCompanies(kpi.getCheckedTaxAFCompanyEconomyEntity(
				year, month));
		data.setMonthAFTax(kpi.getMonthTotalTaxByAffiliateRegion(year, month));
		data.setLastYearAFTax(kpi.getMonthTotalTaxByAffiliateRegion(year - 1,
				month));
		return data;
	}

	@Override
	public List<TaxData> getMonthTaxDataByBlock(int year, int month,
			AffiliateBlock block) {
		return totalTax.getTaxListByAffiliateBlock(year, month,
				SystemEnumeration.getInstance().getIdByKey(block));
	}

}
