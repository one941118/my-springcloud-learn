package com.ybl.demo.optimizeifelse;

/**
 * @ProjectName: demo
 * @Package: com.ybl.demo.optimizeifelse
 * @ClassName: UploadClient
 * @Author: liyongbin
 * @Description: ${description}
 * @Date: 2020/3/6 14:37
 * @Version: 1.0
 */
public class UploadClient {
    public static void main(String[] args) {
        UploadType iStorageType = UploadTypeFactory.storageTypeCreate("hdfs");
        iStorageType.uploadFile("simpleFactory.txt");
    }

}
