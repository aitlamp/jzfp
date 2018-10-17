package com.atlp.jzfp.controller.fpzj.zjfj;

import com.atlp.jzfp.entity.fpzj.JzfpBZjFjEntity;
import com.atlp.jzfp.service.fpzj.zjfj.IZjfjService;
import lombok.extern.slf4j.Slf4j;
import org.atlp.base.BaseController;
import org.atlp.wrapper.FastDFSClientWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/12 16:12
 * @Decription: 资金附件 controll
 */
@Slf4j
@Controller
@RequestMapping(value = "/fpzj/zjfj", method = {RequestMethod.POST, RequestMethod.GET})
public class ZjfjController extends BaseController {
    @Autowired
    private IZjfjService zjfjService;

    /**
     * 删除对应的资金附件
     */
    @RequestMapping(value = "doDelete")
    @ResponseBody
    public Boolean doDelete(String fjid) {
        return zjfjService.doDelete(fjid);
    }

}
