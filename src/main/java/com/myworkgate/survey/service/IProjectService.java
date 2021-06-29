package com.myworkgate.survey.service;

import com.myworkgate.survey.model.Project;

import java.util.List;

public interface IProjectService {
    Project createProject(Project project);
    List<Project> listAllProject();
}
