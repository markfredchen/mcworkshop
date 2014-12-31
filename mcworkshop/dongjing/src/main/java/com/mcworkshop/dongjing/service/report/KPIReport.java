// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.mcworkshop.common.web.report.CellStyleWrapper;
import com.mcworkshop.dongjing.service.report.model.EconomyEntityCount;
import com.mcworkshop.dongjing.service.report.model.EconomyEntityRegAssets;
import com.mcworkshop.dongjing.service.report.model.EconomyEntityTaxData;
import com.mcworkshop.dongjing.service.report.model.KPIReportData;

/**
 * @author $Author$
 * 
 */
public class KPIReport extends DJBaseReport<KPIReportData> {

	public KPIReport(KPIReportData data, int year, int month) {
		super(data, year, month);
		if (data.getYearNewRegAssets() == null) {
			data.setYearNewRegAssets(new EconomyEntityRegAssets());
		}
		if (data.getCompanies() == null) {
			data.setCompanies(new EconomyEntityCount());
		}
		if (data.getCheckedCompanies() == null) {
			data.setCheckedCompanies(new EconomyEntityCount());
		}
		if (data.getMonthNewAddedCompanies() == null) {
			data.setMonthNewAddedCompanies(new EconomyEntityCount());
		}
		if (data.getMonthNewSignedOffCompanies() == null) {
			data.setMonthNewSignedOffCompanies(new EconomyEntityCount());
		}
		if (data.getYearNewAddedCompanies() == null) {
			data.setYearNewAddedCompanies(new EconomyEntityCount());
		}
		if (data.getYearNewSignedOffCompanies() == null) {
			data.setYearNewSignedOffCompanies(new EconomyEntityCount());
		}
		if (data.getMonthSales() == null) {
			data.setMonthSales(new EconomyEntityTaxData());
		}
		if (data.getYearSales() == null) {
			data.setYearSales(new EconomyEntityTaxData());
		}
		if (data.getIncomingTax() == null){
			data.setIncomingTax(new EconomyEntityTaxData());
		}
	}

	@Override
	protected String getReportFileName() {
		return KPIReport.class.getSimpleName() + ".xlsx";
	}

