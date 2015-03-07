package com.mcworkshop.dongjing.web.page.company.basic.panel;

import com.google.inject.Inject;
import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.domain.CompanyShareholder;
import com.mcworkshop.dongjing.persistence.DJServiceMapper;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;

/**
 * @author Markfred
 *
 */
public class CompanyShareholderFormPanel extends Panel {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJServiceMapper mapper;

	private Form<CompanyShareholder> addCSForm;
	private WebMarkupContainer csContainer;
	private WebMarkupContainer csEmptyContainer;
	private ListView<CompanyShareholder> csList;
	private boolean isCSUpdate = false;

	public CompanyShareholderFormPanel(String id, final IModel<Company> model) {
		super(id);

		CompoundPropertyModel<CompanyShareholder> csModel = new CompoundPropertyModel<CompanyShareholder>(
				new CompanyShareholder());
		addCSForm = new Form<CompanyShareholder>("add-cs-form", csModel);
		add(addCSForm);
		addCSForm.setOutputMarkupId(true);
		addCSForm.add(new TextField<String>("fullName"));
		addCSForm.add(new TextField<String>("shareRatio"));
		addCSForm.add(new TextField<String>("idType"));
		addCSForm.add(new TextField<String>("idCard"));
		addCSForm.add(new TextField<String>("address"));
		addCSForm.add(new TextField<String>("phone"));
		addCSForm.add(new AjaxSubmitLink("submit", addCSForm) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				CompanyShareholder ns = (CompanyShareholder) form
						.getModelObject();
				if (!isCSUpdate) {
					model.getObject().getCompanyShareholders().add(ns);
				}
				isCSUpdate = false;
				addCSForm
						.setModel(new CompoundPropertyModel<CompanyShareholder>(
								new CompanyShareholder()));
				target.add(addCSForm);
				target.add(csContainer);
			}

		});
		csContainer = new WebMarkupContainer("cs-list-container") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				if (model.getObject().getCompanyShareholders().isEmpty()) {
					csList.setVisible(false);
					csEmptyContainer.setVisible(true);
				} else {
					csList.setVisible(true);
					csEmptyContainer.setVisible(false);
				}
				super.onBeforeRender();
			}
		};
		csContainer.setOutputMarkupId(true);
		add(csContainer);
		csList = new ListView<CompanyShareholder>("cs-list",
				new LoadableDetachableModel<List<CompanyShareholder>>() {
					private static final long serialVersionUID = 1L;

					@Override
					protected List<CompanyShareholder> load() {
						return model.getObject().getCompanyShareholders();
					}

				}) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<CompanyShareholder> item) {
				final CompanyShareholder ns = item.getModelObject();
				item.add(new Label("fullName", ns.getFullName()));
				item.add(new Label("phone", ns.getPhone()));
				item.add(new Label("idCard", ns.getIdCard()));
				item.add(new Label("shareRatio", ns.getShareRatio()));
				item.add(new AjaxLink<Void>("update") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						addCSForm
								.setModel(new CompoundPropertyModel<CompanyShareholder>(
										ns));
						isCSUpdate = true;
						target.add(addCSForm);
					}
				});
				item.add(new AjaxLink<Void>("delete") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						model.getObject().getCompanyShareholders().remove(ns);
						if (ns.getContactID() != null) {
							mapper.deleteCompanyShareholder(ns.getContactID());
						}
						addCSForm
								.setModel(new CompoundPropertyModel<CompanyShareholder>(
										ns));
						target.add(addCSForm);
						target.add(csContainer);
					}
				});

			}
		};
		csContainer.add(csList);
		csEmptyContainer = new WebMarkupContainer("empty-cs-list");
		csEmptyContainer.setOutputMarkupId(true);
		csContainer.add(csEmptyContainer);
	}

}
