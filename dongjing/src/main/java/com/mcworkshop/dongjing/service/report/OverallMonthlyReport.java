// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report;

import com.mcworkshop.common.configuration.Config;
import com.mcworkshop.common.web.report.BaseReport;
import com.mcworkshop.common.web.report.CellStyleWrapper;
import com.mcworkshop.common.web.report.ReportUtil;
import com.mcworkshop.dongjing.configuration.DJConfigurationKey;
import com.mcworkshop.dongjing.service.report.model.OverallReportData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author $Author$ 松江经济小区综合情况月报表
 * 
 */
public class OverallMonthlyReport extends BaseReport<OverallReportData> {

	private static final String FILENAME = "CombineStatisticsMonthlyReport.xlsx";

	private int year;
	private int month;

	public OverallMonthlyReport(OverallReportData data, int year, int month) {
		super(data);
		this.year = year;
		this.month = month;
		prepareDirectory(year, month);
	}

	@Override
	protected void constructReport() {
		Sheet sheet = workbook.createSheet();
		Cell title = sheet.createRow(0).createCell(0);
		title.setCellValue("松江经济小区综合情况月报表");
		title.setCellStyle(new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().fontSize((short) 14).bold().done());
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));

		Cell monthSubTitle = sheet.createRow(1).createCell(0);
		monthSubTitle.setCellValue("(" + year + "年" + month + "月）");
		monthSubTitle.setCellStyle(new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().fontSize((short) 13).bold().done());
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 11));
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 11));

		CellStyle headerStyle = new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().setBorder((short) 1).done();
		headerStyle.setRotation((short) 0xff);
		CellStyle contentStyle = new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().setBorder((short) 1).done();

		constructMerchantReport(sheet, headerStyle, contentStyle, 3);
		constructTaxReport(sheet, headerStyle, contentStyle, 11);
		constructSaleData(sheet, headerStyle, contentStyle, 18);
		constructOtherData(sheet, headerStyle, contentStyle, 19);

		ReportUtil.setBorder(2, CellRangeAddress.valueOf("A4:L11"), sheet,
				workbook);
		ReportUtil.setBorder(2, CellRangeAddress.valueOf("A12:L18"), sheet,
				workbook);
		ReportUtil.setBorder(2, CellRangeAddress.valueOf("A19:L19"), sheet,
				workbook);
		ReportUtil.setBorder(2, CellRangeAddress.valueOf("A20:L26"), sheet,
				workbook);

		CellStyle footerStyle = new CellStyleWrapper(workbook).alignLeft()
				.alignMiddle().setBorder((short) 0, false, false, false, false)
				.done();

		createMergedContentCell(footerStyle, sheet, sheet.createRow(26),
				"A27:L27", 0, "填报说明:", false);
		createMergedContentCell(footerStyle, sheet, sheet.createRow(27),
				"A28:L28", 0,
				"         1、本月报表于下月5日之前上报区民营经济发展办公室。地址：松江区荣乐东路145号，邮编：201613,",
				false);
		createMergedContentCell(footerStyle, sheet, sheet.createRow(28),
				"A29:L29", 0, "联系电话：37722747(传真)。", false);
		createMergedContentCell(footerStyle, sheet, sheet.createRow(29),
				"A30:L30", 0,
				"         2、此报表是各经济小区年度目标责任考核内容，是民营办上报和对外公布有关数据的依据，务请逐", false);
		createMergedContentCell(footerStyle, sheet, sheet.createRow(30),
				"A31:L31", 0, "项正确填写，及时上报。", false);
		Row footer1 = sheet.createRow(31);
		createMergedContentCell(footerStyle, sheet, footer1, "A32:E32", 0,
				"填报单位(盖章):", false);
		createMergedContentCell(footerStyle, sheet, footer1, "F32:L32", 5,
				"单位负责人(签章):", false);
		Row footer2 = sheet.createRow(32);
		createMergedContentCell(footerStyle, sheet, footer2, "A33:D33", 0,
				"填表人:", false);
		createMergedContentCell(footerStyle, sheet, footer2, "E33:G33", 4,
				"联系电话:", false);
		createMergedContentCell(footerStyle, sheet, footer2, "H33:L33", 7,
				"填报日期:", false);

		sheet.autoSizeColumn(0);
	}

	private void constructMerchantReport(Sheet sheet, CellStyle headerStyle,
			CellStyle contentStyle, int startRow) {
		Row row1 = sheet.createRow(startRow);
		Row row2 = sheet.createRow(startRow + 1);
		Row row3 = sheet.createRow(startRow + 2);
		Row row4 = sheet.createRow(startRow + 3);
		Row row5 = sheet.createRow(startRow + 4);
		Row row6 = sheet.createRow(startRow + 5);
		Row row7 = sheet.createRow(startRow + 6);
		Row row8 = sheet.createRow(startRow + 7);

		int merchantMonthlyTitleColumn = 2;
		int merchantMonthlyColumn = 3;
		int merchantYearlyTitleColumn = 7;
		int merchantYearlyColumn = 8;
		int regAssetsYearlyTitleColumn = 10;
		int regAssetsYearlyColumn = 11;

		createMergedContentCell(headerStyle, sheet, row1, "A4:A11", 0,
				"招商情况(户)");
		createMergedContentCell(headerStyle, sheet, row1, "B4:B9", 1, "本月招商");
		createContentCell(contentStyle, row1, merchantMonthlyTitleColumn, "小计");
		createContentCell(contentStyle, row2, merchantMonthlyTitleColumn, "实体型");
		createContentCell(contentStyle, row3, merchantMonthlyTitleColumn, "贸易型");
		createContentCell(contentStyle, row4, merchantMonthlyTitleColumn, "服务型");
		createContentCell(contentStyle, row5, merchantMonthlyTitleColumn, "建筑型");
		createContentCell(contentStyle, row6, merchantMonthlyTitleColumn,
				"房地产业");
		Cell merchantSubtotle = row1.createCell(merchantMonthlyColumn);
		merchantSubtotle.setCellStyle(contentStyle);
		merchantSubtotle.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		merchantSubtotle.setCellFormula("SUM(D5:D9)");
		sheet.addMergedRegion(CellRangeAddress.valueOf("D4:E4"));
		createMergedContentCell(contentStyle, sheet, row2, "D5:E5",
				merchantMonthlyColumn, data.getMonthData()
						.getMerchantIndustry());
		createMergedContentCell(contentStyle, sheet, row3, "D6:E6",
				merchantMonthlyColumn, data.getMonthData()
						.getMerchantCommercial());
		createMergedContentCell(contentStyle, sheet, row4, "D7:E7",
				merchantMonthlyColumn, data.getMonthData().getMerchantService());
		createMergedContentCell(contentStyle, sheet, row5, "D8:E8",
				merchantMonthlyColumn, data.getMonthData()
						.getMerchantConstruct());
		createMergedContentCell(contentStyle, sheet, row6, "D9:E9",
				merchantMonthlyColumn, data.getMonthData()
						.getMerchantHouseHolding());
		createMergedContentCell(contentStyle, sheet, row7, "B10:C10", 1,
				"本月实体型投资额(万元)");
		createMergedContentCell(contentStyle, sheet, row7, "D10:E10",
				merchantMonthlyColumn, data.getMonthData()
						.getRegAssetsIndustry());
		createMergedContentCell(contentStyle, sheet, row8, "B11:C11", 1, "注销户数");
		createMergedContentCell(contentStyle, sheet, row8, "D11:E11",
				merchantMonthlyColumn, data.getMonthData()
						.getMerchantSignOffCompanies());

		createMergedContentCell(headerStyle, sheet, row1, "F4:G9", 5, "当年招商");
		createContentCell(contentStyle, row1, merchantYearlyTitleColumn, "小计");
		createContentCell(contentStyle, row2, merchantYearlyTitleColumn, "实体型");
		createContentCell(contentStyle, row3, merchantYearlyTitleColumn, "贸易型");
		createContentCell(contentStyle, row4, merchantYearlyTitleColumn, "服务型");
		createContentCell(contentStyle, row5, merchantYearlyTitleColumn, "建筑型");
		createContentCell(contentStyle, row6, merchantYearlyTitleColumn, "房地产业");
		Cell yearlyMerchantSubtotle = row1.createCell(merchantYearlyColumn);
		yearlyMerchantSubtotle.setCellStyle(contentStyle);
		yearlyMerchantSubtotle.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		yearlyMerchantSubtotle.setCellFormula("SUM(I5:I9)");
		createContentCell(contentStyle, row2, merchantYearlyColumn, data
				.getYearData().getMerchantIndustry());
		createContentCell(contentStyle, row3, merchantYearlyColumn, data
				.getYearData().getMerchantCommercial());
		createContentCell(contentStyle, row4, merchantYearlyColumn, data
				.getYearData().getMerchantService());
		createContentCell(contentStyle, row5, merchantYearlyColumn, data
				.getYearData().getMerchantConstruct());
		createContentCell(contentStyle, row6, merchantYearlyColumn, data
				.getYearData().getMerchantHouseHolding());
		createMergedContentCell(contentStyle, sheet, row7, "F10:H10", 5,
				"当年实体型投资额(万元)");
		createMergedContentCell(contentStyle, sheet, row7, "I10:L10", 8, data
				.getYearData().getRegAssetsIndustry());
		createMergedContentCell(contentStyle, sheet, row8, "F11:H11", 5,
				"当年注销户数累计");
		createMergedContentCell(contentStyle, sheet, row8, "I11:L11", 8, data
				.getYearData().getMerchantSignOffCompanies());

		createMergedContentCell(headerStyle, sheet, row1, "J4:J9", 9, "注册资金");
		createContentCell(contentStyle, row1, regAssetsYearlyTitleColumn, "小计");
		createContentCell(contentStyle, row2, regAssetsYearlyTitleColumn, "实体型");
		createContentCell(contentStyle, row3, regAssetsYearlyTitleColumn, "贸易型");
		createContentCell(contentStyle, row4, regAssetsYearlyTitleColumn, "服务型");
		createContentCell(contentStyle, row5, regAssetsYearlyTitleColumn, "建筑型");
		createContentCell(contentStyle, row6, regAssetsYearlyTitleColumn,
				"房地产业");

		Cell yearlyRegAssetsSubtotle = row1.createCell(regAssetsYearlyColumn);
		yearlyRegAssetsSubtotle.setCellStyle(contentStyle);
		yearlyRegAssetsSubtotle.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		yearlyRegAssetsSubtotle.setCellFormula("SUM(L5:L9)");
		createContentCell(contentStyle, row2, regAssetsYearlyColumn, data
				.getYearData().getRegAssetsIndustry());
		createContentCell(contentStyle, row3, regAssetsYearlyColumn, data
				.getYearData().getRegAssetsCommercial());
		createContentCell(contentStyle, row4, regAssetsYearlyColumn, data
				.getYearData().getRegAssetsService());
		createContentCell(contentStyle, row5, regAssetsYearlyColumn, data
				.getYearData().getRegAssetsConstruct());
		createContentCell(contentStyle, row6, regAssetsYearlyColumn, data
				.getYearData().getRegAssetsHouseholding());
	}

	private void constructTaxReport(Sheet sheet, CellStyle headerStyle,
			CellStyle contentStyle, int startRow) {
		Row row1 = sheet.createRow(startRow);
		Row row2 = sheet.createRow(startRow + 1);
		Row row3 = sheet.createRow(startRow + 2);
		Row row4 = sheet.createRow(startRow + 3);
		Row row5 = sheet.createRow(startRow + 4);
		Row row6 = sheet.createRow(startRow + 5);
		Row row7 = sheet.createRow(startRow + 6);

		int pcMonthlyTitleColumn = 2;
		int pcMonthlyColumn = 3;
		int pcYearlyTitleColumn = 7;
		int pcYearlyColumn = 8;

		createMergedContentCell(headerStyle, sheet, row1, "A12:A18", 0,
				"税收情况(万元)");
		createMergedContentCell(headerStyle, sheet, row1, "B12:B18", 1, "本月实绩");

		createContentCell(contentStyle, row1, pcMonthlyTitleColumn, "私企小计");
		createContentCell(contentStyle, row2, pcMonthlyTitleColumn, "私企增值税");
		createContentCell(contentStyle, row3, pcMonthlyTitleColumn, "私企营业税");
		createContentCell(contentStyle, row4, pcMonthlyTitleColumn, "私企所得税");
		createContentCell(contentStyle, row5, pcMonthlyTitleColumn, "城建税");
		createContentCell(contentStyle, row6, pcMonthlyTitleColumn, "私企其他");
		createContentCell(contentStyle, row7, pcMonthlyTitleColumn, "合计");

		Cell monthlyTaxSubtotal = row1.createCell(pcMonthlyColumn);
		monthlyTaxSubtotal.setCellStyle(contentStyle);
		monthlyTaxSubtotal.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		monthlyTaxSubtotal.setCellFormula("SUM(D13:D17)");
		sheet.addMergedRegion(CellRangeAddress.valueOf("D12:E12"));
		createMergedContentCell(contentStyle, sheet, row2, "D13:E13",
				pcMonthlyColumn, data.getMonthData().getVat());
		createMergedContentCell(contentStyle, sheet, row3, "D14:E14",
				pcMonthlyColumn, data.getMonthData().getOperateTax());
		createMergedContentCell(contentStyle, sheet, row4, "D15:E15",
				pcMonthlyColumn, data.getMonthData().getIncomingTax());
		createMergedContentCell(contentStyle, sheet, row5, "D16:E16",
				pcMonthlyColumn, data.getMonthData().getConstructTax());
		createMergedContentCell(contentStyle, sheet, row6, "D17:E17",
				pcMonthlyColumn, data.getMonthData().getOtherTax());
		createMergedContentCell(contentStyle, sheet, row7, "D18:E18",
				pcMonthlyColumn, "/");

		createMergedContentCell(headerStyle, sheet, row1, "F12:G18", 5, "当年实绩");

		createContentCell(contentStyle, row1, pcYearlyTitleColumn, "私企小计");
		createContentCell(contentStyle, row2, pcYearlyTitleColumn, "私企增值税");
		createContentCell(contentStyle, row3, pcYearlyTitleColumn, "私企营业税");
		createContentCell(contentStyle, row4, pcYearlyTitleColumn, "私企所得税");
		createContentCell(contentStyle, row5, pcYearlyTitleColumn, "城建税");
		createContentCell(contentStyle, row6, pcYearlyTitleColumn, "私企其他");
		createContentCell(contentStyle, row7, pcYearlyTitleColumn, "合计");
		Cell yearlyTaxSubtotal = row1.createCell(pcYearlyColumn);
		yearlyTaxSubtotal.setCellStyle(contentStyle);
		yearlyTaxSubtotal.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		yearlyTaxSubtotal.setCellFormula("SUM(I13:I17)");
		sheet.addMergedRegion(CellRangeAddress.valueOf("I12:L12"));
		createMergedContentCell(contentStyle, sheet, row2, "I13:L13",
				pcYearlyColumn, data.getYearData().getVat());
		createMergedContentCell(contentStyle, sheet, row3, "I14:L14",
				pcYearlyColumn, data.getYearData().getOperateTax());
		createMergedContentCell(contentStyle, sheet, row4, "I15:L15",
				pcYearlyColumn, data.getYearData().getIncomingTax());
		createMergedContentCell(contentStyle, sheet, row5, "I16:L16",
				pcYearlyColumn, data.getYearData().getConstructTax());
		createMergedContentCell(contentStyle, sheet, row6, "I17:L17",
				pcYearlyColumn, data.getYearData().getOtherTax());
		createMergedContentCell(contentStyle, sheet, row7, "I18:L18",
				pcYearlyColumn, "/");
	}

	private void constructSaleData(Sheet sheet, CellStyle headerStyle,
			CellStyle contentStyle, int startRow) {
		Row row = sheet.createRow(startRow);
		createContentCell(contentStyle, row, 0, "销售收入(万元)");
		createMergedContentCell(contentStyle, sheet, row, "B19:C19", 1, "本月小计");
		createMergedContentCell(contentStyle, sheet, row, "D19:E19", 3, data
				.getMonthData().getSalesTotal());
		createMergedContentCell(contentStyle, sheet, row, "F19:H19", 5, "当年合计");
		createMergedContentCell(contentStyle, sheet, row, "I19:L19", 8, data
				.getYearData().getSalesTotal());
	}

	private void constructOtherData(Sheet sheet, CellStyle headerStyle,
			CellStyle contentStyle, int startRow) {
		Row row1 = sheet.createRow(startRow);
		Row row2 = sheet.createRow(startRow + 1);
		Row row3 = sheet.createRow(startRow + 2);
		Row row4 = sheet.createRow(startRow + 3);
		Row row5 = sheet.createRow(startRow + 4);
		Row row6 = sheet.createRow(startRow + 5);
		Row row7 = sheet.createRow(startRow + 6);

		createMergedContentCell(headerStyle, sheet, row1, "A20:A24", 0, "基建情况");
		createMergedContentCell(contentStyle, sheet, row1, "B20:C20", 1,
				"规划总面积(亩)");
		createMergedContentCell(contentStyle, sheet, row2, "B21:C21", 1,
				"基建投资总额(万元)");
		createMergedContentCell(contentStyle, sheet, row3, "B22:C22", 1,
				"在建厂房(幢)");
		createMergedContentCell(contentStyle, sheet, row4, "B23:C23", 1,
				"在建厂房面积(平方米)");
		createMergedContentCell(contentStyle, sheet, row5, "B24:C24", 1,
				"小区已建道路公里数");

		createMergedContentCell(contentStyle, sheet, row1, "D20:E20", 3, "");
		createMergedContentCell(contentStyle, sheet, row2, "D21:E21", 3, "");
		createMergedContentCell(contentStyle, sheet, row3, "D22:E22", 3, "");
		createMergedContentCell(contentStyle, sheet, row4, "D23:E23", 3, "");
		createMergedContentCell(contentStyle, sheet, row5, "D24:E24", 3, "");

		createMergedContentCell(contentStyle, sheet, row1, "F20:H20", 5,
				"已开发面积(亩)");
		createMergedContentCell(contentStyle, sheet, row1, "I20:L20", 8, "");
		createMergedContentCell(contentStyle, sheet, row2, "F21:H21", 5,
				"当月盘活存量资产(万元)");
		createMergedContentCell(contentStyle, sheet, row2, "I21:J21", 8, "/");
		createContentCell(contentStyle, row2, 10, "合计");
		createContentCell(contentStyle, row2, 11, "/");
		createMergedContentCell(contentStyle, sheet, row3, "F22:G22", 5,
				"已建厂房(幢)");
		createContentCell(contentStyle, row3, 7, "");
		createContentCell(contentStyle, row3, 8, "面积");
		createMergedContentCell(contentStyle, sheet, row3, "J22:L22", 9, "/");
		createMergedContentCell(contentStyle, sheet, row4, "F23:F24", 5, "售租");
		createContentCell(contentStyle, row4, 6, "已售");
		createContentCell(contentStyle, row4, 7, "");
		createContentCell(contentStyle, row4, 8, "面积");
		createMergedContentCell(contentStyle, sheet, row4, "J23:L23", 9, "/");
		createContentCell(contentStyle, row5, 6, "已租");
		createContentCell(contentStyle, row5, 7, "");
		createContentCell(contentStyle, row5, 8, "面积");
		createMergedContentCell(contentStyle, sheet, row5, "J24:L24", 9, "/");

		createMergedContentCell(contentStyle, sheet, row6, "A25:A26", 0,
				"吸纳劳动力");
		createMergedContentCell(contentStyle, sheet, row6, "B25:E25", 1,
				"招用本地劳动力(个)");
		createMergedContentCell(contentStyle, sheet, row6, "F25:L25", 5,
				"招用外地劳动力(个)");
		createContentCell(contentStyle, row7, 1, "本月招用");
		createContentCell(contentStyle, row7, 2, "");
		createContentCell(contentStyle, row7, 3, "当年合计");
		createContentCell(contentStyle, row7, 4, "");
		createMergedContentCell(contentStyle, sheet, row7, "F26:G26", 5, "本月招用");
		createContentCell(contentStyle, row7, 7, "");
		createContentCell(contentStyle, row7, 8, "面积");
		createMergedContentCell(contentStyle, sheet, row7, "J26:L26", 9, "/");
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

	public static void main(String[] args) throws Throwable {

		OverallMonthlyReport report = new OverallMonthlyReport(
				new OverallReportData(), 2012, 1);
		File file = new File("/Users/mchen1/MCWorkshop/client/dongjing/test/"
				+ FILENAME);
		report.constructReport();
		report.workbook.write(new FileOutputStream(file));
	}
}
