// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.error;

import com.mcworkshop.common.configuration.Config;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.configuration.DJConfigurationKey;
import com.mcworkshop.dongjing.web.layout.DJBasePage;
import com.mcworkshop.dongjing.web.layout.HeaderPanel;
import com.mcworkshop.dongjing.web.page.home.HomePage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;

/**
 * @author $Author$
 * 
 */
public class ErrorPage extends DJBasePage {

	private static final long serialVersionUID = 1L;

	public ErrorPage() {
		add(new BookmarkablePageLink<Void>("home", HomePage.class));
		add(new ExternalLink("email", "mailto:["
				+ Config.getConfig(DJConfigurationKey.SYSTEM_ADMIN_EMAIL) + "]"));
	}

	@Override
	protected String getPageClassName() {
		return "error";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return WicketMessageUtil.getResourceModel("dj.title");
	}

	@Override
	protected Panel getHeaderPanel(String id) {
		return new HeaderPanel(id);
	}

}
