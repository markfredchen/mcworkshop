// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.domain;

import com.mcworkshop.common.domain.DomainObject;
import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.common.util.NumberUtil;

import java.text.DecimalFormat;

/**
 * @author $Author$
 * 
 */
public class TaxData extends DomainObject {

	private static final long serialVersionUID = 1L;
	private int year;
	private int month;
	private Company company = new Company();
	private String industry;
	private Double accSales;
	private Double sales;
	private Double vat;
	private Double operateTax;
	private Double expenseTax;
	private Double domesticIncomingTax;
	private Double foreignIncomingTax;
	private Double housingTax;
	private Double stampTax;
	private Double trafficTax;
	private Double landVAT;
	private Double landUseTax;
	private Double personalIncomingTax;
	private Double constructionTax;
	private Double veichleTax;
	private Double riverTax;
	private Double educationTax;
	private Double cultureTax;
	private Double otherTax;
	private EconomyEntity economyEntity;
	private EconomyNature economyNature;
	private TaxOrg taxOrg;
	private AffiliateRegion affiliateRegion;
	private boolean isMYC;

	private DecimalFormat df = new DecimalFormat("###0.00");

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Double getAccSales() {
		return accSales;
	}

	public void setAccSales(Double accSales) {
		this.accSales = accSales;
	}

	public Double getSales() {
		return sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
	}

	public Double getVat() {
		return vat;
	}

	public void setVat(Double vat) {
		this.vat = vat;
	}

	public Double getOperateTax() {
		return operateTax;
	}

	public void setOperateTax(Double operateTax) {
		this.operateTax = operateTax;
	}

	public Double getExpenseTax() {
		return expenseTax;
	}

	public void setExpenseTax(Double expenseTax) {
		this.expenseTax = expenseTax;
	}

	public Double getDomesticIncomingTax() {
		return domesticIncomingTax;
	}

	public void setDomesticIncomingTax(Double domesticIncomingTax) {
		this.domesticIncomingTax = domesticIncomingTax;
	}

	public Double getForeignIncomingTax() {
		return foreignIncomingTax;
	}

	public void setForeignIncomingTax(Double foreignIncomingTax) {
		this.foreignIncomingTax = foreignIncomingTax;
	}

	public Double getHousingTax() {
		return housingTax;
	}

	public void setHousingTax(Double housingTax) {
		this.housingTax = housingTax;
	}

	public Double getStampTax() {
		return stampTax;
	}

	public void setStampTax(Double stampTax) {
		this.stampTax = stampTax;
	}

	public Double getTrafficTax() {
		return trafficTax;
	}

	public void setTrafficTax(Double trafficTax) {
		this.trafficTax = trafficTax;
	}

	public Double getLandVAT() {
		return landVAT;
	}

	public void setLandVAT(Double landVAT) {
		this.landVAT = landVAT;
	}

	public Double getLandUseTax() {
		return landUseTax;
	}

	public void setLandUseTax(Double landUseTax) {
		this.landUseTax = landUseTax;
	}

	public Double getPersonalIncomingTax() {
		return personalIncomingTax;
	}

	public void setPersonalIncomingTax(Double personalIncomingTax) {
		this.personalIncomingTax = personalIncomingTax;
	}

	public Double getConstructionTax() {
		return constructionTax;
	}

	public void setConstructionTax(Double constructionTax) {
		this.constructionTax = constructionTax;
	}

	public Double getVeichleTax() {
		return veichleTax;
	}

	public void setVeichleTax(Double veichleTax) {
		this.veichleTax = veichleTax;
	}

	public Double getRiverTax() {
		return riverTax;
	}

	public void setRiverTax(Double riverTax) {
		this.riverTax = riverTax;
	}

	public Double getEducationTax() {
		return educationTax;
	}

	public void setEducationTax(Double educationTax) {
		this.educationTax = educationTax;
	}

	public Double getCultureTax() {
		return cultureTax;
	}

	public void setCultureTax(Double cultureTax) {
		this.cultureTax = cultureTax;
	}

	public Double getOtherTax() {
		return otherTax;
	}

	public void setOtherTax(Double otherTax) {
		this.otherTax = otherTax;
	}

	public AffiliateRegion getAffiliateRegion() {
		return affiliateRegion;
	}

	public void setAffiliateRegion(AffiliateRegion affiliateRegion) {
		this.affiliateRegion = affiliateRegion;
	}

	public Long getAffiliateRegionID() {
		return SystemEnumeration.getInstance().getIdByKey(this.affiliateRegion);
	}

	public void setAffiliateRegionID(Long affiliateRegionID) {
		this.affiliateRegion = SystemEnumeration.getInstance().getKeyById(
				AffiliateRegion.class, affiliateRegionID);
	}

	public boolean isMYC() {
		return isMYC;
	}

	public void setMYC(boolean isMYC) {
		this.isMYC = isMYC;
	}

	public Double getTotalTax() {
		return NumberUtil.add(vat, operateTax, expenseTax, domesticIncomingTax,
				foreignIncomingTax, housingTax, stampTax, trafficTax, landVAT,
				landUseTax, personalIncomingTax, constructionTax, veichleTax,
				riverTax, educationTax, cultureTax, otherTax);
	}

