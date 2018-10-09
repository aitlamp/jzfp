package com.atlp.jzfp.service.fpxm.xmfl;

import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.common.utils.AtlpUtil;
import com.atlp.jzfp.entity.fpxm.JzfpBXmFlEntity;
import com.atlp.jzfp.entity.fpxm.JzfpBXmZlEntity;
import com.atlp.jzfp.repository.fpxm.FpxmXmZlRepository;
import com.atlp.jzfp.repository.fpxm.FpxmXmflRepository;
import com.atlp.jzfp.service.fpxm.xmzl.IXmzlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-08 15:31
 * @Decription: 项目分类业务层实现类
 */
@Service
@Transactional
public class XmflServiceImpl implements IXmflService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FpxmXmflRepository xmflRepository;
    @Autowired
    private FpxmXmZlRepository xmZlRepository;
    @Autowired
    private IXmzlService iXmzlService;

    @Override
    public Map<String, Object> getPage(PageModel page) throws Exception {
        Map<String, Object> reMap = new HashMap();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        // 查询数据
        String sql = "select t.flid,t.xssx,t.flmc,t.sm,t.yhxm,t.lasttime from jzfp_b_xm_fl t where pflid = '"+ page.getPmap().get("pflid").toString() +"' ";
        String[][] columns = {{"xssx", "显示顺序", "100"},
                {"flmc", "分类名称", "80"},
                {"sm", "说明", "50"}, {"yhxm", "维护人", "80"},
                {"lasttime", "维护时间", "80"}};
        page = xmflRepository.findPageBySql(sql, page, columns);

        // 查询list无数据
        if (AtlpUtil.isEmpty(page.getRows())) {
            logger.debug("项目分类信息空空如也...分类信息==={}", page.toString());
            reMap.put("code", "-1");
            reMap.put("msg", "项目分类信息空空如也.");
            return reMap;
        }

        reMap.put("data", page);
        return reMap;
    }

    @Override
    public List<JzfpBXmFlEntity> getList() throws Exception {
        return xmflRepository.findAll();
    }

    @Override
    public Map<String, Object> getListWithTitle() throws Exception {
        Map<String, Object> reMap = new HashMap();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        List xmflList = xmflRepository.findAll(Sort.by("xssx"));

        Map<String, Object> map = new HashMap<>();
        map.put("flid", "root");
        map.put("pflid", "");
        map.put("flmc", "项目分类树");
        xmflList.add(map);

        reMap.put("data", xmflList);
        return reMap;
    }

    @Override
    public List<JzfpBXmFlEntity> getListByPflid(String pflid) throws Exception {
        return xmflRepository.findByPflid(pflid);
    }

    @Override
    public Map<String, Object> getInfoByKey(String key) throws Exception {
        Map<String, Object> reMap = new HashMap();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        // 查询项目分类信息
        JzfpBXmFlEntity xmFlEntity = xmflRepository.findByFlid(key);
        if (null == xmFlEntity) {
            logger.debug("查询项目分类信息失败...主键id==={}", key);
            reMap.put("code", "-1");
            reMap.put("msg", "查询项目分类信息失败.");
            return reMap;
        }

        // 项目分类相关资料
        List<JzfpBXmZlEntity> xmZlList = xmZlRepository.findByFlid(key);
        if (!AtlpUtil.isEmpty(xmZlList)) {
            xmFlEntity.setXmZlEntityList(xmZlList);
        }

        reMap.put("data", xmFlEntity);
        return reMap;
    }

    @Override
    public JzfpBXmFlEntity getInfoById(String key) throws Exception {
        return xmflRepository.findByFlid(key);
    }

    @Override
    public Map<String, Object> doSaveOrUpdate(JzfpBXmFlEntity entity) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        try {
            JzfpBXmFlEntity saveEntity = new JzfpBXmFlEntity();

            // 判断主键id，增加或修改
            if (AtlpUtil.isEmpty(entity.getFlid())) {
                BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
                // AtlpUtil.setUserInfo(saveEntity, request);
                saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
                saveEntity.setLasttime(new Timestamp(new Date().getTime()));
                saveEntity.setDqzt("有效");
            } else {
                saveEntity = xmflRepository.findByFlid(entity.getFlid());
                if (AtlpUtil.isEmpty(saveEntity)) {
                    logger.debug("参数异常，查询项目分类失败...项目分类id==={}", entity.getFlid());
                    reMap.put("code", "-1");
                    reMap.put("msg", "系统异常，查询项目分类失败.");
                    return reMap;
                }
                BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
                saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            }

            // 执行数据库事务
            JzfpBXmFlEntity save = xmflRepository.save(saveEntity);
            if (null == save || null == save.getFlid()) {
                logger.debug("添加或修改项目分类失败...原项目分类信息==={}，新项目分类信息==={}",
                        saveEntity.toString(), save.toString());
                reMap.put("code", "-2");
                reMap.put("msg", "系统异常，添加或修改项目分类失败.");
                return reMap;
            }

            // 项目分类添加成功，添加项目分类对应资料
            List<JzfpBXmZlEntity> xmZlEntityList = entity.getXmZlEntityList();
            if (!AtlpUtil.isEmpty(xmZlEntityList)) {
                // 项目附件list
                for (JzfpBXmZlEntity xmZlEntity : xmZlEntityList) {
                    // 根据项目附件id和项目分类id查询该附件是否存在，不存在该附件则直接添加
                    JzfpBXmZlEntity zlEntity = xmZlRepository.findByZlidAndFlid(xmZlEntity.getZlid(), entity.getFlid());
                    if (AtlpUtil.isEmpty(zlEntity)) {
                        iXmzlService.doSave(xmZlEntity);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception();
        }

        return reMap;
    }

    @Override
    public Map<String, Object> doUpdate(JzfpBXmFlEntity entity) throws Exception {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("code", "0");
        reMap.put("msg", "SUCCESS");

        // 查询原分类信息是否存在
        JzfpBXmFlEntity saveEntity = xmflRepository.findByFlid(entity.getFlid());
        if (AtlpUtil.isEmpty(saveEntity)) {
            logger.debug("查询项目分类失败...项目分类id==={}", entity.getFlid());
            reMap.put("code", "-1");
            reMap.put("msg", "系统异常，查询项目分类失败.");
            return reMap;
        }
        BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
        saveEntity.setLasttime(new Timestamp(new Date().getTime()));

        // 执行数据库事务
        JzfpBXmFlEntity save = xmflRepository.save(saveEntity);
        if (null == save || null == save.getFlid()) {
            logger.debug("修改项目分类失败...原项目分类信息==={}，新项目分类信息==={}",
                    saveEntity.toString(), save.toString());
            reMap.put("code", "-2");
            reMap.put("msg", "系统异常，修改项目分类失败.");
            return reMap;
        }

        return reMap;
    }
}
