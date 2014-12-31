// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: BasePage.java 54 2014-03-30 16:03:34Z mchen $
package com.mcworkshop.common.web.layout;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
abstract public class BasePage extends WebPage {

    private static final long serialVersionUID = 1L;

    public BasePage() {
        this(new PageParameters());
    }

    public BasePage(PageParameters params) {
        add(new Label("title", getPageTitle()));
        add(getHeaderPanel("header"));
        add(getFooterPanel("footer"));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(OnDomReadyHeaderItem
                .forScript("$('body').attr('class', '" + getPageClassName()
                        + "')"));
        super.renderHead(response);
    }

    abstract protected String getPageClassName();

    abstract protected ResourceModel getPageTitle();

    abstract protected Panel getHeaderPanel(String id);

    abstract protected Panel getFooterPanel(String id);
}
