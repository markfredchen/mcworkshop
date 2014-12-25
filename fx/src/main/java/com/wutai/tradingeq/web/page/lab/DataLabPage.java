// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.page.lab;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.wutai.tradingeq.view.NaviMenu;
import com.wutai.tradingeq.web.layout.InnerBasePage;

/**
 * @author $Author$
 *
 */
public class DataLabPage extends InnerBasePage {

    private static final long serialVersionUID = 1L;

    @Override
    protected Panel getContentPanel(String id) {
        return new DataLabPanel(id);
    }

    @Override
    protected List<NaviMenu> getNaviMenus() {
        NaviMenu tmbox = new NaviMenu();
        tmbox.setCurrent(false);
        tmbox.setMessageKey("fx.header.navi.menu.sub.lab.strategy.lab");
        tmbox.setPageClass(StrategyRealDataReportPage.class);
        NaviMenu tmbox1 = new NaviMenu();
        tmbox1.setCurrent(false);
        tmbox1.setMessageKey("fx.header.navi.menu.sub.lab.data.lab");
        tmbox1.setPageClass(DataLabPage.class);
        return Arrays.asList(tmbox, tmbox1);
    }

    @Override
    protected String getBrowserTitle() {
        return WicketMessageUtil.getResourceString("fx.header.navi.menu.sub.lab.data.lab");
    }

}
