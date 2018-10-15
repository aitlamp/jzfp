package com.atlp.jzfp.controller.fpzj.zjly;

import com.atlp.jzfp.entity.fpzj.JzfpBZjLyEntity;
import com.atlp.jzfp.service.fpzj.zjly.IZjlyService;
import lombok.extern.slf4j.Slf4j;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/9 11:03
 * @Decription: 资金来源 controller
 */
@Slf4j
@Controller
@RequestMapping(value = "/fpzj/zjly", method = {RequestMethod.GET, RequestMethod.POST})
public class ZjlyController extends BaseController {
    @Autowired
    private IZjlyService iZjlyService;

    /**
     * 资金来源分页数据展示
     *
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    @ResponseBody
    public PageModel getPage(@RequestBody PageModel page) throws Exception {
        return iZjlyService.getPage(page);
    }

    /**
     * 增加资金来源信息
     *
     * @param request
     * @param entiy
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public Boolean doSave(HttpServletRequest request, @RequestBody JzfpBZjLyEntity entiy) throws BusinessException {

        if (AtlpUtil.isEmpty(entiy) || AtlpUtil.isEmpty(entiy.getLymc())) {
            log.debug("参数异常，传入资金来源信息不完整，增加资金来源信息失败...来源名称==={}", entiy.toString());
            throw new BusinessException(4201,"传入资金来源信息不完整，增加资金来源信息失败");
        }

        return iZjlyService.doSaveOrUpdate(entiy);
    }

    /**
     * 修改资金来源信息
     *
     * @param request
     * @param entiy
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Boolean doUpdate(HttpServletRequest request, @RequestBody JzfpBZjLyEntity entiy) throws Exception {


        if (AtlpUtil.isEmpty(entiy) || AtlpUtil.isEmpty(entiy.getLyid()) || AtlpUtil.isEmpty(entiy.getLymc())) {
            log.debug("参数异常，传入资金来源信息不完整，修改资金来源信息失败...来源名称==={}", entiy.toString());
            throw new BusinessException(4201,"传入资金来源信息不完整，修改资金来源信息失败...");
        }

        return iZjlyService.doSaveOrUpdate(entiy);
    }

    /**
     * 删除对应的资金来源信息
     *
     * @param request
     * @param entiy
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doDelete", method = RequestMethod.POST)
    @ResponseBody
    public Boolean doDelete(HttpServletRequest request, @RequestBody JzfpBZjLyEntity entiy) throws Exception {
        return iZjlyService.doDelete(entiy);
    }
}
