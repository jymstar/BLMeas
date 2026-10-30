package com.example.blmeas.mapper;

import com.example.blmeas.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserMapper {

    @Select("SELECT * FROM sys_user WHERE username = #{username}")
    SysUser findByUsername(String username);

    @Select("INSERT INTO sys_user(username, password, real_name, email, phone, status) " +
            "VALUES(#{username}, #{password}, #{realName}, #{email}, #{phone}, 0)")
    int insertUser(SysUser user);
}