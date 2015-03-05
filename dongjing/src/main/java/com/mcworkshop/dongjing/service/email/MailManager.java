// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: MailManager.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.service.email;

import org.apache.commons.mail.EmailException;

/**
 * @author $Author: mchen $
 * 
 */
public interface MailManager {
	void send(Mail mail) throws EmailException;
}
