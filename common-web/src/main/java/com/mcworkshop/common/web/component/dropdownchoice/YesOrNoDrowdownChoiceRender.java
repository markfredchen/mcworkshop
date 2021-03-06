// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component.dropdownchoice;

import org.apache.wicket.markup.html.form.IChoiceRenderer;

import com.mcworkshop.common.web.util.WicketMessageUtil;

/**
 * @author $Author$
 *
 */
public class YesOrNoDrowdownChoiceRender implements IChoiceRenderer<Boolean> {

    private static final long serialVersionUID = 1L;

    private String prefix = "";

    public YesOrNoDrowdownChoiceRender() {
    }

    public YesOrNoDrowdownChoiceRender(String prefix) {
        this.prefix = prefix + ".";
    }

    @Override
    public Object getDisplayValue(Boolean object) {
        return WicketMessageUtil.getResourceString(prefix + "YesOrNoDrowdownChoiceRender." + object.toString().toUpperCase());
    }

    @Override
    public String getIdValue(Boolean object, int index) {
        return index + "" + object.toString().toUpperCase();
    }

}
