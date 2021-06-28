package com.myworkgate.survey;


import com.myworkgate.survey.controller.ProjectController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes =  SurveyApplication.class)
public class ProjectAPITests {

    MockMvc mockMvc;
    @InjectMocks
    private ProjectController projectController;
    @BeforeEach
    public void preTest(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(projectController)
                .build();
    }
/*
    @Test
    public void getProjectsTest()  throws  Exception{
        String expected_list_projects = "[{\"id\":1,\"name\":\"dummy\"}]";

            mockMvc.perform(get("/projects"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(expected_list_projects));
    }
    */
    @Test
    public void curdProjectTest()  throws  Exception{
        String expected_list_projects = "{\"id\":1,\"name\":\"dummy\"}";

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expected_list_projects) )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expected_list_projects));
    }

}
