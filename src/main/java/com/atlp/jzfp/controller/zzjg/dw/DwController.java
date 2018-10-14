package com.atlp.jzfp.controller.zzjg.dw;

import com.alibaba.fastjson.JSONObject;
import com.atlp.jzfp.common.base.BaseController;
import com.atlp.jzfp.common.base.FastDFSClientWrapper;
import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.common.prop.CustomProps;
import com.atlp.jzfp.service.zzjg.dw.IDwService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 组织架构--单位 Controller
 *
 * @author ctc
 * @date 2018年10月7日 11:30:06
 */
@Slf4j
@Controller
@RequestMapping(value = "zzjg/dw")
public class DwController extends BaseController {
    @Autowired
    private IDwService dwService;
    @Autowired
    CustomProps customProps;
    @Autowired
    private FastDFSClientWrapper dfsClient;

    /**
     * 获取单位分页数据
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    @ResponseBody
    public Map getPage(@RequestBody PageModel pageModel) {
        return dwService.getPage(pageModel);
    }

    //处理文件上传
    @ResponseBody
    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String doUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String imgUrl = "";
        try {
            imgUrl = dfsClient.uploadFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回
        log.debug(imgUrl);
        return imgUrl;
    }

    //删除
    @ResponseBody
    @RequestMapping(value = "/doDelete", method = RequestMethod.POST)
    public boolean doDelete(@RequestBody Map pmap) {
        return dwService.doDelete(pmap.get("dwid").toString());
    }

}
