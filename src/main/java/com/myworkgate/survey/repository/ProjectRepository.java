package com.myworkgate.survey.repository;

import com.myworkgate.survey.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findById(long id);

}
