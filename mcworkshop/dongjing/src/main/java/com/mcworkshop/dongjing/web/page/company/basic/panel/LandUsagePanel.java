package com.mcworkshop.dongjing.web.page.company.basic.panel;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

import com.google.inject.Inject;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerOption;
import com.mcworkshop.common.web.component.form.datetimepicker.DatetimePickerPlugin;
import com.mcworkshop.common.web.component.form.datetimepicker.View;
import com.mcworkshop.dongjing.domain.Company;
import com.mcworkshop.dongjing.domain.RentStatus;
import com.mcworkshop.dongjing.persistence.DJServiceMapper;
import com.mcworkshop.dongjing.web.page.company.tax.CompanySuggestionTextFeild;

/**
 * @author Markfred
 *
 */
public class LandUsagePanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DJServiceMapper mapper;
	
	private Form<RentStatus> addNSForm;
	private WebMarkupContainer nsContainer;
	private WebMarkupContainer nsEmptyContainer;
	private ListView<RentStatus> nsList;
	private boolean isNSUpdate = false;
	
	private static final String DATE_PATTERN = "MM-dd-yyyy";
	private DatetimePickerOption datePickerOption = new DatetimePickerOption();

	public LandUsagePanel(String id, final CompoundPropertyModel<Company> model) {
		super(id);
		this.setOutputMarkupId(true);
		RadioGroup<Boolean> isRent = new RadioGroup<Boolean>("isRent");
		isRent.add(new Radio<Boolean>("isRent", new Model<Boolean>(Boolean.TRUE)));
		isRent.add(new Radio<Boolean>("notRent", new Model<Boolean>(Boolean.FALSE)));
		add(isRent);
		final WebMarkupContainer notRentContainer = new WebMarkupContainer("notRentContainer");
		notRentContainer.setOutputMarkupId(true);
		notRentContainer.add(new TextField<Double>("factoryActualArea"));
		notRentContainer.add(new TextField<Double>("factoryArea"));
		notRentContainer.add(new TextField<Double>("factoryUsageArea"));
		notRentContainer.add(new TextField<Double>("factoryRentArea"));
		notRentContainer.add(new TextField<Double>("factoryControlArea"));
		final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd"); 
		
		final ListView<RentStatus> renteeList = new ListView<RentStatus>("rentee-list", new LoadableDetachableModel<List<RentStatus>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<RentStatus> load() {
				return model.getObject().getRentees();
			}
		}) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<RentStatus> item) {
				RentStatus rs = item.getModelObject();
				item.add(new Label("name", rs.getRentee().getName()));
				item.add(new Label("area", rs.getArea()));
				item.add(new Label("address", rs.getAddress()));
				if(rs.getStartDate() != null && rs.getEndDate() != null){
					item.add(new Label("startDate", f.format(rs.getStartDate()) + " 至 " + f.format(rs.getEndDate())));
				}else{
					item.add(new Label("startDate", ""));
				}
			}
		};
		final WebMarkupContainer emptyList = new WebMarkupContainer("empty-rentee-list");
		emptyList.setOutputMarkupId(true);
		WebMarkupContainer renteeListContainer = new WebMarkupContainer("rentee-list-container"){
			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				if(model.getObject().getRentees().isEmpty()){
					renteeList.setVisible(false);
					emptyList.setVisible(true);
				}else{
					renteeList.setVisible(true);
					emptyList.setVisible(false);
				}
				super.onBeforeRender();
			}
		};
		renteeListContainer.setOutputMarkupId(true);
		notRentContainer.add(renteeListContainer);
		renteeListContainer.add(renteeList);
		renteeListContainer.add(emptyList);
		
		final WebMarkupContainer isRentContainer = new WebMarkupContainer("isRentContainer");
		isRentContainer.setOutputMarkupId(true);
		datePickerOption.setMinView(View.MONTH);
		datePickerOption.setMaxView(View.DECADE);
		datePickerOption.setLanguage("zh-CN");
		CompoundPropertyModel<RentStatus> rsModel = new CompoundPropertyModel<RentStatus>(new RentStatus());
		addNSForm = new Form<RentStatus>("add-rentor-form", rsModel);
		isRentContainer.add(addNSForm);
		final CompanySuggestionTextFeild companyNameTextField = new CompanySuggestionTextFeild("rentor", new Model<Company>(), true);
		addNSForm.add(companyNameTextField);
		addNSForm.add(new TextField<Double>("area"));
		addNSForm.add(new TextField<String>("address"));
		addNSForm.add(new DateTextField("startDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		addNSForm.add(new DateTextField("endDate", DATE_PATTERN)
				.add(new DatetimePickerPlugin(datePickerOption)));
		addNSForm.add(new AjaxSubmitLink("submit", addNSForm) {

			private static final long serialVersionUID = 1L;
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				RentStatus ns = (RentStatus) form.getModelObject();
				Company rentor = companyNameTextField.getConvertedInput();
				if(rentor == null || rentor.getCompanyID() == null){
					String nonDJCompany = companyNameTextField.getInput();
					ns.setNonDJCompany(nonDJCompany);
					if(!isNSUpdate){
						model.getObject().getRentors().add(ns);
					}
					isNSUpdate = false;
					companyNameTextField.setModel(new Model<Company>());
					addNSForm.setModel(new CompoundPropertyModel<RentStatus>(new RentStatus()));
					target.add(addNSForm);
					target.add(nsContainer);
				}else{
					ns.setRentor(companyNameTextField.getConvertedInput());
					if(!isNSUpdate){
						model.getObject().getRentors().add(ns);
					}
					isNSUpdate = false;
					companyNameTextField.setModel(new Model<Company>());
					addNSForm.setModel(new CompoundPropertyModel<RentStatus>(new RentStatus()));
					target.add(addNSForm);
					target.add(nsContainer);
				}
			}
			
		});
		nsContainer = new WebMarkupContainer("rentor-list-container"){
			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				if (model.getObject().getRentors().isEmpty()) {
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
		isRentContainer.add(nsContainer);
		nsList = new ListView<RentStatus>("rentor-list", new LoadableDetachableModel<List<RentStatus>>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected List<RentStatus> load() {
				return model.getObject().getRentors();
			}
			
		}) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<RentStatus> item) {
				final RentStatus ns = item.getModelObject();
				if(ns.getRentor() != null){
					item.add(new Label("rentor", ns.getRentor().getName()));
				}else{
					item.add(new Label("rentor", ns.getNonDJCompany()));
				}
				item.add(new Label("area", ns.getArea()));
				if(ns.getStartDate() != null && ns.getEndDate() != null){
					item.add(new Label("startDate", f.format(ns.getStartDate()) + " 至 " + f.format(ns.getEndDate())));
				}else{
					item.add(new Label("startDate", ""));
				}
				item.add(new AjaxLink<Void>("update") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						companyNameTextField.setDefaultModel(new Model<Company>(ns.getRentor()));
						addNSForm
								.setModel(new CompoundPropertyModel<RentStatus>(
										ns));
						isNSUpdate = true;
						target.add(addNSForm);
					}
				});
				item.add(new AjaxLink<Void>("delete") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						model.getObject().getRentors().remove(ns);
						if (model.getObject().getCompanyID() != null) {
							mapper.deleteRentStatus(ns);
						}
						companyNameTextField.setModel(new Model<Company>());
						addNSForm.setModel(new CompoundPropertyModel<RentStatus>(new RentStatus()));
						target.add(addNSForm);
						target.add(nsContainer);
					}
				});
				
			}
		};
		nsContainer.add(nsList);
		nsEmptyContainer = new WebMarkupContainer("empty-rentor-list");
		nsEmptyContainer.setOutputMarkupId(true);
		nsContainer.add(nsEmptyContainer);
		add(notRentContainer);
		add(isRentContainer);
		boolean isRentLand = model.getObject().getIsRent() != null && Boolean.valueOf(model.getObject().getIsRent());
		notRentContainer.setVisible(!isRentLand);
		isRentContainer.setVisible(isRentLand);
		
		if(model.getObject().getIsRent() == null){
			notRentContainer.setVisible(false);
			isRentContainer.setVisible(false);
		}
		
		if(model.getObject().getRentees() != null && model.getObject().getRentees().size() > 0){
			model.getObject().setIsRent(false);
			notRentContainer.setVisible(true);
			isRentContainer.setVisible(false);
		}
		
		isRent.add(new AjaxFormChoiceComponentUpdatingBehavior() {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				Boolean isRentLand = (Boolean) getComponent().getDefaultModelObject();
				notRentContainer.setVisible(!isRentLand);
				isRentContainer.setVisible(isRentLand);
				target.add(LandUsagePanel.this);
			}
		});
	}
	
}
