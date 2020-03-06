package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.PmsBaseCatalog1;
import com.atguigu.gmall.bean.PmsBaseCatalog2;
import com.atguigu.gmall.bean.PmsBaseCatalog3;

import java.util.List;

/**
 * CatalogService
 *
 * @Author: 苏成瑜
 * @CreateTime: 2020-03-05
 * @Description:
 */
public interface CatalogService {
    //获取商品的一级分类
    List<PmsBaseCatalog1> getCatalog1();
    //获取商品的二级分类
    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);
    //获取商品的三级分类
    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);
}
