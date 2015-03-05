// Copyright 2013 Active Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AnnotationsRoleAuthorizationStrategy;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.settings.IRequestCycleSettings.RenderStrategy;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;

import com.google.inject.name.Names;
import com.mcworkshop.common.configuration.Config;
import com.wutai.tradingeq.constant.FXConfigurationKey;
import com.wutai.tradingeq.constant.TradingEQConstants;
import com.wutai.tradingeq.dao.ContactMapper;
import com.wutai.tradingeq.dao.OIDTypeHandler;
import com.wutai.tradingeq.domain.OID;
import com.wutai.tradingeq.model.FutureRealDataReport;
import com.wutai.tradingeq.model.StrategyLabDataReport;
import com.wutai.tradingeq.model.TMBoxRealDataReport;
import com.wutai.tradingeq.service.ServiceManager;
import com.wutai.tradingeq.service.ServiceManagerImpl;
import com.wutai.tradingeq.web.admin.page.ImportData;
import com.wutai.tradingeq.web.admin.page.ImportTMBoxPage;
import com.wutai.tradingeq.web.authorization.RoleChecker;
import com.wutai.tradingeq.web.page.HomePage;
import com.wutai.tradingeq.web.page.api.services.APIServicePage;
import com.wutai.tradingeq.web.page.contact.us.ContactUsPage;
import com.wutai.tradingeq.web.page.ea.advance.EAAdvancePage;
import com.wutai.tradingeq.web.page.fmi.FMIPage;
import com.wutai.tradingeq.web.page.future.cta.FutureCTAPage;
import com.wutai.tradingeq.web.page.future.cta.FutureRealDataReportPage;
import com.wutai.tradingeq.web.page.tmbox.TMBoxPage;
import com.wutai.tradingeq.web.page.tmbox.TMBoxRealDataReportPage;

/**
 * @author mchen1
 * @versin $Rev$, $Date$
 * @since 1.0
 */
public class FXApplication extends WebApplication {

    private static final String      CONFIG_NAME = "fx";

    protected GuiceComponentInjector injector;

    private TMBoxRealDataReport      tmboxRealData;
    private FutureRealDataReport     futureRealData;
    private TMBoxRealDataReport      strategyLabData;

    @Override
    protected void init() {
        Config.init(CONFIG_NAME);

        getComponentInstantiationListeners().add(
                injector = new GuiceComponentInjector(this,
                        new MyBatisModule() {
                            @Override
                            protected void initialize() {
                                bindDataSourceProviderType(PooledDataSourceProvider.class);
                                bindTransactionFactoryType(JdbcTransactionFactory.class);

                                Names.bindProperties(this.binder(),
                                        Config.getConfiguration());
                                // configure type handler
                                handleType(OID.class)
                                        .with(OIDTypeHandler.class);
                                // add data mappers
                                addMapperClass(ContactMapper.class);

                                // bind services
                                bind(ServiceManager.class).to(
                                        ServiceManagerImpl.class);
                            }
                        }));
        getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
        getRequestCycleListeners().add(new FXRequestCycleListener());
        getRequestCycleSettings().setRenderStrategy(
                RenderStrategy.ONE_PASS_RENDER);
        getSecuritySettings().setAuthorizationStrategy(
                new AnnotationsRoleAuthorizationStrategy(new RoleChecker()));
        mountPage("contact-us", ContactUsPage.class);
        mountPage("tmbox", TMBoxPage.class);
        mountPage("tmbox/report", TMBoxRealDataReportPage.class);
        mountPage("api-service", APIServicePage.class);
        mountPage("cta", FutureCTAPage.class);
        mountPage("future/report", FutureRealDataReportPage.class);
        mountPage("fmi", FMIPage.class);
        mountPage("ea", EAAdvancePage.class);
        mountPage("admin/import", ImportTMBoxPage.class);
        loadTMBoxReportData();
        super.init();
    }

    private void loadTMBoxReportData() {
        ServiceManager service = new ServiceManagerImpl();
        this.injector.inject(service);
        try {
            tmboxRealData = service
                    .loadTMBoxRealDataReport(
                            new FileInputStream(
                                    new File(
                                            Config.getConfig(FXConfigurationKey.IMPORT_DATA_FILE_PATH)
                                                    + TradingEQConstants.TMBOX_DATA_FILE)),
                            new FileInputStream(
                                    new File(
                                            Config.getConfig(FXConfigurationKey.IMPORT_DATA_FILE_PATH)
                                                    + TradingEQConstants.TMBOX_DATA_INIT_BALANCE_FILE)));
            futureRealData = service.loadFutureRealDataReport(
                            new FileInputStream(
                                    new File(
                                            Config.getConfig(FXConfigurationKey.IMPORT_DATA_FILE_PATH)
                                                    + TradingEQConstants.FUTURE_REAL_DATA_FILE)),
                            new FileInputStream(
                                    new File(
                                            Config.getConfig(FXConfigurationKey.IMPORT_DATA_FILE_PATH)
                                                    + TradingEQConstants.FUTURE_OVERALL_DATA_FILE)));
            strategyLabData = service
                    .loadTMBoxRealDataReport(
                            new FileInputStream(
                                    new File(
                                            Config.getConfig(FXConfigurationKey.IMPORT_DATA_FILE_PATH)
                                                    + TradingEQConstants.STRATEGY_DATA_FILE)),
                            new FileInputStream(
                                    new File(
                                            Config.getConfig(FXConfigurationKey.IMPORT_DATA_FILE_PATH)
                                                    + TradingEQConstants.STRATEGY_DATA_INIT_BALANCE_FILE)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Session newSession(Request request, Response response) {
        return new FXWebSession(request);
    }

    public static FXApplication get() {
        return (FXApplication) WebApplication.get();
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    public TMBoxRealDataReport getTmboxRealData() {
        return tmboxRealData;
    }

    public void setTmboxRealData(TMBoxRealDataReport tmboxRealData) {
        this.tmboxRealData = tmboxRealData;
    }

    public FutureRealDataReport getFutureRealData() {
        return futureRealData;
    }

    public void setFutureRealData(FutureRealDataReport futureRealData) {
        this.futureRealData = futureRealData;
    }

    public TMBoxRealDataReport getStrategyLabData() {
        return strategyLabData;
    }

    public void setStrategyLabData(TMBoxRealDataReport strategyLabData) {
        this.strategyLabData = strategyLabData;
    }
}
