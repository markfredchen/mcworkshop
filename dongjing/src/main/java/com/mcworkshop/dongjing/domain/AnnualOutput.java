// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: AnnualOutput.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.domain;

import com.mcworkshop.common.domain.DomainObject;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class AnnualOutput extends DomainObject {

	private static final long serialVersionUID = 1L;

	private Long outputID;
	private Integer year;
	private Double output;
	private Double profit;

	public Long getOutputID() {
		return outputID;
	}

	public void setOutputID(Long outputID) {
		this.outputID = outputID;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getOutput() {
		return output;
	}

	public void setOutput(Double output) {
		this.output = output;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

}
