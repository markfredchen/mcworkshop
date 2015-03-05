// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service;

import com.google.inject.Inject;
import com.mcworkshop.common.search.Restriction;
import com.mcworkshop.common.search.SearchCriteria;
import com.mcworkshop.common.search.SearchFilter;
import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.dongjing.domain.*;
import com.mcworkshop.dongjing.persistence.CommercialAreaMapper;
import com.mcworkshop.dongjing.persistence.DJServiceMapper;
import com.mcworkshop.dongjing.persistence.SecurityMapper;
import com.mcworkshop.dongjing.persistence.po.AnnualOutputPO;
import com.mcworkshop.dongjing.persistence.po.RentStatusPO;
import com.mcworkshop.dongjing.persistence.search.*;
import com.mcworkshop.dongjing.security.ACLBlockUtil;
import com.mcworkshop.dongjing.service.report.model.OverallMonthData;
import com.mcworkshop.dongjing.service.report.model.OverallReportData;
import org.apache.wicket.util.string.Strings;
import org.mybatis.guice.transactional.Transactional;

import java.util.*;

/**
 * @author $Author$
 */
public class DJServiceImpl implements DJService {

    @Inject
    private DJServiceMapper mapper;

    @Inject
    private SecurityMapper sm;

    @Inject
    private CommercialAreaMapper area;

    @Override
    public void importCompanySaleTaxData(TaxData data,
                                         List<String> errorCompanies) {
        Company company = this.mapper.getCompanyByName(data.getCompany()
                .getName());
        if (company != null) {
            data.getCompany().setCompanyID(company.getCompanyID());
            data.setEconomyEntity(company.getEconomyEntity());
            data.setEconomyNature(company.getEconomyNature());
            data.setTaxOrg(company.getTaxOrg());
            if (company.getIndustryType() != null) {
                data.setMYC(company.getIndustryType().toUpperCase()
                        .equals("MYC"));
            }
            data.setAffiliateRegion(company.getAffiliateRegion());
            this.mapper.importTaxData(data);
        } else {
            errorCompanies.add(data.getCompany().getName());
        }
    }

