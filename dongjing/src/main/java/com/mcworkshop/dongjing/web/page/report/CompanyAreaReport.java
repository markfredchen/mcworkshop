package com.mcworkshop.dongjing.web.page.report;

import com.google.inject.Inject;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.persistence.DJServiceMapper;
import com.mcworkshop.dongjing.persistence.po.CompanyAreaPO;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.text.DecimalFormat;

/**
 * Created by markfredchen on 4/11/15.
 */
public class CompanyAreaReport extends DJContentBasePage {

    @Inject
    DJServiceMapper mapper;

    public CompanyAreaReport(PageParameters params) {
        super(params);
        CompanyAreaPO areas = mapper.getCompanyAreaReport();
        DecimalFormat df = new DecimalFormat("#.##");
        add(new Label("factoryArea", df.format(areas.getFactoryArea())));
        add(new Label("factoryActualArea", df.format(areas.getFactoryArea() + areas.getFactoryControlArea() + areas.getFactoryRentArea() + areas.getFactoryUsageArea())));
        add(new Label("factoryUsageArea", df.format(areas.getFactoryUsageArea())));
        add(new Label("factoryControlArea", df.format(areas.getFactoryControlArea())));
        add(new Label("factoryRentArea", df.format(areas.getFactoryRentArea())));
    }



    @Override
    protected Panel getLeftNaviPanel(String id) {
        return new ReportLeftNavigatorPanel(id);
    }

    @Override
    protected String getPageClassName() {
        return "report";
    }

    @Override
    protected ResourceModel getPageTitle() {
        return WicketMessageUtil.getResourceModel("dj.report.company.report");
    }

}
