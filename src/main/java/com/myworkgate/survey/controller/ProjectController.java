package com.myworkgate.survey.controller;


import com.myworkgate.survey.model.Project;
import com.myworkgate.survey.repository.ProjectRepository;
import com.myworkgate.survey.service.IProjectService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


@Slf4j
@RestController
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    private Project dummyProject() {
        Project proj = new Project();
        proj.setId(1L);
        proj.setName("dummy");
        return proj;
    }

    @GetMapping("/projects")
    List<Project>  all(){
        return projectService.listAllProject();
    }

    @GetMapping("/project/{id}")
    Project getProject(@PathVariable Long id) {
        return dummyProject();
    }

    @PostMapping("/projects")
    public ResponseEntity<Project> newProject(@RequestBody Project newProject) {
        try {
            log.warn("" + projectService);
            Project proj =projectService.createProject(newProject);
            return new ResponseEntity<>(proj, HttpStatus.CREATED);
        } catch(Exception e) {
            log.error("" + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
