// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.company.security.panel;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import com.mcworkshop.common.web.component.wizard.WizardContentPanel;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.Security;

/**
 * @author $Author$
 * 
 */
public class SpecialEquipmentStep extends WizardContentPanel<Security> {

	private static final long serialVersionUID = 1L;

	public SpecialEquipmentStep(IModel<Security> model) {
		super(model);
		form = new Form<Security>("special-equipment-info");
		add(form);
		form.add(new TextField<Integer>("elevator"));
		form.add(new TextField<Integer>("crane"));
		form.add(new TextField<Integer>("recreationFacility"));
		form.add(new TextField<Integer>("indoorFacility"));
		form.add(new TextField<Integer>("boiler"));
		form.add(new TextField<Integer>("pressureContainer"));
		form.add(new TextField<Integer>("pressurePipe"));
		form.add(new TextField<Integer>("pipeLength"));
		form.add(new TextField<Integer>("specialOthers"));
	}

	@Override
	protected String getTabID() {
		return "special";
	}

	@Override
	protected String getTabContentID() {
		return "special-tab";
	}

	@Override
	protected ResourceModel getTabTitle() {
		return WicketMessageUtil
				.getResourceModel("security.form.tab.special.equipment");
	}

	@Override
	public void disablePanel() {
		form.setEnabled(false);
	}
}
