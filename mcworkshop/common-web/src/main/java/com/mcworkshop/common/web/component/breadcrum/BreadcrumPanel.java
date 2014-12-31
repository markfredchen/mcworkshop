// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component.breadcrum;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author $Author$
 * 
 */
public class BreadcrumPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public BreadcrumPanel(String id, List<BreadcrumItem> items,
            final String separator) {
        super(id);
        add(new ListView<BreadcrumItem>("breadcrum-items", items) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<BreadcrumItem> item) {
                BreadcrumItem bi = item.getModelObject();
                if (item.getIndex() == 0) {
                    item.add(new Label("separator", ""));
                } else {
                    item.add(new Label("separator", separator));
                }
                BookmarkablePageLink<Void> link = new BookmarkablePageLink<Void>(
                        "target-page", bi.getTargetPage());
                link.add(new Label("target-page-name", bi.getText()));
                item.add(link);
                link.setEnabled(bi.isLinkable());
            }
        });
    }

}
