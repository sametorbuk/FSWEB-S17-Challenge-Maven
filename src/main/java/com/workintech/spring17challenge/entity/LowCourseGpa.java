package com.workintech.spring17challenge.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Data
public class LowCourseGpa implements CourseGpa{

    @Override
    public int getGpa() {
        return 3;
    }
}
