// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.system.role;

import com.mcworkshop.common.domain.Right;
import com.mcworkshop.common.domain.Role;
import com.mcworkshop.common.security.Rights;
import com.mcworkshop.common.web.component.validation.FormFieldValidationContainer;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.authorization.Roles;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author $Author$
 */
public class RoleFormPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private Form<Role> form;
    private RequiredTextField<String> name;
    private WebMarkupContainer rightsChoices;
    private CheckGroup<Right> rightsGroup;
    private DropDownChoice<Right> affiliateBlckChoice;

    public RoleFormPanel(String id, CompoundPropertyModel<Role> model) {
        super(id);
        this.setOutputMarkupId(true);
        this.form = new Form<Role>("role-form", model);
        this.form.setOutputMarkupId(true);
        this.form.add(new FeedbackPanel("feedback-panel"));
        this.form.add(new FormFieldValidationContainer("name-feedback-border")
                .add(this.name = new RequiredTextField<String>("name")));
        this.name.add(StringValidator.maximumLength(100));
        this.form.add(new AjaxCheckBox("isAdmin") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(rightsChoices);
            }
        });
        List<Right> rights = Rights.getInstance().getAllRights();
        rights.remove(Rights.getInstance().getRightByName("SYSTEM"));
        this.form.add(rightsChoices = new WebMarkupContainer("rights-choices") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onBeforeRender() {
                Role role = RoleFormPanel.this.form.getModelObject();
                setEnabled(!role.isAdmin());
                super.onBeforeRender();
            }

        });

        rightsChoices.setOutputMarkupId(true);
        rightsGroup = new CheckGroup<Right>("company-module-group", form
                .getModelObject().getRights());
        this.rightsChoices.add(rightsGroup);
        rightsGroup.add(new Check<Right>("cm-change", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.CM_CHANGE))));
        rightsGroup.add(new Check<Right>("cm-view", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.CM_VIEW))));
        rightsGroup.add(new Check<Right>("cm-import", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.CM_IMPORT))));
        rightsGroup.add(new Check<Right>("cm-export", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.CM_EXPORT))));
        rightsGroup.add(affiliateBlckChoice = new DropDownChoice<Right>(
                "affiliate-block",
                getAffiliateBlockRight(form.getModelObject()), Rights
                .getInstance().getAffiliateBlockRights(),
                new IChoiceRenderer<Right>() {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public Object getDisplayValue(Right object) {
                        return WicketMessageUtil.getResourceString("right."
                                + object.getName());
                    }

                    @Override
                    public String getIdValue(Right object, int index) {
                        return index + object.getName();
                    }
                }));
        rightsGroup.add(new Check<Right>("tax-change", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.TAX_CHANGE))));
        rightsGroup.add(new Check<Right>("tax-view", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.TAX_VIEW))));
        rightsGroup.add(new Check<Right>("tax-import", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.TAX_IMPORT))));

        rightsGroup.add(new Check<Right>("security-change", new Model<Right>(
                Rights.getInstance().getRightByName(Roles.SECURITY_CHANGE))));
        rightsGroup.add(new Check<Right>("security-view", new Model<Right>(
                Rights.getInstance().getRightByName(Roles.SECURITY_VIEW))));

        rightsGroup.add(new Check<Right>("ca-change", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.CA_CHANGE))));
        rightsGroup.add(new Check<Right>("ca-view", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.CA_VIEW))));

        rightsGroup.add(new Check<Right>("agri-change", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.AGRI_CHANGE))));
        rightsGroup.add(new Check<Right>("agri-view", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.AGRI_VIEW))));

        rightsGroup.add(new Check<Right>("project-change", new Model<Right>(
                Rights.getInstance().getRightByName(Roles.PM_CHANGE))));
        rightsGroup.add(new Check<Right>("project-view", new Model<Right>(
                Rights.getInstance().getRightByName(Roles.PM_VIEW))));

        rightsGroup.add(new Check<Right>("report-bkx", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.RM_BKX))));
        rightsGroup.add(new Check<Right>("report-overall", new Model<Right>(
                Rights.getInstance().getRightByName(Roles.RM_OVERALL))));

        rightsGroup.add(new Check<Right>("info-sms", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.INFO_SMS))));
        rightsGroup.add(new Check<Right>("info-email", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.INFO_EMAIL))));

        rightsGroup.add(new Check<Right>("menu-jfb", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.JFB))));
        rightsGroup.add(new Check<Right>("menu-czs", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.CZS))));
        rightsGroup.add(new Check<Right>("menu-ajs", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.AJS))));
        rightsGroup.add(new Check<Right>("menu-bkx", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.BKX))));
        rightsGroup.add(new Check<Right>("menu-jgs", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.JGS))));
        rightsGroup.add(new Check<Right>("menu-myc", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.MYC))));
        rightsGroup.add(new Check<Right>("menu-nfgs", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.NFGS))));
        rightsGroup.add(new Check<Right>("menu-zhdw", new Model<Right>(Rights
                .getInstance().getRightByName(Roles.ZHDW))));

        this.add(this.form);
    }

    @Override
    protected void onBeforeRender() {
        Role role = this.form.getModelObject();
        if (role.getRoleID() != null) {
            this.form.setEnabled(role.getRoleID() != 1L);
        }
        rightsGroup.setModelObject(role.getRights());
        affiliateBlckChoice.setModel(getAffiliateBlockRight(role));
        super.onBeforeRender();
    }

    public void setFormModel(CompoundPropertyModel<Role> model) {
        this.form.setDefaultModel(model);
    }

    public Form<Role> getForm() {
        return this.form;
    }

    public void setValue(Form<Role> form) {
        Role role = form.getModelObject();
        if (affiliateBlckChoice.getConvertedInput() != null) {
            role.addRight(affiliateBlckChoice.getConvertedInput());
        }
    }

    public void reset() {
        rightsGroup.setModelObject(new ArrayList<Right>());
        affiliateBlckChoice.setModel(new Model<Right>());
    }

    private Model<Right> getAffiliateBlockRight(Role role) {
        for (Right right : role.getRights()) {
            if (right.getName().startsWith("CM.BLOCK")) {
                return new Model<Right>(right);
            }
        }
        return new Model<Right>();
    }

}
