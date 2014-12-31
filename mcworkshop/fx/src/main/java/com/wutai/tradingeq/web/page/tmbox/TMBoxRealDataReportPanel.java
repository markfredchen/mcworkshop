// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.page.tmbox;

import java.text.SimpleDateFormat;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import com.wutai.tradingeq.model.TMBoxRealData;
import com.wutai.tradingeq.model.TMBoxRealDataReport;
import com.wutai.tradingeq.web.FXApplication;

/**
 * @author $Author$
 *
 */
public class TMBoxRealDataReportPanel extends Panel {

    private static final long serialVersionUID = 1L;
    
    SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");

    public TMBoxRealDataReportPanel(String id, TMBoxRealDataReport report, String title) {
        super(id);
        add(new Label("title", title));
        try {
            add(new Label("tmboxChartData", report.getChartDataJSON()));
        } catch (JSONException e) {
            throw new RuntimeException("error fail");
        }
        add(new ListView<TMBoxRealData>("tmboxRealData", report.getDataListForDisplay()) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<TMBoxRealData> item) {
                TMBoxRealData data = item.getModelObject();
                item.add(new Label("ticket", data.getTicket()));
                item.add(new Label("openTime", df.format(data.getOpenTime())));
                item.add(new Label("type", data.getType()));
                item.add(new Label("size", data.getSize()));
                item.add(new Label("item", data.getItem()));
                item.add(new Label("openPrice", data.getOpenPrice()));
                item.add(new Label("closeTime", df.format(data.getCloseTime())));
                item.add(new Label("closePrice", data.getClosePrice()));
                item.add(new Label("profit", data.getProfit()));
                if(item.getIndex() % 2 == 1 ) {
                    item.add(AttributeModifier.append("bgcolor", "#E0E0E0"));
                }
            }
        });
        add(new Label("grossProfit", report.getGrossProfit()));
        add(new Label("grossLoss", report.getGrossLoss()));
        add(new Label("totalNetProfit", report.getTotalNetProfit()));
        add(new Label("profitFactor", report.getProfitFactor()));
        add(new Label("expectedPayoff", report.getExpectedPayoff()));
        add(new Label("absoluteDrawdown", report.getAbsoluteDrawdown()));
        add(new Label("maxDrawdown", report.getMaxDrawdown()));
        add(new Label("relativeDrawdown", report.getRelativeDrawdown()));
        add(new Label("totalTrades", report.getTotalTrades()));
        add(new Label("shortPositions", report.getShortPositions()));
        add(new Label("longPositions", report.getLongPositions()));
        add(new Label("profitTrades", report.getProfitTrades()));
        add(new Label("lossTrades", report.getLossTrades()));
        add(new Label("largestProfitTrade", report.getLargestProfitTrade()));
        add(new Label("largestLossTrade", report.getLargestLossTrade()));
        add(new Label("averageProfitTrade", report.getAverageProfitTrade()));
        add(new Label("averageLossTrade", report.getAverageLossTrade()));
        add(new Label("maxConsecutiveWins", report.getMaxConsecutiveWins()));
        add(new Label("maxConsecutiveLoss", report.getMaxConsecutiveLoss()));
        add(new Label("minConsecutiveWins", report.getMinConsecutiveWins()));
        add(new Label("minConsecutiveLoss", report.getMinConsecutiveLoss()));
        add(new Label("averageConsecutiveWins", report.getAverageConsecutiveWins()));
        add(new Label("averageConsecutiveLoss", report.getAverageConsecutiveLoss()));
    }
}