    @Override
    public void importCompanyCombinedDate(int year, int month) {
        Date endDate = new Date(new GregorianCalendar(year, month, 0).getTime()
                .getTime());
        Date startDate = new Date(new GregorianCalendar(year, month - 1, 1)
                .getTime().getTime());
        List<TaxData> taxDatas = this.mapper.getMonthlyTaxReport(year, month);
        OverallMonthData data = new OverallMonthData();
        data.setYear(year);
        data.setMonth(month);
        for (TaxData taxData : taxDatas) {
            if (taxData.getCompany().getEstablishDate() != null
                    && taxData.getCompany().getEstablishDate().getTime() > startDate
                    .getTime()
                    && taxData.getCompany().getEstablishDate().getTime() < endDate
                    .getTime()) {
                Double regAssets = taxData.getCompany().getRegisterAsset();
                data.addVat(taxData.getVat());
                data.addOperateTax(taxData.getTotalCompanyIncomeTax());
                data.addIncomingTax(taxData.getTotalCompanyIncomeTax());
                data.addConstructTax(taxData.getConstructionTax());
                data.addOtherTax(taxData.getTotalOtherTax());
                data.addSalesTotal(taxData.getSales());
                if (taxData.getCompany().getEconomyEntity() == null) {

                } else if (taxData.getCompany().getEconomyEntity()
                        .equals(EconomyEntity.INDUSTRY)) {
                    data.addMerchantIndustry();
                    data.addRegAssetsIndustry(regAssets);
                } else if (taxData.getCompany().getEconomyEntity()
                        .equals(EconomyEntity.COMMERCIAL)) {
                    data.addMerchantCommercial();
                    data.addRegAssetsCommercial(regAssets);
                } else if (taxData.getCompany().getEconomyEntity()
                        .equals(EconomyEntity.SERVICE)) {
                    data.addMerchantService();
                    data.addRegAssetsService(regAssets);
                } else if (taxData.getCompany().getEconomyEntity()
                        .equals(EconomyEntity.CONSTRUCTION)) {
                    data.addMerchantConstruct();
                    data.addRegAssetsConstruct(regAssets);
                } else if (taxData.getCompany().getEconomyEntity()
                        .equals(EconomyEntity.HOUSEHOLDING)) {
                    data.addMerchantHouseHolding();
                    data.addRegAssetsHouseholding(regAssets);
                }
            }
            if (taxData.getCompany().getEconomyNature() == null) {

            } else if (taxData.getCompany().getEconomyNature()
                    .equals(EconomyNature.PRIVATE)) {
                data.addPrivateCompanyVAT(taxData.getVat());
                data.addPrivateCompanyOperateTax(taxData
                        .getTotalCompanyIncomeTax());
                data.addPrivateCompanyIncomingTax(taxData
                        .getTotalCompanyIncomeTax());
                data.addPrivateCompanyConstructTax(taxData.getConstructionTax());
                data.addPrivateCompanyPersonalIncomingTax(taxData
                        .getPersonalIncomingTax());
                data.addPrivateCompanyOtherTax(taxData.getTotalOtherTax());
            } else if (taxData.getCompany().getEconomyNature()
                    .equals(EconomyNature.FOREIGN)) {
                data.addPublicCompanyVAT(taxData.getVat());
                data.addPublicCompanyOperateTax(taxData
                        .getTotalCompanyIncomeTax());
                data.addPublicCompanyIncomingTax(taxData
                        .getTotalCompanyIncomeTax());
                data.addPublicCompanyConstructTax(taxData.getConstructionTax());
                data.addPublicCompanyPersonalIncomingTax(taxData
                        .getPersonalIncomingTax());
                data.addPublicCompanyOtherTax(taxData.getTotalOtherTax());
            } else if (taxData.getCompany().getEconomyNature()
                    .equals(EconomyNature.GROUP)) {
                data.addGroupCompanyVAT(taxData.getVat());
                data.addGroupCompanyOperateTax(taxData
                        .getTotalCompanyIncomeTax());
                data.addGroupCompanyIncomingTax(taxData
                        .getTotalCompanyIncomeTax());
                data.addGroupCompanyConstructTax(taxData.getConstructionTax());
                data.addGroupCompanyPersonalIncomingTax(taxData
                        .getPersonalIncomingTax());
                data.addGroupCompanyOtherTax(taxData.getTotalOtherTax());
            }
            if (taxData.getCompany().getEconomyEntity() == null) {

            } else if (taxData.getCompany().getEconomyEntity()
                    .equals(EconomyEntity.INDUSTRY)) {
                data.addIndustryCount();
                data.addIndustryVAT(taxData.getVat());
                data.addIndustryOperateTax(taxData.getOperateTax());
                data.addIndustryIncomingTax(taxData.getTotalCompanyIncomeTax());
                data.addIndustryPersonalIncomingTax(taxData
                        .getPersonalIncomingTax());
                data.addIndustryConstructTax(taxData.getConstructionTax());
                data.addIndustryOtherTax(taxData.getOtherTax());
                data.addIndustrySale(taxData.getSales());
            } else if (taxData.getCompany().getEconomyEntity()
                    .equals(EconomyEntity.COMMERCIAL)) {
                data.addCommercialCount();
                data.addCommercialVAT(taxData.getVat());
                data.addCommercialOperateTax(taxData.getOperateTax());
                data.addCommercialIncomingTax(taxData
                        .getTotalCompanyIncomeTax());
                data.addCommercialPersonalIncomingTax(taxData
                        .getPersonalIncomingTax());
                data.addCommercialConstructTax(taxData.getConstructionTax());
                data.addCommercialOtherTax(taxData.getOtherTax());
                data.addCommercialSale(taxData.getSales());
            } else if (taxData.getCompany().getEconomyEntity()
                    .equals(EconomyEntity.CONSTRUCTION)) {
                data.addConstructCount();
                data.addConstructVAT(taxData.getVat());
                data.addConstructOperateTax(taxData.getOperateTax());
                data.addConstructIncomingTax(taxData.getTotalCompanyIncomeTax());
                data.addConstructPersonalIncomingTax(taxData
                        .getPersonalIncomingTax());
                data.addConstructConstructTax(taxData.getConstructionTax());
                data.addConstructOtherTax(taxData.getOtherTax());
                data.addConstructSale(taxData.getSales());
            } else if (taxData.getCompany().getEconomyEntity()
                    .equals(EconomyEntity.SERVICE)) {
                data.addServiceCount();
                data.addServiceVAT(taxData.getVat());
                data.addServiceOperateTax(taxData.getOperateTax());
                data.addServiceIncomingTax(taxData.getTotalCompanyIncomeTax());
                data.addServicePersonalIncomingTax(taxData
                        .getPersonalIncomingTax());
                data.addServiceConstructTax(taxData.getConstructionTax());
                data.addServiceOtherTax(taxData.getOtherTax());
                data.addServiceSale(taxData.getSales());
            } else if (taxData.getCompany().getEconomyEntity()
                    .equals(EconomyEntity.HOUSEHOLDING)) {
                data.addHouseHoldingCount();
                data.addHouseHoldingVAT(taxData.getVat());
                data.addHouseHoldingOperateTax(taxData.getOperateTax());
                data.addHouseHoldingIncomingTax(taxData
                        .getTotalCompanyIncomeTax());
                data.addHouseHoldingPersonalIncomingTax(taxData
                        .getPersonalIncomingTax());
                data.addHouseHoldingConstructTax(taxData.getConstructionTax());
                data.addHouseHoldingOtherTax(taxData.getOtherTax());
                data.addHouseHoldingSale(taxData.getSales());
            }
        }
        data.setMerchantSignOffCompanies(this.mapper.getSignedOffCompanyCount(
                startDate, endDate));
        this.mapper.insertMonthlyReportData(data);
    }

    @Override
    public List<TaxData> getSaleTaxReportData(int year, int month) {
        return this.mapper.getMonthTaxReport(year, month);
    }

    @Override
    public void clearCompanySaleTaxData(int year, int month) {
        this.mapper.clearTaxDataByMonth(year, month);
        this.mapper.clearMonthlyStatisticData(year, month);
    }

