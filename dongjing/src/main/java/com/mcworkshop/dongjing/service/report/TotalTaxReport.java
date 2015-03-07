// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report;

import com.mcworkshop.common.configuration.Config;
import com.mcworkshop.common.web.report.BaseReport;
import com.mcworkshop.common.web.report.CellStyleWrapper;
import com.mcworkshop.common.web.report.ReportUtil;
import com.mcworkshop.dongjing.configuration.DJConfigurationKey;
import com.mcworkshop.dongjing.domain.TaxData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.wicket.util.file.File;

/**
 * @author $Author$ 松江区百颗星私营经济小区税收入库汇总
 * 
 */
public class TotalTaxReport extends BaseReport<TaxData> {

	private static final String FILENAME = "TotalTaxReport.xlsx";

	private int year;
	private int month;

	public TotalTaxReport(TaxData data, int year, int month) {
		super(data);
		this.year = year;
		this.month = month;
		prepareDirectory(year, month);
	}

	@Override
	protected void constructReport() {
		Sheet sheet = workbook.createSheet();
		Cell title = sheet.createRow(0).createCell(1);
		title.setCellValue("松江区百颗星私营经济小区税收入库汇总");
		title.setCellStyle(new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().fontSize((short) 14).bold().done());
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 8));

		Cell monthSubTitle = sheet.createRow(1).createCell(1);
		monthSubTitle.setCellValue("" + year + "年" + month + "月");
		monthSubTitle.setCellStyle(new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().fontSize((short) 13).bold().done());
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 8));

		Row row = sheet.createRow(4);
		Cell signature = row.createCell(1);
		signature.setCellValue("________________税务所(章)");
		signature.setCellStyle(new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().done());
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 1, 4));

		Cell unit = row.createCell(7);
		unit.setCellValue("单位: 元");
		unit.setCellStyle(new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().done());
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 7, 8));

		Row row1 = sheet.createRow(5);
		Row row2 = sheet.createRow(6);
		CellStyle contentStyle = new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().setBorder((short) 1).done();
		createContentCell(contentStyle, row1, 1, "科目");
		createContentCell(contentStyle, row1, 2, "增值税 100%");
		createContentCell(contentStyle, row1, 3, "营业税");
		createContentCell(contentStyle, row1, 4, "企业所得税");
		createContentCell(contentStyle, row1, 5, "个人所得税");
		createContentCell(contentStyle, row1, 6, "城建税");
		createContentCell(contentStyle, row1, 7, "其他");
		createContentCell(contentStyle, row1, 8, "合计");

		createContentCell(contentStyle, row2, 1, "金额");
		createContentCell(contentStyle, row2, 2, data.getVat());
		createContentCell(contentStyle, row2, 3, data.getOperateTax());
		createContentCell(contentStyle, row2, 4,
				data.getTotalCompanyIncomeTax());
		createContentCell(contentStyle, row2, 5, data.getPersonalIncomingTax());
		createContentCell(contentStyle, row2, 6, data.getConstructionTax());
		createContentCell(contentStyle, row2, 7, data.getOtherTax());
		createFormulaCell(contentStyle, row2, 8, "SUM(C7:H7)");

		Row row3 = sheet.createRow(7);
		CellStyle bStyle = new CellStyleWrapper(workbook).alignLeft()
				.alignMiddle().done();
		createContentCell(bStyle, row3, 1, "负责人：");
		createContentCell(bStyle, row3, 5, "专管员：");

		ReportUtil.setBorder(2, CellRangeAddress.valueOf("B6:I7"), sheet,
				workbook);
	}

	@Override
	protected String getReportFilePath() {
		String path = Config.getConfig(DJConfigurationKey.REPORT_DIR);
		path += "/" + year + "/";
		path += month + "/";
		path += FILENAME;
		return path;
	}

	private void prepareDirectory(int year, int month) {
		StringBuilder outputDirectoryPath = new StringBuilder();
		outputDirectoryPath.append(Config
				.getConfig(DJConfigurationKey.REPORT_DIR));
		outputDirectoryPath.append("/");
		outputDirectoryPath.append(year);
		outputDirectoryPath.append("/");
		outputDirectoryPath.append(month);
		File outputDirectory = new File(outputDirectoryPath.toString());
		outputDirectory.mkdirs();
	}

}
