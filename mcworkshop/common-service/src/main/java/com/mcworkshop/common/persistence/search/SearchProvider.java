// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.persistence.search;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.mcworkshop.common.search.Restriction;
import com.mcworkshop.common.search.SearchCriteria;
import com.mcworkshop.common.search.SearchFilter;

/**
 * @author $Author$
 * 
 */
public class SearchProvider {

    public static String searchUser(Map<String, Object> params) {
        final SearchCriteria criteria = (SearchCriteria) params
                .get("searchCriteria");
        return new SQL() {
            {
                for (String field : criteria.getFields()) {
                    SELECT(field);
                }
                FROM("users u");
                INNER_JOIN("contacts c ON u.contactID = c.contactID");
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
}
