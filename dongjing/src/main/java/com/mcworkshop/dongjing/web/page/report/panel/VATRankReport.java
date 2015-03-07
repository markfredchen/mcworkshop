// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: VATRankReport.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.web.page.report.panel;

import org.apache.wicket.ajax.json.JSONArray;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.ajax.json.JSONObject;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class VATRankReport extends BaseChartReportPanel {

	private static final long serialVersionUID = 1L;

	public VATRankReport(String id, List<Calendar> months) {
		super(id, months);

	}

	@Override
	protected String getReportID() {
		return "vat-rank";
	}

	@Override
	protected String getReportTitle() {
		return "企业增值税排名";
	}

	@Override
	protected String getReportData(int year, int month) {
		Map<String, Double> vatMap = service.getTop10VATCompany(year, month);
		JSONObject data = new JSONObject();
		try {
			data.put("type", "serial");
			data.put("theme", "light");
			data.put("pathToImages", "http://www.amcharts.com/lib/3/images/");

			JSONArray array = new JSONArray();

			for (String name : vatMap.keySet()) {
				JSONObject pData1 = new JSONObject();
				pData1.put("name", name);
				pData1.put("vat", vatMap.get(name));
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
			cursor.put("categoryBalloonEnabled", false);
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
