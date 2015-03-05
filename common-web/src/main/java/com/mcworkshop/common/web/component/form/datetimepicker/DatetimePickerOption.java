// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component.form.datetimepicker;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.util.string.Strings;

/**
 * @author $Author$
 * 
 */
public class DatetimePickerOption implements Serializable {

    private static final long serialVersionUID = 1L;

    private SimpleDateFormat  f                = new SimpleDateFormat(
                                                       "MM-dd-yyyy");

    private String            format           = "mm-dd-yyyy";
    private Integer           weekStart;
    private Date              startDate;
    private Date              endDate;
    private String[]          daysOfWeekDisabled;
    private Boolean           autoclose        = true;
    private View              minView;
    private View              maxView;
    private View			  startView;
    private Boolean           todayBtn;
    private Boolean           todayHighlight;
    private String            keyboardNavigation;
    private String            language;
    private Integer           minuteStep;
    private View              viewSelect;
    private Boolean           showMeridian;
    private Date              initialDate;

    public DatetimePickerOption() {

    }

    public DatetimePickerOption(String format) {
        this.format = format;
    }

    public String toJSONString() {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (!Strings.isEmpty(format)) {
            sb.append("format:");
            sb.append("'" + format + "'");
            i++;
        }
        if (weekStart != null) {
            sb.append(needSeparator(i));
            sb.append("weekStart:");
            sb.append(weekStart + "");
            i++;
        }
        if (startDate != null) {
            sb.append(needSeparator(i));
            sb.append("startDate:");
            sb.append("'" + f.format(startDate) + "'");
            i++;
        }
        if (endDate != null) {
            sb.append(needSeparator(i));
            sb.append("endDate:");
            sb.append("'" + f.format(endDate) + "'");
            i++;
        }
        if (daysOfWeekDisabled != null && daysOfWeekDisabled.length != 0) {
            sb.append(needSeparator(i));
            String value = "daysOfWeekDisabled: ";
            value += "[";
            for (String s : daysOfWeekDisabled) {
                value += s;
                value += ",";
            }
            value.substring(0, value.length() - 1);
            sb.append("]");
            i++;
        }
        if (autoclose != null) {
            sb.append(needSeparator(i));
            sb.append("autoclose:");
            sb.append(autoclose + "");
            i++;
        }
        if (minView != null) {
            sb.append(needSeparator(i));
            sb.append("minView:");
            sb.append("'" + minView.getView() + "'");
            i++;
        }
        if (startView != null) {
            sb.append(needSeparator(i));
            sb.append("startView:");
            sb.append("'" + startView.getView() + "'");
            i++;
        }
        if (maxView != null) {
            sb.append(needSeparator(i));
            sb.append("maxView:");
            sb.append("'" + maxView.getView() + "'");
            i++;
        }
        if (todayBtn != null) {
            sb.append(needSeparator(i));
            sb.append("todayBtn:");
            sb.append(todayBtn + "");
            i++;
        }
        if (todayHighlight != null) {
            sb.append(needSeparator(i));
            sb.append("todayHighlight:");
            sb.append(todayHighlight + "");
            i++;
        }
        if (!Strings.isEmpty(keyboardNavigation)) {
            sb.append(needSeparator(i));
            sb.append("keyboardNavigation:");
            sb.append("'" + keyboardNavigation + "'");
            i++;
        }
        if (!Strings.isEmpty(language)) {
            sb.append(needSeparator(i));
            sb.append("language:");
            sb.append("'" + language + "'");
            i++;
        }
        if (minuteStep != null) {
            sb.append(needSeparator(i));
            sb.append("minuteStep:");
            sb.append(minuteStep + "");
            i++;
        }
        if (viewSelect != null) {
            sb.append(needSeparator(i));
            sb.append("viewSelect:");
            sb.append("'" + viewSelect.getView() + "'");
            i++;
        }
        if (initialDate != null) {
            sb.append(needSeparator(i));
            sb.append("initialDate:");
            sb.append("'" + f.format(initialDate) + "'");
            i++;
        }
        sb.append("}");
        return sb.toString();
    }

    public String needSeparator(int index) {
        if (index == 0) {
            return "";
        } else {
            return ",";
        }

    }

    public boolean isEmpty() {
        return this.format == null && this.weekStart == null
                || this.startDate == null && this.endDate == null
                || this.daysOfWeekDisabled == null && this.autoclose == null
                || this.minView == null && this.maxView == null
                || this.todayBtn == null && this.todayHighlight == null
                || this.keyboardNavigation == null && this.language == null
                || this.minuteStep == null && this.viewSelect == null
                || this.showMeridian == null && this.initialDate == null;
    }

    public Integer getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(Integer weekStart) {
        this.weekStart = weekStart;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String[] getDaysOfWeekDisabled() {
        return daysOfWeekDisabled;
    }

    public void setDaysOfWeekDisabled(String[] daysOfWeekDisabled) {
        this.daysOfWeekDisabled = daysOfWeekDisabled;
    }

    public Boolean getAutoclose() {
        return autoclose;
    }

    public void setAutoclose(Boolean autoclose) {
        this.autoclose = autoclose;
    }

    public View getMinView() {
        return minView;
    }

    public void setMinView(View minView) {
        this.minView = minView;
    }

    public View getMaxView() {
        return maxView;
    }

    public void setMaxView(View maxView) {
        this.maxView = maxView;
    }

    public Boolean getTodayBtn() {
        return todayBtn;
    }

    public void setTodayBtn(Boolean todayBtn) {
        this.todayBtn = todayBtn;
    }

    public Boolean getTodayHighlight() {
        return todayHighlight;
    }

    public void setTodayHighlight(Boolean todayHighlight) {
        this.todayHighlight = todayHighlight;
    }

    public String getKeyboardNavigation() {
        return keyboardNavigation;
    }

    public void setKeyboardNavigation(String keyboardNavigation) {
        this.keyboardNavigation = keyboardNavigation;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getMinuteStep() {
        return minuteStep;
    }

    public void setMinuteStep(Integer minuteStep) {
        this.minuteStep = minuteStep;
    }

    public View getViewSelect() {
        return viewSelect;
    }

    public void setViewSelect(View viewSelect) {
        this.viewSelect = viewSelect;
    }

    public Boolean getShowMeridian() {
        return showMeridian;
    }

    public void setShowMeridian(Boolean showMeridian) {
        this.showMeridian = showMeridian;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

	public View getStartView() {
		return startView;
	}

	public void setStartView(View startView) {
		this.startView = startView;
	}

}
