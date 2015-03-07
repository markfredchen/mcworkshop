// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report;

import com.mcworkshop.common.web.report.CellStyleWrapper;
import com.mcworkshop.dongjing.service.report.model.AffiliationRegionSales;
import com.mcworkshop.dongjing.service.report.model.AffiliationRegionTaxData;
import com.mcworkshop.dongjing.service.report.model.EconomyEntityTaxData;
import com.mcworkshop.dongjing.service.report.model.KPIReportData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author $Author$
 * 
 */
public class CombinedKPIReport extends DJBaseReport<KPIReportData> {

	public CombinedKPIReport(KPIReportData data, int year, int month) {
		super(data, year, month);
		if (data.getLastYearSales() == null) {
			data.setLastYearSales(new EconomyEntityTaxData());
		}
		if (data.getLastYearSalesAF() == null) {
			data.setLastYearSalesAF(new AffiliationRegionSales());
		}
		if (data.getLastYearAFTax() == null) {
			data.setLastYearAFTax(new AffiliationRegionTaxData());
		}
		if (data.getYearSales() == null) {
			data.setYearSales(new EconomyEntityTaxData());
		}
		if (data.getLastYearTax() == null) {
			data.setLastYearTax(new EconomyEntityTaxData());
		}
		if (data.getYearSalesAF() == null) {
			data.setYearSalesAF(new AffiliationRegionSales());
		}
		if (data.getLastYearSalesAF() == null) {
			data.setLastYearSalesAF(new AffiliationRegionSales());
		}

	}

	@Override
	protected String getReportFileName() {
		return CombinedKPIReport.class.getSimpleName();
	}

