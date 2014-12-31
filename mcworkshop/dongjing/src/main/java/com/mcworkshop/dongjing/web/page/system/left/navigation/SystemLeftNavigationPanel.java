// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.system.left.navigation;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.mcworkshop.dongjing.web.page.system.role.RoleManagePage;
import com.mcworkshop.dongjing.web.page.system.user.UserManagePage;

/**
 * @author $Author$
 * 
 */
public class SystemLeftNavigationPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public SystemLeftNavigationPanel(String id) {
		super(id);
		add(new BookmarkablePageLink<Void>("user-manager-link",
				UserManagePage.class));
		add(new BookmarkablePageLink<Void>("role-manager-link",
				RoleManagePage.class));
	}

}
