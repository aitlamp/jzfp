package com.atlp.jzfp.repository.fpzj;

import com.atlp.jzfp.entity.fpzj.JzfpBZjSydjEntity;
import org.atlp.base.BaseRepository;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/12 16:27
 * @Decription: 资金使用持久层接口
 */
public interface FpzjZjsydjRepository extends BaseRepository<JzfpBZjSydjEntity, Long> {
    /**
     * 查询对应的资金使用登记
     */
    JzfpBZjSydjEntity findByDjid(String djid);
}
