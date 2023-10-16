package com.gymnomnom.gymnomnom.service.impl;

import com.gymnomnom.gymnomnom.mapper.AccountMapper;
import com.gymnomnom.gymnomnom.pojo.User;
import com.gymnomnom.gymnomnom.pojo.generalException;
import com.gymnomnom.gymnomnom.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    /**
     * Add a user to the database
     * @param user (all info)
     */
    @Override
    public void add(User user) {
        int age = user.getAge();
        if (age <= 0 || age >= 120) {
            throw new generalException("Unreasonable age number");
        }
        accountMapper.add(user);
    }

    @Override
    public void delete(Integer id) {
        accountMapper.deleteById(id);
    }

    /**
     * Log in by searching the user's name and password
     * @param user (name and password)
     * @return the user in the database
     */
    @Override
    public User login(User user) {
        return accountMapper.getByNameAndPw(user);
    }

    @Override
    public Integer getAgeById(Integer id) {
        return accountMapper.getAgeById(id);
    }
}
