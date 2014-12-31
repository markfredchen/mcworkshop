// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.basic.panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.ResourceModel;

import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.web.component.dropdownchoice.SystemEnumerationDropdownChoiceRender;
import com.mcworkshop.common.web.component.dropdownchoice.YesOrNoDrowdownChoiceRender;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerOption;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerPlugin;
import com.mcworkshop.common.web.component.form.datetimepicker.View;
import com.mcworkshop.common.web.component.validation.FormFieldValidationContainer;
import com.mcworkshop.common.web.component.wizard.WizardContentPanel;
import com.mcworkshop.dongjing.domain.AffiliateBlock;
import com.mcworkshop.dongjing.domain.AffiliateRegion;
import com.mcworkshop.dongjing.domain.CommercialStatus;
import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.domain.Currency;
import com.mcworkshop.dongjing.domain.EconomyEntity;
import com.mcworkshop.dongjing.domain.EconomyNature;
import com.mcworkshop.dongjing.domain.TaxCollectMethod;
import com.mcworkshop.dongjing.domain.TaxNature;
import com.mcworkshop.dongjing.domain.TaxOrg;
import com.mcworkshop.dongjing.domain.TaxStatus;
import com.mcworkshop.dongjing.domain.TaxType;
import com.mcworkshop.dongjing.security.ACLBlockUtil;

/**
 * @author $Author$
 * 
 */
public class BaseInformationStep extends WizardContentPanel<Company> {

	private static final long serialVersionUID = 1L;

	private static final String DATE_PATTERN = "MM-dd-yyyy";
	private DatetimePickerOption datePickerOption = new DatetimePickerOption();

	private DropDownChoice<AffiliateBlock> region;
	private AffiliateBlock block = ACLBlockUtil.getOwnAffiliateBlock();
	private List<AffiliateBlock> blocks = new ArrayList<AffiliateBlock>();

	public BaseInformationStep(CompoundPropertyModel<Company> model) {
		super(model);
		datePickerOption.setMinView(View.MONTH);
		datePickerOption.setMaxView(View.DECADE);
		datePickerOption.setLanguage("zh-CN");

		form = new Form<Company>("company-basic-information-form", model) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				this.getModelObject().setRegion(region.getConvertedInput());
				super.onSubmit();
			}

		};
		add(form);
		form.add(new FormFieldValidationContainer("name-feedback-container",
				new RequiredTextField<String>("name")));
		form.add(new TextField<String>("companyNo"));
		form.add(new TextField<String>("organizationCode"));
		form.add(new TextField<String>("commercialNo"));
		form.add(new TextField<String>("taxRegNo"));
		form.add(new TextField<String>("registerAddress"));
		form.add(new TextField<String>("operateAddress"));
		form.add(new TextField<Integer>("registerAsset"));
		form.add(new DropDownChoice<Currency>("currency", SystemEnumeration
				.getInstance().getKeysByType(Currency.class),
				new SystemEnumerationDropdownChoiceRender()));
		form.add(new DropDownChoice<TaxOrg>("taxOrg", SystemEnumeration
				.getInstance().getKeysByType(TaxOrg.class),
				new SystemEnumerationDropdownChoiceRender()));
		form.add(new TextField<String>("industryType"));
		form.add(region = new DropDownChoice<AffiliateBlock>("region", new LoadableDetachableModel<List<AffiliateBlock>>() {

			@Override
			protected List<AffiliateBlock> load() {
				if (block != null) {
					return Arrays.asList(block);
				} else {
					return SystemEnumeration.getInstance().getKeysByType(
							AffiliateBlock.class);
				}
			}
		},
				new SystemEnumerationDropdownChoiceRender()));
		form.add(new DropDownChoice<CommercialStatus>("commercialStatus",
				SystemEnumeration.getInstance().getKeysByType(
						CommercialStatus.class),
				new SystemEnumerationDropdownChoiceRender()));
		form.add(new DropDownChoice<TaxStatus>("taxStatus", SystemEnumeration
				.getInstance().getKeysByType(TaxStatus.class),
				new SystemEnumerationDropdownChoiceRender()));
		form.add(new DropDownChoice<EconomyEntity>("economyEntity",
				SystemEnumeration.getInstance().getKeysByType(
						EconomyEntity.class),
				new SystemEnumerationDropdownChoiceRender()));
		form.add(new DropDownChoice<EconomyNature>("economyNature",
				SystemEnumeration.getInstance().getKeysByType(
						EconomyNature.class),
				new SystemEnumerationDropdownChoiceRender()));
		form.add(new DropDownChoice<TaxCollectMethod>("collectMethod",
				SystemEnumeration.getInstance().getKeysByType(
						TaxCollectMethod.class),
				new SystemEnumerationDropdownChoiceRender()));
		form.add(new DropDownChoice<AffiliateRegion>("affiliateRegion",
				SystemEnumeration.getInstance().getKeysByType(
						AffiliateRegion.class),
				new SystemEnumerationDropdownChoiceRender()));
		form.add(new DropDownChoice<TaxNature>("taxNature", SystemEnumeration
				.getInstance().getKeysByType(TaxNature.class),
				new SystemEnumerationDropdownChoiceRender()));
		form.add(new DropDownChoice<TaxType>("taxType", SystemEnumeration
				.getInstance().getKeysByType(TaxType.class),
				new SystemEnumerationDropdownChoiceRender()));
		form.add(new DateTextField("establishDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		
		form.add(new DateTextField("operationStartYear", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("operationEndYear", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new TextArea<String>("operationScope"));
		form.add(new TextArea<String>("remarkables"));
	}
	
	

	@Override
	protected void onBeforeRender() {
		region.setEnabled(block == null);
		if(block != null){
			((Company)getDefaultModelObject()).setRegion(block);
		}
		super.onBeforeRender();
	}



	@Override
	protected String getTabID() {
		return "basic";
	}

	@Override
	protected String getTabContentID() {
		return "basic-tab";
	}

	@Override
	protected ResourceModel getTabTitle() {
		return new ResourceModel("company.form.basic.title");
	}

	@Override
	public void disablePanel() {
		form.setEnabled(false);
	}

}
