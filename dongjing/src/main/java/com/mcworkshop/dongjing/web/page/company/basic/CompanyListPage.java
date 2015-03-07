// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.basic;

import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.authorization.Roles;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import com.mcworkshop.dongjing.web.layout.MenuHeaderPanel;
import com.mcworkshop.dongjing.web.page.company.CompanyLeftNaviPanel;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author $Author$
 * 
 */
@AuthorizeInstantiation({ Roles.CM })
public class CompanyListPage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	public CompanyListPage(PageParameters params) {
		super(params);
		add(new SearchCompanyPanel("search-company-panel"));
	}

	@Override
	protected String getPageClassName() {
		return "company-basic-list";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return WicketMessageUtil
				.getResourceModel("dj.company.management.title");
	}

	@Override
	protected Panel getHeaderPanel(String id) {
		return new MenuHeaderPanel(id);
	}

	@Override
	protected Panel getLeftNaviPanel(String id) {
		return new CompanyLeftNaviPanel(id);
	}

}
