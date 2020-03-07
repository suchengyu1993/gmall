package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.PmsBaseSaleAttr;
import com.atguigu.gmall.bean.PmsProductImage;
import com.atguigu.gmall.bean.PmsProductInfo;
import com.atguigu.gmall.bean.PmsProductSaleAttr;

import java.util.List;

/**
 * SpuService
 *
 * @Author: 苏成瑜
 * @CreateTime: 2020-03-06
 * @Description:
 */
public interface SpuService {

    //查询销售属性图片
    List<PmsProductImage> spuImageList(String spuImageId);

    //查询商品销售属性表信息
    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    //查询全部商品列表信息
    List<PmsProductInfo> spuList(String catalog3Id);

    //保存商品的spu信息
    void saveSpuInfo(PmsProductInfo pmsProductInfo);

    //查询销售属性
    List<PmsBaseSaleAttr> baseSaleAttrList();


}
