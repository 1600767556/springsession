package com.bjpowernode.fastdfs.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

public class FastDFSUtil {

    /**
     * 文件上传
     * @return
     */
    public static String[] upload(byte [] buffFile, String fileExtName) {
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            ClientGlobal.init("fastdfs.conf");
            TrackerClient trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
            StorageClient storageClient = new StorageClient(trackerServer,storageServer);
            /**
             * 文件上传
             */
            String [] result = storageClient.upload_file(buffFile,fileExtName,null);
           return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }finally {
            if (storageServer != null) {
                try {
                    storageServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (trackerServer != null) {
                try {
                    trackerServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    /**
     * 文件下载
     */
    public static byte[] download(String groupName,String remoteFileName) {
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            ClientGlobal.init("fastdfs.conf");
            TrackerClient trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
            StorageClient storageClient = new StorageClient(trackerServer,storageServer);
            /**
             * 文件下载
             */

            byte[] buffFile = storageClient.download_file(groupName,remoteFileName);
            return buffFile;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }finally {
            if (storageServer != null) {
                try {
                    storageServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (trackerServer != null) {
                try {
                    trackerServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  null;
    }

    /**
     * 文件删除
     */
    public static void delete(String groupName,String remoteFileName) {
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            ClientGlobal.init("fastdfs.conf");
            TrackerClient trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
            StorageClient storageClient = new StorageClient(trackerServer,storageServer);
            /**
             * 文件删除
             */

            int result = storageClient.delete_file(groupName,remoteFileName);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }finally {
            if (storageServer != null) {
                try {
                    storageServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (trackerServer != null) {
                try {
                    trackerServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
