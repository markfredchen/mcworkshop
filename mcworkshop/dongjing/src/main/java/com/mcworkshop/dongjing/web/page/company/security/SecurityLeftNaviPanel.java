// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.security;

import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.mcworkshop.dongjing.authorization.Roles;

/**
 * @author $Author$
 * 
 */
public class SecurityLeftNaviPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public SecurityLeftNaviPanel(String id) {
		super(id);
		BookmarkablePageLink<Void> sm_change;
		BookmarkablePageLink<Void> sm_list;
		add(sm_list = new BookmarkablePageLink<Void>(
				"company-security-list-link", SecurityListPage.class));
		add(sm_change = new BookmarkablePageLink<Void>(
				"company-security-create-link", UpsertSecurityPage.class));
		MetaDataRoleAuthorizationStrategy.authorize(sm_change, RENDER,
				Roles.SECURITY_CHANGE);
		MetaDataRoleAuthorizationStrategy.authorize(sm_list, RENDER,
				Roles.SECURITY);
	}

}
