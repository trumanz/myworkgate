package com.myworkgate.survey;

import com.myworkgate.survey.model.Project;
import com.myworkgate.survey.repository.ProjectRepository;
import com.myworkgate.survey.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTests {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    public void createProject_shouldReturnProject() {
        Project project = new Project();
        project.setName("TestProj");

        when(projectRepository.save(ArgumentMatchers.any(Project.class))).thenReturn(project);

        Project proj_created = projectService.createProject(project);

        assertThat(proj_created.getName()).isSameAs(project.getName());

        verify(projectRepository).save(project);

    }





}