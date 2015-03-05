// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.exception;

import org.apache.ibatis.exceptions.PersistenceException;

import com.mcworkshop.common.domain.DomainObject;

/**
 * @author $Author$
 * 
 */
public class DuplicateDomainResourceException extends PersistenceException {

    private static final long serialVersionUID = 1L;

    public DuplicateDomainResourceException(String domainIdentifier,
            Class<? extends DomainObject> clazz) {
        this(domainIdentifier, clazz, null);
    }

    public DuplicateDomainResourceException(String domainIdentifier,
            Class<? extends DomainObject> clazz, Throwable e) {
        super("duplicate." + clazz.getSimpleName() + "." + domainIdentifier, e);
    }

}
