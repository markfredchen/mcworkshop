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
public interface RegionTaxMapper {

	@Select("SELECT year, month, economyNatureID, SUM(vat)/10000 AS vat, SUM(operateTax)/10000 AS operateTax, SUM(domesticIncomingTax)/10000 AS domesticIncomingTax, SUM(foreignIncomingTax)/10000 AS foreignIncomingTax, SUM(stampTax)/10000 AS stampTax, SUM(landUseTax)/10000 AS landUseTax, SUM(personalIncomingTax)/10000 AS personalIncomingTax, SUM(constructionTax)/10000 AS constructionTax "
			+ "FROM companySaleTaxData "
			+ "WHERE economyNatureID = #{economyNatureID} AND year = #{year} AND month <= #{month}")
	public TaxData getMonthTaxDataByEconmyNature(
			@Param("economyNatureID") Long economyNatureID,
			@Param("year") Integer year, @Param("month") Integer month);
}
