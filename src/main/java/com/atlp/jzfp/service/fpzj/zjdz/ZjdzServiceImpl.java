package com.atlp.jzfp.service.fpzj.zjdz;

import org.atlp.data.PageModel;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import com.atlp.jzfp.entity.fpzj.JzfpBZjDzEntity;
import com.atlp.jzfp.entity.fpzj.JzfpBZjLyEntity;
import com.atlp.jzfp.repository.fpzj.FpzjZjdzRepository;
import com.atlp.jzfp.repository.fpzj.FpzjZjlyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
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
     */
    @Override
    public PageModel getPage(PageModel page) {
        /*StringBuilder sql = new StringBuilder("select t.nd,t.zjlx,t.lymc,t.dzsj,t.sjxbsj,t.dzje from JZFP_B_ZJ_DZ t where 0=0 ");
        Map pmap = page.getPmap();
        if (pmap != null && pmap.size() > 0) {
            for (Object key : pmap.keySet()) {
                sql.append(" and ").append((String) key).append(" = ").append((String) pmap.get(key));
            }
        }
        sql.append(" order by ");*/
        String sql = "select t.nd,t.zjlx,t.lymc,t.dzsj,t.sjxbsj,t.dzje from JZFP_B_ZJ_DZ t ";
        page = zjdzRepository.findPageBySql(sql, page);
        return page;
    }

    /**
     * 资金到账信息详细查看
     */
    @Override
    public JzfpBZjDzEntity getZjdzById(String dzid) {
        JzfpBZjDzEntity zjDzEntity = zjdzRepository.findByDzid(dzid);
        if (null == zjDzEntity) {
            throw new BusinessException(4201, "参数异常，查询资金到账信息失败...到账id==={}");
        }
        return zjDzEntity;
    }

    /**
     * 增加或修改资金到账信息
     */
    @Override
    @Transactional
    public Boolean doSaveOrUpdate(JzfpBZjDzEntity entity) {
        JzfpBZjDzEntity saveEntity = new JzfpBZjDzEntity();
        //设置资金来源
        JzfpBZjLyEntity zjlyEntity = zjlyRepository.findByLyid(entity.getLyid());
        if (AtlpUtil.isEmpty(zjlyEntity) || AtlpUtil.isEmpty(zjlyEntity.getLymc())) {
            throw new BusinessException(4201, "查询资金来源信息失败...资金来源id==={}");
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
                throw new BusinessException(4201, "查询资金到账信息失败...资金到账id==={}");
            }
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
        }
        JzfpBZjDzEntity save = zjdzRepository.save(saveEntity);
        //判断增加或修改成功
        if (null == save || null == save.getDzid()) {
            throw new BusinessException(4202, "增加或修改资金到账信息失败...");
        }
        return true;
    }

    /**
     * 删除资金到账数据信息
     */
    @Override
    @Transactional
    public Boolean doDelete(String dzid) {
        zjdzRepository.delete(zjdzRepository.findByDzid(dzid));
        return true;
    }
}
