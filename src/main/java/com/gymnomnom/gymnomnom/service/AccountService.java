package com.gymnomnom.gymnomnom.service;


import com.gymnomnom.gymnomnom.pojo.User;
import org.springframework.stereotype.Service;

public interface AccountService {
    int add(User user);

    void delete(Integer id);
}
