// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: MockMailManager.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.service.email.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;

import com.mcworkshop.common.configuration.Config;
import com.mcworkshop.dongjing.configuration.DJConfigurationKey;
import com.mcworkshop.dongjing.service.email.Mail;
import com.mcworkshop.dongjing.service.email.MailFactory;
import com.mcworkshop.dongjing.service.email.MailManager;

/**
 * @author $Author: mchen $
 * 
 */
public class MockMailManager implements MailManager {

	private static final Log log = LogFactory.getLog(MockMailManager.class);

	@Override
	public void send(Mail mail) throws EmailException {
		final Email email = MailFactory.getInstance(mail);
		email.getToAddresses().clear();
		email.addTo(Config.getConfig(DJConfigurationKey.EMAIL_MOCK_TO_ADDRESS));

		if (log.isDebugEnabled()) {
			log.debug("Sending email to " + email.getHostName()
					+ "\nEmail To Addresses:" + email.getToAddresses());
		}
		email.send();
		if (log.isDebugEnabled()) {
			log.debug("Mail sent to: " + email.getToAddresses());
		}

	}

}
