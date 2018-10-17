package com.atlp.jzfp.repository.fpzj;

import com.atlp.jzfp.entity.fpzj.JzfpBZjSydjEntity;
import org.atlp.base.BaseRepository;

import java.util.List;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/12 16:27
 * @Decription: 资金使用持久层接口
 */
public interface FpzjZjsydjRepository extends BaseRepository<JzfpBZjSydjEntity, Long> {
    /**
     * 查询对应的资金使用登记
     */
    public JzfpBZjSydjEntity findByDjid(String djid);

    /**
     * 查询项目的资金使用登记
     */
    public List<JzfpBZjSydjEntity> findByXmid(String xmid);

}
