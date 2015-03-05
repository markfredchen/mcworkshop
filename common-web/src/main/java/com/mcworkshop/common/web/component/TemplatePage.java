// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author $Author$
 * 
 */
public class TemplatePage extends WebPage {
    private static final long  serialVersionUID = 1L;
    protected static final Log log              = LogFactory
                                                        .getLog(TemplatePage.class);

    public TemplatePage() {
        this(new PageParameters());
    }

    public TemplatePage(PageParameters params) {
        super(params);
    }
}
