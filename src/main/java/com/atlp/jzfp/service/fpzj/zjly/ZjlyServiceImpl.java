package com.atlp.jzfp.service.fpzj.zjly;

import com.atlp.jzfp.entity.fpzj.JzfpBZjLyEntity;
import com.atlp.jzfp.repository.fpzj.FpzjZjlyRepository;
import lombok.extern.slf4j.Slf4j;
import org.atlp.data.PageModel;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/9 13:45
 * @Decription: 资金来源业务层实现类
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ZjlyServiceImpl implements IZjlyService {


    @Autowired
    private FpzjZjlyRepository zjlyRepository;

    /**
     * 资金来源分页数据展示
     */
    @Override
    public PageModel getPage(PageModel page) {
        String sql = "select t.lymc,t.sm,t.yhid,t.lasttime from JZFP_B_ZJ_LY t order by t.xssx";
        return zjlyRepository.findPageBySql(sql, page);
    }

    /**
     * 增加资金来源信息
     */
    @Override
    @Transactional
    public Boolean doSaveOrUpdate(JzfpBZjLyEntity entity) {
        JzfpBZjLyEntity saveEntity = new JzfpBZjLyEntity();
        //判断主键ID
        if (null == entity.getLyid()) {
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");
            saveEntity.setYhid("123a");
            saveEntity.setYhxm("aaaaaa");
            saveEntity.setYhdwid("321b");
            saveEntity.setYhdwmc("bbbbbb");
        } else {
            saveEntity = zjlyRepository.findByLyid(entity.getLyid());
            if (AtlpUtil.isEmpty(saveEntity)) {
                log.debug("参数异常，查询资金来源信息失败...资金来源id==={}", entity.getLyid());
                throw new BusinessException(4201, "传入资金来源信息不完整，查询资金来源信息失败");
            }
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
        }
        JzfpBZjLyEntity save = zjlyRepository.save(saveEntity);
        //判断增加或修改是否成功
        if (null == save || null == save.getLyid()) {
            log.debug("资金来源增加或修改失败...资金来源信息==={}", entity.getLyid());
            throw new BusinessException(4202, "资金来源增加或修改失败...");
        }
        return true;
    }

    /**
     * 资金来源信息修改
     * 状态修改
     */
    @Override
    @Transactional
    public Boolean doUpdate(JzfpBZjLyEntity entity) {
        JzfpBZjLyEntity updateEntity = zjlyRepository.findByLyid(entity.getLyid());
        if (AtlpUtil.isEmpty(updateEntity)) {
            log.debug("参数异常，查询资金来源信息失败...资金来源id==={}", entity.getLyid());
            throw new BusinessException(4201, "查询资金来源信息失败");
        }
        BeanUtils.copyProperties(entity, updateEntity, AtlpUtil.getNullPropertyNames(entity));
        updateEntity.setLasttime(new Timestamp(new Date().getTime()));
        JzfpBZjLyEntity update = zjlyRepository.save(updateEntity);
        //判断修改是否成功
        if (AtlpUtil.isEmpty(update) || AtlpUtil.isEmpty(update.getLyid())) {
            log.debug("参数异常,资金来源修改失败...资金来源信息==={}", entity.getLyid());
            throw new BusinessException(4202, "资金来源修改失败");
        }
        return true;
    }

    /**
     * 删除对应的资金来源信息
     */
    @Override
    public Boolean doDelete(String lyid) {
        zjlyRepository.delete(zjlyRepository.findByLyid(lyid));
        return true;
    }
}
