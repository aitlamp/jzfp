package com.atlp.jzfp.controller.fpzj.zjdz;

import com.atlp.jzfp.entity.fpzj.JzfpBZjDzEntity;
import com.atlp.jzfp.service.fpzj.zjdz.IZjdzService;
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
 * @CreateTime: 2018/10/10 11:21
 * @Decription: 资金到账 控制层
 */
@Slf4j
@Controller
@RequestMapping(value = "/fpzj/zjdz", method = {RequestMethod.GET, RequestMethod.POST})
public class ZjdzController extends BaseController {
    @Autowired
    private IZjdzService zjdzService;

    /**
     * 资金到账分页数据展示
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    @ResponseBody
    public PageModel getPage(@RequestBody PageModel page) {
        return zjdzService.getPage(page);
    }

    /**
     * 新增资金到账信息
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public Boolean doSave(@RequestBody JzfpBZjDzEntity entity) {
        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getLyid())
                || AtlpUtil.isEmpty(entity.getDzsj()) || AtlpUtil.isEmpty(entity.getDzje())
                || AtlpUtil.isEmpty(entity.getNd())) {
            throw new BusinessException(4201, "传入资金到账参数信息不完整，增加资金到账信息失败");
        }
        return zjdzService.doSaveOrUpdate(entity);
    }

    /**
     * 修改资金到账信息
     */
    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Boolean doUpdate(@RequestBody JzfpBZjDzEntity entity) {
        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getDzid())
                || AtlpUtil.isEmpty(entity.getLyid()) || AtlpUtil.isEmpty(entity.getDzsj())
                || AtlpUtil.isEmpty(entity.getDzje()) || AtlpUtil.isEmpty(entity.getNd())) {
            throw new BusinessException(4201, "传入资金到账参数信息不完整，修改资金到账信息失败");
        }
        return zjdzService.doSaveOrUpdate(entity);
    }

    /**
     * 单个资金到账信息详细查看
     */
    @RequestMapping(value = "/getZjdzById", method = RequestMethod.POST)
    @ResponseBody
    public JzfpBZjDzEntity getZjdzById(@RequestBody String dzid) {
        return zjdzService.getZjdzById(dzid);
    }

    /**
     * 删除对应的资金到账数据信息
     */
    @RequestMapping(value = "/doDelete", method = RequestMethod.POST)
    @ResponseBody
    public Boolean doDelete(@RequestBody String dzid) {
        return zjdzService.doDelete(dzid);
    }
}
