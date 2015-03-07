// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: SearchCompanyPanel.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.web.page.company.basic;

import com.google.inject.Inject;
import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.web.component.dropdownchoice.SystemEnumerationDropdownChoiceRender;
import com.mcworkshop.common.web.component.pagination.PaginationNavigator;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.authorization.Roles;
import com.mcworkshop.dongjing.domain.AffiliateBlock;
import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.domain.EconomyEntity;
import com.mcworkshop.dongjing.domain.EconomyNature;
import com.mcworkshop.dongjing.security.ACLBlockUtil;
import com.mcworkshop.dongjing.service.DJService;
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
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class SearchCompanyPanel extends Panel {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJService service;

	private CompanyDataProvider provider = new CompanyDataProvider(service);
	private final WebMarkupContainer listContainer;
	private final TextField<String> companyNameTextField;
	private final DropDownChoice<EconomyEntity> economyEntityChoice;
	private final DropDownChoice<EconomyNature> economyNatureChoice;
	private final DropDownChoice<AffiliateBlock> blockChoice;

	public SearchCompanyPanel(String id) {
		super(id);
		Form<Void> searchForm = new Form<Void>("search-company-form");
		add(searchForm);
		searchForm.add(companyNameTextField = new TextField<String>(
				"companyName", new Model<String>()));
		searchForm.add(economyEntityChoice = new DropDownChoice<EconomyEntity>(
				"economyEntity", new Model<EconomyEntity>(), SystemEnumeration
						.getInstance().getKeysByType(EconomyEntity.class),
				new SystemEnumerationDropdownChoiceRender()));
		searchForm.add(economyNatureChoice = new DropDownChoice<EconomyNature>(
				"economyNature", new Model<EconomyNature>(), SystemEnumeration
						.getInstance().getKeysByType(EconomyNature.class),
				new SystemEnumerationDropdownChoiceRender()));
		WebMarkupContainer blockContainer = new WebMarkupContainer(
				"blockContainer");
		blockChoice = new DropDownChoice<AffiliateBlock>("block",
				new Model<AffiliateBlock>(), SystemEnumeration.getInstance()
						.getKeysByType(AffiliateBlock.class),
				new SystemEnumerationDropdownChoiceRender());
		blockContainer.add(blockChoice);
		searchForm.add(blockContainer);
		if (ACLBlockUtil.getOwnAffiliateBlock() != null) {
			blockContainer.setVisible(false);
		}
		searchForm.add(new AjaxSubmitLink("search-company") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				provider.setCompanyName(companyNameTextField.getValue());
				provider.setEconomyEntity(economyEntityChoice
						.getConvertedInput());
				provider.setEconomyNature(economyNatureChoice
						.getConvertedInput());
				provider.setBlock(blockChoice.getConvertedInput());
				target.add(listContainer);
			}
		});

		final DataView<Company> companyList = new DataView<Company>(
				"company-list", provider) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<Company> item) {
				Company company = item.getModelObject();
				item.add(new Label("name", company.getName()));
				if (company.getEconomyEntity() != null) {
					item.add(new Label("economyEntity", WicketMessageUtil
							.getResourceString(company.getEconomyEntity()
									.getMessageKey())));
				} else {
					item.add(new Label("economyEntity", ""));
				}
				if (company.getEconomyNature() != null) {
					item.add(new Label("economyNature", WicketMessageUtil
							.getResourceString(company.getEconomyNature()
									.getMessageKey())));
				} else {
					item.add(new Label("economyNature", ""));
				}
				if (company.getRegion() != null) {
					item.add(new Label("block", WicketMessageUtil
							.getResourceString(company.getRegion()
									.getMessageKey())));
				} else {
					item.add(new Label("block", ""));
				}
				AjaxLink<Void> updaetLink;
				item.add(updaetLink = new AjaxLink<Void>("update") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						Long companyID = item.getModelObject().getCompanyID();
						PageParameters params = new PageParameters();
						params.add("companyID", companyID);
						setResponsePage(UpsertComanyPage.class, params);
					}
				});
				MetaDataRoleAuthorizationStrategy.authorize(updaetLink, RENDER,
						Roles.CM_CHANGE);
				item.add(new AjaxLink<Void>("view") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						Long companyID = item.getModelObject().getCompanyID();
						PageParameters params = new PageParameters();
						params.add("companyID", companyID);
						setResponsePage(ViewCompanyPage.class, params);

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

}
