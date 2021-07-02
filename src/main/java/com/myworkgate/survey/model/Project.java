package com.myworkgate.survey.model;

import javax.persistence.*;


import com.myworkgate.survey.model.survey.SurveyItemText;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Entity
public class Project {

    @Getter  @Setter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private String name;

    @ManyToMany
    List<SurveyItemText> surveyItemTextList;



}
