// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report;

import com.mcworkshop.common.configuration.Config;
import com.mcworkshop.common.web.report.BaseReport;
import com.mcworkshop.common.web.report.CellStyleWrapper;
import com.mcworkshop.common.web.report.ReportUtil;
import com.mcworkshop.dongjing.configuration.DJConfigurationKey;
import com.mcworkshop.dongjing.domain.TaxData;
import com.mcworkshop.dongjing.service.report.model.RegionTaxDetailData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.wicket.util.file.File;

/**
 * @author $Author$ 百颗星公司内外地方税收明细
 */
public class RegionTaxDetailReport extends BaseReport<RegionTaxDetailData> {
	private static final String FILENAME = "RegionTaxDetailReport.xlsx";

	private int year;
	private int month;

	public RegionTaxDetailReport(RegionTaxDetailData data, int year, int month) {
		super(data);
		this.year = year;
		this.month = month;
		prepareDirectory(year, month);
		if (data.getPublicTax() == null) {
			data.setPublicTax(new TaxData());
		}
	}

	@Override
	protected void constructReport() {
		Sheet sheet = workbook.createSheet();
		Cell title = sheet.createRow(0).createCell(0);
		title.setCellValue("百颗星公司内外地方税收明细");
		title.setCellStyle(new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().fontSize((short) 14).bold().done());
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

		Cell monthSubTitle = sheet.createRow(1).createCell(0);
		monthSubTitle.setCellValue("" + year + "年 1 - " + month + "月");
		monthSubTitle.setCellStyle(new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().bold().done());
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 9));

		Row row = sheet.createRow(2);
		Cell unit = row.createCell(7);
		unit.setCellValue("单位: 万元");
		unit.setCellStyle(new CellStyleWrapper(workbook).alignRight()
				.alignMiddle().fontSize((short) 13).done());
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 7, 9));

		CellStyle contentStyle = new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().setBorder((short) 1).done();
		CellStyle dataStyle = new CellStyleWrapper(workbook).alignRight()
				.alignMiddle().setDataFormat("###0.00").setBorder((short) 1)
				.done();

		Row title1 = sheet.createRow(3);
		Row title2 = sheet.createRow(4);
		createMergedContentCell(contentStyle, sheet, title1, "A4:A5", 0, "序号");
		createMergedContentCell(contentStyle, sheet, title1, "B4:B5", 1,
				"纳税企业名称");
		createMergedContentCell(contentStyle, sheet, title1, "C4:C5", 2, "累计");
		createMergedContentCell(contentStyle, sheet, title1, "D4:J4", 3,
				"其              中");
		createContentCell(contentStyle, title2, 3, "增值税");
		createContentCell(contentStyle, title2, 4, "营业税");
		createContentCell(contentStyle, title2, 5, "企所税");
		createContentCell(contentStyle, title2, 6, "个所税");
		createContentCell(contentStyle, title2, 7, "城建税");
		createContentCell(contentStyle, title2, 8, "印花税");
		createContentCell(contentStyle, title2, 9, "土地使用税");

		Row row1 = sheet.createRow(5);
		Row row2 = sheet.createRow(6);
		Row row3 = sheet.createRow(7);
		Row row4 = sheet.createRow(8);
		Row row5 = sheet.createRow(9);
		Row row6 = sheet.createRow(10);
		Row row7 = sheet.createRow(11);
		Row row8 = sheet.createRow(12);
		createContentCell(contentStyle, row1, 0, "1");
		createContentCell(contentStyle, row3, 0, "2");
		createContentCell(contentStyle, row5, 0, "1+2");
		createContentCell(contentStyle, row8, 0, "3");

		createContentCell(contentStyle, row1, 1, "百颗星总税收");
		createContentCell(contentStyle, row2, 1, "地方税");
		createContentCell(contentStyle, row3, 1, "外资税收");
		createContentCell(contentStyle, row4, 1, "地方税");
		createContentCell(contentStyle, row5, 1, "税收合计");
		createContentCell(contentStyle, row6, 1, "地方税");
		createContentCell(contentStyle, row7, 1, "上年同期");
		createContentCell(contentStyle, row8, 1, "同比增减");

		createFormulaCell(dataStyle, row1, 2, "SUM(D6:J6)");
		createFormulaCell(dataStyle, row2, 2, "SUM(D7:J7)");
		createFormulaCell(dataStyle, row3, 2, "SUM(D8:J8)");
		createFormulaCell(dataStyle, row4, 2, "SUM(D9:J9)");
		createFormulaCell(dataStyle, row5, 2, "SUM(D10:J10)");
		createFormulaCell(dataStyle, row6, 2, "SUM(D11:J11)");
		createFormulaCell(dataStyle, row7, 2, "SUM(D12:J12)");
		createFormulaCell(dataStyle, row8, 2, "C11-C12");

		createContentCell(dataStyle, row1, 3, data.getPrivateTax().getVat());
		createFormulaCell(dataStyle, row2, 3, "D6*0.65");
		createContentCell(dataStyle, row3, 3, data.getPublicTax().getVat());
		createFormulaCell(dataStyle, row4, 3, "D8*0.1625");
		createFormulaCell(dataStyle, row5, 3, "D6+D8");
		createFormulaCell(dataStyle, row6, 3, "D7+D9");
		if (data.getLastPrivateTax() != null && data.getLastPublicTax() != null) {
			createContentCell(dataStyle, row7, 3, data.getLastPublicTax()
					.getVat() + data.getLastPrivateTax().getVat());
		} else {
			createContentCell(dataStyle, row7, 3, 0D);
		}
		createFormulaCell(dataStyle, row8, 3, "D11-D12");

		createContentCell(dataStyle, row1, 4, data.getPrivateTax()
				.getOperateTax());
		createFormulaCell(dataStyle, row2, 4, "E6*0.65");
		createContentCell(dataStyle, row3, 4, data.getPublicTax()
				.getOperateTax());
		createFormulaCell(dataStyle, row4, 4, "E8*0.65");
		createFormulaCell(dataStyle, row5, 4, "E6+E8");
		createFormulaCell(dataStyle, row6, 4, "E7+E9");
		if (data.getLastPrivateTax() != null && data.getLastPublicTax() != null) {
			createContentCell(dataStyle, row7, 4, data.getLastPublicTax()
					.getOperateTax() + data.getLastPrivateTax().getOperateTax());
		} else {
			createContentCell(dataStyle, row7, 4, 0D);
		}
		createFormulaCell(dataStyle, row8, 4, "E11-E12");

		createContentCell(dataStyle, row1, 5, data.getPrivateTax()
				.getTotalCompanyIncomeTax());
		createFormulaCell(dataStyle, row2, 5, "F6*0.2");
		createContentCell(dataStyle, row3, 5, data.getPublicTax()
				.getTotalCompanyIncomeTax());
		createFormulaCell(dataStyle, row4, 5, "F8*0.2");
		createFormulaCell(dataStyle, row5, 5, "F6+F8");
		createFormulaCell(dataStyle, row6, 5, "F7+F9");
		if (data.getLastPrivateTax() != null && data.getLastPublicTax() != null) {
			createContentCell(dataStyle, row7, 5, data.getLastPublicTax()
					.getTotalCompanyIncomeTax()
					+ data.getLastPrivateTax().getTotalCompanyIncomeTax());
		} else {
			createContentCell(dataStyle, row7, 5, 0D);
		}
		createFormulaCell(dataStyle, row8, 5, "F11-F12");

		createContentCell(dataStyle, row1, 6, data.getPrivateTax()
				.getPersonalIncomingTax());
		createFormulaCell(dataStyle, row2, 6, "G6*0.22");
		createContentCell(dataStyle, row3, 6, data.getPublicTax()
				.getPersonalIncomingTax());
		createFormulaCell(dataStyle, row4, 6, "G8*0.22");
		createFormulaCell(dataStyle, row5, 6, "G6+G8");
		createFormulaCell(dataStyle, row6, 6, "G7+G9");
		if (data.getLastPrivateTax() != null && data.getLastPublicTax() != null) {
			createContentCell(dataStyle, row7, 6, data.getLastPublicTax()
					.getPersonalIncomingTax()
					+ data.getLastPrivateTax().getPersonalIncomingTax());
		} else {
			createContentCell(dataStyle, row7, 6, 0D);
		}
		createFormulaCell(dataStyle, row8, 6, "G11-G12");

		createContentCell(dataStyle, row1, 7, data.getPrivateTax()
				.getConstructionTax());
		createFormulaCell(dataStyle, row2, 7, "H6*0.65");
		createContentCell(dataStyle, row3, 7, data.getPublicTax()
				.getConstructionTax());
		createFormulaCell(dataStyle, row4, 7, "H8*0.65");
		createFormulaCell(dataStyle, row5, 7, "H6+H8");
		createFormulaCell(dataStyle, row6, 7, "H7+H9");
		if (data.getLastPrivateTax() != null && data.getLastPublicTax() != null) {
			createContentCell(dataStyle, row7, 7, data.getLastPublicTax()
					.getConstructionTax()
					+ data.getLastPrivateTax().getConstructionTax());
		} else {
			createContentCell(dataStyle, row7, 7, 0D);
		}
		createFormulaCell(dataStyle, row8, 7, "H11-H12");

		createContentCell(dataStyle, row1, 8, data.getPrivateTax()
				.getStampTax());
		createContentCell(dataStyle, row2, 8, data.getPrivateTax()
				.getStampTax());
		createContentCell(dataStyle, row3, 8, data.getPublicTax().getStampTax());
		createContentCell(dataStyle, row4, 8, data.getPublicTax().getStampTax());
		createFormulaCell(dataStyle, row5, 8, "I6+I8");
		createFormulaCell(dataStyle, row6, 8, "I7+I9");
		if (data.getLastPrivateTax() != null && data.getLastPublicTax() != null) {
			if (data.getLastPublicTax().getStampTax() != null
					&& data.getLastPrivateTax().getStampTax() != null) {
				createContentCell(dataStyle, row7, 8, data.getLastPublicTax()
						.getStampTax() + data.getLastPrivateTax().getStampTax());
			} else {
				createContentCell(dataStyle, row7, 8, 0D);
			}
		} else {
			createContentCell(dataStyle, row7, 8, 0D);
		}
		createFormulaCell(dataStyle, row8, 8, "I11-I12");

		createContentCell(dataStyle, row1, 9, data.getPrivateTax()
				.getLandUseTax());
		createFormulaCell(dataStyle, row2, 9, "J6*0.5");
		createContentCell(dataStyle, row3, 9, data.getPublicTax()
				.getLandUseTax());
		createFormulaCell(dataStyle, row4, 9, "J8*0.5");
		createFormulaCell(dataStyle, row5, 9, "J6+J8");
		createFormulaCell(dataStyle, row6, 9, "J7+J9");
		if (data.getLastPrivateTax() != null && data.getLastPublicTax() != null) {
			if (data.getLastPublicTax().getLandUseTax() != null
					&& data.getLastPrivateTax().getLandUseTax() != null) {
				createContentCell(dataStyle, row7, 9, data.getLastPublicTax()
						.getLandUseTax()
						+ data.getLastPrivateTax().getLandUseTax());
			} else {
				createContentCell(dataStyle, row7, 9, 0D);
			}
		} else {
			createContentCell(dataStyle, row7, 9, 0D);
		}
		createFormulaCell(dataStyle, row8, 9, "J11-J12");

		Row b1 = sheet.createRow(13);
		Row b2 = sheet.createRow(14);

		for (int i = 0; i < 10; i++) {
			createContentCell(contentStyle, b1, i, "");
		}
		createContentCell(contentStyle, b1, 1, "总指标");

		for (int i = 0; i < 10; i++) {
			createContentCell(contentStyle, b2, i, "");
		}
		createContentCell(contentStyle, b2, 1, "完成%");

		CellStyle bStyle = new CellStyleWrapper(workbook).alignLeft()
				.alignMiddle().done();
		createMergedContentCell(bStyle, sheet, sheet.createRow(16), "A17:C17",
				0, "注：百颗星税收去除房产部分", false);
		createMergedContentCell(bStyle, sheet, sheet.createRow(17), "A18:C18",
				0, "负责人：", false);

		ReportUtil.setBorder(2, CellRangeAddress.valueOf("A4:J10"), sheet,
				workbook);
		ReportUtil.setBorder(2, CellRangeAddress.valueOf("A11:J12"), sheet,
				workbook);
		ReportUtil.setBorder(2, CellRangeAddress.valueOf("A13:J13"), sheet,
				workbook);
		ReportUtil.setBorder(2, CellRangeAddress.valueOf("A14:J15"), sheet,
				workbook);

		sheet.autoSizeColumn(1);
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

	@Override
	protected String getReportFilePath() {
		String path = Config.getConfig(DJConfigurationKey.REPORT_DIR);
		path += "/" + year + "/";
		path += month + "/";
		path += FILENAME;
		return path;
	}

}
