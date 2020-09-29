package com.bjpowernode.fastdfs.controller;

import com.bjpowernode.fastdfs.model.CreditorInfo;
import com.bjpowernode.fastdfs.service.CreditorService;
import com.bjpowernode.fastdfs.utils.FastDFSUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Controller
public class CreditorController {
    @Resource
    private CreditorService creditorService;
    @RequestMapping("/")
    public String creditors(Model model){
        List<CreditorInfo> list = creditorService.selectAll();
        model.addAttribute("creditiorList",list);
        return "creditors";
    }

    @GetMapping("/upload/{id}")
    public String toupload(@PathVariable Integer id , Model model){
        CreditorInfo creditorInfo = creditorService.selectById(id);
        model.addAttribute("creditorInfo",creditorInfo);
        return "upload";
    }

    /**
     * 文件上传
     * @param id
     * @param model
     * @param myFile
     * @return
     */
    @PostMapping("/upload")
    public String upload(Integer id, Model model,MultipartFile myFile) throws IOException {
        String fileName = myFile.getOriginalFilename();
        byte [] buffFile = myFile.getBytes();
        long finalize = myFile.getSize();
        String fileType = myFile.getContentType();
        String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);

        String[] result = FastDFSUtil.upload(buffFile, fileExtName);

        CreditorInfo creditorInfo = new CreditorInfo();
        creditorInfo.setId(id);
        creditorInfo.setFileSize(finalize);
        creditorInfo.setFileType(fileType);
        creditorInfo.setOldFilename(fileName);
        creditorInfo.setGroupName(result[0]);
        creditorInfo.setRemoteFilePath(result[1]);
        creditorService.updateFileInfo(creditorInfo);
        model.addAttribute("message","文件上传成功,点击确定返回列表页面!");
        model.addAttribute("url","/");
        return "success";
    }

    /**
     *完成文件下载
     * @param id 需要下载的文件主键
     * @return ResponseEntity 表示相应的实体,这个类是spring
     */
    @RequestMapping("/download/{id}")
    public ResponseEntity<byte []> download(@PathVariable Integer id){

        CreditorInfo creditorInfo = creditorService.selectById(id);
        byte [] buffFile = FastDFSUtil.download(creditorInfo.getGroupName(), creditorInfo.getRemoteFilePath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);//设置相应类型为文件类型
        headers.setContentLength(creditorInfo.getFileSize());//设置相应是文件大小 用于自动提供文件下载进度
        headers.setContentDispositionFormData("attachment",creditorInfo.getOldFilename());//设置下载时的默认文件名
        ResponseEntity<byte []> responseEntity = new ResponseEntity<byte []>(buffFile,headers, HttpStatus.OK);
        return  responseEntity;

    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
creditorService.deleteFileById(id);
        return "redirect:/";
    }


}
