package com.atlp.jzfp.service.fpzj.zjfj;

import com.atlp.jzfp.entity.fpzj.JzfpBZjFjEntity;
import com.atlp.jzfp.repository.fpzj.FpzjZjfjRepository;
import lombok.extern.slf4j.Slf4j;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/12 16:16
 * @Decription: 资金附件业务层实现类
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ZjfjServiceImpl implements IZjfjService {
    @Autowired
    private FpzjZjfjRepository zjfjRepository;

    /**
     * 添加资金附件
     */
    @Override
    @Transactional
    public void doSave(JzfpBZjFjEntity entity) {
        JzfpBZjFjEntity saveEntity = new JzfpBZjFjEntity();
        BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
        saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
        saveEntity.setLasttime(new Timestamp(new Date().getTime()));
        saveEntity.setDqzt("有效");
        saveEntity.setYhid("123a123");
        saveEntity.setYhxm("aaaaaa");
        saveEntity.setYhdwid("321b123");
        saveEntity.setYhdwmc("bbbbbb");
        JzfpBZjFjEntity save = zjfjRepository.save(saveEntity);
        if (AtlpUtil.isEmpty(save)) {
            log.debug("添加资金附件失败...", save.toString());
            throw new BusinessException(4202, "添加附件信息失败");
        }
    }

    /**
     * 通过资金附件id 删除资金附件
     */
    @Override
    @Transactional
    public Boolean doDelete(String fjid) {
        JzfpBZjFjEntity zjfjEntity = zjfjRepository.findByFjid(fjid);
        if (AtlpUtil.isEmpty(zjfjEntity) || AtlpUtil.isEmpty(zjfjEntity.getFjid())) {
            log.debug("参数异常，删除资金附件失败...", fjid);
            throw new BusinessException(4202, "删除资金附件失败");
        }
        zjfjRepository.delete(zjfjEntity);
        return true;
    }

    /**
     * 删除对应登记id 的资金附件
     */
    @Override
    public Boolean doDeleteBydjid(String djid) {
        List<JzfpBZjFjEntity> zjFjEntityList = zjfjRepository.findByDjid(djid);
        for (JzfpBZjFjEntity zjFjEntity : zjFjEntityList) {
            zjfjRepository.delete(zjFjEntity);
        }
        return true;
    }

    /**
     * 查询对应登记id 的资金附件
     */
    @Override
    public List<JzfpBZjFjEntity> getZjfjByDjid(String djid) {
        List<JzfpBZjFjEntity> zjFjEntityList = zjfjRepository.findByDjid(djid);
        return zjFjEntityList;
    }

}
