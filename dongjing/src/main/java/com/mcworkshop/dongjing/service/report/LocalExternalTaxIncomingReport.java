// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report;

import com.mcworkshop.common.configuration.Config;
import com.mcworkshop.common.web.report.BaseReport;
import com.mcworkshop.common.web.report.CellStyleWrapper;
import com.mcworkshop.dongjing.configuration.DJConfigurationKey;
import com.mcworkshop.dongjing.service.report.model.OverallReportData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

/**
 * @author $Author$ 百颗星经济区内外税入库汇总
 * 
 */
public class LocalExternalTaxIncomingReport extends
		BaseReport<OverallReportData> {

	private static final String FILENAME = "CompanyLocalExternalTaxIncomingMonthlyReport.xlsx";

	private int year;
	private int month;

	public LocalExternalTaxIncomingReport(OverallReportData data, int year,
			int month) {
		super(data);
		this.year = year;
		this.month = month;
		prepareDirectory(year, month);
	}

	@Override
	protected void constructReport() {
		DecimalFormat df = new DecimalFormat("######0");
		Sheet sheet = workbook.createSheet();
		CellStyle headerStyle = new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().fontSize((short) 14).bold().done();
		CellStyle tableHeaderStyle = new CellStyleWrapper(workbook)
				.alignCenter().alignMiddle().fontSize((short) 12)
				.setBorder((short) 1).bold().done();
		CellStyle contentStyle = new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().fontSize((short) 12).setBorder((short) 1).done();
		CellStyle unitStyle = new CellStyleWrapper(workbook).alignRight()
				.alignMiddle().done();
		createMergedContentCell(headerStyle, sheet, sheet.createRow(0),
				"B1:I1", 1, "百颗星经济区内外税入库汇总", false);
		createMergedContentCell(headerStyle, sheet, sheet.createRow(1),
				"B2:I2", 1, year + "年" + month + "月", false);
		createContentCell(unitStyle, sheet.createRow(2), 8, "单位:万元");
		Row row1 = sheet.createRow(3);
		Row row2 = sheet.createRow(4);
		Row row3 = sheet.createRow(5);
		Row row4 = sheet.createRow(6);
		Row row5 = sheet.createRow(7);
		Row row6 = sheet.createRow(8);
		Row row7 = sheet.createRow(9);
		Row row8 = sheet.createRow(10);
		Row row9 = sheet.createRow(11);
		row1.setHeightInPoints(30);
		row2.setHeightInPoints(30);
		row3.setHeightInPoints(30);
		row4.setHeightInPoints(30);
		row5.setHeightInPoints(30);
		row6.setHeightInPoints(30);
		row7.setHeightInPoints(30);
		row8.setHeightInPoints(30);
		row9.setHeightInPoints(30);
		createContentCell(tableHeaderStyle, row1, 1, "科目");
		createContentCell(tableHeaderStyle, row1, 2, "增值税");
		createContentCell(tableHeaderStyle, row1, 3, "营业税");
		createContentCell(tableHeaderStyle, row1, 4, "企业所得税");
		createContentCell(tableHeaderStyle, row1, 5, "个人所得税");
		createContentCell(tableHeaderStyle, row1, 6, "城建税");
		createContentCell(tableHeaderStyle, row1, 7, "其他税");
		createContentCell(tableHeaderStyle, row1, 8, "合计");

		createContentCell(contentStyle, row2, 1, "本月内税");
		createContentCell(contentStyle, row2, 2,
				df.format(data.getMonthData().getPrivateCompanyVAT()));
		createContentCell(contentStyle, row2, 3,
				df.format(data.getMonthData().getPrivateCompanyOperateTax()));
		createContentCell(contentStyle, row2, 4,
				df.format(data.getMonthData().getPrivateCompanyIncomingTax()));
		createContentCell(contentStyle, row2, 5, df.format(data.getMonthData()
				.getPrivateCompanyPersonalIncomingTax()));
		createContentCell(contentStyle, row2, 6,
				df.format(data.getMonthData().getPrivateCompanyConstructTax()));
		createContentCell(contentStyle, row2, 7,
				df.format(data.getMonthData().getPrivateCompanyOtherTax()));
		Cell cell1 = row2.createCell(8);
		cell1.setCellStyle(contentStyle);
		cell1.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		cell1.setCellFormula("SUM(C5:H5)");

		createContentCell(tableHeaderStyle, row3, 1, "内税累计");
		createContentCell(contentStyle, row3, 2,
				df.format(data.getYearData().getPrivateCompanyVAT()));
		createContentCell(contentStyle, row3, 3,
				df.format(data.getYearData().getPrivateCompanyOperateTax()));
		createContentCell(contentStyle, row3, 4,
				df.format(data.getYearData().getPrivateCompanyIncomingTax()));
		createContentCell(contentStyle, row3, 5, df.format(data.getYearData()
				.getPrivateCompanyPersonalIncomingTax()));
		createContentCell(contentStyle, row3, 6,
				df.format(data.getYearData().getPrivateCompanyConstructTax()));
		createContentCell(contentStyle, row3, 7,
				df.format(data.getYearData().getPrivateCompanyOtherTax()));
		Cell cell2 = row3.createCell(8);
		cell2.setCellStyle(contentStyle);
		cell2.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		cell2.setCellFormula("SUM(C6:H6)");

		createContentCell(contentStyle, row4, 1, "本月外税");
		createContentCell(contentStyle, row4, 2,
				df.format(data.getMonthData().getPublicCompanyVAT()));
		createContentCell(contentStyle, row4, 3,
				df.format(data.getMonthData().getPublicCompanyOperateTax()));
		createContentCell(contentStyle, row4, 4,
				df.format(data.getMonthData().getPublicCompanyIncomingTax()));
		createContentCell(contentStyle, row4, 5, df.format(data.getMonthData()
				.getPublicCompanyPersonalIncomingTax()));
		createContentCell(contentStyle, row4, 6,
				df.format(data.getMonthData().getPublicCompanyConstructTax()));
		createContentCell(contentStyle, row4, 7,
				df.format(data.getMonthData().getPublicCompanyOtherTax()));
		Cell cell3 = row4.createCell(8);
		cell3.setCellStyle(contentStyle);
		cell3.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		cell3.setCellFormula("SUM(C7:H7)");

		createContentCell(tableHeaderStyle, row5, 1, "外税累计");
		createContentCell(contentStyle, row5, 2,
				df.format(data.getYearData().getPublicCompanyVAT()));
		createContentCell(contentStyle, row5, 3,
				df.format(data.getYearData().getPublicCompanyOperateTax()));
		createContentCell(contentStyle, row5, 4,
				df.format(data.getYearData().getPublicCompanyIncomingTax()));
		createContentCell(contentStyle, row5, 5, df.format(data.getYearData()
				.getPublicCompanyPersonalIncomingTax()));
		createContentCell(contentStyle, row5, 6,
				df.format(data.getYearData().getPublicCompanyConstructTax()));
		createContentCell(contentStyle, row5, 7,
				df.format(data.getYearData().getPublicCompanyOtherTax()));
		Cell cell4 = row5.createCell(8);
		cell4.setCellStyle(contentStyle);
		cell4.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		cell4.setCellFormula("SUM(C8:H8)");

		createContentCell(contentStyle, row6, 1, "本月集体");
		createContentCell(contentStyle, row6, 2,
				df.format(data.getMonthData().getGroupCompanyVAT()));
		createContentCell(contentStyle, row6, 3,
				df.format(data.getMonthData().getGroupCompanyOperateTax()));
		createContentCell(contentStyle, row6, 4,
				df.format(data.getMonthData().getGroupCompanyIncomingTax()));
		createContentCell(contentStyle, row6, 5, df.format(data.getMonthData()
				.getGroupCompanyPersonalIncomingTax()));
		createContentCell(contentStyle, row6, 6,
				df.format(data.getMonthData().getGroupCompanyConstructTax()));
		createContentCell(contentStyle, row6, 7,
				df.format(data.getMonthData().getGroupCompanyOtherTax()));
		Cell cell5 = row6.createCell(8);
		cell5.setCellStyle(contentStyle);
		cell5.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		cell5.setCellFormula("SUM(C9:H9)");

		createContentCell(tableHeaderStyle, row7, 1, "集体累计");
		createContentCell(contentStyle, row7, 2,
				df.format(data.getYearData().getGroupCompanyVAT()));
		createContentCell(contentStyle, row7, 3,
				df.format(data.getYearData().getGroupCompanyOperateTax()));
		createContentCell(contentStyle, row7, 4,
				df.format(data.getYearData().getGroupCompanyIncomingTax()));
		createContentCell(contentStyle, row7, 5, df.format(data.getYearData()
				.getGroupCompanyPersonalIncomingTax()));
		createContentCell(contentStyle, row7, 6,
				df.format(data.getYearData().getGroupCompanyConstructTax()));
		createContentCell(contentStyle, row7, 7,
				df.format(data.getYearData().getGroupCompanyOtherTax()));
		Cell cell6 = row7.createCell(8);
		cell6.setCellStyle(contentStyle);
		cell6.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		cell6.setCellFormula("SUM(C10:H10)");

		createContentCell(contentStyle, row8, 1, "本月合计");
		Cell totalVat = row8.createCell(2);
		totalVat.setCellStyle(contentStyle);
		totalVat.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		totalVat.setCellFormula("SUM(C5+C7+C9)");
		Cell totalOperateTax = row8.createCell(3);
		totalOperateTax.setCellStyle(contentStyle);
		totalOperateTax.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		totalOperateTax.setCellFormula("SUM(D5+D7+D9)");
		Cell totalIncomingTax = row8.createCell(4);
		totalIncomingTax.setCellStyle(contentStyle);
		totalIncomingTax.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		totalIncomingTax.setCellFormula("SUM(E5+E7+E9)");
		Cell totalPersonalIncomingTax = row8.createCell(5);
		totalPersonalIncomingTax.setCellStyle(contentStyle);
		totalPersonalIncomingTax.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		totalPersonalIncomingTax.setCellFormula("SUM(F5+F7+F9)");
		Cell totalConstructTax = row8.createCell(6);
		totalConstructTax.setCellStyle(contentStyle);
		totalConstructTax.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		totalConstructTax.setCellFormula("SUM(G5+G7+G9)");
		Cell totalOtherTax = row8.createCell(7);
		totalOtherTax.setCellStyle(contentStyle);
		totalOtherTax.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		totalOtherTax.setCellFormula("SUM(H5+H7+H9)");
		Cell cell7 = row8.createCell(8);
		cell7.setCellStyle(contentStyle);
		cell7.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		cell7.setCellFormula("SUM(C11:H11)");

		createContentCell(tableHeaderStyle, row9, 1, "三项累计");
		Cell totalYearVat = row9.createCell(2);
		totalYearVat.setCellStyle(contentStyle);
		totalYearVat.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		totalYearVat.setCellFormula("SUM(C6+C8+C10)");
		Cell totalYearOperateTax = row9.createCell(3);
		totalYearOperateTax.setCellStyle(contentStyle);
		totalYearOperateTax.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		totalYearOperateTax.setCellFormula("SUM(D5+D8+D10)");
		Cell totalYearIncomingTax = row9.createCell(4);
		totalYearIncomingTax.setCellStyle(contentStyle);
		totalYearIncomingTax.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		totalYearIncomingTax.setCellFormula("SUM(E6+E8+E10)");
		Cell totalYearPersonalIncomingTax = row9.createCell(5);
		totalYearPersonalIncomingTax.setCellStyle(contentStyle);
		totalYearPersonalIncomingTax.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		totalYearPersonalIncomingTax.setCellFormula("SUM(F6+F8+F10)");
		Cell totalYearConstructTax = row9.createCell(6);
		totalYearConstructTax.setCellStyle(contentStyle);
		totalYearConstructTax.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		totalYearConstructTax.setCellFormula("SUM(G6+G8+G10)");
		Cell totalYearOtherTax = row9.createCell(7);
		totalYearOtherTax.setCellStyle(contentStyle);
		totalYearOtherTax.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		totalYearOtherTax.setCellFormula("SUM(H6+H8+H10)");
		Cell cell8 = row9.createCell(8);
		cell8.setCellStyle(contentStyle);
		cell8.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		cell8.setCellFormula("SUM(C12:H12)");

		RegionUtil.setBorderTop(2, CellRangeAddress.valueOf("B4:I4"), sheet,
				workbook);
		RegionUtil.setBorderBottom(2, CellRangeAddress.valueOf("B4:I4"), sheet,
				workbook);
		RegionUtil.setBorderBottom(2, CellRangeAddress.valueOf("B6:I6"), sheet,
				workbook);
		RegionUtil.setBorderBottom(2, CellRangeAddress.valueOf("B8:I8"), sheet,
				workbook);
		RegionUtil.setBorderBottom(2, CellRangeAddress.valueOf("B10:I10"),
				sheet, workbook);
		RegionUtil.setBorderBottom(2, CellRangeAddress.valueOf("B11:I11"),
				sheet, workbook);
		RegionUtil.setBorderBottom(2, CellRangeAddress.valueOf("B12:I12"),
				sheet, workbook);
		RegionUtil.setBorderRight(2, CellRangeAddress.valueOf("B4:B12"), sheet,
				workbook);
		RegionUtil.setBorderLeft(2, CellRangeAddress.valueOf("B4:B12"), sheet,
				workbook);
		RegionUtil.setBorderRight(2, CellRangeAddress.valueOf("I4:I12"), sheet,
				workbook);
		RegionUtil.setBorderLeft(2, CellRangeAddress.valueOf("I4:I12"), sheet,
				workbook);

		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.autoSizeColumn(4);
		sheet.autoSizeColumn(5);
		sheet.autoSizeColumn(6);
		sheet.autoSizeColumn(7);
		sheet.autoSizeColumn(8);
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

	public static void main(String[] args) throws Throwable {

		LocalExternalTaxIncomingReport report = new LocalExternalTaxIncomingReport(
				new OverallReportData(), 2012, 1);
		File file = new File("/Users/mchen1/MCWorkshop/client/dongjing/test/"
				+ FILENAME);
		report.constructReport();
		report.workbook.write(new FileOutputStream(file));
	}
}
