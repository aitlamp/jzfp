package com.atlp.jzfp.repository.fpxm;

import org.atlp.base.BaseRepository;
import com.atlp.jzfp.entity.fpxm.JzfpBXmJdEntity;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-10 18:22
 * @Decription:
 */
public interface FpxmXmjdRepository extends BaseRepository<JzfpBXmJdEntity, Long> {

    /**
     * 主键进度id查询项目阶段
     * @param jdid
     * @return
     */
    public JzfpBXmJdEntity findByJdid(String jdid);

}
