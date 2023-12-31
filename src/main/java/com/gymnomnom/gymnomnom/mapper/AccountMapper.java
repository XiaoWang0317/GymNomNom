package com.gymnomnom.gymnomnom.mapper;

import com.gymnomnom.gymnomnom.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AccountMapper {
    /**
     * Create an account and add it to the user_table
     */
    @Insert("insert into user_table (name, password, fitness_type, age, gender) VALUE (#{name}, #{password}, #{fitness_type}, #{age}, #{gender})")
    void add(User user);

    /**
     * Delete the account base on the id and remove from the user_table
     */
    @Delete("delete from user_table where id = #{id}")
    void deleteById(Integer id);

    /**
     * Search user's name and password
     */
    @Select("select * from user_table where name = #{name} and password = #{password}")
    User getByNameAndPw(User user);

    /**
     * Get user's age by id
     */
    @Select("select age from user_table where id = #{id}")
    Integer getAgeById(Integer id);

    /**
     * Get user's gender by id
     */
    @Select("select gender from user_table where id = #{id}")
    Integer getGenderById(Integer id);

    @Select("select fitness_type from user_table where id = #{id}")
    Integer getFitnessTypeById(Integer id);
}

