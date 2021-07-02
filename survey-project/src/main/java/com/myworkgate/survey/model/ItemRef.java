package com.myworkgate.survey.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@Entity
public class ItemRef {

    @Setter @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
