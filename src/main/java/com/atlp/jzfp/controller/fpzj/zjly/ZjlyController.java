package com.atlp.jzfp.controller.fpzj.zjly;

import com.atlp.jzfp.common.base.BaseController;
import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.common.utils.AtlpUtil;
import com.atlp.jzfp.entity.fpzj.JzfpBZjLyEntity;
import com.atlp.jzfp.service.fpzj.zjly.IZjlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/9 11:03
 * @Decription: 资金来源 controller
 */
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
    public Map<String, Object> getPage(@RequestBody PageModel page) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");
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
    public Map<String, Object> doSave(HttpServletRequest request, @RequestBody JzfpBZjLyEntity entiy) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        if (AtlpUtil.isEmpty(entiy) || AtlpUtil.isEmpty(entiy.getLymc())) {
            logger.debug("传入资金来源信息不完整，增加资金来源信息失败...来源名称==={}", entiy.toString());
            reMap.put("code", "-1");
            reMap.put("msg", "传入资金来源信息不完整，增加资金来源信息失败");
            return reMap;
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
    public Map<String, Object> doUpdate(HttpServletRequest request, @RequestBody JzfpBZjLyEntity entiy) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        if (AtlpUtil.isEmpty(entiy) || AtlpUtil.isEmpty(entiy.getLyid()) || AtlpUtil.isEmpty(entiy.getLymc())) {
            logger.debug("传入资金来源信息不完整，修改资金来源信息失败...来源名称==={}", entiy.toString());
            reMap.put("code", "-1");
            reMap.put("msg", "传入资金来源信息不完整，修改资金来源信息失败");
            return reMap;
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
    public Map<String, Object> doDelete(HttpServletRequest request, @RequestBody JzfpBZjLyEntity entiy) throws Exception {
        return iZjlyService.doDelete(entiy);
    }
}
