package com.gymnomnom.gymnomnom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginLog {
    private LocalDateTime date;
    private String name;
    private String password;
    private int id;
}
