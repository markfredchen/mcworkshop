// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.project;

import java.util.Arrays;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Inject;
import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.web.component.dropdownchoice.SystemEnumerationDropdownChoiceRender;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerOption;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerPlugin;
import com.mcworkshop.common.web.component.form.datetimepicker.View;
import com.mcworkshop.common.web.component.validation.FormFieldValidationContainer;
import com.mcworkshop.common.web.util.WebUtil;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.domain.ConstructType;
import com.mcworkshop.dongjing.domain.Project;
import com.mcworkshop.dongjing.domain.ProjectStatus;
import com.mcworkshop.dongjing.service.DJService;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import com.mcworkshop.dongjing.web.page.building.BuildingPage;
import com.mcworkshop.dongjing.web.page.company.tax.CompanySuggestionTextFeild;

/**
 * @author $Author$
 * 
 */
public class UpsertProjectInfoPage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJService service;

	private Project project;
	private Form<Project> form;

	private DropDownChoice<ProjectStatus> status;

	private WebMarkupContainer auditted;
	private WebMarkupContainer afterAuditted;

	private static final String DATE_PATTERN = "mm-yyyy";
	private DatetimePickerOption datePickerOption = new DatetimePickerOption(
			DATE_PATTERN);

	private CompanySuggestionTextFeild companySuggestion;
	private WebMarkupContainer companyListContainer;

	public UpsertProjectInfoPage(PageParameters params) {
		super(params);
		final Long projectID = WebUtil.getParameterAsLong("projectID");
		project = new Project();
		if (projectID != null) {
			project = service.getProject(projectID);
		}
		datePickerOption.setMinView(View.YEAR);
		datePickerOption.setStartView(View.YEAR);
		datePickerOption.setMaxView(View.DECADE);
		datePickerOption.setLanguage("zh-CN");

		form = new Form<Project>("upsert-project",
				new CompoundPropertyModel<Project>(project));
		form.setOutputMarkupId(true);
		form.add(new FormFieldValidationContainer("name-feedback",
				new RequiredTextField<String>("name")));
		form.add(new FormFieldValidationContainer("status-feedback",
				status = new DropDownChoice<ProjectStatus>("status",
						SystemEnumeration.getInstance().getKeysByType(
								ProjectStatus.class),
						new SystemEnumerationDropdownChoiceRender())));
		status.setRequired(true);
		form.add(new DropDownChoice<Boolean>("isCommercial", Arrays.asList(
				Boolean.TRUE, Boolean.FALSE), new IsCommercialRender()));
		final Form<Company> addCompanyForm = new Form<Company>(
				"add-company-form", new Model<Company>());
		form.add(addCompanyForm);
		addCompanyForm.add(companySuggestion = new CompanySuggestionTextFeild(
				"relatedCompany", new Model<Company>(), false));
		addCompanyForm.add(new AjaxSubmitLink("add-company-link",
				addCompanyForm) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Company company = companySuggestion.getConvertedInput();
				Project project = (Project) UpsertProjectInfoPage.this.form
						.getDefaultModelObject();
				project.getRelatedCompanies().add(company);
				companySuggestion.setDefaultModel(new Model<Company>());
				target.add(companyListContainer);
				target.add(addCompanyForm);
			}
		});
		form.add(companyListContainer = new WebMarkupContainer(
				"related-company-list"));
		companyListContainer.setOutputMarkupId(true);
		ListView<Company> companyListView = new ListView<Company>(
				"relatedCompanies", form.getModelObject().getRelatedCompanies()) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Company> item) {
				final Company company = item.getModelObject();
				item.add(new Label("company-name", company.getName()));
				item.add(new AjaxLink<Void>("delete-icon") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						Long companyID = company.getCompanyID();

						Project project = (Project) form
								.getDefaultModelObject();
						for (Company c : project.getRelatedCompanies()) {
							if (c.getCompanyID() == companyID) {
								project.getRelatedCompanies().remove(c);
								break;
							}
						}
						target.add(companyListContainer);
					}
				});

			}
		};
		companyListContainer.add(companyListView);
		status.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				ProjectStatus currentStatus = status.getModel().getObject();
				if (currentStatus.equals(ProjectStatus.AUDITTED)) {
					afterAuditted.setVisible(false);
					auditted.setVisible(true);
				} else {
					afterAuditted.setVisible(true);
					auditted.setVisible(false);
				}
				target.add(form);
			}
		});

		afterAuditted = new WebMarkupContainer("after-auditted");
		afterAuditted.setOutputMarkupId(true);
		form.add(afterAuditted);
		afterAuditted.add(new TextField<String>("product"));
		afterAuditted.add(new DropDownChoice<ConstructType>("constructType",
				SystemEnumeration.getInstance().getKeysByType(
						ConstructType.class),
				new SystemEnumerationDropdownChoiceRender()));
		afterAuditted.add(new TextField<Double>("area"));
		afterAuditted.add(new TextField<Double>("actualArea"));
		afterAuditted.add(new TextField<Double>("constructionArea"));
		afterAuditted.add(new TextField<String>("numberOfBuilding"));
		afterAuditted.add(new TextField<Double>("totalInvestment"));
		afterAuditted.add(new TextField<Double>("fixInvestment"));
		afterAuditted.add(new TextField<Double>("sales"));
		afterAuditted.add(new TextField<Double>("profit"));
		afterAuditted.add(new TextField<Double>("tax"));
		afterAuditted.add(new DateTextField("startDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		afterAuditted.add(new DateTextField("endDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		afterAuditted.add(new DateTextField("produceDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		afterAuditted.add(new TextField<String>("progress"));
		afterAuditted.add(new TextField<String>("problem"));
		afterAuditted.add(new TextField<String>("contactor"));
		afterAuditted.add(new TextField<String>("phone"));
		afterAuditted.add(new TextField<String>("email"));
		afterAuditted.add(new TextField<String>("fax"));
		afterAuditted.add(new TextField<String>("comment"));

		auditted = new WebMarkupContainer("auditted");
		auditted.setOutputMarkupId(true);
		form.add(auditted);
		auditted.add(new TextField<Integer>("area"));
		auditted.add(new TextField<Integer>("constructionArea"));
		auditted.add(new TextField<String>("product"));
		auditted.add(new TextField<Integer>("domesticInvestment"));
		auditted.add(new TextField<Integer>("foreignInvestment"));
		auditted.add(new TextField<Integer>("fixInvestment"));
		auditted.add(new TextField<Double>("sales"));
		auditted.add(new TextField<Double>("profit"));
		auditted.add(new TextField<Double>("tax"));
		auditted.add(new DateTextField("auditPassDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		auditted.add(new TextField<String>("contactor"));
		auditted.add(new TextField<String>("phone"));
		auditted.add(new TextField<String>("email"));
		auditted.add(new TextField<String>("fax"));
		auditted.add(new TextField<String>("comment"));

		add(form);

		add(new BookmarkablePageLink<Void>("cancel-link", BuildingPage.class));
		add(new AjaxSubmitLink("submit-link", form) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Project project = (Project) form.getModelObject();
				if (project.getProjectID() == null) {
					service.createProject(project);
				} else {
					service.updateProject(project);
				}
				setResponsePage(ProjectListPage.class);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(form);
			}

		});
	}

	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		if (project.getProjectID() == null) {
			afterAuditted.setVisible(false);
			auditted.setVisible(false);
		} else {
			if (project.getStatus() != null) {
				if (project.getStatus().equals(ProjectStatus.AUDITTED)) {
					afterAuditted.setVisible(false);
					auditted.setVisible(true);
				} else {
					afterAuditted.setVisible(true);
					auditted.setVisible(false);
				}
			}
		}
	}

	@Override
	protected Panel getLeftNaviPanel(String id) {
		return new ProjectLeftNaviPanel(id);
	}

	@Override
	protected String getPageClassName() {
		return "project";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return WicketMessageUtil.getResourceModel("project.module.title");
	}

}
