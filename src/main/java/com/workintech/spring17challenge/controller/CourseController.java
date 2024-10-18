package com.workintech.spring17challenge.controller;


import com.workintech.spring17challenge.entity.*;
import com.workintech.spring17challenge.exceptions.ApiException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.annotation.HttpConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.util.*;

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


    @GetMapping
    public List getCourses(){
        return courses;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Course> getCourseByName(@PathVariable String name){
        String cleanedName = name.replaceAll("[\\p{Punct}\\s]+", "").toLowerCase();
        for(Course course : courses){
            if (course.getName().replaceAll("[\\p{Punct}\\s]+", "")
                    .toLowerCase().equals(cleanedName)) {
                return new ResponseEntity<>(course , HttpStatus.OK);
            } else{
                throw new ApiException("There is no course with this name " , HttpStatus.NOT_FOUND);
            }
        }


        return null;
    }


    @PostMapping
    public ResponseEntity<Map<Double , Course>> addCourse(@RequestBody Course course){
        if(course.getId() == null){
            throw new ApiException("Please enter valid id" , HttpStatus.BAD_REQUEST);
        }
        if(course.getCredit() == null){
            throw  new ApiException("Credit cannot be null" , HttpStatus.BAD_REQUEST);
        }
        if(course.getName() == null){
            throw new ApiException("Name cannot be null" , HttpStatus.BAD_REQUEST);

        }
        if(course.getGrade() == null){
            throw new ApiException("Grade cannot be null" , HttpStatus.BAD_REQUEST);
        }


        if(course.getCredit() < 0 || course.getCredit() > 4){
            throw new ApiException("The credit cannot be less then 0 and more than 4" , HttpStatus.BAD_REQUEST);
        }

        courses.add(course);

        double totalGpa=0;

        switch (course.getCredit()){
            case 1:
                totalGpa = course.getGrade().getCoefficient()*course.getCredit();
            case 2:
                totalGpa = course.getGrade().getCoefficient()*course.getCredit();
            case 3:
                totalGpa = course.getGrade().getCoefficient()*course.getCredit()* mediumCourse.getGpa();
            case 4:
                totalGpa = course.getGrade().getCoefficient()*course.getCredit()* highCourse.getGpa();
        }

        Map<Double , Course> response = new HashMap<>();
        response.put(totalGpa , course);

        return new ResponseEntity<>(response , HttpStatus.CREATED);

    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<Double , Course>> updateCourse(@PathVariable int id , @RequestBody Course course){
        if(id <= 0){
            throw new ApiException("Id cannot be less than 1" , HttpStatus.BAD_REQUEST);
        }

        if(course.getCredit() < 0 || course.getCredit() > 4){
            throw new ApiException("The credit cannot be less than 0 and more than 4" , HttpStatus.BAD_REQUEST);
        }

        for(Course object : courses){
            if(id == object.getId()){
                object.setCredit(course.getCredit());
                object.setName(course.getName());
                object.setGrade(course.getGrade());
            }
        }

        double totalGpa =0;

        switch (course.getCredit()){
            case 1:
                totalGpa = course.getGrade().getCoefficient()*course.getCredit();
            case 2:
                totalGpa = course.getGrade().getCoefficient()*course.getCredit();
            case 3:
                totalGpa = course.getGrade().getCoefficient()*course.getCredit()* mediumCourse.getGpa();
            case 4:
                totalGpa = course.getGrade().getCoefficient()*course.getCredit()* highCourse.getGpa();
        }

        Map<Double , Course> response = new HashMap<>();
        response.put(totalGpa , course);

        return new ResponseEntity<>(response , HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable int id){
        if(id <= 0){
            throw new ApiException("Id cannot be less than 1" , HttpStatus.BAD_REQUEST);
        }

      Iterator<Course> iterator = courses.iterator();

        while(iterator.hasNext()){
            Course course = iterator.next();

            if(course.getId() == id){
                iterator.remove();
                return  new ResponseEntity<>(course , HttpStatus.OK);
            }
        }

        throw new ApiException("There is no course with this id" , HttpStatus.NOT_FOUND);

    }









}
