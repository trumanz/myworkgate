package com.myworkgate.survey;

import com.myworkgate.survey.model.Project;
import com.myworkgate.survey.repository.ProjectRepository;
import com.myworkgate.survey.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ProjectServiceTests {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    public void createProject_andGetProject() {
        Project project = new Project();
        project.setName("TestProj");

        when(projectRepository.save(ArgumentMatchers.any(Project.class))).thenReturn(project);

        Project proj_created = projectService.createOrUpdateProject(project);

        assertThat(proj_created.getName()).isSameAs(project.getName());

        verify(projectRepository).save(project);

    }





}