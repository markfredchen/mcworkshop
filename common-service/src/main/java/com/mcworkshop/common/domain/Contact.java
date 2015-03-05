// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.domain;

/**
 * @author $Author$
 * 
 */
public class Contact extends DomainObject {

    private static final long serialVersionUID = 3778489937297403122L;

    protected Long            contactID;
    protected String          fullName;
    protected String          phone;
    protected String          cellPhone;
    protected String          emailAddress;

    public Long getContactID() {
        return this.contactID;
    }

    public void setContactID(Long contactID) {
        this.contactID = contactID;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCellPhone() {
        return this.cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
