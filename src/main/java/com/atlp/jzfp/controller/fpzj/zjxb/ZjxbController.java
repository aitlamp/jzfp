package com.atlp.jzfp.controller.fpzj.zjxb;

import com.atlp.jzfp.entity.fpzj.JzfpBZjXbEntity;
import com.atlp.jzfp.service.fpzj.zjxb.IZjxbService;
import lombok.extern.slf4j.Slf4j;
import org.atlp.base.BaseController;
import org.atlp.data.PageModel;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/11 15:41
 * @Decription: 资金下拨控制层
 */
@Slf4j
@Controller
@RequestMapping(value = "/fpzj/zjxb", method = {RequestMethod.GET, RequestMethod.POST})
public class ZjxbController extends BaseController {
    @Autowired
    private IZjxbService zjxbService;

    /**
     * 资金下拨分页数据展示
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    @ResponseBody
    public PageModel getPage(@RequestBody PageModel page) {
        return zjxbService.getPage(page);
    }

    /**
     * 新增资金下拨信息
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public Boolean doSave(@RequestBody JzfpBZjXbEntity entity) {
        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getXbsj())
                || AtlpUtil.isEmpty(entity.getXbje()) || AtlpUtil.isEmpty(entity.getJsdwid())
                || AtlpUtil.isEmpty(entity.getNd())) {
            throw new BusinessException(4201, "传入资金到账信息不完整，增加资金下拨信息失败");
        }
        return zjxbService.doSaveOrUpdate(entity);
    }

    /**
     * 修改资金下拨信息
     */
    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Boolean doUpdate(@RequestBody JzfpBZjXbEntity entity) {

        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getDzid())
                || AtlpUtil.isEmpty(entity.getXbsj()) || AtlpUtil.isEmpty(entity.getXbje())
                || AtlpUtil.isEmpty(entity.getJsdwid()) || AtlpUtil.isEmpty(entity.getNd())) {
            throw new BusinessException(4201, "传入资金下拨信息不完整，修改资金下拨信息失败");
        }
        return zjxbService.doSaveOrUpdate(entity);
    }

    /**
     * 单个资金下拨信息详细查看
     */
    @RequestMapping(value = "/getZjxbById", method = RequestMethod.POST)
    @ResponseBody
    public JzfpBZjXbEntity getZjxbById(@RequestBody String dzid) {
        return zjxbService.getZjxbById(dzid);
    }

    /**
     * 删除对应的资金下拨数据信息
     */
    @RequestMapping(value = "/doDelete", method = RequestMethod.POST)
    @ResponseBody
    public Boolean doDelete(@RequestBody String dzid) {
        return zjxbService.doDelete(dzid);
    }
}
