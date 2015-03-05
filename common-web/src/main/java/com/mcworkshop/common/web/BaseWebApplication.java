// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: BaseWebApplication.java 54 2014-03-30 16:03:34Z mchen $
package com.mcworkshop.common.web;

import org.apache.wicket.Application;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.IRequestCycleSettings.RenderStrategy;
import org.mybatis.guice.MyBatisModule;

import com.mcworkshop.common.configuration.Config;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public abstract class BaseWebApplication extends WebApplication {

    protected GuiceComponentInjector injector;

    @Override
    protected void init() {
        Config.init(getProjectName());
        initServiceModel();
        initWebComponent();
        mountPages();
        initSystemEnumeration();

        super.init();
    }

    private void initServiceModel() {
        getComponentInstantiationListeners().add(
                injector = new GuiceComponentInjector(this, getServiceModel()));
    }

    protected void initWebComponent() {
        getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
        getRequestCycleSettings().setRenderStrategy(
                RenderStrategy.ONE_PASS_RENDER);
    }

    abstract protected String getProjectName();

    abstract protected MyBatisModule getServiceModel();

    abstract protected void mountPages();

    protected void initSystemEnumeration() {
    }

    public String getStaticPath() {
        return "/" + getProjectName() + "/static";
    }

    public static BaseWebApplication get() {
        return (BaseWebApplication) Application.get();
    }

}
