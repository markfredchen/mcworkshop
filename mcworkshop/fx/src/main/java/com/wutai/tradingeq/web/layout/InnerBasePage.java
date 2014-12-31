// Copyright 2013 Active Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.layout;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.wutai.tradingeq.view.NaviMenu;

/**
 * @author mchen1
 * @versin $Rev$, $Date$
 * @since 1.0
 */
public abstract class InnerBasePage extends BasePage {

    
    public InnerBasePage(){
        add(new LeftNaviPanel("left-navi-panel", getNaviMenus()));
        add(getContentPanel("centent-panel"));
    }
    
    abstract protected Panel getContentPanel(String id);
    abstract protected List<NaviMenu> getNaviMenus();
    
}
