package com.atlp.jzfp.repository.fpzj;

import com.atlp.jzfp.common.base.BaseRepository;
import com.atlp.jzfp.entity.fpzj.JzfpBZjDzEntity;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/10 11:29
 * @Decription: 资金到账持久层接口
 */
public interface FpzjZjdzRepository extends BaseRepository<JzfpBZjDzEntity, Long> {
    /**
     * 根据主键查对应的资金到账信息
     *
     * @param dzid
     * @return
     */
    public JzfpBZjDzEntity findByDzid(String dzid);
}
