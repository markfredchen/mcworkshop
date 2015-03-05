// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component.pagination;

import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;

/**
 * @author $Author$
 * 
 */
public class PaginationNavigator extends PagingNavigator {

    private static final long serialVersionUID = 1L;

    public PaginationNavigator(String id, IPageable pageable) {
        super(id, pageable);
    }

}
