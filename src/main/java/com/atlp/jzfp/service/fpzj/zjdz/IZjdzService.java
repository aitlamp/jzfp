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

    public PageModel getPage(PageModel page);

    public JzfpBZjDzEntity getZjdzById(String dzid);

    public Boolean doSaveOrUpdate(JzfpBZjDzEntity entity);

    public Boolean doDelete(String dzid);
}
