package com.ustc.service;

import com.ustc.domain.Message;
import com.ustc.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    User login(String username,String password);

    int changePassWord(String username,String current_password,String new_password);

    int register(User user);

    int sendEmoji(String emoji_id, String username);

    List<Message> findPreviousEmoji();
}
