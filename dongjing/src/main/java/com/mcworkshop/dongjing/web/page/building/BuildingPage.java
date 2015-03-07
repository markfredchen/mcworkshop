// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.building;

import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author $Author$
 * 
 */
public class BuildingPage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	public BuildingPage(PageParameters params) {
		super(params);
	}

	@Override
	protected Panel getLeftNaviPanel(String id) {
		return new EmptyPanel(id);
	}

	@Override
	protected String getPageClassName() {
		return "building";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return new ResourceModel("dj.title");
	}

}
