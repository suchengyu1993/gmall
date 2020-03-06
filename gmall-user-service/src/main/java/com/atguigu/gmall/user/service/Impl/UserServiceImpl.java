package com.atguigu.gmall.user.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.UmsMember;
import com.atguigu.gmall.service.UserService;
import com.atguigu.gmall.user.mapper.UmsMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * UserServiceImpl
 *
 * @Author: 苏成瑜
 * @CreateTime: 2020-03-03
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UmsMemberMapper umsMemberMapper;
    @Override
    public List<UmsMember> getAllUser() {
        List<UmsMember> umsMemberList = umsMemberMapper.getAllUser();
        return umsMemberList;
    }

    @Override
    public List<UmsMember> getAllUser2() {
        List<UmsMember> umsMemberList2 = umsMemberMapper.selectAll();
        return umsMemberList2;
    }
}
