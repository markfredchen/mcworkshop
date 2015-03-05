// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.wicket.ajax.json.JSONArray;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.ajax.json.JSONObject;

/**
 * @author $Author$
 * 
 */
public class FutureRealDataReport implements Serializable {

    private static final long       serialVersionUID = 1L;
    private List<FutureOverallData> overallData      = new ArrayList<FutureOverallData>();
    private List<FutureRealData>    realData         = new ArrayList<FutureRealData>();
    
    private SimpleDateFormat df = new SimpleDateFormat("MM/dd");

    public String getChartDataJSON() throws JSONException{
        Collections.sort(overallData, new Comparator<FutureOverallData>() {
            @Override
            public int compare(FutureOverallData d1, FutureOverallData d2) {
                if(d1.getDate().getTime() > d2.getDate().getTime()) {
                    return 1;
                }else if(d1.getDate().getTime() == d2.getDate().getTime()){
                    return 0;
                }else {
                    return -1;
                }
            }
        });
        JSONObject chartDataJSON = new JSONObject();
        JSONArray chartDataItems = new JSONArray();
        for(int i = 0; i < overallData.size(); i++) {
            JSONObject chartDataItem = new JSONObject();
            chartDataItem.put("index", df.format(overallData.get(i).getDate()));
            chartDataItem.put("profit", overallData.get(i).getAssets());
            chartDataItems.put(chartDataItem);
        }
        chartDataJSON.put("chartData", chartDataItems);
        
        return chartDataJSON.toString();
    }
    
    public List<FutureOverallData> getOverallData() {
        return overallData;
    }

    public List<FutureRealData> getRealData() {
        return realData;
    }

    public void setOverallData(List<FutureOverallData> overallData) {
        this.overallData = overallData;
    }

    public void setRealData(List<FutureRealData> realData) {
        this.realData = realData;
    }

    public List<FutureRealData> getDataListForDisplay() {
        if(realData.size() > 30) {
            return this.realData.subList(0, 30);
        }else {
            return this.realData;
        }
    }
}
