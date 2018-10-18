package com.atlp.jzfp.service.fpxm.xmzx;

import com.atlp.jzfp.common.base.IStaticInfo;
import com.atlp.jzfp.entity.fpxm.JzfpBXmFjEntity;
import com.atlp.jzfp.entity.fpxm.JzfpBXmJdEntity;
import com.atlp.jzfp.entity.fpxm.JzfpBXmXxEntity;
import com.atlp.jzfp.entity.fpxm.JzfpBXmZxEntity;
import com.atlp.jzfp.repository.fpxm.FpxmXmfjRepository;
import com.atlp.jzfp.repository.fpxm.FpxmXmjdRepository;
import com.atlp.jzfp.repository.fpxm.FpxmXmxxRepository;
import com.atlp.jzfp.repository.fpxm.FpxmXmzxRepository;
import com.atlp.jzfp.service.fpxm.xmfj.IXmfjService;
import lombok.extern.slf4j.Slf4j;
import org.atlp.data.ExceptionEnum;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-17 10:04
 * @Decription: 项目执行
 */
@Slf4j
@Service
@Transactional
public class XmzxServiceImpl implements IXmzxService {

    @Autowired
    private FpxmXmzxRepository xmzxRepository;
    @Autowired
    private FpxmXmjdRepository xmjdRepository;
    @Autowired
    private FpxmXmxxRepository xmxxRepository;
    @Autowired
    private FpxmXmfjRepository xmfjRepository;
    @Autowired
    private IXmfjService iXmfjService;

    @Override
    public JzfpBXmZxEntity getInfoByKey(String key) throws BusinessException {
        // 1. 查询阶段执行信息
        JzfpBXmZxEntity xmZxEntity = xmzxRepository.findByZxid(key);
        if (AtlpUtil.isEmpty(xmZxEntity)) {
            log.debug("参数异常，查询项目执行情况失败...执行id==={}", key);
            throw new BusinessException(ExceptionEnum.ERROR.getCode(), "查询项目执行情况失败.");
        }

        // 2. 项目信息
        JzfpBXmXxEntity xmXxEntity = xmxxRepository.findByXmid(xmZxEntity.getXmid());
        xmZxEntity.setXmmc(xmXxEntity.getXmmc());

        // 3. 阶段上次执行进度
        BigDecimal jdljjd = new BigDecimal(Double.toString(xmZxEntity.getLjzxjd()));    // 阶段累计执行进度
        BigDecimal bczxjd = new BigDecimal(Double.toString(xmZxEntity.getBczxjd()));    // 阶段本次执行进度
        // 计算阶段上次累计执行进度
        BigDecimal sczxjd = jdljjd.subtract(bczxjd);
        xmZxEntity.setSczxjd(sczxjd.doubleValue());

        // 4. 项目累计执行进度
        Double completeRate = this.workProjectStageTotalCompleteRate(xmZxEntity.getXmid(),
                xmZxEntity.getJdid(), xmZxEntity.getLjzxjd());
        xmZxEntity.setXmljjd(completeRate);

        return xmZxEntity;
    }

    @Override
    public Boolean doSaveOrUpdate(JzfpBXmZxEntity entity, HttpServletRequest request) throws BusinessException {
        // 计算本次进度完成进度
        BigDecimal jdljjd = new BigDecimal(Double.toString(entity.getLjzxjd())); // 阶段累计完成进度（率）
        BigDecimal jdscjd = new BigDecimal(Double.toString(entity.getSczxjd())); // 阶段上次完成进度
        // 本次完成进度
        BigDecimal bcwcjd = jdljjd.subtract(jdscjd);
        entity.setBczxjd(bcwcjd.doubleValue());

        // todo 完成时间
        entity.setWcsj(new Timestamp(new Date().getTime()));

        JzfpBXmZxEntity saveEntity = new JzfpBXmZxEntity();
        if (AtlpUtil.isEmpty(entity.getZxid())) {
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            AtlpUtil.setUserInfo(saveEntity, request);
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("保存");
        } else {
            saveEntity = xmzxRepository.findByZxid(entity.getZxid());
            if (AtlpUtil.isEmpty(saveEntity)) {
                log.debug("参数异常，查询项目执行情况失败...执行id==={}", entity.getZxid());
                throw new BusinessException(ExceptionEnum.ERROR.getCode(), "查询项目执行情况失败.");
            }
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
        }

        // 保存执行情况
        JzfpBXmZxEntity save = xmzxRepository.save(saveEntity);
        if (null == save || null == save.getZxid()) {
            log.debug("添加或修改项目执行情况失败...原项目执行情况==={}，新项目执行情况==={}",
                    saveEntity.toString(), save.toString());
            throw new BusinessException(ExceptionEnum.ERROR.getCode(), "维护项目执行信息失败..");
        }

        // 项目所需附件
        List<JzfpBXmFjEntity> xmFjEntityList = entity.getXmFjEntityList();
        // 附件list不为空，则表示有附件上传
        if (!AtlpUtil.isEmpty(xmFjEntityList)) {
            for (JzfpBXmFjEntity xmFjEntity : xmFjEntityList) {
                // 项目附件id查询附件是否存在，不存在就直接添加
                // JzfpBXmFjEntity fjEntity = xmfjRepository.findByFjid(xmFjEntity.getFjid());
                if (AtlpUtil.isEmpty(xmFjEntity.getFjid())) {
                    xmFjEntity.setXmid(entity.getXmid());
                    xmFjEntity.setJdid(entity.getJdid());
                    xmFjEntity.setZxid(save.getZxid());
                    iXmfjService.doSave(xmFjEntity, request);
                }
            }
        }

        return true;
    }

