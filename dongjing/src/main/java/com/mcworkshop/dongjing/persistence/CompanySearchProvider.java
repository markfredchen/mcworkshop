// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.persistence;

import com.mcworkshop.common.search.Restriction;
import com.mcworkshop.common.search.SearchCriteria;
import com.mcworkshop.common.search.SearchFilter;
import com.mcworkshop.common.sysenum.SystemEnumeration;
import com.mcworkshop.dongjing.domain.EconomyEntity;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * @author $Author$
 * 
 */
public class CompanySearchProvider {

	public static String searchCompany(Map<String, Object> params) {
		final SearchCriteria criteria = (SearchCriteria) params
				.get("searchCriteria");
		return new SQL() {
			{
				for (String field : criteria.getFields()) {
					SELECT(field);
				}
				FROM("company");
				int i = 0;
				for (SearchFilter filter : criteria.getFilters()) {
					if (i > 0
							&& filter.getFilterRestriction().equals(
									Restriction.OR)) {
						OR();
					}
					WHERE(filter.getClause());
					i++;
				}
			}
		}.toString()
				+ " LIMIT "
				+ criteria.getStartIndex()
				+ ","
				+ criteria.getFetch();
	}

	public static String searchCompanyWithTax(Map<String, Object> params) {
		final SearchCriteria criteria = (SearchCriteria) params
				.get("searchCriteria");
		return new SQL() {
			{
				for (String field : criteria.getFields()) {
					SELECT_DISTINCT(field);
				}

				FROM("company c");
				INNER_JOIN("companySaleTaxData t ON t.companyID = c.companyID");
				int i = 0;
				for (SearchFilter filter : criteria.getFilters()) {
					if (i > 0
							&& filter.getFilterRestriction().equals(
									Restriction.OR)) {
						OR();
					}
					WHERE(filter.getClause());
					i++;
				}
			}
		}.toString()
				+ " LIMIT "
				+ criteria.getStartIndex()
				+ ","
				+ criteria.getFetch();
	}

	public static String searchCompanyTotal(Map<String, Object> params) {
		final SearchCriteria criteria = (SearchCriteria) params
				.get("searchCriteria");
		return new SQL() {
			{
				SELECT("count(*) AS total");
				FROM("company c");
				int i = 0;
				for (SearchFilter filter : criteria.getFilters()) {
					if (i > 0
							&& filter.getFilterRestriction().equals(
									Restriction.OR)) {
						OR();
					}
					WHERE(filter.getClause());
					i++;
				}
			}
		}.toString();
	}

	public static String searchCompanyWithTaxTotal(Map<String, Object> params) {
		final SearchCriteria criteria = (SearchCriteria) params
				.get("searchCriteria");
		return new SQL() {
			{
				SELECT("count(*) AS total");
				FROM("company c");
				INNER_JOIN("companySaleTaxData t ON t.companyID = c.companyID");
				int i = 0;
				for (SearchFilter filter : criteria.getFilters()) {
					if (i > 0
							&& filter.getFilterRestriction().equals(
									Restriction.OR)) {
						OR();
					}
					WHERE(filter.getClause());
					i++;
				}
			}
		}.toString();
	}

