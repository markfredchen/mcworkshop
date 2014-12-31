// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.Page;
import org.apache.wicket.authorization.AuthorizationException;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.protocol.http.PageExpiredException;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

import com.mcworkshop.common.exception.ExceptionUtil;

/**
 * @author $Author$
 * 
 */
abstract public class BaseRequestCycleListener extends
        AbstractRequestCycleListener {

	private static final Log log = LogFactory.getLog("ErrorLog");
	
    @Override
    public IRequestHandler onException(RequestCycle cycle, Exception e) {
        log.error(e);
        if (e instanceof PageExpiredException
                || e instanceof AuthorizationException
                || ExceptionUtil.findCause(e, AuthorizationException.class) != null) {
            return new RenderPageRequestHandler(new PageProvider(
                    getAccessDenyPage()));
        }
        return super.onException(cycle, e);
    }

    abstract protected Class<? extends Page> getAccessDenyPage();

    abstract protected Class<? extends Page> getErrorPage();
}
