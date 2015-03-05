// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: CommercialAreaListPage.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.web.comercial.area;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
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
import com.mcworkshop.dongjing.domain.CommercialArea;
import com.mcworkshop.dongjing.domain.CommercialAreaType;
import com.mcworkshop.dongjing.service.DJService;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class CommercialAreaListPage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJService service;

	private CommercialAreaDataProvider provider = new CommercialAreaDataProvider(
			service);
	private final WebMarkupContainer listContainer;
	private final TextField<String> nameTextField;
	private final DropDownChoice<CommercialAreaType> typeChoice;

	public CommercialAreaListPage(PageParameters params) {
		super(params);
		Form<Void> searchForm = new Form<Void>("search-company-form");
		add(searchForm);
		searchForm.add(nameTextField = new TextField<String>("name",
				new Model<String>()));
		searchForm.add(typeChoice = new DropDownChoice<CommercialAreaType>(
				"type", new Model<CommercialAreaType>(), SystemEnumeration
						.getInstance().getKeysByType(CommercialAreaType.class),
				new SystemEnumerationDropdownChoiceRender()));
		searchForm.add(new AjaxSubmitLink("search-company") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				provider.setName(nameTextField.getValue());
				provider.setType(typeChoice.getConvertedInput());
				target.add(listContainer);
			}
		});

		final DataView<CommercialArea> companyList = new DataView<CommercialArea>(
				"company-list", provider) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<CommercialArea> item) {
				CommercialArea area = item.getModelObject();
				item.add(new Label("name", area.getName()));
				if (area.getType() != null) {
					item.add(new Label("type", WicketMessageUtil
							.getResourceString(area.getType().getMessageKey())));
				} else {
					item.add(new Label("type", ""));
				}

				item.add(new AjaxEventBehavior("onclick") {
					private static final long serialVersionUID = 1L;

					@Override
					protected void onEvent(AjaxRequestTarget target) {
						Long areaID = item.getModelObject()
								.getCommercialAreaID();
						PageParameters params = new PageParameters();
						params.add("areaID", areaID);
						setResponsePage(UpsertCommercialAreaPage.class, params);
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
		return new CommercialAreaLeftNaviPanel(id);
	}

	@Override
	protected String getPageClassName() {
		return "area";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return WicketMessageUtil.getResourceModel("commercial.area.page.title");
	}

}
