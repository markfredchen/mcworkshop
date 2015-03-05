// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.layout;

import com.mcworkshop.dongjing.authorization.Roles;
import com.mcworkshop.dongjing.web.DJWebSession;
import com.mcworkshop.dongjing.web.comercial.area.CommercialAreaListPage;
import com.mcworkshop.dongjing.web.comercial.area.UpsertCommercialAreaPage;
import com.mcworkshop.dongjing.web.page.agriculture.AgricultureListPage;
import com.mcworkshop.dongjing.web.page.agriculture.UpsertAgriculturePage;
import com.mcworkshop.dongjing.web.page.company.basic.CompanyListPage;
import com.mcworkshop.dongjing.web.page.company.basic.ExportCompanyPage;
import com.mcworkshop.dongjing.web.page.company.basic.ImportCompanyPage;
import com.mcworkshop.dongjing.web.page.company.basic.UpsertComanyPage;
import com.mcworkshop.dongjing.web.page.company.security.SecurityListPage;
import com.mcworkshop.dongjing.web.page.company.security.UpsertSecurityPage;
import com.mcworkshop.dongjing.web.page.company.tax.CompanyTaxDetailPage;
import com.mcworkshop.dongjing.web.page.company.tax.ImportTaxDataPage;
import com.mcworkshop.dongjing.web.page.home.HomePage;
import com.mcworkshop.dongjing.web.page.landing.LandingPage;
import com.mcworkshop.dongjing.web.page.news.EmailPage;
import com.mcworkshop.dongjing.web.page.news.SMSPage;
import com.mcworkshop.dongjing.web.page.project.ProjectListPage;
import com.mcworkshop.dongjing.web.page.project.UpsertProjectInfoPage;
import com.mcworkshop.dongjing.web.page.report.CompanyTaxMonthlyReportPage;
import com.mcworkshop.dongjing.web.page.report.OverallReportPage;
import com.mcworkshop.dongjing.web.page.system.role.RoleManagePage;
import com.mcworkshop.dongjing.web.page.system.user.UserManagePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author $Author$
 */
@AuthorizeInstantiation(Roles.USER)
public class MenuHeaderPanel extends Panel {

    private static final long serialVersionUID = 1L;

