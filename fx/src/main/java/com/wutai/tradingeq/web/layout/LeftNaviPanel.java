// Copyright 2013 Active Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.layout;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;

import com.wutai.tradingeq.view.NaviMenu;

/**
 * @author mchen1
 * @versin $Rev$, $Date$
 * @since 1.0
 */
public class LeftNaviPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public LeftNaviPanel(String id, List<NaviMenu> menus) {
        super(id);
        add(new ListView<NaviMenu>("navi-menu-item", menus) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<NaviMenu> item) {
				NaviMenu menuItem = item.getModelObject();
				BookmarkablePageLink<Void> menuItemLink = new BookmarkablePageLink<Void>("navi-menu-item-link", menuItem.getPageClass());
				menuItemLink.add(new Label("navi-menu-item-name", new ResourceModel(menuItem.getMessageKey())));
				if(menuItem.isCurrent()) {
				    AttributeModifier.append("class", "active");
				}
				item.add(menuItemLink);
			}
		});
    }

}
