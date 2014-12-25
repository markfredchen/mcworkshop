// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.search;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author $Author$
 *
 */
public class SearchQueryUtil {

    public static String getSearchQuery(final SearchCriteria criteria) {
        return new SQL() {{
            for(String field: criteria.getFields()) {
                SELECT(field);
            }
            for(int i = 0; i < criteria.getTables().size(); i++) {
                if(i == 0) {
                    FROM(criteria.getTables().get(i));
                }else {
                    JOIN(criteria.getTables().get(i));
                }
            }
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
        }}.toString() 
                + " LIMIT "
                + criteria.getStartIndex()
                + ","
                + criteria.getFetch();
    }
    
    public static String getSearchTotalQuery(final SearchCriteria criteria) {
        return new SQL() {{
            SELECT("count(*) AS total");
            for(int i = 0; i < criteria.getTables().size(); i++) {
                if(i == 0) {
                    FROM(criteria.getTables().get(i));
                }else {
                    JOIN(criteria.getTables().get(i));
                }
            }
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
        }}.toString();
    }
    
    
}
