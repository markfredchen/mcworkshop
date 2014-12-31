// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.security.panel;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerOption;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerPlugin;
import com.mcworkshop.common.web.component.form.datetimepicker.View;
import com.mcworkshop.common.web.component.wizard.WizardContentPanel;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.Security;

/**
 * @author $Author$
 * 
 */
public class TrainingStep extends WizardContentPanel<Security> {

	private static final long serialVersionUID = 1L;

	private static final String DATE_PATTERN = "MM-dd-yyyy";
	private DatetimePickerOption datePickerOption = new DatetimePickerOption();

	public TrainingStep(IModel<Security> model) {
		super(model);
		datePickerOption.setMinView(View.MONTH);
		datePickerOption.setMaxView(View.DECADE);
		datePickerOption.setLanguage("zh-CN");

		form = new Form<Security>("training-info");
		add(form);
		form.add(new DateTextField("mgnCertValidDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new TextField<String>("safeManagerName"));
		form.add(new DateTextField("traningValidDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new TextField<Integer>("electronist"));
		form.add(new TextField<Integer>("boilist"));
		form.add(new TextField<Integer>("electrowelder"));
		form.add(new TextField<Integer>("climbConstructor"));
		form.add(new TextField<Integer>("forklifter"));
		form.add(new TextField<Integer>("driver"));
		form.add(new TextField<Integer>("elevateOperator"));
		form.add(new TextField<Integer>("trainingOther"));
		form.add(new TextField<Integer>("employeeNumber"));
		form.add(new TextField<Integer>("employeeTrainee"));
		form.add(new TextField<Integer>("farmerNumber"));
		form.add(new TextField<Integer>("farmerTrainee"));
	}

	@Override
	protected String getTabID() {
		return "training";
	}

	@Override
	protected String getTabContentID() {
		return "training-tab";
	}

	@Override
	protected ResourceModel getTabTitle() {
		return WicketMessageUtil.getResourceModel("security.form.tab.training");
	}

	@Override
	public void disablePanel() {
		form.setEnabled(false);
	}
}
