package com.wutai.tradingeq.web.page.ea.advance;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.wutai.tradingeq.view.NaviMenu;
import com.wutai.tradingeq.web.layout.InnerBasePage;
import com.wutai.tradingeq.web.page.fmi.FMIPage;

public class EAAdvancePage extends InnerBasePage {

	@Override
	protected Panel getContentPanel(String id) {
		return new EAAdvanceContentPanel(id);
	}

	@Override
	protected List<NaviMenu> getNaviMenus() {
		NaviMenu fmi = new NaviMenu();
		fmi.setPageClass(FMIPage.class);
		fmi.setMessageKey("fx.header.navi.menu.sub.edu.finance.market.intro");
		NaviMenu ea = new NaviMenu();
		ea.setPageClass(EAAdvancePage.class);
		ea.setMessageKey("fx.header.navi.menu.sub.edu.ea.advanced");
		return Arrays.asList(fmi, ea);
	}

	@Override
	protected String getBrowserTitle() {
		return WicketMessageUtil.getResourceString("fx.browser.title.ea.advanced");
	}

}
