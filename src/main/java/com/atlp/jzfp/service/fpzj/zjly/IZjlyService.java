package com.atlp.jzfp.service.fpzj.zjly;

import org.atlp.data.PageModel;
import com.atlp.jzfp.entity.fpzj.JzfpBZjLyEntity;
import org.atlp.exception.BusinessException;

import java.util.Map;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/9 12:05
 * @Decription: 资金来源业务层接口
 */

public interface IZjlyService {
    /**
     * 资金来源信息分页展示
     *
     * @param page
     * @return
     * @throws Exception
     */
    public Map<String, Object> getPage(PageModel page) throws Exception;

    /**
     * 添加资金来源
     *
     * @param entity
     * @return
     * @throws Exception
     */
    public Boolean doSaveOrUpdate(JzfpBZjLyEntity entity) throws BusinessException;

    /**
     * 修改资金来源
     * 修改状态
     *
     * @param entity
     * @return
     * @throws Exception
     */
    public Map<String, Object> doUpdate(JzfpBZjLyEntity entity) throws Exception;

    /**
     * 删除资金来源信息
     *
     * @param entiy
     * @return
     */
    public Map<String, Object> doDelete(JzfpBZjLyEntity entiy);
}
