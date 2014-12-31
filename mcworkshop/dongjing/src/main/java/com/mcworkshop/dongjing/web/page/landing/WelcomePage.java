// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: WelcomePage.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.web.page.landing;

import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mcworkshop.dongjing.web.layout.DJContentBasePage;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class WelcomePage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	public WelcomePage(PageParameters params) {
		super(params);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getPageClassName() {
		return "welcome";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return new ResourceModel("dj.landing.title");
	}

	@Override
	protected Panel getLeftNaviPanel(String id) {
		return new EmptyPanel(id);
	}

}
