// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.report.form;

import java.util.Calendar;
import java.util.List;

import com.mcworkshop.common.web.report.BaseReport;
import com.mcworkshop.dongjing.service.report.KPIReport;
import com.mcworkshop.dongjing.service.report.model.KPIReportData;

/**
 * @author $Author$
 * 
 */
public class ExportKPIReportForm extends BaseExportReportForm<KPIReportData> {

	private static final long serialVersionUID = 1L;

	public ExportKPIReportForm(String id, List<Calendar> months) {
		super(id, "松江区私营经济区主要指标月报表.xlsx", months);
	}

	@Override
	protected BaseReport<KPIReportData> getReport() {
		return new KPIReport(reportService.getKPIReportDate(month.getObject()
				.get(Calendar.YEAR), month.getObject().get(Calendar.MONTH) + 1),
				month.getObject().get(Calendar.YEAR), month.getObject().get(
						Calendar.MONTH) + 1);
	}

}
