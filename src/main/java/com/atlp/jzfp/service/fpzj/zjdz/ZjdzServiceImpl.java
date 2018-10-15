package com.atlp.jzfp.service.fpzj.zjdz;

import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.common.utils.AtlpUtil;
import com.atlp.jzfp.entity.fpzj.JzfpBZjDzEntity;
import com.atlp.jzfp.entity.fpzj.JzfpBZjLyEntity;
import com.atlp.jzfp.repository.fpzj.FpzjZjdzRepository;
import com.atlp.jzfp.repository.fpzj.FpzjZjlyRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/10 11:27
 * @Decription: 资金到账业务层实现类
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ZjdzServiceImpl implements IZjdzService {

    @Autowired
    private FpzjZjdzRepository zjdzRepository;
    @Autowired
    private FpzjZjlyRepository zjlyRepository;

    /**
     * 资金到账分页数据展示
     *
     * @param page
     * @return
     */
    @Override
    public Map<String, Object> getPage(PageModel page) {
        Map retMap = new HashMap();
        retMap.put("code", "0");
        retMap.put("msg", "成功");
        String sql = "select t.nd,t.zjlx,t.lymc,t.dzsj,t.sjxbsj,t.dzje from JZFP_B_ZJ_DZ t ";
        page = zjdzRepository.findPageBySql(sql, page);
        retMap.put("data", page);
        return retMap;
    }

    /**
     * 资金到账信息详细查看
     *
     * @param dzid
     * @return
     */
    @Override
    public Map<String, Object> getZjdzById(String dzid) {
        Map retMap = new HashMap();
        retMap.put("code", "0");
        retMap.put("msg", "成功");

        JzfpBZjDzEntity zjDzEntity = zjdzRepository.findByDzid(dzid);
        if (null == zjDzEntity) {
            log.debug("参数异常，查询资金到账信息失败...资金到账id==={}", zjDzEntity.getDzid());
            retMap.put("code", "-1");
            retMap.put("msg", "系统异常，查询资金到账信息失败");
            return retMap;
        }
        retMap.put("data", zjDzEntity);
        return retMap;
    }

    /**
     * 增加或修改资金到账信息
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> doSaveOrUpdate(JzfpBZjDzEntity entity) {
        Map retMap = new HashMap();
        retMap.put("code", "0");
        retMap.put("msg", "成功");

        JzfpBZjDzEntity saveEntity = new JzfpBZjDzEntity();

        JzfpBZjLyEntity zjlyEntity = zjlyRepository.findByLyid(entity.getLyid());
        if (AtlpUtil.isEmpty(zjlyEntity) || AtlpUtil.isEmpty(zjlyEntity.getLymc())) {
            log.debug("参数异常，查询资金来源信息失败...资金来源名称==={}", zjlyEntity.getLymc());
            retMap.put("code", "-1");
            retMap.put("msg", "系统异常，资金来源信息输入有误.");
            return retMap;
        }
        entity.setLymc(zjlyEntity.getLymc());
        //判断主键ID
        if (null == entity.getDzid()) {
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            saveEntity.setYd("04");
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");
            saveEntity.setYhid("123a");
            saveEntity.setYhxm("aaaaaa");
            saveEntity.setYhdwid("321b");
            saveEntity.setYhdwmc("bbbbbb");
        } else {
            saveEntity = zjdzRepository.findByDzid(entity.getDzid());
            if (AtlpUtil.isEmpty(saveEntity)) {
                log.debug("参数异常，查询资金到账信息失败...资金到账id==={}", entity.getDzid());
                retMap.put("code", "-1");
                retMap.put("msg", "系统异常，查询资金到账信息失败.");
                return retMap;
            }
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
        }
        JzfpBZjDzEntity save = zjdzRepository.save(saveEntity);
        //判断增加或修改成功
        if (null == save || null == save.getDzid()) {
            log.debug("参数异常，增加或修改资金到账信息失败...资金到账id==={}", entity.getDzid());
            retMap.put("code", "-2");
            retMap.put("msg", "系统异常,增加或修改资金到账信息失败");
            return retMap;
        }

        return retMap;
    }

    /**
     * 删除资金到账数据信息
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> doDelete(JzfpBZjDzEntity entity) {
        Map retMap = new HashMap();
        retMap.put("code", "0");
        retMap.put("msg", "成功");

        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getDzid())) {
            log.debug("参数异常，删除资金到账信息失败...资金到账id==={}", entity.getDzid());
            retMap.put("code", "-2");
            retMap.put("msg", "系统异常,删除资金到账信息失败");
            return retMap;
        }
        zjdzRepository.delete(entity);
        return retMap;
    }

}
