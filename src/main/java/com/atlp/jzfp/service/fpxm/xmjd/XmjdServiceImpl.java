package com.atlp.jzfp.service.fpxm.xmjd;

import com.atlp.jzfp.common.base.IStaticInfo;
import com.atlp.jzfp.entity.fpxm.JzfpBXmJdEntity;
import com.atlp.jzfp.entity.fpxm.JzfpBXmJdYbbEntity;
import com.atlp.jzfp.entity.fpxm.JzfpBXmXxEntity;
import com.atlp.jzfp.repository.fpxm.FpxmXmjdRepository;
import lombok.extern.slf4j.Slf4j;
import org.atlp.data.ExceptionEnum;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.atlp.utils.DateTimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-10 18:29
 * @Decription:
 */
@Slf4j
@Service
@Transactional
public class XmjdServiceImpl implements IXmjdService {

    @Autowired
    private FpxmXmjdRepository xmjdRepository;
    @Autowired
    private IxmjdYbbService ixmjdYbbService;

    @Override
    public JzfpBXmJdEntity getInfoByKey(String key) throws BusinessException {
        return xmjdRepository.findByJdid(key);
    }

    @Override
    public List<JzfpBXmJdEntity> getListByXmid(String xmid) throws BusinessException {
        List<JzfpBXmJdEntity> xmJdEntityList = xmjdRepository.findAllByXmid(xmid);
        if (AtlpUtil.isEmpty(xmJdEntityList)) {
            log.debug("参数异常，查询项目阶段信息失败...项目id==={}", xmid);
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "查询项目阶段信息失败.");
        }

