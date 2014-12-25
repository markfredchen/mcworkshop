// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.service;

import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.mcworkshop.common.persistence.SystemEnumerationMapper;

/**
 * @author $Author$
 * 
 */
public class CommonServiceImpl implements CommonService {

    @Inject
    private SystemEnumerationMapper sysEnumMapper;

    @Override
    public List<Map<String, Object>> getAllSystemEnumeration() {
        return this.sysEnumMapper.getAllSystemEnumeration();
    }

}
