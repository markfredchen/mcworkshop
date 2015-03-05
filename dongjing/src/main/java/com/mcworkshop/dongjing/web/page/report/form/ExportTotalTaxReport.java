// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.report.form;

import java.util.Calendar;
import java.util.List;

import com.mcworkshop.common.web.report.BaseReport;
import com.mcworkshop.dongjing.domain.TaxData;
import com.mcworkshop.dongjing.service.report.TotalTaxReport;

/**
 * @author $Author$
 * 
 */
public class ExportTotalTaxReport extends BaseExportReportForm<TaxData> {

	private static final long serialVersionUID = 1L;

	public ExportTotalTaxReport(String id, List<Calendar> months) {
		super(id, "松江区百颗星私营经济小区税收入库汇总.xlsx", months);
	}

	@Override
	protected BaseReport<TaxData> getReport() {
		return new TotalTaxReport(reportService.getTotalTaxData(month
				.getObject().get(Calendar.YEAR),
				month.getObject().get(Calendar.MONTH) + 1), month.getObject().get(
				Calendar.YEAR), month.getObject().get(Calendar.MONTH) + 1);
	}
}
