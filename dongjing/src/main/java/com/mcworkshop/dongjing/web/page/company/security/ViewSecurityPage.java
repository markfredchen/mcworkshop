package com.mcworkshop.dongjing.web.page.company.security;

import com.mcworkshop.common.web.component.wizard.WizardContentPanel;
import com.mcworkshop.dongjing.authorization.Roles;
import com.mcworkshop.dongjing.domain.Security;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@AuthorizeInstantiation({ Roles.SECURITY })
public class ViewSecurityPage extends UpsertSecurityPage {

	private static final long serialVersionUID = 1L;

	public ViewSecurityPage(PageParameters params) {
		super(params);
		for (WizardContentPanel<Security> panel : wizard.getSteps()) {
			panel.disablePanel();
		}
		wizard.hideButtonGroup();
	}

}
