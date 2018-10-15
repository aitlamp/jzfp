package com.atlp.jzfp.repository.fpxm;

import org.atlp.base.BaseRepository;
import com.atlp.jzfp.entity.fpxm.JzfpBXmXxEntity;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-10 16:37
 * @Decription: 项目信息持久层接口
 */
public interface FpxmXmxxRepository extends BaseRepository<JzfpBXmXxEntity, Long> {

    /**
     * 主键id查询项目信息
     * @param xmid
     * @return
     */
    public JzfpBXmXxEntity findByXmid(String xmid);

}
