package com.atlp.jzfp.controller.fpxm.xmjd;

import com.atlp.jzfp.entity.fpxm.JzfpBXmJdEntity;
import com.atlp.jzfp.service.fpxm.xmjd.IXmjdService;
import org.atlp.base.BaseController;
import org.atlp.data.ExceptionEnum;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public boolean doDelete(@PathVariable(name = "jdid", required = true) String jdid) throws Exception {
        // 查询该附件是否存在
        JzfpBXmJdEntity xmJdEntity = iXmjdService.getInfoByKey(jdid);
        if (AtlpUtil.isEmpty(xmJdEntity)) {
            logger.debug("查询项目阶段为空,删除失败...阶段id==={}", jdid);
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "查询项目阶段为空,删除失败.");
        }

        return iXmjdService.doDelete(xmJdEntity);
    }

}
