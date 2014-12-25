// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.admin.importform;

import java.io.FileInputStream;

import org.apache.wicket.util.file.File;

import com.mcworkshop.common.configuration.Config;
import com.wutai.tradingeq.constant.FXConfigurationKey;
import com.wutai.tradingeq.constant.TradingEQConstants;
import com.wutai.tradingeq.web.FXApplication;

/**
 * @author $Author$
 *
 */
public class ImportStrategyPanel extends ImportTMBoxPanel {
    

    public ImportStrategyPanel(String id, String dataFile, String initDataFile, String title) {
        super(id, dataFile, initDataFile, title);
    }
    
    @Override
    public void populateData() {
        try {
            FXApplication.get().setTmboxRealData(service
                    .loadTMBoxRealDataReport(
                            new FileInputStream(
                                    new File(
                                            Config.getConfig(FXConfigurationKey.IMPORT_DATA_FILE_PATH)
                                                    + dataFile)),
                            new FileInputStream(
                                    new File(
                                            Config.getConfig(FXConfigurationKey.IMPORT_DATA_FILE_PATH)
                                                    + initDataFile))));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
