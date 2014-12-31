// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.admin.page;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author $Author$
 *
 */
public class AdminHeaderPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public AdminHeaderPanel(String id) {
        super(id);
        
    }
    
    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(OnDomReadyHeaderItem.forScript("try {ace.settings.check('navbar', 'fixed');} catch (e) {}"));
    }

}
