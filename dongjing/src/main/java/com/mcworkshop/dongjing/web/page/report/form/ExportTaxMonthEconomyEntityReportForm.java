// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.report.form;

import java.util.Calendar;
import java.util.List;

import com.google.inject.Inject;
import com.mcworkshop.common.web.report.BaseReport;
import com.mcworkshop.dongjing.service.ReportService;
import com.mcworkshop.dongjing.service.report.TaxMonthEconomyEntityReport;
import com.mcworkshop.dongjing.service.report.model.EconomyEntityTaxData;

/**
 * @author $Author$
 * 
 */
public class ExportTaxMonthEconomyEntityReportForm extends
		BaseExportReportForm<EconomyEntityTaxData> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ReportService reportService;

	public ExportTaxMonthEconomyEntityReportForm(String id,
			List<Calendar> months) {
		super(id, "百颗星私营开发区税收月报表.xlsx", months);
	}

	@Override
	protected BaseReport<EconomyEntityTaxData> getReport() {
		EconomyEntityTaxData data = reportService
				.getTaxMonthEconomyEntityReport(
						month.getObject().get(Calendar.YEAR), month.getObject()
								.get(Calendar.MONTH) + 1);
		return new TaxMonthEconomyEntityReport(data, month.getObject().get(
				Calendar.YEAR), month.getObject().get(Calendar.MONTH) + 1);
	}

}