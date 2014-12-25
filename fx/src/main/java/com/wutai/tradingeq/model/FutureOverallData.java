// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author $Author$
 *
 */
public class FutureOverallData implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date date;
    private double assets;
    public Date getDate() {
        return date;
    }
    public double getAssets() {
        return assets;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setAssets(double assets) {
        this.assets = assets;
    }
    
    
}
