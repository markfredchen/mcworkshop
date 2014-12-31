// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: Config.java 22 2014-01-30 16:19:27Z mchen $
package com.mcworkshop.common.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class Config {
    private static Properties props = new Properties();

    private Config() {
    }

    public static void init(String propertyFileName) {
        InputStream in = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(propertyFileName + ".properties");
        try {
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("");
        }
    }

    public static Properties getConfiguration() {
        return props;
    }

    public static String getConfig(ConfigurationKey key) {
        return props.getProperty(key.value());
    }

}