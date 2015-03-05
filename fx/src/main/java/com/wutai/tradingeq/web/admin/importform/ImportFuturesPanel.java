// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web.admin.importform;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.file.Folder;

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
public class ImportFuturesPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @Inject
    private ServiceManager service;
    
    private SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    
    private Form<Void> importFutureForm;
    private FileUploadField futureRealDataUpload;
    private FileUploadField futureOvallUpload;
    
    public ImportFuturesPanel(String id) {
        super(id);
        importFutureForm = new Form<Void>("future-import");
        add(importFutureForm);
        importFutureForm.add(futureOvallUpload = new FileUploadField("future-overall-file"));
        importFutureForm.add(futureRealDataUpload = new FileUploadField("future-real-data-file"));
        importFutureForm.add(new AjaxSubmitLink("tmbox-import", importFutureForm) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                String fileName = futureRealDataUpload.getFileUpload().getClientFileName();
                if (fileName.endsWith(".csv")) {
                    try {
                        
                        FileUploadUtil.uploadFile(
                                futureRealDataUpload.getFileUpload(),
                                new Folder(
                                        new File(
                                                Config.getConfig(FXConfigurationKey.IMPORT_DATA_FILE_PATH))),
                                TradingEQConstants.FUTURE_REAL_DATA_FILE);
                        
                        FXApplication.get().setFutureRealData(service
                                .loadFutureRealDataReport(
                                        new FileInputStream(
                                                new File(
                                                        Config.getConfig(FXConfigurationKey.IMPORT_DATA_FILE_PATH)
                                                                + TradingEQConstants.FUTURE_REAL_DATA_FILE)),
                                        new FileInputStream(
                                                new File(
                                                        Config.getConfig(FXConfigurationKey.IMPORT_DATA_FILE_PATH)
                                                                + TradingEQConstants.FUTURE_OVERALL_DATA_FILE))));
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

}
