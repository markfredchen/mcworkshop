// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.web.component.wizard;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import com.mcworkshop.common.domain.DomainObject;
import com.mcworkshop.common.web.util.ClassModifier;

/**
 * @author $Author$
 * 
 */
public class Wizard<T extends DomainObject> extends Panel {

    private static final long           serialVersionUID = 1L;

    private WizardContentPanel<T>       currentStep;

    private List<WizardContentPanel<T>> steps            = new ArrayList<WizardContentPanel<T>>();

    private AjaxSubmitLink              previousLink;
    private AjaxSubmitLink              nextLink;
    private AjaxSubmitLink              submitLink;
    
    private WebMarkupContainer buttonGroup;
    private WebMarkupContainer tabContainer;
    private WebMarkupContainer headerContainer;
    

    private Form<T>                     wizardForm;

    public Wizard(String id, T t, final List<WizardContentPanel<T>> steps) {
        super(id);
        this.steps = steps;
        for (int i = 0; i < steps.size(); i++) {
            steps.get(i).setSequence(i);
        }
        if (currentStep == null) {
            currentStep = steps.get(0);
        }
        this.setDefaultModel(new CompoundPropertyModel<T>(t));
        this.setOutputMarkupId(true);
        wizardForm = new Form<T>("wizard-form", new Model<T>(t));
        add(wizardForm);
        headerContainer = new WebMarkupContainer("header-container");
        headerContainer.setOutputMarkupId(true);
        wizardForm.add(headerContainer);
        headerContainer.add(new ListView<WizardContentPanel<T>>("tab-header-item",
                this.steps) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<WizardContentPanel<T>> item) {
                final WizardContentPanel<T> step = item.getModelObject();
                AjaxSubmitLink stepLink;
                if (currentStep.equals(step)) {
                    item.add(AttributeModifier.append("class", "active"));
                }
                item.add(stepLink = new AjaxSubmitLink("tab-title-link") {

                    private static final long serialVersionUID = 1L;

					@Override
					public void onSubmit(AjaxRequestTarget target, Form<?> form) {
						currentStep = step;
						target.appendJavaScript("$('#" + step.getTabID() + "').tab('show');");
                        target.appendJavaScript("$(window).scrollTop(0)");
					}

					@Override
		            protected void onError(AjaxRequestTarget target, Form<?> form) {
		                target.add(currentStep);
		            }
                    
                });
                stepLink.add(new AttributeAppender("data-toggle", ""));
                stepLink.add(new AttributeAppender("role", "tab"));
                stepLink.add(new AttributeAppender("href", "#" + step.getTabContentID()){

					@Override
					protected String newValue(String currentValue,
							String appendValue) {
						return appendValue;
					}
                	
                });
                stepLink.setMarkupId(step.getTabID());
                stepLink.add(new Label("tab-title", step.getTabTitle()));
            }
        });
        tabContainer = new WebMarkupContainer("tab-content"){

			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				addOrReplace(currentStep);
				super.onBeforeRender();
			}
        	
        };
        tabContainer.setOutputMarkupId(true);
        
        wizardForm
                .add(tabContainer = new ListView<WizardContentPanel<T>>("tab-content", steps) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void populateItem(
                            ListItem<WizardContentPanel<T>> item) {
                        WizardContentPanel<T> panel = item.getModelObject();
                        if (panel.equals(currentStep)) {
                            item.add(ClassModifier.addClass("active in"));
                        } else {
                            item.add(ClassModifier.removeClass("active in"));
                        }
                        item.setMarkupId(panel.getTabContentID());
                        item.add(panel);
                    }
                });
        tabContainer.setOutputMarkupId(true);
        wizardForm.add(tabContainer);
        buttonGroup = new WebMarkupContainer("buttonGroup");
        add(buttonGroup);
        buttonGroup.setOutputMarkupId(true);
        buttonGroup.add(previousLink = new AjaxSubmitLink("previous-step-link",
                currentStep.getForm()) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit(AjaxRequestTarget target, Form<?> form) {
                int currentSequence = currentStep.getSequence();
                if (currentSequence != 0 && !currentStep.getForm().hasError()) {
                    WizardContentPanel<T> previousStep = steps
                            .get(currentSequence - 1);
                    currentStep = previousStep;
                    target.appendJavaScript("$(window).scrollTop(0)");
                    target.appendJavaScript("$('#" + previousStep.getTabID() + "').tab('show');");
                }
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(currentStep);
            }

        });

        buttonGroup.add(nextLink = new AjaxSubmitLink("next-step-link",
                currentStep.getForm()) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit(AjaxRequestTarget target, Form<?> form) {
                int currentSequence = currentStep.getSequence();

                if (currentSequence + 1 != steps.size()
                        && !currentStep.getForm().hasError()) {
                    WizardContentPanel<T> nextStep = steps
                            .get(currentSequence + 1);
                    currentStep = nextStep;
                    
                    target.appendJavaScript("$(window).scrollTop(0);");
                    target.appendJavaScript("$('#" + nextStep.getTabID() + "').tab('show');");
                }
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
            	target.appendJavaScript("$(window).scrollTop(0);");
                target.add(currentStep);
            }

        });

        buttonGroup.add(submitLink = new AjaxSubmitLink("submit-link", wizardForm) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                Wizard.this.onSubmit(target, form);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                List<WizardContentPanel<T>> errorSteps = new ArrayList<WizardContentPanel<T>>();
                for (WizardContentPanel<T> step : steps) {
                    if (step.hasErrorMessage()) {
                        errorSteps.add(step);
                    }
                }

                if (errorSteps.size() > 0) {
                    currentStep = errorSteps.get(0);
                }
                target.add(Wizard.this);
            }

        });
        
    }

    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

    };

    public void addWizardStep(WizardContentPanel<T> step) {
        step.setSequence(steps.size());
        this.steps.add(step);
        step.setDefaultModel(this.getDefaultModel());
    }
    
    public List<WizardContentPanel<T>> getSteps(){
    	return steps;
    }
    
    public void hideButtonGroup(){
    	this.buttonGroup.setVisible(false);
    }
    
    public void setCurrentWizardStep(WizardContentPanel<T> step){
    	this.currentStep = step;
    }

}
