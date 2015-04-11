package com.mcworkshop.dongjing.web.page.report;

import com.google.inject.Inject;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.persistence.DJServiceMapper;
import com.mcworkshop.dongjing.persistence.po.ProjectApplyStatus;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Created by markfredchen on 4/11/15.
 */
public class ProjectApplicationReport extends DJContentBasePage {

    @Inject
    DJServiceMapper mapper;

    public ProjectApplicationReport(PageParameters params) {
        super(params);
        ProjectApplyStatus status = mapper.getProjectApplyStatus();
        add(new Label("isACount", status.getIsACount()));
        add(new Label("isBCount", status.getIsBCount()));
        add(new Label("isCCount", status.getIsCCount()));
        add(new Label("isDCount", status.getIsDCount()));
        add(new Label("isECount", status.getIsECount()));
        add(new Label("isFCount", status.getIsFCount()));
        add(new Label("isGCount", status.getIsGCount()));
        add(new Label("isHCount", status.getIsHCount()));
        add(new Label("isICount", status.getIsICount()));
        add(new Label("isJCount", status.getIsJCount()));
        add(new Label("isKCount", status.getIsKCount()));
        add(new Label("isLCount", status.getIsLCount()));
        add(new Label("isMCount", status.getIsMCount()));
        add(new Label("isNCount", status.getIsNCount()));
        add(new Label("isOCount", status.getIsOCount()));
        add(new Label("isPCount", status.getIsPCount()));
        add(new Label("isQCount", status.getIsQCount()));
        add(new Label("isRCount", status.getIsRCount()));
        add(new Label("isSCount", status.getIsSCount()));
        add(new Label("isTCount", status.getIsTCount()));
        add(new Label("isUCount", status.getIsUCount()));
        add(new Label("isVCount", status.getIsVCount()));
        add(new Label("isWCount", status.getIsWCount()));

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
