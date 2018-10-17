package com.atlp.jzfp.controller.common.file;

import lombok.extern.slf4j.Slf4j;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.atlp.wrapper.FastDFSClientWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 统一文件处理 Controller
 *
 * @author ctc
 * @date 2018年10月17日 16:01:29
 */
@Slf4j
@Controller
public class FileController {
    @Autowired
    private FastDFSClientWrapper fdfsClient;

    //上传
//    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//    @ResponseBody
//    public Object uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
////        Map retMap = new HashMap();
////        retMap.put("path", fdfsClient.uploadFile(file));
////        return retMap;
//        return new String[]{fdfsClient.uploadFile(file)};
//    }

    //多文件上传
    @RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadFiles(HttpServletRequest request) throws IOException {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String[] paths = new String[files.size()];
        for (int i = 0; i < files.size(); i++) {
            paths[i] = fdfsClient.uploadFile(files.get(i));
        }
        return paths;
    }

    //删除
    @ResponseBody
    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    public boolean deleteFile(@RequestBody Map pmap) {
        String path = AtlpUtil.toString(pmap.get("path"));
        if (AtlpUtil.isEmpty(path)) {
            throw new BusinessException(4201, "附件地址不能为空");
        }
        fdfsClient.deleteFile(path);
        return true;
    }

    //下载
}