    public MenuHeaderPanel(String id) {
        super(id);
        add(new BookmarkablePageLink<Void>("home-link", HomePage.class));
        add(new BookmarkablePageLink<Void>("home-menu-link", HomePage.class));
        add(new Label("user-full-name", DJWebSession.get().getUser()
                .getContact().getFullName()));
        add(new AjaxLink<Void>("logout-link") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                DJWebSession.get().invalidate();
                setResponsePage(HomePage.class);
            }
        });

        WebMarkupContainer cm = new WebMarkupContainer("company-basic");
        cm.add(new BookmarkablePageLink<Void>("company-basic-link",
                LandingPage.class, new PageParameters().add("module", "CM")));
        BookmarkablePageLink<Void> searchCompanyLink = new BookmarkablePageLink<Void>(
                "search-company-basic-link", CompanyListPage.class);
        BookmarkablePageLink<Void> upsertCompanyLink = new BookmarkablePageLink<Void>(
                "create-company-basic-link", UpsertComanyPage.class);
        BookmarkablePageLink<Void> importCompanyLink = new BookmarkablePageLink<Void>(
                "import-company-basic-link", ImportCompanyPage.class);
        BookmarkablePageLink<Void> exportCompanyLink = new BookmarkablePageLink<Void>(
                "export-company-basic-link", ExportCompanyPage.class);
        cm.add(searchCompanyLink);
        cm.add(upsertCompanyLink);
        cm.add(importCompanyLink);
        cm.add(exportCompanyLink);
        MetaDataRoleAuthorizationStrategy.authorize(searchCompanyLink, RENDER,
                Roles.CM);
        MetaDataRoleAuthorizationStrategy.authorize(upsertCompanyLink, RENDER,
                Roles.CM_CHANGE);
        MetaDataRoleAuthorizationStrategy.authorize(importCompanyLink, RENDER,
                Roles.CM_IMPORT);
        MetaDataRoleAuthorizationStrategy.authorize(exportCompanyLink, RENDER,
                Roles.CM_EXPORT);

        WebMarkupContainer tax = new WebMarkupContainer("company-tax");
        tax.add(new BookmarkablePageLink<Void>("company-tax-link",
                LandingPage.class, new PageParameters().add("module", "TAX")));
        BookmarkablePageLink<Void> viewTaxLink = new BookmarkablePageLink<Void>(
                "search-company-tax-link", CompanyTaxDetailPage.class);
        BookmarkablePageLink<Void> importTaxLink = new BookmarkablePageLink<Void>(
                "import-company-tax-link", ImportTaxDataPage.class);
        tax.add(importTaxLink);
        tax.add(viewTaxLink);
        MetaDataRoleAuthorizationStrategy.authorize(viewTaxLink, RENDER,
                Roles.TAX);
        MetaDataRoleAuthorizationStrategy.authorize(importTaxLink, RENDER,
                Roles.TAX_IMPORT);

        WebMarkupContainer security = new WebMarkupContainer("company-security");
        security.add(new BookmarkablePageLink<Void>("company-security-link",
                LandingPage.class, new PageParameters().add("module",
                "SECURITY")));
        BookmarkablePageLink<Void> searchSecurityLink = new BookmarkablePageLink<Void>(
                "search-company-security-link", SecurityListPage.class);
        BookmarkablePageLink<Void> upsertSecurityLink = new BookmarkablePageLink<Void>(
                "create-company-security-link", UpsertSecurityPage.class);
        security.add(searchSecurityLink);
        security.add(upsertSecurityLink);
        MetaDataRoleAuthorizationStrategy.authorize(searchSecurityLink, RENDER,
                Roles.SECURITY);
        MetaDataRoleAuthorizationStrategy.authorize(upsertSecurityLink, RENDER,
                Roles.SECURITY_CHANGE);

        WebMarkupContainer report = new WebMarkupContainer("report");
        report.add(new BookmarkablePageLink<Void>("company-report-link",
                LandingPage.class, new PageParameters().add("module", "REPORT")));
        BookmarkablePageLink<Void> taxReportLink = new BookmarkablePageLink<Void>(
                "financial-report", CompanyTaxMonthlyReportPage.class);
        BookmarkablePageLink<Void> overallReportLink = new BookmarkablePageLink<Void>(
                "overall-report", OverallReportPage.class);
        report.add(taxReportLink);
        report.add(overallReportLink);
        MetaDataRoleAuthorizationStrategy.authorize(taxReportLink, RENDER,
                Roles.RM_BKX);
        MetaDataRoleAuthorizationStrategy.authorize(overallReportLink, RENDER,
                Roles.RM_OVERALL);

        WebMarkupContainer system = new WebMarkupContainer("system-management");
        system.add(new BookmarkablePageLink<Void>("company-system-link",
                LandingPage.class, new PageParameters().add("module", "SYSTEM")));
        system.add(new BookmarkablePageLink<Void>("system-user-link",
                UserManagePage.class));
        system.add(new BookmarkablePageLink<Void>("system-role-link",
                RoleManagePage.class));

        WebMarkupContainer infoIssue = new WebMarkupContainer(
                "information-issue");
        infoIssue.add(new BookmarkablePageLink<Void>("company-info-issue-link",
                LandingPage.class, new PageParameters().add("module",
                "INFOISSUE")));
        infoIssue.add(new BookmarkablePageLink<Void>("send-sms-link",
                SMSPage.class));
        infoIssue.add(new BookmarkablePageLink<Void>("send-email-link",
                EmailPage.class));

        WebMarkupContainer project = new WebMarkupContainer("project");
        project.add(new BookmarkablePageLink<Void>("company-project-link",
                LandingPage.class, new PageParameters()
                .add("module", "PROJECT")));
        BookmarkablePageLink<Void> searchProjectLink = new BookmarkablePageLink<Void>(
                "search-project-link", ProjectListPage.class);
        BookmarkablePageLink<Void> upsertProjectLink = new BookmarkablePageLink<Void>(
                "create-project-link", UpsertProjectInfoPage.class);
        project.add(searchProjectLink);
        project.add(upsertProjectLink);
        MetaDataRoleAuthorizationStrategy.authorize(searchProjectLink, RENDER,
                Roles.PM);
        MetaDataRoleAuthorizationStrategy.authorize(upsertProjectLink, RENDER,
                Roles.PM_CHANGE);

        WebMarkupContainer agri = new WebMarkupContainer("agriculutre");
        agri.add(new BookmarkablePageLink<Void>("company-agri-link",
                LandingPage.class, new PageParameters().add("module", "AGRI")));
        BookmarkablePageLink<Void> searchAgriLink = new BookmarkablePageLink<Void>(
                "search-agri-link", AgricultureListPage.class);
        BookmarkablePageLink<Void> upsertAgriLink = new BookmarkablePageLink<Void>(
                "create-agri-link", UpsertAgriculturePage.class);
        agri.add(searchAgriLink);
        agri.add(upsertAgriLink);
        MetaDataRoleAuthorizationStrategy.authorize(searchAgriLink, RENDER,
                Roles.AGRI);
        MetaDataRoleAuthorizationStrategy.authorize(upsertAgriLink, RENDER,
                Roles.AGRI_CHANGE);

        WebMarkupContainer area = new WebMarkupContainer("commercial-area");
        area.add(new BookmarkablePageLink<Void>("company-area-link",
                LandingPage.class, new PageParameters().add("module", "AREA")));
        BookmarkablePageLink<Void> searchCALink = new BookmarkablePageLink<Void>(
                "search-commercial-area-link", CommercialAreaListPage.class);
        BookmarkablePageLink<Void> upsertCALink = new BookmarkablePageLink<Void>(
                "create-commercial-area-link", UpsertCommercialAreaPage.class);
        area.add(searchCALink);
        area.add(upsertCALink);
        MetaDataRoleAuthorizationStrategy.authorize(searchCALink, RENDER,
                Roles.CA);
        MetaDataRoleAuthorizationStrategy.authorize(upsertCALink, RENDER,
                Roles.CA_CHANGE);

        add(cm);
        MetaDataRoleAuthorizationStrategy.authorize(cm, RENDER, Roles.CM);
        add(tax);
        MetaDataRoleAuthorizationStrategy.authorize(tax, RENDER, Roles.TAX);
        add(security);
        MetaDataRoleAuthorizationStrategy.authorize(security, RENDER,
                Roles.SECURITY);
        add(report);
        MetaDataRoleAuthorizationStrategy.authorize(report, RENDER, Roles.RM);
        add(system);
        MetaDataRoleAuthorizationStrategy.authorize(system, RENDER,
                Roles.SYSTEM);
        add(infoIssue);
        MetaDataRoleAuthorizationStrategy.authorize(infoIssue, RENDER,
                Roles.INFO);
        add(project);
        MetaDataRoleAuthorizationStrategy.authorize(project, RENDER, Roles.PM);
        add(agri);
        MetaDataRoleAuthorizationStrategy.authorize(agri, RENDER, Roles.AGRI);
        add(area);
        MetaDataRoleAuthorizationStrategy.authorize(area, RENDER, Roles.CA);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
    }

}
