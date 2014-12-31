// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.authorization;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.wicket.authroles.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;

import com.mcworkshop.dongjing.domain.User;
import com.mcworkshop.dongjing.web.DJWebSession;

/**
 * @author $Author$
 * 
 */
public class RoleChecker implements IRoleCheckingStrategy {

	@Override
	public boolean hasAnyRole(Roles roles) {
		User user = DJWebSession.get().getUser();
		if (user == null) {
			return false;
		} else if (roles
				.contains(com.mcworkshop.dongjing.authorization.Roles.USER)) {
			return true;
		} else {
			Set<String> grantedRights = user.getRole().getGrantedRights();
			Set<String> departmentRights = new HashSet<String>();
			if (DJWebSession.get().getDepartmentRights() != null
					&& DJWebSession.get().getDepartmentRights().size() > 0) {
				List<String> department = DJWebSession.get()
						.getDepartmentRights();
				for (String grantedRight : grantedRights) {
					for (String dr : department) {
						if (grantedRight.contains("SYSTEM")) {
							departmentRights.add(grantedRight);
						} else if (grantedRight.contains(dr)) {
							departmentRights.add(grantedRight);
						}
					}
				}
			} else {
				departmentRights = grantedRights;
			}
			for (String requiredRole : roles) {
				for (String grantedRight : departmentRights) {
					if (grantedRight.contains("SYSTEM")) {
						return true;
					}
					if (grantedRight.contains(requiredRole)) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
