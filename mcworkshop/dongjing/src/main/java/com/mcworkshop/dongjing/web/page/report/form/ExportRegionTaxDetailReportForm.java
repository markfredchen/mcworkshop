// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.report.form;

import java.util.Calendar;
import java.util.List;

import com.google.inject.Inject;
import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.web.report.BaseReport;
import com.mcworkshop.dongjing.domain.EconomyNature;
import com.mcworkshop.dongjing.persistence.RegionTaxMapper;
import com.mcworkshop.dongjing.service.DJService;
import com.mcworkshop.dongjing.service.report.RegionTaxDetailReport;
import com.mcworkshop.dongjing.service.report.model.RegionTaxDetailData;

/**
 * @author $Author$
 * 
 */
public class ExportRegionTaxDetailReportForm extends
		BaseExportReportForm<RegionTaxDetailData> {

	private static final long serialVersionUID = 1L;

	@Inject
	private RegionTaxMapper mapper;
	@Inject
	private DJService service;

	public ExportRegionTaxDetailReportForm(String id, List<Calendar> months) {
		super(id, "百颗星私营开发区税收月报表.xlsx", months);
	}

	@Override
	protected BaseReport<RegionTaxDetailData> getReport() {
		RegionTaxDetailData data = new RegionTaxDetailData();
		data.setPrivateTax(mapper.getMonthTaxDataByEconmyNature(
				SystemEnumeration.getInstance().getIdByKey(
						EconomyNature.PRIVATE),
				month.getObject().get(Calendar.YEAR),
				month.getObject().get(Calendar.MONTH) + 1));
		data.setPublicTax(mapper.getMonthTaxDataByEconmyNature(
				SystemEnumeration.getInstance().getIdByKey(
						EconomyNature.FOREIGN),
				month.getObject().get(Calendar.YEAR),
				month.getObject().get(Calendar.MONTH) + 1));
		data.setLastPrivateTax(mapper.getMonthTaxDataByEconmyNature(
				SystemEnumeration.getInstance().getIdByKey(
						EconomyNature.PRIVATE),
				month.getObject().get(Calendar.YEAR) - 1, month.getObject()
						.get(Calendar.MONTH) + 1));
		data.setLastPublicTax(mapper.getMonthTaxDataByEconmyNature(
				SystemEnumeration.getInstance().getIdByKey(
						EconomyNature.FOREIGN),
				month.getObject().get(Calendar.YEAR) - 1, month.getObject()
						.get(Calendar.MONTH) + 1));
		return new RegionTaxDetailReport(data, month.getObject().get(
				Calendar.YEAR), month.getObject().get(Calendar.MONTH) + 1);
	}

}