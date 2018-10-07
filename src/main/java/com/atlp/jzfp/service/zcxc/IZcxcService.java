package com.atlp.jzfp.service.zcxc;

import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.entity.zcxc.JzfpBZcxcEntity;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-07 15:28
 * @Decription: 政策宣传服务层接口
 */
public interface IZcxcService {

    /**
     * 分页查询政策宣传
     * @param page
     * @return
     * @throws Exception
     */
    public Page<JzfpBZcxcEntity> getPage(PageModel page) throws Exception;

    /**
     * 查询所有政策宣传
     * @return
     * @throws Exception
     */
    public List<JzfpBZcxcEntity> getList() throws Exception;

    /**
     * 主键查询宣传政策
     * @param key
     * @return
     * @throws Exception
     */
    public JzfpBZcxcEntity getInfoById(String key) throws Exception;

    /**
     * 新增政策宣传
     * @param entity
     * @return
     * @throws Exception
     */
    public Map<String, Object> doSaveOrUpdate(JzfpBZcxcEntity entity, HttpServletRequest request) throws Exception;

    /**
     * 删除政策宣传
     * @param entity
     * @return
     * @throws Exception
     */
    public Map<String, Object> doDelete(JzfpBZcxcEntity entity) throws Exception;
}
