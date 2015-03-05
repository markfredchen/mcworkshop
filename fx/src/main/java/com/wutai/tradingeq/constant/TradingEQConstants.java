// Copyright 2013 Active Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.constant;

/**
 * @author mchen1
 * @versin $Rev$, $Date$
 * @since 1.0
 */
public interface TradingEQConstants {
    
    public static final String CONTEXT_PATH = "fx";

    public static final String STATIC_PATH = "/"+ CONTEXT_PATH + "/static/";
    
    public static final String COMMON_JS_PATH  = STATIC_PATH + "common/js";

    public static final String COMMON_CSS_PATH = STATIC_PATH + "common/css";
    
    public static final String TMBOX_DATA_FILE = "tmbox.csv";
    public static final String TMBOX_DATA_INIT_BALANCE_FILE = "initialBalance.csv";
    public static final String FUTURE_OVERALL_DATA_FILE = "futureOveralData.csv";
    public static final String FUTURE_REAL_DATA_FILE = "futureRealData.csv";
    
    public static final String STRATEGY_DATA_FILE = "strategy.csv";
    public static final String STRATEGY_DATA_INIT_BALANCE_FILE = "initialBalance-strategy.csv";
    
}
