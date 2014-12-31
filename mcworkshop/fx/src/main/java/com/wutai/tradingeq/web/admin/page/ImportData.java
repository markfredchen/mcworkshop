// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.admin.page;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.wutai.tradingeq.constant.TradingEQConstants;
import com.wutai.tradingeq.web.admin.importform.ImportFuturesPanel;
import com.wutai.tradingeq.web.admin.importform.ImportTMBoxPanel;


/**
 * @author $Author$
 *
 */
public class ImportData extends AdminBasePage {
    private static final long serialVersionUID = 1L;
    
    public ImportData(PageParameters params) {
        add(new ImportTMBoxPanel("import-tmbox", TradingEQConstants.TMBOX_DATA_FILE, TradingEQConstants.TMBOX_DATA_INIT_BALANCE_FILE, "TMBox外汇实盘数据导入"));
        add(new ImportFuturesPanel("import-future"));
        add(new ImportTMBoxPanel("import-strategy", TradingEQConstants.STRATEGY_DATA_FILE, TradingEQConstants.STRATEGY_DATA_INIT_BALANCE_FILE, "策略实验室数据导入"));
    }

    @Override
    protected String getActiveSlideBarID() {
        return "nav-import";
    }


}
