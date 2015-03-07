// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.tax;

import com.mcworkshop.dongjing.authorization.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author $Author$
 * 
 */
public class TaxLeftNavigationPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public TaxLeftNavigationPanel(String id) {
		super(id);
		BookmarkablePageLink<Void> tax_list;
		BookmarkablePageLink<Void> tax_import;
		add(tax_list = new BookmarkablePageLink<Void>("company-tax-list-link",
				CompanyTaxDetailPage.class));
		add(tax_import = new BookmarkablePageLink<Void>(
				"company-tax-import-link", ImportTaxDataPage.class));
		MetaDataRoleAuthorizationStrategy.authorize(tax_list, RENDER,
				Roles.TAX_VIEW);
		MetaDataRoleAuthorizationStrategy.authorize(tax_import, RENDER,
				Roles.TAX_IMPORT);
	}

}
