package com.atlp.jzfp.service.fpzj.zjdz;

import org.atlp.data.PageModel;
import com.atlp.jzfp.entity.fpzj.JzfpBZjDzEntity;

import java.util.Map;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/10 11:04
 * @Decription: 资金到账业务层接口
 */
public interface IZjdzService {
    /**
     * 资金到账分页展示
     *
     * @param page
     * @return
     */
    public PageModel getPage(PageModel page);

    /**
     * 查看资金到账的详细信息
     *
     * @param entity
     * @return
     */
    public JzfpBZjDzEntity getZjdzById(JzfpBZjDzEntity entity);

    /**
     * 增加或修改资金到账信息
     *
     * @param entity
     * @return
     */
    public Boolean doSaveOrUpdate(JzfpBZjDzEntity entity);

    /**
     * 删除对应的资金到账数据信息
     *
     * @param entity
     * @return
     */
    public Boolean doDelete(JzfpBZjDzEntity entity);
}
