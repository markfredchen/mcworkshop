package com.mcworkshop.dongjing.persistence.po;

import com.mcworkshop.dongjing.domain.AnnualOutput;

/**
 * Created by MarkfredChen on 2014/9/22.
 */
public class AnnualOutputPO extends AnnualOutput {
    private Long companyID;

    public Long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Long companyID) {
        this.companyID = companyID;
    }
}
