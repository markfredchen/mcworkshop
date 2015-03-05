// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.report.preview;

import java.util.List;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.inject.Inject;
import com.mcworkshop.common.util.NumberUtil;
import com.mcworkshop.common.web.service.page.JSONServicePage;
import com.mcworkshop.common.web.util.WebUtil;
import com.mcworkshop.dongjing.domain.TaxData;
import com.mcworkshop.dongjing.service.DJService;

/**
 * @author $Author$
 * 
 */
public class SalesTaxReportServicePage extends JSONServicePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJService service;

	// private static final String MSG_KEY_PREFIX =
	// CompanyTaxMonthlyReport.class.getSimpleName() + ".";

	public SalesTaxReportServicePage(PageParameters params) {
		super(params);
	}

	@Override
	public JSONObject getResponseBody(PageParameters params) {
		int year = WebUtil.getParameterAsInteger("year");
		int month = WebUtil.getParameterAsInteger("month");
		List<TaxData> taxDatas = this.service.getSaleTaxReportData(year, month);
		JSONObject json = new JSONObject();
		json.put("year", year + "");
		json.put("month", month + "");
		JSONObject sheet1 = new JSONObject();
		JSONArray dataArray = new JSONArray();
		int i = 1;
		for (TaxData taxData : taxDatas) {
			JSONObject dataJSON = new JSONObject();
			dataJSON.put("companyID", i + "");
			dataJSON.put("taxer", taxData.getCompany().getName());
			dataJSON.put("createdDate", "02/14/2009");
			dataJSON.put("totalTax",
					NumberUtil.getDoubleForDisplay(taxData.getTotalTax()));
			dataJSON.put("vat",
					NumberUtil.getDoubleForDisplay(taxData.getTotalVAT()));
			dataJSON.put("operateTax",
					NumberUtil.getDoubleForDisplay(taxData.getOperateTax()));
			dataJSON.put("constructTax", NumberUtil.getDoubleForDisplay(taxData
					.getConstructionTax()));
			dataJSON.put("comanyIncomeTax", NumberUtil
					.getDoubleForDisplay(taxData.getTotalCompanyIncomeTax()));
			dataJSON.put("personalIncomeTax", NumberUtil
					.getDoubleForDisplay(taxData.getPersonalIncomingTax()));
			dataJSON.put("otherTax",
					NumberUtil.getDoubleForDisplay(taxData.getTotalOtherTax()));
			dataJSON.put("sales",
					NumberUtil.getDoubleForDisplay(taxData.getSales()));
			dataArray.put(dataJSON);
			i++;
		}
		sheet1.put("data", dataArray);
		json.put("sheet1", sheet1);
		return json;
	}

}
