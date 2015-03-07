// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.tax;

import com.google.inject.Inject;
import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.service.DJService;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import java.util.Iterator;

/**
 * @author $Author$
 * 
 */
public class CompanyWithTaxDataProvider implements IDataProvider<Company> {
	private static final long serialVersionUID = 1L;

	private String companyName;

	@Inject
	private DJService service;

	public CompanyWithTaxDataProvider(DJService service) {
		this.service = service;
	}

	public CompanyWithTaxDataProvider(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public void detach() {

	}

	@Override
	public Iterator<? extends Company> iterator(long first, long count) {
		return service.searchCompanyWithTax(companyName, count, first)
				.iterator();
	}

	@Override
	public long size() {
		return service.getCompanyWithTaxTotalCount(companyName);
	}

	@Override
	public IModel<Company> model(Company object) {
		return new CompoundPropertyModel<Company>(object);
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