	@Override
	protected void constructReport() {
		Sheet sheet = workbook.createSheet();

		createMergedContentCell(new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().fontSize((short) 14).done(), sheet,
				sheet.createRow(0), "A1:L1", 0, "洞泾镇百颗星私营经济区企业注册经营情况", false);
		createMergedContentCell(new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().fontSize((short) 13).done(), sheet,
				sheet.createRow(1), "A2:L2", 0, year + "年" + month + "月", false);

		Row titleRow1 = sheet.createRow(3);
		Row titleRow2 = sheet.createRow(4);

		createMergedContentCell(contentStyle, sheet, titleRow1, "A4:A5", 0,
				"指标名称");
		createMergedContentCell(contentStyle, sheet, titleRow1, "B4:B5", 1,
				"计量单位");
		createMergedContentCell(contentStyle, sheet, titleRow1, "C4:H4", 2,
				"按行业分");
		createContentCell(contentStyle, titleRow2, 2, "合计");
		createContentCell(contentStyle, titleRow2, 3, "工业");
		createContentCell(contentStyle, titleRow2, 4, "建筑业");
		createContentCell(contentStyle, titleRow2, 5, "房地产业");
		createContentCell(contentStyle, titleRow2, 6, "商贸业");
		createContentCell(contentStyle, titleRow2, 7, "服务业");
		createMergedContentCell(contentStyle, sheet, titleRow1, "I4:L4", 8,
				"按经营地分");
		createContentCell(contentStyle, titleRow2, 8, "合计");
		createContentCell(contentStyle, titleRow2, 9, "注册型");
		createContentCell(contentStyle, titleRow2, 10, "在地");
		createContentCell(contentStyle, titleRow2, 11, "其中：贸易城");

		Row row1 = sheet.createRow(5);
		Row row2 = sheet.createRow(6);
		Row row3 = sheet.createRow(7);
		Row row4 = sheet.createRow(8);
		Row row5 = sheet.createRow(9);
		Row row6 = sheet.createRow(10);
		Row row7 = sheet.createRow(11);
		Row row8 = sheet.createRow(12);
		Row row9 = sheet.createRow(13);
		Row row10 = sheet.createRow(14);
		Row row11 = sheet.createRow(15);
		Row row12 = sheet.createRow(16);
		Row row13 = sheet.createRow(17);
		Row row14 = sheet.createRow(18);
		Row row15 = sheet.createRow(19);
		Row row16 = sheet.createRow(20);

		createContentCell(contentStyle, row1, 0, "工商年检企业数");
		createContentCell(contentStyle, row2, 0, "本年累计新增企业数");
		createContentCell(contentStyle, row3, 0, "上年同期");
		createContentCell(contentStyle, row4, 0, "同比增减户数");
		createContentCell(contentStyle, row5, 0, "本年累计注销企业数");
		createContentCell(contentStyle, row6, 0, "期末实有注册企业数");
		createContentCell(contentStyle, row7, 0, "注册资金");
		createContentCell(contentStyle, row8, 0, "期末从业人数");
		createContentCell(contentStyle, row9, 0, "本月经营生产户数");
		createContentCell(contentStyle, row10, 0, "本年累计销售（营业）额");
		createContentCell(contentStyle, row11, 0, "上年同期");
		createContentCell(contentStyle, row12, 0, "同比增长率");
		createContentCell(contentStyle, row13, 0, "本月纳税户数");
		createContentCell(contentStyle, row14, 0, "本月累计纳税额");
		createContentCell(contentStyle, row15, 0, "上年同期");
		createContentCell(contentStyle, row16, 0, "同比增长率");

		createContentCell(contentStyle, row1, 1, "户");
		createContentCell(contentStyle, row2, 1, "户");
		createContentCell(contentStyle, row3, 1, "户");
		createContentCell(contentStyle, row4, 1, "户");
		createContentCell(contentStyle, row5, 1, "户");
		createContentCell(contentStyle, row6, 1, "户");
		createContentCell(contentStyle, row7, 1, "万元");
		createContentCell(contentStyle, row8, 1, "人");
		createContentCell(contentStyle, row9, 1, "户");
		createContentCell(contentStyle, row10, 1, "万元");
		createContentCell(contentStyle, row11, 1, "万元");
		createContentCell(contentStyle, row12, 1, "%");
		createContentCell(contentStyle, row13, 1, "户");
		createContentCell(contentStyle, row14, 1, "万元");
		createContentCell(contentStyle, row15, 1, "万元");
		createContentCell(contentStyle, row16, 1, "%");

		createFormulaCell(intStyle, row1, 2, "SUM(D6:H6)");
		createFormulaCell(intStyle, row2, 2, "SUM(D7:H7)");
		createFormulaCell(intStyle, row3, 2, "SUM(D8:H8)");
		createFormulaCell(intStyle, row4, 2, "SUM(D9:H9)");
		createFormulaCell(intStyle, row5, 2, "SUM(D10:H10)");
		createFormulaCell(intStyle, row6, 2, "SUM(D11:H11)");
		createFormulaCell(intStyle, row7, 2, "SUM(D12:H12)");
		createFormulaCell(intStyle, row8, 2, "SUM(D13:H13)");
		createFormulaCell(intStyle, row9, 2, "SUM(D14:H14)");
		createFormulaCell(intStyle, row10, 2, "SUM(D15:H15)");
		createFormulaCell(intStyle, row11, 2, "SUM(D16:H16)");
		createFormulaCell(percentageContentStyle, row12, 2, "(C15-C16)/C16*100");
		createFormulaCell(intStyle, row13, 2, "SUM(D18:H18)");
		createFormulaCell(intStyle, row14, 2, "SUM(D19:H19)");
		createFormulaCell(intStyle, row15, 2, "SUM(D20:H20)");
		createFormulaCell(percentageContentStyle, row16, 2, "(C19-C20)/C20*100");

		createContentCell(intStyle, row1, 3, data.getCheckedCompanies()
				.getIndustryCount());
		createContentCell(intStyle, row1, 4, data.getCheckedCompanies()
				.getConstructionCount());
		createContentCell(intStyle, row1, 5, data.getCheckedCompanies()
				.getHouseHoldingCount());
		createContentCell(intStyle, row1, 6, data.getCheckedCompanies()
				.getCommercialCount());
		createContentCell(intStyle, row1, 7, data.getCheckedCompanies()
				.getServiceCount());

		createContentCell(intStyle, row2, 3, data.getYearNewAddedCompanies()
				.getIndustryCount());
		createContentCell(intStyle, row2, 4, data.getYearNewAddedCompanies()
				.getConstructionCount());
		createContentCell(intStyle, row2, 5, data.getYearNewAddedCompanies()
				.getHouseHoldingCount());
		createContentCell(intStyle, row2, 6, data.getYearNewAddedCompanies()
				.getCommercialCount());
		createContentCell(intStyle, row2, 7, data.getYearNewAddedCompanies()
				.getServiceCount());

		createContentCell(intStyle, row3, 3, data
				.getLastYearNewAddedCompanies().getIndustryCount());
		createContentCell(intStyle, row3, 4, data
				.getLastYearNewAddedCompanies().getConstructionCount());
		createContentCell(intStyle, row3, 5, data
				.getLastYearNewAddedCompanies().getHouseHoldingCount());
		createContentCell(intStyle, row3, 6, data
				.getLastYearNewAddedCompanies().getCommercialCount());
		createContentCell(intStyle, row3, 7, data
				.getLastYearNewAddedCompanies().getServiceCount());

		createFormulaCell(intStyle, row4, 3, "D7-D8");
		createFormulaCell(intStyle, row4, 4, "E7-E8");
		createFormulaCell(intStyle, row4, 5, "F7-F8");
		createFormulaCell(intStyle, row4, 6, "G7-G8");
		createFormulaCell(intStyle, row4, 7, "H7-H8");

		createContentCell(intStyle, row5, 3, data
				.getYearNewSignedOffCompanies().getIndustryCount());
		createContentCell(intStyle, row5, 4, data
				.getYearNewSignedOffCompanies().getConstructionCount());
		createContentCell(intStyle, row5, 5, data
				.getYearNewSignedOffCompanies().getHouseHoldingCount());
		createContentCell(intStyle, row5, 6, data
				.getYearNewSignedOffCompanies().getCommercialCount());
		createContentCell(intStyle, row5, 7, data
				.getYearNewSignedOffCompanies().getServiceCount());

		createFormulaCell(intStyle, row6, 3, "D6+D7-D10");
		createFormulaCell(intStyle, row6, 4, "E6+E7-E10");
		createFormulaCell(intStyle, row6, 5, "F6+F7-F10");
		createFormulaCell(intStyle, row6, 6, "G6+G7-G10");
		createFormulaCell(intStyle, row6, 7, "H6+H7-H10");

		createContentCell(intStyle, row7, 3, data.getAllRegAssets()
				.getIndustry());
		createContentCell(intStyle, row7, 4, data.getAllRegAssets()
				.getConstruction());
		createContentCell(intStyle, row7, 5, data.getAllRegAssets()
				.getHouseHolding());
		createContentCell(intStyle, row7, 6, data.getAllRegAssets()
				.getCommercial());
		createContentCell(intStyle, row7, 7, data.getAllRegAssets()
				.getService());

		createContentCell(intStyle, row8, 3, "");
		createContentCell(intStyle, row8, 4, "");
		createContentCell(intStyle, row8, 5, "");
		createContentCell(intStyle, row8, 6, "");
		createContentCell(intStyle, row8, 7, "");

		createContentCell(intStyle, row9, 3, data.getOperatedCompanies()
				.getIndustryCount());
		createContentCell(intStyle, row9, 4, data.getOperatedCompanies()
				.getConstructionCount());
		createContentCell(intStyle, row9, 5, data.getOperatedCompanies()
				.getHouseHoldingCount());
		createContentCell(intStyle, row9, 6, data.getOperatedCompanies()
				.getCommercialCount());
		createContentCell(intStyle, row9, 7, data.getOperatedCompanies()
				.getServiceCount());

		createContentCell(intStyle, row10, 3, data.getYearSales()
				.getIndustrySales());
		createContentCell(intStyle, row10, 4, data.getYearSales()
				.getConstructionSales());
		createContentCell(intStyle, row10, 5, data.getYearSales()
				.getHouseHoldingSales());
		createContentCell(intStyle, row10, 6, data.getYearSales()
				.getCommercialSales());
		createContentCell(intStyle, row10, 7, data.getYearSales()
				.getServiceSales());

		createContentCell(intStyle, row11, 3, data.getLastYearSales()
				.getIndustrySales());
		createContentCell(intStyle, row11, 4, data.getLastYearSales()
				.getConstructionSales());
		createContentCell(intStyle, row11, 5, data.getLastYearSales()
				.getHouseHoldingSales());
		createContentCell(intStyle, row11, 6, data.getLastYearSales()
				.getCommercialSales());
		createContentCell(intStyle, row11, 7, data.getLastYearSales()
				.getServiceSales());

		createFormulaCell(percentageContentStyle, row12, 3, "(D15-D16)/I16*100");
		createFormulaCell(percentageContentStyle, row12, 4, "(E15-E16)/I16*100");
		createFormulaCell(percentageContentStyle, row12, 5, "(F15-F16)/I16*100");
		createFormulaCell(percentageContentStyle, row12, 6, "(G15-G16)/I16*100");
		createFormulaCell(percentageContentStyle, row12, 7, "(H15-H16)/I16*100");

		createContentCell(intStyle, row13, 3, data.getCheckedTaxCompanies()
				.getIndustryCount());
		createContentCell(intStyle, row13, 4, data.getCheckedTaxCompanies()
				.getConstructionCount());
		createContentCell(intStyle, row13, 5, data.getCheckedTaxCompanies()
				.getHouseHoldingCount());
		createContentCell(intStyle, row13, 6, data.getCheckedTaxCompanies()
				.getCommercialCount());
		createContentCell(intStyle, row13, 7, data.getCheckedTaxCompanies()
				.getServiceCount());

		createContentCell(intStyle, row14, 3, data.getMonthTax()
				.getIndustryTax());
		createContentCell(intStyle, row14, 4, data.getMonthTax()
				.getConstructionTax());
		createContentCell(intStyle, row14, 5, data.getMonthTax()
				.getHouseHoldingTax());
		createContentCell(intStyle, row14, 6, data.getMonthTax()
				.getCommercialTax());
		createContentCell(intStyle, row14, 7, data.getMonthTax()
				.getServiceTax());

		createContentCell(intStyle, row15, 3, data.getLastYearTax()
				.getIndustryTax());
		createContentCell(intStyle, row15, 4, data.getLastYearTax()
				.getConstructionTax());
		createContentCell(intStyle, row15, 5, data.getLastYearTax()
				.getHouseHoldingTax());
		createContentCell(intStyle, row15, 6, data.getLastYearTax()
				.getCommercialTax());
		createContentCell(intStyle, row15, 7, data.getLastYearTax()
				.getServiceTax());

		createFormulaCell(percentageContentStyle, row16, 3, "(D19-D20)/C20*100");
		createFormulaCell(percentageContentStyle, row16, 4, "(E19-E20)/C20*100");
		createFormulaCell(percentageContentStyle, row16, 5, "(F19-F20)/C20*100");
		createFormulaCell(percentageContentStyle, row16, 6, "(G19-G20)/C20*100");
		createFormulaCell(percentageContentStyle, row16, 7, "(H19-H20)/C20*100");

		createFormulaCell(intStyle, row1, 8, "SUM(J6:K6)");
		createContentCell(intStyle, row1, 9, data.getCheckedAFCompanies()
				.getRegCount());
		createContentCell(intStyle, row1, 10, data.getCheckedAFCompanies()
				.getLocalCount());
		createContentCell(intStyle, row1, 11, data.getCheckedAFCompanies()
				.getMycCount());

		createFormulaCell(intStyle, row2, 8, "SUM(J7:K7)");
		createContentCell(intStyle, row2, 9, data.getYearNewAddedAFCompanies()
				.getRegCount());
		createContentCell(intStyle, row2, 10, data.getYearNewAddedAFCompanies()
				.getLocalCount());
		createContentCell(intStyle, row2, 11, data.getYearNewAddedAFCompanies()
				.getMycCount());

		createFormulaCell(intStyle, row3, 8, "SUM(J8:K8)");
		createContentCell(intStyle, row3, 9, data
				.getLastYearNewAddedAFCompanies().getRegCount());
		createContentCell(intStyle, row3, 10, data
				.getLastYearNewAddedAFCompanies().getLocalCount());
		createContentCell(intStyle, row3, 11, data
				.getLastYearNewAddedAFCompanies().getMycCount());

		createFormulaCell(intStyle, row4, 8, "SUM(J9:K9)");
		createFormulaCell(intStyle, row4, 9, "J7-J8");
		createFormulaCell(intStyle, row4, 10, "K7-K8");
		createFormulaCell(intStyle, row4, 11, "L7-L8");

		createFormulaCell(intStyle, row5, 8, "SUM(J10:K10)");
		createContentCell(intStyle, row5, 9, data
				.getYearNewSignedOffAFCompanies().getRegCount());
		createContentCell(intStyle, row5, 10, data
				.getYearNewSignedOffAFCompanies().getLocalCount());
		createContentCell(intStyle, row5, 11, data
				.getYearNewSignedOffAFCompanies().getMycCount());

		createFormulaCell(intStyle, row6, 8, "SUM(J11:K11)");
		createFormulaCell(intStyle, row6, 9, "J6+J7-J10");
		createFormulaCell(intStyle, row6, 10, "K6+K7-K10");
		createFormulaCell(intStyle, row6, 11, "L6+L7-L10");

		createFormulaCell(intStyle, row7, 8, "SUM(J12:K12)");
		createContentCell(intStyle, row7, 9, data.getAllRegAssetsAF().getReg());
		createContentCell(intStyle, row7, 10, data.getAllRegAssetsAF()
				.getLocal());
		createContentCell(intStyle, row7, 11, data.getAllRegAssetsAF().getMyc());

		createContentCell(intStyle, row8, 8, "");
		createContentCell(intStyle, row8, 9, "");
		createContentCell(intStyle, row8, 10, "");
		createContentCell(intStyle, row8, 11, "");

		createFormulaCell(intStyle, row9, 8, "SUM(J14:K14)");
		createContentCell(intStyle, row9, 9, data.getOperatedAFCompanies()
				.getRegCount());
		createContentCell(intStyle, row9, 10, data.getOperatedAFCompanies()
				.getLocalCount());
		createContentCell(intStyle, row9, 11, data.getOperatedAFCompanies()
				.getMycCount());

		createFormulaCell(intStyle, row10, 8, "SUM(J15:K15)");
		createContentCell(intStyle, row10, 9, data.getYearSalesAF()
				.getRegSales());
		createContentCell(intStyle, row10, 10, data.getYearSalesAF()
				.getLocalSales());
		createContentCell(intStyle, row10, 11, data.getYearSalesAF()
				.getMycSales());

		createFormulaCell(intStyle, row11, 8, "SUM(J16:K16)");
		createContentCell(intStyle, row11, 9, data.getLastYearSalesAF()
				.getRegSales());
		createContentCell(intStyle, row11, 10, data.getLastYearSalesAF()
				.getLocalSales());
		createContentCell(intStyle, row11, 11, data.getLastYearSalesAF()
				.getMycSales());

		createFormulaCell(percentageContentStyle, row12, 8, "(I15-I16)/I16*100");
		createFormulaCell(percentageContentStyle, row12, 9, "(J15-J16)/I16*100");
		createFormulaCell(percentageContentStyle, row12, 10,
				"(K15-K16)/I16*100");
		createFormulaCell(percentageContentStyle, row12, 11,
				"(L15-L16)/I16*100");

		createFormulaCell(intStyle, row13, 8, "SUM(J18:K18)");
		createContentCell(intStyle, row13, 9, data.getCheckedTaxAFCompanies()
				.getRegCount());
		createContentCell(intStyle, row13, 10, data.getCheckedTaxAFCompanies()
				.getLocalCount());
		createContentCell(intStyle, row13, 11, data.getCheckedTaxAFCompanies()
				.getMycCount());

		createFormulaCell(intStyle, row14, 8, "SUM(J19:K19)");
		createContentCell(intStyle, row14, 9, data.getMonthAFTax().getRegTax());
		createContentCell(intStyle, row14, 10, data.getMonthAFTax()
				.getLocalTax());
		createContentCell(intStyle, row14, 11, data.getMonthAFTax().getMycTax());

		createFormulaCell(intStyle, row15, 8, "SUM(J20:K20)");
		createContentCell(intStyle, row15, 9, data.getLastYearAFTax()
				.getRegTax());
		createContentCell(intStyle, row15, 10, data.getLastYearAFTax()
				.getLocalTax());
		createContentCell(intStyle, row15, 11, data.getLastYearAFTax()
				.getMycTax());

		createFormulaCell(percentageContentStyle, row16, 8, "(I19-I20)/C20*100");
		createFormulaCell(percentageContentStyle, row16, 9, "(J19-J20)/C20*100");
		createFormulaCell(percentageContentStyle, row16, 10,
				"(K19-K20)/C20*100");
		createFormulaCell(percentageContentStyle, row16, 11,
				"(L19-L20)/C20*100");

		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(11);

	}

}
