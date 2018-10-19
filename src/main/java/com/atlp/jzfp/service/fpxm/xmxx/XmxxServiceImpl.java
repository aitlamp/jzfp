package com.atlp.jzfp.service.fpxm.xmxx;

import com.atlp.jzfp.common.base.IStaticInfo;
import com.atlp.jzfp.entity.fpxm.JzfpBXmJdEntity;
import com.atlp.jzfp.entity.fpxm.JzfpBXmXxEntity;
import com.atlp.jzfp.repository.fpxm.FpxmXmjdRepository;
import com.atlp.jzfp.repository.fpxm.FpxmXmxxRepository;
import com.atlp.jzfp.service.fpxm.xmjd.IXmjdService;
import lombok.extern.slf4j.Slf4j;
import org.atlp.data.ExceptionEnum;
import org.atlp.data.PageModel;
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
import java.util.Date;
import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-10 16:39
 * @Decription:
 */
@Slf4j
@Service
@Transactional
public class XmxxServiceImpl implements IXmxxService {

    @Autowired
    private FpxmXmxxRepository xmxxRepository;
    @Autowired
    private FpxmXmjdRepository xmjdRepository;
    @Autowired
    private IXmjdService iXmjdService;

    @Override
    public PageModel getPage(PageModel page) throws BusinessException {
        // 查询语句
        String sql = "select t.xmid, t.xmjc, t.szx, t.szxz, t.dlmc, t.xlmc, t.kssj, t.jssj, t.zrrmc, t.ysje " +
                " from jzfp_b_xm_xx t ";
        page = xmxxRepository.findPageBySql(sql, page);

        // 查询list无数据
        if (AtlpUtil.isEmpty(page.getRows())) {
            log.debug("项目信息空空如也...资料信息==={}", page.toString());
            throw new BusinessException(ExceptionEnum.ERROR.getCode(), "项目信息空空如也.");
        }

        return page;
    }

    @Override
    public JzfpBXmXxEntity getInfoByKey(String key) throws BusinessException {
        // 查询项目信息
        JzfpBXmXxEntity xmXxEntity = xmxxRepository.findByXmid(key);
        if (AtlpUtil.isEmpty(xmXxEntity)) {
            log.debug("查询项目信息失败...项目id==={}", key);
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "查询项目信息失败.");
        }

