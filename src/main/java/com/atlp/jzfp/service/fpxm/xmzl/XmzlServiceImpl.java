package com.atlp.jzfp.service.fpxm.xmzl;

import org.atlp.data.PageModel;
import org.atlp.utils.AtlpUtil;
import com.atlp.jzfp.entity.fpxm.JzfpBXmFjEntity;
import com.atlp.jzfp.entity.fpxm.JzfpBXmFlEntity;
import com.atlp.jzfp.entity.fpxm.JzfpBXmZlEntity;
import com.atlp.jzfp.repository.fpxm.FpxmXmZlRepository;
import com.atlp.jzfp.repository.fpxm.FpxmXmfjRepository;
import com.atlp.jzfp.repository.fpxm.FpxmXmflRepository;
import com.atlp.jzfp.service.fpxm.xmfj.IXmfjService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-08 17:25
 * @Decription:
 */
@Service
@Transactional
public class XmzlServiceImpl implements IXmzlService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FpxmXmZlRepository xmZlRepository;
    @Autowired
    private FpxmXmfjRepository xmfjRepository;
    @Autowired
    private FpxmXmflRepository xmflRepository;
    @Autowired
    private IXmfjService iXmfjService;

    @Override
    public Map<String, Object> getPage(PageModel page) throws Exception {
        Map<String, Object> reMap = new HashMap();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        // 查询数据
        String sql = "select z.zlid,z.zlmc,f.flmc flmc,z.dqzt,z.xssx,z.lasttime from jzfp_b_xm_zl z" +
                " left join jzfp_b_xm_fl f on f.flid = z.flid " +
                " where z.flid = '"+ page.getPmap().get("flid").toString() +"' " +
                " order by z.xssx ";
        page = xmZlRepository.findPageBySql(sql, page);

        // 查询list无数据
        if (AtlpUtil.isEmpty(page.getRows())) {
            logger.debug("项目资料空空如也...资料信息==={}", page.toString());
            reMap.put("code", "-1");
            reMap.put("msg", "项目资料信息空空如也.");
            return reMap;
        }

        reMap.put("data", page);
        return reMap;
    }

    @Override
    public Map<String, Object> getInfoByKey(String key) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        // 查询项目资料信息
        JzfpBXmZlEntity xmZlEntity = xmZlRepository.findByZlid(key);
        if (null == xmZlEntity) {
            logger.debug("主键id查询项目资料失败,主键id==={}", key);
            reMap.put("code", "-1");
            reMap.put("msg", "查询项目资料失败.");
            return reMap;
        }

        // 查询项目所属的项目分类
        JzfpBXmFlEntity xmFlEntity = xmflRepository.findByFlid(xmZlEntity.getFlid());
        if (null == xmZlEntity) {
            logger.debug("查询项目资料所属分类失败,项目资料id==={}", xmZlEntity.getFlid());
            reMap.put("code", "-2");
            reMap.put("msg", "查询项目资料所属分类失败.");
            return reMap;
        }
        xmZlEntity.setFlmc(xmFlEntity.getFlmc());

        // 查询项目资料附件
        JzfpBXmFjEntity xmFjEntity = xmfjRepository.findByZlid(key);
        if (null == xmFjEntity) {
            reMap.put("code", "-3");
            reMap.put("msg", "查询项目附件失败.");
            return reMap;
        }
        xmZlEntity.setXmFjEntity(xmFjEntity);

        reMap.put("data", xmZlEntity);
        return reMap;
    }

    @Override
    public JzfpBXmZlEntity getInfoById(String id) throws Exception {
        // 查询项目资料信息
        JzfpBXmZlEntity xmZlEntity = xmZlRepository.findByZlid(id);

        if (null == xmZlEntity) {
            logger.debug("主键id查询项目资料失败,主键id==={}", id);
            xmZlEntity = new JzfpBXmZlEntity();
        }

        // 查询项目资料附件
        JzfpBXmFjEntity xmFjEntity = xmfjRepository.findByZlid(id);
        if (null == xmFjEntity) {
            logger.debug("项目资料id查询项目附件失败,资料id==={}", id);
            xmFjEntity = new JzfpBXmFjEntity();
        }
        xmZlEntity.setXmFjEntity(xmFjEntity);

        return xmZlEntity;
    }

    @Override
    public Map<String, Object> doSaveOrUpdate(JzfpBXmZlEntity entity, HttpServletRequest request) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        try {
            // 判断项目附件是否正常
            if (AtlpUtil.isEmpty(entity.getXmFjEntity())) {
                logger.debug("项目资料附件为空，添加项目分类资料失败...项目分类资料信息==={}",entity.toString());
                reMap.put("code", "-1");
                reMap.put("msg", "系统异常，添加项目分类资料失败.");
                return reMap;
            }

            JzfpBXmZlEntity saveEntity = new JzfpBXmZlEntity();
            if (AtlpUtil.isEmpty(entity.getZlid())) {
                BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
                // AtlpUtil.setUserInfo(saveEntity, request);
                saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
                saveEntity.setLasttime(new Timestamp(new Date().getTime()));
                saveEntity.setDqzt("有效");
            } else {
                // 查询当前资料
                saveEntity = xmZlRepository.findByZlid(entity.getZlid());
                if (AtlpUtil.isEmpty(saveEntity)) {
                    logger.debug("参数异常，查询项目资料失败...项目资料id==={}", entity.getZlid());
                    reMap.put("code", "-1");
                    reMap.put("msg", "系统异常，查询项目资料失败.");
                    return reMap;
                }
                BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
                saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            }

            // 执行数据库事务
            JzfpBXmZlEntity save = xmZlRepository.save(saveEntity);
            if (null == save || null == save.getZlid()) {
                logger.debug("添加项目分类资料失败...原项目分类资料信息==={}，新项目分类资料信息==={}",
                        saveEntity.toString(), save.toString());
                reMap.put("code", "-2");
                reMap.put("msg", "系统异常，添加项目分类资料失败.");
                return reMap;
            }

            // 项目资料添加成功，添加资料附件
            JzfpBXmFjEntity xmFjEntity = entity.getXmFjEntity();
            if (AtlpUtil.isEmpty(xmFjEntity.getFjid())) {
                xmFjEntity.setZlid(save.getZlid());
                xmFjEntity.setFileName(save.getZlmc());
                iXmfjService.doSave(xmFjEntity, request);
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception();
        }

        return reMap;
    }

    @Override
    public Map<String, Object> doDelete(JzfpBXmZlEntity entity) throws Exception {
        return null;
    }
}
