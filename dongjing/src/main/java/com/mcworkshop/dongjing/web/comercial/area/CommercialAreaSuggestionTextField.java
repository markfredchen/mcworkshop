// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.comercial.area;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import com.google.inject.Inject;
import com.mcworkshop.dongjing.domain.CommercialArea;
import com.mcworkshop.dongjing.service.DJService;

/**
 * @author $Author$
 * 
 */
public class CommercialAreaSuggestionTextField extends
		AutoCompleteTextField<CommercialArea> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJService service;

	private Map<String, CommercialArea> areaMap = new HashMap<String, CommercialArea>();

	public CommercialAreaSuggestionTextField(String id,
			IModel<CommercialArea> model) {
		super(id, model, CommercialArea.class,
				new CommercialAreaSuggestionRender(),
				new AutoCompleteSettings().setCssClassName("area-suggestion")
						.setPreselect(false).setThrottleDelay(350)
						.setAdjustInputWidth(false)
						.setUseSmartPositioning(false));
	}

	@Override
	protected Iterator<CommercialArea> getChoices(String input) {
		List<CommercialArea> areas = new ArrayList<CommercialArea>();
		areaMap.clear();
		if (input.length() == 0) {
			return areas.iterator();
		}
		areas = service.searchCommercialArea(input, null, 10, 0);
		if (areas.size() > 0) {
			for (CommercialArea area : areas) {
				areaMap.put(area.getName(), area);
			}
			return areas.iterator();
		}
		return new ArrayList<CommercialArea>().iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <C> IConverter<C> getConverter(Class<C> type) {
		return new IConverter() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object convertToObject(String value, Locale locale)
					throws ConversionException {
				if (areaMap.get(value) != null) {
					return areaMap.get(value);
				} else {
					CommercialArea area = new CommercialArea();
					area.setName(value);
					return area;
				}
			}

			@Override
			public String convertToString(Object value, Locale locale) {
				return ((CommercialArea) value).getName();
			}
		};
	}

}
