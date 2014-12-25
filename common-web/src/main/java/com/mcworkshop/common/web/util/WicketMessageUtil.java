// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.util;

import java.util.Map;

import org.apache.wicket.Application;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.util.string.interpolator.MapVariableInterpolator;

/**
 * @author $Author$
 * 
 */
public class WicketMessageUtil {
    private WicketMessageUtil() {
    }

    /**
     * @param resourceKey
     *            key of the resource this model represents
     * @return A ResourceModel with key specified.
     */
    public static ResourceModel getResourceModel(String resourceKey) {
        return new ResourceModel(resourceKey);
    }

    /**
     * @param resourceKey
     *            key of the resource this model represents
     * @return A String by key specified.
     */
    public static String getResourceString(String resourceKey) {
        return getResourceModel(resourceKey).getObject();
    }

    /**
     * @param resourceKey
     *            key of the resource this model represents
     * @param parameters
     *            The parameters to substitute using a Java MessageFormat object
     * @return A new string resource model using the supplied parameters
     */
    public static StringResourceModel getStringResourceModel(
            String resourceKey, Object... parameters) {
        return new StringResourceModel(resourceKey, null, parameters);
    }

    /**
     * @param resourceKey
     *            key of the resource this model represents
     * @param parameters
     *            The parameters to substitute using a Java MessageFormat object
     * @return A new string using the supplied parameters
     */
    public static String getResourceString(String resourceKey,
            Object... parameters) {
        return getStringResourceModel(resourceKey, parameters).getObject();
    }

    /**
     * @param resourceKey
     *            The resource key for this string resource
     * @param model
     *            The model to use for property substitutions
     * @return A new string resource model using the supplied parameters
     */
    public static StringResourceModel getStringResourceModel(String messageKey,
            IModel<?> model) {
        return new StringResourceModel(messageKey, model);
    }

    /**
     * @param resourceKey
     *            The resource key for this string resource
     * @param model
     *            The model to use for property substitutions
     * @return A new string using the supplied parameters
     */
    public static String getResourceString(String resourceKey, IModel<?> model) {
        return getStringResourceModel(resourceKey, model).getObject();
    }

    /**
     * @param resourceKey
     *            The resource key for this string resource
     * @param model
     *            The model to use for property substitutions
     * @param parameters
     *            The parameters to substitute using a Java MessageFormat object
     * @return A new string resource model using the supplied parameters
     */
    public static StringResourceModel getStringResourceModel(
            String resourceKey, IModel<?> model, Object... parameters) {
        return new StringResourceModel(resourceKey, model, parameters);
    }

    /**
     * @param resourceKey
     *            The resource key for this string resource
     * @param model
     *            The model to use for property substitutions
     * @param parameters
     *            The parameters to substitute using a Java MessageFormat object
     * @return A new string using the supplied parameters
     */
    public static String getResourceString(String resourceKey, IModel<?> model,
            Object... parameters) {
        return getStringResourceModel(resourceKey, model, parameters)
                .getObject();
    }

    public static String getResourceStringWithVariables(String resourceKey,
            Map<String, Object> vars) {
        return new MapVariableInterpolator(
                WicketMessageUtil.getResourceString(resourceKey), vars,
                Application.get().getResourceSettings()
                        .getThrowExceptionOnMissingResource()).toString();
    }
}
