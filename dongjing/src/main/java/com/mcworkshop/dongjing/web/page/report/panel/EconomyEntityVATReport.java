// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: EconomyEntityVATReport.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.web.page.report.panel;

import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.EconomyEntity;
import org.apache.wicket.ajax.json.JSONArray;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.ajax.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class EconomyEntityVATReport extends BaseChartReportPanel {

	private static final long serialVersionUID = 1L;

	public EconomyEntityVATReport(String id, List<Calendar> months) {
		super(id, months);
	}

	@Override
	protected String getReportID() {
		return "eevat";
	}

	@Override
	protected String getReportTitle() {
		return "经济实体及增值税报表";
	}

	@Override
	protected String getReportData(int year, int month) {
		Map<String, Double> dataMap = service.getEconomyEntityVAT(year, month);
		JSONObject data = new JSONObject();
		try {
			data.put("type", "serial");
			data.put("theme", "light");
			data.put("pathToImages", "http://www.amcharts.com/lib/3/images/");

			JSONArray array = new JSONArray();
			List<String> keyList = new ArrayList<String>();
			keyList.add(EconomyEntity.INDUSTRY.toString());
			keyList.add(EconomyEntity.COMMERCIAL.toString());
			keyList.add(EconomyEntity.SERVICE.toString());
			keyList.add(EconomyEntity.CONSTRUCTION.toString());
			keyList.add(EconomyEntity.HOUSEHOLDING.toString());
			keyList.add("other");
			for (String name : keyList) {
				JSONObject pData1 = new JSONObject();
				if (name.equals("other")) {
					pData1.put("name", "其他");
				} else {
					pData1.put("name", WicketMessageUtil
							.getResourceString(EconomyEntity.valueOf(name)
									.getMessageKey()));
				}
				pData1.put("vat", dataMap.get(name));
				pData1.put("color", "#0D52D1");
				array.put(pData1);
			}
			data.put("dataProvider", array);
			JSONArray configArray = new JSONArray();
			JSONObject config = new JSONObject();
			config.put("axisAlpha", 0);
			config.put("position", "left");
			config.put("title", getReportTitle());
			configArray.put(config);
			data.put("valueAxes", configArray);
			data.put("startDuration", 1);
			JSONArray graphsArray = new JSONArray();
			JSONObject graphs = new JSONObject();
			graphs.put("balloonText", "<b>[[category]]: [[value]]</b>");
			graphs.put("colorField", "color");
			graphs.put("fillAlphas", 0.9);
			graphs.put("lineAlpha", 0.2);
			graphs.put("type", "column");
			graphs.put("valueField", "vat");
			graphsArray.put(graphs);
			data.put("graphs", graphsArray);
			JSONObject cursor = new JSONObject();
			cursor.put("categoryBalloonEnabled", true);
			cursor.put("cursorAlpha", 0);
			cursor.put("zoomable", false);
			data.put("chartCursor", cursor);
			data.put("categoryField", "name");
			JSONObject axis = new JSONObject();
			axis.put("gridPosition", "start");
			axis.put("labelRotation", 45);
			data.put("categoryAxis", axis);
			data.put("amExport", new JSONObject());
			return data.toString();
		} catch (JSONException e) {
			return null;
		}
	}

}