	@Override
	protected void constructReport() {
		Sheet sheet = workbook.createSheet();
		Cell title = sheet.createRow(0).createCell(0);
		title.setCellValue("松江区百颗星私营经济小区税收入库汇总");
		title.setCellStyle(new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().fontSize((short) 14).bold().done());
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));

		createMergedContentCell(new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().fontSize((short) 13).done(), sheet,
				sheet.createRow(1), "A2:K2", 0, year + "年" + month + "月", false);

		createMergedContentCell(titleStyle, sheet, sheet.createRow(2), "H3:K3",
				7, "表    号：松统综1号", false);
		createMergedContentCell(titleStyle, sheet, sheet.createRow(3), "H4:K4",
				7, "制表机关：松江区统计局", false);
		createMergedContentCell(titleStyle, sheet, sheet.createRow(4), "H5:K5",
				7, "文    号：区府办字（96）第32号", false);

		Row row = sheet.createRow(5);
		createMergedContentCell(titleStyle, sheet, row, "A6:F6", 0,
				"填报单位：上海百颗星私营经济开发有限公司（盖章）", false);
		createMergedContentCell(titleStyle, sheet, row, "H6:K6", 7, "有效期限：",
				false);

		Row row1 = sheet.createRow(6);
		Row row2 = sheet.createRow(7);
		Row row3 = sheet.createRow(8);
		Row row4 = sheet.createRow(9);
		Row row5 = sheet.createRow(10);
		Row row6 = sheet.createRow(11);
		Row row7 = sheet.createRow(12);
		Row row8 = sheet.createRow(13);
		Row row9 = sheet.createRow(14);
		Row row10 = sheet.createRow(15);
		Row row11 = sheet.createRow(16);
		Row row12 = sheet.createRow(17);
		Row row13 = sheet.createRow(18);
		Row row14 = sheet.createRow(19);
		Row row15 = sheet.createRow(20);
		Row row16 = sheet.createRow(21);
		Row row17 = sheet.createRow(22);
		Row row18 = sheet.createRow(23);
		Row row19 = sheet.createRow(24);
		Row row20 = sheet.createRow(25);
		Row row21 = sheet.createRow(26);

		createMergedContentCell(contentStyle, sheet, row1, "A7:C7", 0, "指标名称");
		createContentCell(contentStyle, row1, 3, "代码");
		createContentCell(contentStyle, row1, 4, "计量单位");
		createContentCell(contentStyle, row1, 5, "合计");
		createContentCell(contentStyle, row1, 6, "工业");
		createContentCell(contentStyle, row1, 7, "建筑业");
		createContentCell(contentStyle, row1, 8, "商业");
		createContentCell(contentStyle, row1, 9, "社会服务业");
		createContentCell(contentStyle, row1, 10, "房地产");

		createMergedContentCell(contentStyle, sheet, row2, "A8:C8", 0, "甲");
		createContentCell(contentStyle, row2, 3, "乙");
		createContentCell(contentStyle, row2, 4, "丙");
		createContentCell(contentStyle, row2, 5, "1");
		createContentCell(contentStyle, row2, 6, "2");
		createContentCell(contentStyle, row2, 7, "3");
		createContentCell(contentStyle, row2, 8, "4");
		createContentCell(contentStyle, row2, 9, "5");
		createContentCell(contentStyle, row2, 10, "6");

		createMergedContentCell(contentStyle, sheet, row3, "A9:C9", 0,
				"历年累计注册企业数");
		createMergedContentCell(contentStyle, sheet, row4, "A10:C10", 0,
				"工商年检企业数");
		createMergedContentCell(contentStyle, sheet, row5, "A11:C11", 0,
				"本月新增注册企业数");
		createMergedContentCell(contentStyle, sheet, row6, "A12:C12", 0,
				"本年累计新增注册企业数");
		createMergedContentCell(contentStyle, sheet, row7, "A13:C13", 0,
				"本月注销企业数");
		createMergedContentCell(contentStyle, sheet, row8, "A14:C14", 0,
				"本年累计注销企业数");
		createMergedContentCell(contentStyle, sheet, row9, "A15:C15", 0,
				"本年实有注册企业数");
		createMergedContentCell(contentStyle, sheet, row10, "A16:C16", 0,
				"注册资金");
		createMergedContentCell(contentStyle, sheet, row11, "A17:C17", 0,
				"从业人员");
		createMergedContentCell(contentStyle, sheet, row12, "A18:A21", 0,
				"销售或营业收入");
		createMergedContentCell(contentStyle, sheet, row12, "B18:C18", 1,
				"本月生产经营户数");
		createMergedContentCell(contentStyle, sheet, row13, "B19:C19", 1,
				"本月销售(营业)收入");
		createMergedContentCell(contentStyle, sheet, row14, "B20:C20", 1,
				"本年累计销售(营业)收入");
		createMergedContentCell(contentStyle, sheet, row15, "B21:C21", 1,
				"累计增长率");

		createMergedContentCell(contentStyle, sheet, row16, "A22:A27", 0,
				"应交税金");
		createMergedContentCell(contentStyle, sheet, row16, "B22:C22", 1,
				"本月纳税户数");
		createMergedContentCell(contentStyle, sheet, row17, "B23:C23", 1,
				"本月纳税金额");
		createMergedContentCell(contentStyle, sheet, row18, "B24:C24", 1,
				"本月累积纳税");
		createMergedContentCell(contentStyle, sheet, row19, "B25:B26", 1, "其中");
		createContentCell(contentStyle, row19, 2, "企业及个人所得税");
		createContentCell(contentStyle, row20, 2, "扶持资金");
		createMergedContentCell(contentStyle, sheet, row21, "B27:C27", 1,
				"累计增长率");

		createContentCell(contentStyle, row3, 3, "1");
		createContentCell(contentStyle, row4, 3, "2");
		createContentCell(contentStyle, row5, 3, "3");
		createContentCell(contentStyle, row6, 3, "4");
		createContentCell(contentStyle, row7, 3, "5");
		createContentCell(contentStyle, row8, 3, "6");
		createContentCell(contentStyle, row9, 3, "7");
		createContentCell(contentStyle, row10, 3, "8");
		createContentCell(contentStyle, row11, 3, "9");
		createContentCell(contentStyle, row12, 3, "10");
		createContentCell(contentStyle, row13, 3, "11");
		createContentCell(contentStyle, row14, 3, "12");
		createContentCell(contentStyle, row15, 3, "13");
		createContentCell(contentStyle, row16, 3, "14");
		createContentCell(contentStyle, row17, 3, "15");
		createContentCell(contentStyle, row18, 3, "16");
		createContentCell(contentStyle, row19, 3, "17");
		createContentCell(contentStyle, row20, 3, "18");
		createContentCell(contentStyle, row21, 3, "19");

		createContentCell(contentStyle, row3, 4, "户");
		createContentCell(contentStyle, row4, 4, "户");
		createContentCell(contentStyle, row5, 4, "户");
		createContentCell(contentStyle, row6, 4, "户");
		createContentCell(contentStyle, row7, 4, "户");
		createContentCell(contentStyle, row8, 4, "户");
		createContentCell(contentStyle, row9, 4, "户");
		createContentCell(contentStyle, row10, 4, "万元");
		createContentCell(contentStyle, row11, 4, "人");
		createContentCell(contentStyle, row12, 4, "户");
		createContentCell(contentStyle, row13, 4, "万元");
		createContentCell(contentStyle, row14, 4, "万元");
		createContentCell(contentStyle, row15, 4, "%");
		createContentCell(contentStyle, row16, 4, "户");
		createContentCell(contentStyle, row17, 4, "万元");
		createContentCell(contentStyle, row18, 4, "万元");
		createContentCell(contentStyle, row19, 4, "万元");
		createContentCell(contentStyle, row20, 4, "万元");
		createContentCell(contentStyle, row21, 4, "%");

		createFormulaCell(intStyle, row3, 5, "SUM(G9:K9)");
		createFormulaCell(intStyle, row4, 5, "SUM(G10:K10)");
		createFormulaCell(intStyle, row5, 5, "SUM(G11:K11)");
		createFormulaCell(intStyle, row6, 5, "SUM(G12:K12)");
		createFormulaCell(intStyle, row7, 5, "SUM(G13:K13)");
		createFormulaCell(intStyle, row8, 5, "SUM(G14:K14)");
		createFormulaCell(intStyle, row9, 5, "SUM(G15:K15)");
		createFormulaCell(intStyle, row10, 5, "SUM(G16:K16)");
		createFormulaCell(intStyle, row11, 5, "SUM(G17:K17)");
		createFormulaCell(intStyle, row12, 5, "SUM(G18:K18)");
		createFormulaCell(intStyle, row13, 5, "SUM(G19:K19)");
		createFormulaCell(intStyle, row14, 5, "SUM(G20:K20)");
		createFormulaCell(intStyle, row15, 5, "SUM(G21:K21)");
		createFormulaCell(intStyle, row16, 5, "SUM(G22:K22)");
		createFormulaCell(intStyle, row17, 5, "SUM(G23:K23)");
		createFormulaCell(intStyle, row18, 5, "SUM(G24:K24)");
		createFormulaCell(intStyle, row19, 5, "SUM(G25:K25)");
		createFormulaCell(intStyle, row20, 5, "SUM(G26:K26)");
		createFormulaCell(intStyle, row21, 5, "SUM(G27:K27)");

		createContentCell(intStyle, row3, 6, data.getCompanies()
				.getIndustryCount());
		createContentCell(intStyle, row4, 6, data.getCheckedCompanies()
				.getIndustryCount());
		createContentCell(intStyle, row5, 6, data.getMonthNewAddedCompanies()
				.getIndustryCount());
		createContentCell(intStyle, row6, 6, data.getYearNewAddedCompanies()
				.getIndustryCount());
		createContentCell(intStyle, row7, 6, data
				.getMonthNewSignedOffCompanies().getIndustryCount());
		createContentCell(intStyle, row8, 6, data
				.getYearNewSignedOffCompanies().getIndustryCount());
		createContentCell(intStyle, row9, 6, data.getYearNewRegAssets()
				.getIndustry());
		createContentCell(intStyle, row10, 6, data.getAllRegAssets()
				.getIndustry());
		createContentCell(intStyle, row11, 6, "");
		createContentCell(intStyle, row12, 6, data.getOperatedCompanies()
				.getIndustryCount());
		createContentCell(intStyle, row13, 6, data.getMonthSales()
				.getIndustrySales());
		createContentCell(intStyle, row14, 6, data.getYearSales()
				.getIndustrySales());
		createContentCell(intStyle, row15, 6, "");
		createContentCell(intStyle, row16, 6, data.getCheckedCompanies()
				.getIndustryCount());
		createContentCell(intStyle, row17, 6, data.getMonthTax()
				.getIndustryTax());
		createContentCell(intStyle, row18, 6, data.getYearTax()
				.getIndustryTax());
		createContentCell(intStyle, row19, 6, data.getIncomingTax()
				.getIndustryTax());
		createFormulaCell(intStyle, row20, 6, "G25*0.75");
		createContentCell(intStyle, row21, 6, "");

		createContentCell(intStyle, row3, 7, data.getCompanies()
				.getConstructionCount());
		createContentCell(intStyle, row4, 7, data.getCheckedCompanies()
				.getConstructionCount());
		createContentCell(intStyle, row5, 7, data.getMonthNewAddedCompanies()
				.getConstructionCount());
		createContentCell(intStyle, row6, 7, data.getYearNewAddedCompanies()
				.getConstructionCount());
		createContentCell(intStyle, row7, 7, data
				.getMonthNewSignedOffCompanies().getConstructionCount());
		createContentCell(intStyle, row8, 7, data
				.getYearNewSignedOffCompanies().getConstructionCount());
		createContentCell(intStyle, row9, 7, data.getYearNewRegAssets()
				.getConstruction());
		createContentCell(intStyle, row10, 7, data.getAllRegAssets()
				.getConstruction());
		createContentCell(intStyle, row11, 7, "");
		createContentCell(intStyle, row12, 7, data.getOperatedCompanies()
				.getConstructionCount());
		createContentCell(intStyle, row13, 7, data.getMonthSales()
				.getConstructionSales());
		createContentCell(intStyle, row14, 7, data.getYearSales()
				.getConstructionSales());
		createContentCell(intStyle, row15, 7, "");
		createContentCell(intStyle, row16, 7, data.getCheckedTaxCompanies()
				.getConstructionCount());
		createContentCell(intStyle, row17, 7, data.getMonthTax()
				.getConstructionTax());
		createContentCell(intStyle, row18, 7, data.getYearTax()
				.getConstructionTax());
		createContentCell(intStyle, row19, 7, data.getIncomingTax()
				.getConstructionTax());
		createFormulaCell(intStyle, row20, 7, "H25*0.75");
		createContentCell(intStyle, row21, 7, "");

		createContentCell(intStyle, row3, 8, data.getCompanies()
				.getCommercialCount());
		createContentCell(intStyle, row4, 8, data.getCheckedCompanies()
				.getCommercialCount());
		createContentCell(intStyle, row5, 8, data.getMonthNewAddedCompanies()
				.getCommercialCount());
		createContentCell(intStyle, row6, 8, data.getYearNewAddedCompanies()
				.getCommercialCount());
		createContentCell(intStyle, row7, 8, data
				.getMonthNewSignedOffCompanies().getCommercialCount());
		createContentCell(intStyle, row8, 8, data
				.getYearNewSignedOffCompanies().getCommercialCount());
		createContentCell(intStyle, row9, 8, data.getYearNewRegAssets()
				.getCommercial());
		createContentCell(intStyle, row10, 8, data.getAllRegAssets()
				.getCommercial());
		createContentCell(intStyle, row11, 8, "");
		createContentCell(intStyle, row12, 8, data.getOperatedCompanies()
				.getCommercialCount());
		createContentCell(intStyle, row13, 8, data.getMonthSales()
				.getCommercialSales());
		createContentCell(intStyle, row14, 8, data.getYearSales()
				.getCommercialSales());
		createContentCell(intStyle, row15, 8, "");
		createContentCell(intStyle, row16, 8, data.getCheckedTaxCompanies()
				.getCommercialCount());
		createContentCell(intStyle, row17, 8, data.getMonthTax()
				.getCommercialTax());
		createContentCell(intStyle, row18, 8, data.getYearTax()
				.getCommercialTax());
		createContentCell(intStyle, row19, 8, data.getIncomingTax()
				.getCommercialTax());
		createFormulaCell(intStyle, row20, 8, "I25*0.75");
		createContentCell(intStyle, row21, 8, "");

		createContentCell(intStyle, row3, 9, data.getCompanies()
				.getServiceCount());
		createContentCell(intStyle, row4, 9, data.getCheckedCompanies()
				.getServiceCount());
		createContentCell(intStyle, row5, 9, data.getMonthNewAddedCompanies()
				.getServiceCount());
		createContentCell(intStyle, row6, 9, data.getYearNewAddedCompanies()
				.getServiceCount());
		createContentCell(intStyle, row7, 9, data
				.getMonthNewSignedOffCompanies().getServiceCount());
		createContentCell(intStyle, row8, 9, data
				.getYearNewSignedOffCompanies().getServiceCount());
		createContentCell(intStyle, row9, 9, data.getYearNewRegAssets()
				.getService());
		createContentCell(intStyle, row10, 9, data.getAllRegAssets()
				.getService());
		createContentCell(intStyle, row11, 9, "");
		createContentCell(intStyle, row12, 9, data.getOperatedCompanies()
				.getServiceCount());
		createContentCell(intStyle, row13, 9, data.getMonthSales()
				.getServiceSales());
		createContentCell(intStyle, row14, 9, data.getYearSales()
				.getServiceSales());
		createContentCell(intStyle, row15, 9, "");
		createContentCell(intStyle, row16, 9, data.getCheckedTaxCompanies()
				.getServiceCount());
		createContentCell(intStyle, row17, 9, data.getMonthTax()
				.getServiceTax());
		createContentCell(intStyle, row18, 9, data.getYearTax().getServiceTax());
		createContentCell(intStyle, row19, 9, data.getIncomingTax()
				.getServiceTax());
		createFormulaCell(intStyle, row20, 9, "J25*0.75");
		createContentCell(intStyle, row21, 9, "");

		createContentCell(intStyle, row3, 10, data.getCompanies()
				.getHouseHoldingCount());
		createContentCell(intStyle, row4, 10, data.getCheckedCompanies()
				.getHouseHoldingCount());
		createContentCell(intStyle, row5, 10, data.getMonthNewAddedCompanies()
				.getHouseHoldingCount());
		createContentCell(intStyle, row6, 10, data.getYearNewAddedCompanies()
				.getHouseHoldingCount());
		createContentCell(intStyle, row7, 10, data
				.getMonthNewSignedOffCompanies().getHouseHoldingCount());
		createContentCell(intStyle, row8, 10, data
				.getYearNewSignedOffCompanies().getHouseHoldingCount());
		createContentCell(intStyle, row9, 10, data.getYearNewRegAssets()
				.getHouseHolding());
		createContentCell(intStyle, row10, 10, data.getAllRegAssets()
				.getHouseHolding());
		createContentCell(intStyle, row11, 10, "");
		createContentCell(intStyle, row12, 10, data.getOperatedCompanies()
				.getHouseHoldingCount());
		createContentCell(intStyle, row13, 10, data.getMonthSales()
				.getHouseHoldingSales());
		createContentCell(intStyle, row14, 10, data.getYearSales()
				.getHouseHoldingSales());
		createContentCell(intStyle, row15, 10, "");
		createContentCell(intStyle, row16, 10, data.getCheckedTaxCompanies()
				.getHouseHoldingCount());
		createContentCell(intStyle, row17, 10, data.getMonthTax()
				.getHouseHoldingTax());
		createContentCell(intStyle, row18, 10, data.getYearTax()
				.getHouseHoldingTax());
		createContentCell(intStyle, row19, 10, data.getIncomingTax()
				.getHouseHoldingTax());
		createFormulaCell(intStyle, row20, 10, "K25*0.75");
		createContentCell(intStyle, row21, 10, "");

	}

}
