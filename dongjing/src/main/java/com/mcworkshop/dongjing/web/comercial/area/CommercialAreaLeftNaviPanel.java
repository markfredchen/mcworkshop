// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: CommercialAreaLeftNaviPanel.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.web.comercial.area;

import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.mcworkshop.dongjing.authorization.Roles;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class CommercialAreaLeftNaviPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public CommercialAreaLeftNaviPanel(String id) {
		super(id);
		BookmarkablePageLink<Void> ca_change;
		BookmarkablePageLink<Void> ca_list;
		add(ca_list = new BookmarkablePageLink<Void>(
				"commercial-area-list-link", CommercialAreaListPage.class));
		add(ca_change = new BookmarkablePageLink<Void>(
				"commercial-area-create-link", UpsertCommercialAreaPage.class));
		MetaDataRoleAuthorizationStrategy.authorize(ca_change, RENDER,
				Roles.CA_CHANGE);
		MetaDataRoleAuthorizationStrategy.authorize(ca_list, RENDER, Roles.CA);
	}

}
