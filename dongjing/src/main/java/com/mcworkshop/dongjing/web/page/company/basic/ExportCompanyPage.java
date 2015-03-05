package com.mcworkshop.dongjing.web.page.company.basic;

import com.google.inject.Inject;
import com.mcworkshop.common.exception.ExceptionUtil;
import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.web.component.dropdownchoice.SystemEnumerationDropdownChoiceRender;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.AffiliateBlock;
import com.mcworkshop.dongjing.persistence.DJServiceMapper;
import com.mcworkshop.dongjing.security.ACLBlockUtil;
import com.mcworkshop.dongjing.service.DJService;
import com.mcworkshop.dongjing.service.report.CompanyListReport;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import com.mcworkshop.dongjing.web.layout.MenuHeaderPanel;
import com.mcworkshop.dongjing.web.page.company.CompanyLeftNaviPanel;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MarkfredChen on 2014/9/1.
 */
public class ExportCompanyPage extends DJContentBasePage {
    @Inject
    private DJServiceMapper mapper;

    @Inject
    private DJService service;

    private Model<AffiliateBlock> blockModel = new Model<AffiliateBlock>();

    public ExportCompanyPage(PageParameters params) {
        super(params);
        final DropDownChoice<AffiliateBlock> blockChoice;
        add(blockChoice = new DropDownChoice<AffiliateBlock>("region-select", blockModel, new AbstractReadOnlyModel<List<? extends AffiliateBlock>>() {
            @Override
            public List<? extends AffiliateBlock> getObject() {
                AffiliateBlock block = ACLBlockUtil.getOwnAffiliateBlock();
                if (block != null) {
                    return Arrays.asList(block);
                } else {
                    return SystemEnumeration.getInstance().getKeysByType(
                            AffiliateBlock.class);
                }
            }
        }, new SystemEnumerationDropdownChoiceRender()) {
            @Override
            protected boolean wantOnSelectionChangedNotifications() {
                return true;
            }
        });

        add(new DownloadLink("export-company-basic", new AbstractReadOnlyModel<File>() {
            @Override
            public File getObject() {
                CompanyListReport report = null;
                if (blockModel.getObject() == null && ACLBlockUtil.getOwnAffiliateBlock() == null) {
                    report = new CompanyListReport(service.getCompaniesForExport(null));
                } else if (blockModel.getObject() == null && ACLBlockUtil.getOwnAffiliateBlock() != null) {
                    report = new CompanyListReport(service.getCompaniesForExport(ACLBlockUtil.getOwnAffiliateBlock()));
                } else {
                    report = new CompanyListReport(service.getCompaniesForExport(blockModel.getObject()));
                }
                try {
                    return report.generateReportByFile(true);
                } catch (Throwable e) {
                    throw ExceptionUtil.handleRuntimeException(
                            "generate report fail: CompanyReport", e);
                }
            }
        }, "企业基础信息.xlsx"));

    }

    @Override
    protected String getPageClassName() {
        return "company-basic-list";
    }

    @Override
    protected ResourceModel getPageTitle() {
        return WicketMessageUtil
                .getResourceModel("dj.company.management.title");
    }

    @Override
    protected Panel getHeaderPanel(String id) {
        return new MenuHeaderPanel(id);
    }

    @Override
    protected Panel getLeftNaviPanel(String id) {
        return new CompanyLeftNaviPanel(id);
    }
}
