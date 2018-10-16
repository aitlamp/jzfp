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
    public PageModel getPage(PageModel page);

    public Boolean doSaveOrUpdate(JzfpBZjXbEntity entity);

    public JzfpBZjXbEntity getZjxbById(String dzid);

    public Boolean doDelete(String dzid);
}
