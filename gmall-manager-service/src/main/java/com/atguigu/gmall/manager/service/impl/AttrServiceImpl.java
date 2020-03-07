package com.atguigu.gmall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.PmsBaseAttrInfo;
import com.atguigu.gmall.bean.PmsBaseAttrValue;
import com.atguigu.gmall.manager.mapper.PmsBaseAttrInfoMapper;
import com.atguigu.gmall.manager.mapper.PmsBaseAttrValueMapper;
import com.atguigu.gmall.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * AttrServiceImpl
 *
 * @Author: 苏成瑜
 * @CreateTime: 2020-03-05
 * @Description:
 @Service:选择com.alibaba.dubbo.config.annotation.Service;
 */
@Service
public class AttrServiceImpl implements AttrService {
    @Autowired
    private PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;
    @Autowired
    private PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    /**
     * 查询平台属性表的数据和平台属性值表的数据
     * @param catalog3Id
     * @return
     */
    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        //查询平台属性表的数据
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        //传递过去对象,查询数据
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);
        //添加查询平台属性值表的功能
        for (PmsBaseAttrInfo baseAttrInfo : pmsBaseAttrInfos) {
            PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
            //设置平台属性值表里的属性id
            pmsBaseAttrValue.setAttrId(baseAttrInfo.getId());
            //查询平台属性值表数据
            List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
            //把平台属性值表数据设置到平台属性表里
            baseAttrInfo.setAttrValueList(pmsBaseAttrValues);
        }
        return pmsBaseAttrInfos;
    }

    /**
     * 保存平台属性值的数据
     * @param pmsBaseAttrInfo
     */
    @Override
    public void saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        //PmsBaseAttrInfo   平台属性表
        //PmsBaseAttrValue  平台属性值表

        /*
        *使用insert,表里有5个字段,我只插入3个字段,没有值的2个字段会自动用null覆盖 ,我们使用的是主键自动生成,不能用null值覆盖掉主键
        * 使用insertSelective,只插入有值的部分,没有值的部分不插入,主键不插入null
         */
        //保存平台属性表
        pmsBaseAttrInfoMapper.insertSelective(pmsBaseAttrInfo);
        //返回添加的平台属性表主键
        String attrInfoId = pmsBaseAttrInfo.getId();
        //保存平台属性值表
        List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
        for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
            pmsBaseAttrValue.setAttrId(attrInfoId);
            pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
        }
    }
}
