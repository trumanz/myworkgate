package com.myworkgate.survey.model.survey;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//https://www.w3schools.com/html/html_form_input_types.asp
//https://www.baeldung.com/hibernate-inheritance

@MappedSuperclass
public class SurveyItemBase {
    @Getter
    @Setter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private String subject;
}
