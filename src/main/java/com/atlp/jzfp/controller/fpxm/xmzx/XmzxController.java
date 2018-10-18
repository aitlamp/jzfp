package com.atlp.jzfp.controller.fpxm.xmzx;

import com.atlp.jzfp.entity.fpxm.JzfpBXmJdEntity;
import com.atlp.jzfp.entity.fpxm.JzfpBXmXxEntity;
import com.atlp.jzfp.entity.fpxm.JzfpBXmZxEntity;
import com.atlp.jzfp.service.fpxm.xmfj.IXmfjService;
import com.atlp.jzfp.service.fpxm.xmjd.IXmjdService;
import com.atlp.jzfp.service.fpxm.xmxx.IXmxxService;
import com.atlp.jzfp.service.fpxm.xmzx.IXmzxService;
import org.atlp.base.BaseController;
import org.atlp.data.ExceptionEnum;
import org.atlp.data.PageModel;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private IXmxxService iXmxxService;
    @Autowired
    private IXmzxService iXmzxService;
    @Autowired
    private IXmjdService iXmjdService;

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
     * 上报进度
     * 查询项目阶段进度信息
     * @param xmid
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getXmxxInfo/{xmid}")
    @ResponseBody
    public Map<String, Object> getXmxxInfo(@PathVariable(name = "xmid", required = true) String xmid)
            throws BusinessException {
        Map<String, Object> map = new HashMap<>();

        // 查询项目信息和阶段信息
        JzfpBXmXxEntity xmXxEntity = iXmxxService.getInfoByKey(xmid);
        if (AtlpUtil.isEmpty(xmXxEntity) || AtlpUtil.isEmpty(xmXxEntity.getXmJdEntityList())) {
            logger.debug("查询项目信息失败...项目id==={}", xmid);
            throw new BusinessException(ExceptionEnum.ERROR.getCode(), "查询项目信息失败.");
        }
        // 项目阶段list
        List<JzfpBXmJdEntity> xmJdEntityList = xmXxEntity.getXmJdEntityList();
        // 查询项目阶段的阶段累计完成率
        for (JzfpBXmJdEntity xmJdEntity : xmJdEntityList) {
            JzfpBXmZxEntity xmZxEntity = iXmzxService.getProjectStageTotalCompleteRate(xmJdEntity.getJdid());
            xmJdEntity.setJdljzxjd(xmZxEntity.getLjzxjd());
        }
        map.put("xmxx", xmXxEntity);

        // 查询项目附件
        List<Map> sxfjList = iXmfjService.getXmsxfjByXmid(xmid);
        List<Map> yscfjList = iXmfjService.getXmyscfjByXmid(xmid);
        if (AtlpUtil.isEmpty(sxfjList)) {
            logger.debug("查询项目所需附件信息失败...项目id==={}", xmid);
            throw new BusinessException(ExceptionEnum.ERROR.getCode(), "查询项目所需附件失败.");
        }
        map.put("sxfj", sxfjList);
        map.put("yscfj", yscfjList);

        return map;
    }

    /**
     * 查询项目阶段进度信息
     * @param zxid
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getXmzxInfo/{zxid}")
    @ResponseBody
    public Map<String, Object> getXmzxInfo(@PathVariable(name = "zxid", required = true) String zxid)
            throws BusinessException {
        Map<String, Object> map = new HashMap<>();

        // 查询项目阶段执行信息
        JzfpBXmZxEntity xmZxEntity = iXmzxService.getInfoByKey(zxid);
        map.put("xmzx", xmZxEntity);

        // 项目阶段list
        List<JzfpBXmJdEntity> xmJdEntityList = iXmjdService.getListByXmid(xmZxEntity.getXmid());
        map.put("xmjd", xmJdEntityList);

        // 进度上报支撑材料
        List<Map> fjList = iXmfjService.getZxjdyscfjByZxid(xmZxEntity.getZxid());
        map.put("jdcl", fjList);

        return map;
    }

    /**
     * 计算项目累计完成率
     * @param xmid  项目id
     * @param jdid  进度id
     * @param jdljwcl   进度累计完成率
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/workProjectStageTotalCompleteRate/{xmid}/{jdid}/{jdljwcl}")
    @ResponseBody
    public Double workProjectStageTotalCompleteRate(@PathVariable(name = "xmid", required = true) String xmid,
            @PathVariable(name = "jdid", required = true) String jdid,
            @PathVariable(name = "jdljwcl", required = true) Double jdljwcl) throws BusinessException {
        return iXmzxService.workProjectStageTotalCompleteRate(xmid, jdid, jdljwcl);
    }

    /**
     * 保存
     * @param request
     * @param entity
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public Boolean doSave(HttpServletRequest request, @RequestBody JzfpBXmZxEntity entity) throws BusinessException {
        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getXmid()) || AtlpUtil.isEmpty(entity.getJdid())) {
            logger.debug("传入执行情况信息不完整...执行信息==={}", entity.toString());
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "传入执行情况信息不完整.");
        }

        return iXmzxService.doSaveOrUpdate(entity, request);
    }

    /**
     * 保存
     * @param request
     * @param entity
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Boolean doUpdate(HttpServletRequest request, @RequestBody JzfpBXmZxEntity entity) throws BusinessException {
        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getZxid())) {
            logger.debug("传入执行情况信息不完整...执行信息==={}", entity.toString());
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "传入执行情况信息不完整.");
        }

        return iXmzxService.doSaveOrUpdate(entity, request);
    }

    /**
     * 查询项目所需附件list
     * @param xmid
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getXmsxfjList/{xmid}")
    @ResponseBody
    public List<Map> getXmsxfjList(@PathVariable(name = "xmid", required = true) String xmid)
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
    public List<Map> getXmyscfjList(@PathVariable(name = "xmid", required = true) String xmid)
            throws BusinessException {
        return iXmfjService.getXmyscfjByXmid(xmid);
    }



}
