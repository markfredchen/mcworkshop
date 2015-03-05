// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.security;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Inject;
import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.web.component.dropdownchoice.SystemEnumerationDropdownChoiceRender;
import com.mcworkshop.common.web.component.pagination.PaginationNavigator;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.authorization.Roles;
import com.mcworkshop.dongjing.domain.EconomyEntity;
import com.mcworkshop.dongjing.domain.Security;
import com.mcworkshop.dongjing.service.DJService;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;

/**
 * @author $Author$
 * 
 */
public class SecurityListPage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJService service;

	private SecurityDataProvider provider = new SecurityDataProvider(service);

	private final WebMarkupContainer listContainer;
	private final TextField<String> companyNameTextField;

	public SecurityListPage(PageParameters params) {
		super(params);
		Form<Void> searchForm = new Form<Void>("search-company-form");
		add(searchForm);
		searchForm.add(companyNameTextField = new TextField<String>(
				"companyName", new Model<String>()));
		searchForm.add(new AjaxSubmitLink("search-company") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				provider.setCompanyName(companyNameTextField.getValue());
				target.add(listContainer);
			}
		});

		final DataView<Security> companyList = new DataView<Security>(
				"company-list", provider) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<Security> item) {
				Security company = item.getModelObject();
				item.add(new Label("name", company.getName()));
				AjaxLink<Void> updateLink;
				item.add(updateLink = new AjaxLink<Void>("update") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						Long companyID = item.getModelObject()
								.getSecurityInfoID();
						PageParameters params = new PageParameters();
						params.add("securityID", companyID);
						setResponsePage(UpsertSecurityPage.class, params);
					}
				});
				MetaDataRoleAuthorizationStrategy.authorize(updateLink, RENDER,
						Roles.SECURITY_CHANGE);
				item.add(new AjaxLink<Void>("view") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						Long companyID = item.getModelObject()
								.getSecurityInfoID();
						PageParameters params = new PageParameters();
						params.add("securityID", companyID);
						setResponsePage(ViewSecurityPage.class, params);

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
	}

	@Override
	protected Panel getLeftNaviPanel(String id) {
		return new SecurityLeftNaviPanel(id);
	}

	@Override
	protected String getPageClassName() {
		return "security-list";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return WicketMessageUtil.getResourceModel("dj.menu.company.security");
	}

}
