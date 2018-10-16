package com.atlp.jzfp.repository.fpxm;

import org.atlp.base.BaseRepository;
import com.atlp.jzfp.entity.fpxm.JzfpBXmJdEntity;

import java.util.List;

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

    /**
     * 查询项目下的所有阶段
     * @param xmid
     * @return
     */
    public List<JzfpBXmJdEntity> findAllByXmid(String xmid);

}
