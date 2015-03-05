// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.page.future.cta;

import java.text.SimpleDateFormat;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import com.wutai.tradingeq.model.FutureRealData;
import com.wutai.tradingeq.model.FutureRealDataReport;
import com.wutai.tradingeq.web.FXApplication;

/**
 * @author $Author$
 *
 */
public class FutureRealDataReportPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");

    public FutureRealDataReportPanel(String id) {
        super(id);
        FutureRealDataReport report = FXApplication.get().getFutureRealData();
        try {
            add(new Label("futureChartData", report.getChartDataJSON()));
        } catch (JSONException e) {
            throw new RuntimeException("error fail");
        }
        add(new ListView<FutureRealData>("tmboxRealData", report.getDataListForDisplay()) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<FutureRealData> item) {
                FutureRealData data = item.getModelObject();
                item.add(new Label("date", df.format(data.getDate())));
                item.add(new Label("ticket", data.getTicket()));
                item.add(new Label("type", data.getType()));
                item.add(new Label("isOpen", data.getIsOpen()));
                item.add(new Label("price", data.getPrice()));
                item.add(new Label("amount", data.getAmount()));
                item.add(new Label("commission", data.getCommission()));
                item.add(new Label("toubao", data.getToubao()));
                item.add(new Label("tradingNo", data.getTradingNo()));
                if(item.getIndex() % 2 == 1 ) {
                    item.add(AttributeModifier.append("bgcolor", "#E0E0E0"));
                }
            }
        });
    }

}
