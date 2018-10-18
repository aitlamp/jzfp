package com.atlp.jzfp.service.fpxm.xmzx;

import com.atlp.jzfp.entity.fpxm.JzfpBXmZxEntity;
import org.atlp.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-17 09:57
 * @Decription:
 */
public interface IXmzxService {

    /**
     * 主键id查询执行进度信息
     * @param key
     * @return
     * @throws BusinessException
     */
    public JzfpBXmZxEntity getInfoByKey(String key) throws BusinessException;

    /**
     * 确认保存项目执行信息
     * @param entity
     * @param request
     * @return
     * @throws BusinessException
     */
    public Boolean doSaveOrUpdate(JzfpBXmZxEntity entity, HttpServletRequest request) throws BusinessException;

    /**
     * 查询项目阶段累计完成率
     * @param jdid
     * @return
     * @throws BusinessException
     */
    public JzfpBXmZxEntity getProjectStageTotalCompleteRate(String jdid) throws BusinessException;

    /**
     * 计算项目累计完成率
     * @param xmid  项目id
     * @param jdid  阶段id
     * @param jdljwcl   阶段累计完成率
     * @return
     * @throws BusinessException
     */
    public Double workProjectStageTotalCompleteRate(String xmid, String jdid, Double jdljwcl) throws BusinessException;

}
