package com.myworkgate.survey.entity;


//import javax.persistence.Entity;
//import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

//@Entity
public class Project {

    @Getter  @Setter
    private Long id;

    @Getter @Setter
    private String name;




}