	public Double getTotalVAT() {
		return NumberUtil.add(vat, landVAT);
	}

	public Double getTotalCompanyIncomeTax() {
		return NumberUtil.add(domesticIncomingTax, foreignIncomingTax);
	}

	// 印花税+土地使用税+消费税+房产税+车船使用税+土地增值税+车购税+河道费+教育费附加+文化事业费+其它
	public Double getTotalOtherTax() {
		return NumberUtil.add(stampTax, landUseTax, expenseTax, housingTax,
				trafficTax, landVAT, veichleTax, riverTax, educationTax,
				cultureTax, otherTax);
	}

	public String getAccSalesForDisplay() {
		if (this.accSales != null) {
			return df.format(accSales);
		} else {
			return "0.00";
		}
	}

	public String getSalesForDisplay() {
		if (this.sales != null) {
			return df.format(sales);
		} else {
			return "0.00";
		}
	}

	public String getVatForDisplay() {
		if (this.vat != null) {
			return df.format(vat);
		} else {
			return "0.00";
		}
	}

	public String getOperateTaxForDisplay() {
		if (this.operateTax != null) {
			return df.format(operateTax);
		} else {
			return "0.00";
		}
	}

	public String getExpenseTaxForDisplay() {
		if (this.expenseTax != null) {
			return df.format(expenseTax);
		} else {
			return "0.00";
		}
	}

	public String getDomesticIncomingTaxForDisplay() {
		if (this.domesticIncomingTax != null) {
			return df.format(domesticIncomingTax);
		} else {
			return "0.00";
		}
	}

	public String getForeignIncomingTaxForDisplay() {
		if (this.foreignIncomingTax != null) {
			return df.format(foreignIncomingTax);
		} else {
			return "0.00";
		}
	}

	public String getHousingTaxForDisplay() {
		if (this.housingTax != null) {
			return df.format(housingTax);
		} else {
			return "0.00";
		}
	}

	public String getStampTaxForDisplay() {
		if (this.stampTax != null) {
			return df.format(stampTax);
		} else {
			return "0.00";
		}
	}

	public String getTrafficTaxForDisplay() {
		if (this.trafficTax != null) {
			return df.format(trafficTax);
		} else {
			return "0.00";
		}
	}

	public String getLandVATForDisplay() {
		if (this.landVAT != null) {
			return df.format(landVAT);
		} else {
			return "0.00";
		}
	}

	public String getLandUseTaxForDisplay() {
		if (this.landUseTax != null) {
			return df.format(landUseTax);
		} else {
			return "0.00";
		}
	}

	public String getPersonalIncomingTaxForDisplay() {
		if (this.personalIncomingTax != null) {
			return df.format(personalIncomingTax);
		} else {
			return "0.00";
		}
	}

	public String getConstructionTaxForDisplay() {
		if (this.constructionTax != null) {
			return df.format(constructionTax);
		} else {
			return "0.00";
		}
	}

	public String getVeichleTaxForDisplay() {
		if (this.veichleTax != null) {
			return df.format(veichleTax);
		} else {
			return "0.00";
		}
	}

	public String getRiverTaxForDisplay() {
		if (this.riverTax != null) {
			return df.format(riverTax);
		} else {
			return "0.00";
		}
	}

	public String getEducationTaxForDisplay() {
		if (this.educationTax != null) {
			return df.format(educationTax);
		} else {
			return "0.00";
		}
	}

	public String getCultureTaxForDisplay() {
		if (this.cultureTax != null) {
			return df.format(cultureTax);
		} else {
			return "0.00";
		}
	}

	public String getOtherTaxForDisplay() {
		if (this.otherTax != null) {
			return df.format(otherTax);
		} else {
			return "0.00";
		}
	}

	public EconomyEntity getEconomyEntity() {
		return economyEntity;
	}

	public EconomyNature getEconomyNature() {
		return economyNature;
	}

	public void setEconomyEntity(EconomyEntity economyEntity) {
		this.economyEntity = economyEntity;
	}

	public void setEconomyNature(EconomyNature economyNature) {
		this.economyNature = economyNature;
	}

	public Long getEconomyEntityID() {
		return SystemEnumeration.getInstance().getIdByKey(this.economyEntity);
	}

	public Long getEconomyNatureID() {
		return SystemEnumeration.getInstance().getIdByKey(this.economyNature);
	}

	public void setEconomyEntity(Long economyEntityID) {
		this.economyEntity = SystemEnumeration.getInstance().getKeyById(
				EconomyEntity.class, economyEntityID);
	}

	public void setEconomyNature(Long economyNatureID) {
		this.economyNature = SystemEnumeration.getInstance().getKeyById(
				EconomyNature.class, economyNatureID);
	}

	public TaxOrg getTaxOrg() {
		return taxOrg;
	}

	public void setTaxOrg(TaxOrg taxOrg) {
		this.taxOrg = taxOrg;
	}

	public Long getTaxOrgID() {
		return SystemEnumeration.getInstance().getIdByKey(this.taxOrg);
	}

	public void setTaxOrg(Long taxOrgID) {
		this.taxOrg = SystemEnumeration.getInstance().getKeyById(TaxOrg.class,
				taxOrgID);
	}

	public boolean isEmpty() {
		return year == 0;
	}
}
