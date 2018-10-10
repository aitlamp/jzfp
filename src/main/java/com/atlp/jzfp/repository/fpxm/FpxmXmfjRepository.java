package com.atlp.jzfp.repository.fpxm;

import com.atlp.jzfp.common.base.BaseRepository;
import com.atlp.jzfp.entity.fpxm.JzfpBXmFjEntity;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-09 16:36
 * @Decription: 项目附件持久层接口
 */
public interface FpxmXmfjRepository extends BaseRepository<JzfpBXmFjEntity, Long> {

    /**
     * 主键附件id查询项目附件
     * @param fjid
     * @return
     */
    public JzfpBXmFjEntity findByFjid(String fjid);

    /**
     * 资料id查询项目附件
     * @param zlid
     * @return
     */
    public JzfpBXmFjEntity findByZlid(String zlid);

}
