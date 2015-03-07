// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.basic.panel;

import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.web.component.dropdownchoice.SystemEnumerationDropdownChoiceRender;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerOption;
import com.mcworkshop.common.web.component.form.datetimepicker.View;
import com.mcworkshop.common.web.component.wizard.WizardContentPanel;
import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.domain.Gender;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

/**
 * @author $Author$
 * 
 */
public class OtherInformationStep extends WizardContentPanel<Company> {

	private static final long serialVersionUID = 1L;
	
	private DatetimePickerOption datePickerOption = new DatetimePickerOption();
	
	public OtherInformationStep(final IModel<Company> model) {
		super(model);
		datePickerOption.setMinView(View.MONTH);
		datePickerOption.setMaxView(View.DECADE);
		datePickerOption.setLanguage("zh-CN");
		form = new Form<Company>("company-other-information-form", model);
		add(form);
		form.add(new TextField<String>("ownerContact.fullName"));
		form.add(new DropDownChoice<Gender>("ownerContact.gender",
				SystemEnumeration.getInstance().getKeysByType(Gender.class),
				new SystemEnumerationDropdownChoiceRender()));
		form.add(new TextField<String>("ownerContact.idCard"));
		form.add(new TextField<String>("ownerContact.address"));
		form.add(new TextField<String>("ownerContact.fax"));
		form.add(new TextField<String>("ownerContact.phone"));
		form.add(new TextField<String>("ownerContact.cellPhone"));
		form.add(new TextField<String>("ownerContact.emailAddress"));

		form.add(new TextField<String>("otherContact.fullName"));
		form.add(new TextField<String>("otherContact.postalCode"));
		form.add(new TextField<String>("otherContact.phone"));

		add(new NatureShareholderFormPanel("ns-form", model));
		add(new CompanyShareholderFormPanel("cs-form", model));
	}

	@Override
	protected String getTabID() {
		return "other";
	}

	@Override
	protected String getTabContentID() {
		return "other-tab";
	}

	@Override
	protected ResourceModel getTabTitle() {
		return new ResourceModel("company.form.other.title");
	}

	@Override
	public void disablePanel() {
		form.setEnabled(false);
	}

}
