// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.model;

import java.io.Serializable;
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
public class TMBoxRealDataReport implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Double              initialBalance;
    private String  grossProfit;
    private String  grossLoss;
    private String  totalNetProfit;
    private String  profitFactor;
    private String  expectedPayoff;
    private String  absoluteDrawdown;
    private String  maxDrawdown;
    private String  relativeDrawdown;
    private String  totalTrades;
    private String  shortPositions;
    private String  longPositions;
    private String  profitTrades;
    private String  lossTrades;
    private String  largestProfitTrade;
    private String  largestLossTrade;
    private String  averageProfitTrade;
    private String  averageLossTrade;
    private String  maxConsecutiveWins;
    private String  maxConsecutiveLoss;
    private String  minConsecutiveWins;
    private String  minConsecutiveLoss;
    private String  averageConsecutiveWins;
    private String  averageConsecutiveLoss;
    private List<TMBoxRealData> dataList = new ArrayList<TMBoxRealData>();
    
    public String getChartDataJSON() throws JSONException{
        Double balance = new Double(initialBalance);
        Collections.sort(dataList, new Comparator<TMBoxRealData>() {
            @Override
            public int compare(TMBoxRealData d1, TMBoxRealData d2) {
                if(d1.getCloseTime().getTime() > d2.getCloseTime().getTime()) {
                    return -1;
                }else if(d1.getCloseTime().getTime() == d2.getCloseTime().getTime()){
                    return 0;
                }else {
                    return 1;
                }
            }
        });
        JSONObject chartDataJSON = new JSONObject();
        JSONArray chartDataItems = new JSONArray();
        for(int i = 0; i < dataList.size(); i++) {
            balance = balance + dataList.get(i).getProfit(); 
            JSONObject chartDataItem = new JSONObject();
            chartDataItem.put("index", i+1);
            chartDataItem.put("profit", balance.toString());
            chartDataItems.put(chartDataItem);
        }
        chartDataJSON.put("chartData", chartDataItems);
        
        return chartDataJSON.toString();
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public List<TMBoxRealData> getDataList() {
        return dataList;
    }

    public void setDataList(List<TMBoxRealData> dataList) {
        this.dataList = dataList;
    }
    
    public List<TMBoxRealData> getDataListForDisplay(){
        return dataList.subList(0, 30);
    }

    public String getGrossProfit() {
        return grossProfit;
    }

    public String getGrossLoss() {
        return grossLoss;
    }

    public String getTotalNetProfit() {
        return totalNetProfit;
    }

    public String getProfitFactor() {
        return profitFactor;
    }

    public String getExpectedPayoff() {
        return expectedPayoff;
    }

    public String getAbsoluteDrawdown() {
        return absoluteDrawdown;
    }

    public String getMaxDrawdown() {
        return maxDrawdown;
    }

    public String getRelativeDrawdown() {
        return relativeDrawdown;
    }

    public String getTotalTrades() {
        return totalTrades;
    }

    public String getShortPositions() {
        return shortPositions;
    }

    public String getLongPositions() {
        return longPositions;
    }

    public String getProfitTrades() {
        return profitTrades;
    }

    public String getLossTrades() {
        return lossTrades;
    }

    public String getLargestProfitTrade() {
        return largestProfitTrade;
    }

    public String getLargestLossTrade() {
        return largestLossTrade;
    }

    public String getAverageProfitTrade() {
        return averageProfitTrade;
    }

    public String getAverageLossTrade() {
        return averageLossTrade;
    }

    public String getMaxConsecutiveWins() {
        return maxConsecutiveWins;
    }

    public String getMaxConsecutiveLoss() {
        return maxConsecutiveLoss;
    }

    public String getMinConsecutiveWins() {
        return minConsecutiveWins;
    }

    public String getMinConsecutiveLoss() {
        return minConsecutiveLoss;
    }

    public String getAverageConsecutiveWins() {
        return averageConsecutiveWins;
    }

    public String getAverageConsecutiveLoss() {
        return averageConsecutiveLoss;
    }

    public void setGrossProfit(String grossProfit) {
        this.grossProfit = grossProfit;
    }

    public void setGrossLoss(String grossLoss) {
        this.grossLoss = grossLoss;
    }

    public void setTotalNetProfit(String totalNetProfit) {
        this.totalNetProfit = totalNetProfit;
    }

    public void setProfitFactor(String profitFactor) {
        this.profitFactor = profitFactor;
    }

    public void setExpectedPayoff(String expectedPayoff) {
        this.expectedPayoff = expectedPayoff;
    }

    public void setAbsoluteDrawdown(String absoluteDrawdown) {
        this.absoluteDrawdown = absoluteDrawdown;
    }

    public void setMaxDrawdown(String maxDrawdown) {
        this.maxDrawdown = maxDrawdown;
    }

    public void setRelativeDrawdown(String relativeDrawdown) {
        this.relativeDrawdown = relativeDrawdown;
    }

    public void setTotalTrades(String totalTrades) {
        this.totalTrades = totalTrades;
    }

    public void setShortPositions(String shortPositions) {
        this.shortPositions = shortPositions;
    }

    public void setLongPositions(String longPositions) {
        this.longPositions = longPositions;
    }

    public void setProfitTrades(String profitTrades) {
        this.profitTrades = profitTrades;
    }

    public void setLossTrades(String lossTrades) {
        this.lossTrades = lossTrades;
    }

    public void setLargestProfitTrade(String largestProfitTrade) {
        this.largestProfitTrade = largestProfitTrade;
    }

    public void setLargestLossTrade(String largestLossTrade) {
        this.largestLossTrade = largestLossTrade;
    }

    public void setAverageProfitTrade(String averageProfitTrade) {
        this.averageProfitTrade = averageProfitTrade;
    }

    public void setAverageLossTrade(String averageLossTrade) {
        this.averageLossTrade = averageLossTrade;
    }

    public void setMaxConsecutiveWins(String maxConsecutiveWins) {
        this.maxConsecutiveWins = maxConsecutiveWins;
    }

    public void setMaxConsecutiveLoss(String maxConsecutiveLoss) {
        this.maxConsecutiveLoss = maxConsecutiveLoss;
    }

    public void setMinConsecutiveWins(String minConsecutiveWins) {
        this.minConsecutiveWins = minConsecutiveWins;
    }

    public void setMinConsecutiveLoss(String minConsecutiveLoss) {
        this.minConsecutiveLoss = minConsecutiveLoss;
    }

    public void setAverageConsecutiveWins(String averageConsecutiveWins) {
        this.averageConsecutiveWins = averageConsecutiveWins;
    }

    public void setAverageConsecutiveLoss(String averageConsecutiveLoss) {
        this.averageConsecutiveLoss = averageConsecutiveLoss;
    }

    
}
