// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.util;

import java.text.DecimalFormat;

/**
 * @author $Author$
 * 
 */
public class NumberUtil {

    public static Double add(Double a, Double b) {
        if (a == null && b == null) {
            return null;
        }
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return new Double(a.doubleValue() + b.doubleValue());
    }

    public static Double add(Double... doubles) {
        Double rnt = null;
        for (Double d : doubles) {
            rnt = add(rnt, d);
        }
        return rnt;
    }

    private static DecimalFormat df = new DecimalFormat("######0.00");

    public static String getDoubleForDisplay(Double d) {

        if (d == null) {
            return "";
        } else if (d == 0) {
            return "";
        } else {
            return df.format(d);
        }
    }
    
    private static DecimalFormat integerFormatter = new DecimalFormat("######0");
    public static Integer addDouble(Integer i, Double d) {
        if(d == null && i == null) {
            return null;
        }else if(d == null && i != null) {
            return i;
        }else if(d != null && i == null) {
            return Integer.valueOf(integerFormatter.format(d));
        }else {
            return i + Integer.valueOf(integerFormatter.format(d));
        }
    }
    
    public static Integer getInteger(Double d) {
        if(d == null) {
            return null;
        }else {
            return Integer.valueOf(integerFormatter.format(d));
        }
    }
    
}
