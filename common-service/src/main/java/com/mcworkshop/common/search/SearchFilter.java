// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.search;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author $Author$
 * 
 */
public class SearchFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    private Restriction       filterRestriction;
    private Restriction       clauseRestriction;
    private String            searchField;
    private Class             valueClass       = String.class;
    private String            value;
    private List<String>      values;

    public SearchFilter() {
    }

    public SearchFilter(Restriction filterRestriction,
            Restriction clauseRestriction, String searchField, String value) {
        this(filterRestriction, clauseRestriction, searchField, value, String.class);
    }
    
    public SearchFilter(Restriction filterRestriction,
            Restriction clauseRestriction, String searchField, String value,
            Class valueClass) {
        this.filterRestriction = filterRestriction;
        this.clauseRestriction = clauseRestriction;
        this.searchField = searchField;
        this.value = value;
        this.valueClass = valueClass;
    }

    public Restriction getFilterRestriction() {
        return filterRestriction;
    }

    public void setFilterRestriction(Restriction filterRestriction) {
        this.filterRestriction = filterRestriction;
    }

    public Restriction getClauseRestriction() {
        return clauseRestriction;
    }

    public void setClauseRestriction(Restriction clauseRestriction) {
        this.clauseRestriction = clauseRestriction;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public Class getValueClass() {
        return valueClass;
    }

    public void setValueClass(Class valueClass) {
        this.valueClass = valueClass;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public String getClause() {
        StringBuilder s = new StringBuilder();
        s.append(searchField);
        if (clauseRestriction.equals(Restriction.EQUAL)) {
            s.append(" = ");
            if (valueClass.equals(String.class)
                    || valueClass.equals(Date.class)) {
                s.append("'");
                s.append(value);
                s.append("'");
            } else {
                s.append(value);
            }

        } else if (clauseRestriction.equals(Restriction.START_WITH)
                && valueClass.equals(String.class)) {
            s.append(" LIKE ");
            s.append("'");
            s.append(value);
            s.append("%'");
        } else if (clauseRestriction.equals(Restriction.CONTAIN)
                && valueClass.equals(String.class)) {
            s.append(" LIKE ");
            s.append("'%");
            s.append(value);
            s.append("%'");
        } else if (clauseRestriction.equals(Restriction.IN)) {
            // Implement later
        }

        return s.toString();
    }
}
