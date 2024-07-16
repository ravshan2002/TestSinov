package com.company.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "test")
public class TestEntity {
    private Integer id;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;

}
