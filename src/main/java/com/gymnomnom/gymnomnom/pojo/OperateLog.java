package com.gymnomnom.gymnomnom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperateLog {
    private int id;
    private LocalDateTime date;
    private String class_name;
    private String method_name;
    private String parameters;
    private String return_value;
    private double time;

}
