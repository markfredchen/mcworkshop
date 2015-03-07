// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.home;

import com.google.inject.Inject;
import com.mcworkshop.common.exception.AuthenticationException;
import com.mcworkshop.common.web.component.validation.FormFieldValidationContainer;
import com.mcworkshop.dongjing.authorization.Roles;
import com.mcworkshop.dongjing.domain.User;
import com.mcworkshop.dongjing.service.SecurityService;
import com.mcworkshop.dongjing.web.DJWebSession;
import com.mcworkshop.dongjing.web.layout.DJBasePage;
import com.mcworkshop.dongjing.web.layout.HeaderPanel;
import com.mcworkshop.dongjing.web.page.landing.WelcomePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;

/**
 * @author $Author$
 * 
 */
public class HomePage extends DJBasePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private SecurityService service;

	private User user;
	private Form<User> form;
	private TextField<String> usernameTextField;
	private PasswordTextField passwordTextField;

	private WebMarkupContainer loginPanel;
	private WebMarkupContainer landingPanel;

	public HomePage() {
		DJWebSession.get().setDepartmentRights(null);
		loginPanel = new WebMarkupContainer("login-panel");
		landingPanel = new WebMarkupContainer("landing-panel");
		loginPanel.setOutputMarkupId(true);
		landingPanel.setOutputMarkupId(true);
		add(loginPanel);
		add(landingPanel);

		if (DJWebSession.get().getUser() != null) {
			loginPanel.setVisible(false);
			landingPanel.setVisible(true);
		} else {
			loginPanel.setVisible(true);
			landingPanel.setVisible(false);
		}

		this.user = new User();
		this.form = new Form<User>("login-form",
				new CompoundPropertyModel<User>(this.user));

		loginPanel.add(this.form);
		this.form.add(new FeedbackPanel("feedback-panel"));
		this.form.add(new FormFieldValidationContainer(
				"username-feedback-border")
				.add(this.usernameTextField = new RequiredTextField<String>(
						"username")));
		this.usernameTextField.setLabel(new ResourceModel(
				"dj.login.form.username"));
		this.form
				.add(new FormFieldValidationContainer(
						"password-feedback-border")
						.add(this.passwordTextField = new PasswordTextField(
								"password")));
		this.passwordTextField.setLabel(new ResourceModel(
				"dj.login.form.password"));
		this.form.add(new AjaxSubmitLink("submit-link", this.form) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				try {
					User user = service.authenticate(
							HomePage.this.user.getUsername(),
							HomePage.this.user.getPassword());
					DJWebSession.get().setUser(user);
					this.setResponsePage(HomePage.class);
				} catch (AuthenticationException e) {
					getSession().error(
							new ResourceModel(e.getMessage()).getObject());
					target.add(form);
					target.appendJavaScript("$('#login-feedback').modal('show')");
				}
			}

		});
		AjaxLink<Void> jfb = new AjaxLink<Void>("jifaban") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				DJWebSession.get().setDepartmentRights(Roles.JFB_RIGHTS);
				setResponsePage(WelcomePage.class);
			}
		};
		AjaxLink<Void> czs = new AjaxLink<Void>("caiwusuo") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				DJWebSession.get().setDepartmentRights(Roles.CZS_RIGHTS);
				setResponsePage(WelcomePage.class);
			}
		};
		AjaxLink<Void> ajs = new AjaxLink<Void>("anjiansuo") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				DJWebSession.get().setDepartmentRights(Roles.AJS_RIGHTS);
				setResponsePage(WelcomePage.class);
			}
		};

		AjaxLink<Void> bkx = new AjaxLink<Void>("baikexing") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				DJWebSession.get().setDepartmentRights(Roles.BKX_RIGHTS);
				setResponsePage(WelcomePage.class);
			}
		};

		AjaxLink<Void> jgs = new AjaxLink<Void>("jingguansuo") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				DJWebSession.get().setDepartmentRights(Roles.JGS_RIGHTS);
				setResponsePage(WelcomePage.class);
			}
		};
		AjaxLink<Void> myc = new AjaxLink<Void>("maoyicheng") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				DJWebSession.get().setDepartmentRights(Roles.MYC_RIGHTS);
				setResponsePage(WelcomePage.class);
			}
		};
		AjaxLink<Void> nfgs = new AjaxLink<Void>("nongfugongsi") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				DJWebSession.get().setDepartmentRights(Roles.NFGS_RIGHTS);
				setResponsePage(WelcomePage.class);
			}
		};
		AjaxLink<Void> zhdw = new AjaxLink<Void>("zonghedangwei") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				DJWebSession.get().setDepartmentRights(Roles.ZHDW_RIGHTS);
				setResponsePage(WelcomePage.class);
			}
		};
		landingPanel.add(jfb);
		landingPanel.add(czs);
		landingPanel.add(ajs);
		landingPanel.add(bkx);
		landingPanel.add(jgs);
		landingPanel.add(myc);
		landingPanel.add(nfgs);
		landingPanel.add(zhdw);
		MetaDataRoleAuthorizationStrategy.authorize(jfb, ENABLE, Roles.JFB);
		MetaDataRoleAuthorizationStrategy.authorize(czs, ENABLE, Roles.CZS);
		MetaDataRoleAuthorizationStrategy.authorize(ajs, ENABLE, Roles.AJS);
		MetaDataRoleAuthorizationStrategy.authorize(bkx, ENABLE, Roles.BKX);
		MetaDataRoleAuthorizationStrategy.authorize(jgs, ENABLE, Roles.JGS);
		MetaDataRoleAuthorizationStrategy.authorize(myc, ENABLE, Roles.MYC);
		MetaDataRoleAuthorizationStrategy.authorize(nfgs, ENABLE, Roles.NFGS);
		MetaDataRoleAuthorizationStrategy.authorize(zhdw, ENABLE, Roles.ZHDW);
	}

	@Override
	protected String getPageClassName() {
		return "home";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return new ResourceModel("home.page.title");
	}

	@Override
	protected Panel getHeaderPanel(String id) {
		return new HeaderPanel(id);
	}

}
