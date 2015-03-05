// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.layout;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author $Author$
 * 
 */
abstract public class DJContentBasePage extends DJBasePage {

	private static final long serialVersionUID = 1L;

	public DJContentBasePage(PageParameters params) {
		this.add(this.getLeftNaviPanel("left-navi"));
		this.add(new ChangePasswordPanel("changePasswordDialog"));
	}

	@Override
	protected Panel getHeaderPanel(String id) {
		return new MenuHeaderPanel(id);
	}

	abstract protected Panel getLeftNaviPanel(String id);
}
