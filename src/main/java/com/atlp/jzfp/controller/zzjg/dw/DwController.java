package com.atlp.jzfp.controller.zzjg.dw;

import com.atlp.jzfp.common.base.BaseController;
import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.service.zzjg.dw.IDwService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 组织架构--单位 Controller
 *
 * @author ctc
 * @date 2018年10月7日 11:30:06
 */
@Controller
@RequestMapping(value = "zzjg/dw")
public class DwController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IDwService dwService;

    /**
     * 获取单位分页数据
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    @ResponseBody
    public Page getPage(PageModel page) throws Exception {
        return dwService.getPage(page);
    }

}
