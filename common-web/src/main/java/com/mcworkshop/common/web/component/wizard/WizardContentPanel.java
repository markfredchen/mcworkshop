// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component.wizard;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import com.mcworkshop.common.domain.DomainObject;

/**
 * @author $Author$
 * @param <T>
 * 
 */
public abstract class WizardContentPanel<T extends DomainObject> extends Panel {

    private static final long   serialVersionUID = 1L;

    private static final String ID               = "tab-content-panel";

    private int                 sequence;

    protected Form<T>           form;

    public WizardContentPanel(IModel<T> model) {
        super(ID, model);
        setOutputMarkupId(true);
    }

    abstract protected String getTabID();

    abstract protected String getTabContentID();

    abstract protected ResourceModel getTabTitle();

    public Form<T> getForm() {
        return form;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof WizardContentPanel)) {
            return false;
        }
        WizardContentPanel<T> panel = (WizardContentPanel<T>) obj;
        return this.getTabID().equals(panel.getTabID());
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    abstract public void disablePanel();
}
