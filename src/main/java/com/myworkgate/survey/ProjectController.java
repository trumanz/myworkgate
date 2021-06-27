package com.myworkgate.survey;


import com.myworkgate.survey.entity.Project;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;



@RestController
public class ProjectController {

    private Project dummyProject() {
        Project proj = new Project();
        proj.setId(1L);
        proj.setName("dummy");
        return proj;
    }

    @GetMapping("/projects")
    List<Project> all(){
        List<Project> projects = new LinkedList<Project>();
        projects.add( dummyProject());
        return projects;
    }

    @GetMapping("/project/{id}")
    Project one(@PathVariable Long id) {
        return dummyProject();
    }

    @PostMapping("/projects")
    Project newProject(@RequestBody Project newProject) {
        return dummyProject();
    }



}
