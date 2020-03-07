package com.atguigu.gmall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.manager.mapper.*;
import com.atguigu.gmall.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * skuServiceImpl
 *
 * @Author: 苏成瑜
 * @CreateTime: 2020-03-07
 * @Description:
 * @Service:使用的是阿里云的注解com.alibaba.dubbo.config.annotation.Service;
 */
@Service
public class skuServiceImpl implements SkuService {

    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;
    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;
    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;
    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;

    /**
     * 保存sku商品属性
     * @param pmsSkuInfo
     */
    @Override
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        //保存sku
        pmsSkuInfoMapper.insertSelective(pmsSkuInfo);
        //获取skuId主键
        String skuInfoId = pmsSkuInfo.getId();
        //保存sku图片
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : skuImageList) {
            pmsSkuImage.setSkuId(skuInfoId);
            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }
        //保存平台属性中间表
        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
            pmsSkuAttrValue.setSkuId(skuInfoId);
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }
        //保存平台销售属性中间值表
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(skuInfoId);
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }

    }
}
