// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: MailManagerImpl.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.service.email.impl;

import com.mcworkshop.dongjing.service.email.Mail;
import com.mcworkshop.dongjing.service.email.MailFactory;
import com.mcworkshop.dongjing.service.email.MailManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;

/**
 * @author $Author: mchen $
 * 
 */
public class MailManagerImpl implements MailManager {

	private static final Log log = LogFactory.getLog(MailManagerImpl.class);

	@Override
	public void send(Mail mail) throws EmailException {

		final Email email = MailFactory.getInstance(mail);

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
