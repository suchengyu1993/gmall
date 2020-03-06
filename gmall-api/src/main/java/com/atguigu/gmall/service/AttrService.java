package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.PmsBaseAttrInfo;

import java.util.List;

/**
 * AttrService
 *
 * @Author: 苏成瑜
 * @CreateTime: 2020-03-05
 * @Description:
 */
public interface AttrService {
    //查询平台属性的数据
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    void saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);
}
