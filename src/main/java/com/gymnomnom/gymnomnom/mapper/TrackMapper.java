package com.gymnomnom.gymnomnom.mapper;

import com.gymnomnom.gymnomnom.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrackMapper {
    @Insert("insert into track_table (id, date, class_name, method_name, parameters, return_value, time) " +
            "values (#{id}, #{date}, #{class_name}, #{method_name}, #{parameters}, #{return_value}, #{time})")
    void insert(OperateLog log);
}
