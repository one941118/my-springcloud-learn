package com.ybl.demo.optimizeifelse;

/**
 * @ProjectName: demo
 * @Package: com.ybl.demo.optimizeifelse
 * @ClassName: UploadTypeFactory
 * @Author: liyongbin
 * @Description: ${description}
 * @Date: 2020/3/6 14:29
 * @Version: 1.0
 */
public class UploadTypeFactory {
    private final static String LOCAL = "local";
    private final static String FTP = "ftp";
    private final static String FASTDFS = "fastdfs";
    private final static String HDFS = "hdfs";

    public static UploadType storageTypeCreate(String storageType) {
        UploadType uploadType = null;
        switch (storageType) {
            case LOCAL:
                uploadType = new LocalStorageType();
                break;
            case FTP:
                uploadType = new FtpStorageType();
                break;
            case FASTDFS:
                uploadType = new FastDfsStorageType();
                break;
            case HDFS:
                uploadType = new HdfsStorageType();
                break;
        }
        return uploadType;
    }
}
