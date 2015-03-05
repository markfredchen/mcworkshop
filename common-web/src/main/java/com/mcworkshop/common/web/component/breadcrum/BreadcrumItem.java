// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component.breadcrum;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;

/**
 * @author $Author$
 * 
 */
public class BreadcrumItem implements Serializable {

    private static final long        serialVersionUID = 1L;

    private Class<? extends WebPage> targetPage;
    private String                   text;
    private String                   icon;
    private boolean                  isLinkable;

    public BreadcrumItem(String text, Class<? extends WebPage> pageClass) {
        this(text, pageClass, true);
    }

    public BreadcrumItem(String text, Class<? extends WebPage> pageClass,
            boolean isLinkable) {
        this.text = text;
        this.targetPage = pageClass;
        this.isLinkable = isLinkable;
    }

    public Class<? extends WebPage> getTargetPage() {
        return targetPage;
    }

    public void setTargetPage(Class<? extends WebPage> targetPage) {
        this.targetPage = targetPage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isLinkable() {
        return isLinkable;
    }

    public void setLinkable(boolean isLinkable) {
        this.isLinkable = isLinkable;
    }
}
