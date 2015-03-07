// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.tax;

import com.google.inject.Inject;
import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.service.DJService;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.util.*;

/**
 * @author $Author$
 * 
 */
public class CompanySuggestionTextFeild extends AutoCompleteTextField<Company> {

	private static final long serialVersionUID = 1L;
	@Inject
	private DJService service;

	private Map<String, Company> companyMap = new HashMap<String, Company>();
	
	boolean allCompany;

	public CompanySuggestionTextFeild(String id, IModel<Company> model, boolean allCompany) {
		super(id, model, Company.class, new CompanySuggestionRender(),
				new AutoCompleteSettings()
						.setCssClassName("company-suggestion")
						.setPreselect(false).setThrottleDelay(350)
						.setAdjustInputWidth(false)
						.setUseSmartPositioning(false));
		this.allCompany = allCompany;
		setMarkupId(id);
		setOutputMarkupId(true);
	}

	@Override
	protected Iterator<Company> getChoices(String input) {
		List<Company> companies = new ArrayList<Company>();
		companyMap.clear();
		if (input.length() == 0) {
			return companies.iterator();
		}
		companies = this.service.searchCompany(input, null, null, null, allCompany, 10, 0);
		if (companies.size() > 0) {
			for (Company company : companies) {
				companyMap.put(company.getName(), company);
			}
			return companies.iterator();
		} else {
			return new ArrayList<Company>().iterator();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <C> IConverter<C> getConverter(Class<C> type) {
		return new IConverter() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object convertToObject(String value, Locale locale)
					throws ConversionException {
				if (companyMap.get(value) != null) {
					return companyMap.get(value);
				} else {
					Company company = new Company();
					company.setName(value);
					return company;
				}
			}

			@Override
			public String convertToString(Object value, Locale locale) {
				return ((Company) value).getName();
			}

		};
	}
}
