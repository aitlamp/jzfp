package com.atlp.jzfp.service.fpzj.zjfj;

import com.atlp.jzfp.entity.fpzj.JzfpBZjFjEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/12 16:15
 * @Decription: 资金附件业务层接口
 */
public interface IZjfjService {
    public void doSave(JzfpBZjFjEntity zjFjEntity);

    public Boolean doDelete(String fjid);

    public Boolean doDeleteBydjid(String djid);

    public List<JzfpBZjFjEntity> getZjfjByDjid(String djid);
}
