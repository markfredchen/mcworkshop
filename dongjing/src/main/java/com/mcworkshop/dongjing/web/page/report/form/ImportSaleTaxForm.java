// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.report.form;

import com.google.inject.Inject;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.service.DJService;
import com.mcworkshop.dongjing.service.importdata.TaxDataXLSHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author $Author$
 * 
 */
public class ImportSaleTaxForm extends Panel {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ImportSaleTaxForm.class);

	@Inject
	private DJService service;

	private Model<Integer> importYear = new Model<Integer>(Calendar
			.getInstance().get(Calendar.YEAR));
	private Model<Integer> importMonth = new Model<Integer>(Calendar
			.getInstance().get(Calendar.MONTH));
	private FileUploadField upload;

	final Form<Void> form;

	public ImportSaleTaxForm(String id) {
		super(id);
		form = new Form<Void>("import-data-form") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onValidate() {
				if (upload.getFileUpload() == null) {
					form.error(WicketMessageUtil
							.getResourceString("Report.File.Required"));
				}
				super.onValidate();
			}

		};
		form.setOutputMarkupId(true);
		add(form);
		form.add(new FeedbackPanel("feedback"));
		final WebMarkupContainer importMonthSelector = new WebMarkupContainer(
				"month-selector");
		importMonthSelector.setOutputMarkupId(true);
		form.add(importMonthSelector);
		final DropDownChoice<Integer> importMonthSelect = new DropDownChoice<Integer>(
				"month-select", importMonth,
				new LoadableDetachableModel<List<Integer>>() {

					private static final long serialVersionUID = 1L;

					@Override
					protected List<Integer> load() {
						// Special logic for current year and month. Month in
						// the future should not be listed.
						List<Integer> months = new ArrayList<Integer>();
						int maxMonth = 12;
						if (importYear.getObject() == Calendar.getInstance()
								.get(Calendar.YEAR)) {
							maxMonth = Calendar.getInstance().get(
									Calendar.MONTH);
						}
						for (int i = 1; i <= maxMonth; i++) {
							months.add(i);
						}
						return months;
					}
				}) {

			private static final long serialVersionUID = 1L;

			@Override
			protected boolean wantOnSelectionChangedNotifications() {
				// Return true to allow year select to update list
				return true;
			}

		};
		importMonthSelector.add(importMonthSelect);
		final DropDownChoice<Integer> importYearSelect = new DropDownChoice<Integer>(
				"year-select", importYear,
				new AbstractReadOnlyModel<List<Integer>>() {

					private static final long serialVersionUID = 1L;

					@Override
					public List<Integer> getObject() {
						List<Integer> yearList = new ArrayList<Integer>();
						for (int i = 0; i < 6; i++) {
							yearList.add(Calendar.getInstance().get(
									Calendar.YEAR)
									- i);
						}
						return yearList;
					}
				});
		importMonthSelector.add(importYearSelect);
		importYearSelect.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				importMonth.setObject(1);
				target.add(importMonthSelector);
			}
		});
		upload = new FileUploadField("tax-report-data");
		form.add(upload);
		form.add(new AjaxSubmitLink("import-sale-tax-report", form) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(AjaxRequestTarget target, Form<?> form) {
				String fileName = upload.getFileUpload().getClientFileName();
				if (fileName.endsWith(".xls")) {
					try {
						service.clearCompanySaleTaxData(
								importYearSelect.getModelObject(),
								importMonthSelect.getModelObject());
						TaxDataXLSHandler handler = new TaxDataXLSHandler(
								upload.getFileUpload().getInputStream(),
								importYearSelect.getModelObject(),
								importMonthSelect.getModelObject(), service);
						handler.process();
						service.importCompanyCombinedDate(
								importYearSelect.getModelObject(),
								importMonthSelect.getModelObject());
						if (handler.getFailedCompanies().size() > 0) {
							form.error("数据导入完毕，以下企业未找到对应企业基础信息数据");
							List<String> errors = handler.getFailedCompanies();
							for (String error : errors) {
								form.error(error);
							}
						} else {
							form.success("数据导入完毕。");
						}
					} catch (Throwable e) {
						log.error(e);
						form.error(WicketMessageUtil
								.getResourceString("report.data.import.fail"));
					}
				} else {
					form.error(WicketMessageUtil
							.getResourceString("report.data.import.file.not.support"));
				}
				target.add(form);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.appendJavaScript("$('.feedback').attr('class', 'feedback alert alert-danger')");
				target.add(form);
			}
		});
	}

}
