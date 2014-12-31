// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.agriculture;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Inject;
import com.mcworkshop.common.web.component.pagination.PaginationNavigator;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.AgricultureInfo;
import com.mcworkshop.dongjing.service.DJService;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;

/**
 * @author $Author$
 * 
 */
public class AgricultureListPage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJService service;

	private AgricultureDataProvider provider = new AgricultureDataProvider(
			service);

	private final WebMarkupContainer listContainer;
	private final TextField<String> companyNameTextField;

	public AgricultureListPage(PageParameters params) {
		super(params);
		Form<Void> searchForm = new Form<Void>("search-company-form");
		add(searchForm);
		searchForm.add(companyNameTextField = new TextField<String>(
				"companyName", new Model<String>()));
		searchForm.add(new AjaxSubmitLink("search-company") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				provider.setName(companyNameTextField.getValue());
				target.add(listContainer);
			}
		});
		final DataView<AgricultureInfo> companyList = new DataView<AgricultureInfo>(
				"company-list", provider) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<AgricultureInfo> item) {
				AgricultureInfo agri = item.getModelObject();
				item.add(new Label("name", agri.getFarmerName()));
				item.add(new Label("contactNumber", agri.getContactNumber()));
				item.add(new Label("area", agri.getArea()));

				item.add(new AjaxEventBehavior("onclick") {
					private static final long serialVersionUID = 1L;

					@Override
					protected void onEvent(AjaxRequestTarget target) {
						Long agriID = item.getModelObject().getAgriID();
						PageParameters params = new PageParameters();
						params.add("agriID", agriID);
						setResponsePage(UpsertAgriculturePage.class, params);
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
		return new AgricultureLeftNaviPanel(id);
	}

	@Override
	protected String getPageClassName() {
		return "argiculture";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return WicketMessageUtil.getResourceModel("agriculture.page.title");
	}

}
