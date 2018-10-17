package com.atlp.jzfp.controller.fpzj.zjsy;

import com.atlp.jzfp.entity.fpzj.JzfpBZjSydjEntity;
import com.atlp.jzfp.service.fpzj.zjsy.IZjsydjService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.atlp.base.BaseController;
import org.atlp.data.PageModel;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public Boolean doSave(HttpServletRequest request, JzfpBZjSydjEntity entity) {
        //判断是否有上传文件
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        List<MultipartFile> files = null;
        if (isMultipart) {
            MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
            files = multipartRequest.getFiles("file");
        }
        //判断参数
        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getSydwid())
                || AtlpUtil.isEmpty(entity.getZjyt()) || AtlpUtil.isEmpty(entity.getSyje())
                || AtlpUtil.isEmpty(entity.getDjsj()) || AtlpUtil.isEmpty(entity.getNd())) {
            throw new BusinessException(4201, "添加资金使用登记信息失败");
        }
        return zjsydjService.doSaveOrUpdate(entity, files);
    }

    /**
     * 修改资金使用登记
     */
    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Boolean doUpdate(HttpServletRequest request, JzfpBZjSydjEntity entity) {
        //判断是否有上传文件
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        List<MultipartFile> files=null;
        if (isMultipart) {
            MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
            files = multipartRequest.getFiles("file");
        }
        //判断参数
        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getDjid())
                || AtlpUtil.isEmpty(entity.getSydwid()) || AtlpUtil.isEmpty(entity.getZjyt())
                || AtlpUtil.isEmpty(entity.getSyje()) || AtlpUtil.isEmpty(entity.getDjsj())
                || AtlpUtil.isEmpty(entity.getNd())) {
            throw new BusinessException(4201, "修改资金使用登记信息失败");
        }
        return zjsydjService.doSaveOrUpdate(entity, files);
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
    /**
     * 查询单个项目的资金使用登记信息
     */
    @RequestMapping(value = "/getZjsydjByXmid")
    @ResponseBody
    public List<JzfpBZjSydjEntity> getZjsydjByXmid(String xmid) {
        return zjsydjService.getZjsydjByXmid(xmid);
    }
}
