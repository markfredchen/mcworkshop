// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.system.user;

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
import com.mcworkshop.common.exception.DuplicateDomainResourceException;
import com.mcworkshop.common.exception.ExceptionUtil;
import com.mcworkshop.common.web.util.FeedbackMessageUtil;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.authorization.Roles;
import com.mcworkshop.dongjing.domain.User;
import com.mcworkshop.dongjing.service.SecurityService;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import com.mcworkshop.dongjing.web.page.system.left.navigation.SystemLeftNavigationPanel;

/**
 * @author $Author$
 * 
 */
@AuthorizeInstantiation(value = { Roles.SYSTEM })
public class UserManagePage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private SecurityService service;

	private UserFormPanel formPanel;
	private WebMarkupContainer userListContainer;
	private WebMarkupContainer buttonGroup;
	private AjaxSubmitLink deleteLink;

	public UserManagePage(PageParameters params) {
		super(params);
		this.userListContainer = new WebMarkupContainer("user-table");
		this.userListContainer.setOutputMarkupId(true);
		this.add(this.userListContainer);
		this.userListContainer.add(new ListView<User>("user-list",
				new LoadableDetachableModel<List<User>>() {
					private static final long serialVersionUID = 1L;

					@Override
					protected List<User> load() {
						List<User> users = UserManagePage.this.service
								.getAllUsers();
						int userSize = users.size();
						if (userSize < 15) {
							for (int i = 0; i < 15 - userSize; i++) {
								users.add(new User());
							}
						}
						return users;
					}
				}) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<User> item) {
				final User user = item.getModelObject();
				item.add(new AjaxEventBehavior("onclick") {

					private static final long serialVersionUID = 1L;

					@Override
					protected void onEvent(AjaxRequestTarget target) {
						if (user.getUserID() != null) {
							UserManagePage.this.formPanel
									.setFormModel(new CompoundPropertyModel<User>(
											UserManagePage.this.service
													.getUserByID(user
															.getUserID())));
						}
						FeedbackMessageUtil
								.clearFeedbackMessage(UserManagePage.this.formPanel
										.getForm());
						target.add(UserManagePage.this.userListContainer);
						target.add(UserManagePage.this.buttonGroup);
						target.add(UserManagePage.this.formPanel);
					}
				});
				item.add(new Label("username", user.getUsername()));
				item.add(new Label("name", user.getContact().getFullName()));

			}
		});
		this.add(this.formPanel = new UserFormPanel("user-form"));
		this.formPanel
				.setFormModel(new CompoundPropertyModel<User>(new User()));

		this.buttonGroup = new WebMarkupContainer("button-group") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				if (UserManagePage.this.formPanel.getForm().getModelObject()
						.getUserID() == null) {
					UserManagePage.this.deleteLink.add(AttributeModifier
							.append("disabled", "disabled"));
				} else if (UserManagePage.this.formPanel.getForm()
						.getModelObject().getUserID() == 1L) {
					UserManagePage.this.deleteLink.add(AttributeModifier
							.append("disabled", "disabled"));
				} else {
					UserManagePage.this.deleteLink.add(AttributeModifier
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
				UserManagePage.this.formPanel
						.setFormModel(new CompoundPropertyModel<User>(
								new User()));
				FeedbackMessageUtil
						.clearFeedbackMessage(UserManagePage.this.formPanel
								.getForm());
				target.add(UserManagePage.this.formPanel);
				target.add(UserManagePage.this.buttonGroup);
			}
		});
		this.buttonGroup.add(this.deleteLink = new AjaxSubmitLink(
				"delete-link", this.formPanel.getForm()) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				User user = (User) form.getDefaultModelObject();
				UserManagePage.this.service.deleteUser(user);
				form.setDefaultModel(new Model<User>(new User()));
				target.add(UserManagePage.this.userListContainer);
				target.add(UserManagePage.this.buttonGroup);
				target.add(UserManagePage.this.formPanel);
			}

		});
		this.buttonGroup.add(new AjaxLink<Void>("reset-link") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				UserManagePage.this.formPanel
						.setFormModel(new CompoundPropertyModel<User>(
								new User()));
				FeedbackMessageUtil
						.clearFeedbackMessage(UserManagePage.this.formPanel
								.getForm());
				target.add(UserManagePage.this.formPanel);
				target.add(UserManagePage.this.buttonGroup);
			}
		});
		this.buttonGroup.add(new AjaxSubmitLink("update-link", this.formPanel
				.getForm()) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				User user = (User) form.getDefaultModelObject();

				try {
					if (user.getUserID() == null) {
						UserManagePage.this.service.createUser(user);
						this.getSession().success("用户创建成功");
						UserManagePage.this.formPanel
								.setFormModel(new CompoundPropertyModel<User>(
										new User()));
					} else {
						// TODO: update user
						if (!UserManagePage.this.formPanel.getOriginalUser()
								.equals(user)) {
							UserManagePage.this.service.updateUser(
									UserManagePage.this.formPanel
											.getOriginalUser(), user);
						}
						UserManagePage.this.formPanel
								.setFormModel(new CompoundPropertyModel<User>(
										new User()));
					}
					target.add(UserManagePage.this.userListContainer);
				} catch (Throwable e) {
					DuplicateDomainResourceException de = ExceptionUtil
							.findCause(e,
									DuplicateDomainResourceException.class);
					if (de != null) {
						this.getSession().error(
								WicketMessageUtil.getResourceString(de
										.getMessage()));
					} else {
						throw new RuntimeException(e);
					}
				}
				target.add(UserManagePage.this.buttonGroup);
				target.add(UserManagePage.this.formPanel);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(UserManagePage.this.formPanel);
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
		return WicketMessageUtil.getResourceModel("dj.menu.system.user");
	}

}
