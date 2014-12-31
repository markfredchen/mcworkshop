// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.persistence;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.mcworkshop.dongjing.domain.TaxData;

/**
 * @author $Author$
 * 
 */
public interface TotalTaxDataMapper {

	@Select("SELECT year, month, economyNatureID, SUM(vat) AS vat, SUM(operateTax) AS operateTax, SUM(domesticIncomingTax) AS domesticIncomingTax, SUM(foreignIncomingTax) AS foreignIncomingTax, SUM(stampTax) AS stampTax, SUM(landUseTax) AS landUseTax, SUM(personalIncomingTax) AS personalIncomingTax, SUM(constructionTax) AS constructionTax "
			+ "FROM companySaleTaxData "
			+ "WHERE year = #{year} AND month = #{month}")
	public TaxData getMonthTaxDataByEconmyNature(@Param("year") Integer year,
			@Param("month") Integer month);

	@Select("SELECT t.* FROM companySaleTaxData t INNER JOIN company c ON c.companyID = t.companyID"
			+ " WHERE t.year = #{year} AND t.month = #{month} AND c.regionID = #{regionID}")
	@Results(value = { @Result(column = "companyID", property = "company", one = @One(select = "com.mcworkshop.dongjing.persistence.DJServiceMapper.getCompanyForReport")) })
	public List<TaxData> getTaxListByAffiliateBlock(@Param("year") int year,
			@Param("month") int month, @Param("regionID") Long regionID);
}
