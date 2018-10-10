package com.atlp.jzfp.controller.fpxm.xmzl;

import com.atlp.jzfp.common.base.BaseController;
import com.atlp.jzfp.common.data.PageModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-10 09:51
 * @Decription:
 */
@Controller
@RequestMapping(value = "/fpxm/xmzl", method = {RequestMethod.POST, RequestMethod.GET})
public class XmzlController extends BaseController {

    /**
     * 项目资料分页
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getPage")
    @ResponseBody
    public Map<String, Object> getPage(PageModel page) throws Exception {
        System.out.println("asdfg");
        return null;
    }

}
