package com.atlp.jzfp.controller.fpxm.xmfj;

import com.atlp.jzfp.entity.fpxm.JzfpBXmFjEntity;
import com.atlp.jzfp.service.fpxm.xmfj.IXmfjService;
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
 * @CreateTime: 2018-10-10 13:10
 * @Decription: 项目附件控制层
 */
@Controller
@RequestMapping(value = "/fpxm/xmfj")
public class XmfjController extends BaseController {

    @Autowired
    private IXmfjService iXmfjService;

    /**
     * 删除项目附件
     * @param fjid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doDelete/{fjid}")
    @ResponseBody
    public Boolean doDelete(@PathVariable(name = "fjid", required = true) String fjid) throws BusinessException {
        // 查询该附件是否存在
        JzfpBXmFjEntity xmFjEntity = iXmfjService.getInfoById(fjid);
        if (AtlpUtil.isEmpty(xmFjEntity)) {
            logger.debug("查询项目附件为空,删除失败...附件id==={}", fjid);
            throw new BusinessException(ExceptionEnum.ERROR.getCode(), "查询项目附件为空,删除失败.");
        }

        return iXmfjService.doDelete(xmFjEntity);
    }

}
