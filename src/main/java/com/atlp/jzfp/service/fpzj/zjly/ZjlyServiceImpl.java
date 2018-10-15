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
     *
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public PageModel getPage(PageModel page) throws Exception {

        String sql = "select t.lymc,t.sm,t.yhid,t.lasttime from JZFP_B_ZJ_LY t order by t.xssx";
        String[][] columns = {{"lymc", "来源说明", "80"},
                {"sm", "说明", "100"}, {"yhxm", "维护人", "80"},
                {"lasttime", "维护时间", "80"}};
        page = zjlyRepository.findPageBySql(sql, page, columns);

        return page;
    }

    /**
     * 增加资金来源信息
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Boolean doSaveOrUpdate(JzfpBZjLyEntity entity) throws BusinessException {
        Boolean ret = true;

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
                ret = false;
                throw new BusinessException(4201, "传入资金来源信息不完整，查询资金来源信息失败");
            }
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
        }
        JzfpBZjLyEntity save = zjlyRepository.save(saveEntity);
        //判断增加或修改是否成功
        if (null == save || null == save.getLyid()) {
            log.debug("资金来源增加或修改失败...资金来源信息==={}", entity.getLyid());
            ret = false;
            throw new BusinessException(4202, "资金来源增加或修改失败...");
        }

        return ret;
    }

    /**
     * 资金来源信息修改
     * 状态修改
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Boolean doUpdate(JzfpBZjLyEntity entity) throws Exception {
        Boolean ret = true;
        JzfpBZjLyEntity updateEntity = zjlyRepository.findByLyid(entity.getLyid());
        if (AtlpUtil.isEmpty(updateEntity)) {
            log.debug("参数异常，查询资金来源信息失败...资金来源id==={}", entity.getLyid());
            ret = false;
            throw new BusinessException(4201, "查询资金来源信息失败");
        }
        BeanUtils.copyProperties(entity, updateEntity, AtlpUtil.getNullPropertyNames(entity));
        updateEntity.setLasttime(new Timestamp(new Date().getTime()));

        JzfpBZjLyEntity update = zjlyRepository.save(updateEntity);
        //判断修改是否成功
        if (AtlpUtil.isEmpty(update) || AtlpUtil.isEmpty(update.getLyid())) {
            log.debug("参数异常,资金来源修改失败...资金来源信息==={}", entity.getLyid());
            ret = false;
            throw new BusinessException(4202, "资金来源修改失败");
        }
        return ret;
    }

    /**
     * 删除对应的资金来源信息
     *
     * @param entity
     * @return
     */
    @Override
    public Boolean doDelete(JzfpBZjLyEntity entity) {
        Boolean ret = true;
        JzfpBZjLyEntity deleteEntity = zjlyRepository.findByLyid(entity.getLyid());
        if (AtlpUtil.isEmpty(deleteEntity) || AtlpUtil.isEmpty(deleteEntity.getLyid())) {
            log.debug("参数异常，资金来源删除失败...资金来源ID==={}", entity.getLyid());
            throw new BusinessException(4202, "资金来源删除失败...资金来源ID==={}");
        }
        zjlyRepository.delete(deleteEntity);
        return ret;
    }
}
