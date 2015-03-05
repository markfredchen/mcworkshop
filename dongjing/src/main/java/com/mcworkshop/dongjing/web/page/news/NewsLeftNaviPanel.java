// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.news;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author $Author$
 * 
 */
public class NewsLeftNaviPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public NewsLeftNaviPanel(String id) {
		super(id);
		add(new BookmarkablePageLink<Void>("sms-link", SMSPage.class));
		add(new BookmarkablePageLink<Void>("email-link", EmailPage.class));
	}

}
