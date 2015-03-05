// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.comercial.area;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.IAutoCompleteRenderer;
import org.apache.wicket.request.Response;

import com.mcworkshop.dongjing.domain.CommercialArea;

/**
 * @author $Author$
 * 
 */
public class CommercialAreaSuggestionRender implements
		IAutoCompleteRenderer<CommercialArea> {

	private static final long serialVersionUID = 1L;

	@Override
	public void render(CommercialArea object, Response response, String criteria) {
		response.write("<li class=\"company-item\">");
		response.write(object.getName());
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
