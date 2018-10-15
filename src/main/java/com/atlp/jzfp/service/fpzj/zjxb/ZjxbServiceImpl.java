package com.atlp.jzfp.service.fpzj.zjxb;

import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.common.utils.AtlpUtil;
import com.atlp.jzfp.entity.fpzj.JzfpBZjXbEntity;
import com.atlp.jzfp.entity.zzjg.JzfpBZzjgDwEntity;
import com.atlp.jzfp.repository.fpzj.FpzjZjxbRepository;
import com.atlp.jzfp.repository.zzjg.ZzjgDwRepository;
import lombok.extern.slf4j.Slf4j;
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
 * @CreateTime: 2018/10/11 15:55
 * @Decription: 资金下拨业务层实现类
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ZjxbServiceImpl implements IZjxbService {

    @Autowired
    private FpzjZjxbRepository zjxbRepository;
    @Autowired
    private ZzjgDwRepository zzjgDwRepository;

    /**
     * 资金下拨数据信息分页展示
     *
     * @param page
     * @return
     */
    @Override
    public Map<String, Object> getPage(PageModel page) {
        Map retMap = new HashMap();
        retMap.put("code", "0");
        retMap.put("msg", "成功");
        String sql = "select t.nd,t.zjlx,t.xbsj,t.xbje,t.pc,t.jsdwmc from JZFP_B_ZJ_XB t";
        page = zjxbRepository.findPageBySql(sql, page);
        retMap.put("data", page);
        return retMap;
    }

    /**
     * 新增或修改资金下拨数据信息
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> doSaveOrUpdate(JzfpBZjXbEntity entity) {
        Map retMap = new HashMap();
        retMap.put("code", "0");
        retMap.put("msg", "成功");
        //设置接收单位、下拨单位
        JzfpBZzjgDwEntity zzjgDwEntity = zzjgDwRepository.findByDwid(entity.getJsdwid());
        if (entity.getJsdwmc() != null || !zzjgDwEntity.getDwmc().equals(entity.getJsdwmc())) {
            entity.setXbdwid(zzjgDwEntity.getPdwid());
            entity.setXbdwmc(zzjgDwRepository.findByDwid(zzjgDwEntity.getPdwid()).getDwmc());
            entity.setJsdwmc(zzjgDwEntity.getDwmc());
        }

        JzfpBZjXbEntity saveEntity = new JzfpBZjXbEntity();
        //判断主键id
        if (null == entity.getDzid()) {
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            saveEntity.setYd("04");
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");
            saveEntity.setYhid("123a123");
            saveEntity.setYhxm("aaaaaa");
            saveEntity.setYhdwid("321b123");
            saveEntity.setYhdwmc("bbbbbb");
        } else {
            saveEntity = zjxbRepository.findByDzid(entity.getDzid());
            if (AtlpUtil.isEmpty(saveEntity)) {
                log.debug("参数异常，查询资金下拨信息失败...资金下拨id==={}", entity.getDzid());
                retMap.put("code", "-1");
                retMap.put("msg", "系统异常，查询资金到账信息失败.");
                return retMap;
            }
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
        }
        JzfpBZjXbEntity save = zjxbRepository.save(saveEntity);
        if (null == save || null == save.getDzid()) {
            log.debug("参数异常，新增或修改资金下拨信息失败...资金下拨id==={}", entity.getDzid());
            retMap.put("code", "-2");
            retMap.put("msg", "系统异常，新增或修改资金下拨信息失败.");
            return retMap;
        }

        return retMap;
    }

    /**
     * 查询对应的资金下拨详细数据信息
     *
     * @param dzid
     * @return
     */
    @Override
    public Map<String, Object> getZjxbById(String dzid) {
        Map retMap = new HashMap();
        retMap.put("code", "0");
        retMap.put("msg", "成功");
        JzfpBZjXbEntity zjxbEntity = zjxbRepository.findByDzid(dzid);
        if (AtlpUtil.isEmpty(zjxbEntity)) {
            log.debug("参数异常，查询资金下拨详细信息失败...资金下拨id==={}", zjxbEntity.getDzid());
            retMap.put("code", "-1");
            retMap.put("msg", "系统异常，查询资金下拨详细信息失败.");
            return retMap;
        }
        retMap.put("data", zjxbEntity);
        return retMap;
    }

    /**
     * 删除对应的资金下拨数据信息
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> doDelete(JzfpBZjXbEntity entity) {
        Map retMap = new HashMap();
        retMap.put("code", "0");
        retMap.put("msg", "成功");
        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getDzid())) {
            log.debug("参数异常，删除资金下拨信息失败...资金到账id==={}", entity.getDzid());
            retMap.put("code", "-2");
            retMap.put("msg", "系统异常,删除资金下拨信息失败");
            return retMap;
        }
        zjxbRepository.delete(entity);
        return retMap;
    }
}
