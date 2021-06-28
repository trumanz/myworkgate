package com.myworkgate.survey.service;

import com.myworkgate.survey.model.Project;
import com.myworkgate.survey.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements  IProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }


}
