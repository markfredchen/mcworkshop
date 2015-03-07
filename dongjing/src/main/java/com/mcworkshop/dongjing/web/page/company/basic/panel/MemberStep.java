// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.basic.panel;

import com.google.inject.Inject;
import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.web.component.dropdownchoice.SystemEnumerationDropdownChoiceRender;
import com.mcworkshop.common.web.component.wizard.WizardContentPanel;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.domain.CompanyMember;
import com.mcworkshop.dongjing.domain.IdType;
import com.mcworkshop.dongjing.persistence.DJServiceMapper;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.ResourceModel;

import java.util.List;

/**
 * @author $Author$
 * 
 */
public class MemberStep extends WizardContentPanel<Company> {

	private static final long serialVersionUID = 1L;
	@Inject
	private DJServiceMapper mapper;

	private Form<CompanyMember> addMemberForm;
	private WebMarkupContainer container;
	private WebMarkupContainer emptyMemberList;
	private ListView<CompanyMember> memberList;
	private boolean isUpdate = false;

	public MemberStep(final IModel<Company> model) {
		super(model);
		form = new Form<Company>("member-form", model);
		add(form);
		form.add(new TextField<Integer>("employeeNumber"));
		form.add(new TextField<Integer>("localEmployeeNumber"));
		CompoundPropertyModel<CompanyMember> memberModel = new CompoundPropertyModel<CompanyMember>(
				new CompanyMember());
		addMemberForm = new Form<CompanyMember>("add-member-form", memberModel);
		add(addMemberForm);
		addMemberForm.setOutputMarkupId(true);
		addMemberForm.add(new TextField<String>("name"));
		addMemberForm.add(new TextField<String>("position"));
		addMemberForm.add(new TextField<String>("produceMethod"));
		addMemberForm.add(new DropDownChoice<IdType>("idType",
				SystemEnumeration.getInstance().getKeysByType(IdType.class),
				new SystemEnumerationDropdownChoiceRender()));
		addMemberForm.add(new TextField<String>("idNo"));
		addMemberForm.add(new TextField<String>("certificate"));
		addMemberForm.add(new AjaxSubmitLink("submit", addMemberForm) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				CompanyMember member = (CompanyMember) form.getModelObject();
				if (!isUpdate) {
					model.getObject().getMembers().add(member);
				}
				isUpdate = false;
				addMemberForm
						.setModel(new CompoundPropertyModel<CompanyMember>(
								new CompanyMember()));
				target.add(addMemberForm);
				target.add(container);
			}

		});

		container = new WebMarkupContainer("member-list-container") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				if (model.getObject().getMembers().isEmpty()) {
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
		memberList = new ListView<CompanyMember>("member-list",
				new LoadableDetachableModel<List<CompanyMember>>() {

					private static final long serialVersionUID = 1L;

					@Override
					protected List<CompanyMember> load() {
						return model.getObject().getMembers();
					}
				}) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<CompanyMember> item) {
				final CompanyMember member = item.getModelObject();
				item.add(new Label("name", member.getName()));
				item.add(new Label("postion", member.getPosition()));
				item.add(new Label("productMethon", member.getProduceMethod()));
				if (member.getIdType() != null) {
					item.add(new Label("idType", WicketMessageUtil
							.getResourceString(member.getIdType()
									.getMessageKey())));
				} else {
					item.add(new Label("idType", ""));
				}
				item.add(new Label("idNo", member.getIdNo()));
				item.add(new Label("certificate", member.getCertificate()));
				item.add(new AjaxLink<Void>("update") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						addMemberForm
								.setModel(new CompoundPropertyModel<CompanyMember>(
										member));
						isUpdate = true;
						target.add(addMemberForm);
					}
				});
				item.add(new AjaxLink<Void>("delete") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						model.getObject().getMembers().remove(member);
						if (member.getMemberID() != null) {
							mapper.deleteCompanyMember(member.getMemberID());
						}
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
		return "member";
	}

	@Override
	protected String getTabContentID() {
		return "member-tab";
	}

	@Override
	protected ResourceModel getTabTitle() {
		return WicketMessageUtil.getResourceModel("company.form.member.title");
	}

	@Override
	public void disablePanel() {
		form.setEnabled(false);
		addMemberForm.setEnabled(false);
	}

}
