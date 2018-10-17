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

}
