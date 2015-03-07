// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.profile;

import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author $Author$
 * 
 */
public class ViewUserProfile extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	public ViewUserProfile(PageParameters params) {
		super(params);
	}

	@Override
	protected Panel getLeftNaviPanel(String id) {
		return null;
	}

	@Override
	protected String getPageClassName() {
		return null;
	}

	@Override
	protected ResourceModel getPageTitle() {
		return null;
	}

}
