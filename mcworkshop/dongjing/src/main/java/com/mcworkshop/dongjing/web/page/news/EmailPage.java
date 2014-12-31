// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: EmailPage.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.web.page.news;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.EmailException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Inject;
import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.web.component.dropdownchoice.SystemEnumerationDropdownChoiceRender;
import com.mcworkshop.common.web.component.form.ckeditor.CKEditorPlugin;
import com.mcworkshop.common.web.component.pagination.PaginationNavigator;
import com.mcworkshop.common.web.component.validation.FormFieldValidationContainer;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.domain.EconomyEntity;
import com.mcworkshop.dongjing.domain.EconomyNature;
import com.mcworkshop.dongjing.service.DJService;
import com.mcworkshop.dongjing.service.email.Mail;
import com.mcworkshop.dongjing.service.email.MailManager;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import com.mcworkshop.dongjing.web.page.company.basic.CompanyDataProvider;

/**
 * @author $Author: mchen $
 * 
 */
public class EmailPage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(EmailPage.class);

	private Form<Void> smsForm;
	private RequiredTextField<String> subject;
	private TextArea<String> message;

	@Inject
	private MailManager mm;

	@Inject
	private DJService service;

	private CompanyDataProvider provider = new CompanyDataProvider(service);
	private final WebMarkupContainer listContainer;
	private final TextField<String> companyNameTextField;
	private final DropDownChoice<EconomyEntity> economyEntityChoice;
	private final DropDownChoice<EconomyNature> economyNatureChoice;

	private WebMarkupContainer searchCompanyPanel;
	private Form<Void> searchForm;
	private final WebMarkupContainer container;

	private final Set<Company> companies = new HashSet<Company>();

	private static final String TO_ALL = "TO_ALL";
	private static final String TO_SPECIFIC = "TO_SPECIFIC";

	private String target = TO_ALL;

	public EmailPage(PageParameters params) {
		super(params);

		add(smsForm = new Form<Void>("send-sms-form"));
		smsForm.add(new FormFieldValidationContainer(
				"subject-feedback-container",
				subject = new RequiredTextField<String>("subject",
						new Model<String>())));
		smsForm.add(new FormFieldValidationContainer(
				"message-feedback-container", message = new TextArea<String>(
						"message", new Model<String>())));
		message.add(new CKEditorPlugin());
		message.setRequired(true);
		smsForm.add(new AjaxSubmitLink("submit-link", smsForm) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget t, Form<?> form) {
				String emailSubject = subject.getConvertedInput();
				String emailContect = message.getConvertedInput();
				Mail mail = new Mail(emailSubject, emailContect);
				if (companies.size() == 0 && target.equals(TO_SPECIFIC)) {
					t.appendJavaScript("$('#empty-email-list').modal('show')");
				} else if (target.equals(TO_ALL)) {
					Map<String, String> emailList = service
							.getCompanyEmail(null);
					for (String email : emailList.keySet()) {
						mail.addTo(email, emailList.get(email));
					}
					log.error(emailList.size());
					log.error(emailList);
					try {
						mm.send(mail);
					} catch (EmailException e) {

					}
					t.appendJavaScript("$('#sent-successful').modal('show')");
				} else {
					List<Long> companyIDs = new ArrayList<Long>();
					for (Company c : companies) {
						companyIDs.add(c.getCompanyID());
					}
					Map<String, String> emailList = service
							.getCompanyEmail(companyIDs);
					for (String email : emailList.keySet()) {
						mail.addTo(email, emailList.get(email));
					}
					try {
						mm.send(mail);
					} catch (EmailException e) {

					}
					t.appendJavaScript("$('#sent-successful').modal('show')");
				}
			}

		});

		RadioGroup<String> targetCompanies = new RadioGroup<String>("to_mode",
				new Model<String>(target));
		targetCompanies.add(new Radio<String>("to_all", new Model<String>(
				TO_ALL)));
		targetCompanies.add(new Radio<String>("to_specific", new Model<String>(
				TO_SPECIFIC)));
		smsForm.add(targetCompanies);
		targetCompanies.add(new AjaxFormChoiceComponentUpdatingBehavior() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget t) {
				target = getComponent().getDefaultModelObjectAsString();
				searchForm.setVisible(target.equals(TO_SPECIFIC));
				listContainer.setVisible(target.equals(TO_SPECIFIC));
				t.add(searchCompanyPanel);

			}
		});

		smsForm.add(new AjaxLink<Void>("added-company-dialog") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				target.add(container);
				target.appendJavaScript("$('#added-companies').modal('show')");
			}
		});
		searchCompanyPanel = new WebMarkupContainer("target-group-list");
		searchCompanyPanel.setOutputMarkupId(true);
		add(searchCompanyPanel);
		searchForm = new Form<Void>("search-company-form");
		searchCompanyPanel.add(searchForm);
		searchForm.add(companyNameTextField = new TextField<String>(
				"companyName", new Model<String>()));
		searchForm.add(economyEntityChoice = new DropDownChoice<EconomyEntity>(
				"economyEntity", new Model<EconomyEntity>(), SystemEnumeration
						.getInstance().getKeysByType(EconomyEntity.class),
				new SystemEnumerationDropdownChoiceRender()));
		searchForm.add(economyNatureChoice = new DropDownChoice<EconomyNature>(
				"economyNature", new Model<EconomyNature>(), SystemEnumeration
						.getInstance().getKeysByType(EconomyNature.class),
				new SystemEnumerationDropdownChoiceRender()));
		searchForm.add(new AjaxSubmitLink("search-company") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				provider.setCompanyName(companyNameTextField.getValue());
				provider.setEconomyEntity(economyEntityChoice
						.getConvertedInput());
				provider.setEconomyNature(economyNatureChoice
						.getConvertedInput());
				target.add(listContainer);
			}
		});

		final DataView<Company> companyList = new DataView<Company>(
				"company-list", provider) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<Company> item) {
				final Company company = item.getModelObject();
				item.add(new Label("name", company.getName()));
				if (company.getEconomyEntity() != null) {
					item.add(new Label("economyEntity", WicketMessageUtil
							.getResourceString(company.getEconomyEntity()
									.getMessageKey())));
				} else {
					item.add(new Label("economyEntity", ""));
				}
				if (company.getEconomyNature() != null) {
					item.add(new Label("economyNature", WicketMessageUtil
							.getResourceString(company.getEconomyNature()
									.getMessageKey())));
				} else {
					item.add(new Label("economyNature", ""));
				}
				AjaxLink<Void> addLink = new AjaxLink<Void>("add-link") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						companies.add(company);
						target.add(listContainer);
					}
				};

				AjaxLink<Void> deleteLink = new AjaxLink<Void>("delete-link") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						removeCompany(company);
						target.add(listContainer);
					}
				};
				item.add(addLink);
				item.add(deleteLink);
				addLink.setVisible(!containsCompany(company));
				deleteLink.setVisible(containsCompany(company));
			}
		};
		companyList.setOutputMarkupId(true);
		companyList.setItemsPerPage(10);

		final PaginationNavigator navigator = new PaginationNavigator(
				"navigator", companyList);
		final WebMarkupContainer emptyList = new WebMarkupContainer(
				"empty-list");

		listContainer = new WebMarkupContainer("paging-container") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				super.onBeforeRender();
				long companyListSize = provider.size();
				companyList.setVisible(companyListSize > 0);
				navigator.setVisible(companyListSize > 0);
				emptyList.setVisible(companyListSize <= 0);
			}

		};
		searchCompanyPanel.add(listContainer);
		listContainer.setOutputMarkupId(true);
		listContainer.add(companyList);
		listContainer.add(navigator);
		listContainer.add(emptyList);
		container = new WebMarkupContainer("related-company-list");
		container.setOutputMarkupId(true);
		add(container);
		ListView<Company> companyListView = new ListView<Company>(
				"relatedCompanies",
				new LoadableDetachableModel<List<Company>>() {

					private static final long serialVersionUID = 1L;

					@Override
					protected List<Company> load() {
						return new ArrayList<Company>(companies);
					}
				}) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Company> item) {
				final Company company = item.getModelObject();
				item.add(new Label("company-name", company.getName()));
				item.add(new AjaxLink<Void>("delete-icon") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						companies.remove(company);
						target.add(listContainer);
						target.add(container);
					}
				});
			}
		};

		container.add(companyListView);
	}

	@Override
	protected Panel getLeftNaviPanel(String id) {
		return new NewsLeftNaviPanel(id);
	}

	@Override
	protected String getPageClassName() {
		return "news";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return WicketMessageUtil
				.getResourceModel("news.page.title.moblie.newspaper");
	}

	private boolean containsCompany(Company c) {
		for (Company c1 : companies) {
			if (c1.getCompanyID() == c.getCompanyID()) {
				return true;
			}
		}
		return false;
	}

	private void removeCompany(Company c) {
		for (Company c1 : companies) {
			if (c1.getCompanyID() == c.getCompanyID()) {
				companies.remove(c1);
				return;
			}
		}
	}

	@Override
	protected void onBeforeRender() {
		searchForm.setVisible(target.equals(TO_SPECIFIC));
		listContainer.setVisible(target.equals(TO_SPECIFIC));
		super.onBeforeRender();
	}

}
