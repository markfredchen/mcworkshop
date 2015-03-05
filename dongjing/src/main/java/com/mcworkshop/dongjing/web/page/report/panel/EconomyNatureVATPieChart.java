// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: EconomyNatureVATPieChart.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.web.page.report.panel;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.wicket.ajax.json.JSONArray;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.ajax.json.JSONObject;

import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.EconomyNature;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class EconomyNatureVATPieChart extends BaseChartReportPanel {

	private static final long serialVersionUID = 1L;

	public EconomyNatureVATPieChart(String id, List<Calendar> months) {
		super(id, months);
	}

	@Override
	protected String getReportID() {
		return "envat-pie";
	}

	@Override
	protected String getReportTitle() {
		return "企业性质及个人所得税饼状图";
	}

	@Override
	protected String getReportData(int year, int month) {
		Map<Long, Double> enpitMap = service
				.getEconomyNaturePersonalIncomingTaxData(year, month);
		JSONObject data = new JSONObject();
		try {
			data.put("type", "pie");
			data.put("theme", "light");
			JSONArray array = new JSONArray();
			JSONObject pData1 = new JSONObject();
			pData1.put("economyNature", WicketMessageUtil
					.getResourceString(EconomyNature.PRIVATE.getMessageKey()));
			pData1.put(
					"vat",
					enpitMap.get(SystemEnumeration.getInstance().getIdByKey(
							EconomyNature.PRIVATE)));
			array.put(pData1);

			JSONObject pData2 = new JSONObject();
			pData2.put("economyNature", WicketMessageUtil
					.getResourceString(EconomyNature.FOREIGN.getMessageKey()));
			pData2.put(
					"vat",
					enpitMap.get(SystemEnumeration.getInstance().getIdByKey(
							EconomyNature.FOREIGN)));
			array.put(pData2);

			JSONObject pData3 = new JSONObject();
			pData3.put("economyNature", WicketMessageUtil
					.getResourceString(EconomyNature.GROUP.getMessageKey()));
			pData3.put(
					"vat",
					enpitMap.get(SystemEnumeration.getInstance().getIdByKey(
							EconomyNature.GROUP)));
			array.put(pData3);
			data.put("dataProvider", array);

			data.put("valueField", "vat");
			data.put("titleField", "economyNature");
			data.put("outlineAlpha", 0.4);
			data.put("depth3D", 15);
			data.put("balloonText", "[[title]]");
			data.put("angle", 30);
			return data.toString();
		} catch (JSONException e) {
			return null;
		}
	}

}
