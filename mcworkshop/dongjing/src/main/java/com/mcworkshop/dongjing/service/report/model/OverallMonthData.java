// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.service.report.model;

import com.mcworkshop.common.domain.DomainObject;

/**
 * @author $Author$
 * 
 */
public class OverallMonthData extends DomainObject {

	private static final long serialVersionUID = 1L;

	private int year;
	private int month;

	private int merchantIndustry;
	private int merchantCommercial;
	private int merchantService;
	private int merchantConstruct;
	private int merchantHouseHolding;
	private int merchantSignOffCompanies;

	private int regAssetsIndustry;
	private int regAssetsCommercial;
	private int regAssetsService;
	private int regAssetsConstruct;
	private int regAssetsHouseholding;

	private int vat;
	private int operateTax;
	private int incomingTax;
	private int constructTax;
	private int otherTax;

	private int salesTotal;

	private double privateCompanyVAT;
	private double privateCompanyOperateTax;
	private double privateCompanyIncomingTax;
	private double privateCompanyPersonalIncomingTax;
	private double privateCompanyConstructTax;
	private double privateCompanyOtherTax;

	private double publicCompanyVAT;
	private double publicCompanyOperateTax;
	private double publicCompanyIncomingTax;
	private double publicCompanyPersonalIncomingTax;
	private double publicCompanyConstructTax;
	private double publicCompanyOtherTax;

	private double groupCompanyVAT;
	private double groupCompanyOperateTax;
	private double groupCompanyIncomingTax;
	private double groupCompanyPersonalIncomingTax;
	private double groupCompanyConstructTax;
	private double groupCompanyOtherTax;

	private long industryCount;
	private long commercialCount;
	private long constructCount;
	private long serviceCount;
	private long houseHoldingCount;

	private double industrySale;
	private double commercialSale;
	private double constructSale;
	private double serviceSale;
	private double houseHoldingSale;

	private double industryVAT;
	private double commercialVAT;
	private double constructVAT;
	private double serviceVAT;
	private double houseHoldingVAT;

	private double industryOperateTax;
	private double commercialOperateTax;
	private double constructOperateTax;
	private double serviceOperateTax;
	private double houseHoldingOperateTax;

	private double industryIncomingTax;
	private double commercialIncomingTax;
	private double constructIncomingTax;
	private double serviceIncomingTax;
	private double houseHoldingIncomingTax;

	private double industryPersonalIncomingTax;
	private double commercialPersonalIncomingTax;
	private double constructPersonalIncomingTax;
	private double servicePersonalIncomingTax;
	private double houseHoldingPersonalIncomingTax;

	private double industryConstructTax;
	private double commercialConstructTax;
	private double constructConstructTax;
	private double serviceConstructTax;
	private double houseHoldingConstructTax;

	private double industryOtherTax;
	private double commercialOtherTax;
	private double constructOtherTax;
	private double serviceOtherTax;
	private double houseHoldingOtherTax;

	public int getMerchantIndustry() {
		return merchantIndustry;
	}

	public int getMerchantCommercial() {
		return merchantCommercial;
	}

	public int getMerchantService() {
		return merchantService;
	}

	public int getMerchantConstruct() {
		return merchantConstruct;
	}

	public int getMerchantHouseHolding() {
		return merchantHouseHolding;
	}

	public int getMerchantSignOffCompanies() {
		return merchantSignOffCompanies;
	}

	public int getRegAssetsIndustry() {
		return regAssetsIndustry;
	}

	public int getRegAssetsCommercial() {
		return regAssetsCommercial;
	}

	public int getRegAssetsService() {
		return regAssetsService;
	}

	public int getRegAssetsConstruct() {
		return regAssetsConstruct;
	}

	public int getRegAssetsHouseholding() {
		return regAssetsHouseholding;
	}

	public int getVat() {
		return vat;
	}

	public int getOperateTax() {
		return operateTax;
	}

