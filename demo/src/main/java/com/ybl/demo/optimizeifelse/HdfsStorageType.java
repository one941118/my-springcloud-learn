package com.ybl.demo.optimizeifelse;

/**
 * @ProjectName: demo
 * @Package: com.ybl.demo.optimizeifelse
 * @ClassName: HdfsStorageType
 * @Author: liyongbin
 * @Description: ${description}
 * @Date: 2020/3/6 14:31
 * @Version: 1.0
 */
public class HdfsStorageType implements UploadType {

    @Override
    public void uploadFile(String file) {
        System.out.println("上传hdfs");
    }
}
