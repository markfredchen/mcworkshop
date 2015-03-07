// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web;

import com.mcworkshop.common.configuration.Config;
import com.mcworkshop.common.model.ServiceModel;
import com.mcworkshop.common.security.BaseSecurityServiceImpl;
import com.mcworkshop.common.security.Rights;
import com.mcworkshop.common.service.CommonService;
import com.mcworkshop.common.service.CommonServiceImpl;
import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.web.BaseWebApplication;
import com.mcworkshop.dongjing.authorization.RoleChecker;
import com.mcworkshop.dongjing.configuration.DJConfigurationKey;
import com.mcworkshop.dongjing.persistence.*;
import com.mcworkshop.dongjing.service.*;
import com.mcworkshop.dongjing.service.email.MailManager;
import com.mcworkshop.dongjing.web.page.building.BuildingPage;
import com.mcworkshop.dongjing.web.page.company.basic.CompanyListPage;
import com.mcworkshop.dongjing.web.page.company.basic.UpsertComanyPage;
import com.mcworkshop.dongjing.web.page.error.ErrorPage;
import com.mcworkshop.dongjing.web.page.home.HomePage;
import com.mcworkshop.dongjing.web.page.landing.LandingPage;
import com.mcworkshop.dongjing.web.page.landing.WelcomePage;
import com.mcworkshop.dongjing.web.page.report.CompanyTaxMonthlyReportPage;
import com.mcworkshop.dongjing.web.page.report.preview.SalesTaxReportServicePage;
import com.mcworkshop.dongjing.web.page.report.preview.SalesTaxReportTemplatePage;
import com.mcworkshop.dongjing.web.page.system.role.RoleManagePage;
import com.mcworkshop.dongjing.web.page.system.user.UserManagePage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authorization.strategies.role.RoleAuthorizationStrategy;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.settings.IExceptionSettings;
import org.mybatis.guice.MyBatisModule;

/**
 * @author $Author$
 * 
 */
public class DJApplication extends BaseWebApplication {

	private static final Log log = LogFactory.getLog(DJApplication.class);

	@Override
	protected void init() {
		super.init();
		initRights();
		getExceptionSettings().setUnexpectedExceptionDisplay(
				IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
		getApplicationSettings().setPageExpiredErrorPage(getHomePage());
		getApplicationSettings().setInternalErrorPage(getHomePage());
	}

	@Override
	protected MyBatisModule getServiceModel() {
		return new ServiceModel() {

			@SuppressWarnings("unchecked")
			@Override
			protected void customizedInitialize() {
				this.addMapperClass(DJSecurityMapper.class);
				this.addMapperClass(DJServiceMapper.class);
				this.addMapperClass(SecurityMapper.class);
				this.addMapperClass(RegionTaxMapper.class);
				this.addMapperClass(TotalTaxDataMapper.class);
				this.addMapperClass(TaxOrgReportMapper.class);
				this.addMapperClass(KPIReportMapper.class);
				this.addMapperClass(EconomyEntityTaxMapper.class);
				this.addMapperClass(CommercialAreaMapper.class);
				this.bind(SecurityService.class).to(SecurityServiceImpl.class);
				this.bind(DJService.class).to(DJServiceImpl.class);
				this.bind(ReportService.class).to(ReportServiceImpl.class);
				try {
					this.bind(MailManager.class)
							.to((Class<? extends MailManager>) Class.forName(Config
									.getConfig(DJConfigurationKey.EMAIL_MANAGER)));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}

		};
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	protected String getProjectName() {
		return "dongjing";
	}

	@Override
	protected void initWebComponent() {
		super.initWebComponent();
		getRequestCycleListeners().add(new DJRequestCyclerListener());
		getSecuritySettings().setAuthorizationStrategy(
				new RoleAuthorizationStrategy(new RoleChecker()));
	}

	@Override
	protected void mountPages() {
		this.mountPage("error", ErrorPage.class);
		this.mountPage("landing", LandingPage.class);
		this.mountPage("company/basic/list", CompanyListPage.class);
		this.mountPage("company/basic/upsert", UpsertComanyPage.class);
		this.mountPage("system/user", UserManagePage.class);
		this.mountPage("system/role", RoleManagePage.class);
		this.mountPage("report/sales", CompanyTaxMonthlyReportPage.class);
		this.mountPage("sales/tax/report/template",
				SalesTaxReportTemplatePage.class);
		this.mountPage("sales/tax/report/service",
				SalesTaxReportServicePage.class);
		this.mountPage("building", BuildingPage.class);
		this.mountPage("welcome", WelcomePage.class);
	}

	@Override
	protected void initSystemEnumeration() {
		CommonService service = new CommonServiceImpl();
		this.injector.inject(service);
		try {
			SystemEnumeration.initSystemEnumeration(service
					.getAllSystemEnumeration());
		} catch (Throwable e) {
			throw new RuntimeException("init SystemEnumeration fail", e);
		}
	}

	protected void initRights() {
		com.mcworkshop.common.security.SecurityService service = new BaseSecurityServiceImpl();
		this.injector.inject(service);
		try {
			Rights.initRights(service.getAllRights());
		} catch (Throwable e) {
			throw new RuntimeException("init Rights fail");
		}
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new DJWebSession(request);
	}

}
