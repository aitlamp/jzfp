package com.atlp.jzfp.service.zcxc;

import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.common.utils.AtlpUtil;
import com.atlp.jzfp.entity.zcxc.JzfpBZcxcEntity;
import com.atlp.jzfp.repository.zcxc.ZcxcRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
 * @CreateTime: 2018-10-07 15:34
 * @Decription: 政策宣传业务层接口实现
 */
@Service
@Transactional
public class ZcxcServiceImpl implements IZcxcService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ZcxcRepository zcxcRepository;

    @Override
    public Page<JzfpBZcxcEntity> getPage(PageModel page) throws Exception {
        return zcxcRepository.findAll(PageRequest.of(page.getPage(), page.getLimit(),
                new Sort(Sort.Direction.DESC, "fbrq")));
    }

    @Override
    public List<JzfpBZcxcEntity> getList() throws Exception {
        return zcxcRepository.findAll();
    }

    @Override
    public JzfpBZcxcEntity getInfoById(String key) throws Exception {
        return zcxcRepository.findByXcid(key);
    }

    @Override
    public Map<String, Object> doSaveOrUpdate(JzfpBZcxcEntity entity, HttpServletRequest request) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        try {
            JzfpBZcxcEntity saveEntity = new JzfpBZcxcEntity();
            // 判断主键id，增加或修改
            if (null == entity.getXcid()) {
                BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
                // AtlpUtil.setUserInfo(saveEntity, request);
                saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
                saveEntity.setLasttime(new Timestamp(new Date().getTime()));
                saveEntity.setDqzt("有效");
            } else {
                saveEntity = zcxcRepository.findByXcid(entity.getXcid());
                BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
                saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            }

            // 执行数据库事务
            JzfpBZcxcEntity save = zcxcRepository.save(saveEntity);
            if (null == save || null == save.getXcid()) {
                logger.debug("添加或修改政策宣传失败...政策宣传信息==={}", save.toString());
                reMap.put("code", "-2");
                reMap.put("msg", "系统异常，添加或修改政策宣传失败.");
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
    public Map<String, Object> doDelete(JzfpBZcxcEntity entity) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        try {
            zcxcRepository.delete(entity);
        } catch (Exception e) {
            logger.debug("删除政策宣传失败...政策宣传信息==={}", entity.toString());
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return reMap;
    }
}
