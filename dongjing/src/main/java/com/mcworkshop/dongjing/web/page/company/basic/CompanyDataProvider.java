// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.basic;

import java.util.Iterator;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.google.inject.Inject;
import com.mcworkshop.dongjing.domain.AffiliateBlock;
import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.domain.EconomyEntity;
import com.mcworkshop.dongjing.domain.EconomyNature;
import com.mcworkshop.dongjing.service.DJService;

/**
 * @author $Author$
 * 
 */
public class CompanyDataProvider implements IDataProvider<Company> {

	private static final long serialVersionUID = 1L;

	private String companyName;
	private EconomyEntity economyEntity;
	private EconomyNature economyNature;
	private AffiliateBlock block;

	@Inject
	private DJService service;

	public CompanyDataProvider(DJService service) {
		this.service = service;
	}

	public CompanyDataProvider(String companyName, EconomyEntity economyEntity,
			EconomyNature economyNature, AffiliateBlock block) {
		this.companyName = companyName;
		this.economyEntity = economyEntity;
		this.economyNature = economyNature;
		this.block = block;
	}

	@Override
	public void detach() {

	}

	@Override
	public Iterator<? extends Company> iterator(long first, long count) {
		return service.searchCompany(companyName, economyEntity, economyNature,
				block, false, count, first).iterator();
	}

	@Override
	public long size() {
		return service.getCompanyTotalCount(companyName, economyEntity,
				economyNature, block);
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

	public EconomyEntity getEconomyEntity() {
		return economyEntity;
	}

	public void setEconomyEntity(EconomyEntity economyEntity) {
		this.economyEntity = economyEntity;
	}

	public EconomyNature getEconomyNature() {
		return economyNature;
	}

	public void setEconomyNature(EconomyNature economyNature) {
		this.economyNature = economyNature;
	}

	public AffiliateBlock getBlock() {
		return block;
	}

	public void setBlock(AffiliateBlock block) {
		this.block = block;
	}

}
