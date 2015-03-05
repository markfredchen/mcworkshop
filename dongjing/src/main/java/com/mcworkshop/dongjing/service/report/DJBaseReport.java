// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report;

import org.apache.wicket.util.file.File;

import com.mcworkshop.common.configuration.Config;
import com.mcworkshop.common.web.report.BaseReport;
import com.mcworkshop.dongjing.configuration.DJConfigurationKey;

/**
 * @author $Author$
 * 
 */
abstract public class DJBaseReport<T extends Object> extends BaseReport<T> {

	protected int year;
	protected int month;

	public DJBaseReport(T data, int year, int month) {
		super(data);
		this.year = year;
		this.month = month;
		prepareDirectory(year, month);
	}

	@Override
	protected String getReportFilePath() {
		String path = Config.getConfig(DJConfigurationKey.REPORT_DIR);
		path += "/" + year + "/";
		path += month + "/";
		path += getReportFileName();
		return path;
	}

	abstract protected String getReportFileName();

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
}
