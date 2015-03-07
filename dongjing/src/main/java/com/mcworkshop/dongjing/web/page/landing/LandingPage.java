// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.landing;

import com.mcworkshop.dongjing.authorization.Roles;
import com.mcworkshop.dongjing.web.comercial.area.CommercialAreaListPage;
import com.mcworkshop.dongjing.web.comercial.area.UpsertCommercialAreaPage;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import com.mcworkshop.dongjing.web.page.agriculture.AgricultureListPage;
import com.mcworkshop.dongjing.web.page.agriculture.UpsertAgriculturePage;
import com.mcworkshop.dongjing.web.page.company.basic.CompanyListPage;
import com.mcworkshop.dongjing.web.page.company.basic.ImportCompanyPage;
import com.mcworkshop.dongjing.web.page.company.basic.UpsertComanyPage;
import com.mcworkshop.dongjing.web.page.company.security.SecurityListPage;
import com.mcworkshop.dongjing.web.page.company.security.UpsertSecurityPage;
import com.mcworkshop.dongjing.web.page.company.tax.CompanyTaxDetailPage;
import com.mcworkshop.dongjing.web.page.company.tax.ImportTaxDataPage;
import com.mcworkshop.dongjing.web.page.news.EmailPage;
import com.mcworkshop.dongjing.web.page.news.SMSPage;
import com.mcworkshop.dongjing.web.page.project.ProjectListPage;
import com.mcworkshop.dongjing.web.page.project.UpsertProjectInfoPage;
import com.mcworkshop.dongjing.web.page.report.CompanyTaxMonthlyReportPage;
import com.mcworkshop.dongjing.web.page.report.OverallReportPage;
import com.mcworkshop.dongjing.web.page.system.role.RoleManagePage;
import com.mcworkshop.dongjing.web.page.system.user.UserManagePage;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author $Author$
 * 
 */
@AuthorizeInstantiation({ Roles.USER })
public class LandingPage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	public LandingPage(PageParameters params) {
		super(params);
		String module = params.get("module").toString();
		if (module == null) {
			setResponsePage(WelcomePage.class);
		}

		WebMarkupContainer cm = new WebMarkupContainer("company-basic");
		WebMarkupContainer cm_change = new WebMarkupContainer("cm_change");
		WebMarkupContainer cm_view = new WebMarkupContainer("cm_view");
		WebMarkupContainer cm_import = new WebMarkupContainer("cm_import");
		cm.add(cm_change);
		cm.add(cm_view);
		cm.add(cm_import);
		cm_view.add(new BookmarkablePageLink<Void>("search-company-basic-link",
				CompanyListPage.class));
		cm_change.add(new BookmarkablePageLink<Void>(
				"create-company-basic-link", UpsertComanyPage.class));
		cm_import.add(new BookmarkablePageLink<Void>(
				"import-company-basic-link", ImportCompanyPage.class));
		cm_view.add(new BookmarkablePageLink<Void>(
				"search-company-basic-link-img", CompanyListPage.class));
		cm_change.add(new BookmarkablePageLink<Void>(
				"create-company-basic-link-img", UpsertComanyPage.class));
		cm_import.add(new BookmarkablePageLink<Void>(
				"import-company-basic-link-img", ImportCompanyPage.class));
		MetaDataRoleAuthorizationStrategy.authorize(cm_view, RENDER, Roles.CM);
		MetaDataRoleAuthorizationStrategy.authorize(cm_change, RENDER,
				Roles.CM_CHANGE);
		MetaDataRoleAuthorizationStrategy.authorize(cm_import, RENDER,
				Roles.CM_IMPORT);

		WebMarkupContainer tax = new WebMarkupContainer("company-tax");
		WebMarkupContainer tax_view = new WebMarkupContainer("tax_view");
		WebMarkupContainer tax_import = new WebMarkupContainer("tax_import");
		tax.add(tax_view);
		tax.add(tax_import);
		tax_view.add(new BookmarkablePageLink<Void>("search-company-tax-link",
				CompanyTaxDetailPage.class));
		tax_import.add(new BookmarkablePageLink<Void>(
				"import-company-tax-link", ImportTaxDataPage.class));
		tax_view.add(new BookmarkablePageLink<Void>(
				"search-company-tax-link-img", CompanyTaxDetailPage.class));
		tax_import.add(new BookmarkablePageLink<Void>(
				"import-company-tax-link-img", ImportTaxDataPage.class));

		MetaDataRoleAuthorizationStrategy
				.authorize(tax_view, RENDER, Roles.TAX);
		MetaDataRoleAuthorizationStrategy.authorize(tax_import, RENDER,
				Roles.TAX_IMPORT);

		WebMarkupContainer security = new WebMarkupContainer("company-security");
		WebMarkupContainer sm_view = new WebMarkupContainer("sm_view");
		WebMarkupContainer sm_change = new WebMarkupContainer("sm_change");
		security.add(sm_view);
		security.add(sm_change);

		sm_view.add(new BookmarkablePageLink<Void>(
				"search-company-security-link", SecurityListPage.class));
		sm_change.add(new BookmarkablePageLink<Void>(
				"create-company-security-link", UpsertSecurityPage.class));
		sm_view.add(new BookmarkablePageLink<Void>(
				"search-company-security-link-img", SecurityListPage.class));
		sm_change.add(new BookmarkablePageLink<Void>(
				"create-company-security-link-img", UpsertSecurityPage.class));

