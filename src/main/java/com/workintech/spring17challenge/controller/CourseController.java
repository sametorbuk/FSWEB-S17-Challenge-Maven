package com.workintech.spring17challenge.controller;


import com.workintech.spring17challenge.entity.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private List<Course> courses;
    LowCourseGpa lowCourse;
    HighCourseGpa highCourse;
    MediumCourseGpa mediumCourse;



    @PostConstruct
    public void init(){
        courses = new ArrayList<>();
    }



    @Autowired
    public CourseController(LowCourseGpa lowCourse , MediumCourseGpa mediumCourse , HighCourseGpa highCourse){
        this.lowCourse=lowCourse;
        this.highCourse=highCourse;
        this.mediumCourse=mediumCourse;

    }












}
