// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.admin.importform;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.file.Folder;
import org.jumpmind.symmetric.csv.CsvWriter;

import com.google.inject.Inject;
import com.mcworkshop.common.configuration.Config;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.wutai.tradingeq.constant.FXConfigurationKey;
import com.wutai.tradingeq.constant.TradingEQConstants;
import com.wutai.tradingeq.service.ServiceManager;
import com.wutai.tradingeq.util.FileUploadUtil;
import com.wutai.tradingeq.web.FXApplication;

/**
 * @author $Author$
 *
 */
public class ImportTMBoxPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @Inject
    protected ServiceManager service;
    
    private SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    
    private Form<Void> importTMBoxForm;
    private RequiredTextField<Double> tmboxInitialBalance;
    private TextField<String>  grossProfit;
    private TextField<String>  grossLoss;
    private TextField<String>  totalNetProfit;
    private TextField<String>  profitFactor;
    private TextField<String>  expectedPayoff;
    private TextField<String>  absoluteDrawdown;
    private TextField<String>  maxDrawdown;
    private TextField<String>  relativeDrawdown;
    private TextField<String>  totalTrades;
    private TextField<String>  shortPositions;
    private TextField<String>  longPositions;
    private TextField<String>  profitTrades;
    private TextField<String>  lossTrades;
    private TextField<String>  largestProfitTrade;
    private TextField<String>  largestLossTrade;
    private TextField<String>  averageProfitTrade;
    private TextField<String>  averageLossTrade;
    private TextField<String>  maxConsecutiveWins;
    private TextField<String>  maxConsecutiveLoss;
    private TextField<String>  minConsecutiveWins;
    private TextField<String>  minConsecutiveLoss;
    private TextField<String>  averageConsecutiveWins;
    private TextField<String>  averageConsecutiveLoss;
    private FileUploadField tmboxUpload;
    private AjaxSubmitLink tmboxImport;

    protected String dataFile;
    protected String initDataFile;
    
    public ImportTMBoxPanel(String id, final String dataFile, final String initDataFile, String title) {
        super(id);
        add(new Label("title", title));
        this.dataFile = dataFile;
        this.initDataFile = initDataFile;
        importTMBoxForm = new Form<Void>("import-tmbox-form");
        add(importTMBoxForm);
        importTMBoxForm.add(tmboxInitialBalance = new RequiredTextField<Double>("initialBalance", new Model<Double>(0D)));
        importTMBoxForm.add(grossProfit = new TextField<String>("grossProfit", new Model<String>("")));
        importTMBoxForm.add(grossLoss = new TextField<String>("grossLoss", new Model<String>("")));
        importTMBoxForm.add(totalNetProfit = new TextField<String>("totalNetProfit", new Model<String>("")));
        importTMBoxForm.add(profitFactor = new TextField<String>("profitFactor", new Model<String>("")));
        importTMBoxForm.add(expectedPayoff = new TextField<String>("expectedPayoff", new Model<String>("")));
        importTMBoxForm.add(absoluteDrawdown = new TextField<String>("absoluteDrawdown", new Model<String>("")));
        importTMBoxForm.add(maxDrawdown = new TextField<String>("maxDrawdown", new Model<String>("")));
        importTMBoxForm.add(relativeDrawdown = new TextField<String>("relativeDrawdown", new Model<String>("")));
        importTMBoxForm.add(totalTrades = new TextField<String>("totalTrades", new Model<String>("")));
        importTMBoxForm.add(shortPositions = new TextField<String>("shortPositions", new Model<String>("")));
        importTMBoxForm.add(longPositions = new TextField<String>("longPositions", new Model<String>("")));
        importTMBoxForm.add(profitTrades = new TextField<String>("profitTrades", new Model<String>("")));
        importTMBoxForm.add(lossTrades = new TextField<String>("lossTrades", new Model<String>("")));
        importTMBoxForm.add(largestProfitTrade = new TextField<String>("largestProfitTrade", new Model<String>("")));
        importTMBoxForm.add(largestLossTrade = new TextField<String>("largestLossTrade", new Model<String>("")));
        importTMBoxForm.add(averageProfitTrade = new TextField<String>("averageProfitTrade", new Model<String>("")));
        importTMBoxForm.add(averageLossTrade = new TextField<String>("averageLossTrade", new Model<String>("")));
        importTMBoxForm.add(maxConsecutiveWins = new TextField<String>("maxConsecutiveWins", new Model<String>("")));
        importTMBoxForm.add(maxConsecutiveLoss = new TextField<String>("maxConsecutiveLoss", new Model<String>("")));
        importTMBoxForm.add(minConsecutiveWins = new TextField<String>("minConsecutiveWins", new Model<String>("")));
        importTMBoxForm.add(minConsecutiveLoss = new TextField<String>("minConsecutiveLoss", new Model<String>("")));
        importTMBoxForm.add(averageConsecutiveWins = new TextField<String>("averageConsecutiveWins", new Model<String>("")));
        importTMBoxForm.add(averageConsecutiveLoss = new TextField<String>("averageConsecutiveLoss", new Model<String>("")));
        importTMBoxForm.add(tmboxUpload = new FileUploadField("tmbox-data-file"));
        importTMBoxForm.add(tmboxImport = new AjaxSubmitLink("tmbox-import", importTMBoxForm) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                String fileName = tmboxUpload.getFileUpload().getClientFileName();
                if (fileName.endsWith(".csv")) {
                    try {
                        
                        CsvWriter writer = new CsvWriter(Config.getConfig(FXConfigurationKey.IMPORT_DATA_FILE_PATH) + initDataFile);
                        writer.writeRecord(Arrays.asList("initialBalance", "grossProfit", "grossLoss", "totalNetProfit", "profitFactor", "expectedPayoff", "absoluteDrawdown", "maxDrawdown", "relativeDrawdown", "totalTrades", "shortPositions", "longPositions", "profitTrades", "lossTrades", "largestProfitTrade", "largestLossTrade", "averageProfitTrade", "averageLossTrade", "maxConsecutiveWins", "maxConsecutiveLoss", "minConsecutiveWins", "minConsecutiveLoss", "averageConsecutiveWins", "averageConsecutiveLoss").toArray(new String[] {}));
                        writer.writeRecord(Arrays.asList(tmboxInitialBalance.getValue(), grossProfit.getValue(), grossLoss.getValue(), totalNetProfit.getValue(), profitFactor.getValue(), expectedPayoff.getValue(), absoluteDrawdown.getValue(), maxDrawdown.getValue(), relativeDrawdown.getValue(), totalTrades.getValue(), shortPositions.getValue(), longPositions.getValue(), profitTrades.getValue(), lossTrades.getValue(), largestProfitTrade.getValue(), largestLossTrade.getValue(), averageProfitTrade.getValue(), averageLossTrade.getValue(), maxConsecutiveWins.getValue(), maxConsecutiveLoss.getValue(), minConsecutiveWins.getValue(), minConsecutiveLoss.getValue(), averageConsecutiveWins.getValue(), averageConsecutiveLoss.getValue()).toArray(new String[] {}));
                        writer.close();
                        FileUploadUtil.uploadFile(
                                tmboxUpload.getFileUpload(),
                                new Folder(
                                        new File(
                                                Config.getConfig(FXConfigurationKey.IMPORT_DATA_FILE_PATH))),
                                dataFile);
                        
                        populateData();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }else {
                    form.error(WicketMessageUtil
                            .getResourceString("file.format.not.support"));
                }
                
                super.onSubmit(target, form);
            }
            
        });
    }
    
    public void populateData() {
        try {
            FXApplication.get().setTmboxRealData(service
                    .loadTMBoxRealDataReport(
                            new FileInputStream(
                                    new File(
                                            Config.getConfig(FXConfigurationKey.IMPORT_DATA_FILE_PATH)
                                                    + TradingEQConstants.TMBOX_DATA_FILE)),
                            new FileInputStream(
                                    new File(
                                            Config.getConfig(FXConfigurationKey.IMPORT_DATA_FILE_PATH)
                                                    + TradingEQConstants.TMBOX_DATA_INIT_BALANCE_FILE))));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
