package com.atguigu.gmall.manager.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.PmsBaseAttrInfo;
import com.atguigu.gmall.bean.PmsBaseAttrValue;
import com.atguigu.gmall.service.AttrService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AttrController
 *平台属性相关
 * @Author: 苏成瑜
 * @CreateTime: 2020-03-05
 * @Description:
 * @CrossOrigin:解决跨域问题
 */
@RestController
@CrossOrigin
public class AttrController {

    //远程注入,需要使用@Reference,导入的包为 alibaba.dubbo
    @Reference
    private AttrService attrService;
    /**
     * 查询平台属性
     * @param catalog3Id 三级分类id
     * @return
     */
    @RequestMapping("/attrInfoList")
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id){
        List<PmsBaseAttrInfo> attrInfoList = attrService.attrInfoList(catalog3Id);
        return attrInfoList;
    }

    /**
     * 保存平台属性值
     * @param pmsBaseAttrInfo 前端传递过来的数据
     * @return 不需要返回数据,返回提示信息,成功或失败
     */
    @RequestMapping("/saveAttrInfo")
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){
        // 保存平台属性的业务方法
        attrService.saveAttrInfo(pmsBaseAttrInfo);
        //返回提示信息
        return "success";
    }
}
