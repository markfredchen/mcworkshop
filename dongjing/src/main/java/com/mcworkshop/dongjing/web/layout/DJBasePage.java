// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.layout;

import com.mcworkshop.common.web.layout.BasePage;
import com.mcworkshop.dongjing.constants.WebConstants;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author $Author$
 * 
 */
abstract public class DJBasePage extends BasePage {

	private static final long serialVersionUID = 1L;

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forUrl(WebConstants.COMMON_CSS_PATH
				+ "/bootstrap.css"));
		response.render(CssHeaderItem.forUrl(WebConstants.COMMON_CSS_PATH
				+ "/bootstrap-datetimepicker.css"));
		response.render(CssHeaderItem.forUrl(WebConstants.DJ_CSS_PATH
				+ "/dongjing.css"));
		response.render(JavaScriptHeaderItem.forUrl(WebConstants.COMMON_JS_PATH
				+ "/bootstrap.js"));
		response.render(JavaScriptHeaderItem.forUrl(WebConstants.COMMON_JS_PATH
				+ "/common.ui.js"));
		response.render(JavaScriptHeaderItem.forUrl(WebConstants.COMMON_JS_PATH
				+ "/bootstrap-datetimepicker.js"));
		response.render(JavaScriptHeaderItem.forUrl(WebConstants.DJ_JS_PATH
				+ "/dongjing.js"));
	}

	@Override
	protected Panel getFooterPanel(String id) {
		return new FooterPanel(id);
	}
}
