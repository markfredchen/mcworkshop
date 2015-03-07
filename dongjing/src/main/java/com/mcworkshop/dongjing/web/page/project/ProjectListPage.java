// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.project;

import com.google.inject.Inject;
import com.mcworkshop.common.exception.ExceptionUtil;
import com.mcworkshop.common.web.component.pagination.PaginationNavigator;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.Project;
import com.mcworkshop.dongjing.persistence.DJServiceMapper;
import com.mcworkshop.dongjing.service.DJService;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.io.File;

/**
 * @author $Author$
 * 
 */
public class ProjectListPage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJService service;

	@Inject
	private DJServiceMapper mapper;

	private ProjectDataProvider provider = new ProjectDataProvider(service);

	private final WebMarkupContainer listContainer;
	private final TextField<String> projectName;

	public ProjectListPage(PageParameters params) {
		super(params);
		Form<Void> searchForm = new Form<Void>("search-project-form");
		add(searchForm);
		searchForm.add(projectName = new TextField<String>("projectName",
				new Model<String>()));
		searchForm.add(new AjaxSubmitLink("search-project") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				provider.setProjectName(projectName.getValue());
				// provider.setStatus(status.getModelObject());
				target.add(listContainer);
			}
		});

		final DataView<Project> companyList = new DataView<Project>(
				"project-list", provider) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<Project> item) {
				Project project = item.getModelObject();
				item.add(new Label("name", project.getName()));
				item.add(new Label("status", WicketMessageUtil
						.getResourceString(project.getStatus().getMessageKey())));
				item.add(new AjaxEventBehavior("onclick") {
					private static final long serialVersionUID = 1L;

					@Override
					protected void onEvent(AjaxRequestTarget target) {
						Long projectID = item.getModelObject().getProjectID();
						PageParameters params = new PageParameters();
						params.add("projectID", projectID);
						setResponsePage(UpsertProjectInfoPage.class, params);
					}
				});
			}
		};
		companyList.setOutputMarkupId(true);
		companyList.setItemsPerPage(10);

		final PaginationNavigator navigator = new PaginationNavigator(
				"navigator", companyList);
		final WebMarkupContainer emptyList = new WebMarkupContainer(
				"empty-list");

		listContainer = new WebMarkupContainer("paging-container") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				super.onBeforeRender();
				long companyListSize = provider.size();
				companyList.setVisible(companyListSize > 0);
				navigator.setVisible(companyListSize > 0);
				emptyList.setVisible(companyListSize <= 0);
			}

		};
		add(listContainer);
		listContainer.setOutputMarkupId(true);
		listContainer.add(companyList);
		listContainer.add(navigator);
		listContainer.add(emptyList);
		final String fileName = "洞泾项目情况表.xlsx";
		add(new DownloadLink("export-report-link",
				new AbstractReadOnlyModel<File>() {

					private static final long serialVersionUID = 1L;

					@Override
					public File getObject() {

						ProjectReport report = new ProjectReport(
								mapper.getProjectsForReport());
						try {
							return report.generateReportByFile(true);
						} catch (Throwable e) {
							throw ExceptionUtil.handleRuntimeException(
									"generate report fail: " + fileName, e);
						}
					}
				}, fileName));
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
