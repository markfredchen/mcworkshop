// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author $Author$
 * 
 */
public class SearchCriteria implements Serializable {

    private static final long  serialVersionUID = 1L;

    private long               startIndex;
    private long               fetch;
    private List<String>       fields           = new ArrayList<String>();
    private List<String>       tables           = new ArrayList<String>();
    private List<SearchFilter> filters          = new ArrayList<SearchFilter>();

    public long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(long startIndex) {
        this.startIndex = startIndex;
    }

    public long getFetch() {
        return fetch;
    }

    public void setFetch(long fetch) {
        this.fetch = fetch;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public void addField(String field) {
        this.fields.add(field);
    }

    public List<SearchFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<SearchFilter> filters) {
        this.filters = filters;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }
    
    public void addTable(String table) {
        this.tables.add(table);
    }

}
