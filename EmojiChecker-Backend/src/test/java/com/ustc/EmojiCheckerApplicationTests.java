package com.ustc;

import com.ustc.Dao.UserDao;
import com.ustc.controller.UserController;
import com.ustc.domain.Message;
import com.ustc.domain.User;
import com.ustc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

@SpringBootTest
class EmojiCheckerApplicationTests {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;
    @Autowired
    private UserController userController;
    @Test
    void dao() {
        User user=userDao.selectByUsername("xian");
        System.out.println(user);
        //sendMessage
        /*
        Date date=new Date();
        SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String ts=temp.format(date);
        Message message=new Message(ts,"Angry",1);
        int type=userDao.sendEmoji(message);
        System.out.println(type);
         */
        //previous
//        List<Message> messages=userDao.findPreviousEmoji();
//        System.out.println(messages);
        //login




    }
    @Test
    void service(){

    }
    @Test
    void controller(){

    }

}
