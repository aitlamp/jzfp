package com.atlp.jzfp.controller.zcxc;

import com.atlp.jzfp.common.base.BaseController;
import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.entity.zcxc.JzfpBZcxcEntity;
import com.atlp.jzfp.service.zcxc.IZcxcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-07 15:42
 * @Decription: 政策宣传控制层
 */
@Controller
@RequestMapping(value = "/zcxc/zcxc", method = {RequestMethod.GET, RequestMethod.POST})
public class ZcxcController extends BaseController {

    @Autowired
    private IZcxcService iZcxcService;

    /**
     * 分页查询
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getPage(@RequestBody PageModel page) throws Exception {
        return iZcxcService.getPage(page);
    }

    /**
     * 新增政策宣传
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doSave(HttpServletRequest request, @RequestBody JzfpBZcxcEntity entity) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        if (null == entity || null == entity.getLx() || null == entity.getBt() || null == entity.getNr()) {
            logger.debug("传入政策宣传信息不完整，添加政策宣传失败...政策宣传==={}", entity.toString());
            reMap.put("code", "-1");
            reMap.put("msg", "传入政策宣传信息不完整，添加政策宣传失败.");
            return reMap;
        }

        return iZcxcService.doSaveOrUpdate(entity, request);
    }
    /**
     * 新增政策宣传
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doUpdate(HttpServletRequest request, @RequestBody JzfpBZcxcEntity entity) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        if (null == entity || null == entity.getXcid()) {
            logger.debug("传入政策宣传信息不完整，添加政策宣传失败...政策宣传==={}", entity.toString());
            reMap.put("code", "-1");
            reMap.put("msg", "传入政策宣传信息不完整，添加政策宣传失败.");
            return reMap;
        }

        return iZcxcService.doSaveOrUpdate(entity, request);
    }

    /**
     * 删除政策宣传
     * @param xcid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doDelete/{xcid}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doDelete(@PathVariable(name = "xcid", required = true) String xcid) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        if (null == xcid) {
            logger.debug("传入政策宣传主键id为空，删除失败...主键id==={}", xcid);
            reMap.put("code", "-1");
            reMap.put("msg", "传入参数异常，删除失败...");
            return reMap;
        }

        // 查询政策宣传
        JzfpBZcxcEntity zcxcEntity = iZcxcService.getInfoById(xcid);
        if (null == zcxcEntity) {
            logger.debug("查询政策宣传信息失败，删除失败...主键id==={}", xcid);
            reMap.put("code", "-2");
            reMap.put("msg", "查询政策宣传信息失败，删除失败...");
            return reMap;
        }

        return iZcxcService.doDelete(zcxcEntity);
    }

}
