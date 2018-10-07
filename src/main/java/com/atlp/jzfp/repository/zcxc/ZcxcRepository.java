package com.atlp.jzfp.repository.zcxc;

import com.atlp.jzfp.common.base.BaseRepository;
import com.atlp.jzfp.entity.zcxc.JzfpBZcxcEntity;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-07 15:17
 * @Decription: 政策宣传持久层接口
 */
public interface ZcxcRepository extends BaseRepository<JzfpBZcxcEntity, Long> {

    /**
     * 主键宣传id查询信息
     * @param xcid
     * @return
     */
    public JzfpBZcxcEntity findByXcid(String xcid);

}
