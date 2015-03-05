// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mcworkshop.common.web.BaseWebSession;
import com.mcworkshop.dongjing.domain.User;

/**
 * @author $Author$
 * 
 */
public class DJWebSession extends BaseWebSession {

	private static final Logger log = LoggerFactory
			.getLogger(DJWebSession.class);

	private static final long serialVersionUID = 1L;

	private User user;
	private List<String> departmentRights = new ArrayList<String>();

	public DJWebSession(Request request) {
		super(request);
		log.error("session started");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static DJWebSession get() {
		return (DJWebSession) WebSession.get();
	}

	public List<String> getDepartmentRights() {
		return departmentRights;
	}

	public void setDepartmentRights(List<String> departmentRights) {
		this.departmentRights = departmentRights;
	}

}
