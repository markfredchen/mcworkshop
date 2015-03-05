// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.basic.panel;

import java.util.List;

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

import com.google.inject.Inject;
import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.web.component.dropdownchoice.SystemEnumerationDropdownChoiceRender;
import com.mcworkshop.common.web.component.wizard.WizardContentPanel;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.domain.Gender;
import com.mcworkshop.dongjing.domain.PartyMember;
import com.mcworkshop.dongjing.persistence.DJServiceMapper;

/**
 * @author $Author$
 * 
 */
public class PartyStep extends WizardContentPanel<Company> {

	private static final long serialVersionUID = 1L;
	@Inject
	private DJServiceMapper mapper;

	private Form<PartyMember> partyForm;
	private WebMarkupContainer container;
	private WebMarkupContainer emptyMemberList;
	private ListView<PartyMember> memberList;
	private boolean isUpdate = false;

	public PartyStep(final IModel<Company> model) {
		super(model);
		form = new Form<Company>("party-form", model);
		form.add(new TextField<String>("partyName"));
		form.add(new TextField<String>("partyLeader"));
		add(form);
		CompoundPropertyModel<PartyMember> memberModel = new CompoundPropertyModel<PartyMember>(
				new PartyMember());
		partyForm = new Form<PartyMember>("add-party-member-form", memberModel);
		add(partyForm);
		partyForm.setOutputMarkupId(true);
		partyForm.add(new TextField<String>("name"));
		partyForm.add(new DropDownChoice<Gender>("gender", SystemEnumeration
				.getInstance().getKeysByType(Gender.class),
				new SystemEnumerationDropdownChoiceRender()));
		partyForm.add(new TextField<String>("education"));
		partyForm.add(new AjaxSubmitLink("submit", partyForm) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				PartyMember member = (PartyMember) form.getDefaultModelObject();
				if (!isUpdate) {
					model.getObject().getPartyMembers().add(member);
				}
				isUpdate = false;
				partyForm.setModel(new CompoundPropertyModel<PartyMember>(
						new PartyMember()));
				target.add(partyForm);
				target.add(container);
			}

		});
		container = new WebMarkupContainer("member-list-container") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				if (model.getObject().getPartyMembers().isEmpty()) {
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
		memberList = new ListView<PartyMember>("member-list",
				new LoadableDetachableModel<List<PartyMember>>() {

					private static final long serialVersionUID = 1L;

					@Override
					protected List<PartyMember> load() {
						return model.getObject().getPartyMembers();
					}
				}) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<PartyMember> item) {
				final PartyMember member = item.getModelObject();
				item.add(new Label("name", member.getName()));
				if (member.getGender() != null) {
					item.add(new Label("gender", WicketMessageUtil
							.getResourceString(member.getGender()
									.getMessageKey())));
				} else {
					item.add(new Label("gender", ""));
				}
				item.add(new Label("education", member.getEducation()));
				item.add(new AjaxLink<Void>("update") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						partyForm
								.setModel(new CompoundPropertyModel<PartyMember>(
										member));
						isUpdate = true;
						target.add(partyForm);
					}
				});
				item.add(new AjaxLink<Void>("delete") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						model.getObject().getPartyMembers().remove(member);
						if (member.getMemberID() != null) {
							mapper.deletePartyMember(member.getMemberID());
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
		return "party";
	}

	@Override
	protected String getTabContentID() {
		return "party-tab";
	}

	@Override
	protected ResourceModel getTabTitle() {
		return WicketMessageUtil.getResourceModel("company.form.party.title");
	}

	@Override
	public void disablePanel() {
		form.setEnabled(false);
		partyForm.setEnabled(false);
	}
}