    @Override
    @Transactional
    public void createCompany(Company company) {
        if (!company.getOwnerContact().isEmpty()) {
            this.mapper.createCompanyContact(company.getOwnerContact());
        }
        if (!company.getOtherContact().isEmpty()) {
            this.mapper.createCompanyContact(company.getOtherContact());
        }
        this.mapper.createCompany(company);
        for (CompanyMember member : company.getMembers()) {
            member.setCompanyID(company.getCompanyID());
            this.mapper.createCompanyMemeber(member);
        }
        for (PartyMember member : company.getPartyMembers()) {
            member.setCompanyID(company.getCompanyID());
            this.mapper.createPartyMember(member);
        }
        for (AnnualOutput output : company.getOutputs()) {
            this.mapper.createAnnualOutput(company.getCompanyID(), output);
        }
        for (NatureShareholder ns : company.getNatureShareholders()) {
            ns.setCompanyID(company.getCompanyID());
            this.mapper.createNatureShareholder(ns);
        }

        for (CompanyShareholder ns : company.getCompanyShareholders()) {
            ns.setCompanyID(company.getCompanyID());
            this.mapper.createCompanyShareholder(ns);
        }

        for (RentStatus rs : company.getRentors()) {
            rs.setRentee(company);
            this.mapper.createRentStatus(rs);
        }

    }

