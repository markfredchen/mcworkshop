// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.constant;

import com.mcworkshop.common.configuration.ConfigurationKey;

/**
 * @author $Author$
 *
 */
public enum FXConfigurationKey implements ConfigurationKey {
    IMPORT_DATA_FILE_PATH("import.data.file.path");
    
    private String key;
    
    private FXConfigurationKey(String key) {
        this.key = key;
    }

    @Override
    public String value() {
        return this.key;
    }

}
