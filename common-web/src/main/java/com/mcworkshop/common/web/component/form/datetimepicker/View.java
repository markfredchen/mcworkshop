// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component.form.datetimepicker;

/**
 * @author $Author$
 * 
 */
public enum View {
    HOUR("hour"), DAY("day"), MONTH("month"), YEAR("year"), DECADE("decade");

    private String view;

    private View(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }
}
