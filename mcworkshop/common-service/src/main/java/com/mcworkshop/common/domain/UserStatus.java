// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.domain;

import com.mcworkshop.common.sysenum.SysEnum;

/**
 * @author $Author$
 * 
 */
public enum UserStatus implements SysEnum {
    ACTIVE, DEACTIVE, LOCKED_OUT;

    private int    sequence;
    private String messageKey;

    @Override
    public Class<? extends SysEnum> getEnumClass() {
        return UserStatus.class;
    }

    @Override
    public Enum<? extends SysEnum> getEnumValue() {
        return this;
    }

    @Override
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public int getSequence() {
        return this.sequence;
    }

    @Override
    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    @Override
    public String getMessageKey() {
        return this.messageKey;
    }

}
