// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

/**
 * @author $Author$
 *
 */
public interface DataImportMapper {
    
    @Insert("INSERT INTO tmbox_real_data (ticket,type,size,item,openTime,closeTime,openPrice,closePrice,profit)"
            +"VALUES(#{ticket}, #{type}, #{size}, #{item}, #{openTime}, #{closeTime}, #{openPrice}, #{closePrice}, #{profit})")
    public void importTMBoxRealData(Map<String, Object> params);
    
    @Delete("DELETE FROM tmbox_real_data")
    public void clearTMBoxRealData();

}
