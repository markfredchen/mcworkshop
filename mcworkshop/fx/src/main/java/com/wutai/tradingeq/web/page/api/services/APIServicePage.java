package com.wutai.tradingeq.web.page.api.services;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.wutai.tradingeq.view.NaviMenu;
import com.wutai.tradingeq.web.layout.InnerBasePage;

public class APIServicePage extends InnerBasePage{

	private static final long serialVersionUID = 1L;

	@Override
	protected Panel getContentPanel(String id) {
		return new APIServiceContentPanel(id);
	}

	@Override
	protected List<NaviMenu> getNaviMenus() {
		NaviMenu m = new NaviMenu();
		m.setMessageKey("fx.header.navi.menu.sub.fts.api.service");
		m.setPageClass(APIServicePage.class);
		return Arrays.asList(m);
	}

	@Override
	protected String getBrowserTitle() {
		return WicketMessageUtil.getResourceString("fx.browser.title.api.service");
	}

}
