// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Inject;
import com.mcworkshop.common.web.component.wizard.Wizard;
import com.mcworkshop.common.web.component.wizard.WizardContentPanel;
import com.mcworkshop.common.web.util.WebUtil;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.authorization.Roles;
import com.mcworkshop.dongjing.domain.Security;
import com.mcworkshop.dongjing.persistence.SecurityMapper;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import com.mcworkshop.dongjing.web.page.company.security.panel.BasicInfoStep;
import com.mcworkshop.dongjing.web.page.company.security.panel.SpecialEquipmentStep;
import com.mcworkshop.dongjing.web.page.company.security.panel.TrainingStep;

/**
 * @author $Author$
 * 
 */
@AuthorizeInstantiation({ Roles.SECURITY_CHANGE })
public class UpsertSecurityPage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	@Inject
	SecurityMapper mapper;
	protected Wizard<Security> wizard;

	public UpsertSecurityPage(PageParameters params) {
		super(params);
		final Long securityID = WebUtil.getParameterAsLong("securityID");
		Security security = new Security();
		if (securityID != null) {
			security = this.mapper.getSecurityInfo(securityID);
		}

		CompoundPropertyModel<Security> securityModel = new CompoundPropertyModel<Security>(
				security);
		List<WizardContentPanel<Security>> steps = new ArrayList<WizardContentPanel<Security>>();
		steps.add(new BasicInfoStep(securityModel));
		steps.add(new SpecialEquipmentStep(securityModel));
		steps.add(new TrainingStep(securityModel));
		add(wizard = new Wizard<Security>("company-security-wizard", security,
				steps) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Security security = (Security) form.getModelObject();
				if (security.getSecurityInfoID() == null) {
					mapper.createSecurityInfo(security);
				} else {
					mapper.updateSecurityInfo(security);
				}
				setResponsePage(SecurityListPage.class);
			}
		});
	}

	@Override
	protected Panel getLeftNaviPanel(String id) {
		return new SecurityLeftNaviPanel(id);
	}

	@Override
	protected String getPageClassName() {
		return "security";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return WicketMessageUtil.getResourceModel("dj.menu.company.security");
	}

}
