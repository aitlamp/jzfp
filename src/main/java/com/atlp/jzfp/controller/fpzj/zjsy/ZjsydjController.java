package com.atlp.jzfp.controller.fpzj.zjsy;

import com.atlp.jzfp.entity.fpzj.JzfpBZjSydjEntity;
import com.atlp.jzfp.service.fpzj.zjsy.IZjsydjService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.atlp.base.BaseController;
import org.atlp.data.PageModel;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/12 16:22
 * @Decription: 资金使用 controller
 */
@Slf4j
@Controller
@RequestMapping(value = "/fpzj/zjsy", method = {RequestMethod.POST, RequestMethod.GET})
public class ZjsydjController extends BaseController {
    @Autowired
    private IZjsydjService zjsydjService;

    /**
     * 资金使用分页展示
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    @ResponseBody
    public PageModel getPage(@RequestBody PageModel page) {
        return zjsydjService.getPage(page);
    }

    /**
     * 新增资金使用登记
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public Boolean doSave(HttpServletRequest request,@RequestBody JzfpBZjSydjEntity entity) {
        //判断是否有上传文件
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        MultipartFile file=null;
        if (isMultipart){
            MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
            file = multipartRequest.getFile("file");
        }
        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getSydwid())
                || AtlpUtil.isEmpty(entity.getZjyt()) || AtlpUtil.isEmpty(entity.getSyje())
                || AtlpUtil.isEmpty(entity.getDjsj()) || AtlpUtil.isEmpty(entity.getNd())) {
            throw new BusinessException(4201, "添加资金使用登记信息失败");
        }
        return zjsydjService.doSaveOrUpdate(entity, file);
    }

    /**
     * 修改资金使用登记
     */
    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Boolean doUpdate(HttpServletRequest request,@RequestBody JzfpBZjSydjEntity entity) {
        //判断是否有上传文件
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        MultipartFile file=null;
        if (isMultipart){
            MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
            file = multipartRequest.getFile("file");
        }
        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getDjid())
                || AtlpUtil.isEmpty(entity.getSydwid()) || AtlpUtil.isEmpty(entity.getZjyt())
                || AtlpUtil.isEmpty(entity.getSyje()) || AtlpUtil.isEmpty(entity.getDjsj())
                || AtlpUtil.isEmpty(entity.getNd())) {
            throw new BusinessException(4201, "修改资金使用登记信息失败");
        }
        return zjsydjService.doSaveOrUpdate(entity, file);
    }

    /**
     * 删除资金使用登记
     */
    @RequestMapping(value = "/doDelete")
    @ResponseBody
    public Boolean doDelete(String djid) {
        return zjsydjService.doDelete(djid);
    }

    /**
     * 单个资金使用登记信息详细查看
     */
    @RequestMapping(value = "/getZjsydjById")
    @ResponseBody
    public JzfpBZjSydjEntity getZjsydjById(String djid) {
        return zjsydjService.getZjsydjById(djid);
    }
}
