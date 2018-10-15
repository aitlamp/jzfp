package com.atlp.jzfp.service.fpxm.xmfl;

import org.atlp.data.PageModel;
import com.atlp.jzfp.entity.fpxm.JzfpBXmFlEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-08 15:07
 * @Decription: 项目分类业务层接口
 */
public interface IXmflService {

    /**
     * 分页查询分类信息
     * @param page
     * @return
     * @throws Exception
     */
    public Map<String, Object> getPage(PageModel page) throws Exception;

    /**
     * 查询项目分类list
     * @return
     * @throws Exception
     */
    public List<JzfpBXmFlEntity> getList() throws Exception;

    /**
     * 查询所有项目分类
     * 拼接title，项目树专用
     * @return
     * @throws Exception
     */
    public Map<String, Object> getListWithTitle() throws Exception;

    /**
     * 根据上级分类if查询分类select
     * @param pflid
     * @return
     * @throws Exception
     */
    public Map<String, Object> getSelectByPflid(String pflid) throws Exception;

    /**
     * 根据上级分类id查询下级分类list
     * @param pflid
     * @return
     * @throws Exception
     */
    public List<JzfpBXmFlEntity> getListByPflid(String pflid) throws Exception;

    /**
     * 主键查询项目分类信息
     * @param key
     * @return
     * @throws Exception
     */
    public Map<String, Object> getInfoByKey(String key) throws Exception;

    /**
     * 主键查询项目分类信息
     * @param key
     * @return
     * @throws Exception
     */
    public JzfpBXmFlEntity getInfoById(String key) throws Exception;

    /**
     * 添加分类
     * @param entity
     * @return
     * @throws Exception
     */
    public Map<String, Object> doSaveOrUpdate(JzfpBXmFlEntity entity, HttpServletRequest request) throws Exception;

    /**
     * 修改项目分类
     * 修改状态
     * @param entity
     * @return
     * @throws Exception
     */
    public Map<String, Object> doUpdate(JzfpBXmFlEntity entity) throws Exception;
}
