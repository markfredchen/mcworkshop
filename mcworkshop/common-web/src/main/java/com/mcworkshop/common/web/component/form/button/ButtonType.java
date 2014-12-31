// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component.form.button;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;

import com.mcworkshop.common.web.util.ClassModifier;

/**
 * @author $Author$
 * 
 */
public abstract class ButtonType extends Behavior {

    private static final long serialVersionUID = 1L;

    @Override
    public void beforeRender(Component component) {
        super.beforeRender(component);
        component.add(ClassModifier.addClass("btn " + getButtonType()));
    }

    abstract protected String getButtonType();
}
