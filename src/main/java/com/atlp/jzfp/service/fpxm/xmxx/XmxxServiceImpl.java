package com.atlp.jzfp.service.fpxm.xmxx;

import com.atlp.jzfp.common.utils.AtlpUtil;
import com.atlp.jzfp.entity.fpxm.JzfpBXmJdEntity;
import com.atlp.jzfp.entity.fpxm.JzfpBXmXxEntity;
import com.atlp.jzfp.repository.fpxm.FpxmXmjdRepository;
import com.atlp.jzfp.repository.fpxm.FpxmXmxxRepository;
import com.atlp.jzfp.service.fpxm.xmjd.IXmjdService;
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
import java.util.List;
import java.util.Map;

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
    public Map<String, Object> doSaveOrUpdate(JzfpBXmXxEntity entity, HttpServletRequest request) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        try {
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
                    reMap.put("code", "-1");
                    reMap.put("msg", "系统异常，查询项目信息失败.");
                    return reMap;
                }
                BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
                saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            }

            // 数据保存
            JzfpBXmXxEntity save = xmxxRepository.save(saveEntity);
            if (null == save || null == save.getXmid()) {
                log.debug("添加或修改项目信息失败...原项目信息信息==={}，新项目信息信息==={}",
                        saveEntity.toString(), save.toString());
                reMap.put("code", "-2");
                reMap.put("msg", "系统异常，维护项目信息失败.");
                return reMap;
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
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception();
        }

        return reMap;
    }
}
