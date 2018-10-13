package com.atlp.jzfp.service.fpxm.xmjd;

import com.atlp.jzfp.common.utils.AtlpUtil;
import com.atlp.jzfp.entity.fpxm.JzfpBXmJdEntity;
import com.atlp.jzfp.repository.fpxm.FpxmXmjdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    public JzfpBXmJdEntity getInfoById(String id) throws Exception {
        return xmjdRepository.findByJdid(id);
    }

    @Override
    public Map<String, Object> doSaveOrUpdate(JzfpBXmJdEntity entity, HttpServletRequest request) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        try {
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
                    reMap.put("code", "-1");
                    reMap.put("msg", "系统异常，查询项目阶段信息失败.");
                    return reMap;
                }
                BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
                saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            }

            // 数据保存
            JzfpBXmJdEntity save = xmjdRepository.save(saveEntity);
            if (null == save || null == save.getJdid()) {
                log.debug("添加或修改项目阶段信息失败...原项目阶段信息==={}，新项目阶段信息==={}",
                        saveEntity.toString(), save.toString());
                reMap.put("code", "-2");
                reMap.put("msg", "系统异常，维护项目阶段信息失败.");
                return reMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception();
        }

        return reMap;
    }

    @Override
    public Map<String, Object> doDelete(JzfpBXmJdEntity entity) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        try {
            xmjdRepository.delete(entity);
        } catch (Exception e) {
            log.debug("删除项目阶段失败...项目阶段信息==={}", entity.toString());
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            reMap.put("code", "-1");
            reMap.put("msg", "删除项目阶段失败.");
            return reMap;
        }

        return reMap;
    }
}
