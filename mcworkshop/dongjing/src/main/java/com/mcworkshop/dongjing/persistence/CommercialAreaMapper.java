// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id: CommercialAreaMapper.java 143 2014-08-02 17:12:12Z mchen $
package com.mcworkshop.dongjing.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.mcworkshop.common.search.SearchCriteria;
import com.mcworkshop.dongjing.domain.CommercialArea;

/**
 * @author $Author: mchen $
 * @since 1.0
 */
public interface CommercialAreaMapper {

	@Insert("INSERT INTO commercialArea(commercialAreaID, name, typeID, scope, residentialArea, residentNumbers, storePopulation, ownerEduProvinceAge, start, end, length, totalArea, ownArea, rentArea, spareArea, constructDescription, retailNumber, retailMainBrands, caterNumber, caterMainBrands, entertainNumber, entertainMainBrands, serviceNumber, serviceMainBrands, farmNumber, farmMainBrands, otherNumber, otherMainBrands, annualIncoming, annualTax, comment, totalCommercialEquipment)"
			+ " VALUES(#{commercialAreaID}, #{name}, #{typeID}, #{scope}, #{residentialArea}, #{residentNumbers}, #{storePopulation}, #{ownerEduProvinceAge}, #{start}, #{end}, #{length}, #{totalArea}, #{ownArea}, #{rentArea}, #{spareArea}, #{constructDescription}, #{retailNumber}, #{retailMainBrands}, #{caterNumber}, #{caterMainBrands}, #{entertainNumber}, #{entertainMainBrands}, #{serviceNumber}, #{serviceMainBrands}, #{farmNumber}, #{farmMainBrands}, #{otherNumber}, #{otherMainBrands}, #{annualIncoming}, #{annualTax}, #{comment}, #{totalCommercialEquipment})")
	void createCommercialArea(CommercialArea area);

	@Update("UPDATE commercialArea SET name = #{name}, typeID = #{typeID}, scope = #{scope}, residentialArea = #{residentialArea}, residentNumbers = #{residentNumbers}, storePopulation = #{storePopulation}, ownerEduProvinceAge = #{ownerEduProvinceAge}, start = #{start}, end = #{end}, length = #{length}, totalArea = #{totalArea}, ownArea = #{ownArea}, rentArea = #{rentArea}, spareArea = #{spareArea}, constructDescription = #{constructDescription}, retailNumber = #{retailNumber}, retailMainBrands = #{retailMainBrands}, caterNumber = #{caterNumber}, caterMainBrands = #{caterMainBrands}, entertainNumber = #{entertainNumber}, entertainMainBrands = #{entertainMainBrands}, serviceNumber = #{serviceNumber}, serviceMainBrands = #{serviceMainBrands}, farmNumber = #{farmNumber}, farmMainBrands = #{farmMainBrands}, otherNumber = #{otherNumber}, otherMainBrands = #{otherMainBrands}, annualIncoming = #{annualIncoming}, annualTax = #{annualTax}, comment = #{comment}, totalCommercialEquipment = #{totalCommercialEquipment}"
			+ " WHERE commercialAreaID = #{commercialAreaID}")
	void updateCommercialArea(CommercialArea area);

	@Select("SELECT * FROM commercialArea WHERE commercialAreaID = #{commercialAreaID}")
	CommercialArea getCommercialArea(
			@Param("commercialAreaID") Long commercialAreaID);

	@SelectProvider(type = CompanySearchProvider.class, method = "searchCommercialArea")
	List<Map<String, Object>> searchCommercialArea(
			@Param("searchCriteria") SearchCriteria criteria);

	@SelectProvider(type = CompanySearchProvider.class, method = "searchCommercialAreaTotal")
	int getCommercialAreaTotalCount(
			@Param("searchCriteria") SearchCriteria criteria);
}
