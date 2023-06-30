package com.music.dao;

import com.music.entity.user;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
public interface UserMapper{
    @Select("select * from admin where username=#{username} and password=#{password}")
    user getUser(@Param("username") String username, @Param("password") String password);


    @Insert("insert into admin(username,password) values(#{username},#{password})")
    user Insertuser(@Param("username") String username, @Param("password") String password);

}
