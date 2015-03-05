// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.domain;

import com.mcworkshop.common.sysenum.SystemEnumeration;

/**
 * @author $Author$
 * 
 */
public class User extends DomainObject {

    private static final long serialVersionUID = 3971464054305578130L;

    protected Long            userID;
    protected String          username;
    protected String          password;
    protected UserStatus      userStatus;
    protected String          fullName;
    protected String          phone;
    protected String          cellPhone;
    protected String          emailAddress;

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getUserStatus() {
        return this.userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public Long getUserStatusID() {
        return SystemEnumeration.getInstance().getIdByKey(this.userStatus);
    }

    public void setUserStatusID(Long userStatusID) {
        this.userStatus = SystemEnumeration.getInstance().getKeyById(
                UserStatus.class, userStatusID);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
