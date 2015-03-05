// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.report.form;

import java.util.Calendar;
import java.util.List;

import com.google.inject.Inject;
import com.mcworkshop.common.web.report.BaseReport;
import com.mcworkshop.dongjing.service.DJService;
import com.mcworkshop.dongjing.service.report.LocalExternalTaxIncomingReport;
import com.mcworkshop.dongjing.service.report.model.OverallReportData;

/**
 * @author $Author$
 * 
 */
public class ExportLocalExternalTaxIncomingForm extends
		BaseExportReportForm<OverallReportData> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJService service;

	public ExportLocalExternalTaxIncomingForm(String id, List<Calendar> months) {
		super(id, "百颗星经济区内外税入库汇总.xlsx", months);
	}

	@Override
	protected BaseReport<OverallReportData> getReport() {
		OverallReportData data = service.getOverallMonthReportData(month
				.getObject().get(Calendar.YEAR),
				month.getObject().get(Calendar.MONTH) + 1);
		return new LocalExternalTaxIncomingReport(data, month.getObject().get(
				Calendar.YEAR), month.getObject().get(Calendar.MONTH) + 1);
	}
}
