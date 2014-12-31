// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.page.lab;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;

import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.wutai.tradingeq.constant.TradingEQConstants;
import com.wutai.tradingeq.view.NaviMenu;
import com.wutai.tradingeq.web.FXApplication;
import com.wutai.tradingeq.web.layout.InnerBasePage;
import com.wutai.tradingeq.web.page.tmbox.TMBoxRealDataReportPanel;

/**
 * @author $Author$
 *
 */
public class StrategyRealDataReportPage extends InnerBasePage {

    private static final long serialVersionUID = 1L;
    
    public StrategyRealDataReportPage() {
        super();
        
    }

    @Override
    protected Panel getContentPanel(String id) {
        return new TMBoxRealDataReportPanel(id, FXApplication.get().getStrategyLabData(), "策略实验室");
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
        return WicketMessageUtil.getResourceString("fx.header.navi.menu.sub.lab.strategy.lab");
    }
    
    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(JavaScriptHeaderItem.forUrl(TradingEQConstants.STATIC_PATH + "common/amcharts/amcharts.js"));
        response.render(JavaScriptHeaderItem.forUrl(TradingEQConstants.STATIC_PATH + "common/amcharts/serial.js"));
        response.render(JavaScriptHeaderItem.forUrl(TradingEQConstants.STATIC_PATH + "js/tmbox.js"));
        response.render(OnDomReadyHeaderItem.forScript("TMBoxRealData.init()"));
    }

}
