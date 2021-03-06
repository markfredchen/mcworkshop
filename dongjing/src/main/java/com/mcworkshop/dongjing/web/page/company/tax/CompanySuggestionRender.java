// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.tax;

import com.mcworkshop.dongjing.domain.Company;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.IAutoCompleteRenderer;
import org.apache.wicket.request.Response;

/**
 * @author $Author$
 * 
 */
public class CompanySuggestionRender implements IAutoCompleteRenderer<Company> {

	private static final long serialVersionUID = 1L;

	@Override
	public void render(Company c, Response response, String criteria) {
		response.write("<li class=\"company-item\">");
		response.write(c.getName());
		response.write("</li>");
	}

	@Override
	public void renderHeader(Response response) {
		response.write("<ul>");

	}

	@Override
	public void renderFooter(Response response, int count) {
		response.write("</ul>");

	}

}
