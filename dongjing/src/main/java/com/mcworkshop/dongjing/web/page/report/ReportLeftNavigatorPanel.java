// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.report;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author $Author$
 */
public class ReportLeftNavigatorPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public ReportLeftNavigatorPanel(String id) {
        super(id);
        add(new BookmarkablePageLink<Void>("report-tax-sales-link",
                CompanyTaxMonthlyReportPage.class));
        add(new BookmarkablePageLink<Void>("overall-report-link",
                OverallReportPage.class));
        add(new BookmarkablePageLink<Void>("project-report-link", ProjectApplicationReport.class));
        add(new BookmarkablePageLink<Void>("company-area-report-link", CompanyAreaReport.class));
    }

}
