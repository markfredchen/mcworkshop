// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.agriculture;

import com.google.inject.Inject;
import com.mcworkshop.dongjing.domain.AgricultureInfo;
import com.mcworkshop.dongjing.service.DJService;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import java.util.Iterator;

/**
 * @author $Author$
 * 
 */
public class AgricultureDataProvider implements IDataProvider<AgricultureInfo> {

	private static final long serialVersionUID = 1L;

	private String name;

	@Inject
	private DJService service;

	public AgricultureDataProvider(DJService service) {
		this.service = service;
	}

	@Override
	public void detach() {

	}

	@Override
	public Iterator<? extends AgricultureInfo> iterator(long first, long count) {
		return service.searchAgricultureInfo(name, count, first).iterator();
	}

	@Override
	public long size() {
		return service.getAgricultureInfoTotalCount(name);
	}

	@Override
	public IModel<AgricultureInfo> model(AgricultureInfo object) {
		return new CompoundPropertyModel<AgricultureInfo>(object);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
