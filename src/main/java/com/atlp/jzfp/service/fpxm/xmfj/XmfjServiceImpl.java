package com.atlp.jzfp.service.fpxm.xmfj;

import com.atlp.jzfp.common.utils.AtlpUtil;
import com.atlp.jzfp.entity.fpxm.JzfpBXmFjEntity;
import com.atlp.jzfp.repository.fpxm.FpxmXmfjRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-09 16:44
 * @Decription: 项目附件业务层实现类
 */
@Service
@Transactional
public class XmfjServiceImpl implements IXmfjService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FpxmXmfjRepository xmfjRepository;

    @Override
    public void doSave(JzfpBXmFjEntity entity) throws Exception {
        try {
            JzfpBXmFjEntity saveEntity = new JzfpBXmFjEntity();

            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            // AtlpUtil.setUserInfo(saveEntity, request);
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");

            // 执行数据库事务
            JzfpBXmFjEntity save = xmfjRepository.save(saveEntity);
            if (null == save || null == save.getZlid()) {
                logger.debug("添加项目分类资料附件失败...原项目分类资料附件信息==={}，新项目分类资料附件信息==={}",
                        saveEntity.toString(), save.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception();
        }
    }

    @Override
    public Map<String, Object> doDelete(JzfpBXmFjEntity entity) throws Exception {
        return null;
    }
}
