// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: AffiliateBlock.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.domain;

import com.mcworkshop.common.sysenum.SysEnum;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public enum AffiliateBlock implements SysEnum {
	BLOCK_1, BLOCK_2, BLOCK_3, BLOCK_4, BLOCK_5, BLOCK_6, BLOCK_7, BLOCK_8, BLOCK_9, BLOCK_10, BLOCK_11, BLOCK_12, BLOCK_13, BLOCK_14, BLOCK_15, BLOCK_16, BLOCK_17, BLOCK_18, BLOCK_19, BLOCK_BLANK;

	private int sequence;
	private String messageKey;

	@Override
	public Class<? extends SysEnum> getEnumClass() {
		return AffiliateBlock.class;
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

	public static AffiliateBlock fromValue(String i) {
		for (AffiliateBlock b : AffiliateBlock.values()) {
			if (b.toString().endsWith("_" + i)) {
				return b;
			}
		}
		return null;
	}
}
