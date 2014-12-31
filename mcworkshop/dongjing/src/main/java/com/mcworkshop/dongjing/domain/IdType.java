// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: IdType.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.domain;

import com.mcworkshop.common.sysenum.SysEnum;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public enum IdType implements SysEnum {
	PASSPORT, IDCARD;
	private int sequence;
	private String messageKey;

	@Override
	public Class<? extends SysEnum> getEnumClass() {
		return EconomyEntity.class;
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
