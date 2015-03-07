// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service;

import com.mcworkshop.dongjing.domain.AffiliateBlock;
import com.mcworkshop.dongjing.domain.TaxData;
import com.mcworkshop.dongjing.service.report.model.EconomyEntityTaxData;
import com.mcworkshop.dongjing.service.report.model.KPIReportData;
import com.mcworkshop.dongjing.service.report.model.TaxOrgData;

import java.util.List;

/**
 * @author $Author$
 * 
 */
public interface ReportService {

	EconomyEntityTaxData getTaxMonthEconomyEntityReport(int year, int month);

	TaxData getTotalTaxData(int year, int month);

	TaxOrgData getTaxOrgTaxDataReport(int year, int month);

	KPIReportData getKPIReportDate(int year, int month);

	KPIReportData getCombineKPIReportData(int year, int month);

	List<TaxData> getMonthTaxDataByBlock(int year, int month,
			AffiliateBlock block);
}
