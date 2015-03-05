// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.head.filter.AbstractHeaderResponseFilter;
import org.apache.wicket.markup.head.filter.FilteringHeaderResponse;
import org.apache.wicket.markup.head.filter.OppositeHeaderResponseFilter;
import org.apache.wicket.markup.html.IHeaderResponseDecorator;
import org.apache.wicket.util.lang.Args;

/**
 * @author $Author$
 * 
 */
public class RenderJavaScriptToFooterHeaderResponseDecorator implements
        IHeaderResponseDecorator {

    final List<FilteringHeaderResponse.IHeaderResponseFilter> filters;

    /**
     * Construct.
     * 
     * @param filterName
     *            The name of the footer container
     */
    public RenderJavaScriptToFooterHeaderResponseDecorator(
            final String filterName) {
        Args.notEmpty(filterName, "filterName");

        filters = new ArrayList<FilteringHeaderResponse.IHeaderResponseFilter>();

        final AbstractHeaderResponseFilter jsAcceptingFilter = new AbstractHeaderResponseFilter(
                filterName) {
            public boolean accepts(HeaderItem item) {
                return item instanceof JavaScriptHeaderItem
                        || item instanceof OnDomReadyHeaderItem
                        || item instanceof OnLoadHeaderItem;
            }
        };

        filters.add(jsAcceptingFilter);
        filters.add(new OppositeHeaderResponseFilter("headBucket",
                jsAcceptingFilter));
    }

    /**
     * decorates the original {@link IHeaderResponse}
     * 
     * @param response
     *            original {@link IHeaderResponse}
     * @return decorated {@link IHeaderResponse}
     */
    public IHeaderResponse decorate(final IHeaderResponse response) {
        return new FilteringHeaderResponse(response, "headBucket", filters);
    }
}
