// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web;

import com.mcworkshop.common.web.BaseRequestCycleListener;
import com.mcworkshop.dongjing.web.page.error.ErrorPage;
import com.mcworkshop.dongjing.web.page.home.HomePage;
import org.apache.wicket.Page;

/**
 * @author $Author$
 * 
 */
public class DJRequestCyclerListener extends BaseRequestCycleListener {

	@Override
	protected Class<? extends Page> getAccessDenyPage() {
		return HomePage.class;
	}

	@Override
	protected Class<? extends Page> getErrorPage() {
		return ErrorPage.class;
	}

}
