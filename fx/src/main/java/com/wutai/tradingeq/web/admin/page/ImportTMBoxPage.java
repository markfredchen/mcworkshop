// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.admin.page;

import com.wutai.tradingeq.constant.TradingEQConstants;
import com.wutai.tradingeq.web.admin.importform.ImportTMBoxPanel;

/**
 * @author $Author$
 *
 */
public class ImportTMBoxPage extends AdminBasePage {

    private static final long serialVersionUID = 1L;

    public ImportTMBoxPage() {
        add(new ImportTMBoxPanel("import-tmbox", TradingEQConstants.TMBOX_DATA_FILE, TradingEQConstants.TMBOX_DATA_INIT_BALANCE_FILE, "TMBox外汇实盘数据导入"));
    }
    
    @Override
    protected String getActiveSlideBarID() {
        return "nav-import-tmbox";
    }

}
