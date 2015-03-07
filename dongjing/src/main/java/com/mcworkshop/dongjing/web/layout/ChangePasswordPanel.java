// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.layout;

import com.google.inject.Inject;
import com.mcworkshop.common.exception.AuthenticationException;
import com.mcworkshop.common.web.component.validation.FormFieldValidationContainer;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.User;
import com.mcworkshop.dongjing.service.SecurityService;
import com.mcworkshop.dongjing.web.DJWebSession;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * @author $Author$
 *
 */
public class ChangePasswordPanel extends Panel {

	private static final long serialVersionUID = 1L;

	@Inject
	private SecurityService service;

	private PasswordTextField pwd;
	private PasswordTextField new_pwd;
	private PasswordTextField pwd_again;

	public ChangePasswordPanel(String id) {
		super(id);
		final Form<Void> form = new Form<Void>("change-password-form");
		form.setOutputMarkupId(true);
		add(form);
		form.add(new AbstractFormValidator() {
			private static final long serialVersionUID = 1L;

			@Override
			public void validate(Form<?> form) {

				String passwordString = new_pwd.getValue();
				String confirmPasswordString = pwd_again.getValue();

				if (!passwordString.equals(confirmPasswordString)) {
					form.error("输入密码不匹配，请重新输入。");
				}
			}

			@Override
			public FormComponent<?>[] getDependentFormComponents() {
				return new FormComponent<?>[] { new_pwd, pwd_again };
			}

		});
		form.add(new FeedbackPanel("feedback"));
		form.add(new FormFieldValidationContainer("pwd-feedback-container",
				pwd = new PasswordTextField("pwd", new Model<String>())));
		form.add(new FormFieldValidationContainer("new-pwd-feedback-container",
				new_pwd = new PasswordTextField("new-pwd", new Model<String>())));
		form.add(new FormFieldValidationContainer(
				"pwd-again-feedback-container",
				pwd_again = new PasswordTextField("pwd-again",
						new Model<String>())));
		pwd.setLabel(WicketMessageUtil
				.getResourceModel("change.password.form.pwd"));
		new_pwd.setLabel(WicketMessageUtil
				.getResourceModel("change.password.form.new-pwd"));
		pwd_again.setRequired(false);
		form.add(new AjaxSubmitLink("submit-link", form) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				pwd.setDefaultModel(new Model<String>());
				new_pwd.setDefaultModel(new Model<String>());
				pwd_again.setDefaultModel(new Model<String>());
				target.add(form);
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				User user = service.getUserByID(DJWebSession.get().getUser()
						.getUserID());
				try {

					service.changePassword(pwd.getValue(), new_pwd.getValue(),
							user);

				} catch (AuthenticationException e) {
					getSession().error("原密码错误，请重新输入");
					pwd.setDefaultModel(new Model<String>());
					new_pwd.setDefaultModel(new Model<String>());
					pwd_again.setDefaultModel(new Model<String>());
					target.add(form);
					return;
				}
				getSession().info("密码修改成功");
				pwd.setDefaultModel(new Model<String>());
				new_pwd.setDefaultModel(new Model<String>());
				pwd_again.setDefaultModel(new Model<String>());
				target.add(form);
			}

		});
		form.add(new AjaxLink<Void>("cancel-link") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				pwd.setDefaultModel(new Model<String>());
				new_pwd.setDefaultModel(new Model<String>());
				pwd_again.setDefaultModel(new Model<String>());
				target.add(form);
			}
		});
	}

}
