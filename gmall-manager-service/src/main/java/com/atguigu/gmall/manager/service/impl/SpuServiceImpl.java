package com.atguigu.gmall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.manager.mapper.*;
import com.atguigu.gmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * SpuServiceImpl
 *
 * @Author: 苏成瑜
 * @CreateTime: 2020-03-06
 * @Description:
 * @Service:使用的是阿里云的注解com.alibaba.dubbo.config.annotation.Service;
 */
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;
    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;
    @Autowired
    PmsProductImageMapper pmsProductImageMapper;
    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;
    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    /**
     * 查询销售属性图片
     * @param spuImageId
     * @return
     */
    @Override
    public List<PmsProductImage> spuImageList(String spuImageId) {
        PmsProductImage pmsProductImage = new PmsProductImage();
        pmsProductImage.setProductId(spuImageId);
        List<PmsProductImage> pmsProductImages = pmsProductImageMapper.select(pmsProductImage);
        return pmsProductImages;
    }


    /**
     * 查询商品销售属性表信息
     * @param spuId
     * @return
     */
    @Override
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {
        //查询销售属性集合
        PmsProductSaleAttr pmsProductSaleAttr = new PmsProductSaleAttr();
        pmsProductSaleAttr.setProductId(spuId);
        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrMapper.select(pmsProductSaleAttr);
        //将销售属性值的集合放入销售属性
        for (PmsProductSaleAttr productSaleAttr : pmsProductSaleAttrs) {
            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setSaleAttrId(productSaleAttr.getSaleAttrId());//商品销售属性id
            pmsProductSaleAttrValue.setProductId(spuId);//商品的id
            //查询商品销售属性值
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = pmsProductSaleAttrValueMapper.select(pmsProductSaleAttrValue);
            //设置商品销售属性表中的商品销售属性值
            productSaleAttr.setSpuSaleAttrValueList(pmsProductSaleAttrValues);
        }
        return pmsProductSaleAttrs;
    }

    /**
     * 查询全部商品列表信息
     * @return
     */
    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        //设置商品信息表的三级分类id
        pmsProductInfo.setCatalog3Id(catalog3Id);
        //查询全部三级分类下的商品信息
        List<PmsProductInfo> pmsProductInfoList = pmsProductInfoMapper.select(pmsProductInfo);
        return pmsProductInfoList;
    }

    /**
     * 保存添加的销售属性信息
     */
    @Override
    public void saveSpuInfo(PmsProductInfo pmsProductInfo) {
        //保存productInfo表,商品信息表
        pmsProductInfoMapper.insertSelective(pmsProductInfo);
        //返回product主键
        String pmsProductInfoId = pmsProductInfo.getId();
        //保存图片表
        //获取图片的信息集合
        List<PmsProductImage> spuImageList = pmsProductInfo.getSpuImageList();
        //通过for循环添加
        for (PmsProductImage pmsProductImage : spuImageList) {
            //设置图片里的信息表id
            pmsProductImage.setProductId(pmsProductInfoId);
            //保存图片信息到数据库
            pmsProductImageMapper.insertSelective(pmsProductImage);
        }
        //保存销售属性表
        List<PmsProductSaleAttr> spuSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
        for (PmsProductSaleAttr pmsProductSaleAttr : spuSaleAttrList) {
            pmsProductSaleAttr.setProductId(pmsProductInfoId); //设置商品属性id
            pmsProductSaleAttrMapper.insertSelective(pmsProductSaleAttr);//保存销售属性表
            //保存销售属性值表
            List<PmsProductSaleAttrValue> spuSaleAttrValueList = pmsProductSaleAttr.getSpuSaleAttrValueList();
            for (PmsProductSaleAttrValue pmsProductSaleAttrValue : spuSaleAttrValueList) {
                pmsProductSaleAttrValue.setProductId(pmsProductInfoId);
                //此处应该是平台销售属性的主键，而不是spu销售属性表中的主键
                pmsProductSaleAttrValue.setSaleAttrId(pmsProductSaleAttr.getSaleAttrId());
                pmsProductSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);
            }
        }
    }
    /**
     * 查询销售属性表
     * @return
     */
    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        List<PmsBaseSaleAttr> pmsBaseSaleAttrs = pmsBaseSaleAttrMapper.selectAll();
        return pmsBaseSaleAttrs;
    }

}