		MetaDataRoleAuthorizationStrategy.authorize(sm_view, RENDER,
				Roles.SECURITY);
		MetaDataRoleAuthorizationStrategy.authorize(sm_change, RENDER,
				Roles.SECURITY_CHANGE);

		WebMarkupContainer agri = new WebMarkupContainer("agriculture");
		WebMarkupContainer agri_view = new WebMarkupContainer("agri-view");
		WebMarkupContainer agri_change = new WebMarkupContainer("agri-change");
		agri.add(agri_view);
		agri.add(agri_change);
		agri_view.add(new BookmarkablePageLink<Void>("search-agri-link",
				AgricultureListPage.class));
		agri_change.add(new BookmarkablePageLink<Void>("create-agri-link",
				UpsertAgriculturePage.class));
		agri_view.add(new BookmarkablePageLink<Void>("search-agri-link-img",
				AgricultureListPage.class));
		agri_change.add(new BookmarkablePageLink<Void>("create-agri-link-img",
				UpsertAgriculturePage.class));
		MetaDataRoleAuthorizationStrategy.authorize(agri_view, RENDER,
				Roles.AGRI);
		MetaDataRoleAuthorizationStrategy.authorize(agri_change, RENDER,
				Roles.AGRI_CHANGE);

		WebMarkupContainer report = new WebMarkupContainer("report");

		report.add(new BookmarkablePageLink<Void>("financial-report",
				CompanyTaxMonthlyReportPage.class));
		report.add(new BookmarkablePageLink<Void>("overall-report",
				OverallReportPage.class));
		report.add(new BookmarkablePageLink<Void>("financial-report-img",
				CompanyTaxMonthlyReportPage.class));
		report.add(new BookmarkablePageLink<Void>("overall-report-img",
				OverallReportPage.class));

		WebMarkupContainer system = new WebMarkupContainer("system-management");
		system.add(new BookmarkablePageLink<Void>("system-user",
				UserManagePage.class));
		system.add(new BookmarkablePageLink<Void>("system-role",
				RoleManagePage.class));
		system.add(new BookmarkablePageLink<Void>("system-user-img",
				UserManagePage.class));
		system.add(new BookmarkablePageLink<Void>("system-role-img",
				RoleManagePage.class));

		WebMarkupContainer infoIssue = new WebMarkupContainer(
				"information-issue");
		infoIssue.add(new BookmarkablePageLink<Void>("send-sms-link",
				SMSPage.class));
		infoIssue.add(new BookmarkablePageLink<Void>("send-email-link",
				EmailPage.class));
		infoIssue.add(new BookmarkablePageLink<Void>("send-sms-link-img",
				SMSPage.class));
		infoIssue.add(new BookmarkablePageLink<Void>("send-email-link-img",
				EmailPage.class));

		// TODO: need to add acl
		WebMarkupContainer project = new WebMarkupContainer(
				"project-management");
		project.add(new BookmarkablePageLink<Void>("project-info",
				ProjectListPage.class));
		project.add(new BookmarkablePageLink<Void>("project-status",
				UpsertProjectInfoPage.class));
		project.add(new BookmarkablePageLink<Void>("project-info-img",
				ProjectListPage.class));
		project.add(new BookmarkablePageLink<Void>("project-status-img",
				UpsertProjectInfoPage.class));

		WebMarkupContainer area = new WebMarkupContainer("area");
		WebMarkupContainer area_view = new WebMarkupContainer("area-view");
		WebMarkupContainer area_change = new WebMarkupContainer("area-change");
		area.add(area_view);
		area.add(area_change);
		area_view.add(new BookmarkablePageLink<Void>("search-area-link",
				CommercialAreaListPage.class));
		area_change.add(new BookmarkablePageLink<Void>("create-area-link",
				UpsertCommercialAreaPage.class));
		area_view.add(new BookmarkablePageLink<Void>("search-area-link-img",
				CommercialAreaListPage.class));
		area_change.add(new BookmarkablePageLink<Void>("create-area-link-img",
				UpsertCommercialAreaPage.class));
		MetaDataRoleAuthorizationStrategy.authorize(area_view, RENDER,
				Roles.CA_VIEW);
		MetaDataRoleAuthorizationStrategy.authorize(area_change, RENDER,
				Roles.CA_CHANGE);

		add(cm);
		add(tax);
		add(security);
		add(report);
		add(system);
		add(infoIssue);
		add(project);
		add(agri);
		add(area);
		cm.setVisible(module.toUpperCase().contains("CM"));
		tax.setVisible(module.toUpperCase().contains("TAX"));
		security.setVisible(module.toUpperCase().contains("SECURITY"));
		report.setVisible(module.toUpperCase().contains("REPORT"));
		system.setVisible(module.toUpperCase().contains("SYSTEM"));
		infoIssue.setVisible(module.toUpperCase().contains("INFOISSUE"));
		project.setVisible(module.toUpperCase().contains("PROJECT"));
		agri.setVisible(module.toUpperCase().contains("AGRI"));
		area.setVisible(module.toUpperCase().contains("AREA"));
	}

	@Override
	protected String getPageClassName() {
		return "landing";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return new ResourceModel("dj.landing.title");
	}

	@Override
	protected Panel getLeftNaviPanel(String id) {
		return new EmptyPanel(id);
	}

}
