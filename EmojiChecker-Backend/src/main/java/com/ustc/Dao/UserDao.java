package com.ustc.Dao;

import com.ustc.domain.Message;
import com.ustc.domain.User;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface UserDao {
    @Results(
            @Result(property = "phoneNumber",column = "phone_number"))
    @Select("select * from api_user where username = #{username}")
    User selectByUsername(String username);

    @Update("update api_user set password=#{new_password} where userName=#{username}")
    int changePassWord(@Param("username") String username,@Param("new_password") String new_password);

    @Insert(("insert into api_user" +
            "(password,dateJoined,isSuperuser, userName, firstName, " +
            "lastName, email, phone_number, birthDate) VALUES " +
            "(#{password},#{dateJoined},0,#{username},#{firstName}," +
            "#{lastName},#{email},#{phoneNumber},#{birthDate})"))
    int register(User user);

    @Insert("insert into api_message(ts,emoji_id,user_id) values (#{ts},#{emoji_id},#{user_id})")
    int sendEmoji(Message message);

    @Select("select * from api_message order by ts ")
    List<Message> findPreviousEmoji();



}
