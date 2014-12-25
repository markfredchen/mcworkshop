// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: BaseWebSession.java 54 2014-03-30 16:03:34Z mchen $
package com.mcworkshop.common.web;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class BaseWebSession extends WebSession {

    private static final long serialVersionUID = 1L;

    public BaseWebSession(Request request) {
        super(request);
    }

    public static BaseWebSession get() {
        return (BaseWebSession) WebSession.get();
    }
}
