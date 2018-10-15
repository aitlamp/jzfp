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

import javax.servlet.http.HttpServletRequest;

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
     *
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    @ResponseBody
    public PageModel getPage(@RequestBody PageModel page) throws Exception {
        return zjxbService.getPage(page);
    }

    /**
     * 新增资金下拨信息
     *
     * @param request
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public Boolean doSave(HttpServletRequest request, @RequestBody JzfpBZjXbEntity entity) throws Exception {
        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getXbsj())
                || AtlpUtil.isEmpty(entity.getXbje()) || AtlpUtil.isEmpty(entity.getJsdwid())
                || AtlpUtil.isEmpty(entity.getNd())) {
            log.debug("传入资金下拨信息不完整，增加资金下拨信息失败...下拨时间==={},下拨金额==={},接收单位id==={},资金年度==={}",
                    entity.toString());
            throw new BusinessException(4201, "传入资金到账信息不完整，增加资金下拨信息失败");
        }
        return zjxbService.doSaveOrUpdate(entity);
    }

    /**
     * 修改资金下拨信息
     *
     * @param request
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Boolean doUpdate(HttpServletRequest request, @RequestBody JzfpBZjXbEntity entity) throws Exception {

        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getDzid())
                || AtlpUtil.isEmpty(entity.getXbsj()) || AtlpUtil.isEmpty(entity.getXbje())
                || AtlpUtil.isEmpty(entity.getJsdwid()) || AtlpUtil.isEmpty(entity.getNd())) {
            log.debug("传入资金下拨信息不完整，修改资金下拨信息失败...下拨id==={},下拨单位id==={},下拨单位名称==={}," +
                    "下拨时间==={},下拨金额==={},接收单位id==={},接收单位名称==={},资金年度==={}", entity.toString());
            throw new BusinessException(4201, "传入资金下拨信息不完整，修改资金下拨信息失败");
        }
        return zjxbService.doSaveOrUpdate(entity);
    }

    /**
     * 单个资金下拨信息详细查看
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getZjxbById", method = RequestMethod.POST)
    @ResponseBody
    public JzfpBZjXbEntity getZjxbById(HttpServletRequest request, @RequestBody JzfpBZjXbEntity entity) throws Exception {
        return zjxbService.getZjxbById(entity);
    }

    /**
     * 删除对应的资金下拨数据信息
     *
     * @param request
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doDelete", method = RequestMethod.POST)
    @ResponseBody
    public Boolean doDelete(HttpServletRequest request, @RequestBody JzfpBZjXbEntity entity) throws Exception {
        return zjxbService.doDelete(entity);
    }
}
