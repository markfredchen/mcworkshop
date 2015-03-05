// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.examples.web;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import com.mcworkshop.common.web.util.RenderJavaScriptToFooterHeaderResponseDecorator;
import com.mcworkshop.examples.web.avatar.AvatarUploaderPage;
import com.mcworkshop.examples.web.upload.GetAvatarResourceReference;
import com.mcworkshop.examples.web.upload.UploadAvatarResourceReference;

/**
 * @author $Author$
 *
 */
public class ExampleApplication extends WebApplication {

    
    
    @Override
    protected void init() {
        super.init();
        getMarkupSettings().setStripWicketTags(true);
        setHeaderResponseDecorator(new RenderJavaScriptToFooterHeaderResponseDecorator("footer-scripts"));
        mountResource("upload/avatar", new UploadAvatarResourceReference("/Users/mchen1/MCWorkshop/client/examples/avator"));
        mountResource("get/avatar", new GetAvatarResourceReference("/Users/mchen1/MCWorkshop/client/examples/avator"));
    }
    
    

    @Override
    public Class<? extends Page> getHomePage() {
        return AvatarUploaderPage.class;
    }

}
