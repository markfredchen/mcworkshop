// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.util;

import java.util.List;

import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

/**
 * @author $Author$
 * 
 */
public class WebUtil {

    private WebUtil() {
    }

    public static String getParameter(String name, PageParameters params) {
        StringValue sValue = null;
        if (params != null) {
            sValue = params.get(name);
        }
        if (sValue == null || sValue.isEmpty()) {
            sValue = RequestCycle.get().getRequest().getRequestParameters()
                    .getParameterValue(name);
        }
        if (sValue != null && !sValue.isEmpty()) {
            return sValue.toString();
        }
        return null;
    }

    public static String getParameter(String name) {
        StringValue sValue = RequestCycle.get().getRequest()
                .getRequestParameters().getParameterValue(name);
        if (sValue != null && !sValue.isEmpty()) {
            return sValue.toString();
        }
        return null;
    }

    public static String[] getParameters(String name) {
        List<StringValue> svList = RequestCycle.get().getRequest()
                .getRequestParameters().getParameterValues(name);
        if (svList != null && !svList.isEmpty()) {
            String[] ss = new String[svList.size()];
            for (int i = 0; i < svList.size(); i++) {
                if (!svList.get(i).isEmpty()) {
                    ss[i] = svList.get(i).toString();
                }
            }
            return ss;
        }
        return null;
    }

    public static Integer getParameterAsInteger(String name) {
        StringValue sValue = RequestCycle.get().getRequest()
                .getRequestParameters().getParameterValue(name);
        if (sValue != null && !sValue.isEmpty()) {
            return sValue.toInteger();
        }
        return null;
    }

    public static Long getParameterAsLong(String name) {
        StringValue sValue = RequestCycle.get().getRequest()
                .getRequestParameters().getParameterValue(name);
        if (sValue != null && !sValue.isEmpty()) {
            return sValue.toLongObject();
        }
        return null;
    }

    public static boolean getParameterAsBoolean(String name) {
        StringValue sValue = RequestCycle.get().getRequest()
                .getRequestParameters().getParameterValue(name);
        if (sValue != null && !sValue.isEmpty()) {
            return sValue.toBoolean();
        }
        return false;
    }
}
