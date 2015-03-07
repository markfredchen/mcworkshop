// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report;

import com.mcworkshop.common.configuration.Config;
import com.mcworkshop.common.web.report.BaseReport;
import com.mcworkshop.common.web.report.CellStyleWrapper;
import com.mcworkshop.common.web.report.ReportUtil;
import com.mcworkshop.dongjing.configuration.DJConfigurationKey;
import com.mcworkshop.dongjing.service.report.model.EconomyEntityTaxData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;

/**
 * @author $Author$ 百颗星私营开发区税收月报表
 * 
 */
public class TaxMonthEconomyEntityReport extends
		BaseReport<EconomyEntityTaxData> {

	private static final String FILENAME = "TaxMonthEconomyEntityReport.xlsx";

	private int year;
	private int month;

	public TaxMonthEconomyEntityReport(EconomyEntityTaxData data, int year,
			int month) {
		super(data);
		this.year = year;
		this.month = month;
		prepareDirectory(year, month);
	}

	@Override
	protected void constructReport() {
		Sheet sheet = workbook.createSheet();
		Cell title = sheet.createRow(0).createCell(0);
		title.setCellValue("百颗星私营开发区税收月报表");
		title.setCellStyle(new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().fontSize((short) 14).bold().done());
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));

		Cell monthSubTitle = sheet.createRow(1).createCell(0);
		monthSubTitle.setCellValue("" + year + "年" + month + "月");
		monthSubTitle.setCellStyle(new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().fontSize((short) 12).bold().done());
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 12));
		Row row = sheet.createRow(2);
		Cell signature = row.createCell(0);
		signature.setCellValue("填报单位：上海百颗星私营经济开发有限公司(盖章)");
		signature.setCellStyle(new CellStyleWrapper(workbook).alignLeft()
				.alignMiddle().done());
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 6));

		Cell unit = row.createCell(11);
		unit.setCellValue("单位: 户万元");
		unit.setCellStyle(new CellStyleWrapper(workbook).alignRight()
				.alignMiddle().done());
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 11, 12));

		Row row1 = sheet.createRow(3);
		Row row2 = sheet.createRow(4);
		Row row3 = sheet.createRow(5);
		Row row4 = sheet.createRow(6);
		Row row5 = sheet.createRow(7);
		Row row6 = sheet.createRow(8);
		Row row7 = sheet.createRow(9);
		Row row8 = sheet.createRow(10);
		Row row9 = sheet.createRow(11);
		Row row10 = sheet.createRow(12);
		Row row11 = sheet.createRow(13);
		CellStyle contentStyle = new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().setBorder((short) 1).done();
		CellStyle intStyle = new CellStyleWrapper(workbook).alignRight()
				.alignMiddle().setDataFormat("###0").setBorder((short) 1)
				.done();
		CellStyle dataStyle = new CellStyleWrapper(workbook).alignRight()
				.alignMiddle().setDataFormat("###0.00").setBorder((short) 1)
				.done();
		CellStyle percentageContentStyle = new CellStyleWrapper(workbook)
				.alignCenter().alignMiddle().setBorder((short) 1)
				.setDataFormat("0.00%").done();
		createMergedContentCell(contentStyle, sheet, row1, "A4:D4", 0, "户数");
		createMergedContentCell(contentStyle, sheet, row1, "E4:E5", 4,
				"本月销售额或营业收入");
		createMergedContentCell(contentStyle, sheet, row1, "F4:M4", 5, "本月纳税数");
		createContentCell(contentStyle, row2, 0, "项目");
		createContentCell(contentStyle, row2, 1, "注册户数");
		createContentCell(contentStyle, row2, 2, "当月申报数");
		createContentCell(contentStyle, row2, 3, "申报率(%)");
		createContentCell(contentStyle, row2, 5, "小计");
		createContentCell(contentStyle, row2, 6, "增值税");
		createContentCell(contentStyle, row2, 7, "营业税");
		createContentCell(contentStyle, row2, 8, "企业所得税");
		createContentCell(contentStyle, row2, 9, "个人所得税");
		createContentCell(contentStyle, row2, 10, "城建税");
		createContentCell(contentStyle, row2, 11, "其他");
		createContentCell(contentStyle, row2, 12, "合计");

		createContentCell(contentStyle, row3, 0, "实体");
		createContentCell(contentStyle, row4, 0, "商贸");
		createContentCell(contentStyle, row5, 0, "服务型");
		createContentCell(contentStyle, row6, 0, "建筑型");
		createContentCell(contentStyle, row7, 0, "房地产型");
		createContentCell(contentStyle, row8, 0, "当月合计");
		createContentCell(contentStyle, row9, 0, "年度累计");
		createContentCell(contentStyle, row10, 0, "上年同期");
		createContentCell(contentStyle, row11, 0, "增长%");

		createContentCell(intStyle, row3, 1, data.getIndustryCount());
		createContentCell(intStyle, row4, 1, data.getCommercialCount());
		createContentCell(intStyle, row5, 1, data.getServiceCount());
		createContentCell(intStyle, row6, 1, data.getConstructCount());
		createContentCell(intStyle, row7, 1, data.getHouseHoldingCount());
		createContentCell(contentStyle, row8, 1, "");
		createContentCell(contentStyle, row9, 1, "");
		createContentCell(contentStyle, row10, 1, "");
		createContentCell(contentStyle, row11, 1, "");

		createContentCell(intStyle, row3, 2, data.getIndustryMonthCount());
		createContentCell(intStyle, row4, 2, data.getCommercialMonthCount());
		createContentCell(intStyle, row5, 2, data.getServiceMonthCount());
		createContentCell(intStyle, row6, 2, data.getConstructMonthCount());
		createContentCell(intStyle, row7, 2, data.getHouseHoldingMonthCount());
		createContentCell(contentStyle, row8, 2, "");
		createContentCell(contentStyle, row9, 2, "");
		createContentCell(contentStyle, row10, 2, "");
		createContentCell(contentStyle, row11, 2, "");

		createFormulaCell(percentageContentStyle, row3, 3, "C6/B6");
		createFormulaCell(percentageContentStyle, row4, 3, "C7/B7");
		createFormulaCell(percentageContentStyle, row5, 3, "C8/B8");
		createFormulaCell(percentageContentStyle, row6, 3, "C9/B9");
		createFormulaCell(percentageContentStyle, row7, 3, "C10/B10");
		createContentCell(contentStyle, row8, 3, "");
		createContentCell(contentStyle, row9, 3, "");
		createContentCell(contentStyle, row10, 3, "");
		createContentCell(contentStyle, row11, 3, "");

		createContentCell(dataStyle, row3, 4, data.getIndustyData().getSales());
		createContentCell(dataStyle, row4, 4, data.getCommercialData()
				.getSales());
		createContentCell(dataStyle, row5, 4, data.getServiceData().getSales());
		createContentCell(dataStyle, row6, 4, data.getConstructionData()
				.getSales());
		createContentCell(dataStyle, row7, 4, data.getHouseHoldingData()
				.getSales());
		createFormulaCell(dataStyle, row8, 4, "SUM(E6:E10)");
		createContentCell(dataStyle, row9, 4, data.getYearData().getSales());
		createContentCell(dataStyle, row10, 4, data.getLastYearData()
				.getSales());
		createFormulaCell(percentageContentStyle, row11, 4, "(E12-E13)/E13");

		createFormulaCell(dataStyle, row3, 5, "SUM(G6:K6)");
		createFormulaCell(dataStyle, row4, 5, "SUM(G7:K7)");
		createFormulaCell(dataStyle, row5, 5, "SUM(G8:K8)");
		createFormulaCell(dataStyle, row6, 5, "SUM(G9:K9)");
		createFormulaCell(dataStyle, row7, 5, "SUM(G10:K10)");
		createFormulaCell(dataStyle, row8, 5, "SUM(F6:F10)");
		createFormulaCell(dataStyle, row9, 5, "SUM(G12:K12)");
		createFormulaCell(dataStyle, row10, 5, "SUM(G13:K13)");
		createFormulaCell(percentageContentStyle, row11, 5, "(F12-F13)/F13");

		createContentCell(dataStyle, row3, 6, data.getIndustyData().getVat());
		createContentCell(dataStyle, row4, 6, data.getCommercialData().getVat());
		createContentCell(dataStyle, row5, 6, data.getServiceData().getVat());
		createContentCell(dataStyle, row6, 6, data.getConstructionData()
				.getVat());
		createContentCell(dataStyle, row7, 6, data.getHouseHoldingData()
				.getVat());
		createFormulaCell(dataStyle, row8, 6, "SUM(G6:G10)");
		createContentCell(dataStyle, row9, 6, data.getYearData().getVat());
		createContentCell(dataStyle, row10, 6, data.getLastYearData().getVat());
		createFormulaCell(percentageContentStyle, row11, 6, "(G12-G13)/G13");

		createContentCell(dataStyle, row3, 7, data.getIndustyData()
				.getOperateTax());
		createContentCell(dataStyle, row4, 7, data.getCommercialData()
				.getOperateTax());
		createContentCell(dataStyle, row5, 7, data.getServiceData()
				.getOperateTax());
		createContentCell(dataStyle, row6, 7, data.getConstructionData()
				.getOperateTax());
		createContentCell(dataStyle, row7, 7, data.getHouseHoldingData()
				.getOperateTax());
		createFormulaCell(dataStyle, row8, 7, "SUM(H6:H10)");
		createContentCell(dataStyle, row9, 7, data.getYearData()
				.getOperateTax());
		createContentCell(dataStyle, row10, 7, data.getLastYearData()
				.getOperateTax());
		createFormulaCell(percentageContentStyle, row11, 7, "(H12-H13)/H13");

		createContentCell(dataStyle, row3, 8, data.getIndustyData()
				.getTotalCompanyIncomeTax());
		createContentCell(dataStyle, row4, 8, data.getCommercialData()
				.getTotalCompanyIncomeTax());
		createContentCell(dataStyle, row5, 8, data.getServiceData()
				.getTotalCompanyIncomeTax());
		createContentCell(dataStyle, row6, 8, data.getConstructionData()
				.getTotalCompanyIncomeTax());
		createContentCell(dataStyle, row7, 8, data.getHouseHoldingData()
				.getTotalCompanyIncomeTax());
		createFormulaCell(dataStyle, row8, 8, "SUM(I6:I10)");
		createContentCell(dataStyle, row9, 8, data.getYearData()
				.getTotalCompanyIncomeTax());
		createContentCell(dataStyle, row10, 8, data.getLastYearData()
				.getTotalCompanyIncomeTax());
		createFormulaCell(percentageContentStyle, row11, 8, "(I12-I13)/I13");

		createContentCell(dataStyle, row3, 9, data.getIndustyData()
				.getPersonalIncomingTax());
		createContentCell(dataStyle, row4, 9, data.getCommercialData()
				.getPersonalIncomingTax());
		createContentCell(dataStyle, row5, 9, data.getServiceData()
				.getPersonalIncomingTax());
		createContentCell(dataStyle, row6, 9, data.getConstructionData()
				.getPersonalIncomingTax());
		createContentCell(dataStyle, row7, 9, data.getHouseHoldingData()
				.getPersonalIncomingTax());
		createFormulaCell(dataStyle, row8, 9, "SUM(J6:J10)");
		createContentCell(dataStyle, row9, 9, data.getYearData()
				.getPersonalIncomingTax());
		createContentCell(dataStyle, row10, 9, data.getLastYearData()
				.getPersonalIncomingTax());
		createFormulaCell(percentageContentStyle, row11, 9, "(J12-J13)/J13");

		createContentCell(dataStyle, row3, 10, data.getIndustyData()
				.getConstructionTax());
		createContentCell(dataStyle, row4, 10, data.getCommercialData()
				.getConstructionTax());
		createContentCell(dataStyle, row5, 10, data.getServiceData()
				.getConstructionTax());
		createContentCell(dataStyle, row6, 10, data.getConstructionData()
				.getConstructionTax());
		createContentCell(dataStyle, row7, 10, data.getHouseHoldingData()
				.getConstructionTax());
		createFormulaCell(dataStyle, row8, 10, "SUM(K6:K10)");
		createContentCell(dataStyle, row9, 10, data.getYearData()
				.getConstructionTax());
		createContentCell(dataStyle, row10, 10, data.getLastYearData()
				.getConstructionTax());
		createFormulaCell(percentageContentStyle, row11, 10, "(K12-K13)/K13");

		createContentCell(dataStyle, row3, 11, data.getIndustyData()
				.getOtherTax());
		createContentCell(dataStyle, row4, 11, data.getCommercialData()
				.getOtherTax());
		createContentCell(dataStyle, row5, 11, data.getServiceData()
				.getOtherTax());
		createContentCell(dataStyle, row6, 11, data.getConstructionData()
				.getOtherTax());
		createContentCell(dataStyle, row7, 11, data.getHouseHoldingData()
				.getOtherTax());
		createFormulaCell(dataStyle, row8, 11, "SUM(L6:L10)");
		createContentCell(dataStyle, row9, 11, data.getYearData().getOtherTax());
		createContentCell(dataStyle, row10, 11, data.getLastYearData()
				.getOtherTax());
		createFormulaCell(percentageContentStyle, row11, 11, "(L12-L13)/L13");

		createFormulaCell(dataStyle, row3, 12, "SUM(G6:L6)");
		createFormulaCell(dataStyle, row4, 12, "SUM(G7:L7)");
		createFormulaCell(dataStyle, row5, 12, "SUM(G8:L8)");
		createFormulaCell(dataStyle, row6, 12, "SUM(G9:L9)");
		createFormulaCell(dataStyle, row7, 12, "SUM(G10:L10)");
		createFormulaCell(dataStyle, row8, 12, "SUM(M6:M10)");
		createFormulaCell(dataStyle, row9, 12, "SUM(G12:L12)");
		createFormulaCell(dataStyle, row10, 12, "SUM(G13:L13)");
		createFormulaCell(percentageContentStyle, row11, 12, "(M12-M13)/M13");

		Row bRow1 = sheet.createRow(14);
		Row bRow2 = sheet.createRow(15);
		Row bRow3 = sheet.createRow(16);

		createMergedContentCell(contentStyle, sheet, bRow1, "A15:A17", 0, "说明");

		CellStyle bContentStyle = new CellStyleWrapper(workbook).alignLeft()
				.alignMiddle().setBorder((short) 1).done();
		createMergedContentCell(bContentStyle, sheet, bRow1, "B15:M15", 1,
				"其他一项包括河道、印花税、土地增值税等合计");
		createMergedContentCell(bContentStyle, sheet, bRow2, "B16:M16", 1,
				"小计=增值税+营业税+企所税+个所税+城建税");
		createMergedContentCell(bContentStyle, sheet, bRow3, "B17:M17", 1,
				"总计=小计+其他");

		Row bRow = sheet.createRow(18);
		CellStyle bContentStyle1 = new CellStyleWrapper(workbook).alignLeft()
				.alignMiddle().done();
		createContentCell(bContentStyle1, bRow, 1, "负责人：");
		createContentCell(bContentStyle1, bRow, 5, "复核：");
		createContentCell(bContentStyle1, bRow, 8, "报送日期：");

		ReportUtil.setBorder(2, CellRangeAddress.valueOf("A4:M14"), sheet,
				workbook);
		ReportUtil.setBorder(2, CellRangeAddress.valueOf("A15:M17"), sheet,
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
