package com.workintech.spring17challenge.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Data
public class Course {
    private String name;
    private int credit;
    private Grade grade;

}
