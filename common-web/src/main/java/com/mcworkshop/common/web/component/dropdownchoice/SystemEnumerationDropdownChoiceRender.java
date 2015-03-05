// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component.dropdownchoice;

import org.apache.wicket.markup.html.form.IChoiceRenderer;

import com.mcworkshop.common.sysenum.SysEnum;
import com.mcworkshop.common.web.util.WicketMessageUtil;

/**
 * @author $Author$
 * 
 */
public class SystemEnumerationDropdownChoiceRender implements
        IChoiceRenderer<SysEnum> {

    private static final long serialVersionUID = 1L;

    @Override
    public Object getDisplayValue(SysEnum object) {
        return WicketMessageUtil.getResourceString(object.getMessageKey());
    }

    @Override
    public String getIdValue(SysEnum object, int index) {
        return index + object.toString();
    }

}
