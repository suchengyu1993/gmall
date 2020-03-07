package com.atguigu.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.PmsBaseSaleAttr;
import com.atguigu.gmall.bean.PmsProductInfo;
import com.atguigu.gmall.manager.util.GmallUploadUtil;
import com.atguigu.gmall.service.SpuService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * SpuController
 *
 * @Author: 苏成瑜
 * @CreateTime: 2020-03-06
 * @Description:
 * @CrossOrigin:解决跨域问题
 */
@RestController
@CrossOrigin
public class SpuController {
    //远程注入,需要使用@Reference,导入的包为 alibaba.dubbo
    @Reference
    private SpuService spuService;

    /**
     * 查询全部商品列表信息
     * @param catalog3Id 三级分类的id
     * @return 商品列表信息
     */
    @RequestMapping("/spuList")
    public List<PmsProductInfo> spuList(String catalog3Id){
        List<PmsProductInfo> pmsProductInfos =  spuService.spuList(catalog3Id);
        return pmsProductInfos;
    }


    /**
     * 保存商品的spu信息
     * @param pmsProductInfo
     */
    @RequestMapping("/saveSpuInfo")
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        //保存完整的商品spu信息
        spuService.saveSpuInfo(pmsProductInfo);
        //返回提示信息
        return "success";
    }
    /**
     * 查询销售属性
     * @return
     */
    @RequestMapping("/baseSaleAttrList")
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        List<PmsBaseSaleAttr> pmsBaseSaleAttrs = spuService.baseSaleAttrList();
        return pmsBaseSaleAttrs;
    }

    //上传图片
    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("file")MultipartFile file){
        //调用公共的上传方法
        String url =  GmallUploadUtil.uploadImage(file);
        return url;
}

}
