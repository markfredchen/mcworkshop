// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.basic;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Inject;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.authorization.Roles;
import com.mcworkshop.dongjing.service.DJService;
import com.mcworkshop.dongjing.service.importdata.CompanyXLSHandler;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import com.mcworkshop.dongjing.web.page.company.CompanyLeftNaviPanel;

/**
 * @author $Author$
 * 
 */
@AuthorizeInstantiation({ Roles.CM_IMPORT })
public class ImportCompanyPage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	private Form<Void> importForm;
	private FileUploadField upload;

	@Inject
	private DJService service;

	public ImportCompanyPage(PageParameters params) {
		super(params);
		importForm = new Form<Void>("import-data-form") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onValidate() {
				if (upload.getFileUpload() == null) {
					importForm.error(WicketMessageUtil
							.getResourceString("Report.File.Required"));
				}
				super.onValidate();
			}
		};
		importForm.setOutputMarkupId(true);
		add(importForm);
		importForm.add(new FeedbackPanel("feedback"));
		upload = new FileUploadField("company-basic-import");
		importForm.add(upload);
		importForm.add(new AjaxSubmitLink("import-company-basic", importForm) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(AjaxRequestTarget target, Form<?> form) {
				String fileName = upload.getFileUpload().getClientFileName();
				if (fileName.endsWith(".xls")) {
					try {
						new CompanyXLSHandler(upload.getFileUpload()
								.getInputStream(), service).process();
						setResponsePage(CompanyListPage.class);
					} catch (Throwable e) {
						form.error(WicketMessageUtil
								.getResourceString("report.data.import.fail"));
						e.printStackTrace();
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

	@Override
	protected Panel getLeftNaviPanel(String id) {
		return new CompanyLeftNaviPanel(id);
	}

	@Override
	protected String getPageClassName() {
		return "company-basic-import";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return WicketMessageUtil
				.getResourceModel("dj.company.management.title");
	}

}
