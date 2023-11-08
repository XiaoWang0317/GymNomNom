package com.gymnomnom.gymnomnom.mapper;

import com.gymnomnom.gymnomnom.pojo.Diet;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NutritionMapper {
    /**
     * Input nurition data to database
     * @param diet object
     */
    @Insert("insert into diet_table (id, date, age, fat, vc, va, calories, protein, carbs, gender) values " +
            "(#{id}, #{date}, #{age}, #{fat}, #{vc}, #{va}, #{calories}, #{protein}, #{carbs}, #{gender})")
    void inputDiet(Diet diet);

    @Select("select * from diet_table where id = #{id}")
    List<Diet> getNutHistory(Integer id);

    @Delete("delete from diet_table where id = #{id}")
    void deleteAccount(Integer id);
}
