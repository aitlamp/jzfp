package com.atlp.jzfp.service.fpzj.zjxb;

import org.atlp.data.PageModel;
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
    public PageModel getPage(PageModel page);

    /**
     * 新增或修改资金下拨数据信息
     *
     * @param entity
     * @return
     */
    public Boolean doSaveOrUpdate(JzfpBZjXbEntity entity);

    /**
     * 查询对应的资金下拨数据信息
     *
     * @param entity
     * @return
     */
    public JzfpBZjXbEntity getZjxbById(JzfpBZjXbEntity entity);

    /**
     * 删除对应的资金下拨数据信息
     *
     * @param entity
     * @return
     */
    public Boolean doDelete(JzfpBZjXbEntity entity);
}
