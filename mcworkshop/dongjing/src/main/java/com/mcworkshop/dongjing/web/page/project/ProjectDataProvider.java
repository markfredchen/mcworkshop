// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.dongjing.web.page.project;

import java.util.Iterator;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.mcworkshop.dongjing.domain.Project;
import com.mcworkshop.dongjing.domain.ProjectStatus;
import com.mcworkshop.dongjing.service.DJService;

/**
 * @author $Author$
 * 
 */
public class ProjectDataProvider implements IDataProvider<Project> {

	private static final long serialVersionUID = 1L;
	private String projectName;
	private ProjectStatus status;

	private DJService service;

	public ProjectDataProvider(DJService service) {
		this.service = service;
	}

	public ProjectDataProvider(DJService service, String projectName,
			ProjectStatus status) {
		this.service = service;
		this.projectName = projectName;
		this.status = status;
	}

	@Override
	public void detach() {

	}

	@Override
	public Iterator<? extends Project> iterator(long first, long count) {
		return service.searchProjects(projectName, status, count, first)
				.iterator();
	}

	@Override
	public long size() {
		return service.getProjectTotalCount(projectName, status);
	}

	@Override
	public IModel<Project> model(Project object) {
		return new CompoundPropertyModel<Project>(object);
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

}
