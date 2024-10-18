package com.workintech.spring17challenge.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Data
@NoArgsConstructor
public class Grade {
    private int coefficient;
    private  String note;
}
