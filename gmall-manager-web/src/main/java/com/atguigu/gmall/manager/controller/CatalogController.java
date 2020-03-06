package com.atguigu.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.PmsBaseCatalog1;
import com.atguigu.gmall.bean.PmsBaseCatalog2;
import com.atguigu.gmall.bean.PmsBaseCatalog3;
import com.atguigu.gmall.service.CatalogService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * CatalogController
 *
 * @Author: 苏成瑜
 * @CreateTime: 2020-03-05
 * @Description:
 * @CrossOrigin:解决跨域问题
 */
@RestController
@CrossOrigin
public class CatalogController {

    //远程注入,需要使用@Reference,导入的包为 alibaba.dubbo
    @Reference
    private CatalogService catalogService;

    /**
     * 获取商品的一级分类数据
     * @return
     */
    @RequestMapping(value = "/getCatalog1")
    public List<PmsBaseCatalog1> getCatalog1(){
        List<PmsBaseCatalog1> catalog1List = catalogService.getCatalog1();
        return catalog1List;
    }

    /**
     * 查询获取商品的所有二级分类数据
     * @param catalog1Id 一级分类商品的id
     * @return
     */
    @RequestMapping("/getCatalog2")
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id){
         List<PmsBaseCatalog2> catalog2List = catalogService.getCatalog2(catalog1Id);
         return catalog2List;
    }


    /**
     * 询获取商品的所有三级分类数据
     * @param catalog2Id 二级分类商品的id
     * @return
     */
    @RequestMapping("/getCatalog3")
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        List<PmsBaseCatalog3> catalog3List = catalogService.getCatalog3(catalog2Id);
        return catalog3List;
    }
}
