// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

/**
 * @author $Author$
 * 
 */
public interface SystemEnumerationMapper {

    @Select("SELECT * FROM sysEnums")
    List<Map<String, Object>> getAllSystemEnumeration();
}
