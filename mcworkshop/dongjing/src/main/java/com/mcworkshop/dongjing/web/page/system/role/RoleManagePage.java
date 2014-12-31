// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.system.role;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Inject;
import com.mcworkshop.common.domain.Role;
import com.mcworkshop.common.exception.DuplicateDomainResourceException;
import com.mcworkshop.common.exception.ExceptionUtil;
import com.mcworkshop.common.exception.UndeletableException;
import com.mcworkshop.common.web.util.FeedbackMessageUtil;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.authorization.Roles;
import com.mcworkshop.dongjing.service.SecurityService;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import com.mcworkshop.dongjing.web.page.system.left.navigation.SystemLeftNavigationPanel;

/**
 * @author $Author$
 * 
 */
@AuthorizeInstantiation(value = { Roles.SYSTEM })
public class RoleManagePage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private SecurityService service;

	private RoleFormPanel formPanel;
	private WebMarkupContainer roleListContainer;
	private WebMarkupContainer buttonGroup;
	private AjaxSubmitLink deleteLink;

	public RoleManagePage(PageParameters params) {
		super(params);
		this.roleListContainer = new WebMarkupContainer("role-table");
		this.roleListContainer.setOutputMarkupId(true);
		this.add(roleListContainer);
		this.roleListContainer.add(new ListView<Role>("role-list",
				new LoadableDetachableModel<List<Role>>() {

					private static final long serialVersionUID = 1L;

					@Override
					protected List<Role> load() {
						List<Role> roles = RoleManagePage.this.service
								.getAllRoles();
						int roleSize = roles.size();
						if (roleSize < 15) {
							for (int i = 0; i < 15 - roleSize; i++) {
								roles.add(new Role());
							}
						}
						return roles;
					}
				}) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Role> item) {
				final Role role = item.getModelObject();
				item.add(new AjaxEventBehavior("onclick") {

					private static final long serialVersionUID = 1L;

					@Override
					protected void onEvent(AjaxRequestTarget target) {
						if (role.getRoleID() != null) {
							RoleManagePage.this.formPanel
									.setFormModel(new CompoundPropertyModel<Role>(
											role));
						}
						FeedbackMessageUtil
								.clearFeedbackMessage(RoleManagePage.this.formPanel
										.getForm());
						target.add(RoleManagePage.this.roleListContainer);
						target.add(RoleManagePage.this.buttonGroup);
						target.add(RoleManagePage.this.formPanel);
					}
				});
				item.add(new Label("name", role.getName()));
			}
		});
		this.add(this.formPanel = new RoleFormPanel("role-form",
				new CompoundPropertyModel<Role>(new Role())));
		this.buttonGroup = new WebMarkupContainer("button-group") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				if (RoleManagePage.this.formPanel.getForm().getModelObject()
						.getRoleID() == null) {
					RoleManagePage.this.deleteLink.add(AttributeModifier
							.append("disabled", "disabled"));
				} else if (RoleManagePage.this.formPanel.getForm()
						.getModelObject().getRoleID() == 1L) {
					RoleManagePage.this.deleteLink.add(AttributeModifier
							.append("disabled", "disabled"));
				} else {
					RoleManagePage.this.deleteLink.add(AttributeModifier
							.remove("disabled"));
				}
				super.onBeforeRender();
			}

		};
		this.add(this.buttonGroup);
		this.buttonGroup.setOutputMarkupId(true);
		this.buttonGroup.add(new AjaxLink<Void>("create-link") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				RoleManagePage.this.formPanel
						.setFormModel(new CompoundPropertyModel<Role>(
								new Role()));
				FeedbackMessageUtil
						.clearFeedbackMessage(RoleManagePage.this.formPanel
								.getForm());
				RoleManagePage.this.formPanel.reset();
				target.add(RoleManagePage.this.formPanel);
				target.add(RoleManagePage.this.buttonGroup);
			}
		});
		this.buttonGroup.add(this.deleteLink = new AjaxSubmitLink(
				"delete-link", this.formPanel.getForm()) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Role role = (Role) form.getDefaultModelObject();
				try {
					RoleManagePage.this.service.deleteRole(role);
					form.setDefaultModel(new Model<Role>(new Role()));
				} catch (Throwable e) {
					UndeletableException de = ExceptionUtil.findCause(e,
							UndeletableException.class);
					if (de != null) {
						getSession().error(
								WicketMessageUtil.getResourceString(de
										.getMessage()));
					} else {
						throw e;
					}
				}
				target.add(RoleManagePage.this.roleListContainer);
				target.add(RoleManagePage.this.buttonGroup);
				target.add(RoleManagePage.this.formPanel);
			}

		});
		this.buttonGroup.add(new AjaxLink<Void>("reset-link") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				RoleManagePage.this.formPanel
						.setFormModel(new CompoundPropertyModel<Role>(
								new Role()));
				FeedbackMessageUtil
						.clearFeedbackMessage(RoleManagePage.this.formPanel
								.getForm());
				target.add(RoleManagePage.this.formPanel);
				target.add(RoleManagePage.this.buttonGroup);
			}
		});
		this.buttonGroup.add(new AjaxSubmitLink("update-link", this.formPanel
				.getForm()) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Role role = (Role) form.getDefaultModelObject();
				RoleManagePage.this.formPanel.setValue((Form<Role>) form);
				try {
					if (role.getRoleID() == null) {
						RoleManagePage.this.service.createRole(role);
					} else {
						RoleManagePage.this.service.updateRole(role);

					}
					RoleManagePage.this.formPanel
							.setFormModel(new CompoundPropertyModel<Role>(
									new Role()));
					target.add(RoleManagePage.this.roleListContainer);
				} catch (Throwable e) {
					DuplicateDomainResourceException de = ExceptionUtil
							.findCause(e,
									DuplicateDomainResourceException.class);
					if (de != null) {
						this.getSession().error(
								WicketMessageUtil.getResourceString(e
										.getMessage()));
					} else {
						throw new RuntimeException(e);
					}
				}
				RoleManagePage.this.formPanel.reset();
				target.add(RoleManagePage.this.buttonGroup);
				target.add(RoleManagePage.this.formPanel);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(RoleManagePage.this.formPanel);
			}

		});
	}

	@Override
	protected Panel getLeftNaviPanel(String id) {
		return new SystemLeftNavigationPanel(id);
	}

	@Override
	protected String getPageClassName() {
		return "system";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return WicketMessageUtil.getResourceModel("dj.menu.system.role");
	}

}
