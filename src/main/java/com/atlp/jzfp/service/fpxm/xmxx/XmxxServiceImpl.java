package com.atlp.jzfp.service.fpxm.xmxx;

import com.atlp.jzfp.entity.fpxm.JzfpBXmJdEntity;
import com.atlp.jzfp.entity.fpxm.JzfpBXmXxEntity;
import com.atlp.jzfp.repository.fpxm.FpxmXmjdRepository;
import com.atlp.jzfp.repository.fpxm.FpxmXmxxRepository;
import com.atlp.jzfp.service.fpxm.xmjd.IXmjdService;
import lombok.extern.slf4j.Slf4j;
import org.atlp.data.ExceptionEnum;
import org.atlp.data.PageModel;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-10 16:39
 * @Decription:
 */
@Slf4j
@Service
@Transactional
public class XmxxServiceImpl implements IXmxxService {

    @Autowired
    private FpxmXmxxRepository xmxxRepository;
    @Autowired
    private FpxmXmjdRepository xmjdRepository;
    @Autowired
    private IXmjdService iXmjdService;

    @Override
    public PageModel getPage(PageModel page) throws BusinessException {
        // 查询语句
        String sql = "select t.xmid, t.xmjc, t.szx, t.szxz, t.dlmc, t.xlmc, t.kssj, t.jssj, t.zrrmc, t.ysje " +
                " from jzfp_b_xm_xx t ";
        page = xmxxRepository.findPageBySql(sql, page);

        // 查询list无数据
        if (AtlpUtil.isEmpty(page.getRows())) {
            log.debug("项目信息空空如也...资料信息==={}", page.toString());
            throw new BusinessException(ExceptionEnum.ERROR.getCode(), "项目信息空空如也.");
        }

        return page;
    }

    @Override
    public JzfpBXmXxEntity getInfoByKey(String key) throws BusinessException {
        // 查询项目信息
        JzfpBXmXxEntity xmXxEntity = xmxxRepository.findByXmid(key);
        if (AtlpUtil.isEmpty(xmXxEntity)) {
            log.debug("查询项目信息失败...项目id==={}", key);
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "查询项目信息失败.");
        }

        // 查询项目阶段
        List<JzfpBXmJdEntity> xmJdEntityList = xmjdRepository.findAllByXmid(key);
        if (AtlpUtil.isEmpty(xmJdEntityList)) {
            log.debug("查询项目阶段信息失败...项目id==={}", key);
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "查询项目阶段信息失败.");
        } else {
            xmXxEntity.setXmJdEntityList(xmJdEntityList);
        }

        return xmXxEntity;
    }

    @Override
    public boolean doSaveOrUpdate(JzfpBXmXxEntity entity, HttpServletRequest request) throws BusinessException {
        JzfpBXmXxEntity saveEntity = new JzfpBXmXxEntity();
        // 判断主键id，增加或是修改
        if (AtlpUtil.isEmpty(entity.getXmid())) {
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            // AtlpUtil.setUserInfo(saveEntity, request);
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");
        } else {
            // 查询原项目信息是否存在
            saveEntity = xmxxRepository.findByXmid(entity.getXmid());
            if (AtlpUtil.isEmpty(saveEntity)) {
                log.debug("参数异常，查询项目信息失败...项目信息id==={}", entity.getXmid());
                throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "参数异常，查询项目信息失败.");
            }
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
        }

        // 数据保存
        JzfpBXmXxEntity save = xmxxRepository.save(saveEntity);
        if (null == save || null == save.getXmid()) {
            log.debug("添加或修改项目信息失败...原项目信息信息==={}，新项目信息信息==={}",
                    saveEntity.toString(), save.toString());
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "参数异常，维护项目信息失败.");
        }

        // 项目阶段
        List<JzfpBXmJdEntity> entityList = entity.getXmJdEntityList();
        for (JzfpBXmJdEntity xmJdEntity : entityList) {
            // 项目阶段主键id是否存在
            if (AtlpUtil.isEmpty(xmJdEntity.getJdid())) {
                // 新添加项目阶段，项目id数据填充
                xmJdEntity.setXmid(save.getXmid());
            }
            iXmjdService.doSaveOrUpdate(xmJdEntity, request);
        }

        return true;
    }
}
