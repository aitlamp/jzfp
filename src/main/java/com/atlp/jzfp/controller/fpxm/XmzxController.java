package com.atlp.jzfp.controller.fpxm;

import com.atlp.jzfp.entity.fpxm.JzfpBXmFjEntity;
import com.atlp.jzfp.service.fpxm.xmfj.IXmfjService;
import org.atlp.base.BaseController;
import org.atlp.data.PageModel;
import org.atlp.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-16 13:24
 * @Decription:
 */
@Controller
@RequestMapping(value = "/fpxm/xmzx", method = {RequestMethod.POST, RequestMethod.GET})
public class XmzxController extends BaseController {

    @Autowired
    private IXmfjService iXmfjService;

    /**
     * 分页查询项目执行情况
     * @param page
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getPage")
    @ResponseBody
    public PageModel getPage(@RequestBody PageModel page) throws BusinessException {
        return null;
    }

    /**
     * 查询项目所需附件list
     * @param xmid
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getXmsxfjList/{xmid}")
    @ResponseBody
    public List<JzfpBXmFjEntity> getXmsxfjList(@PathVariable(name = "xmid", required = true) String xmid)
            throws BusinessException {
        return iXmfjService.getXmsxfjByXmid(xmid);
    }

    /**
     * 查询项目已上传附件list
     * @param xmid
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getXmyscfjList/{xmid}")
    @ResponseBody
    public List<JzfpBXmFjEntity> getXmyscfjList(@PathVariable(name = "xmid", required = true) String xmid)
            throws BusinessException {
        return iXmfjService.getXmyscfjByXmid(xmid);
    }



}
