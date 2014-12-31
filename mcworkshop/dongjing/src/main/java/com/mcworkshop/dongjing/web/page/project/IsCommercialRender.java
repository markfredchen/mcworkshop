// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.project;

import org.apache.wicket.markup.html.form.IChoiceRenderer;

import com.mcworkshop.common.web.util.WicketMessageUtil;

/**
 * @author $Author$
 * 
 */
public class IsCommercialRender implements IChoiceRenderer<Boolean> {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getDisplayValue(Boolean object) {
		return WicketMessageUtil.getResourceString("ProjectIsCommercial."
				+ object.toString().toUpperCase());
	}

	@Override
	public String getIdValue(Boolean object, int index) {
		return index + "" + object.toString().toUpperCase();
	}

}
