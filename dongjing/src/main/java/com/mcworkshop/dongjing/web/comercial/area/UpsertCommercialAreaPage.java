// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: UpsertCommercialAreaPage.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.web.comercial.area;

import com.google.inject.Inject;
import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.web.component.dropdownchoice.SystemEnumerationDropdownChoiceRender;
import com.mcworkshop.common.web.component.validation.FormFieldValidationContainer;
import com.mcworkshop.common.web.util.WebUtil;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.CommercialArea;
import com.mcworkshop.dongjing.domain.CommercialAreaType;
import com.mcworkshop.dongjing.persistence.CommercialAreaMapper;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class UpsertCommercialAreaPage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private CommercialAreaMapper mapper;

	public UpsertCommercialAreaPage(PageParameters params) {
		super(params);
		final Long areaID = WebUtil.getParameterAsLong("areaID");
		CommercialArea area = new CommercialArea();
		if (areaID != null) {
			area = mapper.getCommercialArea(areaID);
		}
		CompoundPropertyModel<CommercialArea> model = new CompoundPropertyModel<CommercialArea>(
				area);
		final Form<CommercialArea> form = new Form<CommercialArea>(
				"upsert-area-form", model);
		add(form);
		form.add(new FormFieldValidationContainer("name-feedback-container",
				new RequiredTextField<String>("name")));
		form.add(new DropDownChoice<CommercialAreaType>("type",
				SystemEnumeration.getInstance().getKeysByType(
						CommercialAreaType.class),
				new SystemEnumerationDropdownChoiceRender()));
		form.add(new TextField<String>("scope"));
		form.add(new TextField<String>("residentialArea"));
		form.add(new TextField<String>("residentNumbers"));
		form.add(new TextField<String>("storePopulation"));
		form.add(new TextField<String>("ownerEduProvinceAge"));
		form.add(new TextField<String>("start"));
		form.add(new TextField<String>("end"));
		form.add(new TextField<String>("length"));
		form.add(new TextField<String>("totalArea"));
		form.add(new TextField<String>("ownArea"));
		form.add(new TextField<String>("rentArea"));
		form.add(new TextField<String>("spareArea"));
		form.add(new TextField<String>("constructDescription"));
		form.add(new TextField<Integer>("retailNumber"));
		form.add(new TextField<String>("retailMainBrands"));
		form.add(new TextField<Integer>("caterNumber"));
		form.add(new TextField<String>("caterMainBrands"));
		form.add(new TextField<Integer>("entertainNumber"));
		form.add(new TextField<String>("entertainMainBrands"));
		form.add(new TextField<Integer>("serviceNumber"));
		form.add(new TextField<String>("serviceMainBrands"));
		form.add(new TextField<Integer>("farmNumber"));
		form.add(new TextField<String>("farmMainBrands"));
		form.add(new TextField<Integer>("otherNumber"));
		form.add(new TextField<String>("otherMainBrands"));
		form.add(new TextField<String>("annualIncoming"));
		form.add(new TextField<String>("annualTax"));
		form.add(new TextField<String>("comment"));
		form.add(new TextField<String>("totalCommercialEquipment"));
		form.add(new SubmitLink("submit-link", form) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				CommercialArea area = (CommercialArea) form.getModelObject();
				if (area.getCommercialAreaID() == null) {
					mapper.createCommercialArea(area);
				} else {
					mapper.updateCommercialArea(area);
				}
				setResponsePage(CommercialAreaListPage.class);
			}

		});
		form.add(new BookmarkablePageLink<Void>("cancel-link",
				CommercialAreaListPage.class));
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
