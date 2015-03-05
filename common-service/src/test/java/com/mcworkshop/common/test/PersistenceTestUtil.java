// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;

import com.google.inject.Injector;

/**
 * @author $Author$
 * 
 */
public class PersistenceTestUtil {

    public static void initDB(Injector injector, List<File> scripts)
            throws SQLException, FileNotFoundException {
        Environment environment = injector.getInstance(SqlSessionFactory.class)
                .getConfiguration().getEnvironment();
        DataSource dataSource = environment.getDataSource();
        ScriptRunner runner = new ScriptRunner(dataSource.getConnection());
        for (File script : scripts) {
            runner.runScript(new FileReader(script));
        }
    }
}
