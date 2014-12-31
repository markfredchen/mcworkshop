// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service;

import com.mcworkshop.dongjing.domain.*;
import com.mcworkshop.dongjing.service.report.model.OverallReportData;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author $Author$
 * 
 */
public interface DJService {

	void importCompanySaleTaxData(TaxData data, List<String> errorCompanies);

	void importCompanyCombinedDate(int year, int month);

	void clearCompanySaleTaxData(int year, int month);

	void importCompany(Company company);

	List<TaxData> getSaleTaxReportData(int year, int month);

	void createCompany(Company company);

	void updateCompany(Company company, Company orginalCompany);

	public List<Company> searchCompany(String name,
			EconomyEntity economyEntity, EconomyNature economyNature,
			AffiliateBlock block, boolean allCompany, long fetch, long start);

	int getCompanyTotalCount(String name, EconomyEntity economyEntity,
			EconomyNature economyNature, AffiliateBlock block);

	int getCompanyWithTaxTotalCount(String name);

	List<Calendar> getSaleTaxReportAvailableMonths();

	Company getCompany(Long companyID);

	OverallReportData getOverallMonthReportData(int year, int month);

	TaxData getTaxDetail(int year, int month, long companyID);

	OverallReportData getCompanyEconomyEntityReport(int year, int month);

	List<Company> searchCompanyWithTax(String name, long fetch, long start);

	void createProject(Project project);

	List<Project> searchProjects(String name, ProjectStatus status, long fetch,
			long start);

	int getProjectTotalCount(String name, ProjectStatus status);

	Project getProject(Long projectID);

	void updateProject(Project project);

	void deletePartyMember(Long memberID);

	void deleteCompanyMember(Long memberID);

	public List<Security> searchSecurityInfo(String name,
			EconomyEntity economyEntity, long fetch, long start);

	int getSecurityInfoTotalCount(String name, EconomyEntity economyEntity);

	public List<AgricultureInfo> searchAgricultureInfo(String name, long fetch,
			long start);

	int getAgricultureInfoTotalCount(String name);

	public List<CommercialArea> searchCommercialArea(String name,
			CommercialAreaType type, long fetch, long start);

	int getCommercialAreaTotalCount(String name, CommercialAreaType type);

	Map<Long, Double> getEconomyNaturePersonalIncomingTaxData(int year,
			int month);

	Map<String, Double> getTop10VATCompany(int year, int month);

	Map<String, Double> getEconomyEntityVAT(int year, int month);

	Map<String, String> getCompanyEmail(List<Long> companyIDs);

    List<Company> getCompaniesForExport(AffiliateBlock block);
}
