package com.atguigu.gmall.manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @Author: 苏成瑜
 * @CreateTime: 2020-03-04
 * @Description:
 */
@RestController
public class TestController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
