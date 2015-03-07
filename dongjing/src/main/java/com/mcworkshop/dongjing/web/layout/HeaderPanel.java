// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.layout;

import com.mcworkshop.dongjing.web.page.landing.LandingPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author $Author$
 * 
 */
public class HeaderPanel extends Panel {

	private static final long serialVersionUID = -6629567194465076618L;

	public HeaderPanel(String id) {
		super(id);
		add(new BookmarkablePageLink<Void>("home-link", LandingPage.class));
	}

}
