// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.basic.panel;

import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerOption;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerPlugin;
import com.mcworkshop.common.web.component.form.datetimepicker.View;
import com.mcworkshop.common.web.component.wizard.WizardContentPanel;
import com.mcworkshop.dongjing.domain.Company;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;

/**
 * @author $Author$
 * 
 */
public class AdvanceManagementStep extends WizardContentPanel<Company> {

	private static final long serialVersionUID = 1L;

	private static final String DATE_PATTERN = "MM-dd-yyyy";
	private DatetimePickerOption datePickerOption = new DatetimePickerOption();

	public AdvanceManagementStep(CompoundPropertyModel<Company> model) {
		super(model);
		datePickerOption.setMinView(View.MONTH);
		datePickerOption.setMaxView(View.DECADE);
		datePickerOption.setLanguage("zh-CN");
		form = new Form<Company>("am-form");
		add(form);
		form.add(new DateTextField("passDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new TextField<String>("noOfDeveloper"));
		form.add(new TextField<String>("devBudget"));
	}

	@Override
	protected String getTabID() {
		return "advanced";
	}

	@Override
	protected String getTabContentID() {
		return "advanced-tab";
	}

	@Override
	protected ResourceModel getTabTitle() {
		return new ResourceModel("company.form.advanced.title");
	}

	@Override
	public void disablePanel() {
		form.setEnabled(false);
	}

}
