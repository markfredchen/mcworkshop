// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.admin.page;

import com.wutai.tradingeq.web.admin.importform.ImportFuturesPanel;

/**
 * @author $Author$
 *
 */
public class ImportFuturePage extends AdminBasePage {

    private static final long serialVersionUID = 1L;
    
    public ImportFuturePage() {
        add(new ImportFuturesPanel("import-future"));
    }

    @Override
    protected String getActiveSlideBarID() {
        return "nav-import-future";
    }

}
