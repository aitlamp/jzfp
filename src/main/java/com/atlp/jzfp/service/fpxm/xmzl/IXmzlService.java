package com.atlp.jzfp.service.fpxm.xmzl;

import com.atlp.jzfp.entity.fpxm.JzfpBXmZlEntity;

import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-08 17:30
 * @Decription:
 */
public interface IXmzlService {

    /**
     * 添加项目分类资料
     * @param entity
     * @return
     * @throws Exception
     */
    public Map<String, Object> doSave(JzfpBXmZlEntity entity) throws Exception;

    /**
     * 删除项目分类资料
     * @param entity
     * @return
     * @throws Exception
     */
    public Map<String, Object> doDelete(JzfpBXmZlEntity entity) throws Exception;
}
