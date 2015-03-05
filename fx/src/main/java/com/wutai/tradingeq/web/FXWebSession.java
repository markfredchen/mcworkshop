// Copyright 2013 Active Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.web;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.wutai.tradingeq.web.session.FXPrincipal;

/**
 * @author mchen1
 * @versin $Rev$, $Date$
 * @since 1.0
 */
public class FXWebSession extends WebSession {

    private static final long serialVersionUID = 7731931127365429517L;

    private FXPrincipal principal;
    
    public FXWebSession(Request request) {
        super(request);
    }

    public FXPrincipal getPrincipal() {
        return principal;
    }

    public void setPrincipal(FXPrincipal principal) {
        this.principal = principal;
    }

    public static FXWebSession get(){
        return (FXWebSession)WebSession.get();
    }

    @Override
    public void invalidate() {
        super.invalidate();
    }
}
