package com.atguigu.gmall.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.UmsMember;
import com.atguigu.gmall.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

   @Reference
    private UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/getAllUser")
    public List<UmsMember> getAllUser(){
        List<UmsMember> umsMemebers = userService.getAllUser();
        return umsMemebers;
    }

    @RequestMapping("/getAllUser2")
    public List<UmsMember> getAllUser2(){
        List<UmsMember> umsMemebers2 = userService.getAllUser2();
        return umsMemebers2;
    }
}
