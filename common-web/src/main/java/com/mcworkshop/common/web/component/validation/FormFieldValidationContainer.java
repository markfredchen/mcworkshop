// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component.validation;

import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackCollector;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;

import com.mcworkshop.common.web.util.ClassModifier;

/**
 * @author $Author$
 * 
 */
public class FormFieldValidationContainer extends WebMarkupContainer implements
        IFeedback {

    private static final long serialVersionUID = 1L;

    private FormComponent<?>  cp;

    public FormFieldValidationContainer(String id) {
        super(id);
        setOutputMarkupId(true);
    }

    public FormFieldValidationContainer(String id, FormComponent<?> cp) {
        this(id);
        this.cp = cp;
        add(cp);
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        if (cp != null && !cp.isValid()) {
            add(ClassModifier.addClass("has-error"));
        } else if (cp != null) {
            add(ClassModifier.removeClass("has-error"));
        } else {
            if (new FeedbackCollector().collect(getMessagesFilter()).size() > 0) {
                add(ClassModifier.addClass("has-error"));
            } else {
                add(ClassModifier.removeClass("has-error"));
            }
        }
    }

    protected IFeedbackMessageFilter getMessagesFilter() {
        return new ContainerFeedbackMessageFilter(this);
    }

}
