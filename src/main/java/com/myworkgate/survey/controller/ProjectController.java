package com.myworkgate.survey.controller;


import com.myworkgate.survey.model.Project;
import com.myworkgate.survey.service.IProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/projects/{id}")
    ResponseEntity<Project> getProject(@PathVariable Long id) {
        Project project = projectService.getProject(id);
        if(project == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping("/projects")
    public ResponseEntity<Project> newProject(@RequestBody Project newProject) {
        try {
            log.warn("" + projectService);
            Project proj =projectService.createOrUpdateProject(newProject);
            return new ResponseEntity<>(proj, HttpStatus.CREATED);
        } catch(Exception e) {
            log.error("" + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id,  @RequestBody Project project)
    {
        Project orignal_proj = projectService.getProject(id);
        if(orignal_proj == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(project, orignal_proj);
        projectService.createOrUpdateProject(orignal_proj);
        return new ResponseEntity<>(orignal_proj, HttpStatus.CREATED);

    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Long> deleteProject(@PathVariable Long id)
    {
        projectService.getProject(id);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }


}
