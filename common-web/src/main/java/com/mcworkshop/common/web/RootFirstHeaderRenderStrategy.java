// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.internal.HtmlHeaderContainer;
import org.apache.wicket.markup.html.internal.HtmlHeaderContainer.HeaderStreamState;
import org.apache.wicket.markup.renderStrategy.ChildFirstHeaderRenderStrategy;
import org.apache.wicket.util.lang.Args;

/**
 * @author $Author$
 * 
 */
public class RootFirstHeaderRenderStrategy extends
        ChildFirstHeaderRenderStrategy {

    @Override
    public void renderHeader(HtmlHeaderContainer headerContainer,
            HeaderStreamState headerStreamState, Component rootComponent) {
        Args.notNull(headerContainer, "headerContainer");
        Args.notNull(rootComponent, "rootComponent");

        // First the application level headers
        renderApplicationLevelHeaders(headerContainer);

        // Then the root component's headers
        renderRootComponent(headerContainer, headerStreamState, rootComponent);

        // Then its child hierarchy
        renderChildHeaders(headerContainer, rootComponent);

    }

}
