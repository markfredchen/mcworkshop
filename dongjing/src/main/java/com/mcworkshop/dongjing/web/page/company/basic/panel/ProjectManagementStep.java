// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.basic.panel;

import com.mcworkshop.common.web.component.dropdownchoice.YesOrNoDrowdownChoiceRender;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerOption;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerPlugin;
import com.mcworkshop.common.web.component.form.datetimepicker.View;
import com.mcworkshop.common.web.component.wizard.WizardContentPanel;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.Company;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;

import java.util.Arrays;

/**
 * @author $Author$
 * 
 */
public class ProjectManagementStep extends WizardContentPanel<Company> {

	private static final long serialVersionUID = 1L;

	private static final String DATE_PATTERN = "MM-dd-yyyy";
	private DatetimePickerOption datePickerOption = new DatetimePickerOption();

	public ProjectManagementStep(CompoundPropertyModel<Company> model) {
		super(model);
		datePickerOption.setMinView(View.MONTH);
		datePickerOption.setMaxView(View.DECADE);
		datePickerOption.setLanguage("zh-CN");

		form = new Form<Company>("company-project-information-form", model);
		add(form);
		form.add(new DropDownChoice<Boolean>("isA", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isB", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isC", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isD", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isE", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isF", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isG", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isH", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isI", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isJ", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isK", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isL", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isM", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isN", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isO", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isP", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isQ", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isR", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isS", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isT", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isU", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isV", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DropDownChoice<Boolean>("isW", Arrays.asList(Boolean.TRUE,
				Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
		form.add(new DateTextField("aDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("bDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("cDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("dDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("eDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("fDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("gDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("hDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("iDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("jDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("kDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("lDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("mDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("nDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("rDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("sDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("tDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("uDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("vDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new DateTextField("wDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		form.add(new NumberTextField<Integer>("numberOfO"));
		form.add(new NumberTextField<Integer>("numberOfP"));
		form.add(new NumberTextField<Integer>("numberOfQ"));
	}

	@Override
	protected String getTabID() {
		return "project";
	}

	@Override
	protected String getTabContentID() {
		return "project-tab";
	}

	@Override
	protected ResourceModel getTabTitle() {
		return WicketMessageUtil.getResourceModel("company.form.project.title");
	}

	@Override
	public void disablePanel() {
		form.setEnabled(false);
	}
}
