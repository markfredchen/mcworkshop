// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.tax;

import com.google.inject.Inject;
import com.mcworkshop.common.exception.ExceptionUtil;
import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.web.component.dropdownchoice.SystemEnumerationDropdownChoiceRender;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerOption;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerPlugin;
import com.mcworkshop.common.web.component.form.datetimepicker.View;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.AffiliateBlock;
import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.domain.TaxData;
import com.mcworkshop.dongjing.security.ACLBlockUtil;
import com.mcworkshop.dongjing.service.DJService;
import com.mcworkshop.dongjing.service.ReportService;
import com.mcworkshop.dongjing.service.report.TaxBlockReport;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author $Author$
 * 
 */
public class CompanyTaxDetailPage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJService service;

	@Inject
	private ReportService report;

	private Form<Void> exportForm;
	private DateTextField exportMonthSeletor;
	private DropDownChoice<AffiliateBlock> blockChoice;

	private TaxData taxData;

	private Form<Void> searchForm;
	private CompanySuggestionTextFeild companyNameTextField;
	private DateTextField monthSeletor;
	private CompanyTaxDetailPanel detailPanel;
	private FeedbackPanel feedbackPanel;

	private static final String DATE_PATTERN = "mm-yyyy";
	private DatetimePickerOption datePickerOption = new DatetimePickerOption(
			DATE_PATTERN);
	private DownloadLink downloadReportLink;

	private Model<Date> monthModel;
	private Model<AffiliateBlock> blockModel;

	public CompanyTaxDetailPage(PageParameters params) {
		super(params);
		datePickerOption.setMinView(View.YEAR);
		datePickerOption.setStartView(View.YEAR);
		datePickerOption.setMaxView(View.DECADE);
		datePickerOption.setLanguage("zh-CN");

		exportForm = new Form<Void>("export-tax-form");
		exportForm.setOutputMarkupId(true);
		add(exportForm);
		exportForm.add(exportMonthSeletor = new DateTextField("export-month",
				monthModel = new Model<Date>(), "MM-yyyy"));
		exportMonthSeletor.add(new DatetimePickerPlugin(datePickerOption));
		List<AffiliateBlock> blocks = new ArrayList<AffiliateBlock>();
		blockModel = new Model<AffiliateBlock>();
		if (ACLBlockUtil.getOwnAffiliateBlock() != null) {
			AffiliateBlock block = ACLBlockUtil.getOwnAffiliateBlock();
			blocks.add(block);
			blockModel = new Model<AffiliateBlock>(block);
		} else {
			blocks.addAll(SystemEnumeration.getInstance().getKeysByType(
					AffiliateBlock.class));
		}
		exportForm.add(blockChoice = new DropDownChoice<AffiliateBlock>(
				"block", blockModel, blocks,
				new SystemEnumerationDropdownChoiceRender()));
		exportMonthSeletor
				.add(new AjaxFormComponentUpdatingBehavior("onchange") {

					private static final long serialVersionUID = 1L;

					@Override
					protected void onUpdate(AjaxRequestTarget target) {
						if (monthModel.getObject() == null
								|| blockModel.getObject() == null) {
							downloadReportLink.setEnabled(false);
						} else {
							downloadReportLink.setEnabled(true);
						}
						target.add(exportForm);
					}
				});
		blockChoice.add(new AjaxFormComponentUpdatingBehavior("onchange") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if (monthModel.getObject() == null
						|| blockModel.getObject() == null) {
					downloadReportLink.setEnabled(false);
				} else {
					downloadReportLink.setEnabled(true);
				}
				target.add(exportForm);
			}
		});
		exportForm.add(downloadReportLink = new DownloadLink("submit-link",
				new AbstractReadOnlyModel<File>() {

					private static final long serialVersionUID = 1L;

					@Override
					public File getObject() {
						Date monthDate = monthModel.getObject();
						AffiliateBlock block = blockModel.getObject();
						Calendar cal = Calendar.getInstance();
						cal.setTime(monthDate);
						int year = cal.get(Calendar.YEAR);
						int month = cal.get(Calendar.MONTH) + 1;
						List<TaxData> taxData = report.getMonthTaxDataByBlock(
								year, month, block);
						TaxBlockReport report = new TaxBlockReport(taxData,
								year, month, block);

						try {
							return report.generateReportByFile(true);
						} catch (Throwable e) {
							throw ExceptionUtil.handleRuntimeException(
									"generate report fail: 区块税收报表", e);
						}
					}
				}, "区块税收报表.xlsx"));

		taxData = new TaxData();
		searchForm = new Form<Void>("search-company-text-form");
		add(searchForm);
		searchForm.add(companyNameTextField = new CompanySuggestionTextFeild(
				"name", new Model<Company>(), false));
		searchForm.add(monthSeletor = new DateTextField("month",
				new Model<Date>(), "MM-yyyy"));
		monthSeletor.add(new DatetimePickerPlugin(datePickerOption));
		searchForm.add(new AjaxSubmitLink("select-month", searchForm) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Company company = companyNameTextField.getConvertedInput();
				Date monthDate = monthSeletor.getConvertedInput();
				if (company == null) {
					getSession().error("请选择查询企业");
					detailPanel
							.setDefaultModel(new CompoundPropertyModel<TaxData>(
									new TaxData()));
					target.appendJavaScript("$('#tax-feedback').modal('show')");
				} else if (monthDate == null) {
					getSession().error("请选择税收月份");
					detailPanel
							.setDefaultModel(new CompoundPropertyModel<TaxData>(
									new TaxData()));
					target.appendJavaScript("$('#tax-feedback').modal('show')");
				} else {
					Calendar cal = Calendar.getInstance();
					cal.setTime(monthDate);
					int year = cal.get(Calendar.YEAR);
					int month = cal.get(Calendar.MONTH) + 1;

					TaxData taxData = service.getTaxDetail(year, month,
							company.getCompanyID());
					if (taxData != null) {
						detailPanel
								.setDefaultModel(new CompoundPropertyModel<TaxData>(
										taxData));
					} else {
						getSession().error(
								"该企业" + year + "年" + month + "月没有税收数据");
						detailPanel
								.setDefaultModel(new CompoundPropertyModel<TaxData>(
										new TaxData()));
						target.appendJavaScript("$('#tax-feedback').modal('show')");
					}
				}
				target.add(feedbackPanel);
				target.add(detailPanel);

			}
		});
		add(feedbackPanel = new FeedbackPanel("feedback"));
		feedbackPanel.setOutputMarkupId(true);
		add(detailPanel = new CompanyTaxDetailPanel("tax-detail",
				new CompoundPropertyModel<TaxData>(taxData)));
		detailPanel.setOutputMarkupId(true);
	}

	@Override
	protected void onBeforeRender() {
		if (monthModel.getObject() == null || blockModel.getObject() == null) {
			downloadReportLink.setEnabled(false);
		} else {
			downloadReportLink.setEnabled(true);
		}
		super.onBeforeRender();
	}

	@Override
	protected Panel getLeftNaviPanel(String id) {
		return new TaxLeftNavigationPanel(id);
	}

	@Override
	protected String getPageClassName() {
		return "company-tax-detail";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return WicketMessageUtil.getResourceModel("dj.menu.tax.management");
	}
}
