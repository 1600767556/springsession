package com.bjpowernode.springsession.fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

public class FastDFSUtil {
    public static void main(String[] args) {
        upload();
        //download();
       // delete();
    }

    /**
     * 文件上传
     */
    public static void upload() {
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
            String [] result = storageClient.upload_file("G:\\lolavi\\lol\\2.avi","mp4",null);
            for (String s : result) {
                System.out.println(s);
            }
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
    /**
     * 文件下载
     */
    public static void download() {
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
            String groupName="group1";
            String remoteFileName="M00/00/00/wKiug1894CiADV4sADFoRZFNNBI214.mp4";
            String localFileName="D:/aa.mp4";
            int result = storageClient.download_file(groupName,remoteFileName,localFileName);
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

    /**
     * 文件删除
     */
    public static void delete() {
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
            String groupName="group1";
            String remoteFileName="M00/00/00/wKiug1894CiADV4sADFoRZFNNBI214.mp4";

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