	public int getIncomingTax() {
		return incomingTax;
	}

	public int getConstructTax() {
		return constructTax;
	}

	public int getOtherTax() {
		return otherTax;
	}

	public int getSalesTotal() {
		return salesTotal;
	}

	public void setMerchantIndustry(int merchantIndustry) {
		this.merchantIndustry = merchantIndustry;
	}

	public void setMerchantCommercial(int merchantCommercial) {
		this.merchantCommercial = merchantCommercial;
	}

	public void setMerchantService(int merchantService) {
		this.merchantService = merchantService;
	}

	public void setMerchantConstruct(int merchantConstruct) {
		this.merchantConstruct = merchantConstruct;
	}

	public void setMerchantHouseHolding(int merchantHouseHolding) {
		this.merchantHouseHolding = merchantHouseHolding;
	}

	public void setMerchantSignOffCompanies(int merchantSignOffCompanies) {
		this.merchantSignOffCompanies = merchantSignOffCompanies;
	}

	public void setRegAssetsIndustry(int regAssetsIndustry) {
		this.regAssetsIndustry = regAssetsIndustry;
	}

	public void setRegAssetsCommercial(int regAssetsCommercial) {
		this.regAssetsCommercial = regAssetsCommercial;
	}

	public void setRegAssetsService(int regAssetsService) {
		this.regAssetsService = regAssetsService;
	}

	public void setRegAssetsConstruct(int regAssetsConstruct) {
		this.regAssetsConstruct = regAssetsConstruct;
	}

