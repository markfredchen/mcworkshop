// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.importdata;

import com.mcworkshop.dongjing.domain.TaxData;
import com.mcworkshop.dongjing.service.DJService;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.wicket.util.string.Strings;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author $Author$
 * 
 */
public class TaxDataXLSHandler extends BaseXLSHandler<TaxData> {

	private DJService service;

	private int year;
	private int month;

	private List<String> failedCompanies = new ArrayList<String>();

	public TaxDataXLSHandler(InputStream input, int year, int month,
			DJService service) throws Exception {
		this.fs = new POIFSFileSystem(input);
		this.year = year;
		this.month = month;
		this.service = service;
		data = new TaxData();
		data.setYear(year);
		data.setMonth(month);
	}

	@Override
	protected void onCell(int thisColumn, String thisStr) {
		if (thisColumn < 0) {
		} else if (thisColumn == 0) {
			data.getCompany().setName(thisStr);
		} else if (thisColumn == 1) {
			data.setIndustry(thisStr);
		} else if (thisColumn == 2) {
			data.setAccSales(getDouble(thisStr));
		} else if (thisColumn == 3) {
			data.setSales(getDouble(thisStr));
		} else if (thisColumn == 4) {
			data.setVat(getDouble(thisStr));
		} else if (thisColumn == 5) {
			data.setOperateTax(getDouble(thisStr));
		} else if (thisColumn == 6) {
			data.setExpenseTax(getDouble(thisStr));
		} else if (thisColumn == 7) {
			data.setDomesticIncomingTax(getDouble(thisStr));
		} else if (thisColumn == 8) {
			data.setForeignIncomingTax(getDouble(thisStr));
		} else if (thisColumn == 9) {
			data.setHousingTax(getDouble(thisStr));
		} else if (thisColumn == 10) {
			data.setStampTax(getDouble(thisStr));
		} else if (thisColumn == 11) {
			data.setTrafficTax(getDouble(thisStr));
		} else if (thisColumn == 12) {
			data.setLandVAT(getDouble(thisStr));
		} else if (thisColumn == 13) {
			data.setLandUseTax(getDouble(thisStr));
		} else if (thisColumn == 14) {
			data.setPersonalIncomingTax(getDouble(thisStr));
		} else if (thisColumn == 15) {
			data.setConstructionTax(getDouble(thisStr));
		} else if (thisColumn == 16) {
			data.setVeichleTax(getDouble(thisStr));
		} else if (thisColumn == 17) {
			data.setRiverTax(getDouble(thisStr));
		} else if (thisColumn == 18) {
			data.setEducationTax(getDouble(thisStr));
		} else if (thisColumn == 19) {
			data.setCultureTax(getDouble(thisStr));
		} else if (thisColumn == 20) {
			data.setOtherTax(getDouble(thisStr));
		}
	}

	@Override
	protected void resetData() {
		data = new TaxData();
		data.setYear(year);
		data.setMonth(month);
	}

	@Override
	protected void importData(TaxData data) {
		try {
			this.service.importCompanySaleTaxData(data, failedCompanies);
		} catch (Throwable e) {

		}
	}

	private Double getDouble(String string) {
		if (!Strings.isEmpty(string)) {
			return Double.valueOf(string);
		} else {
			return null;
		}
	}

	public List<String> getFailedCompanies() {
		return failedCompanies;
	}

	public void setFailedCompanies(List<String> failedCompanies) {
		this.failedCompanies = failedCompanies;
	}
}
