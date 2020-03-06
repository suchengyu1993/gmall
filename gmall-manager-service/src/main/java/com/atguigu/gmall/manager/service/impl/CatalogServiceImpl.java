package com.atguigu.gmall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.PmsBaseCatalog2;
import com.atguigu.gmall.bean.PmsBaseCatalog3;
import com.atguigu.gmall.manager.mapper.PmsBaseCatalog1Mapper;
import com.atguigu.gmall.bean.PmsBaseCatalog1;
import com.atguigu.gmall.manager.mapper.PmsBaseCatalog2Mapper;
import com.atguigu.gmall.manager.mapper.PmsBaseCatalog3Mapper;
import com.atguigu.gmall.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * CatalogServiceImpl
 *
 * @Author: 苏成瑜
 * @CreateTime: 2020-03-05
 * @Description:
 * @Service:使用的是阿里云的注解com.alibaba.dubbo.config.annotation.Service;
 */
@Service
public class CatalogServiceImpl implements CatalogService {
    //在内部调用,不是两个服务器的调用不需要远程调用
    @Autowired
    private PmsBaseCatalog1Mapper pmsBaseCatalog1Mapper;
    @Autowired
    private PmsBaseCatalog2Mapper pmsBaseCatalog2Mapper;
    @Autowired
    private PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;


    /**
     * 获取全部一级分类商品数据
     * @return
     */
    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        //使用通用mapper,查询所有信息
        return pmsBaseCatalog1Mapper.selectAll();
    }

    /**
     * 获取商品的二级分类数据
     * @param catalog1Id
     * @return
     */
    @Override
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        //创建PmsBaseCatalog2的对象
        PmsBaseCatalog2 pmsBaseCatalog2 = new PmsBaseCatalog2();
        //设置对象的Catalog1Id的id
        pmsBaseCatalog2.setCatalog1Id(catalog1Id);
        //使用通用mapper查询数据,把对象传递过去
        return pmsBaseCatalog2Mapper.select(pmsBaseCatalog2);
    }

    /**
     * 获取商品的三级分类数据
     * @param catalog2Id
     * @return
     */
    @Override
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        //创建PmsBaseCatalog3的对象
        PmsBaseCatalog3 pmsBaseCatalog3 = new PmsBaseCatalog3();
        //设置对象的Catalog2Id的id
        pmsBaseCatalog3.setCatalog2Id(catalog2Id);
        //使用通用mapper查询数据,把对象传递过去
        return pmsBaseCatalog3Mapper.select(pmsBaseCatalog3);
    }

}
