// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company;

import com.mcworkshop.dongjing.authorization.Roles;
import com.mcworkshop.dongjing.web.page.company.basic.CompanyListPage;
import com.mcworkshop.dongjing.web.page.company.basic.ExportCompanyPage;
import com.mcworkshop.dongjing.web.page.company.basic.ImportCompanyPage;
import com.mcworkshop.dongjing.web.page.company.basic.UpsertComanyPage;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author $Author$
 */
public class CompanyLeftNaviPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public CompanyLeftNaviPanel(String id) {
        super(id);
        BookmarkablePageLink<Void> cm_change;
        BookmarkablePageLink<Void> cm_view;
        BookmarkablePageLink<Void> cm_import;
        BookmarkablePageLink<Void> cm_export;
        add(cm_view = new BookmarkablePageLink<Void>("company-basic-list-link",
                CompanyListPage.class));
        add(cm_change = new BookmarkablePageLink<Void>(
                "company-basic-create-link", UpsertComanyPage.class));
        add(cm_import = new BookmarkablePageLink<Void>(
                "company-basic-import-link", ImportCompanyPage.class));
        add(cm_export = new BookmarkablePageLink<Void>("company-basic-export-link", ExportCompanyPage.class));
        MetaDataRoleAuthorizationStrategy.authorize(cm_view, RENDER, Roles.CM);
        MetaDataRoleAuthorizationStrategy.authorize(cm_change, RENDER,
                Roles.CM_CHANGE);
        MetaDataRoleAuthorizationStrategy.authorize(cm_import, RENDER,
                Roles.CM_IMPORT);
        MetaDataRoleAuthorizationStrategy.authorize(cm_export, RENDER,
                Roles.CM_EXPORT);
    }

}
