// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component.carousel;

import java.util.Arrays;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;

import com.mcworkshop.common.web.BaseWebApplication;

/**
 * @author $Author$
 * 
 */
public class FlexsliderJavascriptResourceReference extends UrlResourceReference {

    private static final long serialVersionUID = 1L;

    public FlexsliderJavascriptResourceReference() {
        super(Url.parse(BaseWebApplication.get().getStaticPath()
                + "/common/assets/plugins/flexslider/jquery.flexslider-min.js"));
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        return Arrays.asList(JavaScriptHeaderItem
                .forReference(BaseWebApplication.get()
                        .getJavaScriptLibrarySettings().getJQueryReference()));
    }
}
