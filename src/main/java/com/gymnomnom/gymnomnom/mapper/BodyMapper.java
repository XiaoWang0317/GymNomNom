package com.gymnomnom.gymnomnom.mapper;

import com.gymnomnom.gymnomnom.pojo.Body;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;


@Mapper
public interface BodyMapper {
    /**
     * Put user's body into the database
     */
    @Insert("insert into body_table (id, date, height, weight, bmi, age, gender) values (#{id}, #{date}, #{height}, #{weight}, #{bmi}, #{age}, #{gender})")
    void inputBody(Body body);


    @Select("select height from body_table where id = #{id}")
    ArrayList<Double> getHeights(Integer id);

    @Select("select weight from body_table where id = #{id}")
    ArrayList<Double> getWeights(Integer id);


    @Select("select bmi from body_table where id = #{id}")
    ArrayList<Double> getBmi(Integer id);
}
