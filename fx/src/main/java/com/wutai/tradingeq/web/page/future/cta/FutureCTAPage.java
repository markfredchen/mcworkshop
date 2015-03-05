package com.wutai.tradingeq.web.page.future.cta;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.wutai.tradingeq.view.NaviMenu;
import com.wutai.tradingeq.web.layout.InnerBasePage;
import com.wutai.tradingeq.web.page.tmbox.TMBoxPage;
import com.wutai.tradingeq.web.page.tmbox.TMBoxRealDataReportPage;

public class FutureCTAPage extends InnerBasePage {

	private static final long serialVersionUID = 1L;

    @Override
	protected Panel getContentPanel(String id) {
		return new FutureCTAContentPanel(id);
	}

	@Override
	protected List<NaviMenu> getNaviMenus() {
	    NaviMenu tmbox = new NaviMenu();
        tmbox.setCurrent(false);
        tmbox.setMessageKey("fx.header.navi.menu.sub.spt.fx.tmbox");
        tmbox.setPageClass(TMBoxPage.class);
        NaviMenu tmboxRealData = new NaviMenu();
        tmboxRealData.setCurrent(true);
        tmboxRealData.setMessageKey("fx.header.navi.menu.sub.spt.fx.tmbox.real.data");
        tmboxRealData.setPageClass(TMBoxRealDataReportPage.class);
        NaviMenu cta = new NaviMenu();
        cta.setCurrent(false);
        cta.setMessageKey("fx.header.navi.menu.sub.spt.future.cta");
        cta.setPageClass(FutureCTAPage.class);
        NaviMenu ctaRealData = new NaviMenu();
        ctaRealData.setCurrent(false);
        ctaRealData.setMessageKey("fx.header.navi.menu.sub.spt.future.real.data");
        ctaRealData.setPageClass(FutureRealDataReportPage.class);
        return Arrays.asList(tmbox, tmboxRealData, cta, ctaRealData);
	}

	@Override
	protected String getBrowserTitle() {
		return WicketMessageUtil.getResourceString("fx.browser.title.future.cta");
	}

}
