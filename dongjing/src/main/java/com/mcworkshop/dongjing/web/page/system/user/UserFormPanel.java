// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.system.user;

import com.google.inject.Inject;
import com.mcworkshop.common.domain.Role;
import com.mcworkshop.common.util.BeanUtil;
import com.mcworkshop.common.web.component.validation.FormFieldValidationContainer;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.User;
import com.mcworkshop.dongjing.service.SecurityService;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

/**
 * @author $Author$
 * 
 */
public class UserFormPanel extends Panel {

	private static final long serialVersionUID = 1L;

	@Inject
	SecurityService service;

	Form<User> form;

	User originalUser;

	RequiredTextField<String> username;
	PasswordTextField password;
	PasswordTextField passwordAgain;
	RequiredTextField<String> name;
	TextField<String> title;
	TextField<String> phone;
	TextField<String> cellPhone;
	TextField<String> emailAddress;
	TextField<String> department;
	DropDownChoice<Role> role;

	public UserFormPanel(String id) {
		super(id);
		this.setOutputMarkupId(true);
		this.form = new Form<User>("user-form");
		this.form.setOutputMarkupId(true);
		this.form.add(new FeedbackPanel("feedback-panel"));
		this.form
				.add(new FormFieldValidationContainer(
						"username-feedback-border")
						.add(this.username = new RequiredTextField<String>(
								"username")));
		this.form.add(new FormFieldValidationContainer(
				"password-feedback-border")
				.add(this.password = new PasswordTextField("password")));
		this.form
				.add(new FormFieldValidationContainer(
						"passwordAgain-feedback-border")
						.add(this.passwordAgain = new PasswordTextField(
								"passwordAgain")));
		this.form.add(new FormFieldValidationContainer("name-feedback-border")
				.add(this.name = new RequiredTextField<String>(
						"contact.fullName")));
		this.form.add(new FormFieldValidationContainer("title-feedback-border")
				.add(this.title = new TextField<String>("contact.title")));
		this.form.add(new FormFieldValidationContainer("phone-feedback-border")
				.add(this.phone = new TextField<String>("contact.phone")));
		this.form
				.add(new FormFieldValidationContainer(
						"cellPhone-feedback-border")
						.add(this.cellPhone = new TextField<String>(
								"contact.cellPhone")));
		this.form.add(new FormFieldValidationContainer(
				"emailAddress-feedback-border")
				.add(this.emailAddress = new TextField<String>(
						"contact.emailAddress")));
		this.form.add(new FormFieldValidationContainer(
				"department-feedback-border")
				.add(this.department = new TextField<String>(
						"contact.department")));
		this.form.add(new FormFieldValidationContainer("role-feedback-border")
				.add(this.role = new DropDownChoice<Role>("role", this.service
						.getAllRoles(), new IChoiceRenderer<Role>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Object getDisplayValue(Role object) {
						return object.getName();
					}

					@Override
					public String getIdValue(Role object, int index) {
						return index + object.getName();
					}
				})));

		// Component Attribute Setting
		this.password.setResetPassword(false);
		this.passwordAgain.setResetPassword(false);

		// Validation Setting
		this.username.add(StringValidator.maximumLength(50));
		this.password.add(StringValidator.maximumLength(60));
		this.passwordAgain.add(StringValidator.maximumLength(60));
		this.name.add(StringValidator.maximumLength(100));
		this.title.add(StringValidator.maximumLength(100));
		this.phone.add(StringValidator.maximumLength(20));
		this.cellPhone.add(StringValidator.maximumLength(20));
		this.emailAddress.add(StringValidator.maximumLength(100));
		this.emailAddress.add(EmailAddressValidator.getInstance());
		this.department.add(StringValidator.maximumLength(45));
		this.role.setRequired(true);
		this.form.add(new AbstractFormValidator() {

			private static final long serialVersionUID = 1L;

			@Override
			public void validate(Form<?> form) {
				if (!UserFormPanel.this.password.getValue().equals(
						UserFormPanel.this.passwordAgain.getValue())) {
					form.error(WicketMessageUtil
							.getResourceString("user.form.validation.error.password.not.match"));
				}
			}

			@Override
			public FormComponent<?>[] getDependentFormComponents() {
				return new FormComponent<?>[] { UserFormPanel.this.password,
						UserFormPanel.this.passwordAgain };
			}
		});
		this.add(this.form);
	}

	@Override
	protected void onBeforeRender() {
		User user = (User) this.form.getDefaultModelObject();
		this.passwordAgain.setModel(new Model<String>(user.getPassword()));
		if (user.getUserID() != null && user.getUserID() == 1L) {
			this.form.setEnabled(false);
		} else {
			this.form.setEnabled(true);
		}
		if (user.getUserID() != null) {
			username.setEnabled(false);
			password.setEnabled(false);
			passwordAgain.setEnabled(false);
		} else {
			username.setEnabled(true);
			password.setEnabled(true);
			passwordAgain.setEnabled(true);
		}

		super.onBeforeRender();
	}

	public Form<User> getForm() {
		return this.form;
	}

	public void setFormModel(CompoundPropertyModel<User> model) {
		this.form.setDefaultModel(model);
		try {
			this.originalUser = (User) BeanUtil.copy(model.getObject());
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public User getOriginalUser() {
		return this.originalUser;
	}
}
