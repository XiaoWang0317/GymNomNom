package com.gymnomnom.gymnomnom.mapper;

import com.gymnomnom.gymnomnom.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AccountMapper {

    @Insert("insert into user_table (name, password, fitness_type, age) VALUE (#{name}, #{password}, #{fitness_type}, #{age})")
    void add(User user);

}

