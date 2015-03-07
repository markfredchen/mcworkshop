// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.security.panel;

import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.web.component.dropdownchoice.SystemEnumerationDropdownChoiceRender;
import com.mcworkshop.common.web.component.dropdownchoice.YesOrNoDrowdownChoiceRender;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerOption;
import com.mcworkshop.common.web.component.form.datetimepicker.View;
import com.mcworkshop.common.web.component.validation.FormFieldValidationContainer;
import com.mcworkshop.common.web.component.wizard.WizardContentPanel;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.DangerousChemistryType;
import com.mcworkshop.dongjing.domain.Security;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import java.util.Arrays;

/**
 * @author $Author$
 */
public class BasicInfoStep extends WizardContentPanel<Security> {

    private static final long serialVersionUID = 1L;

    private static final Log log = LogFactory.getLog(BasicInfoStep.class);

    private static final String DATE_PATTERN = "MM-dd-yyyy";
    private DatetimePickerOption datePickerOption = new DatetimePickerOption();

    private DropDownChoice<Boolean> isDCProduceOrg;
    private DropDownChoice<DangerousChemistryType> dcType;


    public BasicInfoStep(IModel<Security> model) {
        super(model);
        datePickerOption.setMinView(View.MONTH);
        datePickerOption.setMaxView(View.DECADE);
        datePickerOption.setLanguage("zh-CN");
        form = new Form<Security>("basic-info");
        form.add(new FormFieldValidationContainer("name-feedback-container",
                new RequiredTextField<String>("name")));
        form.add(new DropDownChoice<Boolean>("isEmergencyPassStandard", Arrays.asList(
                Boolean.TRUE, Boolean.FALSE), new YesOrNoDrowdownChoiceRender("isEmergencyPassStandard")));
        form.add(new DropDownChoice<Boolean>("isSecurityStandardPassed", Arrays.asList(
                Boolean.TRUE, Boolean.FALSE), new YesOrNoDrowdownChoiceRender("isSecurityStandardPassed")));
        form.add(new TextField<String>("fireController"));
        form.add(new DropDownChoice<Boolean>("isEnvAssessPass", Arrays.asList(
                Boolean.TRUE, Boolean.FALSE), new YesOrNoDrowdownChoiceRender("isEnvAssessPass")));
        form.add(new DropDownChoice<Boolean>("isDirtyWaterMgn", Arrays.asList(
                Boolean.TRUE, Boolean.FALSE), new YesOrNoDrowdownChoiceRender("isDirtyWaterMgn")));
        form.add(isDCProduceOrg = new DropDownChoice<Boolean>("isDCProduceOrg", Arrays.asList(
                Boolean.TRUE, Boolean.FALSE), new YesOrNoDrowdownChoiceRender()));
        form.add(dcType = new DropDownChoice<DangerousChemistryType>("dcType",
                SystemEnumeration.getInstance().getKeysByType(
                        DangerousChemistryType.class),
                new SystemEnumerationDropdownChoiceRender()));
        isDCProduceOrg.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                Boolean isDCProduceOrgValue = ((Security)BasicInfoStep.this.getDefaultModelObject()).getIsDCProduceOrg();
                dcType.setEnabled(isDCProduceOrgValue);
                target.add(dcType);

            }
        });
        Security s = ((Security)getDefaultModelObject());
        dcType.setOutputMarkupId(true);
        dcType.setEnabled(s != null && s.getIsDCProduceOrg() != null && Boolean.valueOf(s.getIsDCProduceOrg()));
        add(form);
    }

    @Override
    protected String getTabID() {
        return "basic";
    }

    @Override
    protected String getTabContentID() {
        return "basic-tab";
    }

    @Override
    protected ResourceModel getTabTitle() {
        return WicketMessageUtil.getResourceModel("security.form.tab.basic");
    }

    @Override
    public void disablePanel() {
        form.setEnabled(false);
    }

}
