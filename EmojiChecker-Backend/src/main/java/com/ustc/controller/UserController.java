package com.ustc.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.ustc.domain.Message;
import com.ustc.domain.User;
import com.ustc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Map.Entry;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/auth/token/login")
    @ResponseBody
    public Map<String, String> login(String username, String password){
        User user = userService.login(username,password);
        Map<String,String> map=new HashMap<>();
        map.put("auth_token",username);
        return map;
    }
    @PostMapping ("/auth/users/create")
    @ResponseBody
    public String register(User user){
        int sucess = userService.register(user);
        return "{'module':'json param'}";
    }
    @RequestMapping("/auth/password/")
    @ResponseBody
    public String changePassWd(String current_password,String new_password,
                               @RequestHeader("Authorization")String Authorization){
        userService.changePassWord(Authorization.substring(6),current_password,new_password);
        return "{'module':'json param'}";
    }

    @ResponseBody
    @GetMapping ("/responses/")
    public Map<String,List<Message>> findPreviousEmoji(){
        List<Message> messages=userService.findPreviousEmoji();
        Map<String,List<Message>> map=new HashMap<>();
        map.put("results",messages);
        return map;
    }
    @RequestMapping("/auth/token/logout")
    @ResponseBody
    public String logout(@RequestHeader("Authorization")String Authorization){
        return "{'module':'json param'}";
    }
    //1 @RequestMapping("/responses/")
    @ResponseBody
    @PostMapping("/responses/")
    public String sendEmoji(String emoji,
                            @RequestHeader("Authorization")String Authorization){
        int type= userService.sendEmoji(emoji,Authorization.substring(6));
//        Map map = (Map)request.getParameterMap();
//        /*
//         * @description: entrySet()该方法返回值就是这个map中各个键值对映射关系的集合。
//         *通过iterator()方法对该map集合进行遍历
//         */
//        if(!map.isEmpty()){
//            Iterator<Entry<String, String[]>> entries = map.entrySet().iterator();
//            while (entries.hasNext()) {
//                Entry<String, String[]> entry = entries.next();
//                String key = entry.getKey();
//                String value =entry.getValue()[0];
//                int type= userService.sendEmoji(emoji,Authorization.substring(6));
//                System.out.println(key + ":" + value);
//            }
//        }
        return "{'module':'json param'}";
    }
}
