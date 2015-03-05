// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.mcworkshop.common.web.report.CellStyleWrapper;
import com.mcworkshop.common.web.report.ReportUtil;
import com.mcworkshop.dongjing.domain.TaxData;
import com.mcworkshop.dongjing.service.report.model.TaxOrgData;

/**
 * @author $Author$
 * 
 */
public class TaxOrgReport extends DJBaseReport<TaxOrgData> {

	public TaxOrgReport(TaxOrgData data, int year, int month) {
		super(data, year, month);
	}

	@Override
	protected void constructReport() {
		constructTaxOrgReport(month + "月", year + "年" + month + "月份",
				data.getMonthTaxOrgData(), data.getMonthHouseData());
		constructTaxOrgReport(month + "月累计", year + "年" + month + "月份累计",
				data.getYearTaxOrgData(), data.getYearHouseData());
	}

	private void constructTaxOrgReport(String sheetName, String sheetTitle,
			TaxData taxOrg, TaxData householdTax) {
		if (taxOrg == null) {
			taxOrg = new TaxData();
		}
		if (householdTax == null) {
			householdTax = new TaxData();
		}
		Sheet sheet = workbook.createSheet(sheetName);
		Cell title = sheet.createRow(0).createCell(0);
		title.setCellValue("私营税收分解表");
		title.setCellStyle(new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().fontSize((short) 14).bold().done());
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));

		Row row = sheet.createRow(1);
		createContentCell(titleStyle, row, 0, sheetTitle);
		createContentCell(titleStyle, row, 9, "单位: 万元");

		Row row1 = sheet.createRow(2);
		Row row2 = sheet.createRow(3);
		Row row3 = sheet.createRow(4);
		Row row4 = sheet.createRow(5);
		Row row5 = sheet.createRow(6);
		Row row6 = sheet.createRow(7);

		createContentCell(contentStyle, row1, 0, "单位名称");
		createContentCell(contentStyle, row1, 1, "小计");
		createContentCell(contentStyle, row1, 2, "增值税");
		createContentCell(contentStyle, row1, 3, "营业税");
		createContentCell(contentStyle, row1, 4, "城建税");
		createContentCell(contentStyle, row1, 5, "企所税");
		createContentCell(contentStyle, row1, 6, "个所税");
		createContentCell(contentStyle, row1, 7, "印花税");
		createContentCell(contentStyle, row1, 8, "土地使用税");
		createContentCell(contentStyle, row1, 9, "其它税");
		createContentCell(contentStyle, row1, 10, "合计");

		createContentCell(contentStyle, row2, 0, "九所税收");
		createContentCell(contentStyle, row3, 0, "房产税收");
		createContentCell(contentStyle, row4, 0, "税收合计");
		createContentCell(contentStyle, row5, 0, "珠江/泽宇/泗泾50%税收");
		createContentCell(contentStyle, row6, 0, "百颗星所属税收");

		createFormulaCell(dataStyle, row2, 1, "SUM(C4:I4)");
		createFormulaCell(dataStyle, row3, 1, "SUM(C5:I5)");
		createFormulaCell(dataStyle, row4, 1, "SUM(C6:I6)");
		createFormulaCell(dataStyle, row5, 1, "SUM(C7:I7)");
		createFormulaCell(dataStyle, row6, 1, "SUM(C8:I8)");

		createContentCell(dataStyle, row2, 2, taxOrg.getVat());
		createContentCell(dataStyle, row3, 2, householdTax.getVat());
		createFormulaCell(dataStyle, row4, 2, "SUM(C4:C5)");
		createContentCell(dataStyle, row5, 2, 0D);
		createFormulaCell(dataStyle, row6, 2, "C4+C7");

		createContentCell(dataStyle, row2, 3, taxOrg.getOperateTax());
		createContentCell(dataStyle, row3, 3, householdTax.getOperateTax());
		createFormulaCell(dataStyle, row4, 3, "SUM(D4:D5)");
		createContentCell(dataStyle, row5, 3, 0D);
		createFormulaCell(dataStyle, row6, 3, "D4+D7");

		createContentCell(dataStyle, row2, 4, taxOrg.getConstructionTax());
		createContentCell(dataStyle, row3, 4, householdTax.getConstructionTax());
		createFormulaCell(dataStyle, row4, 4, "SUM(E4:E5)");
		createContentCell(dataStyle, row5, 4, 0D);
		createFormulaCell(dataStyle, row6, 4, "E4+E7");

		createContentCell(dataStyle, row2, 5, taxOrg.getTotalCompanyIncomeTax());
		createContentCell(dataStyle, row3, 5,
				householdTax.getTotalCompanyIncomeTax());
		createFormulaCell(dataStyle, row4, 5, "SUM(F4:F5)");
		createContentCell(dataStyle, row5, 5, 0D);
		createFormulaCell(dataStyle, row6, 5, "F4+F7");

		createContentCell(dataStyle, row2, 6, taxOrg.getPersonalIncomingTax());
		createContentCell(dataStyle, row3, 6,
				householdTax.getPersonalIncomingTax());
		createFormulaCell(dataStyle, row4, 6, "SUM(G4:G5)");
		createContentCell(dataStyle, row5, 6, 0D);
		createFormulaCell(dataStyle, row6, 6, "G4+G7");

		createContentCell(dataStyle, row2, 7, taxOrg.getStampTax());
		createContentCell(dataStyle, row3, 7, householdTax.getStampTax());
		createFormulaCell(dataStyle, row4, 7, "SUM(H4:H5)");
		createContentCell(dataStyle, row5, 7, 0D);
		createFormulaCell(dataStyle, row6, 7, "H4+H7");

		createContentCell(dataStyle, row2, 8, taxOrg.getLandUseTax());
		createContentCell(dataStyle, row3, 8, householdTax.getLandUseTax());
		createFormulaCell(dataStyle, row4, 8, "SUM(I4:I5)");
		createContentCell(dataStyle, row5, 8, 0D);
		createFormulaCell(dataStyle, row6, 8, "I4+I7");

		createContentCell(dataStyle, row2, 9, taxOrg.getTotalOtherTax());
		createContentCell(dataStyle, row3, 9, householdTax.getTotalOtherTax());
		createFormulaCell(dataStyle, row4, 9, "SUM(J4:J5)");
		createContentCell(dataStyle, row5, 9, 0D);
		createFormulaCell(dataStyle, row6, 9, "J4+J7");

		createContentCell(titleStyle, sheet.createRow(8), 0, "注：小计为可产生地方税税种之合");
		createContentCell(titleStyle, sheet.createRow(9), 0,
				"百颗星所属=总税-房产+珠江50%");

		ReportUtil.setBorder(2, CellRangeAddress.valueOf("A3:K3"), sheet,
				workbook);
		ReportUtil.setBorder(2, CellRangeAddress.valueOf("A4:K5"), sheet,
				workbook);
		ReportUtil.setBorder(2, CellRangeAddress.valueOf("A6:K6"), sheet,
				workbook);
		ReportUtil.setBorder(2, CellRangeAddress.valueOf("A7:K7"), sheet,
				workbook);
		ReportUtil.setBorder(2, CellRangeAddress.valueOf("A8:K8"), sheet,
				workbook);

		ReportUtil.setBorder(2, CellRangeAddress.valueOf("A3:A8"), sheet,
				workbook);
		ReportUtil.setBorder(2, CellRangeAddress.valueOf("B3:B8"), sheet,
				workbook);
		ReportUtil.setBorder(2, CellRangeAddress.valueOf("K3:K8"), sheet,
				workbook);

		sheet.autoSizeColumn(0);
	}

	@Override
	protected String getReportFileName() {
		return TaxOrgReport.class.getSimpleName();
	}

}
