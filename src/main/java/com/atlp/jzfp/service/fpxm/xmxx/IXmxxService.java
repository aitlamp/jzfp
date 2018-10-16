package com.atlp.jzfp.service.fpxm.xmxx;

import com.atlp.jzfp.entity.fpxm.JzfpBXmXxEntity;
import org.atlp.data.PageModel;
import org.atlp.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-10 16:38
 * @Decription: 项目信息业务层接口
 */
public interface IXmxxService {

    /**
     * 分页查询项目信息
     * @param page
     * @return
     * @throws BusinessException
     */
    public PageModel getPage(PageModel page) throws BusinessException;

    /**
     * 主键id查询项目信息
     * @param key
     * @return
     * @throws BusinessException
     */
    public JzfpBXmXxEntity getInfoByKey(String key) throws BusinessException;

    /**
     * 项目信息保存
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public boolean doSaveOrUpdate(JzfpBXmXxEntity entity, HttpServletRequest request) throws BusinessException;

}
