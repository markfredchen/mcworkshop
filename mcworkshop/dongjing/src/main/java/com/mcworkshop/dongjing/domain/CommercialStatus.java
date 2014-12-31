// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.domain;

import com.mcworkshop.common.sysenum.SysEnum;

/**
 * @author $Author$
 * 
 */
public enum CommercialStatus implements SysEnum {

	REVOKED_NOT_SIGN_OFF, REVOKED_SIGN_OFF, TRANSFERRED, CONFIRMED, SIGN_OFF;

	private int sequence;
	private String messageKey;

	@Override
	public Class<? extends SysEnum> getEnumClass() {
		return CommercialStatus.class;
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
