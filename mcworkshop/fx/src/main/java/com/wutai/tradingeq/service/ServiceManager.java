// Copyright 2013 Active Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.service;

import java.io.InputStream;
import java.util.List;

import com.wutai.tradingeq.domain.Contact;
import com.wutai.tradingeq.model.FutureRealDataReport;
import com.wutai.tradingeq.model.TMBoxRealDataReport;

/**
 * @author mchen1
 * @versin $Rev$, $Date$
 * @since 1.0
 */
public interface ServiceManager {

    void createContact(Contact contact);

    TMBoxRealDataReport loadTMBoxRealDataReport(InputStream inputStream,
            InputStream initialBalanceData);

    FutureRealDataReport loadFutureRealDataReport(InputStream futureRealData,
            InputStream futureOverallData);
    List<Contact> getContacts();
}