	public void setRegAssetsHouseholding(int regAssetsHouseholding) {
		this.regAssetsHouseholding = regAssetsHouseholding;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public void setOperateTax(int operateTax) {
		this.operateTax = operateTax;
	}

	public void setIncomingTax(int incomingTax) {
		this.incomingTax = incomingTax;
	}

	public void setConstructTax(int constructTax) {
		this.constructTax = constructTax;
	}

	public void setOtherTax(int otherTax) {
		this.otherTax = otherTax;
	}

	public void setSalesTotal(int salesTotal) {
		this.salesTotal = salesTotal;
	}

	public void addMerchantIndustry() {
		merchantIndustry++;
	}

	public void addMerchantCommercial() {
		merchantCommercial++;
	}

	public void addMerchantService() {
		merchantService++;
	}

	public void addMerchantConstruct() {
		merchantConstruct++;
	}

	public void addMerchantHouseHolding() {
		merchantHouseHolding++;
	}

	public void addMerchantSignOffCompanies() {
		merchantSignOffCompanies++;
	}

	public void addRegAssetsIndustry(Double amount) {
		if (amount != null) {
			regAssetsIndustry += amount;
		}
	}

	public void addRegAssetsCommercial(Double amount) {
		if (amount != null) {
			regAssetsCommercial += amount;
		}
	}

	public void addRegAssetsService(Double amount) {
		if (amount != null) {
			regAssetsService += amount;
		}
	}

	public void addRegAssetsConstruct(Double amount) {
		if (amount != null) {
			regAssetsConstruct += amount;
		}
	}

	public void addRegAssetsHouseholding(Double amount) {
		if (amount != null) {
			regAssetsHouseholding += amount;
		}
	}

	public void addVat(Double amount) {
		if (amount != null) {
			vat += amount;
		}
	}

	public void addOperateTax(Double amount) {
		if (amount != null) {
			operateTax += amount;
		}
	}

	public void addIncomingTax(Double amount) {
		if (amount != null) {
			incomingTax += amount;
		}
	}

	public void addConstructTax(Double amount) {
		if (amount != null) {
			constructTax += amount;
		}
	}

	public void addOtherTax(Double amount) {
		if (amount != null) {
			otherTax += amount;
		}
	}

	public void addSalesTotal(Double amount) {
		if (amount != null) {
			salesTotal += amount;
		}
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public double getPrivateCompanyVAT() {
		return privateCompanyVAT;
	}

	public double getPrivateCompanyOperateTax() {
		return privateCompanyOperateTax;
	}

	public double getPrivateCompanyIncomingTax() {
		return privateCompanyIncomingTax;
	}

	public double getPrivateCompanyPersonalIncomingTax() {
		return privateCompanyPersonalIncomingTax;
	}

	public double getPrivateCompanyConstructTax() {
		return privateCompanyConstructTax;
	}

	public double getPrivateCompanyOtherTax() {
		return privateCompanyOtherTax;
	}

	public double getPublicCompanyVAT() {
		return publicCompanyVAT;
	}

	public double getPublicCompanyOperateTax() {
		return publicCompanyOperateTax;
	}

	public double getPublicCompanyIncomingTax() {
		return publicCompanyIncomingTax;
	}

	public double getPublicCompanyPersonalIncomingTax() {
		return publicCompanyPersonalIncomingTax;
	}

	public double getPublicCompanyConstructTax() {
		return publicCompanyConstructTax;
	}

	public double getPublicCompanyOtherTax() {
		return publicCompanyOtherTax;
	}

	public double getGroupCompanyVAT() {
		return groupCompanyVAT;
	}

	public double getGroupCompanyOperateTax() {
		return groupCompanyOperateTax;
	}

	public double getGroupCompanyIncomingTax() {
		return groupCompanyIncomingTax;
	}

	public double getGroupCompanyPersonalIncomingTax() {
		return groupCompanyPersonalIncomingTax;
	}

	public double getGroupCompanyConstructTax() {
		return groupCompanyConstructTax;
	}

	public double getGroupCompanyOtherTax() {
		return groupCompanyOtherTax;
	}

	public void setPrivateCompanyVAT(double privateCompanyVAT) {
		this.privateCompanyVAT = privateCompanyVAT;
	}

	public void setPrivateCompanyOperateTax(double privateCompanyOperateTax) {
		this.privateCompanyOperateTax = privateCompanyOperateTax;
	}

	public void setPrivateCompanyIncomingTax(double privateCompanyIncomingTax) {
		this.privateCompanyIncomingTax = privateCompanyIncomingTax;
	}

	public void setPrivateCompanyPersonalIncomingTax(
			double privateCompanyPersonalIncomingTax) {
		this.privateCompanyPersonalIncomingTax = privateCompanyPersonalIncomingTax;
	}

	public void setPrivateCompanyConstructTax(double privateCompanyConstructTax) {
		this.privateCompanyConstructTax = privateCompanyConstructTax;
	}

	public void setPrivateCompanyOtherTax(double privateCompanyOtherTax) {
		this.privateCompanyOtherTax = privateCompanyOtherTax;
	}

	public void setPublicCompanyVAT(double publicCompanyVAT) {
		this.publicCompanyVAT = publicCompanyVAT;
	}

	public void setPublicCompanyOperateTax(double publicCompanyOperateTax) {
		this.publicCompanyOperateTax = publicCompanyOperateTax;
	}

	public void setPublicCompanyIncomingTax(double publicCompanyIncomingTax) {
		this.publicCompanyIncomingTax = publicCompanyIncomingTax;
	}

	public void setPublicCompanyPersonalIncomingTax(
			double publicCompanyPersonalIncomingTax) {
		this.publicCompanyPersonalIncomingTax = publicCompanyPersonalIncomingTax;
	}

	public void setPublicCompanyConstructTax(double publicCompanyConstructTax) {
		this.publicCompanyConstructTax = publicCompanyConstructTax;
	}

	public void setPublicCompanyOtherTax(double publicCompanyOtherTax) {
		this.publicCompanyOtherTax = publicCompanyOtherTax;
	}

	public void setGroupCompanyVAT(double groupCompanyVAT) {
		this.groupCompanyVAT = groupCompanyVAT;
	}

	public void setGroupCompanyOperateTax(double groupCompanyOperateTax) {
		this.groupCompanyOperateTax = groupCompanyOperateTax;
	}

	public void setGroupCompanyIncomingTax(double groupCompanyIncomingTax) {
		this.groupCompanyIncomingTax = groupCompanyIncomingTax;
	}

	public void setGroupCompanyPersonalIncomingTax(
			double groupCompanyPersonalIncomingTax) {
		this.groupCompanyPersonalIncomingTax = groupCompanyPersonalIncomingTax;
	}

	public void setGroupCompanyConstructTax(double groupCompanyConstructTax) {
		this.groupCompanyConstructTax = groupCompanyConstructTax;
	}

	public void setGroupCompanyOtherTax(double groupCompanyOtherTax) {
		this.groupCompanyOtherTax = groupCompanyOtherTax;
	}

	public void setPrivateCompanyVAT(int privateCompanyVAT) {
		this.privateCompanyVAT = privateCompanyVAT;
	}

	public void setPrivateCompanyOperateTax(int privateCompanyOperateTax) {
		this.privateCompanyOperateTax = privateCompanyOperateTax;
	}

	public void setPrivateCompanyIncomingTax(int privateCompanyIncomingTax) {
		this.privateCompanyIncomingTax = privateCompanyIncomingTax;
	}

	public void setPrivateCompanyPersonalIncomingTax(
			int privateCompanyPersonalIncomingTax) {
		this.privateCompanyPersonalIncomingTax = privateCompanyPersonalIncomingTax;
	}

	public void setPrivateCompanyConstructTax(int privateCompanyConstructTax) {
		this.privateCompanyConstructTax = privateCompanyConstructTax;
	}

	public void setPrivateCompanyOtherTax(int privateCompanyOtherTax) {
		this.privateCompanyOtherTax = privateCompanyOtherTax;
	}

	public void setPublicCompanyVAT(int publicCompanyVAT) {
		this.publicCompanyVAT = publicCompanyVAT;
	}

	public void setPublicCompanyOperateTax(int publicCompanyOperateTax) {
		this.publicCompanyOperateTax = publicCompanyOperateTax;
	}

	public void setPublicCompanyIncomingTax(int publicCompanyIncomingTax) {
		this.publicCompanyIncomingTax = publicCompanyIncomingTax;
	}

	public void setPublicCompanyPersonalIncomingTax(
			int publicCompanyPersonalIncomingTax) {
		this.publicCompanyPersonalIncomingTax = publicCompanyPersonalIncomingTax;
	}

	public void setPublicCompanyConstructTax(int publicCompanyConstructTax) {
		this.publicCompanyConstructTax = publicCompanyConstructTax;
	}

	public void setPublicCompanyOtherTax(int publicCompanyOtherTax) {
		this.publicCompanyOtherTax = publicCompanyOtherTax;
	}

	public void setGroupCompanyVAT(int groupCompanyVAT) {
		this.groupCompanyVAT = groupCompanyVAT;
	}

	public void setGroupCompanyOperateTax(int groupCompanyOperateTax) {
		this.groupCompanyOperateTax = groupCompanyOperateTax;
	}

	public void setGroupCompanyIncomingTax(int groupCompanyIncomingTax) {
		this.groupCompanyIncomingTax = groupCompanyIncomingTax;
	}

	public void setGroupCompanyPersonalIncomingTax(
			int groupCompanyPersonalIncomingTax) {
		this.groupCompanyPersonalIncomingTax = groupCompanyPersonalIncomingTax;
	}

	public void setGroupCompanyConstructTax(int groupCompanyConstructTax) {
		this.groupCompanyConstructTax = groupCompanyConstructTax;
	}

	public void setGroupCompanyOtherTax(int groupCompanyOtherTax) {
		this.groupCompanyOtherTax = groupCompanyOtherTax;
	}

	public void addPrivateCompanyVAT(Double amount) {
		if (amount != null) {
			this.privateCompanyVAT += amount;
		}
	}

	public void addPrivateCompanyOperateTax(Double amount) {
		if (amount != null) {
			this.privateCompanyOperateTax += amount;
		}
	}

	public void addPrivateCompanyIncomingTax(Double amount) {
		if (amount != null) {
			this.privateCompanyIncomingTax += amount;
		}
	}

	public void addPrivateCompanyPersonalIncomingTax(Double amount) {
		if (amount != null) {
			this.privateCompanyPersonalIncomingTax += amount;
		}
	}

	public void addPrivateCompanyConstructTax(Double amount) {
		if (amount != null) {
			this.privateCompanyConstructTax += amount;
		}
	}

	public void addPrivateCompanyOtherTax(Double amount) {
		if (amount != null) {
			this.privateCompanyOtherTax += amount;
		}
	}

	public void addPublicCompanyVAT(Double amount) {
		if (amount != null) {
			this.publicCompanyVAT += amount;
		}
	}

	public void addPublicCompanyOperateTax(Double amount) {
		if (amount != null) {
			this.publicCompanyOperateTax += amount;
		}
	}

	public void addPublicCompanyIncomingTax(Double amount) {
		if (amount != null) {
			this.publicCompanyIncomingTax += amount;
		}
	}

	public void addPublicCompanyPersonalIncomingTax(Double amount) {
		if (amount != null) {
			this.publicCompanyPersonalIncomingTax += amount;
		}
	}

	public void addPublicCompanyConstructTax(Double amount) {
		if (amount != null) {
			this.publicCompanyConstructTax += amount;
		}
	}

	public void addPublicCompanyOtherTax(Double amount) {
		if (amount != null) {
			this.publicCompanyOtherTax += amount;
		}
	}

	public void addGroupCompanyVAT(Double amount) {
		if (amount != null) {
			this.groupCompanyVAT += amount;
		}
	}

	public void addGroupCompanyOperateTax(Double amount) {
		if (amount != null) {
			this.groupCompanyOperateTax += amount;
		}
	}

	public void addGroupCompanyIncomingTax(Double amount) {
		if (amount != null) {
			this.groupCompanyIncomingTax += amount;
		}
	}

	public void addGroupCompanyPersonalIncomingTax(Double amount) {
		if (amount != null) {
			this.groupCompanyPersonalIncomingTax += amount;
		}
	}

	public void addGroupCompanyConstructTax(Double amount) {
		if (amount != null) {
			this.groupCompanyConstructTax += amount;
		}
	}

	public void addGroupCompanyOtherTax(Double amount) {
		if (amount != null) {
			this.groupCompanyOtherTax += amount;
		}
	}

	public long getIndustryCount() {
		return industryCount;
	}

	public long getCommercialCount() {
		return commercialCount;
	}

	public long getConstructCount() {
		return constructCount;
	}

	public long getServiceCount() {
		return serviceCount;
	}

	public long getHouseHoldingCount() {
		return houseHoldingCount;
	}

	public void setIndustryCount(long industryCount) {
		this.industryCount = industryCount;
	}

	public void setCommercialCount(long commercialCount) {
		this.commercialCount = commercialCount;
	}

	public void setConstructCount(long constructCount) {
		this.constructCount = constructCount;
	}

	public void setServiceCount(long serviceCount) {
		this.serviceCount = serviceCount;
	}

	public void setHouseHoldingCount(long houseHoldingCount) {
		this.houseHoldingCount = houseHoldingCount;
	}

	public void addIndustryCount() {
		industryCount++;
	}

	public void addCommercialCount() {
		commercialCount++;
	}

	public void addConstructCount() {
		constructCount++;
	}

	public void addServiceCount() {
		serviceCount++;
	}

	public void addHouseHoldingCount() {
		houseHoldingCount++;
	}

	public double getIndustryVAT() {
		return industryVAT;
	}

	public double getCommercialVAT() {
		return commercialVAT;
	}

	public double getConstructVAT() {
		return constructVAT;
	}

	public double getServiceVAT() {
		return serviceVAT;
	}

	public double getHouseHoldingVAT() {
		return houseHoldingVAT;
	}

	public double getIndustryOperateTax() {
		return industryOperateTax;
	}

	public double getCommercialOperateTax() {
		return commercialOperateTax;
	}

	public double getConstructOperateTax() {
		return constructOperateTax;
	}

	public double getServiceOperateTax() {
		return serviceOperateTax;
	}

	public double getHouseHoldingOperateTax() {
		return houseHoldingOperateTax;
	}

	public double getIndustryIncomingTax() {
		return industryIncomingTax;
	}

	public double getCommercialIncomingTax() {
		return commercialIncomingTax;
	}

	public double getConstructIncomingTax() {
		return constructIncomingTax;
	}

	public double getServiceIncomingTax() {
		return serviceIncomingTax;
	}

	public double getHouseHoldingIncomingTax() {
		return houseHoldingIncomingTax;
	}

	public double getIndustryPersonalIncomingTax() {
		return industryPersonalIncomingTax;
	}

	public double getCommercialPersonalIncomingTax() {
		return commercialPersonalIncomingTax;
	}

	public double getConstructPersonalIncomingTax() {
		return constructPersonalIncomingTax;
	}

	public double getServicePersonalIncomingTax() {
		return servicePersonalIncomingTax;
	}

	public double getHouseHoldingPersonalIncomingTax() {
		return houseHoldingPersonalIncomingTax;
	}

	public double getIndustryConstructTax() {
		return industryConstructTax;
	}

	public double getCommercialConstructTax() {
		return commercialConstructTax;
	}

	public double getConstructConstructTax() {
		return constructConstructTax;
	}

	public double getServiceConstructTax() {
		return serviceConstructTax;
	}

	public double getHouseHoldingConstructTax() {
		return houseHoldingConstructTax;
	}

	public double getIndustryOtherTax() {
		return industryOtherTax;
	}

	public double getCommercialOtherTax() {
		return commercialOtherTax;
	}

	public double getConstructOtherTax() {
		return constructOtherTax;
	}

	public double getServiceOtherTax() {
		return serviceOtherTax;
	}

	public double getHouseHoldingOtherTax() {
		return houseHoldingOtherTax;
	}

	public void setIndustryVAT(double industryVAT) {
		this.industryVAT = industryVAT;
	}

	public void setCommercialVAT(double commercialVAT) {
		this.commercialVAT = commercialVAT;
	}

	public void setConstructVAT(double constructVAT) {
		this.constructVAT = constructVAT;
	}

	public void setServiceVAT(double serviceVAT) {
		this.serviceVAT = serviceVAT;
	}

	public void setHouseHoldingVAT(double houseHoldingVAT) {
		this.houseHoldingVAT = houseHoldingVAT;
	}

	public void setIndustryOperateTax(double industryOperateTax) {
		this.industryOperateTax = industryOperateTax;
	}

	public void setCommercialOperateTax(double commercialOperateTax) {
		this.commercialOperateTax = commercialOperateTax;
	}

	public void setConstructOperateTax(double constructOperateTax) {
		this.constructOperateTax = constructOperateTax;
	}

	public void setServiceOperateTax(double serviceOperateTax) {
		this.serviceOperateTax = serviceOperateTax;
	}

	public void setHouseHoldingOperateTax(double houseHoldingOperateTax) {
		this.houseHoldingOperateTax = houseHoldingOperateTax;
	}

	public void setIndustryIncomingTax(double industryIncomingTax) {
		this.industryIncomingTax = industryIncomingTax;
	}

	public void setCommercialIncomingTax(double commercialIncomingTax) {
		this.commercialIncomingTax = commercialIncomingTax;
	}

	public void setConstructIncomingTax(double constructIncomingTax) {
		this.constructIncomingTax = constructIncomingTax;
	}

	public void setServiceIncomingTax(double serviceIncomingTax) {
		this.serviceIncomingTax = serviceIncomingTax;
	}

	public void setHouseHoldingIncomingTax(double houseHoldingIncomingTax) {
		this.houseHoldingIncomingTax = houseHoldingIncomingTax;
	}

	public void setIndustryPersonalIncomingTax(
			double industryPersonalIncomingTax) {
		this.industryPersonalIncomingTax = industryPersonalIncomingTax;
	}

	public void setCommercialPersonalIncomingTax(
			double commercialPersonalIncomingTax) {
		this.commercialPersonalIncomingTax = commercialPersonalIncomingTax;
	}

	public void setConstructPersonalIncomingTax(
			double constructPersonalIncomingTax) {
		this.constructPersonalIncomingTax = constructPersonalIncomingTax;
	}

	public void setServicePersonalIncomingTax(double servicePersonalIncomingTax) {
		this.servicePersonalIncomingTax = servicePersonalIncomingTax;
	}

	public void setHouseHoldingPersonalIncomingTax(
			double houseHoldingPersonalIncomingTax) {
		this.houseHoldingPersonalIncomingTax = houseHoldingPersonalIncomingTax;
	}

	public void setIndustryConstructTax(double industryConstructTax) {
		this.industryConstructTax = industryConstructTax;
	}

	public void setCommercialConstructTax(double commercialConstructTax) {
		this.commercialConstructTax = commercialConstructTax;
	}

	public void setConstructConstructTax(double constructConstructTax) {
		this.constructConstructTax = constructConstructTax;
	}

	public void setServiceConstructTax(double serviceConstructTax) {
		this.serviceConstructTax = serviceConstructTax;
	}

	public void setHouseHoldingConstructTax(double houseHoldingConstructTax) {
		this.houseHoldingConstructTax = houseHoldingConstructTax;
	}

	public void setIndustryOtherTax(double industryOtherTax) {
		this.industryOtherTax = industryOtherTax;
	}

	public void setCommercialOtherTax(double commercialOtherTax) {
		this.commercialOtherTax = commercialOtherTax;
	}

	public void setConstructOtherTax(double constructOtherTax) {
		this.constructOtherTax = constructOtherTax;
	}

	public void setServiceOtherTax(double serviceOtherTax) {
		this.serviceOtherTax = serviceOtherTax;
	}

	public void setHouseHoldingOtherTax(double houseHoldingOtherTax) {
		this.houseHoldingOtherTax = houseHoldingOtherTax;
	}

	public void addIndustryVAT(Double amount) {
		if (amount != null) {
			industryVAT += amount;
		}
	}

	public void addCommercialVAT(Double amount) {
		if (amount != null) {
			commercialVAT += amount;
		}
	}

	public void addConstructVAT(Double amount) {
		if (amount != null) {
			constructVAT += amount;
		}
	}

	public void addServiceVAT(Double amount) {
		if (amount != null) {
			serviceVAT += amount;
		}
	}

	public void addHouseHoldingVAT(Double amount) {
		if (amount != null) {
			houseHoldingVAT += amount;
		}
	}

	public void addIndustryOperateTax(Double amount) {
		if (amount != null) {
			industryOperateTax += amount;
		}
	}

	public void addCommercialOperateTax(Double amount) {
		if (amount != null) {
			commercialOperateTax += amount;
		}
	}

	public void addConstructOperateTax(Double amount) {
		if (amount != null) {
			constructOperateTax += amount;
		}
	}

	public void addServiceOperateTax(Double amount) {
		if (amount != null) {
			serviceOperateTax += amount;
		}
	}

	public void addHouseHoldingOperateTax(Double amount) {
		if (amount != null) {
			houseHoldingOperateTax += amount;
		}
	}

	public void addIndustryIncomingTax(Double amount) {
		if (amount != null) {
			industryIncomingTax += amount;
		}
	}

	public void addCommercialIncomingTax(Double amount) {
		if (amount != null) {
			commercialIncomingTax += amount;
		}
	}

	public void addConstructIncomingTax(Double amount) {
		if (amount != null) {
			constructIncomingTax += amount;
		}
	}

	public void addServiceIncomingTax(Double amount) {
		if (amount != null) {
			serviceIncomingTax += amount;
		}
	}

	public void addHouseHoldingIncomingTax(Double amount) {
		if (amount != null) {
			houseHoldingIncomingTax += amount;
		}
	}

	public void addIndustryPersonalIncomingTax(Double amount) {
		if (amount != null) {
			industryPersonalIncomingTax += amount;
		}
	}

	public void addCommercialPersonalIncomingTax(Double amount) {
		if (amount != null) {
			commercialPersonalIncomingTax += amount;
		}
	}

	public void addConstructPersonalIncomingTax(Double amount) {
		if (amount != null) {
			constructPersonalIncomingTax += amount;
		}
	}

	public void addServicePersonalIncomingTax(Double amount) {
		if (amount != null) {
			servicePersonalIncomingTax += amount;
		}
	}

	public void addHouseHoldingPersonalIncomingTax(Double amount) {
		if (amount != null) {
			houseHoldingPersonalIncomingTax += amount;
		}
	}

	public void addIndustryConstructTax(Double amount) {
		if (amount != null) {
			industryConstructTax += amount;
		}
	}

	public void addCommercialConstructTax(Double amount) {
		if (amount != null) {
			commercialConstructTax += amount;
		}
	}

	public void addConstructConstructTax(Double amount) {
		if (amount != null) {
			constructConstructTax += amount;
		}
	}

	public void addServiceConstructTax(Double amount) {
		if (amount != null) {
			serviceConstructTax += amount;
		}
	}

	public void addHouseHoldingConstructTax(Double amount) {
		if (amount != null) {
			houseHoldingConstructTax += amount;
		}
	}

	public void addIndustryOtherTax(Double amount) {
		if (amount != null) {
			industryOtherTax += amount;
		}
	}

	public void addCommercialOtherTax(Double amount) {
		if (amount != null) {
			commercialOtherTax += amount;
		}
	}

	public void addConstructOtherTax(Double amount) {
		if (amount != null) {
			constructOtherTax += amount;
		}
	}

	public void addServiceOtherTax(Double amount) {
		if (amount != null) {
			serviceOtherTax += amount;
		}
	}

	public void addHouseHoldingOtherTax(Double amount) {
		if (amount != null) {
			houseHoldingOtherTax += amount;
		}
	}

	public double getIndustrySale() {
		return industrySale;
	}

	public double getCommercialSale() {
		return commercialSale;
	}

	public double getConstructSale() {
		return constructSale;
	}

	public double getServiceSale() {
		return serviceSale;
	}

	public double getHouseHoldingSale() {
		return houseHoldingSale;
	}

	public void setIndustrySale(double industrySale) {
		this.industrySale = industrySale;
	}

	public void setCommercialSale(double commercialSale) {
		this.commercialSale = commercialSale;
	}

	public void setConstructSale(double constructSale) {
		this.constructSale = constructSale;
	}

	public void setServiceSale(double serviceSale) {
		this.serviceSale = serviceSale;
	}

	public void setHouseHoldingSale(double houseHoldingSale) {
		this.houseHoldingSale = houseHoldingSale;
	}

	public void addIndustrySale(Double amount) {
		if (amount != null) {
			industrySale += amount;
		}
	}

	public void addCommercialSale(Double amount) {
		if (amount != null) {
			commercialSale += amount;
		}
	}

	public void addConstructSale(Double amount) {
		if (amount != null) {
			constructSale += amount;
		}
	}

	public void addServiceSale(Double amount) {
		if (amount != null) {
			serviceSale += amount;
		}
	}

	public void addHouseHoldingSale(Double amount) {
		if (amount != null) {
			houseHoldingSale += amount;
		}
	}

}
