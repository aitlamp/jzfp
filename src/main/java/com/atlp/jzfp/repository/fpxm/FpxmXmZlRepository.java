package com.atlp.jzfp.repository.fpxm;

import com.atlp.jzfp.common.base.BaseRepository;
import com.atlp.jzfp.entity.fpxm.JzfpBXmZlEntity;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-08 17:14
 * @Decription: 项目资料持久层接口
 */
public interface FpxmXmZlRepository extends BaseRepository<JzfpBXmZlEntity, Long> {

    /**
     * 根据资料id和分类id查询项目资料
     * @param zlid
     * @param flid
     * @return
     */
    public JzfpBXmZlEntity findByZlidAndFlid(String zlid, String flid);

    /**
     * 主键id查询项目资料
     * @param zlid
     * @return
     */
    public JzfpBXmZlEntity findByZlid(String zlid);

    /**
     * 查询分类下的资料list
     * @param flid
     * @return
     */
    public List<JzfpBXmZlEntity> findByFlid(String flid);

}
