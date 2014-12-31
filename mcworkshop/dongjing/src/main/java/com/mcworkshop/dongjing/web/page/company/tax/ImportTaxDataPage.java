// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.tax;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import com.mcworkshop.dongjing.web.page.report.form.ImportSaleTaxForm;

/**
 * @author $Author$
 * 
 */
public class ImportTaxDataPage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	public ImportTaxDataPage(PageParameters params) {
		super(params);
		add(new ImportSaleTaxForm("import-data-form"));
	}

	@Override
	protected Panel getLeftNaviPanel(String id) {
		return new TaxLeftNavigationPanel(id);
	}

	@Override
	protected String getPageClassName() {
		return "company-tax";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return WicketMessageUtil.getResourceModel("dj.menu.tax.management");
	}

}
