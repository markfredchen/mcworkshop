package com.mcworkshop.dongjing.web.page.company.basic;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mcworkshop.common.web.component.wizard.WizardContentPanel;
import com.mcworkshop.dongjing.authorization.Roles;
import com.mcworkshop.dongjing.domain.Company;

@AuthorizeInstantiation({ Roles.CM })
public class ViewCompanyPage extends UpsertComanyPage {

	private static final long serialVersionUID = 1L;

	public ViewCompanyPage(PageParameters params) {
		super(params);
		for (WizardContentPanel<Company> panel : wizard.getSteps()) {
			panel.disablePanel();
		}
		wizard.hideButtonGroup();
	}

}
