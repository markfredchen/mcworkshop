// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.basic;

import com.google.inject.Inject;
import com.mcworkshop.common.exception.ExceptionUtil;
import com.mcworkshop.common.web.component.wizard.Wizard;
import com.mcworkshop.common.web.component.wizard.WizardContentPanel;
import com.mcworkshop.common.web.util.WebUtil;
import com.mcworkshop.dongjing.authorization.Roles;
import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.domain.Currency;
import com.mcworkshop.dongjing.security.ACLBlockUtil;
import com.mcworkshop.dongjing.service.DJService;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import com.mcworkshop.dongjing.web.layout.MenuHeaderPanel;
import com.mcworkshop.dongjing.web.page.company.CompanyLeftNaviPanel;
import com.mcworkshop.dongjing.web.page.company.basic.panel.*;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.ArrayList;
import java.util.List;

/**
 * @author $Author$
 * 
 */
@AuthorizeInstantiation({ Roles.CM_CHANGE })
public class UpsertComanyPage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJService service;
	protected Wizard<Company> wizard;

	public UpsertComanyPage(PageParameters params) {
		super(params);

		final Long companyID = WebUtil.getParameterAsLong("companyID");
		Company company = new Company();
		company.setRegion(ACLBlockUtil.getOwnAffiliateBlock());
		company.setCurrency(Currency.CNY);
		if (companyID != null) {
			company = this.service.getCompany(companyID);
		}
		CompoundPropertyModel<Company> companyModel = new CompoundPropertyModel<Company>(
				company);
		final List<WizardContentPanel<Company>> steps = new ArrayList<WizardContentPanel<Company>>();
		steps.add(new BaseInformationStep(companyModel));
		steps.add(new OtherInformationStep(companyModel));
		steps.add(new MemberStep(companyModel));
		steps.add(new CommercialAreaInfo(companyModel));
		steps.add(new PartyStep(companyModel));
		steps.add(new AdvanceManagementStep(companyModel));
		steps.add(new ProjectManagementStep(companyModel));
		add(wizard = new Wizard<Company>("company-wizard", company, steps) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Company company = (Company) form.getModelObject();
				if(ACLBlockUtil.getOwnAffiliateBlock() !=null){
					company.setRegion(ACLBlockUtil
							.getOwnAffiliateBlock());
				}
				try {
					if (company.getCompanyID() == null) {
						UpsertComanyPage.this.service.createCompany(company);
					} else {
						Company persisted = UpsertComanyPage.this.service
								.getCompany(companyID);
						UpsertComanyPage.this.service.updateCompany(company,
								persisted);
					}
					wizard.setCurrentWizardStep(steps.get(0));
					target.add(wizard);
					target.appendJavaScript("$('#feedback').html('企业已创建或更新成功')");
					target.appendJavaScript("$('#company-feedback').modal('show')");
					target.appendJavaScript("$(window).scrollTop(0)");
				} catch (Throwable e) {
					PersistenceException ex = ExceptionUtil.findCause(e,
							PersistenceException.class);
					if (ex != null
							&& ex.getMessage().contains(
									"IDX_company_name_UNIQUE")) {
						target.appendJavaScript("$('#feedback').html('企业已经存在')");
						target.appendJavaScript("$('#company-feedback').modal('show')");
					} else {
						throw new RuntimeException(e);
					}

				}
			}

		});
	}

	@Override
	protected String getPageClassName() {
		return "company-basic";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return new ResourceModel("dj.menu.company.basic");
	}

	@Override
	protected Panel getHeaderPanel(String id) {
		return new MenuHeaderPanel(id);
	}

	@Override
	protected Panel getLeftNaviPanel(String id) {
		return new CompanyLeftNaviPanel(id);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
	}

}
