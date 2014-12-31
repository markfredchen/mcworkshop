package com.wutai.tradingeq.web.page.contact.us;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;

import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.wutai.tradingeq.view.NaviMenu;
import com.wutai.tradingeq.web.layout.InnerBasePage;

public class ContactUsPage extends InnerBasePage {

	private static final long serialVersionUID = 1L;

	@Override
	protected Panel getContentPanel(String id) {
		return new ContactUsContentPanel(id);
	}

	@Override
	protected List<NaviMenu> getNaviMenus() {
		NaviMenu contactUs = new NaviMenu();
		contactUs.setCurrent(false);
		contactUs.setPageClass(ContactUsPage.class);
		contactUs.setMessageKey("fx.header.navi.menu.sub.about.us.contact.info");
		return Arrays.asList(contactUs);
	}
	
	@Override
	protected String getBrowserTitle() {
		return WicketMessageUtil.getResourceString("fx.browser.title.contact.us");
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		response.render(OnDomReadyHeaderItem.forScript("addMapLink();"));
		super.renderHead(response);
	}
	
	

}
