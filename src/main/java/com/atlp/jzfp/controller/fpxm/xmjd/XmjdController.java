package com.atlp.jzfp.controller.fpxm.xmjd;

import com.atlp.jzfp.common.base.BaseController;
import com.atlp.jzfp.common.utils.AtlpUtil;
import com.atlp.jzfp.entity.fpxm.JzfpBXmJdEntity;
import com.atlp.jzfp.service.fpxm.xmjd.IXmjdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-10 18:40
 * @Decription: 项目阶段控制层
 */
@Controller
@RequestMapping(value = "/fpxm/xmjd")
public class XmjdController extends BaseController {

    @Autowired
    private IXmjdService iXmjdService;

    /**
     * 删除项目阶段
     * @param jdid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doDelete/{jdid}")
    @ResponseBody
    public Map<String, Object> doDelete(@PathVariable(name = "jdid", required = true) String jdid) throws Exception {
        // 查询该附件是否存在
        JzfpBXmJdEntity xmJdEntity = iXmjdService.getInfoById(jdid);
        if (AtlpUtil.isEmpty(xmJdEntity)) {
            logger.debug("查询项目阶段为空,删除失败...阶段id==={}", jdid);
            Map<String, Object> reMap = new HashMap<>();
            reMap.put("code", "-1");
            reMap.put("msg", "查询项目阶段为空,删除失败.");
            return reMap;
        }

        return iXmjdService.doDelete(xmJdEntity);
    }

}
