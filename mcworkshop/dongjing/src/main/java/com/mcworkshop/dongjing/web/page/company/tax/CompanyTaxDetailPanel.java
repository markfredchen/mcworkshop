// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.tax;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;

import com.mcworkshop.dongjing.domain.TaxData;

/**
 * @author $Author$
 * 
 */
public class CompanyTaxDetailPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public CompanyTaxDetailPanel(String id,
			final CompoundPropertyModel<TaxData> data) {
		super(id, data);
		add(new Label("month", new AbstractReadOnlyModel<String>() {

			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				TaxData data = (TaxData) CompanyTaxDetailPanel.this
						.getDefaultModelObject();
				if (data.getYear() == 0) {
					return "";
				} else {
					return data.getYear() + "年" + data.getMonth() + "月";
				}
			}
		}));
		add(new Label("company.name"));
		add(new Label("industry"));
		add(new Label("accSalesForDisplay"));
		add(new Label("salesForDisplay"));
		add(new Label("vatForDisplay"));
		add(new Label("operateTaxForDisplay"));
		add(new Label("expenseTaxForDisplay"));
		add(new Label("domesticIncomingTaxForDisplay"));
		add(new Label("foreignIncomingTaxForDisplay"));
		add(new Label("housingTaxForDisplay"));
		add(new Label("stampTaxForDisplay"));
		add(new Label("trafficTaxForDisplay"));
		add(new Label("landVATForDisplay"));
		add(new Label("landUseTaxForDisplay"));
		add(new Label("personalIncomingTaxForDisplay"));
		add(new Label("constructionTaxForDisplay"));
		add(new Label("veichleTaxForDisplay"));
		add(new Label("riverTaxForDisplay"));
		add(new Label("educationTaxForDisplay"));
		add(new Label("cultureTaxForDisplay"));
		add(new Label("otherTaxForDisplay"));
	}

}
