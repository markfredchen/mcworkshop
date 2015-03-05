package com.mcworkshop.dongjing.web.page.report.panel;

import java.util.Calendar;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.google.inject.Inject;
import com.mcworkshop.dongjing.service.DJService;

/**
 * Created by MarkfredChen on 6/25/2014.
 */
abstract public class BaseChartReportPanel extends Panel {

	private static final long serialVersionUID = 1L;

	protected final WebMarkupContainer overallReportContainer;
	private final DropDownChoice<Calendar> yearSelect;
	protected Model<Calendar> month = new Model<Calendar>();;
	@Inject
	protected DJService service;
	private WebMarkupContainer monthSelector;
	private WebMarkupContainer dialogContainer;

	public BaseChartReportPanel(String id, List<Calendar> months) {
		super(id);
		overallReportContainer = new WebMarkupContainer("overall-report");
		overallReportContainer.setOutputMarkupId(true);
		overallReportContainer.add(new Label("report-title", getReportTitle()));
		add(overallReportContainer);
		monthSelector = new WebMarkupContainer("month-selector");
		overallReportContainer.add(monthSelector);
		monthSelector.setOutputMarkupId(true);
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
		monthSelector.add(yearSelect);

		dialogContainer = new WebMarkupContainer("report-dialog");
		dialogContainer.setOutputMarkupId(true);
		dialogContainer.setMarkupId(getReportDialogID());
		dialogContainer.add(new Label("report-title", getReportTitle()));
		overallReportContainer.add(dialogContainer);
		WebMarkupContainer reportChartContainer = new WebMarkupContainer(
				"report-chart");
		reportChartContainer.setMarkupId(getReportChartID());
		dialogContainer.add(reportChartContainer);
		final Label reportData = new Label("report-data");
		dialogContainer.add(reportData);
		reportData.setMarkupId(getReportDataID());

		monthSelector.add(new AjaxLink<Void>("show-chart-btn") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				reportData.setDefaultModel(new Model<String>(getReportData(
						month.getObject().get(Calendar.YEAR), month.getObject()
								.get(Calendar.MONTH)) + 1));
				target.add(dialogContainer);
				target.appendJavaScript("new DongJing().showReport('"
						+ getReportChartID() + "', '" + getReportDialogID()
						+ "', '" + getReportDataID() + "')");
			}
		});

	}

	abstract protected String getReportID();

	abstract protected String getReportTitle();

	abstract protected String getReportData(int year, int month);

	protected String getReportDialogID() {
		return getReportID() + "-dialog";
	}

	protected String getReportChartID() {
		return getReportID() + "-chart";
	}

	protected String getReportDataID() {
		return getReportID() + "-data";
	}

}
