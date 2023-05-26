package com.ustc.service.impl;

import com.ustc.Dao.UserDao;
import com.ustc.domain.Message;
import com.ustc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements com.ustc.service.UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User login(String username,String password) {
        User user=userDao.selectByUsername(username);
        if(user.getPassword().equals(password)){
            return user;
        }else{

        }
        return null;
    }

    @Override
    public int changePassWord(String username,String current_password,String new_password) {
        User user=userDao.selectByUsername(username);
        if(user.getPassword().equals(current_password)){
            userDao.changePassWord(username,new_password);
        }else {
            int i=1/0;
        }
        return 0;
    }

    @Override
    public int register(User user) {
        userDao.register(user);
        return 0;
    }

    @Override
    public int sendEmoji(String emoji_id, String username) {
       // System.out.println(emoji_id);
        User user=userDao.selectByUsername(username);
        Date date=new Date();
        SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ts=temp.format(date);
        Message message=new Message(ts,emoji_id,user.getId());
        return userDao.sendEmoji(message);
    }

    @Override
    public List<Message> findPreviousEmoji() {
        return userDao.findPreviousEmoji();
    }
}
