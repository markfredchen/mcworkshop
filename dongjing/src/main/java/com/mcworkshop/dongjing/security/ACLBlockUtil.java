// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: ACLBlockUtil.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.security;

import com.mcworkshop.common.domain.Right;
import com.mcworkshop.common.domain.Role;
import com.mcworkshop.dongjing.domain.AffiliateBlock;
import com.mcworkshop.dongjing.web.DJWebSession;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class ACLBlockUtil {

	public static AffiliateBlock getOwnAffiliateBlock() {
		Role role = DJWebSession.get().getUser().getRole();
		String rightName = null;
		for (Right right : role.getRights()) {
			if (right.getName().startsWith("CM.BLOCK")) {
				rightName = right.getName();
				break;
			}
		}
		if (rightName == null) {
			return null;
		}
		return AffiliateBlock
				.fromValue(rightName.substring("CM.Block".length()));
	}
}
