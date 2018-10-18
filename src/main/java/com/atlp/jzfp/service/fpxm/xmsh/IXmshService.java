package com.atlp.jzfp.service.fpxm.xmsh;

import org.atlp.data.PageModel;
import org.atlp.exception.BusinessException;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-18 15:06
 * @Decription: 项目、阶段审核业务层接口
 */
public interface IXmshService {

    /**
     * 分页查询审核项目
     * @param page
     * @return
     * @throws BusinessException
     */
    public PageModel getJdshPage(PageModel page) throws BusinessException;
}
