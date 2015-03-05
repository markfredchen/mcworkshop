// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: ProjectReport.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.web.page.project;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.mcworkshop.common.configuration.Config;
import com.mcworkshop.common.web.report.BaseReport;
import com.mcworkshop.common.web.report.CellStyleWrapper;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.configuration.DJConfigurationKey;
import com.mcworkshop.dongjing.domain.Project;
import com.mcworkshop.dongjing.domain.ProjectStatus;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class ProjectReport extends BaseReport<List<Project>> {

	private static final String FILENAME = "ProjectStatusReport.xlsx";
	private static final String DATE_PATTERN = "yyyy.MM";

	private SimpleDateFormat f = new SimpleDateFormat(DATE_PATTERN);

	private Map<ProjectStatus, List<Project>> dataMap = new HashMap<ProjectStatus, List<Project>>();

	public ProjectReport(List<Project> data) {
		super(data);
		for (Project project : data) {
			if (dataMap.get(project.getStatus()) == null) {
				dataMap.put(project.getStatus(), new ArrayList<Project>());
			}
			dataMap.get(project.getStatus()).add(project);
		}
		prepareDirectory();
	}

	private void prepareDirectory() {
		StringBuilder outputDirectoryPath = new StringBuilder();
		outputDirectoryPath.append(Config
				.getConfig(DJConfigurationKey.REPORT_DIR));
		outputDirectoryPath.append("/project");
		File outputDirectory = new File(outputDirectoryPath.toString());
		outputDirectory.mkdirs();
	}

	@Override
	protected void constructReport() {
		createSheetBeforeAudit(getProjectsByStatus(ProjectStatus.AUDITTED),
				"已通过评估待供地项目情况表");
		createSheetAfterAudit(getProjectsByStatus(ProjectStatus.CONFIRMED),
				"洞泾镇已批未建项目情况表");
		createSheetAfterAudit(getProjectsByStatus(ProjectStatus.PLANNED),
				"洞泾镇拟开工项目情况表");
		List<Project> projects = getProjectsByStatus(ProjectStatus.STARTED);
		List<Project> commercialProject = new ArrayList<Project>();
		List<Project> industryProject = new ArrayList<Project>();
		for (Project project : projects) {
			if (project.isCommercial()) {
				commercialProject.add(project);
			} else {
				industryProject.add(project);
			}
		}

		createSheetAfterAudit(commercialProject, "洞泾镇已开工未竣工工业项目情况表");
		createSheetAfterAudit(industryProject, "洞泾镇已开工未竣工商业项目情况表");
		createSheetAfterAudit(getProjectsByStatus(ProjectStatus.FINISHED),
				"洞泾镇拟投产项目情况表");
	}

	private List<Project> getProjectsByStatus(ProjectStatus status) {
		List<Project> projects = dataMap.get(status);
		if (projects == null) {
			return new ArrayList<Project>();
		} else {
			return projects;
		}
	}

	private void createSheetAfterAudit(List<Project> projects, String title) {
		Sheet sheet = workbook.createSheet(title);
		createMergedContentCell(new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().fontSize((short) 14).done(), sheet,
				sheet.createRow(0), "A1:W1", 0, title, false);
		Row titleRow1 = sheet.createRow(1);
		Row titleRow2 = sheet.createRow(2);
		createMergedContentCell(contentStyle, sheet, titleRow1, "A2:A3", 0,
				"序号");
		createMergedContentCell(contentStyle, sheet, titleRow1, "B2:B3", 1,
				"项目名称");
		createMergedContentCell(contentStyle, sheet, titleRow1, "C2:C3", 2,
				"主要产品/产业导向");
		createMergedContentCell(contentStyle, sheet, titleRow1, "D2:D3", 3,
				"新建/改扩建");
		createMergedContentCell(contentStyle, sheet, titleRow1, "E2:E3", 4,
				"总面积(亩)");
		createMergedContentCell(contentStyle, sheet, titleRow1, "F2:F3", 5,
				"本次开工/竣工/投产面积(亩)");
		createMergedContentCell(contentStyle, sheet, titleRow1, "G2:G3", 6,
				"计划总资产\n(万元)");
		createMergedContentCell(contentStyle, sheet, titleRow1, "H2:H3", 7,
				"固定资产投资\n(万元)");
		createMergedContentCell(contentStyle, sheet, titleRow1, "I2:I3", 8,
				"预计年销售收入\n(万元)");
		createMergedContentCell(contentStyle, sheet, titleRow1, "J2:J3", 9,
				"预计利润\n(万元)");
		createMergedContentCell(contentStyle, sheet, titleRow1, "K2:K3", 10,
				"预计税收\n(万元)");
		createMergedContentCell(contentStyle, sheet, titleRow1, "L2:L3", 11,
				"建筑面积\n(平方米)");
		createMergedContentCell(contentStyle, sheet, titleRow1, "M2:M3", 12,
				"栋数及层数");
		createMergedContentCell(contentStyle, sheet, titleRow1, "N2:R2", 13,
				"开工、竣工、投产时间");
		createContentCell(contentStyle, titleRow2, 13, "开工时间");
		createContentCell(contentStyle, titleRow2, 14, "竣工时间");
		createContentCell(contentStyle, titleRow2, 15, "投产时间");
		createContentCell(contentStyle, titleRow2, 16, "具体在办手续");
		createContentCell(contentStyle, titleRow2, 17, "碰到困难");
		createMergedContentCell(contentStyle, sheet, titleRow1, "S2:S3", 18,
				"联系人");
		createMergedContentCell(contentStyle, sheet, titleRow1, "T2:T3", 19,
				"联系电话");
		createMergedContentCell(contentStyle, sheet, titleRow1, "U2:U3", 20,
				"传真");
		createMergedContentCell(contentStyle, sheet, titleRow1, "V2:V3", 21,
				"邮件");
		createMergedContentCell(contentStyle, sheet, titleRow1, "W2:W3", 22,
				"备注");
		for (int i = 1; i <= projects.size(); i++) {
			Project p = projects.get(i - 1);
			Row dataRow = sheet.createRow(2 + i);
			createContentCell(contentStyle, dataRow, 0, i + "");
			createContentCell(contentStyle, dataRow, 1, p.getName());
			createContentCell(contentStyle, dataRow, 2, p.getProduct());
			if (p.getConstructType() != null) {
				createContentCell(contentStyle, dataRow, 3,
						WicketMessageUtil.getResourceString(p
								.getConstructType().getMessageKey()));
			} else {
				createContentCell(contentStyle, dataRow, 3, "");
			}
			createContentCell(dataStyle, dataRow, 4, p.getArea());
			createContentCell(dataStyle, dataRow, 5, p.getActualArea());
			createContentCell(dataStyle, dataRow, 6, p.getTotalInvestment());
			createContentCell(dataStyle, dataRow, 7, p.getFixInvestment());
			createContentCell(dataStyle, dataRow, 8, p.getSales());
			createContentCell(dataStyle, dataRow, 9, p.getProfit());
			createContentCell(dataStyle, dataRow, 10, p.getTax());
			createContentCell(dataStyle, dataRow, 11, p.getConstructionArea());
			createContentCell(contentStyle, dataRow, 12,
					p.getNumberOfBuilding());
			createContentCell(contentStyle, dataRow, 13,
					formatDate(f, p.getStartDate()));
			createContentCell(contentStyle, dataRow, 14,
					formatDate(f, p.getEndDate()));
			createContentCell(contentStyle, dataRow, 15,
					formatDate(f, p.getProduceDate()));
			createContentCell(contentStyle, dataRow, 16, p.getProgress());
			createContentCell(contentStyle, dataRow, 17, p.getProblem());
			createContentCell(contentStyle, dataRow, 18, p.getContactor());
			createContentCell(contentStyle, dataRow, 19, p.getPhone());
			createContentCell(contentStyle, dataRow, 20, p.getFax());
			createContentCell(contentStyle, dataRow, 21, p.getEmail());
			createContentCell(contentStyle, dataRow, 22, p.getComment());
		}
	}

	private void createSheetBeforeAudit(List<Project> projects, String title) {
		Sheet sheet = workbook.createSheet(title);
		createMergedContentCell(new CellStyleWrapper(workbook).alignCenter()
				.alignMiddle().fontSize((short) 14).done(), sheet,
				sheet.createRow(0), "A1:Q1", 0, title, false);
		Row titleRow1 = sheet.createRow(1);
		Row titleRow2 = sheet.createRow(2);
		createMergedContentCell(contentStyle, sheet, titleRow1, "A2:A3", 0,
				"序号");
		createMergedContentCell(contentStyle, sheet, titleRow1, "B2:B3", 1,
				"项目名称");
		createMergedContentCell(contentStyle, sheet, titleRow1, "C2:C3", 2,
				"主要产品");
		createMergedContentCell(contentStyle, sheet, titleRow1, "D2:D3", 3,
				"总面积(亩)");
		createMergedContentCell(contentStyle, sheet, titleRow1, "E2:E3", 4,
				"建筑面积\n(平方米)");
		createMergedContentCell(contentStyle, sheet, titleRow1, "F2:F3", 5,
				"评估通过时间");
		createMergedContentCell(contentStyle, sheet, titleRow1, "G2:I2", 6,
				"总投资");
		createContentCell(contentStyle, titleRow2, 6, "内资(万元)");
		createContentCell(contentStyle, titleRow2, 7, "外资(万元)");
		createContentCell(contentStyle, titleRow2, 8, "其中固定资产投资(万元)");
		createMergedContentCell(contentStyle, sheet, titleRow1, "J2:L2", 9,
				"预计产出效益(万元)");
		createContentCell(contentStyle, titleRow2, 9, "销售收入");
		createContentCell(contentStyle, titleRow2, 10, "利润");
		createContentCell(contentStyle, titleRow2, 11, "上缴税收");
		createMergedContentCell(contentStyle, sheet, titleRow1, "M2:M3", 12,
				"联系人");
		createMergedContentCell(contentStyle, sheet, titleRow1, "N2:N3", 13,
				"联系电话");
		createMergedContentCell(contentStyle, sheet, titleRow1, "O2:O3", 14,
				"传真");
		createMergedContentCell(contentStyle, sheet, titleRow1, "P2:P3", 15,
				"邮件");
		createMergedContentCell(contentStyle, sheet, titleRow1, "Q2:Q3", 16,
				"备注");

		for (int i = 1; i <= projects.size(); i++) {
			Project p = projects.get(i - 1);
			Row dataRow = sheet.createRow(2 + i);
			createContentCell(contentStyle, dataRow, 0, i + "");
			createContentCell(contentStyle, dataRow, 1, p.getName());
			createContentCell(contentStyle, dataRow, 2, p.getProduct());
			createContentCell(dataStyle, dataRow, 3, p.getArea());
			createContentCell(dataStyle, dataRow, 4, p.getConstructionArea());
			createContentCell(contentStyle, dataRow, 5,
					formatDate(f, p.getAuditPassDate()));
			createContentCell(dataStyle, dataRow, 6, p.getDomesticInvestment());
			createContentCell(dataStyle, dataRow, 7, p.getForeignInvestment());
			createContentCell(dataStyle, dataRow, 8, p.getTotalInvestment());
			createContentCell(dataStyle, dataRow, 9, p.getSales());
			createContentCell(dataStyle, dataRow, 10, p.getProfit());
			createContentCell(dataStyle, dataRow, 11, p.getTax());
			createContentCell(contentStyle, dataRow, 12, p.getContactor());
			createContentCell(contentStyle, dataRow, 13, p.getPhone());
			createContentCell(contentStyle, dataRow, 14, p.getFax());
			createContentCell(contentStyle, dataRow, 15, p.getEmail());
			createContentCell(contentStyle, dataRow, 16, p.getComment());
		}
	}

	@Override
	protected String getReportFilePath() {
		String path = Config.getConfig(DJConfigurationKey.REPORT_DIR);
		path += "/project/" + FILENAME;
		return path;
	}

}
