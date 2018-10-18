package com.atlp.jzfp.service.fpxm.xmfj;

import com.atlp.jzfp.entity.fpxm.JzfpBXmFjEntity;
import com.atlp.jzfp.repository.fpxm.FpxmXmfjRepository;
import org.atlp.data.ExceptionEnum;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-09 16:44
 * @Decription: 项目附件业务层实现类
 */
@Service
@Transactional
public class XmfjServiceImpl implements IXmfjService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FpxmXmfjRepository xmfjRepository;

    @Override
    public JzfpBXmFjEntity getInfoById(String id) throws BusinessException {
        return xmfjRepository.findByFjid(id);
    }

    @Override
    public void doSave(JzfpBXmFjEntity entity, HttpServletRequest request) throws BusinessException {
        JzfpBXmFjEntity saveEntity = new JzfpBXmFjEntity();

        BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
        AtlpUtil.setUserInfo(saveEntity, request);
        saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
        saveEntity.setLasttime(new Timestamp(new Date().getTime()));
        saveEntity.setDqzt("有效");

        // 执行数据库事务
        JzfpBXmFjEntity save = xmfjRepository.save(saveEntity);
        if (null == save || null == save.getFjid()) {
            logger.debug("添加项目分类资料附件失败...原项目分类资料附件信息==={}，新项目分类资料附件信息==={}",
                    saveEntity.toString(), save.toString());
            throw new BusinessException(ExceptionEnum.ERROR.getCode(), "添加项目附件失败.");
        }
    }

    @Override
    public Boolean doDelete(JzfpBXmFjEntity entity) throws BusinessException {
        xmfjRepository.delete(entity);

        return true;
    }

    @Override
    public List<Map> getXmsxfjByXmid(String xmid) throws BusinessException {
        // sql
        String sql = " select fj.fjid fjid, fj.file_name fileName, fj.path_url pathUrl, fj.zlid zlid " +
                " from jzfp_b_xm_fj fj " +
                " left join jzfp_b_xm_zl zl on zl.zlid = fj.zlid " +
                " left join jzfp_b_xm_xx xx on xx.dlid = zl.flid " +
                " where xx.xmid = '"+xmid+"' and fj.xmid is null order by zl.xssx ";
        List<Map> xmFjEntityList = xmfjRepository.findListMapBySql(sql);

        if (AtlpUtil.isEmpty(xmFjEntityList)) {
            logger.debug("查询项目附件失败...项目id==={}", xmid);
            throw new BusinessException(ExceptionEnum.ERROR.getCode(), "查询项目附件失败.");
        }

        return xmFjEntityList;
    }

    @Override
    public List<Map> getXmyscfjByXmid(String xmid) throws BusinessException {
        // sql
        String sql = "select fj.fjid fjid,fj.file_name fileName, fj.zlid zlid " +
                " from jzfp_b_xm_fj fj where fj.xmid = '"+ xmid +"' and fj.zlid is not null ";
        List<Map> xmFjEntityList = xmfjRepository.findListMapBySql(sql);

        /*if (AtlpUtil.isEmpty(xmFjEntityList)) {
            logger.debug("查询项目附件失败...项目id==={}", xmid);
            throw new BusinessException(ExceptionEnum.ERROR.getCode(), "查询项目附件失败.");
        }*/

        return xmFjEntityList;
    }

    @Override
    public List<Map> getZxjdyscfjByZxid(String zxid) throws BusinessException {
        // 进度支撑材料
        String jdfjsql = "select fj.fjid fjid, fj.file_name fileName from jzfp_b_xm_fj fj " +
                " where fj.zxid = '"+zxid+"' and fj.zlid is null ";
        return xmfjRepository.findListMapBySql(jdfjsql);
    }
}
