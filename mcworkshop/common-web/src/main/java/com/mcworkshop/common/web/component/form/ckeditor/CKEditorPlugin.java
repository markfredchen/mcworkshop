// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component.form.ckeditor;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;

import com.mcworkshop.common.web.BaseWebApplication;

/**
 * @author $Author$
 * 
 */
public class CKEditorPlugin extends Behavior {

    private static final long serialVersionUID = 1L;

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        String isRendered = "ckeditor.js";
        if (!response.wasRendered(isRendered)) {
            response.render(JavaScriptHeaderItem.forUrl(BaseWebApplication
                    .get().getStaticPath() + "/common/js/ckeditor/ckeditor.js"));
            response.render(JavaScriptHeaderItem.forUrl(BaseWebApplication
                    .get().getStaticPath()
                    + "/common/js/ckeditor/adapters/jquery.js"));
            response.markRendered(isRendered);
        }
        String id = component.getMarkupId();
        response.render(OnDomReadyHeaderItem.forScript("$('#" + id
                + "').ckeditor()"));
    }

}
