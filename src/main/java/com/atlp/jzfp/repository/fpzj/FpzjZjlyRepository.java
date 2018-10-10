package com.atlp.jzfp.repository.fpzj;

import com.atlp.jzfp.common.base.BaseRepository;
import com.atlp.jzfp.entity.fpzj.JzfpBZjLyEntity;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/9 11:27
 * @Decription: 资金来源持久层接口
 */
public interface FpzjZjlyRepository extends BaseRepository<JzfpBZjLyEntity, Long> {
    /**
     * 主键id查询资金来源信息
     * @param lyid
     * @return
     */
    public JzfpBZjLyEntity findByLyid(String lyid);
}
