package com.atlp.jzfp.service.fpzj.zjxb;

import com.atlp.jzfp.entity.fpzj.JzfpBZjXbEntity;
import com.atlp.jzfp.entity.zzjg.JzfpBZzjgDwEntity;
import com.atlp.jzfp.repository.fpzj.FpzjZjxbRepository;
import com.atlp.jzfp.repository.zzjg.ZzjgDwRepository;
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
     */
    @Override
    public PageModel getPage(PageModel page) {
        String sql = "select t.nd,t.zjlx,t.xbsj,t.xbje,t.pc,t.jsdwmc from JZFP_B_ZJ_XB t";
        return zjxbRepository.findPageBySql(sql, page);
    }

    /**
     * 新增或修改资金下拨数据信息
     */
    @Override
    @Transactional
    public Boolean doSaveOrUpdate(JzfpBZjXbEntity entity) {
        //设置接收单位、下拨单位
        JzfpBZzjgDwEntity zzjgDwEntity = zzjgDwRepository.findByDwid(entity.getJsdwid());
        if (entity.getJsdwmc() != null || !entity.getJsdwmc().equals(zzjgDwEntity.getDwmc())) {
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
                throw new BusinessException(4201, "查询资金到账信息失败");
            }
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
        }
        JzfpBZjXbEntity save = zjxbRepository.save(saveEntity);
        if (null == save || null == save.getDzid()) {
            throw new BusinessException(4202, "新增或修改资金下拨信息失败");
        }
        return true;
    }

    /**
     * 查询对应的资金下拨详细数据信息
     */
    @Override
    public JzfpBZjXbEntity getZjxbById(String dzid) {
        JzfpBZjXbEntity zjxbEntity = zjxbRepository.findByDzid(dzid);
        if (AtlpUtil.isEmpty(zjxbEntity)) {
            throw new BusinessException(4201, "查询资金下拨详细信息失败");
        }
        return zjxbEntity;
    }

    /**
     * 删除对应的资金下拨数据信息
     */
    @Override
    @Transactional
    public Boolean doDelete(String dzid) {
        zjxbRepository.delete(zjxbRepository.findByDzid(dzid));
        return true;
    }
}
