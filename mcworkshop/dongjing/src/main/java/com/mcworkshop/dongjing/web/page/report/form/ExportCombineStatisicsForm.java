// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.report.form;

import java.util.Calendar;
import java.util.List;

import com.google.inject.Inject;
import com.mcworkshop.common.web.report.BaseReport;
import com.mcworkshop.dongjing.service.DJService;
import com.mcworkshop.dongjing.service.report.OverallMonthlyReport;
import com.mcworkshop.dongjing.service.report.model.OverallReportData;

/**
 * @author $Author$
 * 
 */
public class ExportCombineStatisicsForm extends
		BaseExportReportForm<OverallReportData> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJService service;

	public ExportCombineStatisicsForm(String id, List<Calendar> months) {
		super(id, "松江经济小区综合情况月报表.xlsx", months);
	}

	@Override
	protected BaseReport<OverallReportData> getReport() {
		OverallReportData data = service.getOverallMonthReportData(month
				.getObject().get(Calendar.YEAR),
				month.getObject().get(Calendar.MONTH) + 1);
		return new OverallMonthlyReport(data, month.getObject().get(
				Calendar.YEAR), month.getObject().get(Calendar.MONTH) + 1);
	}
}