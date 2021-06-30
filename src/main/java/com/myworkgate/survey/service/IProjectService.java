package com.myworkgate.survey.service;

import com.myworkgate.survey.model.Project;

import java.util.List;

public interface IProjectService {
    Project createOrUpdateProject(Project project);
    Project getProject(Long id);
    List<Project> listAllProject();

}
