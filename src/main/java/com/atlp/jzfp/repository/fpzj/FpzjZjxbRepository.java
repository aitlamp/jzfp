package com.atlp.jzfp.repository.fpzj;

import com.atlp.jzfp.common.base.BaseRepository;
import com.atlp.jzfp.entity.fpzj.JzfpBZjXbEntity;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/11 17:28
 * @Decription: 资金下拨持久层接口
 */
public interface FpzjZjxbRepository extends BaseRepository<JzfpBZjXbEntity, Long> {
    /**
     * 查询对应主键的资金下拨信息
     *
     * @param dzid
     * @return
     */
    public JzfpBZjXbEntity findByDzid(String dzid);
}
