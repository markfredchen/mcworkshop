// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.security;

import com.google.inject.Inject;
import com.mcworkshop.dongjing.domain.Security;
import com.mcworkshop.dongjing.service.DJService;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import java.util.Iterator;

/**
 * @author $Author$
 * 
 */
public class SecurityDataProvider implements IDataProvider<Security> {

	private static final long serialVersionUID = 1L;

	private String companyName;

	@Inject
	private DJService service;

	public SecurityDataProvider(DJService service) {
		this.service = service;
	}

	public SecurityDataProvider(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public void detach() {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<? extends Security> iterator(long first, long count) {
		return service.searchSecurityInfo(companyName, count,
				first).iterator();
	}

	@Override
	public long size() {
		return service.getSecurityInfoTotalCount(companyName);
	}

	@Override
	public IModel<Security> model(Security object) {
		return new CompoundPropertyModel<Security>(object);
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


}
