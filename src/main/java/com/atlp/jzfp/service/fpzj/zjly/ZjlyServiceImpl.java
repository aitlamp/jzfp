package com.atlp.jzfp.service.fpzj.zjly;

import org.atlp.data.PageModel;
import org.atlp.utils.AtlpUtil;
import com.atlp.jzfp.entity.fpzj.JzfpBZjLyEntity;
import com.atlp.jzfp.repository.fpzj.FpzjZjlyRepository;
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
 * @CreateTime: 2018/10/9 13:45
 * @Decription: 资金来源业务层实现类
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ZjlyServiceImpl implements IZjlyService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

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
    public Map<String, Object> getPage(PageModel page) throws Exception {
        Map retMap = new HashMap();
        retMap.put("code", "0");
        retMap.put("msg", "成功");
        String sql = "select t.lymc,t.sm,t.yhid,t.lasttime from JZFP_B_ZJ_LY t order by t.xssx";
        String[][] columns = {{"lymc", "来源说明", "80"},
                {"sm", "说明", "100"}, {"yhxm", "维护人", "80"},
                {"lasttime", "维护时间", "80"}};
        page = zjlyRepository.findPageBySql(sql, page, columns);
        retMap.put("data", page);

        return retMap;
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
    public Map<String, Object> doSaveOrUpdate(JzfpBZjLyEntity entity) throws Exception {
        Map retMap = new HashMap();
        retMap.put("code", "0");
        retMap.put("msg", "成功");


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
                logger.debug("参数异常，查询资金来源失败...资金来源id==={}", entity.getLyid());
                retMap.put("code", "-1");
                retMap.put("msg", "系统异常，查询资金来源失败.");
                return retMap;
            }
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
        }
        JzfpBZjLyEntity save = zjlyRepository.save(saveEntity);
        //判断增加或修改是否成功
        if (null == save || null == save.getLyid()) {
            logger.debug("资金来源增加或修改失败...资金来源信息==={}", entity.getLyid());
            retMap.put("code", "-1");
            retMap.put("msg", "系统异常，查询资金来源失败.");
            return retMap;
        }


        return retMap;
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
    public Map<String, Object> doUpdate(JzfpBZjLyEntity entity) throws Exception {
        Map retMap = new HashMap();
        retMap.put("code", "0");
        retMap.put("msg", "成功");

        JzfpBZjLyEntity updateEntity = new JzfpBZjLyEntity();
        updateEntity = zjlyRepository.findByLyid(entity.getLyid());
        if (AtlpUtil.isEmpty(updateEntity)) {
            logger.debug("参数异常，查询资金来源失败...资金来源id==={}", entity.getLyid());
            retMap.put("code", "-1");
            retMap.put("msg", "系统异常，查询资金来源失败.");
            return retMap;
        }
        BeanUtils.copyProperties(entity, updateEntity, AtlpUtil.getNullPropertyNames(entity));
        updateEntity.setLasttime(new Timestamp(new Date().getTime()));

        JzfpBZjLyEntity update = zjlyRepository.save(updateEntity);
        //判断修改是否成功
        if (AtlpUtil.isEmpty(update) || AtlpUtil.isEmpty(update.getLyid())) {
            logger.debug("参数异常,资金来源修改失败...资金来源信息==={}", entity.getLyid());
            retMap.put("code", "-1");
            retMap.put("msg", "系统异常，查询资金来源失败.");
            return retMap;
        }
        return retMap;
    }

    /**
     * 删除对应的资金来源信息
     *
     * @param entity
     * @return
     */
    @Override
    public Map<String, Object> doDelete(JzfpBZjLyEntity entity) {
        Map retMap = new HashMap();
        retMap.put("code", "0");
        retMap.put("msg", "成功");
        if (AtlpUtil.isEmpty(entity) || AtlpUtil.isEmpty(entity.getLyid())) {
            logger.debug("参数异常，资金来源删除失败...资金来源ID==={}", entity.getLyid());
            retMap.put("code", "-1");
            retMap.put("msg", "系统异常，资金来源删除失败");
            return retMap;
        }
        zjlyRepository.delete(entity);
        return retMap;
    }
}
