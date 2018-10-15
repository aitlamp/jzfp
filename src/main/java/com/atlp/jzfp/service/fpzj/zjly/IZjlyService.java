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

    public PageModel getPage(PageModel page);

    public Boolean doSaveOrUpdate(JzfpBZjLyEntity entity) throws BusinessException;

    public Boolean doUpdate(JzfpBZjLyEntity entity) throws Exception;

    public Boolean doDelete(String lyid);
}
