// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.examples.web.avatar;

import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.WebPage;

import com.mcworkshop.examples.web.constant.WebConstants;

/**
 * @author $Author$
 *
 */
public class AvatarUploaderPage extends WebPage {

    
    public AvatarUploaderPage() {
        add(new HeaderResponseContainer("footer-scripts", "footer-scripts"));
    }
    
    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssReferenceHeaderItem.forUrl("http://fonts.googleapis.com/css?family=Open+Sans:400,300"));
        response.render(CssReferenceHeaderItem.forUrl(WebConstants.COMMON_CSS_PATH + "/bootstrap.min.css"));
        response.render(CssReferenceHeaderItem.forUrl(WebConstants.COMMON_CSS_PATH + "/jquery.Jcrop.css"));
        response.render(JavaScriptReferenceHeaderItem.forUrl(WebConstants.COMMON_JS_PATH + "/jquery-1.10.2.min.js"));
        response.render(JavaScriptReferenceHeaderItem.forUrl(WebConstants.COMMON_JS_PATH + "/bootstrap.min.js"));
        response.render(JavaScriptReferenceHeaderItem.forUrl(WebConstants.COMMON_JS_PATH + "/jquery.Jcrop.js"));
        response.render(JavaScriptReferenceHeaderItem.forUrl(WebConstants.COMMON_JS_PATH + "/ajaxfileupload.js"));
        
    }
}
