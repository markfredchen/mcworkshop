// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component.form.datetimepicker;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.string.Strings;

import com.mcworkshop.common.web.BaseWebApplication;

/**
 * @author $Author$
 * 
 */
public class DatetimePickerPlugin extends Behavior {

    private static final long    serialVersionUID = 1L;

    private DatetimePickerOption option;

    public DatetimePickerPlugin(DatetimePickerOption option) {
        this.option = option;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterRender(final Component component) {
        super.afterRender(component);

        Response response = component.getResponse();
        response.write("\n<span class=\"add-on glyphicon glyphicon-calendar\"><i class=\"icon-th\"></i></span>");
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        String isRendered = "bootstrap-datetimepicker.js";
        if (!response.wasRendered(isRendered)) {
            response.render(JavaScriptHeaderItem.forUrl(BaseWebApplication
                    .get().getStaticPath()
                    + "/common/js/bootstrap-datetimepicker.js"));
        }
        response.markRendered(isRendered);
        renderI18NHeader(response, option);
        String id = component.getMarkupId();
        response.render(OnDomReadyHeaderItem.forScript("$('#" + id
                + "').datetimepicker(" + option.toJSONString() + ")"));
    }

    public void renderI18NHeader(IHeaderResponse response,
            DatetimePickerOption option) {
        String isRendered = "bootstrap-datetimepicker-i18n.js";
        if (!response.wasRendered(isRendered)) {
            if (!Strings.isEmpty(option.getLanguage())) {
                response.render(JavaScriptHeaderItem.forUrl(BaseWebApplication
                        .get().getStaticPath()
                        + "/common/locales/bootstrap-datetimepicker."
                        + option.getLanguage() + ".js"));
            }
            response.markRendered(isRendered);
        }
    }

}
