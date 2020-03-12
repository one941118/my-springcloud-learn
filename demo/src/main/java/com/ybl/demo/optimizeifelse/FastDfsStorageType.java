package com.ybl.demo.optimizeifelse;

/**
 * @ProjectName: demo
 * @Package: com.ybl.demo.optimizeifelse
 * @ClassName: FastDfsStorageType
 * @Author: liyongbin
 * @Description: ${description}
 * @Date: 2020/3/6 14:31
 * @Version: 1.0
 */
public class FastDfsStorageType implements UploadType {
    @Override
    public void uploadFile(String file) {
        System.out.println("上传dfs");
    }
}
