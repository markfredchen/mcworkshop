// Copyright 2013 Active Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.admin.page;

import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.wutai.tradingeq.constant.TradingEQConstants;

/**
 * @author mchen1
 * @versin $Rev$, $Date$
 * @since 1.0
 */
abstract public class AdminBasePage extends WebPage{

    private static final long serialVersionUID = 1L;

	public AdminBasePage(){
        this(new PageParameters());
    }
    
    public AdminBasePage(PageParameters page){
        super();
        add(new AdminHeaderPanel("header"));
        add(new AdminLeftNavigationPanel("sidebar", getActiveSlideBarID()));
    }
    
    abstract protected String getActiveSlideBarID();

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssReferenceHeaderItem.forUrl("http://fonts.googleapis.com/css?family=Open+Sans:400,300"));
        response.render(CssReferenceHeaderItem.forUrl(TradingEQConstants.COMMON_CSS_PATH + "/bootstrap.min.css"));
        response.render(CssReferenceHeaderItem.forUrl(TradingEQConstants.COMMON_CSS_PATH + "/font-awesome.min.css"));
        response.render(CssReferenceHeaderItem.forUrl(TradingEQConstants.COMMON_CSS_PATH + "/ace.min.css"));
        response.render(CssReferenceHeaderItem.forUrl(TradingEQConstants.COMMON_CSS_PATH + "/ace-rtl.min.css"));
        response.render(CssReferenceHeaderItem.forUrl(TradingEQConstants.COMMON_CSS_PATH + "/ace-skins.min.css"));
        response.render(JavaScriptReferenceHeaderItem.forUrl("http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"));
        response.render(JavaScriptReferenceHeaderItem.forUrl(TradingEQConstants.COMMON_JS_PATH + "/bootstrap.min.js"));
        response.render(JavaScriptReferenceHeaderItem.forUrl(TradingEQConstants.COMMON_JS_PATH + "/ace.min.js"));
        response.render(JavaScriptReferenceHeaderItem.forUrl(TradingEQConstants.COMMON_JS_PATH + "/ace-elements.min.js"));
        response.render(JavaScriptReferenceHeaderItem.forUrl(TradingEQConstants.COMMON_JS_PATH + "/typeahead-bs2.min.js"));
    }
}
