package com.atlp.jzfp.repository.fpxm;

import com.atlp.jzfp.entity.fpxm.JzfpBXmZxEntity;
import org.atlp.base.BaseRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-17 10:06
 * @Decription:
 */
public interface FpxmXmzxRepository extends BaseRepository<JzfpBXmZxEntity, Long> {

    /**
     * 主键id查询执行情况
     * @param zxid
     * @return
     */
    public JzfpBXmZxEntity findByZxid(String zxid);

    /**
     * 查询阶段执行进度
     * @return
     */
    public List<JzfpBXmZxEntity> findAllByJdid(String jdid, Sort sort);

}
