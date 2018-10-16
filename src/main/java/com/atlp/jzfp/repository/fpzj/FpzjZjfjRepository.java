package com.atlp.jzfp.repository.fpzj;

import com.atlp.jzfp.entity.fpzj.JzfpBZjFjEntity;
import org.atlp.base.BaseRepository;

import java.util.List;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/12 16:18
 * @Decription: 资金附件持久层接口
 */
public interface FpzjZjfjRepository extends BaseRepository<JzfpBZjFjEntity, Long> {
    /**
     * 根据附件id 查询资金附件信息
     */
    public JzfpBZjFjEntity findByFjid(String fjid);

    /**
     * 根据登记id 查询资金附件信息
     */
    public List<JzfpBZjFjEntity> findByDjid(String djid);
}
