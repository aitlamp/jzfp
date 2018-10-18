package com.atlp.jzfp.controller.fpxm.xmsh;

import com.atlp.jzfp.service.fpxm.xmsh.IXmshService;
import org.atlp.base.BaseController;
import org.atlp.data.PageModel;
import org.atlp.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-18 14:27
 * @Decription:
 */
@Controller
@RequestMapping(value = "/fpxm/xmsh", method = {RequestMethod.POST, RequestMethod.GET})
public class XmshController extends BaseController {

    @Autowired
    private IXmshService iXmshService;

    /**
     * 分页查询审核阶段
     * @param page
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getJdPage", method = RequestMethod.POST)
    @ResponseBody
    public PageModel getJdPage(@RequestBody PageModel page) throws BusinessException {
        return iXmshService.getJdshPage(page);
    }

}
