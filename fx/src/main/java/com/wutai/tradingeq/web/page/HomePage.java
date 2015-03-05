// Copyright 2013 Active Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.page;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.wutai.tradingeq.web.layout.BasePage;
import com.wutai.tradingeq.web.page.building.BuildingPage;
import com.wutai.tradingeq.web.page.contact.us.ContactUsPage;
import com.wutai.tradingeq.web.page.ea.advance.EAAdvancePage;
import com.wutai.tradingeq.web.page.fmi.FMIPage;
import com.wutai.tradingeq.web.page.future.cta.FutureCTAPage;
import com.wutai.tradingeq.web.page.tmbox.TMBoxPage;

/**
 * @author mchen1
 * @versin $Rev$, $Date$
 * @since 1.0
 */
public class HomePage extends BasePage{
    private static final long serialVersionUID = 1L;

    public HomePage(){
        this(new PageParameters());
    }
    
    public HomePage(PageParameters params){
        add(new BookmarkablePageLink<Void>("big-icon-news", TMBoxPage.class));
        add(new BookmarkablePageLink<Void>("big-icon-lab", BuildingPage.class));
        add(new BookmarkablePageLink<Void>("tmbox-link", TMBoxPage.class));
        add(new BookmarkablePageLink<Void>("cta-link", FutureCTAPage.class));
        add(new BookmarkablePageLink<Void>("fmi-link", FMIPage.class));
        add(new BookmarkablePageLink<Void>("ea-advanced-link", EAAdvancePage.class));
        add(new BookmarkablePageLink<Void>("new-product-link", TMBoxPage.class));
        add(new BookmarkablePageLink<Void>("contact-us-link", ContactUsPage.class));
        add(new BookmarkablePageLink<Void>("tmbox-menu-link", TMBoxPage.class));
        add(new BookmarkablePageLink<Void>("cta-menu-link", FutureCTAPage.class));
        add(new BookmarkablePageLink<Void>("fmi-menu-link", FMIPage.class));
        add(new BookmarkablePageLink<Void>("ea-advanced-menu-link", EAAdvancePage.class));
        add(new BookmarkablePageLink<Void>("new-product-menu-link", TMBoxPage.class));
        add(new BookmarkablePageLink<Void>("contact-us-menu-link", ContactUsPage.class));
    }

	@Override
	protected String getBrowserTitle() {
		return WicketMessageUtil.getResourceString("fx.browser.title.home");
	}
    
}
