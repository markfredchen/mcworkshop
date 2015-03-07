// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.report.form;

import com.mcworkshop.common.web.report.BaseReport;
import com.mcworkshop.dongjing.service.report.TaxOrgReport;
import com.mcworkshop.dongjing.service.report.model.TaxOrgData;

import java.util.Calendar;
import java.util.List;

/**
 * @author $Author$
 * 
 */
public class ExportTaxOrgReportForm extends BaseExportReportForm<TaxOrgData> {

	private static final long serialVersionUID = 1L;

	public ExportTaxOrgReportForm(String id, List<Calendar> months) {
		super(id, "私营税收分解表.xlsx", months);
	}

	@Override
	protected BaseReport<TaxOrgData> getReport() {
		return new TaxOrgReport(reportService.getTaxOrgTaxDataReport(month
				.getObject().get(Calendar.YEAR),
				month.getObject().get(Calendar.MONTH) + 1), month.getObject().get(
				Calendar.YEAR), month.getObject().get(Calendar.MONTH) + 1);
	}

}
