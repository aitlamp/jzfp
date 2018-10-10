package com.atlp.jzfp.service.fpxm.xmfj;

import com.atlp.jzfp.entity.fpxm.JzfpBXmFjEntity;

import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-09 16:38
 * @Decription: 项目附件业务层接口
 */
public interface IXmfjService {

    /**
     * 添加项目附件
     * @param entity
     * @return
     * @throws Exception
     */
    public void doSave(JzfpBXmFjEntity entity) throws Exception;

    /**
     * 删除项目附件
     * @param entity
     * @return
     * @throws Exception
     */
    public Map<String, Object> doDelete(JzfpBXmFjEntity entity) throws Exception;

}
