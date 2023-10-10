package com.gymnomnom.gymnomnom.mapper;

import com.gymnomnom.gymnomnom.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

@Mapper
public interface AccountMapper {
    /**
     * Create a account and add it to the user_table
     * @param user
     */
    @Insert("insert into user_table (name, password, fitness_type, age) VALUE (#{name}, #{password}, #{fitness_type}, #{age})")
    void add(User user);

    /**
     * Delete the account base on the id and remove from the user_table
     * @param id
     */
    @Delete("delete from user_table where id = #{id}")
    void deleteById(Integer id);

    /**
     * Search user's name and password
     * @param user
     * @return
     */
    @Select("select * from user_table where name = #{name} and password = #{password}")
    User getByNameAndPw(User user);
}

