// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.sysenum;

/**
 * @author $Author$
 * 
 */
public interface SysEnum {

    Class<? extends SysEnum> getEnumClass();

    Enum<? extends SysEnum> getEnumValue();

    void setSequence(int sequence);

    int getSequence();

    void setMessageKey(String messageKey);

    String getMessageKey();

}
