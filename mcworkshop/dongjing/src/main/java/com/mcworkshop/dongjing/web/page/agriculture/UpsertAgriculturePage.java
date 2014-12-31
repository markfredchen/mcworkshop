// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.agriculture;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Inject;
import com.mcworkshop.common.web.util.WebUtil;
import com.mcworkshop.common.web.util.WicketMessageUtil;
import com.mcworkshop.dongjing.domain.AgricultureInfo;
import com.mcworkshop.dongjing.persistence.DJServiceMapper;
import com.mcworkshop.dongjing.web.layout.DJContentBasePage;

/**
 * @author $Author$
 * 
 */
public class UpsertAgriculturePage extends DJContentBasePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJServiceMapper mapper;

	public UpsertAgriculturePage(PageParameters params) {
		super(params);
		final Long agriID = WebUtil.getParameterAsLong("agriID");
		AgricultureInfo agriInfo = new AgricultureInfo();
		if (agriID != null) {
			agriInfo = this.mapper.getAgricultureInfo(agriID);
		}
		CompoundPropertyModel<AgricultureInfo> model = new CompoundPropertyModel<AgricultureInfo>(
				agriInfo);
		final Form<AgricultureInfo> agriForm = new Form<AgricultureInfo>(
				"upsert-agri-form", model);
		add(agriForm);
		agriForm.add(new TextField<String>("farmerName"));
		agriForm.add(new TextField<String>("contactNumber"));
		agriForm.add(new TextField<Integer>("area"));
		agriForm.add(new TextField<String>("address"));
		agriForm.add(new TextField<Integer>("paddyArea"));
		agriForm.add(new TextField<Integer>("wheatArea"));
		agriForm.add(new TextField<Integer>("greenArea"));
		agriForm.add(new TextField<Integer>("assartArea"));
		agriForm.add(new SubmitLink("submit-link", agriForm) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				AgricultureInfo agri = (AgricultureInfo) agriForm
						.getModelObject();
				if (agri.getAgriID() == null) {
					mapper.createAgriculture(agri);
				} else {
					mapper.updateAgriculture(agri);
				}
				setResponsePage(AgricultureListPage.class);
			}

		});
		agriForm.add(new BookmarkablePageLink<Void>("cancel-link",
				AgricultureListPage.class));
	}

	@Override
	protected Panel getLeftNaviPanel(String id) {
		return new AgricultureLeftNaviPanel(id);
	}

	@Override
	protected String getPageClassName() {
		return "argiculture";
	}

	@Override
	protected ResourceModel getPageTitle() {
		return WicketMessageUtil.getResourceModel("agriculture.page.title");
	}

}