	public static String getCompanyEconomyEntity(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("industryCount");
				SELECT("commercialCount");
				SELECT("constructCount");
				SELECT("serviceCount");
				SELECT("houseHoldingCount");
				FROM("(SELECT count(*) AS industryCount FROM company WHERE economyEntityID = "
						+ SystemEnumeration.getInstance().getIdByKey(
								EconomyEntity.INDUSTRY) + ") AS A");
				JOIN("(SELECT count(*) AS commercialCount FROM company WHERE economyEntityID = "
						+ SystemEnumeration.getInstance().getIdByKey(
								EconomyEntity.COMMERCIAL) + ") AS B");
				JOIN("(SELECT count(*) AS constructCount FROM company WHERE economyEntityID = "
						+ SystemEnumeration.getInstance().getIdByKey(
								EconomyEntity.CONSTRUCTION) + ") AS C");
				JOIN("(SELECT count(*) AS serviceCount FROM company WHERE economyEntityID = "
						+ SystemEnumeration.getInstance().getIdByKey(
								EconomyEntity.SERVICE) + ") AS D");
				JOIN("(SELECT count(*) AS houseHoldingCount FROM company WHERE economyEntityID = "
						+ SystemEnumeration.getInstance().getIdByKey(
								EconomyEntity.HOUSEHOLDING) + ") AS E");
			}
		}.toString();
	}

	public static String getCompanyEconomyEntityMonthlyCount(
			Map<String, Object> params) {
		final Integer year = (Integer) params.get("year");
		final Integer month = (Integer) params.get("month");
		return new SQL() {
			{
				SELECT("industryMonthCount");
				SELECT("commercialMonthCount");
				SELECT("constructMonthCount");
				SELECT("serviceMonthCount");
				SELECT("houseHoldingMonthCount");
				FROM("(SELECT count(c.companyID) AS industryMonthCount FROM company c INNER JOIN companySaleTaxData t ON t.companyID = c.companyID WHERE t.year = "
						+ year
						+ " AND t.month = "
						+ month
						+ " AND c.economyEntityID = "
						+ SystemEnumeration.getInstance().getIdByKey(
								EconomyEntity.INDUSTRY) + ") AS A");
				JOIN("(SELECT count(c.companyID) AS commercialMonthCount FROM company c INNER JOIN companySaleTaxData t ON t.companyID = c.companyID WHERE t.year = "
						+ year
						+ " AND t.month = "
						+ month
						+ " AND c.economyEntityID = "
						+ SystemEnumeration.getInstance().getIdByKey(
								EconomyEntity.COMMERCIAL) + ") AS B");
				JOIN("(SELECT count(c.companyID) AS constructMonthCount FROM company c INNER JOIN companySaleTaxData t ON t.companyID = c.companyID WHERE t.year = "
						+ year
						+ " AND t.month = "
						+ month
						+ " AND c.economyEntityID = "
						+ SystemEnumeration.getInstance().getIdByKey(
								EconomyEntity.CONSTRUCTION) + ") AS C");
				JOIN("(SELECT count(c.companyID) AS serviceMonthCount FROM company c INNER JOIN companySaleTaxData t ON t.companyID = c.companyID WHERE t.year = "
						+ year
						+ " AND t.month = "
						+ month
						+ " AND c.economyEntityID = "
						+ SystemEnumeration.getInstance().getIdByKey(
								EconomyEntity.SERVICE) + ") AS D");
				JOIN("(SELECT count(c.companyID) AS houseHoldingMonthCount FROM company c INNER JOIN companySaleTaxData t ON t.companyID = c.companyID WHERE t.year = "
						+ year
						+ " AND t.month = "
						+ month
						+ " AND c.economyEntityID = "
						+ SystemEnumeration.getInstance().getIdByKey(
								EconomyEntity.HOUSEHOLDING) + ") AS E");
			}
		}.toString();
	}

	public static String searchSecurityInfo(Map<String, Object> params) {
		final SearchCriteria criteria = (SearchCriteria) params
				.get("searchCriteria");
		return new SQL() {
			{
				for (String field : criteria.getFields()) {
					SELECT(field);
				}
				FROM("securityInfo");
				int i = 0;
				for (SearchFilter filter : criteria.getFilters()) {
					if (i > 0
							&& filter.getFilterRestriction().equals(
									Restriction.OR)) {
						OR();
					}
					WHERE(filter.getClause());
					i++;
				}
			}
		}.toString()
				+ " LIMIT "
				+ criteria.getStartIndex()
				+ ","
				+ criteria.getFetch();
	}

	public static String searchSecurityInfoTotal(Map<String, Object> params) {
		final SearchCriteria criteria = (SearchCriteria) params
				.get("searchCriteria");
		return new SQL() {
			{
				SELECT("count(*) AS total");
				FROM("securityInfo");
				int i = 0;
				for (SearchFilter filter : criteria.getFilters()) {
					if (i > 0
							&& filter.getFilterRestriction().equals(
									Restriction.OR)) {
						OR();
					}
					WHERE(filter.getClause());
					i++;
				}
			}
		}.toString();
	}

	public static String searchAgricultureInfo(Map<String, Object> params) {
		final SearchCriteria criteria = (SearchCriteria) params
				.get("searchCriteria");
		return new SQL() {
			{
				for (String field : criteria.getFields()) {
					SELECT(field);
				}
				FROM("agricultureInfo");
				int i = 0;
				for (SearchFilter filter : criteria.getFilters()) {
					if (i > 0
							&& filter.getFilterRestriction().equals(
									Restriction.OR)) {
						OR();
					}
					WHERE(filter.getClause());
					i++;
				}
			}
		}.toString()
				+ " LIMIT "
				+ criteria.getStartIndex()
				+ ","
				+ criteria.getFetch();
	}

	public static String searchAgricultureInfoTotal(Map<String, Object> params) {
		final SearchCriteria criteria = (SearchCriteria) params
				.get("searchCriteria");
		return new SQL() {
			{
				SELECT("count(*) AS total");
				FROM("agricultureInfo");
				int i = 0;
				for (SearchFilter filter : criteria.getFilters()) {
					if (i > 0
							&& filter.getFilterRestriction().equals(
									Restriction.OR)) {
						OR();
					}
					WHERE(filter.getClause());
					i++;
				}
			}
		}.toString();
	}

	public static String searchCommercialArea(Map<String, Object> params) {
		final SearchCriteria criteria = (SearchCriteria) params
				.get("searchCriteria");
		return new SQL() {
			{
				for (String field : criteria.getFields()) {
					SELECT(field);
				}
				FROM("commercialArea");
				int i = 0;
				for (SearchFilter filter : criteria.getFilters()) {
					if (i > 0
							&& filter.getFilterRestriction().equals(
									Restriction.OR)) {
						OR();
					}
					WHERE(filter.getClause());
					i++;
				}
			}
		}.toString()
				+ " LIMIT "
				+ criteria.getStartIndex()
				+ ","
				+ criteria.getFetch();
	}

	public static String searchCommercialAreaTotal(Map<String, Object> params) {
		final SearchCriteria criteria = (SearchCriteria) params
				.get("searchCriteria");
		return new SQL() {
			{
				SELECT("count(*) AS total");
				FROM("commercialArea");
				int i = 0;
				for (SearchFilter filter : criteria.getFilters()) {
					if (i > 0
							&& filter.getFilterRestriction().equals(
									Restriction.OR)) {
						OR();
					}
					WHERE(filter.getClause());
					i++;
				}
			}
		}.toString();
	}

	public static String getCompanyContactInfo(Map<String, Object> params) {
		@SuppressWarnings("unchecked")
		final List<Long> companyIDs = (List<Long>) params.get("companyIDs");
		return new SQL() {
			{
				SELECT("cc.fullName");
				SELECT("cc.emailAddress");
				FROM("company c");
				INNER_JOIN("companyContact cc on cc.contactID = c.ownerContactID");
				if (companyIDs != null) {
					StringBuilder companyIDString = new StringBuilder(
							"c.companyID in (");
					for (int i = 0; i < companyIDs.size(); i++) {
						if (i != 0) {
							companyIDString.append(", ");
						}
						companyIDString.append(companyIDs.get(i));
					}
					companyIDString.append(")");
					WHERE(companyIDString.toString());
				}
				WHERE("cc.emailAddress IS NOT NULL");
			}
		}.toString();
	}
}
