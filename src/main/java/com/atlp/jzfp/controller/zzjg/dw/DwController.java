package com.atlp.jzfp.controller.zzjg.dw;

import com.atlp.jzfp.service.zzjg.dw.IDwService;
import lombok.extern.slf4j.Slf4j;
import org.atlp.base.BaseController;
import org.atlp.data.PageModel;
import org.atlp.wrapper.FastDFSClientWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    //获取数据
    @ResponseBody
    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    public Object getData(@RequestBody Map pmap) {
        return dwService.getData(pmap);
    }

}
