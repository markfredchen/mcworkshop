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
 * Created by MarkfredChen on 6/29/2014.
 */
public class EconomyNaturePersonalIncomingTaxReport extends
		BaseChartReportPanel {
	private static final long serialVersionUID = 1L;

	public EconomyNaturePersonalIncomingTaxReport(String id,
			List<Calendar> months) {
		super(id, months);
	}

	@Override
	protected String getReportTitle() {
		return "企业性质及个人所得税";
	}

	@Override
	protected String getReportID() {
		return "enpit";
	}

	@Override
	protected String getReportData(int year, int month) {

		Map<Long, Double> enpitMap = service
				.getEconomyNaturePersonalIncomingTaxData(year, month);
		JSONObject data = new JSONObject();
		try {
			data.put("type", "serial");
			data.put("theme", "light");
			data.put("pathToImages", "http://www.amcharts.com/lib/3/images/");

			JSONArray array = new JSONArray();
			JSONObject pData1 = new JSONObject();
			pData1.put("economyNature", WicketMessageUtil
					.getResourceString(EconomyNature.PRIVATE.getMessageKey()));
			pData1.put(
					"vat",
					enpitMap.get(SystemEnumeration.getInstance().getIdByKey(
							EconomyNature.PRIVATE)));
			pData1.put("color", "#0D52D1");
			array.put(pData1);

			JSONObject pData2 = new JSONObject();
			pData2.put("economyNature", WicketMessageUtil
					.getResourceString(EconomyNature.FOREIGN.getMessageKey()));
			pData2.put(
					"vat",
					enpitMap.get(SystemEnumeration.getInstance().getIdByKey(
							EconomyNature.FOREIGN)));
			pData2.put("color", "#0D8ECF");
			array.put(pData2);

			JSONObject pData3 = new JSONObject();
			pData3.put("economyNature", WicketMessageUtil
					.getResourceString(EconomyNature.GROUP.getMessageKey()));
			pData3.put(
					"vat",
					enpitMap.get(SystemEnumeration.getInstance().getIdByKey(
							EconomyNature.GROUP)));
			pData3.put("color", "#2A0CD0");
			array.put(pData3);
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
			data.put("categoryField", "economyNature");
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
