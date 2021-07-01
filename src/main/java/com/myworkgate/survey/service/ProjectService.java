package com.myworkgate.survey.service;

import com.myworkgate.survey.model.Project;
import com.myworkgate.survey.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//https://www.javatpoint.com/jpa-collection-mapping

@Service
public class ProjectService implements  IProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project createOrUpdateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getProject(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if(project.isEmpty()) return null;
        return project.get();
    }

    @Override
    public List<Project> listAllProject() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        return projects;
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }


}
