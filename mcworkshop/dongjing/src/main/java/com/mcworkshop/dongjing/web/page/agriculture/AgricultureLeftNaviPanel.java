// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.agriculture;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author $Author$
 * 
 */
public class AgricultureLeftNaviPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public AgricultureLeftNaviPanel(String id) {
		super(id);
		add(new BookmarkablePageLink<Void>("agri-list-link",
				AgricultureListPage.class));
		add(new BookmarkablePageLink<Void>("agri-create-link",
				UpsertAgriculturePage.class));
	}

}
