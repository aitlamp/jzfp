package com.atlp.jzfp.controller.fpxm.xmfl;

import com.atlp.jzfp.common.base.BaseController;
import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.common.utils.AtlpUtil;
import com.atlp.jzfp.entity.fpxm.JzfpBXmFlEntity;
import com.atlp.jzfp.service.fpxm.xmfl.IXmflService;
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
 * @Author: zhangchq
 * @CreateTime: 2018-10-08 16:42
 * @Decription: 项目分类controller
 */
@Controller
@RequestMapping(value = "/fpxm/xmfl", method = {RequestMethod.POST, RequestMethod.GET})
public class XmflController extends BaseController {

    @Autowired
    private IXmflService iXmflService;

    /**
     * 分页查询父级分类下的分类信息
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

        if (AtlpUtil.isEmpty(page.getPmap()) || AtlpUtil.isEmpty(page.getPmap().get("pflid").toString())) {
            logger.debug("传入查询信息不完整，分页查询项目分类信息失败...查询信息==={}", page.getPmap().toString());
            reMap.put("code", "-1");
            reMap.put("msg", "传入查询信息不完整，分页查询项目分类信息失败.");
            return reMap;
        }

        return iXmflService.getPage(page);
    }

    /**
     * 添加项目分类
     * @param request
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doSave(HttpServletRequest request, @RequestBody JzfpBXmFlEntity entity) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getPflid()) || AtlpUtil.isEmpty(entity.getFlmc())) {
            logger.debug("传入项目分类信息不完整，增加项目分类信息失败...分类信息==={}", entity.toString());
            reMap.put("code", "-1");
            reMap.put("msg", "传入项目分类信息不完整，增加项目分类信息失败.");
            return reMap;
        }

        return iXmflService.doSaveOrUpdate(entity);
    }

    /**
     * 修改项目分类
     * @param request
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doUpdate(HttpServletRequest request, @RequestBody JzfpBXmFlEntity entity) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getFlid()) || AtlpUtil.isEmpty(entity.getPflid())) {
            logger.debug("传入项目分类信息不完整，修改项目分类信息失败...分类信息==={}", entity.toString());
            reMap.put("code", "-1");
            reMap.put("msg", "传入项目分类信息不完整，修改项目分类信息失败.");
            return reMap;
        }

        return iXmflService.doSaveOrUpdate(entity);
    }

    /**
     * 查询项目分类树
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getXmflTree", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getXmflTreeWithTitle() throws Exception {
        return iXmflService.getListWithTitle();
    }

}
