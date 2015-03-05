// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.util;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

/**
 * @author $Author$
 * 
 */
public class FeedbackMessageUtil {
    public static void clearFeedbackMessage(Form<?> component) {
        component.visitChildren(new IVisitor<Component, Void>() {

            @Override
            public void component(Component object, IVisit<Void> visit) {
                if (object.hasFeedbackMessage()) {
                    object.getFeedbackMessages().clear();
                }
            }
        });
    }
}
