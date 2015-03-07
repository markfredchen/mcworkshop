// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.basic.panel;

import com.google.inject.Inject;
import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.web.component.dropdownchoice.SystemEnumerationDropdownChoiceRender;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerOption;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerPlugin;
import com.mcworkshop.common.web.component.form.datetimepicker.View;
import com.mcworkshop.common.web.component.wizard.WizardContentPanel;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.AnnualOutput;
import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.domain.LandUsage;
import com.mcworkshop.dongjing.persistence.DJServiceMapper;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author $Author$
 * 
 */
public class CommercialAreaInfo extends WizardContentPanel<Company> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJServiceMapper mapper;

	private Form<AnnualOutput> addMemberForm;
	private WebMarkupContainer container;
	private WebMarkupContainer emptyMemberList;
	private ListView<AnnualOutput> memberList;
	private boolean isUpdate = false;

	private static final String DATE_PATTERN = "yyyy";
	private DatetimePickerOption datePickerOption = new DatetimePickerOption(
			"yyyy");

	public CommercialAreaInfo(final CompoundPropertyModel<Company> model) {
		super(model);
		form = new Form<Company>("commercial-area-form", model);
		add(form);
		// TODO - new added
		form.add(new TextField<Integer>("area"));
		form.add(new TextField<String>("ownership"));
		form.add(new DropDownChoice<LandUsage>("landUsage", SystemEnumeration
				.getInstance().getKeysByType(LandUsage.class),
				new SystemEnumerationDropdownChoiceRender()));
		form.add(new TextField<Integer>("sleepingRoomNumbers"));
		form.add(new TextField<Double>("usageArea"));
		form.add(new TextField<Double>("rentArea"));
		form.add(new TextField<Double>("spareArea"));
		form.add(new TextField<Double>("companyArea"));
		
		form.add(new LandUsagePanel("land-use", model));

		CompoundPropertyModel<AnnualOutput> memberModel = new CompoundPropertyModel<AnnualOutput>(
				new AnnualOutput());
		addMemberForm = new Form<AnnualOutput>("add-output-form", memberModel);
		add(addMemberForm);
		addMemberForm.setOutputMarkupId(true);

		datePickerOption.setMinView(View.DECADE);
		datePickerOption.setMaxView(View.DECADE);
		datePickerOption.setStartView(View.DECADE);
		datePickerOption.setLanguage("zh-CN");
		final DateTextField yearDatePick;
		addMemberForm.add(yearDatePick = new DateTextField("year",
				new Model<Date>(), DATE_PATTERN));
		yearDatePick.add(new DatetimePickerPlugin(datePickerOption));
		addMemberForm.add(new TextField<Double>("output"));
		addMemberForm.add(new TextField<Double>("profit"));
		addMemberForm.add(new AjaxSubmitLink("submit", addMemberForm) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				AnnualOutput member = (AnnualOutput) form.getModelObject();
				Date year = yearDatePick.getConvertedInput();
				if(year == null){
					target.appendJavaScript("$('#feedback').html('请选择年份')");
					target.appendJavaScript("$('#company-feedback').modal('show')");
				}else{
					Calendar cal = Calendar.getInstance();
					cal.setTime(year);
					member.setYear(cal.get(Calendar.YEAR));
					if (!isUpdate) {
						model.getObject().getOutputs().add(member);
					}
					isUpdate = false;
					addMemberForm.setModel(new CompoundPropertyModel<AnnualOutput>(
							new AnnualOutput()));
					yearDatePick.setDefaultModel(new Model<Date>());
					target.add(addMemberForm);
					target.add(container);
				}
			}

		});

		container = new WebMarkupContainer("member-list-container") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				if (model.getObject().getOutputs().isEmpty()) {
					memberList.setVisible(false);
					emptyMemberList.setVisible(true);
				} else {
					memberList.setVisible(true);
					emptyMemberList.setVisible(false);
				}
				super.onBeforeRender();
			}

		};
		container.setOutputMarkupId(true);
		add(container);
		memberList = new ListView<AnnualOutput>("member-list",
				new LoadableDetachableModel<List<AnnualOutput>>() {

					private static final long serialVersionUID = 1L;

					@Override
					protected List<AnnualOutput> load() {
						return model.getObject().getOutputs();
					}
				}) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<AnnualOutput> item) {
				final AnnualOutput output = item.getModelObject();
				item.add(new Label("year", output.getYear()));
				item.add(new Label("output", output.getOutput()));
				item.add(new Label("profit", output.getProfit()));
				item.add(new AjaxLink<Void>("update") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						addMemberForm
								.setModel(new CompoundPropertyModel<AnnualOutput>(
										output));
						isUpdate = true;
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.YEAR, output.getYear());
						yearDatePick.setDefaultModel(new Model<Date>(cal.getTime()));
						target.add(addMemberForm);
					}
				});
				item.add(new AjaxLink<Void>("delete") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						model.getObject().getOutputs().remove(output);
						if (output.getOutputID() != null) {
							mapper.deleteAnnualOutput(output.getOutputID());
						}
						addMemberForm
								.setModel(new CompoundPropertyModel<AnnualOutput>(new AnnualOutput()));
						target.add(addMemberForm);
						target.add(container);
					}
				});
			}
		};
		container.add(memberList);
		emptyMemberList = new WebMarkupContainer("empty-member-list");
		emptyMemberList.setOutputMarkupId(true);
		container.add(emptyMemberList);
	}

	@Override
	protected String getTabID() {
		return "commercial-area";
	}

	@Override
	protected String getTabContentID() {
		return "commercial-area-tab";
	}

	@Override
	protected ResourceModel getTabTitle() {
		return WicketMessageUtil
				.getResourceModel("company.form.commercial.area.title");
	}

	@Override
	public void disablePanel() {
		form.setEnabled(false);
		addMemberForm.setEnabled(false);
	}
}
