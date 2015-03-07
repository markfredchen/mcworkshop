// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.persistence;

import com.mcworkshop.common.search.Restriction;
import com.mcworkshop.common.search.SearchCriteria;
import com.mcworkshop.common.search.SearchFilter;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author $Author$
 * 
 */
public class ProjectSearchProvider {

	public static String searchProjects(Map<String, Object> params) {
		final SearchCriteria criteria = (SearchCriteria) params
				.get("searchCriteria");
		String sql = new SQL() {
			{
				for (String field : criteria.getFields()) {
					SELECT(field);
				}
				FROM("project");
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
		return sql;
	}

	public static String searchProjectTotal(Map<String, Object> params) {
		final SearchCriteria criteria = (SearchCriteria) params
				.get("searchCriteria");
		return new SQL() {
			{
				SELECT("count(*) AS total");
				FROM("project");
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
}
