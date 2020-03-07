package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.PmsSkuInfo;

/**
 * SkuService
 *
 * @Author: 苏成瑜
 * @CreateTime: 2020-03-07
 * @Description:
 */
public interface SkuService {
    //保存sku商品属性
    void saveSkuInfo(PmsSkuInfo pmsSkuInfo);
}
