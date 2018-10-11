package com.atlp.jzfp.service.fpxm.xmjd;

import com.atlp.jzfp.entity.fpxm.JzfpBXmJdEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-10 18:26
 * @Decription: 项目阶段业务层接口
 */
public interface IXmjdService {

    /**
     * 主键id查询阶段信息
     * @param id
     * @return
     * @throws Exception
     */
    public JzfpBXmJdEntity getInfoById(String id) throws Exception;

    /**
     * 项目阶段维护
     * @param entity
     * @param request
     * @return
     * @throws Exception
     */
    public Map<String, Object> doSaveOrUpdate(JzfpBXmJdEntity entity, HttpServletRequest request) throws Exception;

    /**
     * 删除项目阶段
     * @param entity
     * @return
     * @throws Exception
     */
    public Map<String, Object> doDelete(JzfpBXmJdEntity entity) throws Exception;
}
