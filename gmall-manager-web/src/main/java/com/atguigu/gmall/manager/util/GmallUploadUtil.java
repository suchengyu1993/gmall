package com.atguigu.gmall.manager.util;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;


/**
 * GmallUploadUtil
 *
 * @Author: 苏成瑜
 * @CreateTime: 2020-03-06
 * @Description:
 * 公共的图片上传功能
 */
public class GmallUploadUtil {

    /**
     * 文件上传功能
     * @param multipartFile
     * @return
     */
    public static String uploadImage(MultipartFile multipartFile) {
        String responseUrl = "http://192.168.114.174";
        String path = GmallUploadUtil.class.getResource("/tracker.conf").getPath();
        try {
            ClientGlobal.init(path);
            // 链接tracker
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getTrackerServer();
            // 获得storage
            StorageClient storageClient = new StorageClient(trackerServer, null);
            //获取文件的名称
            String originalFilename = multipartFile.getOriginalFilename();
            //获取文件名中点的位置
            int index = originalFilename.lastIndexOf(".");
            //截取点之后的字符串,图片的类型如(jpg,png)
            String ext = originalFilename.substring(index);
            // 上传文件 第一个参数是文件路径,第二个参数是文件类型,第三个参数null
            String[] urls = storageClient.upload_file(multipartFile.getBytes(), ext, null);
            // 解析返回的图片的路径url信息
            for (String url : urls) {
                responseUrl = responseUrl + "/" + url;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回路径
        return responseUrl;
    }
}
