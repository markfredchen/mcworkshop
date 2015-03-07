// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: MailFactory.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.service.email;

import com.mcworkshop.common.configuration.Config;
import com.mcworkshop.dongjing.configuration.DJConfigurationKey;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.Map;

/**
 * @author $Author: mchen $
 * 
 */
public class MailFactory {

	private static final Log log = LogFactory.getLog(MailFactory.class);

	public static Email getInstance(Mail source) {
		HtmlEmail email = new HtmlEmail();
		init(email);
		initTo(email, source);
		initData(email, source);
		email.setSubject(source.getSubject());

		return email;
	}

	private static void initData(HtmlEmail email, Mail source) {
		try {
			email.setHtmlMsg(source.getTemplate());
		} catch (EmailException e) {
			log.error("Error setting email html msg", e);
		}
	}

	private static void initTo(HtmlEmail email, Mail source) {
		for (Map.Entry<String, String> entry : source.getTo().entrySet()) {
			if (entry.getValue() == null) {
				try {
					email.addTo(entry.getKey());
				} catch (EmailException e) {
					log.error("Error adding To, key=" + entry.getKey()
							+ ", value=" + entry.getValue(), e);
					continue;
				}
			} else {
				try {
					email.addTo(entry.getKey(), entry.getValue());
				} catch (EmailException e) {
					log.error("Error adding To, key=" + entry.getKey()
							+ ", value=" + entry.getValue(), e);
					continue;
				}
			}
		}
	}

	private static void init(HtmlEmail email) {
		email.setHostName(Config.getConfig(DJConfigurationKey.EMAIL_HOST));
		String fromAddress = Config
				.getConfig(DJConfigurationKey.EMAIL_FROM_ADDRESS);
		String fromName = Config.getConfig(DJConfigurationKey.EMAIL_FROM_NAME);
		try {
			email.setFrom(fromAddress, fromName);
			email.setSSLOnConnect(true);
			email.setSmtpPort(994);
			email.setAuthentication("markfredchen@163.com", "Markfred23");
		} catch (EmailException e) {
			log.error("Set Email From failed. \nFrom Address: " + fromAddress
					+ "\nFromName: " + fromName, e);
		}
	}

}
