package com.wutai.tradingeq.web.page.contact.us;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import com.google.inject.Inject;
import com.mcworkshop.common.web.component.validation.FormFieldValidationContainer;
import com.wutai.tradingeq.domain.Contact;
import com.wutai.tradingeq.service.ServiceManager;

public class ContactUsContentPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private ServiceManager service;
	
	
	private Contact contact;
	private Form<Contact> form;
	private TextField<String> name;
	private TextField<String> email;
	private TextField<String> phone;
	private WebMarkupContainer submitSuccess;
	private WebMarkupContainer submitFailed;
	

	public ContactUsContentPanel(String id) {
		super(id);
		contact = new Contact();
		add(form = new Form<Contact>("contact-us-form", new CompoundPropertyModel<Contact>(contact)));
		form.setOutputMarkupId(true);
		form.add(new FormFieldValidationContainer("name-feedback-border").add(name = new RequiredTextField<String>("name")));
		name.setLabel(new ResourceModel("fx.contact.us.form.name"));
		form.add(new FormFieldValidationContainer("email-feedback-border").add(email = new RequiredTextField<String>("email")));
		email.setLabel(new ResourceModel("fx.contact.us.form.email"));
		email.add(EmailAddressValidator.getInstance());
		form.add(new FormFieldValidationContainer("phone-feedback-border").add(phone = new RequiredTextField<String>("phone")));
		phone.setLabel(new ResourceModel("fx.contact.us.form.phone"));
		form.add(new TextArea<String>("comments"));
		form.add(submitSuccess = new WebMarkupContainer("submit-success"));
		form.add(submitFailed = new WebMarkupContainer("submit-fail"));
		submitSuccess.setOutputMarkupId(true).setVisible(false);
		submitFailed.setOutputMarkupId(true).setVisible(false);
		form.add(new AjaxSubmitLink("submit-link", form) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				submitSuccess.setVisible(false);
				submitFailed.setVisible(true);
				target.add(form);
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				service.createContact(contact);
				submitSuccess.setVisible(true);
				submitFailed.setVisible(false);
				target.add(form);
			}
			
		});
		
	}
	
}
