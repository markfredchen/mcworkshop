// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.admin.page;

import com.wutai.tradingeq.constant.TradingEQConstants;
import com.wutai.tradingeq.web.admin.importform.ImportTMBoxPanel;

/**
 * @author $Author$
 *
 */
public class ImportStrategyPage extends AdminBasePage {

    private static final long serialVersionUID = 1L;
    
    public ImportStrategyPage() {
        add(new ImportTMBoxPanel("import-strategy", TradingEQConstants.STRATEGY_DATA_FILE, TradingEQConstants.STRATEGY_DATA_INIT_BALANCE_FILE, "策略实验室数据导入"));
    }

    @Override
    protected String getActiveSlideBarID() {
        return "nav-import-strategy";
    }

}