    @Override
    public JzfpBXmZxEntity getProjectStageTotalCompleteRate(String jdid) throws BusinessException {
        JzfpBXmZxEntity xmZxEntity = null;

        // 查询阶段执行list
        List<JzfpBXmZxEntity> xmZxEntityList =
                xmzxRepository.findAllByJdid(jdid, new Sort(Sort.Direction.DESC, "firsttime"));
        if (AtlpUtil.isEmpty(xmZxEntityList)) {
            xmZxEntity = new JzfpBXmZxEntity();
            xmZxEntity.setLjzxjd(new Double("0"));
        } else {
            xmZxEntity = xmZxEntityList.get(0);
        }

        return xmZxEntity;
    }

    @Override
    public Double workProjectStageTotalCompleteRate(String xmid, String jdid, Double jdljwcl) throws BusinessException {
        // 计算项目当前阶段的完成率
        JzfpBXmJdEntity xmJdEntity = xmjdRepository.findByJdid(jdid);
        if (AtlpUtil.isEmpty(xmJdEntity)) {
            log.debug("查询项目阶段失败...阶段id==={}", jdid);
            throw new BusinessException(ExceptionEnum.ERROR.getCode(), "查询项目阶段失败.");
        }
        // 计算当前阶段项目累计完成率，阶段累计完成率*阶段占比
        BigDecimal gzlzb = new BigDecimal(Double.toString(xmJdEntity.getGzlzb()));  // 阶段工作量占比
        BigDecimal ljwcl = new BigDecimal(Double.toString(jdljwcl));    // 阶段累计完成率
        BigDecimal xmljwcl = gzlzb.multiply(ljwcl);     // 项目累计完成率
        // 当前阶段项目累计完成率转百分比
        BigDecimal wclPercent = xmljwcl.divide(new BigDecimal(IStaticInfo.WCL), BigDecimal.ROUND_HALF_UP);

        // 查询判断其他阶段是否已上报执行进度
        // 1. 首先查询项目其余阶段信息
        List<JzfpBXmJdEntity> xmJdEntityList = xmjdRepository.findAllByXmid(xmid);
        if (xmJdEntityList.size() != 1) {
            for (JzfpBXmJdEntity jdEntity : xmJdEntityList) {
                // 2. 判断是否是当前阶段
                if (!jdid.equals(jdEntity.getJdid())) {
                    // 3. 查询阶段的执行进度
                    JzfpBXmZxEntity xmZxEntity = this.getProjectStageTotalCompleteRate(jdEntity.getJdid());
                    // 4. 计算项目执行进度
                    BigDecimal xmjdljjd = new BigDecimal(Double.toString(xmZxEntity.getLjzxjd()));  // 阶段累计执行进度
                    BigDecimal jdzb = new BigDecimal(Double.toString(jdEntity.getGzlzb()));
                    BigDecimal xmljjd = xmjdljjd.multiply(jdzb);
                    // 5. 项目执行进度转百分比
                    BigDecimal xmjdPercent = xmljjd.divide(new BigDecimal(IStaticInfo.WCL), BigDecimal.ROUND_HALF_UP);
                    // 6. 所有项目阶段执行进度相加
                    wclPercent = wclPercent.add(xmjdPercent);
                }
            }
        }

        return wclPercent.doubleValue();
    }
}
