// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.search;

/**
 * @author $Author$
 * 
 */
public enum UserSearchField implements SearchField {
    USER_ID("u.userID"), USERNAME("u.username"), FULLNAME("c.fullName"), EMAILADDRESS(
            "c.emailAddress"), PHONE("c.phone"), CELL_PHONE("c.cellPhone");

    private String value;

    private UserSearchField(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return this.value;
    }

}
