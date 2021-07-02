package com.myworkgate.survey.repository;

import com.myworkgate.survey.model.Project;
import com.myworkgate.survey.model.survey.SurveyItemText;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyItemTextRepository extends CrudRepository<SurveyItemText, Long> {
}
