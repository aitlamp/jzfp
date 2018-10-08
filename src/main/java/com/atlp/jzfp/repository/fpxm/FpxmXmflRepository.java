package com.atlp.jzfp.repository.fpxm;

import com.atlp.jzfp.common.base.BaseRepository;
import com.atlp.jzfp.entity.fpxm.JzfpBXmFlEntity;

import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-08 15:04
 * @Decription: 项目分类持久层接口
 */
public interface FpxmXmflRepository extends BaseRepository<JzfpBXmFlEntity, Long> {

    /**
     * 主键分类id查询分类信息
     * @param flid
     * @return
     */
    public JzfpBXmFlEntity findByFlid(String flid);

    /**
     * 上级分类查询子级分类list
     * @param pflid
     * @return
     */
    public List<JzfpBXmFlEntity> findByPflid(String pflid);

}
