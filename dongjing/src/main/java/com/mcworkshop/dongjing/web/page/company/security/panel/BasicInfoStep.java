// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.security.panel;

import java.util.Arrays;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.web.component.dropdownchoice.SystemEnumerationDropdownChoiceRender;
import com.mcworkshop.common.web.component.dropdownchoice.YesOrNoDrowdownChoiceRender;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerOption;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerPlugin;
import com.mcworkshop.common.web.component.form.datetimepicker.View;
import com.mcworkshop.common.web.component.validation.FormFieldValidationContainer;
import com.mcworkshop.common.web.component.wizard.WizardContentPanel;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.DangerousChemistryType;
import com.mcworkshop.dongjing.domain.EconomyEntity;
import com.mcworkshop.dongjing.domain.Security;

/**
 * @author $Author$
 * 
 */
public class BasicInfoStep extends WizardContentPanel<Security> {

	private static final long serialVersionUID = 1L;

	private static final String DATE_PATTERN = "MM-dd-yyyy";
	private DatetimePickerOption datePickerOption = new DatetimePickerOption();

	public BasicInfoStep(IModel<Security> model) {
		super(model);
		datePickerOption.setMinView(View.MONTH);
		datePickerOption.setMaxView(View.DECADE);
		datePickerOption.setLanguage("zh-CN");
		form = new Form<Security>("basic-info");
		form.add(new FormFieldValidationContainer("name-feedback-container",
				new RequiredTextField<String>("name")));
		form.add(new DropDownChoice<EconomyEntity>("economyEntity",
				SystemEnumeration.getInstance().getKeysByType(
						EconomyEntity.class),
				new SystemEnumerationDropdownChoiceRender()));
		form.add(new TextField<String>("major"));
		form.add(new TextField<String>("reportDepartment"));
		form.add(new DateTextField("safeCertificateValidDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DropDownChoice<Boolean>("isContractedCompany", Arrays
				.asList(Boolean.TRUE, Boolean.FALSE),
				new YesOrNoDrowdownChoiceRender()));
		form.add(new TextField<String>("safeLevel"));
		form.add(new DateTextField("assetDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new TextField<String>("safeManageOrg"));
		form.add(new TextField<Integer>("safeMemberFullTime"));
		form.add(new TextField<Integer>("safeMemberParttime"));
		form.add(new TextField<Integer>("fireControlMembers"));
		form.add(new DateTextField("fireControlCertificateDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DropDownChoice<Boolean>("isEnvAssessPass", Arrays.asList(
				Boolean.TRUE, Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new TextField<String>("dirtyWaterMgn"));
		form.add(new DropDownChoice<Boolean>("isPassStandard", Arrays.asList(
				Boolean.TRUE, Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new TextField<String>("safeNo"));
		form.add(new DropDownChoice<Boolean>("isDCProduceOrg", Arrays.asList(
				Boolean.TRUE, Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<DangerousChemistryType>("dcType",
				SystemEnumeration.getInstance().getKeysByType(
						DangerousChemistryType.class),
				new SystemEnumerationDropdownChoiceRender()));
		form.add(new TextField<String>("dcCategroy"));
		form.add(new TextField<Integer>("dcNumber"));
		add(form);
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
		return WicketMessageUtil.getResourceModel("security.form.tab.basic");
	}

	@Override
	public void disablePanel() {
		form.setEnabled(false);
	}

}
