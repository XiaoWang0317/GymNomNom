package com.gymnomnom.gymnomnom.service;


import com.gymnomnom.gymnomnom.pojo.User;

public interface AccountService {
    void add(User user);

    void delete(Integer id);

    User login(User user);

    Integer getAgeById(Integer id);
}
