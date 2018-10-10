package com.atlp.jzfp.service.fpxm.xmzl;

import com.atlp.jzfp.common.utils.AtlpUtil;
import com.atlp.jzfp.entity.fpxm.JzfpBXmFjEntity;
import com.atlp.jzfp.entity.fpxm.JzfpBXmZlEntity;
import com.atlp.jzfp.repository.fpxm.FpxmXmZlRepository;
import com.atlp.jzfp.service.fpxm.xmfj.IXmfjService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-08 17:25
 * @Decription:
 */
@Service
@Transactional
public class XmzlServiceImpl implements IXmzlService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FpxmXmZlRepository xmZlRepository;
    @Autowired
    private IXmfjService iXmfjService;

    @Override
    public Map<String, Object> doSave(JzfpBXmZlEntity entity) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        try {
            // 判断项目附件是否正常
            if (AtlpUtil.isEmpty(entity.getXmFjEntity())) {
                logger.debug("项目资料附件为空，添加项目分类资料失败...项目分类资料信息==={}",entity.toString());
                reMap.put("code", "-1");
                reMap.put("msg", "系统异常，添加项目分类资料失败.");
                return reMap;
            }

            JzfpBXmZlEntity saveEntity = new JzfpBXmZlEntity();
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            // AtlpUtil.setUserInfo(saveEntity, request);
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");

            // 执行数据库事务
            JzfpBXmZlEntity save = xmZlRepository.save(saveEntity);
            if (null == save || null == save.getZlid()) {
                logger.debug("添加项目分类资料失败...原项目分类资料信息==={}，新项目分类资料信息==={}",
                        saveEntity.toString(), save.toString());
                reMap.put("code", "-2");
                reMap.put("msg", "系统异常，添加项目分类资料失败.");
                return reMap;
            }

            // 项目资料添加成功，添加资料附件
            JzfpBXmFjEntity xmFjEntity = entity.getXmFjEntity();
            xmFjEntity.setZlid(save.getZlid());
            iXmfjService.doSave(xmFjEntity);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception();
        }

        return reMap;
    }

    @Override
    public Map<String, Object> doDelete(JzfpBXmZlEntity entity) throws Exception {
        return null;
    }
}