        // 查询项目阶段
        List<JzfpBXmJdEntity> xmJdEntityList = xmjdRepository.findAllByXmid(key);
        if (AtlpUtil.isEmpty(xmJdEntityList)) {
            log.debug("查询项目阶段信息失败...项目id==={}", key);
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "查询项目阶段信息失败.");
        } else {
            xmXxEntity.setXmJdEntityList(xmJdEntityList);
        }

        return xmXxEntity;
    }

    @Override
    public boolean doSaveOrUpdate(JzfpBXmXxEntity entity, HttpServletRequest request) throws BusinessException {
        // 解析项目开始日期和结束日期
        entity = this.AnalyseXmStartDate(entity);
        entity = this.AnalyseXmEndDate(entity);
        // 解析项目阶段开始日期和结束日期
        entity = this.AnalyseJdStartDate(entity);
        entity = this.AnalyseJdEndDate(entity);
        // 比较时间和工作占比
        this.compareDate(entity);
        this.compareGzzb(entity);

        JzfpBXmXxEntity saveEntity = new JzfpBXmXxEntity();
        // 判断主键id，增加或是修改
        if (AtlpUtil.isEmpty(entity.getXmid())) {
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            AtlpUtil.setUserInfo(saveEntity, request);
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");
        } else {
            // 查询原项目信息是否存在
            saveEntity = xmxxRepository.findByXmid(entity.getXmid());
            if (AtlpUtil.isEmpty(saveEntity)) {
                log.debug("参数异常，查询项目信息失败...项目信息id==={}", entity.getXmid());
                throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "参数异常，查询项目信息失败.");
            }
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
        }

        // 数据保存
        JzfpBXmXxEntity save = xmxxRepository.save(saveEntity);
        if (null == save || null == save.getXmid()) {
            log.debug("添加或修改项目信息失败...原项目信息信息==={}，新项目信息信息==={}",
                    saveEntity.toString(), save.toString());
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "参数异常，维护项目信息失败.");
        }

        // 项目阶段
        List<JzfpBXmJdEntity> entityList = entity.getXmJdEntityList();
        for (JzfpBXmJdEntity xmJdEntity : entityList) {
            // 项目阶段主键id是否存在
            if (AtlpUtil.isEmpty(xmJdEntity.getJdid())) {
                // 新添加项目阶段，项目id数据填充
                xmJdEntity.setXmid(save.getXmid());
            }
            iXmjdService.doSaveOrUpdate(save, xmJdEntity, request);
        }

        return true;
    }

    /**
     * 解析项目开始日期
     * 选择上半月或者下半月
     * @param entity
     * @throws BusinessException
     */
    public JzfpBXmXxEntity AnalyseXmStartDate(JzfpBXmXxEntity entity) throws BusinessException {
        // 1. 取得计划开始年月
        Date jhksym = new Date(DateTimeUtil.parseDate(entity.getJhkssj()).getTime());
        // 2. 取得日期年、月份
        String year = DateTimeUtil.getDateYear(jhksym);
        String month = DateTimeUtil.getDateMonth(jhksym);
        entity.setNd(year);

        // 计划开始日期
        if (IStaticInfo.PROJECT_DATE_FIRST.equals(entity.getKssjb())) { // 上半月
            // 3. 日期拼接
            String dateStr = year + "-" + month + "-01";
            // 4. string日期转date
            Date date = DateTimeUtil.parseDate(dateStr);
            entity.setKssj(new Timestamp(date.getTime()));
        } else if (IStaticInfo.PROJECT_DATE_AFTER.equals(entity.getKssjb())) { // 下半月
            // 3. 日期拼接
            String dateStr = year + "-" + month + "-15";
            // 4. string日期转date
            Date date = DateTimeUtil.parseDate(dateStr);
            entity.setKssj(new Timestamp(date.getTime()));
        } else {
            log.debug("项目计划开始日期参数错误...项目信息==={}", entity.toString());
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "项目计划开始日期参数错误.");
        }

        return entity;
    }

    /**
     * 解析项目结束日期
     * 选择上半月或者下半月
     * @param entity
     * @throws BusinessException
     */
    public JzfpBXmXxEntity AnalyseXmEndDate(JzfpBXmXxEntity entity) throws BusinessException {
        // 1. 取得计划开始年月
        Date jhjsym = new Date(DateTimeUtil.parseDate(entity.getJhjssj()).getTime());
        // 2. 取得日期年、月份
        String year = DateTimeUtil.getDateYear(jhjsym);
        String month = DateTimeUtil.getDateMonth(jhjsym);

        // 计划结束日期
        if (IStaticInfo.PROJECT_DATE_FIRST.equals(entity.getJssjb())) { // 上半月
            // 3. 日期拼接
            String dateStr = year + "-" + month + "-15";
            // 4. string日期转date
            Date date = DateTimeUtil.parseDate(dateStr);
            entity.setJssj(new Timestamp(date.getTime()));
        } else if (IStaticInfo.PROJECT_DATE_AFTER.equals(entity.getJssjb())) { // 下半月
            // 计算月底最后一天
            Date lastDay = DateTimeUtil.getLastDayOfMonth(Integer.parseInt(year), Integer.parseInt(month));
            /*// 3. 日期拼接
            String dateStr = DateTimeUtil.date2String(lastDay);
            // 4. string日期转date
            Date date = DateTimeUtil.parseDate(dateStr);*/
            entity.setJssj(new Timestamp(lastDay.getTime()));
        } else {
            log.debug("项目计划结束日期参数错误...项目信息==={}", entity.toString());
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "项目计划结束日期参数错误.");
        }

        return entity;
    }

    /**
     * 解析项目阶段开始日期
     * @param entity
     * @throws BusinessException
     */
    public JzfpBXmXxEntity AnalyseJdStartDate(JzfpBXmXxEntity entity) throws BusinessException {
        // 判断阶段信息是否为空
        List<JzfpBXmJdEntity> xmJdList = entity.getXmJdEntityList();
        if (AtlpUtil.isEmpty(xmJdList)) {
            log.debug("传入项目阶段信息为空，增加项目信息失败...项目信息==={}", entity.toString());
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "项目阶段信息为空.");
        }

        for (JzfpBXmJdEntity xmJdEntity : xmJdList) {
            // 1. 取得计划开始年月
            Date jhksym = new Date(DateTimeUtil.parseDate(xmJdEntity.getJhkssj()).getTime());
            // 2. 取得日期年、月份
            String year = DateTimeUtil.getDateYear(jhksym);
            String month = DateTimeUtil.getDateMonth(jhksym);

            // 计划开始日期
            if (IStaticInfo.PROJECT_DATE_FIRST.equals(xmJdEntity.getKssjb())) { // 上半月
                // 3. 日期拼接
                String dateStr = year + "-" + month + "-01";
                // 4. string日期转date
                Date date = DateTimeUtil.parseDate(dateStr);
                xmJdEntity.setKssj(new Timestamp(date.getTime()));
            } else if (IStaticInfo.PROJECT_DATE_AFTER.equals(xmJdEntity.getKssjb())) { // 下半月
                // 3. 日期拼接
                String dateStr = year + "-" + month + "-15";
                // 4. string日期转date
                Date date = DateTimeUtil.parseDate(dateStr);
                xmJdEntity.setKssj(new Timestamp(date.getTime()));
            } else {
                log.debug("项目计划结束日期参数错误...项目信息==={}", xmJdEntity.toString());
                throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "项目计划结束日期参数错误.");
            }

            xmJdEntity.setNd(year);
            // xmJdEntity.setYd(month);
            xmJdEntity.setKssjb(month + "." + xmJdEntity.getKssjb());
        }

        return entity;
    }

    /**
     * 解析项目阶段结束日期
     * @param entity
     * @throws BusinessException
     */
    public JzfpBXmXxEntity AnalyseJdEndDate(JzfpBXmXxEntity entity) throws BusinessException {
        // 判断阶段信息是否为空
        List<JzfpBXmJdEntity> xmJdList = entity.getXmJdEntityList();
        if (AtlpUtil.isEmpty(xmJdList)) {
            log.debug("传入项目阶段信息为空，增加项目信息失败...项目信息==={}", entity.toString());
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "项目阶段信息为空.");
        }

        for (JzfpBXmJdEntity xmJdEntity : xmJdList) {
            // 1. 取得计划开始年月
            Date jhjsym = new Date(DateTimeUtil.parseDate(xmJdEntity.getJhjssj()).getTime());
            // 2. 取得日期年、月份
            String year = DateTimeUtil.getDateYear(jhjsym);
            String month = DateTimeUtil.getDateMonth(jhjsym);

            // 计划结束日期
            if (IStaticInfo.PROJECT_DATE_FIRST.equals(xmJdEntity.getJssjb())) { // 上半月
                // 3. 日期拼接
                String dateStr = year + "-" + month + "-15";
                // 4. string日期转date
                Date date = DateTimeUtil.parseDate(dateStr);
                xmJdEntity.setJssj(new Timestamp(date.getTime()));
            } else if (IStaticInfo.PROJECT_DATE_AFTER.equals(xmJdEntity.getJssjb())) { // 下半月
                // 计算月底最后一天
                Date lastDay = DateTimeUtil.getLastDayOfMonth(Integer.parseInt(year), Integer.parseInt(month));
                /*// 3. 日期拼接
                String dateStr = year + "-" + month + "-" + lastDay;
                // 4. string日期转date
                Date date = DateTimeUtil.parseDate(dateStr);*/
                xmJdEntity.setJssj(new Timestamp(lastDay.getTime()));
            } else {
                log.debug("项目阶段计划结束日期参数错误...项目信息==={}", xmJdEntity.toString());
                throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "项目阶段计划结束日期参数错误.");
            }

            xmJdEntity.setJssjb(month + "." + xmJdEntity.getJssjb());
        }

        return entity;
    }

    /**
     * 项目信息计划日期与阶段日期比较
     * 阶段日期必须在项目日期之前，至少一个阶段开始日期=项目开始日期，至少一个阶段结束日期=项目结束日期
     * @param entity
     * @throws Exception
     */
    private void compareDate(JzfpBXmXxEntity entity) throws BusinessException {
        List<JzfpBXmJdEntity> xmJdList = entity.getXmJdEntityList();

        Date xmkssj = DateTimeUtil.timestamp2Date(entity.getKssj());
        Date xmjssj = DateTimeUtil.timestamp2Date(entity.getJssj());
        int ksnum = 0, jsnum = 0;
        for (JzfpBXmJdEntity xmJdEntity : xmJdList) {
            Date jdkssj = DateTimeUtil.timestamp2Date(xmJdEntity.getKssj());
            Date jdjssj = DateTimeUtil.timestamp2Date(xmJdEntity.getJssj());
            // 开始日期比较
            if (DateTimeUtil.getDateBetween(xmkssj, jdkssj) < 0) {
                log.debug("项目阶段开始日期低于项目计划开始日期，增加项目信息失败...项目信息==={}", entity.toString());
                throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "项目阶段日期与项目计划日期不匹配.");
            } else {
                if (DateTimeUtil.getDateBetween(xmkssj, jdkssj) == 0) {
                    ksnum ++;
                }
            }
            // 结束日期比较
            if (DateTimeUtil.getDateBetween(jdjssj, xmjssj) < 0) {
                log.debug("项目阶段结束日期低于项目计划结束日期，增加项目信息失败...项目信息==={}", entity.toString());
                throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "项目阶段日期与项目计划日期不匹配.");
            } else {
                if (DateTimeUtil.getDateBetween(jdjssj, xmjssj) == 0) {
                    jsnum ++;
                }
            }
        }
        if (ksnum == 0 || jsnum == 0) {
            log.debug("项目阶段日期与项目计划日期不匹配，增加项目信息失败...项目信息==={}", entity.toString());
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "项目阶段日期与项目计划日期不匹配.");
        }
    }

    /**
     * 阶段工作量占比总和等于100
     * @throws BusinessException
     */
    private void compareGzzb(JzfpBXmXxEntity entity) throws BusinessException {
        List<JzfpBXmJdEntity> xmJdList = entity.getXmJdEntityList();

        BigDecimal gzlzb = new BigDecimal(IStaticInfo.ZERO);
        for (JzfpBXmJdEntity xmJdEntity : xmJdList) {
            gzlzb = gzlzb.add(new BigDecimal(xmJdEntity.getGzlzb()));
        }
        if (gzlzb.compareTo(new BigDecimal(IStaticInfo.GZLZB)) != 0) {
            log.debug("项目阶段工作量占比总和不等于100，增加项目信息失败...项目信息==={}", entity.toString());
            throw new BusinessException(ExceptionEnum.ERROR_PARAM.getCode(), "项目阶段工作量占比总和不等于100.");
        }
    }
}
