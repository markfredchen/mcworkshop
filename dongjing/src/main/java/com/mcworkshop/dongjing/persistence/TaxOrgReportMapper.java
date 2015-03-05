// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.persistence;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mcworkshop.dongjing.domain.TaxData;

/**
 * @author $Author$
 * 
 */
public interface TaxOrgReportMapper {
	@Select("SELECT year, month, economyNatureID, SUM(vat)/10000 AS vat, SUM(operateTax)/10000 AS operateTax, SUM(domesticIncomingTax)/10000 AS domesticIncomingTax, SUM(foreignIncomingTax)/10000 AS foreignIncomingTax, SUM(stampTax)/10000 AS stampTax, SUM(landUseTax)/10000 AS landUseTax, SUM(personalIncomingTax)/10000 AS personalIncomingTax, SUM(constructionTax)/10000 AS constructionTax "
			+ "FROM companySaleTaxData "
			+ "WHERE taxOrgID = 1102 AND economyNatureID = 501 AND year = #{year} AND month = #{month}")
	public TaxData getMonthTaxDataByTaxOrg(@Param("year") Integer year,
			@Param("month") Integer month);

	@Select("SELECT year, month, economyEntityID, SUM(sales)/10000 AS sales, SUM(vat)/10000 AS vat, SUM(operateTax)/10000 AS operateTax, SUM(expenseTax)/10000 AS expenseTax, SUM(domesticIncomingTax)/10000 AS domesticIncomingTax, SUM(foreignIncomingTax)/10000 AS foreignIncomingTax, SUM(housingTax)/10000 AS housingTax, SUM(stampTax)/10000 AS stampTax, SUM(trafficTax)/10000 AS trafficTax, SUM(landVAT)/10000 AS landVAT, SUM(landUseTax)/10000 AS landUseTax, SUM(personalIncomingTax)/10000 AS personalIncomingTax, SUM(constructionTax)/10000 AS constructionTax, SUM(veichleTax)/10000 AS veichleTax, SUM(riverTax)/10000 AS riverTax, SUM(educationTax)/10000 AS educationTax, SUM(cultureTax)/10000 AS cultureTax, SUM(otherTax)/10000 AS otherTax "
			+ "FROM companySaleTaxData "
			+ "WHERE taxOrgID = 1102 AND economyEntityID = 405 AND year = #{year} AND month = #{month}")
	TaxData getMonthTaxDataByEconomyEntity(@Param("year") Integer year,
			@Param("month") Integer month);

	@Select("SELECT year, month, economyNatureID, SUM(vat)/10000 AS vat, SUM(operateTax)/10000 AS operateTax, SUM(domesticIncomingTax)/10000 AS domesticIncomingTax, SUM(foreignIncomingTax)/10000 AS foreignIncomingTax, SUM(stampTax)/10000 AS stampTax, SUM(landUseTax)/10000 AS landUseTax, SUM(personalIncomingTax)/10000 AS personalIncomingTax, SUM(constructionTax)/10000 AS constructionTax "
			+ "FROM companySaleTaxData "
			+ "WHERE taxOrgID = 1102 AND economyNatureID = 501 AND year = #{year} AND month <= #{month}")
	public TaxData getYearTaxDataByTaxOrg(@Param("year") Integer year,
			@Param("month") Integer month);

	@Select("SELECT year, month, economyEntityID, SUM(sales)/10000 AS sales, SUM(vat)/10000 AS vat, SUM(operateTax)/10000 AS operateTax, SUM(expenseTax)/10000 AS expenseTax, SUM(domesticIncomingTax)/10000 AS domesticIncomingTax, SUM(foreignIncomingTax)/10000 AS foreignIncomingTax, SUM(housingTax)/10000 AS housingTax, SUM(stampTax)/10000 AS stampTax, SUM(trafficTax)/10000 AS trafficTax, SUM(landVAT)/10000 AS landVAT, SUM(landUseTax)/10000 AS landUseTax, SUM(personalIncomingTax)/10000 AS personalIncomingTax, SUM(constructionTax)/10000 AS constructionTax, SUM(veichleTax)/10000 AS veichleTax, SUM(riverTax)/10000 AS riverTax, SUM(educationTax)/10000 AS educationTax, SUM(cultureTax)/10000 AS cultureTax, SUM(otherTax)/10000 AS otherTax "
			+ "FROM companySaleTaxData "
			+ "WHERE taxOrgID = 1102 AND economyNatureID = 501 AND year = #{year} AND month <= #{month}")
	TaxData getYearTaxDataByEconomyEntity(@Param("year") Integer year,
			@Param("month") Integer month);

}
