// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.project;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author $Author$
 * 
 */
public class ProjectLeftNaviPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public ProjectLeftNaviPanel(String id) {
		super(id);
		add(new BookmarkablePageLink<Void>("create-project-link",
				UpsertProjectInfoPage.class));
		add(new BookmarkablePageLink<Void>("search-project-link",
				ProjectListPage.class));
	}

}
