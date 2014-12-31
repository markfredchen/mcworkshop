// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.util;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.util.string.Strings;

/**
 * @author $Author$
 * 
 */
public class ClassModifier extends AttributeModifier {

    private static final long serialVersionUID = 1L;

    public ClassModifier(String attribute, Serializable value) {
        super(attribute, value);
    }

    public static AttributeAppender toggleClass(String className) {
        return new AttributeAppender("class", className) {
            private static final long serialVersionUID = 1L;

            @Override
            protected String newValue(String currentValue,
                    String replacementValue) {
                if (Strings.isEmpty(currentValue)) {
                    return replacementValue;
                }

                if (currentValue.contains(replacementValue)) {
                    currentValue = currentValue.replace(replacementValue, "");
                    currentValue = currentValue.replace("  ", " ");
                    return currentValue;
                } else {
                    return super.newValue(currentValue, replacementValue);
                }
            }
        }.setSeparator(" ");
    }

    public static AttributeAppender addClass(String className) {
        return new AttributeAppender("class", className) {
            private static final long serialVersionUID = 1L;

            @Override
            protected String newValue(String currentValue,
                    String replacementValue) {
                if (Strings.isEmpty(currentValue)) {
                    return replacementValue;
                }

                if (currentValue.contains(replacementValue)) {
                    return currentValue;
                } else {
                    return super.newValue(currentValue, replacementValue);
                }
            }
        }.setSeparator(" ");
    }

    public static AttributeAppender removeClass(String className) {
        return new AttributeAppender("class", className) {
            private static final long serialVersionUID = 1L;

            @Override
            protected String newValue(String currentValue,
                    String replacementValue) {
                if (Strings.isEmpty(currentValue)) {
                    return null;
                }

                if (currentValue.contains(replacementValue)) {
                    currentValue = currentValue.replace(replacementValue, "");
                    currentValue = currentValue.replace("  ", " ");
                }
                return currentValue;
            }
        }.setSeparator(" ");
    }
}
