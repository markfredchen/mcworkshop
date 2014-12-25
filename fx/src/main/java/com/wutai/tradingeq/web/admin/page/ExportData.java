// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.admin.page;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import com.google.inject.Inject;
import com.wutai.tradingeq.domain.Contact;
import com.wutai.tradingeq.service.ServiceManager;


/**
 * @author $Author$
 *
 */
public class ExportData extends AdminBasePage {
    
    private static final long serialVersionUID = 1L;

    @Inject
    private ServiceManager service;
    
    public ExportData() {
        List<Contact> contacts = service.getContacts();
        add(new ListView<Contact>("contacts", contacts) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<Contact> item) {
                Contact contact = item.getModelObject();
                item.add(new Label("name", contact.getName()));
                item.add(new Label("email", contact.getEmail()));
                item.add(new Label("phone", contact.getPhone()));
                item.add(new Label("comments", contact.getComments()));
            }
        });
    }

    @Override
    protected String getActiveSlideBarID() {
        return "nav-export";
    }

}
