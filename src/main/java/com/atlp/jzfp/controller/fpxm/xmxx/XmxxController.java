package com.atlp.jzfp.controller.fpxm.xmxx;

import com.atlp.jzfp.entity.fpxm.JzfpBXmXxEntity;
import com.atlp.jzfp.service.fpxm.xmxx.IXmxxService;
import org.atlp.base.BaseController;
import org.atlp.data.ExceptionEnum;
import org.atlp.data.PageModel;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-13 15:36
 * @Decription: 项目信息控制层
 */
@Controller
@RequestMapping(value = "/fpxm/xmxx", method = {RequestMethod.POST, RequestMethod.GET})
public class XmxxController extends BaseController {

    @Autowired
    private IXmxxService iXmxxService;

    /**
     * 分页查询项目信息
     * @param page
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    @ResponseBody
    public PageModel getPage(@RequestBody PageModel page) throws BusinessException {
        return iXmxxService.getPage(page);
    }

    /**
     * 查询项目信息
     * @param xmid
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getInfo/{xmid}")
    @ResponseBody
    public JzfpBXmXxEntity getInfo(@PathVariable(name = "xmid", required = true) String xmid) throws BusinessException {
        return iXmxxService.getInfoByKey(xmid);
    }

    /**
     * 新增项目
     * @param request
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public boolean doSave(HttpServletRequest request, @RequestBody JzfpBXmXxEntity entity) throws BusinessException {
        // 确保项目信息数据完整
        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getXmmc()) || AtlpUtil.isEmpty(entity.getXmjc())
            || AtlpUtil.isEmpty(entity.getDlid()) || AtlpUtil.isEmpty(entity.getXlid())) {
            logger.debug("传入项目信息不完整，增加项目信息失败...项目信息==={}", entity.toString());
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "项目信息不完整.");
        }

        return iXmxxService.doSaveOrUpdate(entity, request);
    }

    /**
     * 编辑修改项目信息
     * @param request
     * @param entity
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public boolean doUpdate(HttpServletRequest request, @RequestBody JzfpBXmXxEntity entity) throws BusinessException {
        // 确保项目信息数据完整
        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getXmid())) {
            logger.debug("传入项目信息不完整，增加项目信息失败...项目信息==={}", entity.toString());
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "项目信息不完整.");
        }

        return iXmxxService.doSaveOrUpdate(entity, request);
    }

}
