// Copyright 2013 Active Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.service;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.List;

import org.jumpmind.symmetric.csv.CsvReader;
import org.mybatis.guice.transactional.Transactional;

import com.google.inject.Inject;
import com.wutai.tradingeq.dao.ContactMapper;
import com.wutai.tradingeq.domain.Contact;
import com.wutai.tradingeq.model.FutureOverallData;
import com.wutai.tradingeq.model.FutureRealData;
import com.wutai.tradingeq.model.FutureRealDataReport;
import com.wutai.tradingeq.model.TMBoxRealData;
import com.wutai.tradingeq.model.TMBoxRealDataReport;

/**
 * @author mchen1
 * @versin $Rev$, $Date$
 * @since 1.0
 */
public class ServiceManagerImpl implements ServiceManager {

    @Inject
    private ContactMapper contactMapper;
    
	@Override
	@Transactional
	public void createContact(Contact contact) {
		contactMapper.createContact(contact);
	}

    @Override
    public TMBoxRealDataReport loadTMBoxRealDataReport(InputStream reportDataStream, InputStream initialBalanceData) {
        try {
            TMBoxRealDataReport report = new TMBoxRealDataReport();
            CsvReader initialBalanceReader = new  CsvReader(initialBalanceData, Charset.defaultCharset());
            initialBalanceReader.readHeaders();
            initialBalanceReader.readRecord();
            report.setInitialBalance(Double.valueOf(initialBalanceReader.get(0)));
            report.setGrossProfit(initialBalanceReader.get(1));
            report.setGrossLoss(initialBalanceReader.get(2));
            report.setTotalNetProfit(initialBalanceReader.get(3));
            report.setProfitFactor(initialBalanceReader.get(4));
            report.setExpectedPayoff(initialBalanceReader.get(5));
            report.setAbsoluteDrawdown(initialBalanceReader.get(6));
            report.setMaxDrawdown(initialBalanceReader.get(7));
            report.setRelativeDrawdown(initialBalanceReader.get(8));
            report.setTotalTrades(initialBalanceReader.get(9));
            report.setShortPositions(initialBalanceReader.get(10));
            report.setLongPositions(initialBalanceReader.get(11));
            report.setProfitTrades(initialBalanceReader.get(12));
            report.setLossTrades(initialBalanceReader.get(13));
            report.setLargestProfitTrade(initialBalanceReader.get(14));
            report.setLargestLossTrade(initialBalanceReader.get(15));
            report.setAverageProfitTrade(initialBalanceReader.get(16));
            report.setAverageLossTrade(initialBalanceReader.get(17));
            report.setMaxConsecutiveWins(initialBalanceReader.get(18));
            report.setMaxConsecutiveLoss(initialBalanceReader.get(19));
            report.setMinConsecutiveWins(initialBalanceReader.get(20));
            report.setMinConsecutiveLoss(initialBalanceReader.get(21));
            report.setAverageConsecutiveWins(initialBalanceReader.get(22));
            report.setAverageConsecutiveLoss(initialBalanceReader.get(23));
            
            SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
            CsvReader reader = new CsvReader(reportDataStream,
                    Charset.defaultCharset());
            reader.readHeaders();
            while (reader.readRecord()) {
                TMBoxRealData data = new TMBoxRealData();
                data.setTicket(reader.get("ticket"));
                data.setOpenTime(df.parse(reader.get("openTime")));
                data.setCloseTime(df.parse(reader.get("closeTime")));
                data.setType(reader.get("type"));
                data.setSize(Double.valueOf(reader.get("size")));
                data.setOpenPrice(Double.valueOf(reader.get("openPrice")));
                data.setClosePrice(Double.valueOf(reader.get("closePrice")));
                data.setItem(reader.get("closePrice"));
                data.setProfit(Double.valueOf(reader.get("profit")));
                report.getDataList().add(data);
            }
            return report;
        } catch (Throwable e) {
            throw new RuntimeException("load tmbox real data failed");
        }
    }

    @Override
    public FutureRealDataReport loadFutureRealDataReport(
            InputStream futureRealData, InputStream futureOverallData) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
        SimpleDateFormat overallDF = new SimpleDateFormat("MM/dd/yyyy");
        try {
            FutureRealDataReport report = new FutureRealDataReport();
            CsvReader overallData = new CsvReader(futureOverallData, Charset.defaultCharset());
            overallData.readHeaders();
            while(overallData.readRecord()) {
                FutureOverallData data = new FutureOverallData();
                data.setDate(overallDF.parse(overallData.get(0)));
                data.setAssets(Double.valueOf(overallData.get(1)));
                report.getOverallData().add(data);
            }
            CsvReader realData = new CsvReader(futureRealData, Charset.defaultCharset());
            realData.readHeaders();
            while(realData.readRecord()) {
                FutureRealData data = new FutureRealData();
                data.setDate(df.parse(realData.get(0)));
                data.setTicket(realData.get(1));
                String type = realData.get(2);
                if(type.equals("0")) {
                    data.setType("卖出");
                }else {
                    data.setType("买入");
                }
                String isOpen = realData.get(3);
                if(isOpen.equals("0")) {
                    data.setIsOpen("开仓");
                }else {
                    data.setIsOpen("平今");
                }
                data.setPrice(Double.valueOf(realData.get(4)));
                data.setAmount(Integer.valueOf(realData.get(5)));
                data.setCommission(Double.valueOf(realData.get(6)));
                data.setToubao("投机");
                data.setTradingNo(realData.get(8));
                report.getRealData().add(data);
            }
            return report;
        } catch (Throwable e) {
            throw new RuntimeException("load future real data failed");
        }
        
        
    }

    @Override
    public List<Contact> getContacts() {
        return this.contactMapper.getContacts();
    }
    
    
}
