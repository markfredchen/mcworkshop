// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.report.form;

import java.util.Calendar;
import java.util.List;

import com.mcworkshop.common.web.report.BaseReport;
import com.mcworkshop.dongjing.service.report.CombinedKPIReport;
import com.mcworkshop.dongjing.service.report.model.KPIReportData;

/**
 * @author $Author$
 * 
 */
public class ExportCombinedKPIReport extends
		BaseExportReportForm<KPIReportData> {

	private static final long serialVersionUID = 1L;

	public ExportCombinedKPIReport(String id, List<Calendar> months) {
		super(id, "洞泾镇百颗星私营经济区企业注册经营情况.xlsx", months);
	}

	@Override
	protected BaseReport<KPIReportData> getReport() {
		return new CombinedKPIReport(reportService.getCombineKPIReportData(
				month.getObject().get(Calendar.YEAR),
				month.getObject().get(Calendar.MONTH) + 1), month.getObject().get(
				Calendar.YEAR), month.getObject().get(Calendar.MONTH) + 1);
	}

}
