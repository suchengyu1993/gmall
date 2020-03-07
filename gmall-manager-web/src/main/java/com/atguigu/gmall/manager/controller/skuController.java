package com.atguigu.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.PmsProductImage;
import com.atguigu.gmall.bean.PmsProductSaleAttr;
import com.atguigu.gmall.bean.PmsSkuInfo;
import com.atguigu.gmall.service.SkuService;
import com.atguigu.gmall.service.SpuService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * skuController
 *
 * @Author: 苏成瑜
 * @CreateTime: 2020-03-07
 * @Description:
 * @CrossOrigin:解决跨域问题
 */
@RestController
@CrossOrigin
public class skuController {

    //远程注入,需要使用@Reference,导入的包为 alibaba.dubbo
    @Reference
    private SpuService spuService;
    @Reference
    private SkuService skuService;

    /**
     * 保存sku商品属性
     * @param pmsSkuInfo
     * @return
     */
    @RequestMapping("/saveSkuInfo")
    public String saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo){
        skuService.saveSkuInfo(pmsSkuInfo);
        return "success";
    }

    /**
     * 查询商品图片
     * @param spuId
     * @return
     */
    @RequestMapping("/spuImageList")
    public List<PmsProductImage> spuImageList(String spuId){
        List<PmsProductImage> pmsProductImages = spuService.spuImageList(spuId);
        return pmsProductImages;
    }

    /**
     * 查询商品销售属性表信息
     * @param spuId
     * @return
     */
    @RequestMapping("/spuSaleAttrList")
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId){
        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrList(spuId);
        return pmsProductSaleAttrs;
    }

}
