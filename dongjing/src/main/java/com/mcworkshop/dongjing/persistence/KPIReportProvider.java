// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.persistence;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author $Author$
 * 
 */
public class KPIReportProvider {

	public static String getCompanyEconomyEntity(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("industryCount");
				SELECT("commercialCount");
				SELECT("constructionCount");
				SELECT("serviceCount");
				SELECT("houseHoldingCount");
				FROM("(SELECT count(*) AS industryCount FROM company WHERE economyEntityID = 401) AS A");
				JOIN("(SELECT count(*) AS commercialCount FROM company WHERE economyEntityID = 402) AS B");
				JOIN("(SELECT count(*) AS constructionCount FROM company WHERE economyEntityID = 404) AS C");
				JOIN("(SELECT count(*) AS serviceCount FROM company WHERE economyEntityID = 403) AS D");
				JOIN("(SELECT count(*) AS houseHoldingCount FROM company WHERE economyEntityID = 405) AS E");
			}
		}.toString();
	}

	public static String getCompanyAffiliationRegion(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("regCount");
				SELECT("localCount");
				SELECT("mycCount");
				FROM("(SELECT count(*) AS regCount FROM company WHERE affiliateRegionID = 201 OR affiliateRegionID = 203) AS A");
				JOIN("(SELECT count(*) AS localCount FROM company WHERE affiliateRegionID = 202 OR affiliateRegionID = 204) AS B");
				JOIN("(SELECT count(*) AS mycCount FROM company WHERE industryType = 'myc') AS C");
			}
		}.toString();
	}
	


	public static String getCompanyConfirmedByEconomyEntity(
			Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("industryCount");
				SELECT("commercialCount");
				SELECT("constructionCount");
				SELECT("serviceCount");
				SELECT("houseHoldingCount");
				FROM("(SELECT count(*) AS industryCount FROM company WHERE economyEntityID = 401 AND commercialStatusID = 304) AS A");
				JOIN("(SELECT count(*) AS commercialCount FROM company WHERE economyEntityID = 402 AND commercialStatusID = 304) AS B");
				JOIN("(SELECT count(*) AS constructionCount FROM company WHERE economyEntityID = 404 AND commercialStatusID = 304) AS C");
				JOIN("(SELECT count(*) AS serviceCount FROM company WHERE economyEntityID = 403 AND commercialStatusID = 304) AS D");
				JOIN("(SELECT count(*) AS houseHoldingCount FROM company WHERE economyEntityID = 405 AND commercialStatusID = 304) AS E");
			}
		}.toString();
	}

	public static String getCompanyConfirmedByAffiliationRegion(
			Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("regCount");
				SELECT("localCount");
				SELECT("mycCount");
				FROM("(SELECT count(*) AS regCount FROM company WHERE (affiliateRegionID = 201 OR affiliateRegionID = 203) AND commercialStatusID = 304) AS A");
				JOIN("(SELECT count(*) AS localCount FROM company WHERE (affiliateRegionID = 202 OR affiliateRegionID = 204) AND commercialStatusID = 304) AS B");
				JOIN("(SELECT count(*) AS mycCount FROM company WHERE industryType = 'myc' AND commercialStatusID = 304) AS C");
			}
		}.toString();
	}

	public static String getNewRegCompanies(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("industryCount");
				SELECT("commercialCount");
				SELECT("constructionCount");
				SELECT("serviceCount");
				SELECT("houseHoldingCount");
				FROM("(SELECT count(*) AS industryCount FROM company WHERE economyEntityID = 401 AND establishDate BETWEEN #{startDate} AND #{endDate}) AS A");
				JOIN("(SELECT count(*) AS commercialCount FROM company WHERE economyEntityID = 402 AND establishDate BETWEEN #{startDate} AND #{endDate}) AS B");
				JOIN("(SELECT count(*) AS constructionCount FROM company WHERE economyEntityID = 404 AND establishDate BETWEEN #{startDate} AND #{endDate}) AS C");
				JOIN("(SELECT count(*) AS serviceCount FROM company WHERE economyEntityID = 403 AND establishDate BETWEEN #{startDate} AND #{endDate}) AS D");
				JOIN("(SELECT count(*) AS houseHoldingCount FROM company WHERE economyEntityID = 405 AND establishDate BETWEEN #{startDate} AND #{endDate}) AS E");
			}
		}.toString();
	}

	public static String getNewRegCompaniesAffiliationRegion(
			Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("regCount");
				SELECT("localCount");
				SELECT("mycCount");
				FROM("(SELECT count(*) AS regCount FROM company WHERE (affiliateRegionID = 201 OR affiliateRegionID = 203) AND establishDate BETWEEN #{startDate} AND #{endDate}) AS A");
				JOIN("(SELECT count(*) AS localCount FROM company WHERE (affiliateRegionID = 202 OR affiliateRegionID = 204) AND establishDate BETWEEN #{startDate} AND #{endDate}) AS B");
				JOIN("(SELECT count(*) AS mycCount FROM company WHERE industryType = 'myc' AND establishDate BETWEEN #{startDate} AND #{endDate}) AS C");
			}
		}.toString();
	}

	public static String getNewCancellationCompanies(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("industryCount");
				SELECT("commercialCount");
				SELECT("constructionCount");
				SELECT("serviceCount");
				SELECT("houseHoldingCount");
				FROM("(SELECT count(*) AS industryCount FROM company WHERE economyEntityID = 401 AND taxCancellation BETWEEN #{startDate} AND #{endDate}) AS A");
				JOIN("(SELECT count(*) AS commercialCount FROM company WHERE economyEntityID = 402 AND taxCancellation BETWEEN #{startDate} AND #{endDate}) AS B");
				JOIN("(SELECT count(*) AS constructionCount FROM company WHERE economyEntityID = 404 AND taxCancellation BETWEEN #{startDate} AND #{endDate}) AS C");
				JOIN("(SELECT count(*) AS serviceCount FROM company WHERE economyEntityID = 403 AND taxCancellation BETWEEN #{startDate} AND #{endDate}) AS D");
				JOIN("(SELECT count(*) AS houseHoldingCount FROM company WHERE economyEntityID = 405 AND taxCancellation BETWEEN #{startDate} AND #{endDate}) AS E");
			}
		}.toString();
	}

	public static String getNewCancellationAFCompanies(
			Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("regCount");
				SELECT("localCount");
				SELECT("mycCount");
				FROM("(SELECT count(*) AS regCount FROM company WHERE (affiliateRegionID = 201 OR affiliateRegionID = 203) AND taxCancellation BETWEEN #{startDate} AND #{endDate}) AS A");
				JOIN("(SELECT count(*) AS localCount FROM company WHERE (affiliateRegionID = 202 OR affiliateRegionID = 204) AND taxCancellation BETWEEN #{startDate} AND #{endDate}) AS B");
				JOIN("(SELECT count(*) AS mycCount FROM company WHERE industryType = 'myc' AND taxCancellation BETWEEN #{startDate} AND #{endDate}) AS C");
			}
		}.toString();
	}

	public static String getNewRegAssetsCompanies(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("industry");
				SELECT("commercial");
				SELECT("construction");
				SELECT("service");
				SELECT("houseHolding");
				FROM("(SELECT SUM(registerAsset) AS industry FROM company WHERE economyEntityID = 401 AND establishDate BETWEEN #{startDate} AND #{endDate}) AS A");
				JOIN("(SELECT SUM(registerAsset) AS commercial FROM company WHERE economyEntityID = 402 AND establishDate BETWEEN #{startDate} AND #{endDate}) AS B");
				JOIN("(SELECT SUM(registerAsset) AS construction FROM company WHERE economyEntityID = 404 AND establishDate BETWEEN #{startDate} AND #{endDate}) AS C");
				JOIN("(SELECT SUM(registerAsset) AS service FROM company WHERE economyEntityID = 403 AND establishDate BETWEEN #{startDate} AND #{endDate}) AS D");
				JOIN("(SELECT SUM(registerAsset) AS houseHolding FROM company WHERE economyEntityID = 405 AND establishDate BETWEEN #{startDate} AND #{endDate}) AS E");
			}
		}.toString();
	}

	public static String getNewRegAssetsAFCompanies(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("reg");
				SELECT("local");
				SELECT("myc");
				FROM("(SELECT SUM(registerAsset) AS reg FROM company WHERE (affiliateRegionID = 201 OR affiliateRegionID = 203 ) AND establishDate BETWEEN #{startDate} AND #{endDate}) AS A");
				JOIN("(SELECT SUM(registerAsset) AS local FROM company WHERE (affiliateRegionID = 202 OR affiliateRegionID = 204) AND establishDate BETWEEN #{startDate} AND #{endDate}) AS B");
				JOIN("(SELECT SUM(registerAsset) AS myc FROM company WHERE industryType = 'myc' AND establishDate BETWEEN #{startDate} AND #{endDate}) AS C");
			}
		}.toString();
	}

	public static String getAllRegAssetsCompanies(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("industry");
				SELECT("commercial");
				SELECT("construction");
				SELECT("service");
				SELECT("houseHolding");
				FROM("(SELECT SUM(registerAsset) AS industry FROM company WHERE economyEntityID = 401) AS A");
				JOIN("(SELECT SUM(registerAsset) AS commercial FROM company WHERE economyEntityID = 402) AS B");
				JOIN("(SELECT SUM(registerAsset) AS construction FROM company WHERE economyEntityID = 404) AS C");
				JOIN("(SELECT SUM(registerAsset) AS service FROM company WHERE economyEntityID = 403) AS D");
				JOIN("(SELECT SUM(registerAsset) AS houseHolding FROM company WHERE economyEntityID = 405) AS E");
			}
		}.toString();
	}

	public static String getAllRegAssetsAFCompanies(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("reg");
				SELECT("local");
				SELECT("myc");
				FROM("(SELECT SUM(registerAsset) AS reg FROM company WHERE affiliateRegionID = 201 OR affiliateRegionID = 203) AS A");
				JOIN("(SELECT SUM(registerAsset) AS local FROM company WHERE affiliateRegionID = 202 OR affiliateRegionID = 204) AS B");
				JOIN("(SELECT SUM(registerAsset) AS myc FROM company WHERE industryType = 'myc') AS C");
			}
		}.toString();
	}

	public static String getCheckedTaxCompanyEconomyEntity(
			Map<String, Object> params) {
		final Integer year = (Integer) params.get("year");
		final Integer month = (Integer) params.get("month");
		return new SQL() {
			{
				SELECT("industryCount");
				SELECT("commercialCount");
				SELECT("constructionCount");
				SELECT("serviceCount");
				SELECT("houseHoldingCount");
				FROM("(SELECT count(*) AS industryCount FROM checkedTaxData WHERE economyEntityID = 401 AND year = "
						+ year + " AND month = " + month + ") AS A");
				JOIN("(SELECT count(*) AS commercialCount FROM checkedTaxData WHERE economyEntityID = 402 AND year = "
						+ year + " AND month = " + month + ") AS B");
				JOIN("(SELECT count(*) AS constructionCount FROM checkedTaxData WHERE economyEntityID = 404 AND year = "
						+ year + " AND month = " + month + ") AS C");
				JOIN("(SELECT count(*) AS serviceCount FROM checkedTaxData WHERE economyEntityID = 403 AND year = "
						+ year + " AND month = " + month + ") AS D");
				JOIN("(SELECT count(*) AS houseHoldingCount FROM checkedTaxData WHERE economyEntityID = 405 AND year = "
						+ year + " AND month = " + month + ") AS E");
			}
		}.toString();
	}

	public static String getCheckedTaxAFCompanyEconomyEntity(
			Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("regCount");
				SELECT("localCount");
				SELECT("mycCount");
				FROM("(SELECT count(*) AS regCount FROM checkedTaxData WHERE (affiliateRegionID = 201 OR affiliateRegionID = 203) AND year = #{year} AND month = #{month}) AS A");
				JOIN("(SELECT count(*) AS localCount FROM checkedTaxData WHERE (affiliateRegionID = 202 OR affiliateRegionID = 204) AND year = #{year} AND month = #{month}) AS B");
				JOIN("(SELECT count(*) AS mycCount FROM checkedTaxData WHERE isMYC = 'true' AND year = #{year} AND month = #{month}) AS C");
			}
		}.toString();
	}

	public static String getTaxedCompanyEconomyEntity(Map<String, Object> params) {
		final Integer year = (Integer) params.get("year");
		final Integer month = (Integer) params.get("month");
		return new SQL() {
			{
				SELECT("industryCount");
				SELECT("commercialCount");
				SELECT("constructionCount");
				SELECT("serviceCount");
				SELECT("houseHoldingCount");
				FROM("(SELECT count(*) AS industryCount FROM companySaleTaxData WHERE economyEntityID = 401 AND year = "
						+ year + " AND month = " + month + ") AS A");
				JOIN("(SELECT count(*) AS commercialCount FROM companySaleTaxData WHERE economyEntityID = 402 AND year = "
						+ year + " AND month = " + month + ") AS B");
				JOIN("(SELECT count(*) AS constructionCount FROM companySaleTaxData WHERE economyEntityID = 404 AND year = "
						+ year + " AND month = " + month + ") AS C");
				JOIN("(SELECT count(*) AS serviceCount FROM companySaleTaxData WHERE economyEntityID = 403 AND year = "
						+ year + " AND month = " + month + ") AS D");
				JOIN("(SELECT count(*) AS houseHoldingCount FROM companySaleTaxData WHERE economyEntityID = 405 AND year = "
						+ year + " AND month = " + month + ") AS E");
			}
		}.toString();
	}

	public static String getTaxedCompanyAffiliateRegion(
			Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("regCount");
				SELECT("localCount");
				SELECT("mycCount");
				FROM("(SELECT count(*) AS regCount FROM companySaleTaxData WHERE (affiliateRegionID = 201 OR affiliateRegionID = 203) AND year = #{year} AND month = #{month}) AS A");
				JOIN("(SELECT count(*) AS localCount FROM companySaleTaxData WHERE (affiliateRegionID = 202 OR affiliateRegionID = 204) AND year = #{year} AND month = #{month}) AS B");
				JOIN("(SELECT count(*) AS mycCount FROM companySaleTaxData WHERE isMYC = 'true' AND year = #{year} AND month = #{month}) AS C");
			}
		}.toString();
	}

	public static String getMonthTotalSalesByEconmyEntity(
			Map<String, Object> params) {
		final Integer year = (Integer) params.get("year");
		final Integer month = (Integer) params.get("month");
		return new SQL() {
			{
				SELECT("industrySales");
				SELECT("commercialSales");
				SELECT("constructionSales");
				SELECT("serviceSales");
				SELECT("houseHoldingSales");
				FROM("(SELECT SUM(sales) AS industrySales FROM companySaleTaxData WHERE economyEntityID = 401 AND year = "
						+ year + " AND month = " + month + ") AS A");
				JOIN("(SELECT SUM(sales) AS commercialSales FROM companySaleTaxData WHERE economyEntityID = 402 AND year = "
						+ year + " AND month = " + month + ") AS B");
				JOIN("(SELECT SUM(sales) AS constructionSales FROM companySaleTaxData WHERE economyEntityID = 404 AND year = "
						+ year + " AND month = " + month + ") AS C");
				JOIN("(SELECT SUM(sales) AS serviceSales FROM companySaleTaxData WHERE economyEntityID = 403 AND year = "
						+ year + " AND month = " + month + ") AS D");
				JOIN("(SELECT SUM(sales) AS houseHoldingSales FROM companySaleTaxData WHERE economyEntityID = 405 AND year = "
						+ year + " AND month = " + month + ") AS E");
			}
		}.toString();
	}

	public static String getYearTotalSalesByEconmyEntity(
			Map<String, Object> params) {
		final Integer year = (Integer) params.get("year");
		final Integer month = (Integer) params.get("month");
		return new SQL() {
			{
				SELECT("industrySales");
				SELECT("commercialSales");
				SELECT("constructionSales");
				SELECT("serviceSales");
				SELECT("houseHoldingSales");
				FROM("(SELECT SUM(sales) AS industrySales FROM companySaleTaxData WHERE economyEntityID = 401 AND year = "
						+ year + " AND month <= " + month + ") AS A");
				JOIN("(SELECT SUM(sales) AS commercialSales FROM companySaleTaxData WHERE economyEntityID = 402 AND year = "
						+ year + " AND month <= " + month + ") AS B");
				JOIN("(SELECT SUM(sales) AS constructionSales FROM companySaleTaxData WHERE economyEntityID = 404 AND year = "
						+ year + " AND month <= " + month + ") AS C");
				JOIN("(SELECT SUM(sales) AS serviceSales FROM companySaleTaxData WHERE economyEntityID = 403 AND year = "
						+ year + " AND month <= " + month + ") AS D");
				JOIN("(SELECT SUM(sales) AS houseHoldingSales FROM companySaleTaxData WHERE economyEntityID = 405 AND year = "
						+ year + " AND month <= " + month + ") AS E");
			}
		}.toString();
	}

	public static String getYearTotalSalesByAffiliateRegion(
			Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("regSales");
				SELECT("localSales");
				SELECT("mycSales");
				FROM("(SELECT SUM(sales) AS regSales FROM companySaleTaxData WHERE (affiliateRegionID = 201 OR affiliateRegionID = 203) AND year = #{year} AND month <= #{month}) AS A");
				JOIN("(SELECT SUM(sales) AS localSales FROM companySaleTaxData WHERE (affiliateRegionID = 202 OR affiliateRegionID = 204) AND year = #{year} AND month <= #{month}) AS B");
				JOIN("(SELECT SUM(sales) AS mycSales FROM companySaleTaxData WHERE isMYC = 'true' AND year = #{year} AND month <= #{month}) AS C");
			}
		}.toString();
	}

	public static String getMonthTotalTaxByEconomyEntity(
			Map<String, Object> params) {
		final Integer year = (Integer) params.get("year");
		final Integer month = (Integer) params.get("month");
		return new SQL() {
			{
				SELECT("industryTax");
				SELECT("commercialTax");
				SELECT("constructionTax");
				SELECT("serviceTax");
				SELECT("houseHoldingTax");
				FROM("(SELECT SUM(IFNull(vat, 0) + IFNull(operateTax, 0) + IFNull(expenseTax, 0) + IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(housingTax, 0) + IFNull(stampTax, 0) + IFNull(trafficTax, 0) + IFNull(landVAT, 0) + IFNull(landUseTax, 0) + IFNull(personalIncomingTax, 0) + IFNull(constructionTax, 0) + IFNull(veichleTax, 0) + IFNull(riverTax, 0) + IFNull(educationTax, 0) + IFNull(cultureTax, 0) + IFNull(otherTax, 0)) AS industryTax FROM companySaleTaxData WHERE economyEntityID = 401 AND year = "
						+ year + " AND month = " + month + ") AS A");
				JOIN("(SELECT SUM(IFNull(vat, 0) + IFNull(operateTax, 0) + IFNull(expenseTax, 0) + IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(housingTax, 0) + IFNull(stampTax, 0) + IFNull(trafficTax, 0) + IFNull(landVAT, 0) + IFNull(landUseTax, 0) + IFNull(personalIncomingTax, 0) + IFNull(constructionTax, 0) + IFNull(veichleTax, 0) + IFNull(riverTax, 0) + IFNull(educationTax, 0) + IFNull(cultureTax, 0) + IFNull(otherTax, 0)) AS commercialTax FROM companySaleTaxData WHERE economyEntityID = 402 AND year = "
						+ year + " AND month = " + month + ") AS B");
				JOIN("(SELECT SUM(IFNull(vat, 0) + IFNull(operateTax, 0) + IFNull(expenseTax, 0) + IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(housingTax, 0) + IFNull(stampTax, 0) + IFNull(trafficTax, 0) + IFNull(landVAT, 0) + IFNull(landUseTax, 0) + IFNull(personalIncomingTax, 0) + IFNull(constructionTax, 0) + IFNull(veichleTax, 0) + IFNull(riverTax, 0) + IFNull(educationTax, 0) + IFNull(cultureTax, 0) + IFNull(otherTax, 0)) AS serviceTax FROM companySaleTaxData WHERE economyEntityID = 403 AND year = "
						+ year + " AND month = " + month + ") AS C");
				JOIN("(SELECT SUM(IFNull(vat, 0) + IFNull(operateTax, 0) + IFNull(expenseTax, 0) + IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(housingTax, 0) + IFNull(stampTax, 0) + IFNull(trafficTax, 0) + IFNull(landVAT, 0) + IFNull(landUseTax, 0) + IFNull(personalIncomingTax, 0) + IFNull(constructionTax, 0) + IFNull(veichleTax, 0) + IFNull(riverTax, 0) + IFNull(educationTax, 0) + IFNull(cultureTax, 0) + IFNull(otherTax, 0)) AS constructionTax FROM companySaleTaxData WHERE economyEntityID = 404 AND year = "
						+ year + " AND month = " + month + ") AS D");
				JOIN("(SELECT SUM(IFNull(vat, 0) + IFNull(operateTax, 0) + IFNull(expenseTax, 0) + IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(housingTax, 0) + IFNull(stampTax, 0) + IFNull(trafficTax, 0) + IFNull(landVAT, 0) + IFNull(landUseTax, 0) + IFNull(personalIncomingTax, 0) + IFNull(constructionTax, 0) + IFNull(veichleTax, 0) + IFNull(riverTax, 0) + IFNull(educationTax, 0) + IFNull(cultureTax, 0) + IFNull(otherTax, 0)) AS houseHoldingTax FROM companySaleTaxData WHERE economyEntityID = 405 AND year = "
						+ year + " AND month = " + month + ") AS E");
			}
		}.toString();
	}

	public static String getMonthTotalTaxByAffiliateRegion(
			Map<String, Object> params) {
		final Integer year = (Integer) params.get("year");
		final Integer month = (Integer) params.get("month");
		return new SQL() {
			{
				SELECT("regTax");
				SELECT("localTax");
				SELECT("mycTax");
				FROM("(SELECT SUM(IFNull(vat, 0) + IFNull(operateTax, 0) + IFNull(expenseTax, 0) + IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(housingTax, 0) + IFNull(stampTax, 0) + IFNull(trafficTax, 0) + IFNull(landVAT, 0) + IFNull(landUseTax, 0) + IFNull(personalIncomingTax, 0) + IFNull(constructionTax, 0) + IFNull(veichleTax, 0) + IFNull(riverTax, 0) + IFNull(educationTax, 0) + IFNull(cultureTax, 0) + IFNull(otherTax, 0)) AS regTax FROM companySaleTaxData WHERE (affiliateRegionID = 201 OR affiliateRegionID = 203) AND year = "
						+ year + " AND month = " + month + ") AS A");
				JOIN("(SELECT SUM(IFNull(vat, 0) + IFNull(operateTax, 0) + IFNull(expenseTax, 0) + IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(housingTax, 0) + IFNull(stampTax, 0) + IFNull(trafficTax, 0) + IFNull(landVAT, 0) + IFNull(landUseTax, 0) + IFNull(personalIncomingTax, 0) + IFNull(constructionTax, 0) + IFNull(veichleTax, 0) + IFNull(riverTax, 0) + IFNull(educationTax, 0) + IFNull(cultureTax, 0) + IFNull(otherTax, 0)) AS localTax FROM companySaleTaxData WHERE (affiliateRegionID = 202 OR affiliateRegionID = 204) AND year = "
						+ year + " AND month = " + month + ") AS B");
				JOIN("(SELECT SUM(IFNull(vat, 0) + IFNull(operateTax, 0) + IFNull(expenseTax, 0) + IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(housingTax, 0) + IFNull(stampTax, 0) + IFNull(trafficTax, 0) + IFNull(landVAT, 0) + IFNull(landUseTax, 0) + IFNull(personalIncomingTax, 0) + IFNull(constructionTax, 0) + IFNull(veichleTax, 0) + IFNull(riverTax, 0) + IFNull(educationTax, 0) + IFNull(cultureTax, 0) + IFNull(otherTax, 0)) AS mycTax FROM companySaleTaxData WHERE isMYC = 'true' AND year = "
						+ year + " AND month = " + month + ") AS C");
			}
		}.toString();
	}

	public static String getYearTotalTaxByEconomyEntity(
			Map<String, Object> params) {
		final Integer year = (Integer) params.get("year");
		final Integer month = (Integer) params.get("month");
		return new SQL() {
			{
				SELECT("industryTax");
				SELECT("commercialTax");
				SELECT("constructionTax");
				SELECT("serviceTax");
				SELECT("houseHoldingTax");
				FROM("(SELECT SUM(IFNull(vat, 0) + IFNull(operateTax, 0) + IFNull(expenseTax, 0) + IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(housingTax, 0) + IFNull(stampTax, 0) + IFNull(trafficTax, 0) + IFNull(landVAT, 0) + IFNull(landUseTax, 0) + IFNull(personalIncomingTax, 0) + IFNull(constructionTax, 0) + IFNull(veichleTax, 0) + IFNull(riverTax, 0) + IFNull(educationTax, 0) + IFNull(cultureTax, 0) + IFNull(otherTax, 0)) AS industryTax FROM companySaleTaxData WHERE economyEntityID = 401 AND year = "
						+ year + " AND month <= " + month + ") AS A");
				JOIN("(SELECT SUM(IFNull(vat, 0) + IFNull(operateTax, 0) + IFNull(expenseTax, 0) + IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(housingTax, 0) + IFNull(stampTax, 0) + IFNull(trafficTax, 0) + IFNull(landVAT, 0) + IFNull(landUseTax, 0) + IFNull(personalIncomingTax, 0) + IFNull(constructionTax, 0) + IFNull(veichleTax, 0) + IFNull(riverTax, 0) + IFNull(educationTax, 0) + IFNull(cultureTax, 0) + IFNull(otherTax, 0)) AS commercialTax FROM companySaleTaxData WHERE economyEntityID = 402 AND year = "
						+ year + " AND month <= " + month + ") AS B");
				JOIN("(SELECT SUM(IFNull(vat, 0) + IFNull(operateTax, 0) + IFNull(expenseTax, 0) + IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(housingTax, 0) + IFNull(stampTax, 0) + IFNull(trafficTax, 0) + IFNull(landVAT, 0) + IFNull(landUseTax, 0) + IFNull(personalIncomingTax, 0) + IFNull(constructionTax, 0) + IFNull(veichleTax, 0) + IFNull(riverTax, 0) + IFNull(educationTax, 0) + IFNull(cultureTax, 0) + IFNull(otherTax, 0)) AS serviceTax FROM companySaleTaxData WHERE economyEntityID = 403 AND year = "
						+ year + " AND month <= " + month + ") AS C");
				JOIN("(SELECT SUM(IFNull(vat, 0) + IFNull(operateTax, 0) + IFNull(expenseTax, 0) + IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(housingTax, 0) + IFNull(stampTax, 0) + IFNull(trafficTax, 0) + IFNull(landVAT, 0) + IFNull(landUseTax, 0) + IFNull(personalIncomingTax, 0) + IFNull(constructionTax, 0) + IFNull(veichleTax, 0) + IFNull(riverTax, 0) + IFNull(educationTax, 0) + IFNull(cultureTax, 0) + IFNull(otherTax, 0)) AS constructionTax FROM companySaleTaxData WHERE economyEntityID = 404 AND year = "
						+ year + " AND month <= " + month + ") AS D");
				JOIN("(SELECT SUM(IFNull(vat, 0) + IFNull(operateTax, 0) + IFNull(expenseTax, 0) + IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(housingTax, 0) + IFNull(stampTax, 0) + IFNull(trafficTax, 0) + IFNull(landVAT, 0) + IFNull(landUseTax, 0) + IFNull(personalIncomingTax, 0) + IFNull(constructionTax, 0) + IFNull(veichleTax, 0) + IFNull(riverTax, 0) + IFNull(educationTax, 0) + IFNull(cultureTax, 0) + IFNull(otherTax, 0)) AS houseHoldingTax FROM companySaleTaxData WHERE economyEntityID = 405 AND year = "
						+ year + " AND month <= " + month + ") AS E");
			}
		}.toString();
	}

	public static String getMonthTotalIncomingTaxByEconomyEntity(
			Map<String, Object> params) {
		final Integer year = (Integer) params.get("year");
		final Integer month = (Integer) params.get("month");
		return new SQL() {
			{
				SELECT("industryTax");
				SELECT("commercialTax");
				SELECT("constructionTax");
				SELECT("serviceTax");
				SELECT("houseHoldingTax");
				FROM("(SELECT SUM(IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(personalIncomingTax, 0)) AS industryTax FROM companySaleTaxData WHERE economyEntityID = 401 AND year = "
						+ year + " AND month < " + month + ") AS A");
				JOIN("(SELECT SUM(IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(personalIncomingTax, 0)) AS commercialTax FROM companySaleTaxData WHERE economyEntityID = 402 AND year = "
						+ year + " AND month < " + month + ") AS B");
				JOIN("(SELECT SUM(IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(personalIncomingTax, 0)) AS serviceTax FROM companySaleTaxData WHERE economyEntityID = 403 AND year = "
						+ year + " AND month < " + month + ") AS C");
				JOIN("(SELECT SUM(IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(personalIncomingTax, 0)) AS constructionTax FROM companySaleTaxData WHERE economyEntityID = 404 AND year = "
						+ year + " AND month < " + month + ") AS D");
				JOIN("(SELECT SUM(IFNull(domesticIncomingTax, 0) + IFNull(foreignIncomingTax, 0) + IFNull(personalIncomingTax, 0)) AS houseHoldingTax FROM companySaleTaxData WHERE economyEntityID = 405 AND year = "
						+ year + " AND month < " + month + ") AS E");
			}
		}.toString();
	}
}
