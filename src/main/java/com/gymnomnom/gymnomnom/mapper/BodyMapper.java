package com.gymnomnom.gymnomnom.mapper;

import com.gymnomnom.gymnomnom.pojo.Body;
import org.apache.ibatis.annotations.*;


@Mapper
public interface BodyMapper {
    /**
     * Put user's body into the database
     */
    @Insert("insert into body_table (id, date, height, weight, bmi, age) values (#{id}, #{date}, #{height}, #{weight}, #{bmi}, #{age})")
    void inputBody(Body body);
}
