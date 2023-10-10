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
    @Override
    public int add(User user) {
        int age = user.getAge();
        if (age <= 0 || age >= 120) {
            throw new generalException("Unreasonable age number");
        }
        accountMapper.add(user);
        return 1;
    }

    @Override
    public void delete(Integer id) {
        accountMapper.deleteById(id);
    }

    /**
     * Log in by searching the user's name and password
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return accountMapper.getByNameAndPw(user);
    }
}
