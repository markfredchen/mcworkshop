// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.service.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.Strings;
import org.json.JSONObject;

import com.mcworkshop.common.web.util.WebUtil;

/**
 * @author $Author$
 * 
 */
abstract public class JSONServicePage extends WebPage {
    private static final long     serialVersionUID   = 1L;

    private static final String   CONTENT_TYPE_JSON  = "application/json";

    private static final String   CONTENT_TYPE_JS    = "text/javascript";

    private static final String   ENCODING           = "UTF-8";

    protected static final String NOT_AVAILABLE      = "N/A";

    private static final String   PARAM_KEY_CALLBACK = "callback";

    public JSONServicePage(PageParameters params) {
        JSONObject json = getResponseBody(params);

        String callback = WebUtil.getParameter(PARAM_KEY_CALLBACK);

        IRequestHandler t = null;
        if (Strings.isEmpty(callback)) {
            t = new TextRequestHandler(CONTENT_TYPE_JSON, ENCODING,
                    json.toString());
        } else {
            t = new TextRequestHandler(CONTENT_TYPE_JS, ENCODING, buildScript(
                    json, callback));
        }

        getRequestCycle().scheduleRequestHandlerAfterCurrent(t);
    }

    public abstract JSONObject getResponseBody(PageParameters params);

    protected String buildScript(JSONObject json, String callback) {
        StringBuilder builder = new StringBuilder(callback);
        builder.append("(");
        builder.append(json.toString());
        builder.append(");");

        return builder.toString();
    }
}
