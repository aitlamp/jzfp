package com.atlp.jzfp.service.fpxm.xmjd;

import com.atlp.jzfp.entity.fpxm.JzfpBXmJdEntity;
import com.atlp.jzfp.repository.fpxm.FpxmXmjdRepository;
import lombok.extern.slf4j.Slf4j;
import org.atlp.data.ExceptionEnum;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-10 18:29
 * @Decription:
 */
@Slf4j
@Service
@Transactional
public class XmjdServiceImpl implements IXmjdService {

    @Autowired
    private FpxmXmjdRepository xmjdRepository;

    @Override
    public JzfpBXmJdEntity getInfoById(String id) throws BusinessException {
        return xmjdRepository.findByJdid(id);
    }

    @Override
    public boolean doSaveOrUpdate(JzfpBXmJdEntity entity, HttpServletRequest request) throws BusinessException {
        JzfpBXmJdEntity saveEntity = new JzfpBXmJdEntity();
        // 判断主键id，增加或是修改
        if (AtlpUtil.isEmpty(entity.getJdid())) {
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            // AtlpUtil.setUserInfo(saveEntity, request);
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");
        } else {
            // 查询原项目信息是否存在
            saveEntity = xmjdRepository.findByJdid(entity.getJdid());
            if (AtlpUtil.isEmpty(saveEntity)) {
                log.debug("参数异常，查询项目阶段信息失败...阶段信息id==={}", entity.getXmid());
                throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "参数异常，查询项目阶段信息失败.");
            }
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
        }

        // 数据保存
        JzfpBXmJdEntity save = xmjdRepository.save(saveEntity);
        if (null == save || null == save.getJdid()) {
            log.debug("添加或修改项目阶段信息失败...原项目阶段信息==={}，新项目阶段信息==={}",
                    saveEntity.toString(), save.toString());
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "参数异常，维护项目阶段信息失败.");
        }

        return true;
    }

    @Override
    public boolean doDelete(JzfpBXmJdEntity entity) throws BusinessException {
        xmjdRepository.delete(entity);

        return true;
    }
}
