package com.mcworkshop.dongjing.web.page.company.basic.panel;

import com.google.inject.Inject;
import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.domain.NatureShareholder;
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

public class NatureShareholderFormPanel extends Panel {

	private static final long serialVersionUID = 1L;

	@Inject
	private DJServiceMapper mapper;

	private Form<NatureShareholder> addNSForm;
	private WebMarkupContainer nsContainer;
	private WebMarkupContainer nsEmptyContainer;
	private ListView<NatureShareholder> nsList;
	private boolean isNSUpdate = false;

	public NatureShareholderFormPanel(String id, final IModel<Company> model) {
		super(id);
		CompoundPropertyModel<NatureShareholder> nsModel = new CompoundPropertyModel<NatureShareholder>(
				new NatureShareholder());
		addNSForm = new Form<NatureShareholder>("add-ns-form", nsModel);
		add(addNSForm);
		addNSForm.setOutputMarkupId(true);
		addNSForm.add(new TextField<String>("fullName"));
		addNSForm.add(new TextField<String>("idCard"));
		addNSForm.add(new TextField<String>("address"));
		addNSForm.add(new TextField<String>("phone"));
		addNSForm.add(new TextField<String>("cellPhone"));
		addNSForm.add(new TextField<String>("emailAddress"));
		addNSForm.add(new TextField<String>("shareRatio"));
		addNSForm.add(new AjaxSubmitLink("submit", addNSForm) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				NatureShareholder ns = (NatureShareholder) form
						.getModelObject();
				if (!isNSUpdate) {
					model.getObject().getNatureShareholders().add(ns);
				}
				isNSUpdate = false;
				addNSForm
						.setModel(new CompoundPropertyModel<NatureShareholder>(
								new NatureShareholder()));
				target.add(addNSForm);
				target.add(nsContainer);
			}

		});
		nsContainer = new WebMarkupContainer("ns-list-container") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				if (model.getObject().getNatureShareholders().isEmpty()) {
					nsList.setVisible(false);
					nsEmptyContainer.setVisible(true);
				} else {
					nsList.setVisible(true);
					nsEmptyContainer.setVisible(false);
				}
				super.onBeforeRender();
			}
		};
		nsContainer.setOutputMarkupId(true);
		add(nsContainer);
		nsList = new ListView<NatureShareholder>("ns-list",
				new LoadableDetachableModel<List<NatureShareholder>>() {
					private static final long serialVersionUID = 1L;

					@Override
					protected List<NatureShareholder> load() {
						return model.getObject().getNatureShareholders();
					}

				}) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<NatureShareholder> item) {
				final NatureShareholder ns = item.getModelObject();
				item.add(new Label("fullName", ns.getFullName()));
				item.add(new Label("cellPhone", ns.getCellPhone()));
				item.add(new Label("emailAddress", ns.getEmailAddress()));
				item.add(new Label("shareRatio", ns.getShareRatio()));
				item.add(new AjaxLink<Void>("update") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						addNSForm
								.setModel(new CompoundPropertyModel<NatureShareholder>(
										ns));
						isNSUpdate = true;
						target.add(addNSForm);
					}
				});
				item.add(new AjaxLink<Void>("delete") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						model.getObject().getNatureShareholders().remove(ns);
						if (ns.getContactID() != null) {
							mapper.deleteNatureShareholder(ns.getContactID());
						}
						addNSForm
								.setModel(new CompoundPropertyModel<NatureShareholder>(
										ns));
						target.add(addNSForm);
						target.add(nsContainer);
					}
				});

			}
		};
		nsContainer.add(nsList);
		nsEmptyContainer = new WebMarkupContainer("empty-ns-list");
		nsEmptyContainer.setOutputMarkupId(true);
		nsContainer.add(nsEmptyContainer);
	}

}
