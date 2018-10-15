package com.atlp.jzfp.service.fpxm.xmzl;

import org.atlp.data.PageModel;
import com.atlp.jzfp.entity.fpxm.JzfpBXmZlEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-08 17:30
 * @Decription:
 */
public interface IXmzlService {

    /**
     * 分页查询项目资料
     * @param page
     * @return
     * @throws Exception
     */
    public Map<String, Object> getPage(PageModel page) throws Exception;

    /**
     * 查询项目资料信息
     * @param key
     * @return
     * @throws Exception
     */
    public Map<String, Object> getInfoByKey(String key) throws Exception;

    /**
     * 主键id查询资料信息
     * @param id
     * @return
     * @throws Exception
     */
    public JzfpBXmZlEntity getInfoById(String id) throws Exception;

    /**
     * 添加项目分类资料
     * @param entity
     * @return
     * @throws Exception
     */
    public Map<String, Object> doSaveOrUpdate(JzfpBXmZlEntity entity, HttpServletRequest request) throws Exception;

    /**
     * 删除项目分类资料
     * @param entity
     * @return
     * @throws Exception
     */
    public Map<String, Object> doDelete(JzfpBXmZlEntity entity) throws Exception;
}
