// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.report;

import com.google.inject.Inject;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.service.DJService;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import com.mcworkshop.dongjing.web.page.report.form.*;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author $Author$
 * 
 */
public class CompanyTaxMonthlyReportPage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJService service;

	public CompanyTaxMonthlyReportPage(PageParameters params) {
		super(params);
		List<Calendar> months = service.getSaleTaxReportAvailableMonths();
		if (months.size() > 0) {
			Collections.sort(months, new Comparator<Calendar>() {

				@Override
				public int compare(Calendar c1, Calendar c2) {
					if (c1.get(Calendar.YEAR) < c2.get(Calendar.YEAR)) {
						return 1;
					} else if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
						if (c1.get(Calendar.MONTH) < c2.get(Calendar.MONTH)) {
							return 1;
						} else if (c1.get(Calendar.MONTH) == c2
								.get(Calendar.MONTH)) {
							return 0;
						} else {
							return -1;
						}
					} else {
						return -1;
					}
				}
			});
		}
		add(new ExportCombineStatisicsForm("export-combined-report-container",
				months));
		add(new ExportLocalExternalTaxIncomingForm(
				"export-local-external-report-container", months));
		add(new ExportTaxMonthEconomyEntityReportForm(
				"export-tax-economy-entity-report-container", months));
		add(new ExportRegionTaxDetailReportForm(
				"export-region-tax-report-container", months));
		add(new ExportTotalTaxReport("export-total-tax-report-container",
				months));
		add(new ExportTaxOrgReportForm("export-tax-org-report-container",
				months));
		add(new ExportKPIReportForm("export-kpi-report-container", months));
		add(new ExportCombinedKPIReport("export-combined-kpi-report-container",
				months));
	}

	@Override
	protected Panel getLeftNaviPanel(String id) {
		return new ReportLeftNavigatorPanel(id);
	}

	@Override
	protected String getPageClassName() {
		return "report";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return WicketMessageUtil.getResourceModel("dj.report.company.report");
	}
}
