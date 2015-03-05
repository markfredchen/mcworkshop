// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.configuration;

import com.mcworkshop.common.configuration.ConfigurationKey;

/**
 * @author $Author$
 * 
 */
public enum DJConfigurationKey implements ConfigurationKey {
	SYSTEM_ADMIN_EMAIL("system.admin.email"), REPORT_DIR("report.dir"), EMAIL_MANAGER(
			"mail.manager"), EMAIL_HOST("mail.server.host"), EMAIL_FROM_ADDRESS(
			"mail.from.address"), EMAIL_FROM_NAME("mail.from.name"), EMAIL_FROM_PASSWORD(
			"mail.from.password"), EMAIL_MOCK_TO_ADDRESS("mail.to.address.mock");

	private String value;

	private DJConfigurationKey(String value) {
		this.value = value;
	}

	@Override
	public String value() {
		return this.value;
	}

}
