package com.bjpowernode.fastdfs.service;

import com.bjpowernode.fastdfs.model.CreditorInfo;

import java.util.List;

public interface CreditorService {
    List<CreditorInfo> selectAll();

    CreditorInfo selectById(Integer id);

    void updateFileInfo(CreditorInfo creditorInfo);

    void deleteFileById(Integer id);
}
