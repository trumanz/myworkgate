package com.myworkgate.survey.service;

import com.myworkgate.survey.model.Project;

import java.util.List;

public interface IProjectService {
    Project createProject(Project project);

    Project createOrUpdateProject(Project project);
    Project getProject(Long id);
    List<Project> listAllProject();
    void deleteProject(Long id);


}
