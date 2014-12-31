// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.domain;

import com.mcworkshop.common.sysenum.SysEnum;

/**
 * @author $Author$
 * 
 */
public enum TaxOrg implements SysEnum {
	TAX_ORG_6, TAX_ORG_9, TAX_ORG_12, OTHER;

	private int sequence;
	private String messageKey;

	@Override
	public Class<? extends SysEnum> getEnumClass() {
		return TaxNature.class;
	}

	@Override
	public Enum<? extends SysEnum> getEnumValue() {
		return this;
	}

	@Override
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@Override
	public int getSequence() {
		return this.sequence;
	}

	@Override
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	@Override
	public String getMessageKey() {
		return this.messageKey;
	}

}
