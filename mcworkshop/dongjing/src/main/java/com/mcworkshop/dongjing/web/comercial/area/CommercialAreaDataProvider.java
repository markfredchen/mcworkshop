// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: CommercialAreaDataProvider.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.web.comercial.area;

import java.util.Iterator;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.google.inject.Inject;
import com.mcworkshop.dongjing.domain.CommercialArea;
import com.mcworkshop.dongjing.domain.CommercialAreaType;
import com.mcworkshop.dongjing.service.DJService;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public class CommercialAreaDataProvider implements
		IDataProvider<CommercialArea> {

	private static final long serialVersionUID = 1L;

	private String name;
	private CommercialAreaType type;

	@Inject
	private DJService service;

	public CommercialAreaDataProvider(DJService service) {
		this.service = service;
	}

	@Override
	public void detach() {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<? extends CommercialArea> iterator(long first, long count) {
		return service.searchCommercialArea(name, type, count, first)
				.iterator();
	}

	@Override
	public long size() {
		return service.getCommercialAreaTotalCount(name, type);
	}

	@Override
	public IModel<CommercialArea> model(CommercialArea object) {
		return new CompoundPropertyModel<CommercialArea>(object);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CommercialAreaType getType() {
		return type;
	}

	public void setType(CommercialAreaType type) {
		this.type = type;
	}

}
