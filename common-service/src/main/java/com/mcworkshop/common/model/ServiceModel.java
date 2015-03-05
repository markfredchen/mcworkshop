// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.model;

import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;

import com.google.inject.name.Names;
import com.mcworkshop.common.configuration.Config;
import com.mcworkshop.common.persistence.SecurityMapper;
import com.mcworkshop.common.persistence.SystemEnumerationMapper;
import com.mcworkshop.common.service.CommonService;
import com.mcworkshop.common.service.CommonServiceImpl;

/**
 * @author $Author$
 * 
 */
public class ServiceModel extends MyBatisModule {

    @Override
    protected void initialize() {
        this.bindDataSourceProviderType(PooledDataSourceProvider.class);
        this.bindTransactionFactoryType(JdbcTransactionFactory.class);

        Names.bindProperties(this.binder(), Config.getConfiguration());
        this.addMapperClass(SecurityMapper.class);
        this.addMapperClass(SystemEnumerationMapper.class);
        this.bind(CommonService.class).to(CommonServiceImpl.class);
        customizedInitialize();
    }

    protected void customizedInitialize() {
    }

}