        return xmJdEntityList;
    }

    @Override
    public boolean doSaveOrUpdate(JzfpBXmXxEntity xmXxEntity, JzfpBXmJdEntity xmJdEntity, HttpServletRequest request)
            throws BusinessException {
        JzfpBXmJdEntity saveEntity = new JzfpBXmJdEntity();
        // 判断主键id，增加或是修改
        if (AtlpUtil.isEmpty(xmJdEntity.getJdid())) {
            BeanUtils.copyProperties(xmJdEntity, saveEntity, AtlpUtil.getNullPropertyNames(xmJdEntity));
            AtlpUtil.setUserInfo(saveEntity, request);
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");
        } else {
            // 查询原项目信息是否存在
            saveEntity = xmjdRepository.findByJdid(xmJdEntity.getJdid());
            if (AtlpUtil.isEmpty(saveEntity)) {
                log.debug("参数异常，查询项目阶段信息失败...阶段信息id==={}", xmJdEntity.getXmid());
                throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "参数异常，查询项目阶段信息失败.");
            }
            BeanUtils.copyProperties(xmJdEntity, saveEntity, AtlpUtil.getNullPropertyNames(xmJdEntity));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
        }

        // 数据保存
        JzfpBXmJdEntity save = xmjdRepository.save(saveEntity);
        if (null == save || null == save.getJdid()) {
            log.debug("添加或修改项目阶段信息失败...原项目阶段信息==={}，新项目阶段信息==={}",
                    saveEntity.toString(), save.toString());
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "参数异常，维护项目阶段信息失败.");
        }

        // 项目阶段月度报表
        List<JzfpBXmJdYbbEntity> xmJdYbbEntityList = this.packageProjectStageMonthCharts(xmXxEntity, save);
        return ixmjdYbbService.doSave(xmJdYbbEntityList, request);
    }

    @Override
    public boolean doDelete(JzfpBXmJdEntity entity) throws BusinessException {
        xmjdRepository.delete(entity);

        return true;
    }

    /**
     * 包装项目阶段月度报表信息
     * @param xmXxEntity
     * @param xmJdEntity
     * @return
     * @throws BusinessException
     */
    private List<JzfpBXmJdYbbEntity> packageProjectStageMonthCharts(JzfpBXmXxEntity xmXxEntity,
              JzfpBXmJdEntity xmJdEntity) throws BusinessException {
        List<JzfpBXmJdYbbEntity> jdYbbEntityList = new ArrayList<>();

        // 1. 取得阶段计划开始日期半月和结束日期半月
        BigDecimal jhkssjyb = new BigDecimal(xmJdEntity.getKssjb());
        BigDecimal jhjssjyb = new BigDecimal(xmJdEntity.getJssjb());
        // 2. 计算开始和结束月半差，整数部分*2+余数*1+1
        BigDecimal sjc = jhjssjyb.subtract(jhkssjyb);
        // 3. 月半差整数部分
        double zhengs = Math.floor(sjc.doubleValue());
        BigDecimal zhengshu = new BigDecimal(Double.toString(zhengs));
        // 4. 月半差余数部分
        List<String> szList = Arrays.asList(sjc.toString().split("\\."));
        BigDecimal yushu = new BigDecimal(szList.get(1));
        if (yushu.compareTo(new BigDecimal(IStaticInfo.ZERO)) != 0) {
            yushu = new BigDecimal(1);
        }
        // 5. 计算总分段差数
        BigDecimal sjcall = zhengshu.multiply(new BigDecimal(2)).add(yushu).add(new BigDecimal(1));
        // 6. 计算个分段预计工作量
        BigDecimal gjdgzl = new BigDecimal(IStaticInfo.WCL).divide(sjcall, BigDecimal.ROUND_HALF_UP);

        // 取得项目阶段开始月和结束月
        String jhkssjyd = DateTimeUtil.getDateMonth(new Date(xmJdEntity.getKssj().getTime()));
        int ksyd = Integer.parseInt(jhkssjyd);
        String jhjssjyd = DateTimeUtil.getDateMonth(new Date(xmJdEntity.getJssj().getTime()));
        int jsyd = Integer.parseInt(jhjssjyd);
        // 开始半月和结束半月
        int ksyb = Integer.parseInt(Arrays.asList(jhkssjyb.toString().split("\\.")).get(1));    // jhkssjyb.intValue();
        int jsyb = Integer.parseInt(Arrays.asList(jhjssjyb.toString().split("\\.")).get(1));    // jhjssjyb.intValue();

        // 循环分段差数
        JzfpBXmJdYbbEntity jdYbbEntity = null;
        BigDecimal tjgzl = new BigDecimal(0);
        for (int i = 0; i < sjcall.intValue(); i++) {
            // 创建阶段月报表实体对象
            jdYbbEntity = new JzfpBXmJdYbbEntity();
            // 月报表项目信息
            jdYbbEntity.setXmid(xmXxEntity.getXmid());
            jdYbbEntity.setXmmc(xmXxEntity.getXmmc());
            jdYbbEntity.setXmjc(xmXxEntity.getXmjc());
            jdYbbEntity.setXmjhjssj(xmXxEntity.getKssj());
            jdYbbEntity.setXmjhjssj(xmXxEntity.getJssj());
            // 月报表阶段信息
            jdYbbEntity.setJdid(xmJdEntity.getJdid());
            jdYbbEntity.setJdmc(xmJdEntity.getJdmc());
            jdYbbEntity.setJdgzlzb(xmJdEntity.getGzlzb());
            jdYbbEntity.setJdjhkssj(xmJdEntity.getKssj());
            jdYbbEntity.setJdjhjssj(xmJdEntity.getJssj());
            // 日期--阶段年月度
            jdYbbEntity.setNd(DateTimeUtil.getDateYear(xmXxEntity.getKssj()));
            jdYbbEntity.setYd(ksyd+"");
            jdYbbEntity.setYb(ksyb+"");
            jdYbbEntity.setKsyb(ksyd + "." + ksyb);
            jdYbbEntity.setJsyb(jsyd + "." + jsyb);
            jdYbbEntity.setTotalyb(sjcall.intValue());
            // 计划比率
            jdYbbEntity.setJdjhzxl(gjdgzl.doubleValue());
            jdYbbEntity.setJdljjhzxl(gjdgzl.multiply(new BigDecimal((i+1))).doubleValue());
            if ((i+1) == sjcall.intValue()) {
                jdYbbEntity.setJdjhzxl(new BigDecimal(IStaticInfo.WCL).doubleValue());
                jdYbbEntity.setJdljjhzxl(new BigDecimal(IStaticInfo.WCL).doubleValue());
            }
            // 项目各阶段分段预计工作量
            BigDecimal xmgzl = gjdgzl.multiply(new BigDecimal(Double.toString(xmJdEntity.getGzlzb())))
                    .divide(new BigDecimal(IStaticInfo.WCL), BigDecimal.ROUND_HALF_UP);
            jdYbbEntity.setXmjhzxl(gjdgzl.multiply(new BigDecimal(Double.toString(xmJdEntity.getGzlzb())))
                    .divide(new BigDecimal(IStaticInfo.WCL), BigDecimal.ROUND_HALF_UP).doubleValue());
            jdYbbEntity.setXmljjhzxl(gjdgzl.multiply(new BigDecimal((i+1)))
                    .multiply(new BigDecimal(Double.toString(xmJdEntity.getGzlzb())))
                    .divide(new BigDecimal(IStaticInfo.WCL), BigDecimal.ROUND_HALF_UP).doubleValue());
            if ((i+1) == sjcall.intValue()) {
                // 剩余工作量
                BigDecimal sygzl = new BigDecimal(Double.toString(xmJdEntity.getGzlzb())).subtract(tjgzl);
                jdYbbEntity.setXmjhzxl(sygzl.multiply(new BigDecimal(IStaticInfo.WCL))
                        .divide(new BigDecimal(IStaticInfo.WCL), BigDecimal.ROUND_HALF_UP).doubleValue());
                jdYbbEntity.setXmljjhzxl(new BigDecimal(Double.toString(xmJdEntity.getGzlzb()))
                        .multiply(new BigDecimal(IStaticInfo.WCL))
                        .divide(new BigDecimal(IStaticInfo.WCL), BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            // 加入list
            jdYbbEntityList.add(jdYbbEntity);

            // 统计阶段总工作量
            tjgzl = tjgzl.add(xmgzl);

            // 每次循环，月半+1
            ksyb += 1;
            // 如果开始半月等于2，则开始月+1；如果开始半月等于1，则半月+1
            if (ksyb > 2) {
                ksyd += 1;
                ksyb = 1;
            }

            // 如果开始月度大于结束月度，结束
            if (ksyd > jsyd) break;
        }

        return jdYbbEntityList;
    }
}
