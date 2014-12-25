// Copyright 2013 Active Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.layout;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.wutai.tradingeq.web.page.api.services.APIServicePage;
import com.wutai.tradingeq.web.page.building.BuildingPage;
import com.wutai.tradingeq.web.page.contact.us.ContactUsPage;
import com.wutai.tradingeq.web.page.ea.advance.EAAdvancePage;
import com.wutai.tradingeq.web.page.fmi.FMIPage;
import com.wutai.tradingeq.web.page.future.cta.FutureCTAPage;
import com.wutai.tradingeq.web.page.lab.DataLabPage;
import com.wutai.tradingeq.web.page.lab.StrategyRealDataReportPage;
import com.wutai.tradingeq.web.page.qa.QAPage;
import com.wutai.tradingeq.web.page.tmbox.TMBoxPage;

/**
 * @author mchen1
 * @versin $Rev$, $Date$
 * @since 1.0
 */
public class HeaderPanel extends Panel {

    private static final long serialVersionUID = 1L;
    
    public HeaderPanel(String id) {
        super(id);
        add(new BookmarkablePageLink<Void>("tmbox-link", TMBoxPage.class));
        add(new BookmarkablePageLink<Void>("cta-link", FutureCTAPage.class));
        add(new BookmarkablePageLink<Void>("api-service-link", APIServicePage.class));
        add(new BookmarkablePageLink<Void>("fmi-link", FMIPage.class));
        add(new BookmarkablePageLink<Void>("eaadvanced-link", EAAdvancePage.class));
        add(new BookmarkablePageLink<Void>("strategy-lab-link", StrategyRealDataReportPage.class));
        add(new BookmarkablePageLink<Void>("data-lab-link", DataLabPage.class));
        add(new BookmarkablePageLink<Void>("new-product-link", TMBoxPage.class));
        add(new BookmarkablePageLink<Void>("contact-us-link", ContactUsPage.class));
        add(new BookmarkablePageLink<Void>("qa-link", QAPage.class));
    }
}
