package com.atlp.jzfp.service.fpxm.xmxx;

import com.atlp.jzfp.entity.fpxm.JzfpBXmXxEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-10 16:38
 * @Decription: 项目信息业务层接口
 */
public interface IXmxxService {

    /**
     * 项目信息保存
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public Map<String, Object> doSaveOrUpdate(JzfpBXmXxEntity entity, HttpServletRequest request) throws Exception;

}
