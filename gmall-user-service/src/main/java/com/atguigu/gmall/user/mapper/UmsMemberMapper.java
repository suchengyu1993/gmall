package com.atguigu.gmall.user.mapper;

import com.atguigu.gmall.bean.UmsMember;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
/**
 * UmsMemberMapper
 *
 * @Author: 苏成瑜
 * @CreateTime: 2020-03-03
 * @Description:
 */
public interface UmsMemberMapper extends Mapper<UmsMember> {
    List<UmsMember> getAllUser();

    List<UmsMember> getAllUser2();
}
