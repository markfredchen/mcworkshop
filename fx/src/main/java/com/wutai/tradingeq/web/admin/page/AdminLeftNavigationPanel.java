// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.admin.page;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author $Author$
 *
 */
public class AdminLeftNavigationPanel extends Panel {

    private static final long serialVersionUID = 1L;
    
    private String activeMenuID;

    public AdminLeftNavigationPanel(String id, String activeMenuID) {
        super(id);
        this.activeMenuID = activeMenuID;
        add(new BookmarkablePageLink<Void>("import-tmbox", ImportTMBoxPage.class));
        add(new BookmarkablePageLink<Void>("import-future", ImportFuturePage.class));
        add(new BookmarkablePageLink<Void>("import-strategy", ImportStrategyPage.class));
        add(new BookmarkablePageLink<Void>("export", ExportData.class));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(OnDomReadyHeaderItem.forScript("try{ace.settings.check('sidebar' , 'fixed')}catch(e){}"));
        response.render(OnDomReadyHeaderItem.forScript("try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}"));
        response.render(OnDomReadyHeaderItem.forScript("$('#" + activeMenuID + "').addClass('active')"));
    }

    
}
