package com.atlp.jzfp.controller.fpxm.xmxx;

import org.atlp.base.BaseController;
import com.atlp.jzfp.common.base.IStaticInfo;
import org.atlp.utils.AtlpUtil;
import org.atlp.utils.DateTimeUtil;
import com.atlp.jzfp.entity.fpxm.JzfpBXmJdEntity;
import com.atlp.jzfp.entity.fpxm.JzfpBXmXxEntity;
import com.atlp.jzfp.service.fpxm.xmxx.IXmxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-13 15:36
 * @Decription: 项目信息控制层
 */
@Controller
@RequestMapping(value = "/fpxm/xmxx", method = {RequestMethod.POST, RequestMethod.GET})
public class XmxxController extends BaseController {

    @Autowired
    private IXmxxService iXmxxService;

    /**
     * 新增项目
     * @param request
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doSave(HttpServletRequest request, @RequestBody JzfpBXmXxEntity entity) throws Exception {
        Map<String, Object> reMap = new HashMap<>();

        // 确保项目信息数据完整
        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getXmmc()) || AtlpUtil.isEmpty(entity.getXmjc())
            || AtlpUtil.isEmpty(entity.getDlid()) || AtlpUtil.isEmpty(entity.getXlid())) {
            logger.debug("传入项目信息不完整，增加项目信息失败...项目信息==={}", entity.toString());
            reMap.put("code", "-1");
            reMap.put("msg", "传入项目信息不完整，增加项目信息失败.");
            return reMap;
        }

        // 项目信息计划日期与阶段日期比较
        // 阶段日期必须在项目日期之前，至少一个阶段开始日期=项目开始日期，至少一个阶段结束日期=项目结束日期
        List<JzfpBXmJdEntity> xmJdList = entity.getXmJdEntityList();
        if (AtlpUtil.isEmpty(xmJdList)) {
            logger.debug("传入项目阶段信息为空，增加项目信息失败...项目信息==={}", entity.toString());
            reMap.put("code", "-2");
            reMap.put("msg", "传入项目阶段信息为空，增加项目信息失败.");
            return reMap;
        }
        Date xmkssj = DateTimeUtil.timestamp2Date(entity.getKssj());
        Date xmjssj = DateTimeUtil.timestamp2Date(entity.getJssj());
        int ksnum = 0, jsnum = 0;
        for (JzfpBXmJdEntity xmJdEntity : xmJdList) {
            Date jdkssj = DateTimeUtil.timestamp2Date(xmJdEntity.getKssj());
            Date jdjssj = DateTimeUtil.timestamp2Date(xmJdEntity.getJssj());
            // 开始日期比较
            if (DateTimeUtil.getDateBetween(xmkssj, jdkssj) < 0) {
                logger.debug("项目阶段开始日期低于项目计划开始日期，增加项目信息失败...项目信息==={}", entity.toString());
                reMap.put("code", "-2");
                reMap.put("msg", "项目阶段日期与项目计划日期不匹配，增加项目信息失败.");
                return reMap;
            } else {
                if (DateTimeUtil.getDateBetween(xmkssj, jdkssj) == 0) {
                    ksnum ++;
                }
            }
            // 结束日期比较
            if (DateTimeUtil.getDateBetween(jdjssj, xmjssj) < 0) {
                logger.debug("项目阶段结束日期低于项目计划结束日期，增加项目信息失败...项目信息==={}", entity.toString());
                reMap.put("code", "-2");
                reMap.put("msg", "项目阶段日期与项目计划日期不匹配，增加项目信息失败.");
                return reMap;
            } else {
                if (DateTimeUtil.getDateBetween(jdjssj, xmjssj) == 0) {
                    jsnum ++;
                }
            }
        }
        if (ksnum == 0 || jsnum == 0) {
            logger.debug("项目阶段日期与项目计划日期不匹配，增加项目信息失败...项目信息==={}", entity.toString());
            reMap.put("code", "-2");
            reMap.put("msg", "项目阶段日期与项目计划日期不匹配，增加项目信息失败.");
            return reMap;
        }

        // 阶段工作量占比总和等于100
        BigDecimal gzlzb = new BigDecimal(IStaticInfo.ZERO);
        for (JzfpBXmJdEntity xmJdEntity : xmJdList) {
            gzlzb = gzlzb.add(new BigDecimal(xmJdEntity.getGzlzb()));
        }
        if (gzlzb.compareTo(new BigDecimal(IStaticInfo.GZLZB)) != 0) {
            logger.debug("项目阶段工作量占比总和不等于100，增加项目信息失败...项目信息==={}", entity.toString());
            reMap.put("code", "-2");
            reMap.put("msg", "项目阶段工作量占比总和不等于100，增加项目信息失败.");
            return reMap;
        }

        return iXmxxService.doSaveOrUpdate(entity, request);
    }

}
