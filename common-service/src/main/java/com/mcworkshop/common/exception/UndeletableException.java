// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.exception;

import org.apache.ibatis.exceptions.PersistenceException;

import com.mcworkshop.common.domain.DomainObject;

/**
 * @author $Author$
 * 
 */
public class UndeletableException extends PersistenceException {

    private static final long serialVersionUID = 1L;

    public UndeletableException(Class<? extends DomainObject> clazz) {
        super(clazz.getSimpleName());
    }

    public UndeletableException(Class<? extends DomainObject> clazz, Throwable e) {
        super("undeletable." + clazz.getSimpleName(), e);
    }
}
