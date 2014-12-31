// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.report.form;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;

import com.google.inject.Inject;
import com.mcworkshop.common.exception.ExceptionUtil;
import com.mcworkshop.common.web.report.BaseReport;
import com.mcworkshop.dongjing.service.DJService;
import com.mcworkshop.dongjing.service.ReportService;

/**
 * @author $Author$
 * 
 */
abstract public class BaseExportReportForm<T extends Object> extends Panel {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJService service;

	@Inject
	protected ReportService reportService;

	private final Form<Void> exportReportContainer;
	private final WebMarkupContainer monthSelector;
	private final DropDownChoice<Calendar> yearSelect;
	private DownloadLink exportLink;
	protected Model<Calendar> month = new Model<Calendar>();

	public BaseExportReportForm(String id, final String fileName,
			List<Calendar> months) {
		super(id);
		exportReportContainer = new Form<Void>("export-report");
		exportReportContainer.setOutputMarkupId(true);
		add(exportReportContainer);
		monthSelector = new WebMarkupContainer("month-selector");
		exportReportContainer.add(monthSelector);
		exportReportContainer.setEnabled(months.size() != 0);
		monthSelector.setOutputMarkupId(true);
		if (months.size() > 0) {
			month.setObject(months.get(0));
		}
		yearSelect = new DropDownChoice<Calendar>("year-select", month, months,
				new IChoiceRenderer<Calendar>() {

					private static final long serialVersionUID = 1L;

					@Override
					public Object getDisplayValue(Calendar c) {
						return c.get(Calendar.YEAR) + "年"
								+ (c.get(Calendar.MONTH) + 1) + "月";
					}

					@Override
					public String getIdValue(Calendar c, int index) {
						return index + "-" + c.get(Calendar.YEAR) + "-"
								+ c.get(Calendar.MONTH);
					}
				}) {

			private static final long serialVersionUID = 1L;

			@Override
			protected boolean wantOnSelectionChangedNotifications() {
				return true;
			}

		};

		yearSelect.setEnabled(months.size() > 0);
		monthSelector.add(yearSelect);

		exportLink = new DownloadLink("export-report-btn",
				new AbstractReadOnlyModel<File>() {

					private static final long serialVersionUID = 1L;

					@Override
					public File getObject() {
						BaseReport<T> report = getReport();
						try {
							return report.generateReportByFile(true);
						} catch (Throwable e) {
							throw ExceptionUtil.handleRuntimeException(
									"generate report fail: " + fileName, e);
						}
					}
				}, fileName);
		exportReportContainer.add(exportLink);
	}

	abstract protected BaseReport<T> getReport();

}