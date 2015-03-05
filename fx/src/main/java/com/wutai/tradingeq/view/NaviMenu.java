package com.wutai.tradingeq.view;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;

public class NaviMenu implements Serializable{

	private static final long serialVersionUID = 1L;
    private Class<? extends WebPage> pageClass;
	private String messageKey;
	private boolean isCurrent;
	public Class<? extends WebPage> getPageClass() {
		return pageClass;
	}
	public void setPageClass(Class<? extends WebPage> pageClass) {
		this.pageClass = pageClass;
	}
	public String getMessageKey() {
		return messageKey;
	}
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	public boolean isCurrent() {
		return isCurrent;
	}
	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
}
