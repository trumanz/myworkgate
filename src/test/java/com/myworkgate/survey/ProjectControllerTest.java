package com.myworkgate.survey;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.myworkgate.survey.controller.ProjectController;
import com.myworkgate.survey.model.Project;
import com.myworkgate.survey.service.IProjectService;
import com.myworkgate.survey.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @MockBean
    IProjectService projectService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createProject() throws Exception {
        Project project = new Project();
        project.setId(1L);
        project.setName("testProj");

        when(projectService.createProject(any(Project.class))).thenReturn(project);

        mockMvc.perform(post("/projects")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content( mapper.writeValueAsString(project) ) )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(project.getName()));


    }


}
