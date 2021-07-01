package com.myworkgate.survey.model.survey;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
public class SurveyItemText extends SurveyItemBase {
    @Getter @Setter
    String optionalDetails = "";

    @Getter @Setter
    String answer = "";
}
