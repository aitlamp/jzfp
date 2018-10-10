package com.atlp.jzfp.controller.fpxm.xmzl;

import com.atlp.jzfp.common.base.BaseController;
import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.common.utils.AtlpUtil;
import com.atlp.jzfp.entity.fpxm.JzfpBXmZlEntity;
import com.atlp.jzfp.service.fpxm.xmzl.IXmzlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-10 09:51
 * @Decription:
 */
@Controller
@RequestMapping(value = "/fpxm/xmzl", method = {RequestMethod.POST, RequestMethod.GET})
public class XmzlController extends BaseController {

    @Autowired
    private IXmzlService iXmzlService;

    /**
     * 项目资料分页
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getPage")
    @ResponseBody
    public Map<String, Object> getPage(@RequestBody PageModel page) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        if (AtlpUtil.isEmpty(page.getPmap()) || AtlpUtil.isEmpty(page.getPmap().get("flid").toString())) {
            logger.debug("传入查询信息不完整，分页查询项目资料信息失败...查询信息==={}", page.getPmap().toString());
            reMap.put("code", "-1");
            reMap.put("msg", "传入查询信息不完整，分页查询项目资料信息失败.");
            return reMap;
        }

        return iXmzlService.getPage(page);
    }

    /**
     * 项目资料信息
     * @param zlid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getInfo/{zlid}")
    @ResponseBody
    public Map<String, Object> getInfo(@PathVariable(name = "zlid", required = true) String zlid) throws Exception {
        return iXmzlService.getInfoByKey(zlid);
    }

    /**
     * 新增项目资料
     * @param request
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doSave(HttpServletRequest request, @RequestBody JzfpBXmZlEntity entity) throws Exception {
        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getZlmc()) || AtlpUtil.isEmpty(entity.getXmFjEntity())) {
            logger.debug("传入项目资料信息不完整，增加项目资料信息失败...资料信息==={}", entity.toString());
            Map<String, Object> reMap = new HashMap<>();
            reMap.put("code", "-1");
            reMap.put("msg", "传入项目资料信息不完整，增加项目资料信息失败.");
            return reMap;
        }

        return iXmzlService.doSaveOrUpdate(entity, request);
    }

    /**
     * 新增项目资料
     * @param request
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doUpdate(HttpServletRequest request, @RequestBody JzfpBXmZlEntity entity) throws Exception {
        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getZlid())
                || AtlpUtil.isEmpty(entity.getZlmc()) || AtlpUtil.isEmpty(entity.getXmFjEntity())) {
            logger.debug("传入项目资料信息不完整，修改项目资料信息失败...资料信息==={}", entity.toString());
            Map<String, Object> reMap = new HashMap<>();
            reMap.put("code", "-1");
            reMap.put("msg", "传入项目资料信息不完整，修改项目资料信息失败.");
            return reMap;
        }

        return iXmzlService.doSaveOrUpdate(entity, request);
    }

}
