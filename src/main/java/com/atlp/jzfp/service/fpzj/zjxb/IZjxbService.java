package com.atlp.jzfp.service.fpzj.zjxb;

import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.entity.fpzj.JzfpBZjXbEntity;

import java.util.Map;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/11 15:46
 * @Decription: 资金下拨业务层接口
 */
public interface IZjxbService {
    /**
     * 资金下拨数据信息分页展示
     *
     * @param page
     * @return
     */
    public Map<String, Object> getPage(PageModel page);

    /**
     * 新增或修改资金下拨数据信息
     *
     * @param entity
     * @return
     */
    public Map<String, Object> doSaveOrUpdate(JzfpBZjXbEntity entity);

    /**
     * 查询对应的资金下拨数据信息
     *
     * @param dzid
     * @return
     */
    public Map<String, Object> getZjxbById(String dzid);

    /**
     * 删除对应的资金下拨数据信息
     *
     * @param entity
     * @return
     */
    public Map<String, Object> doDelete(JzfpBZjXbEntity entity);
}
