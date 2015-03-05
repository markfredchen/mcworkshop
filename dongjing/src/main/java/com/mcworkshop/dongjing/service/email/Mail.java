// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: Mail.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.service.email;

import java.util.LinkedHashMap;

/**
 * @author $Author: mchen $
 * 
 */
public class Mail {

	private String template = null;

	private String subject = null;

	private LinkedHashMap<String, String> toMap = new LinkedHashMap<String, String>();

	public Mail(String subject, String template) {
		this.subject = subject;
		this.template = template;
	}

	public void addTo(String toEmail) {
		toMap.put(toEmail, null);
	}

	public void addTo(String toEmail, String toName) {
		toMap.put(toEmail, toName);
	}

	public LinkedHashMap<String, String> getTo() {
		return toMap;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getTemplate() {
		return template;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}
}
