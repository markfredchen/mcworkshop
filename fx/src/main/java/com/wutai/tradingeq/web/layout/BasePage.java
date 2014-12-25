// Copyright 2013 Active Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.layout;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.wutai.tradingeq.constant.TradingEQConstants;

/**
 * @author mchen1
 * @versin $Rev$, $Date$
 * @since 1.0
 */
abstract public class BasePage extends WebPage {

    private static final long serialVersionUID = 1L;
    
    public BasePage() {
        this(new PageParameters());
    }

    public BasePage(PageParameters pageParameters) {
    	add(new Label("title", getBrowserTitle()));
		add(new HeaderPanel("header"));
		add(new FooterPanel("footer"));
		add(new Label("keywords", getBrowserTitle()));
    }

    abstract protected String getBrowserTitle();

	@Override
    public void renderHead(IHeaderResponse response) {
	    response.render(CssReferenceHeaderItem.forUrl("http://fonts.googleapis.com/css?family=Open+Sans:400,300"));
        response.render(JavaScriptHeaderItem.forUrl(TradingEQConstants.STATIC_PATH + "js/jquery/jquery-1.10.2.js"));
        response.render(JavaScriptHeaderItem.forUrl(TradingEQConstants.STATIC_PATH + "js/jquery/jquery-ui.js"));
        response.render(JavaScriptHeaderItem.forUrl(TradingEQConstants.STATIC_PATH + "js/tradingeq.js"));
        response.render(CssHeaderItem.forUrl(TradingEQConstants.STATIC_PATH + "css/jquery/jquery-ui.css"));
        response.render(CssHeaderItem.forUrl(TradingEQConstants.STATIC_PATH + "css/bootstrap.css"));
        response.render(CssHeaderItem.forUrl(TradingEQConstants.STATIC_PATH + "css/tradingeq.css"));
        super.renderHead(response);
    }
    
    
    

}