    @Override
    public List<Company> searchCompany(String name,
                                       EconomyEntity economyEntity, EconomyNature economyNature,
                                       AffiliateBlock block, boolean allCompany, long fetch, long start) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.getFields().add(CompanySearchField.COMPANY_ID.value());
        criteria.getFields().add(CompanySearchField.NAME.value());
        criteria.getFields().add(CompanySearchField.ECONOMY_ENTITY.value());
        criteria.getFields().add(CompanySearchField.ECONOMY_NATURE.value());
        criteria.getFields().add(CompanySearchField.AFFILIATE_BLOCK.value());
        if (!Strings.isEmpty(name)) {
            SearchFilter nameFilter = new SearchFilter();
            nameFilter.setFilterRestriction(Restriction.AND);
            nameFilter.setClauseRestriction(Restriction.CONTAIN);
            nameFilter.setSearchField("name");
            nameFilter.setValue(name);
            criteria.getFilters().add(nameFilter);
        }
        if (economyEntity != null) {
            SearchFilter economyEntityFilter = new SearchFilter();
            economyEntityFilter.setFilterRestriction(Restriction.AND);
            economyEntityFilter.setClauseRestriction(Restriction.EQUAL);
            economyEntityFilter.setSearchField("economyEntityID");
            economyEntityFilter.setValue(SystemEnumeration.getInstance()
                    .getIdByKey(economyEntity).toString());
            economyEntityFilter.setValueClass(Long.class);
            criteria.getFilters().add(economyEntityFilter);
        }
        if (economyNature != null) {
            SearchFilter economyNatureFilter = new SearchFilter();
            economyNatureFilter.setFilterRestriction(Restriction.AND);
            economyNatureFilter.setClauseRestriction(Restriction.EQUAL);
            economyNatureFilter.setSearchField("economyNatureID");
            economyNatureFilter.setValue(SystemEnumeration.getInstance()
                    .getIdByKey(economyNature).toString());
            economyNatureFilter.setValueClass(Long.class);
            criteria.getFilters().add(economyNatureFilter);
        }
        if (block == null && allCompany) {

        } else if (block == null && ACLBlockUtil.getOwnAffiliateBlock() != null) {
            SearchFilter blockFilter = new SearchFilter();
            blockFilter.setFilterRestriction(Restriction.AND);
            blockFilter.setClauseRestriction(Restriction.EQUAL);
            blockFilter.setSearchField("regionID");
            blockFilter
                    .setValue(SystemEnumeration.getInstance()
                            .getIdByKey(ACLBlockUtil.getOwnAffiliateBlock())
                            .toString());
            blockFilter.setValueClass(Long.class);
            criteria.getFilters().add(blockFilter);
        } else if (block != null) {
            SearchFilter blockFilter = new SearchFilter();
            blockFilter.setFilterRestriction(Restriction.AND);
            blockFilter.setClauseRestriction(Restriction.EQUAL);
            blockFilter.setSearchField("regionID");
            blockFilter.setValue(SystemEnumeration.getInstance()
                    .getIdByKey(block).toString());
            blockFilter.setValueClass(Long.class);
            criteria.getFilters().add(blockFilter);
        }
        criteria.setFetch(fetch);
        criteria.setStartIndex(start);
        List<Map<String, Object>> results = this.mapper.searchCompany(criteria);
        List<Company> companies = new ArrayList<Company>();
        for (Map<String, Object> map : results) {
            Company company = new Company();
            company.setCompanyID((Long) map.get("companyID"));
            company.setName((String) map.get("name"));
            company.setEconomyEntityID((Long) map.get("economyEntityID"));
            company.setEconomyNatureID((Long) map.get("economyNatureID"));
            company.setRegionID((Long) map.get("regionID"));
            companies.add(company);
        }
        return companies;
    }

    @Override
    public List<Company> searchCompanyWithTax(String name, long fetch,
                                              long start) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.getFields().add(CompanySearchField.COMPANY_ID.value());
        criteria.getFields().add(CompanySearchField.NAME.value());
        if (!Strings.isEmpty(name)) {
            SearchFilter nameFilter = new SearchFilter();
            nameFilter.setFilterRestriction(Restriction.AND);
            nameFilter.setClauseRestriction(Restriction.CONTAIN);
            nameFilter.setSearchField("c.name");
            nameFilter.setValue(name);
            criteria.getFilters().add(nameFilter);
        }
        if (ACLBlockUtil.getOwnAffiliateBlock() != null) {
            SearchFilter blockFilter = new SearchFilter();
            blockFilter.setFilterRestriction(Restriction.AND);
            blockFilter.setClauseRestriction(Restriction.EQUAL);
            blockFilter.setSearchField("regionID");
            blockFilter
                    .setValue(SystemEnumeration.getInstance()
                            .getIdByKey(ACLBlockUtil.getOwnAffiliateBlock())
                            .toString());
            blockFilter.setValueClass(Long.class);
            criteria.getFilters().add(blockFilter);
        }
        criteria.setFetch(fetch);
        criteria.setStartIndex(start);
        List<Map<String, Object>> results = this.mapper
                .searchCompanyWithTax(criteria);
        List<Company> companies = new ArrayList<Company>();
        for (Map<String, Object> map : results) {
            Company company = new Company();
            company.setCompanyID((Long) map.get("companyID"));
            company.setName((String) map.get("name"));
            companies.add(company);
        }
        return companies;
    }

    @Override
    public int getCompanyTotalCount(String name, EconomyEntity economyEntity,
                                    EconomyNature economyNature, AffiliateBlock block) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.getFields().add(CompanySearchField.COMPANY_ID.value());
        criteria.getFields().add(CompanySearchField.NAME.value());
        criteria.getFields().add(CompanySearchField.ECONOMY_ENTITY.value());
        criteria.getFields().add(CompanySearchField.ECONOMY_NATURE.value());
        if (!Strings.isEmpty(name)) {
            SearchFilter nameFilter = new SearchFilter();
            nameFilter.setFilterRestriction(Restriction.AND);
            nameFilter.setClauseRestriction(Restriction.CONTAIN);
            nameFilter.setSearchField("c.name");
            nameFilter.setValue(name);
            criteria.getFilters().add(nameFilter);
        }
        if (economyEntity != null) {
            SearchFilter economyEntityFilter = new SearchFilter();
            economyEntityFilter.setFilterRestriction(Restriction.AND);
            economyEntityFilter.setClauseRestriction(Restriction.EQUAL);
            economyEntityFilter.setSearchField("economyEntityID");
            economyEntityFilter.setValue(SystemEnumeration.getInstance()
                    .getIdByKey(economyEntity).toString());
            economyEntityFilter.setValueClass(Long.class);
            criteria.getFilters().add(economyEntityFilter);
        }
        if (economyNature != null) {
            SearchFilter economyNatureFilter = new SearchFilter();
            economyNatureFilter.setFilterRestriction(Restriction.AND);
            economyNatureFilter.setClauseRestriction(Restriction.EQUAL);
            economyNatureFilter.setSearchField("economyNatureID");
            economyNatureFilter.setValue(SystemEnumeration.getInstance()
                    .getIdByKey(economyNature).toString());
            economyNatureFilter.setValueClass(Long.class);
            criteria.getFilters().add(economyNatureFilter);
        }
        if (block == null && ACLBlockUtil.getOwnAffiliateBlock() != null) {
            SearchFilter blockFilter = new SearchFilter();
            blockFilter.setFilterRestriction(Restriction.AND);
            blockFilter.setClauseRestriction(Restriction.EQUAL);
            blockFilter.setSearchField("regionID");
            blockFilter
                    .setValue(SystemEnumeration.getInstance()
                            .getIdByKey(ACLBlockUtil.getOwnAffiliateBlock())
                            .toString());
            blockFilter.setValueClass(Long.class);
            criteria.getFilters().add(blockFilter);
        } else if (block != null) {
            SearchFilter blockFilter = new SearchFilter();
            blockFilter.setFilterRestriction(Restriction.AND);
            blockFilter.setClauseRestriction(Restriction.EQUAL);
            blockFilter.setSearchField("regionID");
            blockFilter.setValue(SystemEnumeration.getInstance()
                    .getIdByKey(block).toString());
            blockFilter.setValueClass(Long.class);
            criteria.getFilters().add(blockFilter);
        }
        return this.mapper.getCompanyTotalCount(criteria);
    }

    @Override
    public int getCompanyWithTaxTotalCount(String name) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.getFields().add(CompanySearchField.COMPANY_ID.value());
        criteria.getFields().add(CompanySearchField.NAME.value());
        if (!Strings.isEmpty(name)) {
            SearchFilter nameFilter = new SearchFilter();
            nameFilter.setFilterRestriction(Restriction.AND);
            nameFilter.setClauseRestriction(Restriction.CONTAIN);
            nameFilter.setSearchField("c.name");
            nameFilter.setValue(name);
            criteria.getFilters().add(nameFilter);
        }
        return this.mapper.getCompanyWithTaxTotal(criteria);
    }

    public List<Calendar> getSaleTaxReportAvailableMonths() {
        List<Calendar> months = new ArrayList<Calendar>();
        List<Map<String, Integer>> monthMapList = this.mapper
                .getSaleTaxReportAvailableMonths();
        for (Map<String, Integer> monthMap : monthMapList) {
            int year = monthMap.get("year");
            int month = monthMap.get("month");
            Calendar c = Calendar.getInstance();
            c.set(Calendar.MONTH, month - 1);
            c.set(Calendar.YEAR, year);
            months.add(c);

        }
        return months;
    }

    @Override
    public void updateCompany(Company company, Company orginalCompany) {
        if (orginalCompany.getOwnerContact() == null) {

        } else if (orginalCompany.getOwnerContact().getContactID() != null) {
            company.getOwnerContact().setContactID(
                    orginalCompany.getOwnerContact().getContactID());
            this.mapper.updateCompanyContact(company.getOwnerContact());
        } else if (orginalCompany.getOwnerContact().getContactID() == null
                && !company.getOwnerContact().isEmpty()) {
            this.mapper.createCompanyContact(company.getOwnerContact());
        }
        if (orginalCompany.getOtherContact().getContactID() != null) {
            company.getOtherContact().setContactID(
                    orginalCompany.getOtherContact().getContactID());
            this.mapper.updateCompanyContact(company.getOtherContact());
        } else if (orginalCompany.getOtherContact().getContactID() == null
                && !company.getOtherContact().isEmpty()) {
            this.mapper.createCompanyContact(company.getOtherContact());
        }
        this.mapper.updateCompany(company);
        this.mapper.updateTaxData(company.getCompanyID(),
                company.getTaxOrgID(), company.getEconomyEntityID(),
                company.getEconomyNatureID());
        for (CompanyMember member : company.getMembers()) {
            if (member.getCompanyID() == null) {
                member.setCompanyID(company.getCompanyID());
                this.mapper.createCompanyMemeber(member);
            } else {
                this.mapper.updateCompanyMember(member);
            }
        }
        for (PartyMember member : company.getPartyMembers()) {
            if (member.getCompanyID() == null) {
                member.setCompanyID(company.getCompanyID());
                this.mapper.createPartyMember(member);
            } else {
                this.mapper.updatePartyMember(member);
            }
        }

        for (AnnualOutput output : company.getOutputs()) {
            if (output.getOutputID() == null) {
                this.mapper.createAnnualOutput(company.getCompanyID(), output);
            } else {
                this.mapper.updateAnnualOutput(output);
            }
        }

        for (NatureShareholder ns : company.getNatureShareholders()) {
            ns.setCompanyID(company.getCompanyID());
            if (ns.getContactID() == null) {
                this.mapper.createNatureShareholder(ns);
            } else {
                this.mapper.updateNatureShareholder(ns);
            }
        }

        for (CompanyShareholder ns : company.getCompanyShareholders()) {
            ns.setCompanyID(company.getCompanyID());
            if (ns.getContactID() == null) {
                this.mapper.createCompanyShareholder(ns);
            } else {
                this.mapper.updateCompanyShareholder(ns);
            }
        }

        this.mapper.clearRentors(company.getCompanyID());
        for (RentStatus rs : company.getRentors()) {
            rs.setRentee(company);
            this.mapper.createRentStatus(rs);
        }
    }

    @Override
    public Company getCompany(Long companyID) {
        return this.mapper.getCompany(companyID);
    }

    @Override
    public void importCompany(Company company) {
        Company persisted = this.mapper.getCompanyByName(company.getName());
        if (persisted == null) {
            this.createCompany(company);
        }
    }

    @Override
    public OverallReportData getOverallMonthReportData(int year, int month) {
        OverallReportData data = new OverallReportData();
        data.setMonthData(this.mapper.getOverallMonthData(year, month));
        data.setYearData(this.mapper.getOverallYearData(year));
        return data;
    }

    @Override
    public TaxData getTaxDetail(int year, int month, long companyID) {
        return this.mapper.getTaxDetail(year, month, companyID);
    }

    @Override
    public OverallReportData getCompanyEconomyEntityReport(int year, int month) {
        OverallReportData data = this.getOverallMonthReportData(year, month);
        Map<String, Long> count = this.mapper.getCompanyEconomyEntityCounts();
        data.setIndustryCount(count.get("industryCount"));
        data.setCommercialCount(count.get("commercialCount"));
        data.setConstructCount(count.get("constructCount"));
        data.setServiceCount(count.get("serviceCount"));
        data.setHouseHoldingCount(count.get("houseHoldingCount"));
        Map<String, Long> monthCount = this.mapper
                .getCompanyEconomyEntityMonthCounts(year, month);
        data.setIndustryMonthCount(monthCount.get("industryMonthCount"));
        data.setCommercialMonthCount(monthCount.get("commercialMonthCount"));
        data.setConstructMonthCount(monthCount.get("constructMonthCount"));
        data.setServiceMonthCount(monthCount.get("serviceMonthCount"));
        data.setHouseHoldingMonthCount(monthCount.get("houseHoldingMonthCount"));
        Map<String, Double> map = this.mapper
                .getOverallYearTaxData(year, month);
        data.getYearData().setTotalSale(map.get("Sale"));
        data.getYearData().setTotalVAT(map.get("VAT"));
        data.getYearData().setTotalOperateTax(map.get("OperateTax"));
        data.getYearData().setTotalIncomingTax(map.get("IncomingTax"));
        data.getYearData().setTotalPersonalIncomingTax(
                map.get("PersonalIncomingTax"));
        data.getYearData().setTotalConstructTax(map.get("ConstructTax"));
        data.getYearData().setTotalOtherTax(map.get("OtherTax"));
        map = this.mapper.getOverallYearTaxData(year - 1, month);
        if (map != null && map.size() > 0) {
            data.getLastYearData().setTotalSale(map.get("Sale"));
            data.getLastYearData().setTotalVAT(map.get("VAT"));
            data.getLastYearData().setTotalOperateTax(map.get("OperateTax"));
            data.getLastYearData().setTotalIncomingTax(map.get("IncomingTax"));
            data.getLastYearData().setTotalPersonalIncomingTax(
                    map.get("PersonalIncomingTax"));
            data.getLastYearData()
                    .setTotalConstructTax(map.get("ConstructTax"));
            data.getLastYearData().setTotalOtherTax(map.get("OtherTax"));
        }
        return data;
    }

    @Override
    public void createProject(Project project) {
        mapper.createProject(project);
        for (Company company : project.getRelatedCompanies()) {
            mapper.createRelatedProject(project.getProjectID(),
                    company.getCompanyID());
        }
    }

    @Override
    public List<Project> searchProjects(String name, ProjectStatus status,
                                        long fetch, long start) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.getFields().add(ProjectSearchField.PROJECT_ID.value());
        criteria.getFields().add(ProjectSearchField.NAME.value());
        criteria.getFields().add(ProjectSearchField.STATUS.value());
        if (!Strings.isEmpty(name)) {
            SearchFilter nameFilter = new SearchFilter();
            nameFilter.setFilterRestriction(Restriction.AND);
            nameFilter.setClauseRestriction(Restriction.CONTAIN);
            nameFilter.setSearchField("name");
            nameFilter.setValue(name);
            criteria.getFilters().add(nameFilter);
        }
        if (status != null) {
            SearchFilter codeFilter = new SearchFilter();
            codeFilter.setFilterRestriction(Restriction.AND);
            codeFilter.setClauseRestriction(Restriction.EQUAL);
            codeFilter.setSearchField("status");
            codeFilter.setValue(SystemEnumeration.getInstance()
                    .getIdByKey(status).toString());
            criteria.getFilters().add(codeFilter);
        }
        criteria.setFetch(fetch);
        criteria.setStartIndex(start);
        return this.mapper.searchProjects(criteria);
    }

    @Override
    public int getProjectTotalCount(String name, ProjectStatus status) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.getFields().add(ProjectSearchField.PROJECT_ID.value());
        criteria.getFields().add(ProjectSearchField.NAME.value());
        criteria.getFields().add(ProjectSearchField.STATUS.value());
        if (!Strings.isEmpty(name)) {
            SearchFilter nameFilter = new SearchFilter();
            nameFilter.setFilterRestriction(Restriction.AND);
            nameFilter.setClauseRestriction(Restriction.CONTAIN);
            nameFilter.setSearchField("name");
            nameFilter.setValue(name);
            criteria.getFilters().add(nameFilter);
        }
        if (status != null) {
            SearchFilter codeFilter = new SearchFilter();
            codeFilter.setFilterRestriction(Restriction.AND);
            codeFilter.setClauseRestriction(Restriction.EQUAL);
            codeFilter.setSearchField("status");
            codeFilter.setValue(SystemEnumeration.getInstance()
                    .getIdByKey(status).toString());
            criteria.getFilters().add(codeFilter);
        }
        return mapper.getProjectTotalCount(criteria);
    }

    @Override
    public Project getProject(Long projectID) {
        return mapper.getProject(projectID);
    }

    @Override
    public void updateProject(Project project) {
        mapper.updateProject(project);
        mapper.deleteRelatedCompanies(project.getProjectID());
        for (Company company : project.getRelatedCompanies()) {
            mapper.createRelatedProject(project.getProjectID(),
                    company.getCompanyID());
        }
    }

    @Override
    public void deletePartyMember(Long memberID) {
        mapper.deleteCompanyMember(memberID);
    }

    @Override
    public void deleteCompanyMember(Long memberID) {
        mapper.deletePartyMember(memberID);
    }

    @Override
    public List<Security> searchSecurityInfo(String name, long fetch, long start) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.getFields().add(
                SecurityInfoSearchField.SECURITY_INFO_ID.value());
        criteria.getFields().add(SecurityInfoSearchField.NAME.value());
        if (!Strings.isEmpty(name)) {
            SearchFilter nameFilter = new SearchFilter();
            nameFilter.setFilterRestriction(Restriction.AND);
            nameFilter.setClauseRestriction(Restriction.CONTAIN);
            nameFilter.setSearchField("name");
            nameFilter.setValue(name);
            criteria.getFilters().add(nameFilter);
        }
        criteria.setFetch(fetch);
        criteria.setStartIndex(start);
        List<Map<String, Object>> results = this.sm
                .searchSecurityInfo(criteria);
        List<Security> securities = new ArrayList<Security>();
        for (Map<String, Object> map : results) {
            Security security = new Security();
            security.setSecurityInfoID((Long) map.get("securityInfoID"));
            security.setName((String) map.get("name"));
            securities.add(security);
        }
        return securities;
    }

    @Override
    public int getSecurityInfoTotalCount(String name) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.getFields().add(
                SecurityInfoSearchField.SECURITY_INFO_ID.value());
        criteria.getFields().add(SecurityInfoSearchField.NAME.value());
        if (!Strings.isEmpty(name)) {
            SearchFilter nameFilter = new SearchFilter();
            nameFilter.setFilterRestriction(Restriction.AND);
            nameFilter.setClauseRestriction(Restriction.CONTAIN);
            nameFilter.setSearchField("name");
            nameFilter.setValue(name);
            criteria.getFilters().add(nameFilter);
        }
        return this.sm.getSecurityInfoTotalCount(criteria);
    }

    @Override
    public List<AgricultureInfo> searchAgricultureInfo(String name, long fetch,
                                                       long start) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.getFields().add(
                AgricultureInfoSearchField.AGRICULTURE_ID.value());
        criteria.getFields().add(AgricultureInfoSearchField.NAME.value());
        criteria.getFields().add(
                AgricultureInfoSearchField.CONTACT_NUMBER.value());
        criteria.getFields().add(AgricultureInfoSearchField.AREA.value());
        if (!Strings.isEmpty(name)) {
            SearchFilter nameFilter = new SearchFilter();
            nameFilter.setFilterRestriction(Restriction.AND);
            nameFilter.setClauseRestriction(Restriction.CONTAIN);
            nameFilter.setSearchField("farmerName");
            nameFilter.setValue(name);
            criteria.getFilters().add(nameFilter);
        }

        criteria.setFetch(fetch);
        criteria.setStartIndex(start);
        List<Map<String, Object>> results = this.mapper
                .searchAgricultureInfo(criteria);
        List<AgricultureInfo> agris = new ArrayList<AgricultureInfo>();
        for (Map<String, Object> map : results) {
            AgricultureInfo agri = new AgricultureInfo();
            agri.setAgriID((Long) map.get("agriID"));
            agri.setFarmerName((String) map.get("farmerName"));
            agri.setArea((Integer) map.get("area"));
            agri.setContactNumber((String) map.get("contactNumber"));
            agris.add(agri);
        }
        return agris;
    }

    @Override
    public int getAgricultureInfoTotalCount(String name) {
        SearchCriteria criteria = new SearchCriteria();
        if (!Strings.isEmpty(name)) {
            SearchFilter nameFilter = new SearchFilter();
            nameFilter.setFilterRestriction(Restriction.AND);
            nameFilter.setClauseRestriction(Restriction.CONTAIN);
            nameFilter.setSearchField("farmerName");
            nameFilter.setValue(name);
            criteria.getFilters().add(nameFilter);
        }
        return mapper.getAgricultureInfoTotalCount(criteria);
    }

    @Override
    public List<CommercialArea> searchCommercialArea(String name,
                                                     CommercialAreaType type, long fetch, long start) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.getFields().add(CommercialAreaSearchField.AREA_ID.value());
        criteria.getFields().add(CommercialAreaSearchField.NAME.value());
        criteria.getFields().add(CommercialAreaSearchField.AREA_TYPE.value());
        if (!Strings.isEmpty(name)) {
            SearchFilter nameFilter = new SearchFilter();
            nameFilter.setFilterRestriction(Restriction.AND);
            nameFilter.setClauseRestriction(Restriction.CONTAIN);
            nameFilter.setSearchField("name");
            nameFilter.setValue(name);
            criteria.getFilters().add(nameFilter);
        }
        if (type != null) {
            SearchFilter nameFilter = new SearchFilter();
            nameFilter.setFilterRestriction(Restriction.AND);
            nameFilter.setClauseRestriction(Restriction.EQUAL);
            nameFilter.setSearchField("typeID");
            nameFilter.setValue(SystemEnumeration.getInstance()
                    .getIdByKey(type).toString());
            nameFilter.setValueClass(Long.class);
            criteria.getFilters().add(nameFilter);
        }
        criteria.setFetch(fetch);
        criteria.setStartIndex(start);
        List<Map<String, Object>> results = this.area
                .searchCommercialArea(criteria);
        List<CommercialArea> areas = new ArrayList<CommercialArea>();
        for (Map<String, Object> map : results) {
            CommercialArea area = new CommercialArea();
            area.setCommercialAreaID((Long) map.get("commercialAreaID"));
            area.setName((String) map.get("name"));
            area.setTypeID((Long) map.get("typeID"));
            areas.add(area);
        }
        return areas;
    }

    @Override
    public int getCommercialAreaTotalCount(String name, CommercialAreaType type) {
        SearchCriteria criteria = new SearchCriteria();
        if (!Strings.isEmpty(name)) {
            SearchFilter nameFilter = new SearchFilter();
            nameFilter.setFilterRestriction(Restriction.AND);
            nameFilter.setClauseRestriction(Restriction.CONTAIN);
            nameFilter.setSearchField("name");
            nameFilter.setValue(name);
            criteria.getFilters().add(nameFilter);
        }
        if (type != null) {
            SearchFilter nameFilter = new SearchFilter();
            nameFilter.setFilterRestriction(Restriction.AND);
            nameFilter.setClauseRestriction(Restriction.EQUAL);
            nameFilter.setSearchField("typeID");
            nameFilter.setValue(SystemEnumeration.getInstance()
                    .getIdByKey(type).toString());
            nameFilter.setValueClass(Long.class);
            criteria.getFilters().add(nameFilter);
        }
        return area.getCommercialAreaTotalCount(criteria);
    }

    @Override
    public Map<Long, Double> getEconomyNaturePersonalIncomingTaxData(int year,
                                                                     int month) {
        List<Map<String, Object>> enpitData = mapper.getEconomyNaturePITReport(
                year, month);
        Map<Long, Double> enpitMap = new HashMap<Long, Double>();
        for (Map<String, Object> enpitD : enpitData) {
            Long economyNatureID = (Long) enpitD.get("economyNatureID");
            Double vat = (Double) enpitD.get("vat");
            if (economyNatureID == null) {
                economyNatureID = SystemEnumeration.getInstance().getIdByKey(
                        EconomyNature.PRIVATE);
            }
            if (enpitMap.get(economyNatureID) != null) {
                enpitMap.put(economyNatureID, enpitMap.get(economyNatureID)
                        + (Double) enpitD.get("vat"));
            } else {
                enpitMap.put(economyNatureID, vat);
            }
        }
        return enpitMap;
    }

    @Override
    public Map<String, Double> getTop10VATCompany(int year, int month) {
        Map<String, Double> vatMap = new HashMap<String, Double>();
        List<Map<String, Object>> companyMap = mapper.getTop10VATReport(year,
                month);
        for (Map<String, Object> data : companyMap) {
            vatMap.put((String) data.get("name"), (Double) data.get("vat"));
        }
        return vatMap;
    }

    @Override
    public Map<String, Double> getEconomyEntityVAT(int year, int month) {
        List<Map<String, Object>> enpitData = mapper.getEconomyEntityVATReport(
                year, month);
        Map<String, Double> enpitMap = new HashMap<String, Double>();
        for (Map<String, Object> enpitD : enpitData) {
            EconomyEntity economyEntity = SystemEnumeration.getInstance()
                    .getKeyById(EconomyEntity.class,
                            (Long) enpitD.get("economyEntityID"));
            Double vat = (Double) enpitD.get("vat");
            if (economyEntity == null) {
                enpitMap.put("other", vat);
            } else {
                enpitMap.put(economyEntity.toString(), vat);
            }

        }
        return enpitMap;
    }

    @Override
    public Map<String, String> getCompanyEmail(List<Long> companyIDs) {
        List<Map<String, Object>> map = mapper.getCompanyContacts(companyIDs);
        Map<String, String> emailList = new HashMap<String, String>();
        for (Map<String, Object> data : map) {
            emailList.put((String) data.get("emailAddress"),
                    (String) data.get("fullName"));
        }
        return emailList;
    }

    @Override
    public List<Company> getCompaniesForExport(AffiliateBlock block) {
        Map<Long, Company> companyIDMap = new HashMap<>();
        Map<Long, List<RentStatus>> renteeMap = new HashMap<>();
        Map<Long, List<RentStatus>> rentorMap = new HashMap<>();
        List<Company> companies;
        if (block == null) {
            companies = mapper.getAllCompanies();
        } else {
            companies = mapper.getExportCompanies(SystemEnumeration.getInstance().getIdByKey(block));
        }
        for (Company c : companies) {
            companyIDMap.put(c.getCompanyID(), c);
        }
        List<RentStatusPO> pos = mapper.getAllRentStatus();
        for (RentStatusPO po : pos) {
            RentStatus rs = new RentStatus();
            rs.setRelationID(rs.getRelationID());
            if (po.getRentorID() != null) {
                rs.setRentor(companyIDMap.get(po.getRentorID()));
            }
            rs.setRentee(companyIDMap.get(po.getRenteeID()));
            rs.setNonDJCompany(po.getNonDJCompany());
            rs.setArea(po.getArea());
            rs.setAddress(po.getAddress());
            rs.setStartDate(po.getStartDate());
            rs.setEndDate(po.getEndDate());
            if (po.getRentorID() != null) {
                if (rentorMap.get(po.getRentorID()) == null) {
                    rentorMap.put(po.getRentorID(), new ArrayList<RentStatus>());
                }
                rentorMap.get(po.getRentorID()).add(rs);
            }
            if (renteeMap.get(po.getRenteeID()) == null) {
                renteeMap.put(po.getRenteeID(), new ArrayList<RentStatus>());
            }
            renteeMap.get(po.getRenteeID()).add(rs);
        }

        for (Company c : companies) {
            if (rentorMap.get(c.getCompanyID()) != null) {
                c.setRentees((rentorMap.get(c.getCompanyID())));
            }
            if (renteeMap.get(c.getCompanyID()) != null) {
                c.setRentors(renteeMap.get(c.getCompanyID()));
            }
        }

        List<NatureShareholder> natureShareholders = mapper.getAllNatureShareholders();
        for (NatureShareholder ns : natureShareholders) {
            Company company = companyIDMap.get(ns.getCompanyID());
            if(company != null) {
                company.getNatureShareholders().add(ns);
            }
        }

        List<CompanyShareholder> companyShareholders = mapper.getAllCompanyShareholders();
        for (CompanyShareholder cs: companyShareholders){
            Company company = companyIDMap.get(cs.getCompanyID());
            if(company != null) {
                company.getCompanyShareholders().add(cs);
            }
        }

        List<AnnualOutputPO> outputs = mapper.getAllOutputs();
        for(AnnualOutputPO output: outputs){
            Company company = companyIDMap.get(output.getCompanyID());
            if(company != null){
                company.getOutputs().add(output);
            }
        }
        List<PartyMember> partyMembers = mapper.getAllPartyMembers();
        for(PartyMember member: partyMembers){
            Company company = companyIDMap.get(member.getCompanyID());
            if(company != null){
                company.getPartyMembers().add(member);
            }
        }

        List<CompanyMember> members = mapper.getAllCompanyMembers();
        for(CompanyMember member: members){
            Company company = companyIDMap.get(member.getCompanyID());
            if(company != null){
                company.getMembers().add(member);
            }
        }

        return companies;
    }
}
