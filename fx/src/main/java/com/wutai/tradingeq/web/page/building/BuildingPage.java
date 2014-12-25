package com.wutai.tradingeq.web.page.building;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.wutai.tradingeq.view.NaviMenu;
import com.wutai.tradingeq.web.layout.InnerBasePage;

public class BuildingPage extends InnerBasePage {

	private static final long serialVersionUID = 1L;

	@Override
	protected Panel getContentPanel(String id) {
		return new BuildingContentPanel(id);
	}

	@Override
	protected List<NaviMenu> getNaviMenus() {
		return null;
	}

	@Override
	protected String getBrowserTitle() {
		return WicketMessageUtil.getResourceString("fx.browser.title.building");
	}

}
