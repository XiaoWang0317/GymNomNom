package com.gymnomnom.gymnomnom.mapper;

import com.gymnomnom.gymnomnom.pojo.LoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    @Insert("insert into login_table (date, name, password, id) values (#{date}, #{name}, #{password}, #{id})")
    void insert(LoginLog log);
}
