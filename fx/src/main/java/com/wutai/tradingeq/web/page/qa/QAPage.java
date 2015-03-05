// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.page.qa;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.wutai.tradingeq.view.NaviMenu;
import com.wutai.tradingeq.web.layout.InnerBasePage;

/**
 * @author $Author$
 *
 */
public class QAPage extends InnerBasePage {

    private static final long serialVersionUID = 1L;

    @Override
    protected Panel getContentPanel(String id) {
        return new QAContentPanel(id);
    }

    @Override
    protected List<NaviMenu> getNaviMenus() {
        NaviMenu qa = new NaviMenu();
        qa.setCurrent(true);
        qa.setMessageKey("fx.header.navi.menu.qa");
        qa.setPageClass(QAPage.class);
        return Arrays.asList(qa);
    }

    @Override
    protected String getBrowserTitle() {
        return "Trading 交易工房";
    }

}
