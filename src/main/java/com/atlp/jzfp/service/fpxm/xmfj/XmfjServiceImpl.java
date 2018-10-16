package com.atlp.jzfp.service.fpxm.xmfj;

import org.atlp.data.ExceptionEnum;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
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
import java.util.HashMap;
import java.util.List;
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
    public JzfpBXmFjEntity getInfoById(String id) throws Exception {
        return xmfjRepository.findByFjid(id);
    }

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
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        try {
            xmfjRepository.delete(entity);
        } catch (Exception e) {
            logger.debug("删除项目附件失败...项目附件信息==={}", entity.toString());
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            reMap.put("code", "-1");
            reMap.put("msg", "删除项目附件失败.");
            return reMap;
        }

        return reMap;
    }

    @Override
    public List<JzfpBXmFjEntity> getXmsxfjByXmid(String xmid) throws BusinessException {
        // sql
        String sql = "select fj.fjid,fj.file_name from jzfp_b_xm_fj fj " +
                " where fj.zlid in (select zl.zlid from jzfp_b_xm_zl zl " +
                    " where zl.flid in (select xx.dlid from jzfp_b_xm_xx xx where xx.xmid = '"+ xmid +"' ))";
        List<JzfpBXmFjEntity> xmFjEntityList = xmfjRepository.findAllBySql(sql, JzfpBXmFjEntity.class);

        if (AtlpUtil.isEmpty(xmFjEntityList)) {
            logger.debug("查询项目附件失败...项目id==={}", xmid);
            throw new BusinessException(ExceptionEnum.ERROR.getCode(), "查询项目附件失败.");
        }

        return xmFjEntityList;
    }

    @Override
    public List<JzfpBXmFjEntity> getXmyscfjByXmid(String xmid) throws BusinessException {
        // sql
        String sql = "select fj.fjid,fj.file_name from jzfp_b_xm_fj fj where fj.xmid = '"+ xmid +"' ))";
        List<JzfpBXmFjEntity> xmFjEntityList = xmfjRepository.findAllBySql(sql, JzfpBXmFjEntity.class);

        if (AtlpUtil.isEmpty(xmFjEntityList)) {
            logger.debug("查询项目附件失败...项目id==={}", xmid);
            throw new BusinessException(ExceptionEnum.ERROR.getCode(), "查询项目附件失败.");
        }

        return xmFjEntityList;
    }
}
