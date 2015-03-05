// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: TaxBlockReport.java 152 2014-08-12 15:48:26Z mchen $
package com.mcworkshop.dongjing.service.report;

import java.io.File;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.mcworkshop.common.configuration.Config;
import com.mcworkshop.common.web.report.CellStyleWrapper;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.configuration.DJConfigurationKey;
import com.mcworkshop.dongjing.domain.AffiliateBlock;
import com.mcworkshop.dongjing.domain.TaxData;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class TaxBlockReport extends DJBaseReport<List<TaxData>> {

	private static final String FILENAME = "TaxMonthBlockReport.xlsx";

	private AffiliateBlock block;

	public TaxBlockReport(List<TaxData> data, int year, int month,
			AffiliateBlock block) {
		super(data, year, month);
		this.block = block;
	}

	@Override
	protected void prepareDirectory(int year, int month) {
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

	@Override
	protected String getReportFileName() {
		return FILENAME;
	}

	@Override
	protected void constructReport() {
		Sheet sheet = workbook.createSheet();
		createMergedContentCell(
				new CellStyleWrapper(workbook).alignCenter().alignMiddle()
						.fontSize((short) 14).bold().done(),
				sheet,
				sheet.createRow(0),
				"A1:U1",
				0,
				year
						+ "年"
						+ month
						+ "月"
						+ WicketMessageUtil.getResourceString(block
								.getMessageKey()) + "税收明细");
		Row headerRow = sheet.createRow(1);
		createContentCell(contentStyle, headerRow, 0, "纳税人名称");
		createContentCell(contentStyle, headerRow, 1, "行业");
		createContentCell(contentStyle, headerRow, 2, "销售额(增)");
		createContentCell(contentStyle, headerRow, 3, "销售额(营)");
		createContentCell(contentStyle, headerRow, 4, "增值税");
		createContentCell(contentStyle, headerRow, 5, "营业税");
		createContentCell(contentStyle, headerRow, 6, "消费税");
		createContentCell(contentStyle, headerRow, 7, "内资企业所得税");
		createContentCell(contentStyle, headerRow, 8, "外资企业所得税");
		createContentCell(contentStyle, headerRow, 9, "房产税");
		createContentCell(contentStyle, headerRow, 10, "印花税");
		createContentCell(contentStyle, headerRow, 11, "车船使用税");
		createContentCell(contentStyle, headerRow, 12, "土地增值税");
		createContentCell(contentStyle, headerRow, 13, "土地使用税");
		createContentCell(contentStyle, headerRow, 14, "个人税得税");
		createContentCell(contentStyle, headerRow, 15, "城建税");
		createContentCell(contentStyle, headerRow, 16, "车购税");
		createContentCell(contentStyle, headerRow, 17, "河道费");
		createContentCell(contentStyle, headerRow, 18, "教育费附加");
		createContentCell(contentStyle, headerRow, 19, "文化事业费");
		createContentCell(contentStyle, headerRow, 20, "其它");

		for (int i = 0; i < data.size(); i++) {
			Row row = sheet.createRow(2 + i);
			createContentCell(contentStyle, row, 0, data.get(i).getCompany()
					.getName());
			createContentCell(contentStyle, row, 1, data.get(i).getIndustry());
			createContentCell(dataStyle, row, 2, data.get(i).getAccSales());
			createContentCell(dataStyle, row, 3, data.get(i).getSales());
			createContentCell(dataStyle, row, 4, data.get(i).getVat());
			createContentCell(dataStyle, row, 5, data.get(i).getOperateTax());
			createContentCell(dataStyle, row, 6, data.get(i).getExpenseTax());
			createContentCell(dataStyle, row, 7, data.get(i)
					.getDomesticIncomingTax());
			createContentCell(dataStyle, row, 8, data.get(i)
					.getForeignIncomingTax());
			createContentCell(dataStyle, row, 9, data.get(i).getHousingTax());
			createContentCell(dataStyle, row, 10, data.get(i).getStampTax());
			createContentCell(dataStyle, row, 11, data.get(i).getTrafficTax());
			createContentCell(dataStyle, row, 12, data.get(i).getLandVAT());
			createContentCell(dataStyle, row, 13, data.get(i).getLandUseTax());
			createContentCell(dataStyle, row, 14, data.get(i)
					.getPersonalIncomingTax());
			createContentCell(dataStyle, row, 15, data.get(i)
					.getConstructionTax());
			createContentCell(dataStyle, row, 16, data.get(i).getVeichleTax());
			createContentCell(dataStyle, row, 17, data.get(i).getRiverTax());
			createContentCell(dataStyle, row, 18, data.get(i).getEducationTax());
			createContentCell(dataStyle, row, 19, data.get(i).getCultureTax());
			createContentCell(dataStyle, row, 20, data.get(i).getOtherTax());
		}
	}

}
